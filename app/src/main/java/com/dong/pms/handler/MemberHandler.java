package com.dong.pms.handler;

import com.dong.pms.domain.Member;
import com.dong.util.Prompt;

public class MemberHandler {

  static final int LENGTH = 100;
  Member[] members = new Member[LENGTH];
  int size = 0;

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

    m.no = Prompt.inputInt("회원번호");
    m.name = Prompt.inputString("이름" );
    m.email = Prompt.inputString("이메일");
    m.photo = Prompt.inputString("사진");
    m.hp = Prompt.inputString("전화번호");
    m.registeredDate = new java.sql.Date(System.currentTimeMillis());

    this.members[size++] = m;
  }

  public void list(){
    System.out.println("[회원 목록]");
    for(int i = 0;i < size; i++) {
      Member m = this.members[i];
      System.out.printf("%s, %s, %s, %s, %s, %s\n",
          m.no, m.name, m.email, m.photo, m.hp, m.registeredDate);
    }
  }

  public void detail(){
    System.out.println("[회원 상세보기]");
    int no = Prompt.inputInt("번호? ");

    for(int i = 0;i < size; i++) {
      Member member = this.members[i];
      if (member.no == no) {
        System.out.printf("이름: %s\n", member.name);
        System.out.printf("이메일: %s\n", member.email);
        System.out.printf("사진: %s\n", member.photo);
        System.out.printf("전화번호: %s\n", member.hp);
        System.out.printf("등록일 %s\n",member.registeredDate);
      }
    }
  }

  public void update(){
    System.out.println("[회원 수정]");
    int no = Prompt.inputInt("번호? ");
    for(int i = 0; i < size; i++) {
      Member member = members[i];

      if(member.no == no) {
        String name = Prompt.inputString(String.format("이름(%s)? ", member.name));
        String email = Prompt.inputString(String.format("이메일(%s)? ", member.email));
        String photo = Prompt.inputString(String.format("사진(%s)? ", member.photo));
        String hp = Prompt.inputString(String.format("전화번호(%s)? ", member.hp));

        String input = Prompt.inputString("정말 변경하시겠습니까?(y/N)");
        if(input.equalsIgnoreCase("Y")) {
          member.name = name;
          member.email = email;
          member.photo = photo;
          member.hp = hp;
          System.out.println("게시글을 변경하였습니다.");
        }else {
          System.out.println("게시글 변경을 취소하였습니다.");
        }
        return;
      }
    }

    System.out.println("해당 번호의 게시글이 없습니다.");
  }

  public void delete(){
    System.out.println("[회원 삭제]");

    int no = Prompt.inputInt("번호? ");

    for(int i = 0;i < size; i++) {
      Member member = this.members[i];
      if(member.no == no ) {
        String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N)");

        if (input.equalsIgnoreCase("Y")) {
          this.members[i] = null;
          System.out.println("게시글을 삭제하였습니다.");
        } else {
          System.out.println("게시글 삭제를 취소하였습니다.");
        }
        return;
      }
    }
    System.out.println("해당 번호의 게시글이 없습니다.");
  }


  public boolean exist(String name) {
    for (int i = 0; i < this.size; i++) {
      if(name.equals(members[i].name)) {
        return true;
      }
    }
    return false;
  }
}
