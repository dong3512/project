package com.dong.pms.handler;

import java.util.List;
import com.dong.pms.domain.Member;

public abstract class AbstractMemberHandler implements Command{

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


}