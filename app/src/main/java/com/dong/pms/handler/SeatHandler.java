package com.dong.pms.handler;

import com.dong.util.Prompt;

public class SeatHandler {

  static class Seat{
    int no ;
    String mgrade;
    int sgrade ;
    String sno ;
    String etc ;
  }
  static final int LENGTH = 100;
  static Seat[] seats = new Seat[LENGTH];
  static int size = 0;

  public static void add(){
    System.out.println("[좌석 등록]");

    Seat t = new Seat();

    t.no = Prompt.inputInt("회원번호: ");
    t.mgrade = Prompt.inputString("회원등급: ");
    t.sgrade = Prompt.inputInt("좌석등급:\n0:퍼스트클래스\n1:비즈니스클래스\n2:이코노미클래스\n");
    t.sno = Prompt.inputString("좌석번호: ");
    t.etc = Prompt.inputString("특이사항: ");
    seats[size++] = t;
  }
  public static void list(){
    System.out.println("[좌석 목록]");
    for (int i = 0; i < size; i++) {
      Seat t = seats[i];

      String gradeLabel = null;
      switch (t.sgrade) {
        case 1:
          gradeLabel = "비즈니스";
          break;
        case 2:
          gradeLabel = "이코노미";
          break;
        default:
          gradeLabel = "퍼스트";
      }
      System.out.printf("%s, %s, %s, %s, %s\n",t.no,t.mgrade,gradeLabel,t.sno, t.etc);
    }
  }
}
