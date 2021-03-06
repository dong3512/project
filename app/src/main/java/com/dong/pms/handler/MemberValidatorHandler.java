package com.dong.pms.handler;

import java.util.List;
import com.dong.pms.domain.Member;
import com.dong.util.Prompt;

public class MemberValidatorHandler extends AbstractMemberHandler {

  public MemberValidatorHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {

  }

  public String inputMember(String promptTitle) {
    while (true) {
      String name = Prompt.inputString(promptTitle);
      if (name.length() == 0) {
        return null;
      } 
      if (findByName(name) != null) {
        return name;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  public String inputMembers(String promptTitle) {
    String members = "";
    while (true) {
      String name = inputMember(promptTitle);
      if (name == null) {
        return members;
      } else {
        if (!members.isEmpty()) {
          members += ",";
        }
        members += name;
      }
    }
  }

}