package com.dong.pms;

import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

public class App {
  static Scanner keyboardScan = new Scanner(System.in);

  static final int length = 100;
  static String[] no = new String[length];
  static String[] name = new String[length];
  static String[] email = new String[length];
  static String[] photo = new String[length];
  static String[] hp = new String[length];
  static Date[] registeredDate = new Date[length];
  static int size = 0;

  static final int Alength = 100;
  static String[] Ano = new String[length];
  static String[] Adestination = new String[length];
  static String[] Aairno = new String[length];
  static Time[] Adtime = new Time[length];
  static Time[] Aatime = new Time[length];
  static String[] Apilot = new String[length];
  static int Asize = 0;

  static final int Blength = 100;
  static String[] Bno = new String[length];
  static String[] Bmgrade = new String[length];
  static int[] Bsgrade = new int[length];
  static String[] Bsno = new String[length];
  static String[] Betc = new String[length];
  static int Bsize = 0;

  static void addMember(){
    System.out.println("[회원 등록]");

    System.out.print("회원번호");
    no[size] = keyboardScan.nextLine();

    System.out.print("이름");
    name[size] = keyboardScan.nextLine();

    System.out.print("이메일");
    email[size] = keyboardScan.nextLine();

    System.out.print("사진");
    photo[size] = keyboardScan.nextLine();

    System.out.print("전화번호");
    hp[size] = keyboardScan.nextLine();

    registeredDate[size] = new java.sql.Date(System.currentTimeMillis());

    size++;
  }
  static void listMember(){
    System.out.println("[회원 목록]");
    for(int i = 0;i < size; i++) {
      System.out.printf("%s, %s, %s, %s, %s, %s",
          no[i], name[i], email[i], photo[i], hp[i], registeredDate[i]);
    }
  }
  static void addSchedule(){
    System.out.println("[비행일정]");

    System.out.print("회원번호: ");
    Ano[Asize] = keyboardScan.nextLine();

    System.out.print("목적지: ");
    Adestination[Asize] = keyboardScan.nextLine();

    System.out.print("항공기번호: ");
    Aairno[Asize] = keyboardScan.nextLine();

    System.out.print("출발시간: ");
    Adtime[Asize] = Time.valueOf(keyboardScan.nextLine());

    System.out.print("도착시간: ");
    Aatime[Asize] = Time.valueOf(keyboardScan.nextLine());

    System.out.print("조종사: ");
    Apilot[Asize] = keyboardScan.nextLine();

    Asize++;
  }
  static void listSchedule(){
    System.out.println("[비행일정 목록]");
    for(int i = 0; i < Asize; i++) {
      System.out.printf("%s, %s, %s, %s, %s, %s",
          Ano[i], Adestination[i], Aairno[i], Adtime[i], Aatime[i],Apilot[i]);
    }
  }

  static void addSeat(){
    System.out.println("[좌석 등록]");

    System.out.print("회원번호: ");
    Bno[Bsize] = keyboardScan.nextLine();

    System.out.print("회원등급: ");
    Bmgrade[Bsize] = keyboardScan.nextLine();

    System.out.println("좌석등급: ");
    System.out.println("0: 퍼스트클래스");
    System.out.println("1: 비즈니스클래스");
    System.out.println("2: 이코노미클래스");
    Bsgrade[Bsize] = Integer.valueOf(keyboardScan.nextLine());

    System.out.print("좌석번호: ");
    Bsno[Bsize] = keyboardScan.nextLine();

    System.out.print("특이사항: ");
    Betc[Bsize] = keyboardScan.nextLine();

    Bsize++;
  }
  static void listSeat(){
    System.out.println("[좌석 목록]");
    for (int i = 0; i < size; i++) {
      String gradeLabel = null;
      switch (Bsgrade[i]) {
        case 1:
          gradeLabel = "비즈니스";
          break;
        case 2:
          gradeLabel = "이코노미";
          break;
        default:
          gradeLabel = "퍼스트";
      }
      System.out.printf("%s, %s, %s, %s, %s",Bno[i],Bmgrade[i],gradeLabel,Bsno[i], Betc[i]);
    }
  }
  public static void main(String[] args) {

    loop:
      while(true) {
        System.out.println("명령어 = "
            + "/member/add, /member/list, /schedule/add, /schedule/list, /seat/add, /seat/list");
        System.out.println("명령>");
        String command = keyboardScan.nextLine();

        switch (command) {
          case "/member/add":
            addMember();
            break;
          case "/member/list":
            listMember();
            break;
          case "/schedule/add":
            addSchedule();
            break;
          case "/schedule/list":
            listSchedule();
            break;
          case "/seat/add":
            addSeat();
            break;
          case "/seat/list":
            listSeat();
            break;
          case "quit":
          case "exit":
            System.out.println("사용해주셔서 감사합니다.");
            break loop;
          default:
            System.out.println("실행할 수 없는 명령입니다.");
        }
      }
    keyboardScan.close();


  }

}
