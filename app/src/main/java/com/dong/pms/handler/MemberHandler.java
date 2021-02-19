package com.dong.pms.handler;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import com.dong.pms.domain.Member;
import com.dong.util.Prompt;

public class MemberHandler {

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  public void category() throws CloneNotSupportedException{

    ArrayList<Member> memberList = new ArrayList<>();
    MemberAddHandler memberAddHandler = new MemberAddHandler(memberList);
    MemberListHandler memberListHandler = new MemberListHandler(memberList);
    MemberDetailHandler memberDetailHandler = new MemberDetailHandler(memberList);
    MemberUpdateHandler memberUpdateHandler = new MemberUpdateHandler(memberList);
    MemberDeleteHandler memberDeleteHandler = new MemberDeleteHandler(memberList);
    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);

    System.out.println("[회원정보]");
    System.out.println("1. 등록 2. 목록 3. 상세보기 4. 수정 5. 삭제");
    String cmd = Prompt.inputString("입력> ");
    switch(cmd) {
      case "1":
        memberAddHandler.add();
        break;
      case "2":
        memberListHandler.list();
        break;
      case "3":
        memberDetailHandler.detail();
        break;
      case "4":
        memberUpdateHandler.update();
        break;
      case "5":
        memberDeleteHandler.delete();
        break;
      default:
        System.out.println("잘못된 명령입니다.");
    }
  }

}