package com.dong.pms.handler;

import java.sql.Time;
import com.dong.util.Prompt;

public class ScheduleHandler {

  static class Schedule{
    int no;
    String destination ;
    String airno ;
    String name;
    Time dtime ;
    Time atime ;
    String pilot;
  }
  static final int LENGTH = 100;
  static Schedule[] schedules = new Schedule[LENGTH];
  static int size = 0;

  public static void category() {
    System.out.println("1. 비행일정등록");
    System.out.println("2. 비행일정목록");
    String cmd = Prompt.inputString("입력> ");
    switch(cmd) {
      case "1":
        add();
        break;

      case "2":
        list();
        break;
      default:
        System.out.println("잘못된 명령입니다.");
    }
  }

  public static void add(){
    System.out.println("[비행일정]");

    Schedule s = new Schedule();

    s.no = Prompt.inputInt("회원번호: ");
    s.destination = Prompt.inputString("목적지: ");
    s.airno = Prompt.inputString("항공기번호: ");
    s.dtime = Prompt.inputTime("출발시간: ");
    s.atime = Prompt.inputTime("도착시간: ");
    while(true) {
      String name = Prompt.inputString("탑승객: (취소: 빈문자열)");
      if(name.length() == 0) {
        System.out.println("등록을 취소합니다.");
        return;
      }
      if(MemberHandler.exist(name)) {
        s.name = name;
        break;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
    s.pilot = Prompt.inputString("조종사: ");
    schedules[size++] = s;
  }

  public static void list(){
    System.out.println("[비행일정 목록]");
    for(int i = 0; i < size; i++) {
      Schedule s = schedules[i];
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n",
          s.no, s.destination, s.airno, s.dtime, s.atime, s.name, s.pilot);
    }
  }
}
