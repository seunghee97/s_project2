 package com.care.root.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpI implements BoardService{
	@Autowired BoardMapper mapper;
	@Autowired BoardFileService bfs;
	
	//목록 조회
	public void boardAllList(Model model) {
		model.addAttribute("boardList",mapper.boardAllList());
	}
	
	//글 작성을 추가하기
	public String writeSave(MultipartHttpServletRequest mul, HttpServletRequest request) {
		//dto에 정보 입력
		BoardDTO dto =new BoardDTO();
		dto.setId(mul.getParameter("id"));
		dto.setTitle(mul.getParameter("title"));
		dto.setContent(mul.getParameter("content"));
		
		MultipartFile file = mul.getFile("image_file_name"); // 파일에대한 정보만가져오기
		if(file.getSize()!=0) {
			dto.setImageFileName(bfs.saveFile(file));
		}else { //파일 선택 없음 
			dto.setImageFileName("nan");
		}
		
		int result = 0;
		
		try {
			result=mapper.writeSave(dto);			
		}catch (Exception e) {
			e.printStackTrace();
		}
		 
		String msg,url;
		if(result==1) {
			//file success
			msg="새글이 추가 되었습니다.";
			url="/board/boardAllList";
		}else {
			//file fail
			msg="문제 발생";
			url="/board/writeForm";
		}
		return bfs.getMessage(request,msg,url);
	}
	
	//내용을 보기
	public void contentView(int writeNo, Model model) {
		model.addAttribute("data", mapper.contentView(writeNo));
		
		//조회수 
		upHit(writeNo);
	}

	private void upHit(int writeNo) {
		mapper.upHit(writeNo);
		
	}
	
	//수정된 내용 저장
	public String modify(MultipartHttpServletRequest mul, HttpServletRequest request) {
		// dto에 사용자의 정보를 넘겨주기
		BoardDTO dto= new BoardDTO();
		dto.setWriteNo(Integer.parseInt(mul.getParameter("writeNo"))); //문자열을 정수로 바꾸기
		dto.setTitle(mul.getParameter("title"));
		dto.setContent(mul.getParameter("content"));
		
		//파일을 수정했는지 안했는지 구분
		MultipartFile file = mul.getFile("imageFileName");
		if(file.getSize() != 0) {
			//수정시 파일이 선택이 있을시
			dto.setImageFileName(bfs.saveFile(file));
			//기존의 파일이름 지우기
			bfs.deleteImage(mul.getParameter("orginFileName"));
		}else { //수정시 파일을 선택하지  않았다면 원래 이름을 넣어줌 
			dto.setImageFileName(mul.getParameter("originFileName"));
		}
		
		int result=mapper.modify(dto); //mapper에  dto넣기 성공시 1이 반환 , 에러시 0이 반환 try,catch
		//메세지를 전달
		String msg,url;
		if(result ==1 ) {
			//성공시 메세지 만들기
			msg="성공적인 수정";
			url="/board/boardAllList";
		}else {
			msg="수정에 문제";
			url="/board/modify_form?writeNo="+dto.getWriteNo();			
		}
		return bfs.getMessage(request, msg, url);
	}
	
	//글 삭제
	public String boardDelete(int writeNo, String imageFileName, HttpServletRequest request) {
		
		int result = mapper.delete(writeNo);
		String msg ,url;
		if(result==1) {
			//삭제되었다면
			msg="성공적인 삭제";
			url="/board/boardAllList";
			bfs.deleteImage(imageFileName); //이미지 삭제
		}else {
			msg="삭제 실패";
			url="/board/contentView?writeNo="+writeNo;
		}
		return bfs.getMessage(request, msg, url);
		
	}
	
	//답변을 추가하기
	public void addReply(BoardRepDTO dto) {
		mapper.addReply(dto);
	}
	
	//답변을 조회하기
	public List<BoardRepDTO> getRepList(int write_group){
		return mapper.getRepList(write_group);
	}
}