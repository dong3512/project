package com.dong.pms.handler;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import com.dong.pms.domain.Seat;
import com.dong.util.Prompt;

public class SeatHandler {

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  public void category() throws CloneNotSupportedException{

    ArrayList<Seat> seatList = new ArrayList<>();
    SeatAddHandler seatAddHandler = new SeatAddHandler(seatList);
    SeatListHandler seatListHandler = new SeatListHandler(seatList);
    SeatDetailHandler seatDetailHandler = new SeatDetailHandler(seatList);
    SeatUpdateHandler seatUpdateHandler = new SeatUpdateHandler(seatList);
    SeatDeleteHandler seatDeleteHandler = new SeatDeleteHandler(seatList);


    System.out.println("[좌석정보]");
    System.out.println("1. 등록 2. 목록 3. 상세보기 4. 수정 5. 삭제");
    String cmd = Prompt.inputString("입력> ");
    switch(cmd) {
      case "1":
        seatAddHandler.add();
        break;

      case "2":
        seatListHandler.list();
        break;

      case "3":
        seatDetailHandler.detail();
        break;

      case "4":
        seatUpdateHandler.update();
        break;

      case "5":
        seatDeleteHandler.delete();
        break;
      default:
        System.out.println("잘못된 명령입니다.");
    }
  }

}


