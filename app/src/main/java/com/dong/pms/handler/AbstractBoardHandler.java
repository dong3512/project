package com.dong.pms.handler;

import java.util.List;
import com.dong.pms.domain.Board;

public abstract class AbstractBoardHandler {

  protected List<Board> boardList;

  public AbstractBoardHandler(List<Board> boardList) {
    this.boardList = boardList;
  }

  protected Board findByNo(int boardNo) {
    Board[] list = boardList.toArray(new Board[0]);
    for (Board b : list) {
      if (b.getNo() == boardNo) {
        return b;
      }
    }
    return null;
  }

}

