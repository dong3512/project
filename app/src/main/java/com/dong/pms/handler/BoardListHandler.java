package com.dong.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.dong.pms.domain.Board;

public class BoardListHandler extends AbstractBoardHandler{

  public BoardListHandler(List<Board> boardList) {
    super(boardList);
  }
  public  void list() throws CloneNotSupportedException {
    System.out.println("[칭찬게시글 목록]");

    Iterator<Board> iterator = boardList.iterator();

    while (iterator.hasNext()) {
      Board b = iterator.next();
      // 번호, 제목, ,전하고싶은말, 등록일, 작성자, 조회수
      System.out.printf("%d, %s, %s, %s, %s, %s\n", 
          b.getNo(), b.getTitle(), b.getMessage(),b.getRegisteredDate(), b.getWriter(), b.getViewCount());
    }
  }


}

