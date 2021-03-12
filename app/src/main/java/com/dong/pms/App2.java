package com.dong.pms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
import com.dong.util.CsvObject;
import com.dong.util.ObjectFactory;
import com.dong.util.Prompt;

public class App2 {

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  static ArrayList<Board> boardList = new ArrayList<>();
  static ArrayList<Member> memberList = new ArrayList<>();
  static LinkedList<Schedule> scheduleList = new LinkedList<>();
  static LinkedList<Seat> seatList = new LinkedList<>();

  static File boardFile = new File("boards.csv");
  static File memberFile = new File("members.csv");
  static File scheduleFile = new File("schedules.csv");
  static File seatFile = new File("seats.csv");

  public static void main(String[] args) {


    loadObjects(boardFile, boardList, Board::new);
    loadObjects(memberFile, memberList, Member::new);
    loadObjects(scheduleFile, scheduleList, Schedule::new);
    loadObjects(seatFile, seatList, Seat::new);


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
        String command = com.dong.util.Prompt.inputString("명령> ");

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
              Command commandHandler = commandMap.get(command);

              if (commandHandler == null) {
                System.out.println("실행할 수 없는 명령입니다.");
              } else {
                commandHandler.service();
              }
          }
        } catch (Exception e) {
          System.out.println("------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", 
              e.getClass().getName(), e.getMessage());
          System.out.println("------------------------------------------");
        }
        System.out.println();
      }

    saveObjects(boardFile, boardList);
    saveObjects(memberFile, memberList);
    saveObjects(scheduleFile, scheduleList);
    saveObjects(seatFile, seatList);

    Prompt.close();
  }

  static <T> void loadObjects(File file, List<T> list, ObjectFactory<T> objFactory) {
    try (BufferedReader in = new BufferedReader(new FileReader(file))){
      String csvStr = null;
      while ((csvStr = in.readLine()) != null) {
        list.add(objFactory.create(csvStr));
      }
      System.out.printf("파일 %s 데이터 로딩!\n", file.getName());

    }catch (Exception e) {
      System.out.printf(" %s 파일 데이터 로딩 중 오류 발생!\n", file.getName());
    }
  }

  static <T extends CsvObject> void saveObjects(File file, List<T> list) {
    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))){
      for (CsvObject csvObj : list) {
        out.write(csvObj.toCsvString() + "\n");
      }
      System.out.printf("파일 %s 저장!\n", file.getName());
    }catch(Exception e) {
      System.out.printf("파일 %s 저장 중 오류 발생!\n", file.getName());
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
