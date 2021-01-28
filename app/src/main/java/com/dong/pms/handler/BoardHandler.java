package com.dong.pms.handler;

import java.sql.Date;
import com.dong.pms.domain.Board;
import com.dong.util.Prompt;

public class BoardHandler {

  static final int LENGTH = 100;
  static Board[] boards = new Board[LENGTH];
  static int size = 0;

  public static void add(){
    System.out.println("[칭찬게시글 등록]");

    Board b = new Board();

    b.no = Prompt.inputInt("회원번호");
    b.name = Prompt.inputString("이름" );
    b.title = Prompt.inputString("제목");
    b.content = Prompt.inputString("내용");
    b.message = Prompt.inputString("전하고싶은말");
    b.writer = Prompt.inputString("작성자");
    b.registeredDate = new Date(System.currentTimeMillis());

    boards[size++] = b;

    System.out.println("게시글을 등록하였습니다.");
  }

  public static void list() {
    System.out.println("[칭찬게시글 목록]");

    for (int i = 0; i < size; i++) {
      Board b = boards[i];
      // 번호, 제목, ,전하고싶은말, 등록일, 작성자, 
      System.out.printf("%d, %s, %s, %s, %s\n", 
          b.no, b.title, b.message,b.registeredDate, b.writer );
    }
  }
}
