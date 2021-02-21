package com.dong.pms.handler;

import java.util.List;
import com.dong.pms.domain.Schedule;
import com.dong.util.Prompt;

public class ScheduleDeleteHandler extends AbstractScheduleHandler{

  public ScheduleDeleteHandler(List<Schedule> scheduleList) {
    super(scheduleList);
  }

  public void delete(){
    System.out.println("[비행일정 삭제]");

    int no = Prompt.inputInt("번호? ");

    Schedule schedule = findByNo(no);
    if (schedule == null) {
      System.out.println("해당 번호의 일정이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N)");

    if (input.equalsIgnoreCase("Y")) {
      scheduleList.remove(schedule);
      System.out.println("비행일정을 삭제하였습니다.");
    }else {
      System.out.println("비행일정 삭제를 취소하였습니다.");
    }
  }


}