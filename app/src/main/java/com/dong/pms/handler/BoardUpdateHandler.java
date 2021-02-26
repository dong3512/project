package com.dong.pms.handler;

import java.util.List;
import com.dong.pms.domain.Board;
import com.dong.util.Prompt;

public class BoardUpdateHandler extends AbstractBoardHandler{

  public BoardUpdateHandler(List<Board> boardList) {
    super(boardList);
  }
  @Override
  public  void service() {
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


}

