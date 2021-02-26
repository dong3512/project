package com.dong.pms.handler;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import com.dong.pms.domain.Member;
import com.dong.pms.domain.Schedule;
import com.dong.util.Prompt;

public class ScheduleHandler {

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  public void category() throws CloneNotSupportedException{

    ArrayList<Member> memberList = new ArrayList<>();
    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);

    LinkedList<Schedule> scheduleList = new LinkedList<>();
    ScheduleAddHandler scheduleAddHandler = new ScheduleAddHandler(scheduleList, memberValidatorHandler);
    ScheduleListHandler scheduleListHandler = new ScheduleListHandler(scheduleList);
    ScheduleDetailHandler scheduleDetailHandler = new ScheduleDetailHandler(scheduleList);
    ScheduleUpdateHandler scheduleUpdateHandler = new ScheduleUpdateHandler(scheduleList, memberValidatorHandler);
    ScheduleDeleteHandler scheduleDeleteHandler = new ScheduleDeleteHandler(scheduleList);

    System.out.println("[비행일정]");
    System.out.println("1. 등록 2. 목록 3. 상세조회 4. 수정 5. 삭제");
    String cmd = Prompt.inputString("입력> ");
    switch(cmd) {
      case "1":
        scheduleAddHandler.add();
        break;

      case "2":
        scheduleListHandler.list();
        break;

      case "3":
        scheduleDetailHandler.detail();
        break;

      case "4":
        scheduleUpdateHandler.update();
        break;

      case "5":
        scheduleDeleteHandler.delete();
        break;
      default:
        System.out.println("잘못된 명령입니다.");
    }
  }

}