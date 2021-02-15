package com.dong.pms.handler;

import com.dong.pms.domain.Schedule;

public class ScheduleList {

  static final int DEFAULT_CAPACITY = 100;
  Schedule[] schedules = new Schedule[DEFAULT_CAPACITY];
  int size = 0;

  void add(Schedule s) {
    this.schedules[this.size++] = s;
  }

  Schedule[] toArray() {
    Schedule[] arr = new Schedule[this.size];
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.schedules[i];
    }
    return arr;
  }

  Schedule get(int scheduleNo) {
    int i = indexOf(scheduleNo);
    if (i == -1)
      return null;
    return schedules[i];
  }

  void delete(int scheduleNo) {
    int index = indexOf(scheduleNo);

    if (index == -1)
      return;

    for (int x = index + 1; x< this.size; x++) {
      this.schedules[x-1] = this.schedules[x];
    }
    this.schedules[--this.size] = null;
  }

  int indexOf(int scheduleNo) {
    for (int i = 0; i < this.size; i++) {
      Schedule schedule = this.schedules[i];
      if (schedule.no == scheduleNo) {
        return i;
      }
    }
    return -1;
  }
}
