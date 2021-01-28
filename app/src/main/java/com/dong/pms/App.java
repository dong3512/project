package com.dong.pms;

import com.dong.pms.handler.BoardHandler;
import com.dong.pms.handler.MemberHandler;
import com.dong.pms.handler.ScheduleHandler;
import com.dong.pms.handler.SeatHandler;
import com.dong.util.Prompt;

public class App {
  public static void main(String[] args) {

    loop:
      while(true) {
        System.out.println("명령어 = "
            + "1.회원입력, 2.회원목록, 3.비행일정입력, 4.비행일정목록 ");
        System.out.println("5.좌석정보입력, 6.좌석정보목록, 7.칭찬게시판입력, 8.칭찬게시판 목록");
        String command = Prompt.inputString("명령> ");

        switch (command) {
          case "1":
            MemberHandler.add();
            break;
          case "2":
            MemberHandler.list();
            break;
          case "3":
            ScheduleHandler.add();
            break;
          case "4":
            ScheduleHandler.list();
            break;
          case "5":
            SeatHandler.add();
            break;
          case "6":
            SeatHandler.list();
            break;
          case "7":
            BoardHandler.add();
            break;
          case "8":
            BoardHandler.list();
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
