package com.dong.pms.handler;

import java.util.List;
import com.dong.pms.domain.Member;

public abstract class AbstractMemberHandler {

  protected List<Member> memberList;

  public AbstractMemberHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  protected Member findByNo(int boardNo) {
    Member[] list = memberList.toArray(new Member[memberList.size()]);
    for (Member m : list) {
      if (m.getNo() == boardNo) {
        return m;
      }
    }
    return null;
  }

  protected Member findByName(String name) {
    Member[] list = memberList.toArray(new Member[memberList.size()]);
    for (Member m : list) {
      if (m.getName().equals(name)) {
        return m;
      }
    }
    return null;
  }


  //  public String inputMember(String promptTitle) {
  //    while(true) {
  //      String name = Prompt.inputString(promptTitle);
  //      if(name.length() == 0) {
  //        return null;
  //      } else if (findByName(name) != null) {
  //        return name;
  //      } else {
  //        System.out.println("등록된 회원이 아닙니다.");
  //      }
  //    }
  //  }

}