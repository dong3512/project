package com.dong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.dong.pms.domain.Board;
import com.dong.util.Prompt;

public class BoardAddHandler extends AbstractBoardHandler{

  public BoardAddHandler(List<Board> boardList) {
    super(boardList);
  }

  public void add(){
    System.out.println("[칭찬게시글 등록]");

    Board b = new Board();

    b.setNo(Prompt.inputInt("회원번호"));
    b.setName(Prompt.inputString("이름" ));
    b.setTitle(Prompt.inputString("제목"));
    b.setContent(Prompt.inputString("내용"));
    b.setMessage(Prompt.inputString("전하고싶은말"));
    b.setWriter(Prompt.inputString("작성자"));
    b.setRegisteredDate(new Date(System.currentTimeMillis()));

    boardList.add(b);

    System.out.println("게시글을 등록하였습니다.");
  }


}

