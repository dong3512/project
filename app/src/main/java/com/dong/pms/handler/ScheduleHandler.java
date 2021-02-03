package com.dong.pms.handler;

import com.dong.pms.domain.Schedule;
import com.dong.util.Prompt;

public class ScheduleHandler {

  static final int LENGTH = 100;
  Schedule[] schedules = new Schedule[LENGTH];
  int size = 0;

  MemberHandler memberList;

  public ScheduleHandler(MemberHandler memberHandler) {
    this.memberList = memberHandler;
  }

  public void category() {
    System.out.println("[비행일정]");
    System.out.println("1. 등록 2. 목록 3. 상세조회 4. 수정 5. 삭제");
    String cmd = Prompt.inputString("입력> ");
    switch(cmd) {
      case "1":
        add();
        break;

      case "2":
        list();
        break;

      case "3":
        detail();
        break;

      case "4":
        update();
        break;

      case "5":
        delete();
        break;
      default:
        System.out.println("잘못된 명령입니다.");
    }
  }

  public void add(){
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
      if(memberList.exist(name)) {
        s.name = name;
        break;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
    s.pilot = Prompt.inputString("조종사: ");
    schedules[size++] = s;
  }

  public void list(){
    System.out.println("[비행일정 목록]");
    for(int i = 0; i < this.size; i++) {
      Schedule s = this.schedules[i];
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n",
          s.no, s.destination, s.airno, s.dtime, s.atime, s.name, s.pilot);
    }
  }

  public void detail(){
    System.out.println("[비행일정 상세조회]");

    int no = Prompt.inputInt("번호? ");

    for(int i = 0; i < this.size; i++) {
      Schedule schedule = this.schedules[i];
      if(schedule.no == no) {
        System.out.printf("목적지: %s\n",schedule.destination);
        System.out.printf("항공기번호: %s\n",schedule.airno);
        System.out.printf("출발시간: %s\n", schedule.dtime);
        System.out.printf("도착시간: %s\n", schedule.atime);
        System.out.printf("탑승자이름: %s\n", schedule.name);
        System.out.printf("조종사: %s\n", schedule.pilot);
        return;
      }
    }
    System.out.println("해당 번호의 게시글이 없습니다.");
  }

  public void update(){
    System.out.println("[비행일정 수정]");

    int no = Prompt.inputInt("번호? ");

    for(int i = 0; i < this.size; i++) {
      Schedule schedule = this.schedules[i];
      if(schedule.no == no) {
        String destination = Prompt.inputString(String.format("목적지(%s) ",schedule.destination));
        String dtime = Prompt.inputString(String.format("출발시간(%s)", schedule.dtime));
        String atime = Prompt.inputString(String.format("도착시간(%s) ", schedule.atime));

        String input = Prompt.inputString("정말 변경하시겠습니까?(y/N)");

        if(input.equalsIgnoreCase("Y")) {
          schedule.destination = destination;
          // schedule.dtime = dtime;
          // schedule.atime = atime;
          // time은 변경이 안된다합니다요 ㅠㅠ

        }
      }
    }
  }

  public void delete(){
    System.out.println("[비행일정 삭제]");

    int no = Prompt.inputInt("번호? ");

    for(int i = 0; i < this.size; i++) {
      Schedule schedule = this.schedules[i];
      if(schedule.no == no) {
        String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N)");

        if (input.equalsIgnoreCase("Y")) {
          this.schedules[i] = null;
          System.out.println("게시글을 삭제하였습니다.");
        }else {
          System.out.println("게시글 삭제를 취소하였습니다.");
        }

        return;
      }
    }
    System.out.println("해당 번호의 게시글이 없습니다.");
  }
}
