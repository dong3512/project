package com.dong.pms;

import com.dong.pms.handler.BoardHandler;
import com.dong.pms.handler.MemberHandler;
import com.dong.pms.handler.ScheduleHandler;
import com.dong.pms.handler.SeatHandler;
import com.dong.util.Iterator;
import com.dong.util.Prompt;
import com.dong.util.Queue;
import com.dong.util.QueueIterator;
import com.dong.util.Stack;

public class App {

  static Stack commandStack = new Stack();
  static Queue commandQueue = new Queue();

  public static void main(String[] args) throws CloneNotSupportedException{

    BoardHandler boardHandler = new BoardHandler();
    MemberHandler memberHandler = new MemberHandler();
    ScheduleHandler scheduleHandler = new ScheduleHandler(memberHandler);
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
            printCommandHistory(new QueueIterator(commandQueue));
            break;
          case "quit":
          case "exit":
            System.out.println("사용해주셔서 감사합니다.");
            break loop;
          default:
            System.out.println("실행할 수 없는 명령입니다.");
        }
        System.out.println();
      }
    Prompt.close();
  }

  static void printCommandHistory(Iterator iterator) {

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
