package com.dong.pms.handler;

import java.util.List;
import com.dong.pms.domain.Schedule;
import com.dong.util.Prompt;

public class ScheduleAddHandler extends AbstractScheduleHandler{

  private MemberValidatorHandler memberValidatorHandler;

  public ScheduleAddHandler(List<Schedule> scheduleList, MemberValidatorHandler memberValidatorHandler) {
    super(scheduleList);
    this.memberValidatorHandler = memberValidatorHandler;
  }


  @Override
  public void service(){
    System.out.println("[비행일정]");

    Schedule s = new Schedule();

    s.setNo(Prompt.inputInt("회원번호: "));
    s.setDestination(Prompt.inputString("목적지: "));
    s.setAirno(Prompt.inputString("항공기번호: "));
    s.setDtime(Prompt.inputTime("출발시간: "));
    s.setAtime(Prompt.inputTime("도착시간: "));

    s.setName(memberValidatorHandler.inputMember("탑승자?(취소: 빈 문자열) "));
    if (s.getName() == null) {
      System.out.println("비행일정 입력을 취소합니다.");
      return;
    }
    s.setPilot(Prompt.inputString("조종사: "));

    scheduleList.add(s);
    System.out.println("비행일정을 등록했습니다.");

  }



}