package com.dong.util;

public class Stack extends List implements Cloneable {

  public Object push(Object item) {
    this.add(item);
    return item;
  }

  public Object pop() {
    return this.delete(this.size - 1);
  }

  @Override
  public Stack clone() throws CloneNotSupportedException {

    Stack stack = new Stack();

    for (int i = 0; i < this.size; i++) {
      stack.push(this.get(i));
    }

    return stack;
  }

  @Override
  public Iterator iterator() throws CloneNotSupportedException {

    Stack stack = this.clone();

    return new  Iterator(){

      @Override
      public boolean hasNext() {
        return Stack.this.size() > 0;
      }

      @Override
      public Object next() {
        return Stack.this.pop();
      }

    };
  }
}