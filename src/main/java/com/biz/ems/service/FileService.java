package com.biz.ems.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.ems.model.EmsVO;

public interface FileService {
	
	public String fileUp(MultipartFile file);	// 단일 파일 업로드 : 실제 파일 업로드를 수행하는 method
	public List<String> filesUp(MultipartHttpServletRequest files);	// 복수 파일 업로드 : List로 전달받은 파일 데이터를 쪼개 FileUp method에 전달

	public int fileDelete(String fileName);

}
