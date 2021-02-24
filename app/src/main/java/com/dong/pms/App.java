package com.dong.pms;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import com.dong.pms.handler.BoardHandler;
import com.dong.pms.handler.MemberHandler;
import com.dong.pms.handler.ScheduleHandler;
import com.dong.pms.handler.SeatHandler;
import com.dong.util.Prompt;

public class App {

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  public static void main(String[] args) throws CloneNotSupportedException{

    BoardHandler boardHandler = new BoardHandler();
    MemberHandler memberHandler = new MemberHandler();
    ScheduleHandler scheduleHandler = new ScheduleHandler();
    SeatHandler seatHandler = new SeatHandler();

    System.out.println("[항공사 회원관리프로그램]");


    loop:
      while(true) {
        System.out.println("1. 회원관리, 2. 비행일정관리, 3. 좌석관리, 4. 칭찬게시판 ");
        String command = Prompt.inputString("명령> ");

        if (command.length() == 0)
          continue;

        commandStack.push(command);
        commandQueue.offer(command);
        try {
          switch (command) {
            case "1":
              memberHandler.category();
              break;
            case "2":
              scheduleHandler.category();
              break;
            case "3":
              seatHandler.category();
              break;
            case "4":
              boardHandler.category();
              break;
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
    Prompt.close();
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
