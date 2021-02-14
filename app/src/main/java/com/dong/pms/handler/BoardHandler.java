package com.dong.pms.handler;

import java.sql.Date;
import com.dong.pms.domain.Board;
import com.dong.util.Prompt;

public class BoardHandler {

  Node first;
  Node last;
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

    Node node = new Node(b);

    if (last == null) {
      last = node ;
      first = node;
    }else {
      last.next = node;
      node.prev = last;
      last = node;
    }

    this.size ++;
    System.out.println("게시글을 등록하였습니다.");
  }

  public  void list() {
    System.out.println("[칭찬게시글 목록]");

    Node cursor = first;

    while (cursor != null) {
      Board b = cursor.board;



      // 번호, 제목, ,전하고싶은말, 등록일, 작성자, 조회수
      System.out.printf("%d, %s, %s, %s, %s, %s\n", 
          b.no, b.title, b.message,b.registeredDate, b.writer, b.viewCount );
      cursor = cursor.next;
    }
  }

  public  void detail() {
    System.out.println("[칭찬게시글 상세보기]");

    int no = Prompt.inputInt("번호? ");

    Board board = findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    board.viewCount++;
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("전하고싶은말: %s\n", board.message);
    System.out.printf("등록일: %s\n", board.registeredDate);
    System.out.printf("작성자: %s\n", board.writer);
    System.out.printf("조회수: %d\n", board.viewCount);
  }

  public  void update() {
    System.out.println("[칭찬게시글 수정]");

    int no = Prompt.inputInt("번호? ");

    Board board = findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("제목(%s)? ", board.title));
    String content = Prompt.inputString(String.format("내용(%s)? ", board.content));
    String message = Prompt.inputString(String.format("메시지(%s)? ", board.message));


    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if(input.equalsIgnoreCase("Y")) {
      board.title = title;
      board.content = content;
      board.message = message;
      System.out.println("게시글을 변경하였습니다.");
    }else {
      System.out.println("게시글 변경을 취소하였습니다.");
    }
  }

  public  void delete() {
    System.out.println("[칭찬게시글 삭제]");

    int no = Prompt.inputInt("번호? ");

    Board board = findByNo(no);
    if ( board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N)");

    if (input.equalsIgnoreCase("Y")) {
      Node cursor = first;
      while (cursor != null) {
        if(cursor.board == board) {
          this.size--;
          if (first == last) {
            first = last = null;
            break;
          }
          if (cursor == first) {
            first = cursor.next;
            cursor.prev = null;
          }else {
            cursor.prev.next = cursor.next;
            if (cursor.next != null) {
              cursor.next.prev = cursor.prev;
            }
          }
          if (cursor == last) {
            last = cursor.prev;
          }
          break;
        }
        cursor = cursor.next;
      }
      System.out.println("게시글을 삭제하였습니다.");

    }else {
      System.out.println("게시글 삭제를 취소하였습니다.");
    }
  }

  Board findByNo(int boardNo) {
    Node cursor = first;
    while (cursor != null) {
      Board b = cursor.board;
      if (b.no == boardNo) {
        return b;
      }
      cursor = cursor.next;
    }
    return null;
  }

  static class Node {
    Board board;
    Node next;
    Node prev;

    Node(Board b) {
      this.board = b;
    }
  }
}
