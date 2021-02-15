package com.dong.pms.handler;

import com.dong.pms.domain.Board;

public class BoardList {
  static final int DEFAULT_CAPACITY = 100;
  Board[] boards = new Board[DEFAULT_CAPACITY];
  int size = 0;

  void add(Board b) {
    this.boards[this.size++] = b;
  }

  Board[] toArray() {
    Board[] arr = new Board[this.size];
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.boards[i];
    }
    return arr;
  }

  Board get(int boardNo) {
    int index = indexOf(boardNo);
    if (index != -1) {
      return this.boards[index];
    }
    return null;
  }

  void delete(int boardNo) {
    int index = indexOf(boardNo);

    if (index == -1)
      return;

    for (int x = index + 1; x < this.size; x++) {
      this.boards[x-1] = this.boards[x];
    }
    this.boards[--this.size] = null;
  }

  int indexOf(int boardNo) {
    for (int i = 0; i < this.size; i++) {
      Board board = this.boards[i];
      if (board.no == boardNo) {
        return i;
      }
    }
    return -1;
  }
}
