package com.dong.util;

public class ListIterator {

  List list;
  int cursor = 0;

  public ListIterator(List list) {
    this.list = list;
  }

  public boolean hasNext() {
    return cursor < list.size();
  }

  public Object next() {
    return list.get(cursor++);
  }
}
