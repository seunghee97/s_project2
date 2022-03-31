package com.care.root.mybatis.board;

import java.util.List;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;

public interface BoardMapper {

	public List<BoardDTO> boardAllList(); //리턴을 list형태로 담기

	public int writeSave(BoardDTO dto); //성공시 1을 반환

	public BoardDTO contentView(int writeNo); //dto형식으로 데이터를 받는다.

	public void upHit(int writeNo);

	public int modify(BoardDTO dto);

	public int delete(int writeNo);

	public void addReply(BoardRepDTO dto);

	public List<BoardRepDTO> getRepList(int write_group);
	

}
