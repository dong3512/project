package com.dong.pms.handler;

import com.dong.pms.domain.Member;

public class MemberList {
  static final int DEFAULT_CAPACITY = 100;
  Member[] members = new Member[DEFAULT_CAPACITY];
  int size = 0;

  void add(Member m) {
    this.members[this.size++] = m;
  }

  Member [] toArray() {
    Member[] arr = new Member[this.size];
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.members[i];
    }
    return arr;
  }

  Member get(int memberNo) {
    int i = indexOf(memberNo);
    if(i == -1)
      return null;
    return members[i];
  }

  void delete(int memberNo) {
    int index = indexOf(memberNo);

    if (index == -1)
      return;

    for (int x = index + 1; x < this.size; x++) {
      this.members[x-1] = this.members[x];
    }
    this.members[--this.size] = null;
  }

  public boolean exist(String name) {
    for (int i = 0; i < this.size; i++) {
      if (name.equals(this.members[i].name)) {
        return true;
      }
    }
    return false;
  }

  int indexOf(int memberNo) {
    for (int i = 0; i < this.size; i++) {
      Member member = this.members[i];
      if (member.no == memberNo) {
        return i;
      }
    }
    return -1;
  }
}
