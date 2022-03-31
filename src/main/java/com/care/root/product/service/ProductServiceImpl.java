package com.care.root.product.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.mybatis.product.ProductMapper;
import com.care.root.product.dto.productDTO;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired ProductMapper pm;
	@Autowired productFileService pfs;
	
	//상품목록
	public void productList(Model model) {
		try {
			model.addAttribute("productList",pm.getProduct());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//상품등록
	public String writeSave(MultipartHttpServletRequest mul, HttpServletRequest request) {
		//dto에 정보 입력
		productDTO dto =new productDTO();
		//dto.setId(mul.getParameter("id")); //int
		int a = Integer.parseInt(mul.getParameter("id"));
		dto.setId(a);
		dto.setName(mul.getParameter("name"));
		//dto.setPrice(mul.getParameter("price")); //int
		int b = Integer.parseInt(mul.getParameter("price"));
		dto.setPrice(b);
		
		MultipartFile file = mul.getFile("imgName"); // 파일에대한 정보만가져오기
		if(file.getSize()!=0) {
			dto.setImgName(pfs.saveFile(file));
		}else { //파일 선택 없음 
			dto.setImgName("nan");
		}
		
		int result = 0;
		
		try {
			result=pm.writeSave(dto);			
		}catch (Exception e) {
			e.printStackTrace();
		}
		 
		String msg,url;
		if(result==1) {
			//file success
			msg="새글이 추가 되었습니다.";
			url="/product/productList";
		}else {
			//file fail
			msg="문제 발생";
			url="/product/productForm";
		}
		return pfs.getMessage(request,msg,url);
	}
	
	
}
