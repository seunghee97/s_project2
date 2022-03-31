package com.care.root.mybatis.product;

import java.util.ArrayList;
import java.util.Map;

import com.care.root.board.dto.BoardDTO;
import com.care.root.product.dto.productDTO;

public interface ProductMapper {
	//상품목록
	public ArrayList<productDTO> getProduct();
	
	//상품등록
	public int writeSave(productDTO dto);
}
