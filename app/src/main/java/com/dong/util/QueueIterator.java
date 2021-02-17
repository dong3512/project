package com.dong.util;

public class QueueIterator {

  Queue queue;

  public QueueIterator(Queue queue) throws CloneNotSupportedException {
    this.queue = queue.clone();
  }

  public boolean hasNext() {
    return this.queue.size() > 0;
  }

  public Object next() {
    return this.queue.poll();
  }
}
