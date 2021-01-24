package com.dong.pms.handler;

import java.sql.Date;
import com.dong.util.Prompt;

public class MemberHandler {

  static class Member{
    int no ;
    String name ;
    String email ;
    String photo ;
    String hp ;
    Date registeredDate ;
  }
  static final int LENGTH = 100;
  static Member[] members = new Member[LENGTH];
  static int size = 0;

  public static void add(){
    System.out.println("[회원 등록]");

    Member m = new Member();

    m.no = Prompt.inputInt("회원번호");
    m.name = Prompt.inputString("이름" );
    m.email = Prompt.inputString("이메일");
    m.photo = Prompt.inputString("사진");
    m.hp = Prompt.inputString("전화번호");
    m.registeredDate = new java.sql.Date(System.currentTimeMillis());

    members[size++] = m;
  }
  public static void list(){
    System.out.println("[회원 목록]");
    for(int i = 0;i < size; i++) {
      Member m = members[i];
      System.out.printf("%s, %s, %s, %s, %s, %s\n",
          m.no, m.name, m.email, m.photo, m.hp, m.registeredDate);
    }
  }

  public static boolean exist(String name) {
    for (int i = 0; i < size; i++) {
      if(name.equals(members[i].name)) {
        return true;
      }
    }
    return false;
  }
}
