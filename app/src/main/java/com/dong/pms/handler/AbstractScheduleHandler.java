package com.dong.pms.handler;

import java.util.List;
import com.dong.pms.domain.Schedule;

public abstract class AbstractScheduleHandler implements Command{

  protected List<Schedule> scheduleList;

  public AbstractScheduleHandler(List<Schedule> scheduleList) {
    this.scheduleList = scheduleList;
  }

  protected Schedule findByNo(int scheduleNo) {
    Schedule[] list = scheduleList.toArray(new Schedule[scheduleList.size()]);
    for (Schedule s : list) {
      if (s.getNo() == scheduleNo) {
        return s;
      }
    }
    return null;
  }

}