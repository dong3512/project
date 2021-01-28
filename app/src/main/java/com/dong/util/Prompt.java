package com.dong.util;

import java.sql.Time;
import java.util.Scanner;

public class Prompt {

  static Scanner keyboardScan = new Scanner(System.in);

  public static String inputString(String title){
    System.out.println(title);
    return keyboardScan.nextLine();
  }

  public static int inputInt(String title) {
    return Integer.parseInt(inputString (title));
  }
  //time 으로 하고 싶은데 하는 방법을 모르겠음.
  public static Time inputTime(String title){
    return Time.valueOf(inputString(title));
  }

  public static void close(){
    keyboardScan.close();
  }
}
