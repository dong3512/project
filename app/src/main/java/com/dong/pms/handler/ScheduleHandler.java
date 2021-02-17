package com.dong.pms.handler;

import com.dong.pms.domain.Schedule;
import com.dong.util.List;
import com.dong.util.ListIterator;
import com.dong.util.Prompt;

public class ScheduleHandler {

  private List scheduleList = new List();

  private MemberHandler memberHandler;

  public ScheduleHandler(MemberHandler memberHandler) {
    this.memberHandler = memberHandler;
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

    s.setNo(Prompt.inputInt("회원번호: "));
    s.setDestination(Prompt.inputString("목적지: "));
    s.setAirno(Prompt.inputString("항공기번호: "));
    s.setDtime(Prompt.inputTime("출발시간: "));
    s.setAtime(Prompt.inputTime("도착시간: "));

    s.setName(memberHandler.inputMember("탑승자?(취소: 빈 문자열) "));
    if (s.getName() == null) {
      System.out.println("비행일정 입력을 취소합니다.");
      return;
    }
    s.setPilot(Prompt.inputString("조종사: "));

    scheduleList.add(s);
    System.out.println("비행일정을 등록했습니다.");

  }

  public void list(){
    System.out.println("[비행일정 목록]");

    ListIterator iterator = new ListIterator(this.scheduleList);

    while (iterator.hasNext()) {
      Schedule s = (Schedule) iterator.next();
      System.out.printf("%s, %s, %s, %s, %s, %s, %s\n",
          s.getNo(), s.getDestination(), s.getAirno(), s.getDtime(), s.getAtime(), s.getName(), s.getPilot());
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

    System.out.printf("목적지: %s\n",schedule.getDestination());
    System.out.printf("항공기번호: %s\n",schedule.getAirno());
    System.out.printf("출발시간: %s\n", schedule.getDtime());
    System.out.printf("도착시간: %s\n", schedule.getAtime());
    System.out.printf("탑승자이름: %s\n", schedule.getName());
    System.out.printf("조종사: %s\n", schedule.getPilot());
  }

  public void update(){
    System.out.println("[비행일정 수정]");

    int no = Prompt.inputInt("번호? ");

    Schedule schedule = findByNo(no);

    if(schedule == null) {
      System.out.println("해당 번호의 프로젝트가 없습니다.");
      return;
    }

    String destination = Prompt.inputString(String.format("목적지(%s) ",schedule.getDestination()));
    String dtime = Prompt.inputString(String.format("출발시간(%s)", schedule.getDtime()));
    String atime = Prompt.inputString(String.format("도착시간(%s) ", schedule.getAtime()));
    String name = memberHandler.inputMember(String.format("탑승자(%s)?(취소: 빈 문자열) ", schedule.getName()));
    if (name == null) {
      System.out.println("비행일정 수정을 취소합니다.");
      return;
    }

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N)");

    if(input.equalsIgnoreCase("Y")) {
      schedule.setDestination(destination);
      //       schedule.dtime = dtime;
      //       schedule.atime = atime;
      // time은 변경이 안된다합니다요 ㅠㅠ
      schedule.setName(name);

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
      scheduleList.delete(schedule);
      System.out.println("비행일정을 삭제하였습니다.");
    }else {
      System.out.println("비행일정 삭제를 취소하였습니다.");
    }
  }

  private int indexOf(int scheduleNo) {
    Object[] list = scheduleList.toArray();
    for (int i = 0; i < list.length; i++) {
      Schedule s = (Schedule) list[i];
      if (s.getNo() == scheduleNo) {
        return i;
      }
    }
    return -1;
  }

  private Schedule findByNo(int scheduleNo) {
    Object[] list = scheduleList.toArray();
    for (Object obj : list) {
      Schedule s = (Schedule) obj;
      if (s.getNo() == scheduleNo) {
        return s;
      }
    }
    return null;
  }

}