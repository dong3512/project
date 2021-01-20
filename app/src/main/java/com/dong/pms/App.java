package com.dong.pms;

import com.dong.pms.handler.MemberHandler;
import com.dong.pms.handler.ScheduleHandler;
import com.dong.pms.handler.SeatHandler;
import com.dong.util.Prompt;

public class App {
  public static void main(String[] args) {

    loop:
      while(true) {
        System.out.println("명령어 = "
            + "/member/add, /member/list, /schedule/add, /schedule/list, /seat/add, /seat/list");
        String command = Prompt.inputString("명령> ");

        switch (command) {
          case "/member/add":
            MemberHandler.add();
            break;
          case "/member/list":
            MemberHandler.list();
            break;
          case "/schedule/add":
            ScheduleHandler.add();
            break;
          case "/schedule/list":
            ScheduleHandler.list();
            break;
          case "/seat/add":
            SeatHandler.add();
            break;
          case "/seat/list":
            SeatHandler.list();
            break;
          case "quit":
          case "exit":
            System.out.println("사용해주셔서 감사합니다.");
            break loop;
          default:
            System.out.println("실행할 수 없는 명령입니다.");
        }
      }
    Prompt.close();
  }
}
