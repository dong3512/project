package com.dong.pms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
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

  public static void main(String[] args) {

    ArrayList<Seat> seatList = new ArrayList<>();

    loadBoards();

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

    Prompt.close();
  }

  static void saveBoards() {
    try (FileInputStream in = new FileInputStream("boards.data")){
      int size = in.read() << 8 | in.read();

      for (int i = 0; i < size; i++ ) {
        Board b = new Board();

        b.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int len = in.read() << 8 | in.read();
        byte[] buf = new byte[len];
        in.read(buf);
        b.setName(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        b.setTitle(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        b.setContent(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        b.setMessage(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        b.setWriter(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        b.setRegisteredDate(Date.valueOf(new String(buf, "UTF-8")));

        b.setViewCount(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        boardList.add(b);
      }
      System.out.println("게시글 데이터 로딩!");
    }catch (Exception e) {
      System.out.println("게시글 데이터 로딩 중 오류 발생!");
    }
  }

  static void loadBoards() {
    try (FileOutputStream out = new FileOutputStream("boards.data")){

      int size = boardList.size();
      out.write(size >> 8);
      out.write(size);

      for (Board b : boardList) {
        out.write(b.getNo() >> 24);
        out.write(b.getNo() >> 16);
        out.write(b.getNo() >> 8);
        out.write(b.getNo());

        byte[] buf = b.getName().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = b.getTitle().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = b.getContent().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = b.getMessage().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = b.getWriter().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = b.getRegisteredDate().toString().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        out.write(b.getViewCount() >> 24);
        out.write(b.getViewCount() >> 16);
        out.write(b.getViewCount() >> 8);
        out.write(b.getViewCount());
      }
      System.out.println("게시글 데이터 저장!");
    }catch(Exception e) {
      System.out.println("게시글 데이터를 파일로 저장 중 오류 발생!");
    }
  }

  static void saveMembers() {
    try (FileInputStream in = new FileInputStream("members.data")){
      int size = in.read() << 8 | in.read();

      for (int i = 0; i < size; i++ ) {
        Member m = new Member();

        m.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int len = in.read() << 8 | in.read();
        byte[] buf = new byte[len];
        in.read(buf);
        m.setName(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        m.setEmail(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        m.setPhoto(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        m.setHp(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        m.setRegisteredDate(Date.valueOf(new String(buf, "UTF-8")));

        memberList.add(m);
      }
      System.out.println("멤버 데이터 로딩!");
    }catch (Exception e) {
      System.out.println("멤버 데이터 로딩 중 오류 발생!");
    }
  }

  static void loadMembers() {
    try (FileOutputStream out = new FileOutputStream("members.data")){

      int size = memberList.size();
      out.write(size >> 8);
      out.write(size);

      for (Member m : memberList) {
        out.write(m.getNo() >> 24);
        out.write(m.getNo() >> 16);
        out.write(m.getNo() >> 8);
        out.write(m.getNo());

        byte[] buf = m.getName().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = m.getEmail().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = m.getPhoto().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = m.getHp().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = m.getRegisteredDate().toString().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

      }
      System.out.println("멤버 데이터 저장!");
    }catch(Exception e) {
      System.out.println("멤버 데이터를 파일로 저장 중 오류 발생!");
    }
  }

  static void saveSchedules() {
    try (FileInputStream in = new FileInputStream("schedules.data")){
      int size = in.read() << 8 | in.read();

      for (int i = 0; i < size; i++ ) {
        Schedule s = new Schedule();

        s.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int len = in.read() << 8 | in.read();
        byte[] buf = new byte[len];
        in.read(buf);
        s.setDestination(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        s.setAirno(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        s.setName(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        s.setDtime(Date.valueOf(new String(buf, "UTF-8")));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        s.setAtime(Date.valueOf(new String(buf, "UTF-8")));

        scheudleList.add(s);
      }
      System.out.println("멤버 데이터 로딩!");
    }catch (Exception e) {
      System.out.println("멤버 데이터 로딩 중 오류 발생!");
    }
  }

  static void loadMembers() {
    try (FileOutputStream out = new FileOutputStream("members.data")){

      int size = memberList.size();
      out.write(size >> 8);
      out.write(size);

      for (Member m : memberList) {
        out.write(m.getNo() >> 24);
        out.write(m.getNo() >> 16);
        out.write(m.getNo() >> 8);
        out.write(m.getNo());

        byte[] buf = m.getName().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = m.getEmail().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = m.getPhoto().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = m.getHp().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = m.getRegisteredDate().toString().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

      }
      System.out.println("멤버 데이터 저장!");
    }catch(Exception e) {
      System.out.println("멤버 데이터를 파일로 저장 중 오류 발생!");
    }
  }

  static void saveMembers() {
    try (FileInputStream in = new FileInputStream("members.data")){
      int size = in.read() << 8 | in.read();

      for (int i = 0; i < size; i++ ) {
        Member m = new Member();

        m.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int len = in.read() << 8 | in.read();
        byte[] buf = new byte[len];
        in.read(buf);
        m.setName(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        m.setEmail(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        m.setPhoto(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        m.setHp(new String(buf, "UTF-8"));

        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        m.setRegisteredDate(Date.valueOf(new String(buf, "UTF-8")));

        memberList.add(m);
      }
      System.out.println("멤버 데이터 로딩!");
    }catch (Exception e) {
      System.out.println("멤버 데이터 로딩 중 오류 발생!");
    }
  }

  static void loadMembers() {
    try (FileOutputStream out = new FileOutputStream("members.data")){

      int size = memberList.size();
      out.write(size >> 8);
      out.write(size);

      for (Member m : memberList) {
        out.write(m.getNo() >> 24);
        out.write(m.getNo() >> 16);
        out.write(m.getNo() >> 8);
        out.write(m.getNo());

        byte[] buf = m.getName().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = m.getEmail().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = m.getPhoto().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = m.getHp().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        buf = m.getRegisteredDate().toString().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

      }
      System.out.println("멤버 데이터 저장!");
    }catch(Exception e) {
      System.out.println("멤버 데이터를 파일로 저장 중 오류 발생!");
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
