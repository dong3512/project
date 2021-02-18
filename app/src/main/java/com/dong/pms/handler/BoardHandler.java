package com.dong.pms.handler;

import java.sql.Date;
import com.dong.pms.domain.Board;
import com.dong.util.Iterator;
import com.dong.util.List;
import com.dong.util.Prompt;

public class BoardHandler {

  private List boardList = new List();

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

    b.setNo(Prompt.inputInt("회원번호"));
    b.setName(Prompt.inputString("이름" ));
    b.setTitle(Prompt.inputString("제목"));
    b.setContent(Prompt.inputString("내용"));
    b.setMessage(Prompt.inputString("전하고싶은말"));
    b.setWriter(Prompt.inputString("작성자"));
    b.setRegisteredDate(new Date(System.currentTimeMillis()));

    boardList.add(b);

    System.out.println("게시글을 등록하였습니다.");
  }

  public  void list() throws CloneNotSupportedException {
    System.out.println("[칭찬게시글 목록]");

    Iterator iterator = boardList.iterator();

    while (iterator.hasNext()) {
      Board b = (Board) iterator.next();
      // 번호, 제목, ,전하고싶은말, 등록일, 작성자, 조회수
      System.out.printf("%d, %s, %s, %s, %s, %s\n", 
          b.getNo(), b.getTitle(), b.getMessage(),b.getRegisteredDate(), b.getWriter(), b.getViewCount());
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

    board.setViewCount(board.getViewCount() + 1);
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("전하고싶은말: %s\n", board.getMessage());
    System.out.printf("등록일: %s\n", board.getRegisteredDate());
    System.out.printf("작성자: %s\n", board.getWriter());
    System.out.printf("조회수: %d\n", board.getViewCount());
  }

  public  void update() {
    System.out.println("[칭찬게시글 수정]");

    int no = Prompt.inputInt("번호? ");

    Board board = findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("제목(%s)? ", board.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s)? ", board.getContent()));
    String message = Prompt.inputString(String.format("메시지(%s)? ", board.getMessage()));


    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if(input.equalsIgnoreCase("Y")) {
      board.setTitle(title);
      board.setContent(content);
      board.setMessage(message);
      System.out.println("게시글을 변경하였습니다.");
    }else {
      System.out.println("게시글 변경을 취소하였습니다.");
    }
  }

  public  void delete() {
    System.out.println("[칭찬게시글 삭제]");

    int no = Prompt.inputInt("번호? ");

    Board board = findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N)");

    if (input.equalsIgnoreCase("Y")) {
      boardList.delete(board);

      System.out.println("게시글을 삭제하였습니다.");

    } else {
      System.out.println("게시글 삭제를 취소하였습니다.");
    }
  }

  private Board findByNo(int boardNo) {
    Object[] list = boardList.toArray();
    for (Object obj : list) {
      Board b = (Board) obj;
      if (b.getNo() == boardNo) {
        return b;
      }
    }
    return null;
  }

}

