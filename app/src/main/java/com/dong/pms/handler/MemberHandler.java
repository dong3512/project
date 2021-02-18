package com.dong.pms.handler;

import com.dong.pms.domain.Member;
import com.dong.util.Iterator;
import com.dong.util.List;
import com.dong.util.Prompt;

public class MemberHandler {

  private List memberList = new List();

  public List getMemberList() {
    return this.memberList;
  }

  public void category() {
    System.out.println("[회원정보]");
    System.out.println("1. 등록 2. 목록 3. 상세보기 4. 수정 5. 삭제");
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

  public void list() throws CloneNotSupportedException{
    System.out.println("[회원 목록]");

    Iterator iterator = memberList.iterator();

    while (iterator.hasNext()) {
      Member m = (Member) iterator.next();
      System.out.printf("%s, %s, %s, %s, %s, %s\n",
          m.getNo(), m.getName(), m.getEmail(), m.getPhoto(), m.getHp(), m.getRegisteredDate());
    }
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

  public void update(){
    System.out.println("[회원 수정]");

    int no = Prompt.inputInt("번호? ");

    Member member = findByNo(no);
    if ( member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }
    String name = Prompt.inputString(String.format("이름(%s)? ", member.getName()));
    String email = Prompt.inputString(String.format("이메일(%s)? ", member.getEmail()));
    String photo = Prompt.inputString(String.format("사진(%s)? ", member.getPhoto()));
    String hp = Prompt.inputString(String.format("전화번호(%s)? ", member.getHp()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N)");
    if(input.equalsIgnoreCase("Y")) {
      member.setName(name);
      member.setEmail(email);
      member.setPhoto(photo);
      member.setHp(hp);
      System.out.println("회원정보를 변경하였습니다.");
    }else {
      System.out.println("회원정보 변경을 취소하였습니다.");
    }
  }

  public void delete(){
    System.out.println("[회원 삭제]");

    int no = Prompt.inputInt("번호? ");

    Member member = findByNo(no);
    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N)");

    if (input.equalsIgnoreCase("Y")) {
      memberList.delete(member);
      System.out.println("회원을 삭제하였습니다.");

    } else {
      System.out.println("회원 삭제를 취소하였습니다.");
    }
  }

  public String inputMember(String promptTitle) {
    while(true) {
      String name = Prompt.inputString(promptTitle);
      if(name.length() == 0) {
        return null;
      } else if (findByName(name) != null) {
        return name;
      } else {
        System.out.println("등록된 회원이 아닙니다.");
      }
    }
  }

  private Member findByNo(int boardNo) {
    Object[] list = memberList.toArray();
    for (Object obj : list) {
      Member m = (Member) obj;
      if (m.getNo() == boardNo) {
        return m;
      }
    }
    return null;
  }

  private Member findByName(String name) {
    Object[] list = memberList.toArray();
    for (Object obj : list) {
      Member m = (Member) obj;
      if (m.getName().equals(name)) {
        return m;
      }
    }
    return null;
  }
}