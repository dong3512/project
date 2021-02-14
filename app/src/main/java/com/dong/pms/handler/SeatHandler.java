package com.dong.pms.handler;

import com.dong.pms.domain.Seat;
import com.dong.util.Prompt;

public class SeatHandler {

  Node first;
  Node last;
  int size = 0;

  public void category() {
    System.out.println("[좌석정보]");
    System.out.println("1. 등록 2. 목록 3. 상세보기 4. 수정 5. 삭제");
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

  public  void add(){
    System.out.println("[좌석 등록]");

    Seat t = new Seat();

    t.no = Prompt.inputInt("회원번호: ");
    t.mgrade = Prompt.inputString("회원등급: ");
    t.sgrade = Prompt.inputInt("좌석등급:\n0:퍼스트클래스\n1:비즈니스클래스\n2:이코노미클래스\n");
    t.sno = Prompt.inputString("좌석번호: ");
    t.etc = Prompt.inputString("특이사항: ");

    Node node = new Node(t);

    if(last == null) {
      last = node ;
      first = node;
    }else {
      last.next = node;
      node.prev = last;
      last = node;
    }

    this.size ++;
    System.out.println("좌석을 등록하였습니다.");
  }

  public void list(){
    System.out.println("[좌석 목록]");

    Node cursor = first;

    while (cursor != null) {
      Seat t = cursor.seat;
      System.out.printf("%s, %s, %s, %s, %s\n",t.no,t.mgrade,gradeLabel(t.sgrade),t.sno, t.etc);
      cursor = cursor.next;
    }
  }

  public void detail(){
    System.out.println("[좌석 상세보기]");

    int no = Prompt.inputInt("번호? ");

    Seat seat = findByNo(no);
    if( seat == null) {
      System.out.println("해당 번호의 좌석이 없습니다.");
      return;
    }

    System.out.printf("회원등급: %s\n", seat.mgrade);
    System.out.printf("좌석등급: %s\n", seat.sgrade);
    System.out.printf("좌석번호: %s\n", seat.sno);
    System.out.printf("특이사항: %s\n", seat.etc);
  }

  public void update(){
    System.out.println("[좌석정보 수정]");

    int no = Prompt.inputInt("번호? ");

    Seat seat = findByNo(no);
    if (seat == null) {
      System.out.println("해당 번호의 좌석이 없습니다.");
      return;
    }

    String mgrade = Prompt.inputString(String.format("회원등급(%s)? ", seat.mgrade));
    int sgrade = Prompt.inputInt(String.format("좌석등급(%s)?\n0: 퍼스트 \n1: 비즈니스\n2: 이코노미", gradeLabel(seat.sgrade)));
    String sno = Prompt.inputString(String.format("좌석번호(%s)?(취소: 빈 문자열) ", seat.sno));
    if(sno == null) {
      System.out.println("작업 변경을 취소합니다.");
      return;
    }

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N)");

    if (input.equalsIgnoreCase("Y")) {
      seat.mgrade = mgrade;
      //          seat.sgrade = sgrade; 오류의 이유를 모르겠습니다 ㅠ switch문이 들어가있긴함 ㅎ
      seat.sno = sno;
      System.out.println("게시글을 변경하였습니다.");
    }else {
      System.out.println("게시글 변경을 취소하였습니다.");
    }
  }

  public void delete(){
    System.out.println("[좌석정보 삭제]");

    int no = Prompt.inputInt("번호? ");

    Seat seat = findByNo(no);

    if (seat == null) {
      System.out.println("해당 번호의 작업이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N)");

    if(input.equalsIgnoreCase("Y")) {
      Node cursor = first;
      while (cursor != null) {
        if (cursor.seat == seat) {
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


      System.out.println("게시글을 삭제하였습니다.");
    }else {
      System.out.println("게시글 삭제를 취소하였습니다.");
    }
  }

  Seat findByNo(int seatNo) {
    Node cursor = first;
    while (cursor != null) {
      Seat t = cursor.seat;
      if (t.no == seatNo) {
        return t;
      }
      cursor = cursor.next;
    }
    return null;
  }

  String gradeLabel(int sgrade) {
    switch (sgrade) {
      case 1:
        return "비즈니스";
      case 2: 
        return "이코노미";
      default:
        return "퍼스트";
    }
  }

  static class Node{
    Seat seat;
    Node next;
    Node prev;

    Node(Seat t) {
      this.seat = t;
    }
  }
}


