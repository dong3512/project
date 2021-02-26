package com.dong.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.dong.pms.domain.Member;

public class MemberListHandler extends AbstractMemberHandler {

  public MemberListHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("[회원 목록]");

    Iterator<Member> iterator = memberList.iterator();

    while (iterator.hasNext()) {
      Member m = iterator.next();
      System.out.printf("%s, %s, %s, %s, %s, %s\n",
          m.getNo(), m.getName(), m.getEmail(), m.getPhoto(), m.getHp(), m.getRegisteredDate());
    }
  }

}