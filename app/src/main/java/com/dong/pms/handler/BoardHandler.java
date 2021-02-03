package com.dong.pms.handler;

import java.sql.Date;
import com.dong.pms.domain.Board;
import com.dong.util.Prompt;

public class BoardHandler {

  static final int LENGTH = 100;
  Board[] boards = new Board[LENGTH];
  int size = 0;


  public void category( ) {
    System.out.println("[칭찬게시판]");
    System.out.println("1. 등록 2. 목록 3. 상세보기 4. 수정 5. 삭제");
    String cmd = Prompt.inputString("입력> ");
    switch(cmd) {
      case "1":
        add();
        break;

      case "2":
        list();
        break;

      case "3":
        detail();
        break;

      case "4":
        update();
        break;

      case "5":
        delete();
        break;
      default:
        System.out.println("잘못된 명령입니다.");
    }
  }

  public void add(){
    System.out.println("[칭찬게시글 등록]");

    Board b = new Board();

    b.no = Prompt.inputInt("회원번호");
    b.name = Prompt.inputString("이름" );
    b.title = Prompt.inputString("제목");
    b.content = Prompt.inputString("내용");
    b.message = Prompt.inputString("전하고싶은말");
    b.writer = Prompt.inputString("작성자");
    b.registeredDate = new Date(System.currentTimeMillis());

    this.boards[this.size++] = b;

  }

  public  void list() {
    System.out.println("[칭찬게시글 목록]");

    for (int i = 0; i < this.size; i++) {
      Board b = this.boards[i];
      // 번호, 제목, ,전하고싶은말, 등록일, 작성자, 조회수
      System.out.printf("%d, %s, %s, %s, %s, %s\n", 
          b.no, b.title, b.message,b.registeredDate, b.writer, b.viewCount );
    }
  }

  public  void detail() {
    System.out.println("[칭찬게시글 상세보기]");

    int no = Prompt.inputInt("번호? ");

    for (int i = 0; i < this.size; i++) {
      Board board = this.boards[i];
      if (board.no == no) {
        board.viewCount++;
        System.out.printf("제목: %s\n", board.title);
        System.out.printf("내용: %s\n", board.content);
        System.out.printf("전하고싶은말: %s\n", board.message);
        System.out.printf("등록일: %s\n", board.registeredDate);
        System.out.printf("작성자: %s\n", board.writer);
        System.out.printf("조회수: %s\n", board.viewCount);
      }
    }
  }

  public  void update() {
    System.out.println("[칭찬게시글 수정]");

    int no = Prompt.inputInt("번호? ");

    for (int i = 0; i < this.size; i++) {
      Board board = this.boards[i];
      if(board.no == no) {
        String title = Prompt.inputString(String.format("제목(%s)? ", board.title));
        String content = Prompt.inputString(String.format("내용(%s)? ", board.content));
        String message = Prompt.inputString(String.format("전하고싶은말(%s)? ",board.message));
        String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

        if(input.equalsIgnoreCase("Y")) {
          board.title = title;
          board.content = content;
          board.message = message;
          System.out.println("게시글을 변경하였습니다.");
        }else {
          System.out.println("게시글 변경을 취소하였습니다.");
        }
        return ;
      }
    }

    System.out.println("해당 번호의 게시글이 없습니다.");
  }

  public  void delete() {
    System.out.println("[칭찬게시글 삭제]");
    int no = Prompt.inputInt("번호? ");

    for (int i = 0; i < this.size; i++) {
      Board board = this.boards[i];
      if(board.no == no) {
        String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N)");

        if ( input.equalsIgnoreCase("Y")) {
          this.boards[i] = null;
          System.out.println("게시글을 삭제하였습니다.");
        }else {
          System.out.println("게시글 삭제를 취소하였습니다.");
        }
        return;
      }
    }

    System.out.println("해당 번호의 게시글이 없습니다.");
  }
}
