package com.dong.pms.handler;

import com.dong.pms.domain.Seat;
import com.dong.util.List;
import com.dong.util.Prompt;

public class SeatHandler {

  private List seatList = new List();

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

    t.setNo(Prompt.inputInt("회원번호: "));
    t.setMgrade(Prompt.inputString("회원등급: "));
    t.setSgrade(Prompt.inputInt("좌석등급:\n0:퍼스트클래스\n1:비즈니스클래스\n2:이코노미클래스\n"));
    t.setSno(Prompt.inputString("좌석번호: "));
    t.setEtc(Prompt.inputString("특이사항: "));

    seatList.add(t);
    System.out.println("좌석을 등록했습니다.");
  }

  public void list(){
    System.out.println("[좌석 목록]");

    Object[] list = seatList.toArray();
    for (Object obj : list) {
      Seat t = (Seat) obj;
      System.out.printf("%s, %s, %s, %s, %s\n",t.getNo(),t.getMgrade(),gradeLabel(t.getSgrade()),t.getSno(), t.getEtc());
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

    System.out.printf("회원등급: %s\n", seat.getMgrade());
    System.out.printf("좌석등급: %s\n", seat.getSgrade());
    System.out.printf("좌석번호: %s\n", seat.getSno());
    System.out.printf("특이사항: %s\n", seat.getEtc());
  }


  public void update(){
    System.out.println("[좌석정보 수정]");

    int no = Prompt.inputInt("번호? ");

    Seat seat = findByNo(no);
    if (seat == null) {
      System.out.println("해당 번호의 좌석이 없습니다.");
      return;
    }

    String mgrade = Prompt.inputString(String.format("회원등급(%s)? ", seat.getMgrade()));
    int sgrade = Prompt.inputInt(String.format("좌석등급(%s)?\n0: 퍼스트 \n1: 비즈니스\n2: 이코노미", gradeLabel(seat.getSgrade())));
    String sno = Prompt.inputString(String.format("좌석번호(%s)?(취소: 빈 문자열) ", seat.getSno()));
    if(sno == null) {
      System.out.println("작업 변경을 취소합니다.");
      return;
    }

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N)");

    if (input.equalsIgnoreCase("Y")) {
      seat.setMgrade(mgrade);
      //          seat.sgrade = sgrade; 오류의 이유를 모르겠습니다 ㅠ switch문이 들어가있긴함 ㅎ
      seat.setSno(sno);
      System.out.println("게시글을 변경하였습니다.");
    }else {
      System.out.println("게시글 변경을 취소하였습니다.");
    }
  }

  public void delete(){
    System.out.println("[좌석정보 삭제]");

    int no = Prompt.inputInt("번호? ");

    int index = indexOf(no);
    if (index == -1) {
      System.out.println("해당 번호의 좌석이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N)");

    if(input.equalsIgnoreCase("Y")) {
      seatList.delete(index);

      System.out.println("게시글을 삭제하였습니다.");
    }else {
      System.out.println("게시글 삭제를 취소하였습니다.");
    }
  }

  private String gradeLabel(int sgrade) {
    switch (sgrade) {
      case 1:
        return "비즈니스";
      case 2: 
        return "이코노미";
      default:
        return "퍼스트";
    }
  }

  private int indexOf(int seatNo) {
    Object[] list = seatList.toArray();
    for (int i = 0; i < list.length; i++) {
      Seat t = (Seat) list[i];
      if (t.getNo() == seatNo) {
        return i;
      }
    }
    return -1;
  }

  private Seat findByNo(int seatNo) {
    Object[] list = seatList.toArray();
    for (Object obj : list) {
      Seat t = (Seat) obj;
      if (t.getNo() == seatNo) {
        return t;
      }
    }
    return null;
  }
}


