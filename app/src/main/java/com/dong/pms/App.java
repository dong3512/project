package com.dong.pms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.dong.context.ApplicationContextListener;
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
import com.dong.pms.listener.AppListener;
import com.dong.pms.listener.FileListener;
import com.dong.util.Prompt;

public class App {

  List<ApplicationContextListener> listeners = new ArrayList<>();

  ArrayDeque<String> commandStack = new ArrayDeque<>();
  LinkedList<String> commandQueue = new LinkedList<>();

  Map<String,Object> appContext = new HashMap<>();

  public static void main(String[] args) {
    App app = new App();

    app.addApplicationContextListener(new AppListener());
    app.addApplicationContextListener(new FileListener());

    app.service();
  }

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }


  @SuppressWarnings("unchecked")
  public void service() {

    notifyOnServiceStarted();

    List<Board> boardList = (List<Board>) appContext.get("boardList");
    List<Member> memberList = (List<Member>) appContext.get("memberList");
    List<Schedule> scheduleList = (List<Schedule>) appContext.get("scheduleList");
    List<Seat> seatList = (List<Seat>) appContext.get("seatList");


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

    Prompt.close();

    notifyOnServiceStopped();
  }

  private void notifyOnServiceStarted() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(appContext);
    }
  }

  private void notifyOnServiceStopped() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(appContext);
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
