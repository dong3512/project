package com.dong.pms.handler2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import com.dong.pms.domain.Board;
import com.dong.util.Prompt;

public class BoardHandler {

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  public static void category( ) throws CloneNotSupportedException{

    ArrayList<Board> boardList = new ArrayList<>();
    BoardAddHandler boardAddHandler = new BoardAddHandler(boardList);
    BoardListHandler boardListHandler = new BoardListHandler(boardList);

    System.out.println("[칭찬게시판]");
    System.out.println("1. 등록 2. 목록 3. 상세보기 4. 수정 5. 삭제");
    String cmd = Prompt.inputString("입력> ");
    switch(cmd) {
      case "1":
        boardAddHandler.add();
        break;

      default:
        System.out.println("잘못된 명령입니다.");
    }
  }


}

