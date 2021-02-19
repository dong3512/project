package com.dong.pms.handler;

import java.util.List;
import com.dong.pms.domain.Member;
import com.dong.util.Prompt;

public class MemberAddHandler extends AbstractMemberHandler {

  public MemberAddHandler(List<Member> memberList) {
    super(memberList);
  }

  public void add(){
    System.out.println("[회원 등록]");

    Member m = new Member();

    m.setNo(Prompt.inputInt("회원번호"));
    m.setName(Prompt.inputString("이름" ));
    m.setEmail(Prompt.inputString("이메일"));
    m.setPhoto(Prompt.inputString("사진"));
    m.setHp(Prompt.inputString("전화번호"));
    m.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));

    memberList.add(m);

    System.out.println("회원을 등록하였습니다.");
  }

}