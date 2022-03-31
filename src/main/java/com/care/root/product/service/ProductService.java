package com.care.root.product.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface ProductService {
	
	//상품목록
	public void productList(Model model);

	//상품등록
	public String writeSave(MultipartHttpServletRequest mul, HttpServletRequest request);
	
}
