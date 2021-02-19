package com.dong.pms.handler;

import java.util.List;
import com.dong.pms.domain.Member;
import com.dong.util.Prompt;

public class MemberDetailHandler extends AbstractMemberHandler {

  public MemberDetailHandler(List<Member> memberList) {
    super(memberList);
  }
  public void detail(){
    System.out.println("[회원 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Member member = findByNo(no);
    if (member == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화번호: %s\n", member.getHp());
    System.out.printf("등록일 %s\n",member.getRegisteredDate());
  }

}