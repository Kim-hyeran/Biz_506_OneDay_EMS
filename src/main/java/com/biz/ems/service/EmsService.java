package com.biz.ems.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.biz.ems.model.EmsVO;

public interface EmsService {
	
	public List<EmsVO> selectAll();
	public EmsVO findById(long ems_id);
	public int insert(EmsVO emsVO);
	public int update(EmsVO emsVO);
	public int delete(long ems_id);
	
	public void insert(EmsVO emsVO, MultipartFile file);

}
