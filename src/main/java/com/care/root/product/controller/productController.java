package com.care.root.product.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.product.service.ProductService;

@Controller
@RequestMapping("product")
public class productController {
	
	@Autowired ProductService ps;
	
	@GetMapping("productList")
	public String productList(Model model) {
		ps.productList(model);
		return "product/productList";
	}
	
	@GetMapping("productForm")
	public String productForm() {
		return "product/productForm";
	}
	
	//상품등록 FORM을 POST방식
	@PostMapping("writeSave")
	public void writeSave(MultipartHttpServletRequest mul,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String message=ps.writeSave(mul,request); 
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		out.print(message);
	}
}
