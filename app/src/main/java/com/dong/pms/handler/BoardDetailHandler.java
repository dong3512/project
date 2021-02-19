package com.dong.pms.handler;

import java.util.List;
import com.dong.pms.domain.Board;
import com.dong.util.Prompt;

public class BoardDetailHandler extends AbstractBoardHandler{

  public BoardDetailHandler(List<Board> boardList) {
    super(boardList);
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



}

