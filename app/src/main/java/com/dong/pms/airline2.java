package com.dong.pms;

import java.sql.Time;
import java.util.Scanner;

public class airline2 {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("[비행일정]");
    Scanner keyboardScan = new Scanner(System.in);

    final int length = 100;
    String[] no = new String[length];
    String[] destination = new String[length];
    String[] airno = new String[length];
    Time[] dtime = new Time[length];
    Time[] atime = new Time[length];
    String[] pilot = new String[length];
    int size = 0;

    for(int i = 0; i < length;i++) {
      System.out.print("회원번호: ");
      no[i] = keyboardScan.nextLine();

      System.out.print("목적지: ");
      destination[i] = keyboardScan.nextLine();

      System.out.print("항공기번호: ");
      airno[i] = keyboardScan.nextLine();

      System.out.print("출발시간: ");
      dtime[i] = Time.valueOf(keyboardScan.nextLine());

      System.out.print("도착시간: ");
      atime[i] = Time.valueOf(keyboardScan.nextLine());

      System.out.print("조종사: ");
      pilot[i] = keyboardScan.nextLine();

      size++;
      System.out.println();
      System.out.print("계속입력하시겠습니까? (y/N)");
      String str = keyboardScan.nextLine();
      if(!str.equalsIgnoreCase("y")) {
        break;
      }
      System.out.println();
    }
    keyboardScan.close();
    for(int i = 0; i < size; i++) {
      System.out.printf("%s, %s, %s, %s, %s, %s",
          no[i], destination[i], airno[i], dtime[i], atime[i],pilot[i]);
    }
  }

}
