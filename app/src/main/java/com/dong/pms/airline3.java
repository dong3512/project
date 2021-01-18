package com.dong.pms;

import java.util.Scanner;

public class airline3 {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Scanner keyboardScan = new Scanner(System.in);
    System.out.println("[좌석]");

    final int length = 100;
    String[] no = new String[length];
    String[] mgrade = new String[length];
    int[] sgrade = new int[length];
    String[] sno = new String[length];
    String[] etc = new String[length];
    int size = 0;

    for(int i = 0; i < length; i++) {
      System.out.print("회원번호: ");
      no[i] = keyboardScan.nextLine();

      System.out.print("회원등급: ");
      mgrade[i] = keyboardScan.nextLine();

      System.out.println("좌석등급: ");
      System.out.println("0: 퍼스트클래스");
      System.out.println("1: 비즈니스클래스");
      System.out.println("2: 이코노미클래스");
      sgrade[i] = Integer.valueOf(keyboardScan.nextLine());

      System.out.print("좌석번호: ");
      sno[i] = keyboardScan.nextLine();

      System.out.print("특이사항: ");
      etc[i] = keyboardScan.nextLine();

      size++;
      System.out.println();
      System.out.println("계속입력하시겠습니까?(y/N)");
      String str = keyboardScan.nextLine();
      if(!str.equalsIgnoreCase("y")) {
        break;
      }
    }
    keyboardScan.close();

    for (int i = 0; i < size; i++) {
      String gradeLabel = null;
      switch (sgrade[i]) {
        case 1:
          gradeLabel = "비즈니스";
          break;
        case 2:
          gradeLabel = "이코노미";
          break;
        default:
          gradeLabel = "퍼스트";
      }


      System.out.printf("%s, %s, %s, %s, %s",no[i],mgrade[i],gradeLabel,sno[i], etc[i]);
    }
  }

}
