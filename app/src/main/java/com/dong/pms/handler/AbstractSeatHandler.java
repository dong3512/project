package com.dong.pms.handler;

import java.util.List;
import com.dong.pms.domain.Seat;

public abstract class AbstractSeatHandler {

  protected List<Seat> seatList ;

  public AbstractSeatHandler(List<Seat> seatList) {
    this.seatList = seatList;
  }
  protected String gradeLabel(int sgrade) {
    switch (sgrade) {
      case 1:
        return "비즈니스";
      case 2: 
        return "이코노미";
      default:
        return "퍼스트";
    }
  }

  protected Seat findByNo(int seatNo) {
    Seat[] list = seatList.toArray(new Seat[seatList.size()]);
    for (Seat t : list) {
      if (t.getNo() == seatNo) {
        return t;
      }
    }
    return null;
  }
}


