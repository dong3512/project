package com.dong.pms.handler;

import java.util.List;
import com.dong.pms.domain.Board;
import com.dong.util.Prompt;

public class BoardDeleteHandler extends AbstractBoardHandler{

  public BoardDeleteHandler(List<Board> boardList) {
    super(boardList);
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
      boardList.remove(board);

      System.out.println("게시글을 삭제하였습니다.");

    } else {
      System.out.println("게시글 삭제를 취소하였습니다.");
    }
  }

}

