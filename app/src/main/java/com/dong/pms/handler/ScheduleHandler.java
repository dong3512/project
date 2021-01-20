package com.dong.pms.handler;

import java.sql.Date;
import com.dong.util.Prompt;

public class ScheduleHandler {

  static class Schedule{
    int no;
    String destination ;
    String airno ;
    Date dtime ;
    Date atime ;
    String pilot;
  }
  static final int LENGTH = 100;
  static Schedule[] schedules = new Schedule[LENGTH];
  static int size = 0;

  public static void add(){
    System.out.println("[비행일정]");

    Schedule s = new Schedule();

    s.no = Prompt.inputInt("회원번호: ");
    s.destination = Prompt.inputString("목적지: ");
    s.airno = Prompt.inputString("항공기번호: ");
    s.dtime = Prompt.inputDate("출발일자: ");
    s.atime = Prompt.inputDate("도착일자: ");
    s.pilot = Prompt.inputString("조종사: ");
    schedules[size++] = s;
  }

  public static void list(){
    System.out.println("[비행일정 목록]");
    for(int i = 0; i < size; i++) {
      Schedule s = schedules[i];
      System.out.printf("%s, %s, %s, %s, %s, %s\n",
          s.no, s.destination, s.airno, s.dtime, s.atime, s.pilot);
    }
  }
}
