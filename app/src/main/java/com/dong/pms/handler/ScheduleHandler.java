package com.dong.pms.handler;

import com.dong.pms.domain.Schedule;
import com.dong.util.Prompt;

public class ScheduleHandler {

  Node first;
  Node last;
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

    s.name = inputMember("탑승자?(취소: 빈 문자열) ");
    if (s.name == null) {
      System.out.println("비행일정 입력을 취소합니다.");
      return;
    }
    s.pilot = Prompt.inputString("조종사: ");

    Node node = new Node(s);

    if (last == null) {
      last = node;
      first = node;
    }else {
      last.next = node;
      node.prev = last;
      last = node;
    }

    this.size++;
    System.out.println("비행일정을 등록하였습니다.");
  }

  public void list(){
    System.out.println("[비행일정 목록]");

    Node cursor = first;

    while (cursor != null) {
      Schedule s = cursor.schedule;

      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n",
          s.no, s.destination, s.airno, s.dtime, s.atime, s.name, s.pilot);
      cursor = cursor.next;
    }
  }

  public void detail(){
    System.out.println("[비행일정 상세조회]");

    int no = Prompt.inputInt("번호? ");

    Schedule schedule = findByNo(no);
    if(schedule == null) {
      System.out.println("해당 번호의 일정이 없습니다.");
      return;
    }

    System.out.printf("목적지: %s\n",schedule.destination);
    System.out.printf("항공기번호: %s\n",schedule.airno);
    System.out.printf("출발시간: %s\n", schedule.dtime);
    System.out.printf("도착시간: %s\n", schedule.atime);
    System.out.printf("탑승자이름: %s\n", schedule.name);
    System.out.printf("조종사: %s\n", schedule.pilot);
    return;
  }

  public void update(){
    System.out.println("[비행일정 수정]");

    int no = Prompt.inputInt("번호? ");

    Schedule schedule = findByNo(no);

    if(schedule == null) {
      System.out.println("해당 번호의 프로젝트가 없습니다.");
      return;
    }

    String destination = Prompt.inputString(String.format("목적지(%s) ",schedule.destination));
    String dtime = Prompt.inputString(String.format("출발시간(%s)", schedule.dtime));
    String atime = Prompt.inputString(String.format("도착시간(%s) ", schedule.atime));
    String name = inputMember(String.format("탑승자(%s)?(취소: 빈 문자열) ", schedule.name));
    if (name == null) {
      System.out.println("비행일정 수정을 취소합니다.");
      return;
    }

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N)");

    if(input.equalsIgnoreCase("Y")) {
      schedule.destination = destination;
      //       schedule.dtime = dtime;
      //       schedule.atime = atime;
      // time은 변경이 안된다합니다요 ㅠㅠ
      schedule.name = name;

      System.out.println("비행일정을 변경하였습니다.");
    }else {
      System.out.println("프로젝트 변경을 취소하였습니다.");
    }
  }

  public void delete(){
    System.out.println("[비행일정 삭제]");

    int no = Prompt.inputInt("번호? ");

    Schedule schedule = findByNo(no);
    if (schedule == null) {
      System.out.println("해당 번호의 일정이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N)");

    if (input.equalsIgnoreCase("Y")) {
      Node cursor = first;
      while (cursor != null) {
        if (cursor.schedule == schedule) {
          this.size--;
          if (first == last) {
            first = last = null;
            break;
          }
          if (cursor == first) {
            first = cursor.next;
            cursor.prev = null;
          } else {
            cursor.prev.next = cursor.next;
            if (cursor.next != null) {
              cursor.next.prev = cursor.prev;
            }
          }
          if (cursor == last) {
            last = cursor.prev;
          }

          break;
        }
        cursor = cursor.next;
      }
      System.out.println("비행일정을 삭제하였습니다.");
    }else {
      System.out.println("비행일정 삭제를 취소하였습니다.");
    }
  }

  Schedule findByNo(int scheduleNo) {
    Node cursor = first;
    while (cursor != null) {
      Schedule s = cursor.schedule;
      if (s.no == scheduleNo) {
        return s;
      }
      cursor = cursor.next;
    }
    return null;
  }

  String inputMember(String promptTitle) {
    while(true) {
      String name = Prompt.inputString(promptTitle);
      if(name.length() == 0) {
        return null;
      }
      if(memberList.exist(name)) {
        return name;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  static class Node {
    Schedule schedule;
    Node next;
    Node prev;

    Node(Schedule s){
      this.schedule = s;
    }
  }
}