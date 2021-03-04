package com.dong.pms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import com.dong.pms.domain.Board;
import com.dong.pms.domain.Member;
import com.dong.pms.domain.Schedule;
import com.dong.pms.domain.Seat;
import com.dong.pms.handler.BoardAddHandler;
import com.dong.pms.handler.BoardDeleteHandler;
import com.dong.pms.handler.BoardDetailHandler;
import com.dong.pms.handler.BoardListHandler;
import com.dong.pms.handler.BoardUpdateHandler;
import com.dong.pms.handler.Command;
import com.dong.pms.handler.MemberAddHandler;
import com.dong.pms.handler.MemberDeleteHandler;
import com.dong.pms.handler.MemberDetailHandler;
import com.dong.pms.handler.MemberListHandler;
import com.dong.pms.handler.MemberUpdateHandler;
import com.dong.pms.handler.MemberValidatorHandler;
import com.dong.pms.handler.ScheduleAddHandler;
import com.dong.pms.handler.ScheduleDeleteHandler;
import com.dong.pms.handler.ScheduleDetailHandler;
import com.dong.pms.handler.ScheduleListHandler;
import com.dong.pms.handler.ScheduleUpdateHandler;
import com.dong.pms.handler.SeatAddHandler;
import com.dong.pms.handler.SeatDeleteHandler;
import com.dong.pms.handler.SeatDetailHandler;
import com.dong.pms.handler.SeatListHandler;
import com.dong.pms.handler.SeatUpdateHandler;
import com.dong.util.Prompt;

public class App2 {

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  static ArrayList<Board> boardList = new ArrayList<>();
  static ArrayList<Member> memberList = new ArrayList<>();
  static LinkedList<Schedule> scheduleList = new LinkedList<>();
  static ArrayList<Seat> seatList = new ArrayList<>();

  public static void main(String[] args) {


    loadBoards();
    loadMembers();
    loadSchedules();
    loadSeats();


    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("/board/add", new BoardAddHandler(boardList));
    commandMap.put("/board/list", new BoardListHandler(boardList));
    commandMap.put("/board/detail", new BoardDetailHandler(boardList));
    commandMap.put("/board/update", new BoardUpdateHandler(boardList));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardList));

    commandMap.put("/member/add", new MemberAddHandler(memberList));
    commandMap.put("/member/list", new MemberListHandler(memberList));
    commandMap.put("/member/detail", new MemberDetailHandler(memberList));
    commandMap.put("/member/update", new MemberUpdateHandler(memberList));
    commandMap.put("/member/delete", new MemberDeleteHandler(memberList));
    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);

    commandMap.put("/schedule/add", new ScheduleAddHandler(scheduleList, memberValidatorHandler));
    commandMap.put("/schedule/list", new ScheduleListHandler(scheduleList));
    commandMap.put("/schedule/detail", new ScheduleDetailHandler(scheduleList));
    commandMap.put("/schedule/update", new ScheduleUpdateHandler(scheduleList, memberValidatorHandler));
    commandMap.put("/schedule/delete", new ScheduleDeleteHandler(scheduleList));

    commandMap.put("/seat/add", new SeatAddHandler(seatList));
    commandMap.put("/seat/list", new SeatListHandler(seatList));
    commandMap.put("/seat/detail", new SeatDetailHandler(seatList));
    commandMap.put("/seat/update", new SeatUpdateHandler(seatList));
    commandMap.put("/seat/delete", new SeatDeleteHandler(seatList));


    System.out.println("[항공사 회원관리프로그램]");


    loop:
      while(true) {
        String command = Prompt.inputString("명령> ");

        if (command.length() == 0)
          continue;

        commandStack.push(command);
        commandQueue.offer(command);
        try {
          switch (command) {
            case "history":
              printCommandHistory(commandStack.iterator());
              break;
            case "history2":
              printCommandHistory(commandQueue.iterator());
              break;
            case "quit":
            case "exit":
              System.out.println("사용해주셔서 감사합니다.");
              break loop;
            default:
              System.out.println("실행할 수 없는 명령입니다.");
          }
        } catch (Exception e) {
          System.out.println("------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", 
              e.getClass().getName(), e.getMessage());
          System.out.println("------------------------------------------");
        }
        System.out.println();
      }

    saveBoards();
    saveMembers();
    saveSchedules();
    saveSeats();

    Prompt.close();
  }

  static void loadBoards() {
    try (DataInputStream in = new DataInputStream(new FileInputStream("boards.data"))){

      int size = in.readInt;

      for (int i = 0; i < size; i++ ) {
        Board b = new Board();
        b.setNo(in.readInt());
        b.setName(in.readUTF());
        b.setTitle(in.readUTF());
        b.setContent(in.readUTF());
        b.setMessage(in.readUTF());
        b.setWriter(in.readUTF());
        b.setRegisteredDate(Date.valueOf(in.readUTF()));
        b.setViewCount(in.readInt());

        boardList.add(b);
      }
      System.out.println("게시글 데이터 로딩!");
    }catch (Exception e) {
      System.out.println("게시글 데이터 로딩 중 오류 발생!");
    }
  }

  static void saveBoards() {
    try (DataOutputStream out = new DataOutputStream(new FileOutputStream("boards.data"))){

      out.writeInt(boardList.size());

      for (Board b : boardList) {
        out.writeInt(b.getNo());
        out.writeUTF(b.getName());
        out.writeUTF(b.getTitle());
        out.writeUTF(b.getContent());
        out.writeUTF(b.getMessage());
        out.writeUTF(b.getWriter());
        out.writeUTF(b.getRegisteredDate().toString());
        out.writeInt(b.getViewCount());

      }
      System.out.println("게시글 데이터 저장!");
    }catch(Exception e) {
      System.out.println("게시글 데이터를 파일로 저장 중 오류 발생!");
    }
  }

  static void loadMembers() {
    try (DataInputStream in = new DataInputStream(new FileInputStream("members.data"))){

      int size = in.readInt();

      for (int i = 0; i < size; i++ ) {
        Member m = new Member();
        m.setNo(in.readInt());
        m.setName(in.readUTF());
        m.setEmail(in.readUTF());
        m.setPhoto(in.readUTF());
        m.setHp(in.readUTF());
        m.setRegisteredDate(Date.valueOf(in.readUTF()));

        memberList.add(m);
      }
      System.out.println("회원 데이터 로딩!");
    }catch (Exception e) {
      System.out.println("회원 데이터 로딩 중 오류 발생!");
    }
  }

  static void saveMembers() {
    try (DataOutputStream out = new DataOutputStream(new FileOutputStream("members.data"))){

      out.writeInt(memberList.size());

      for (Member m : memberList) {
        out.writeInt(m.getNo());
        out.writeUTF(m.getName());
        out.writeUTF(m.getEmail());
        out.writeUTF(m.getPhoto());
        out.writeUTF(m.getHp());
        out.writeUTF(m.getRegisteredDate().toString());
      }
      System.out.println("회원 데이터 저장!");
    }catch(Exception e) {
      System.out.println("회원 데이터를 파일로 저장 중 오류 발생!");
    }
  }

  static void loadSchedules() {
    try (DataInputStream in = new DataInputStream(new FileInputStream("schedules.data"))){

      int size = in.readInt();

      for (int i = 0; i < size; i++ ) {
        Schedule s = new Schedule();
        s.setNo(in.readInt());
        s.setDestination(in.readUTF());
        s.setAirno(in.readUTF());
        s.setDestination(in.readUTF());
        s.setName(in.readUTF());
        s.setDtime(Time.valueOf(in.readUTF()));
        s.setAtime(Time.valueOf(in.readUTF()));

        scheduleList.add(s);
      }
      System.out.println("스케쥴 데이터 로딩!");
    }catch (Exception e) {
      System.out.println("스케쥴 데이터 로딩 중 오류 발생!");
    }
  }

  static void saveSchedules() {
    try (DataOutputStream out = new DataOutputStream(new FileOutputStream("schedules.data"))){


      out.writeInt(scheduleList.size());

      for (Schedule s : scheduleList) {
        out.writeInt(s.getNo());
        out.writeUTF(s.getDestination());
        out.writeUTF(s.getAirno());
        out.writeUTF(s.getName());
        out.writeUTF(s.getDtime().toString());
        out.writeUTF(s.getAtime().toString());

      }
      System.out.println("스케쥴 데이터 저장!");
    }catch(Exception e) {
      System.out.println("스케쥴 데이터를 파일로 저장 중 오류 발생!");
    }
  }

  static void loadSeats() {
    try (DataInputStream in = new DataInputStream(new FileInputStream("seats.data"))){
      int size = in.readInt();

      for (int i = 0; i < size; i++ ) {
        Seat t = new Seat();
        t.setNo(in.readInt());
        t.setMgrade(in.readUTF());
        t.setSgrade(in.readInt());
        t.setSno(in.readUTF());
        t.setEtc(in.readUTF());

        seatList.add(t);
      }
      System.out.println("좌석 데이터 로딩!");
    }catch (Exception e) {
      System.out.println("좌석 데이터 로딩 중 오류 발생!");
    }
  }

  static void saveSeats() {
    try (FileOutputStream out = new FileOutputStream("members.data")){

      out.write(seatList.size() >> 8);
      out.write(seatList.size());

      for (Seat t : seatList) {
        out.write(t.getNo() >> 24);
        out.write(t.getNo() >> 16);
        out.write(t.getNo() >> 8);
        out.write(t.getNo());

        byte[] buf = t.getMgrade().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        out.write(t.getSgrade() >> 24);
        out.write(t.getSgrade() >> 16);
        out.write(t.getSgrade() >> 8);
        out.write(t.getSgrade());

        buf = t.getSno().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = t.getEtc().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

      }
      System.out.println("좌석 데이터 저장!");
    }catch(Exception e) {
      System.out.println("좌석 데이터를 파일로 저장 중 오류 발생!");
    }
  }

  static void printCommandHistory(Iterator<String> iterator) {

    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      if ((++count % 5) == 0) {
        String input = Prompt.inputString(": ");
        if (input.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

}
