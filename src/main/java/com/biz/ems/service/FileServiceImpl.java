package com.biz.ems.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.ems.model.EmsVO;

@Service("fileService")
public class FileServiceImpl implements FileService {

	@Autowired
	private String winPath;	// servlet-context에서 설정한 경로가 자동으로 담겨있게 된다.
	
	@Autowired
	private String linuxPath;

	private String fileUploadPath;
	
	@Autowired	// fileUpload라는 변수 setting
	public void fileUploadPath() {
		this.fileUploadPath=winPath;
		
		File dir=new File(linuxPath);
		if(dir.exists()) {
			this.fileUploadPath=linuxPath;
		}
	}

	@Override
	public String fileUp(MultipartFile file) {
		if (file == null) {
			return null;
		}

		String strUUID = UUID.randomUUID().toString();
		
		// file이 존재하지 않는 경우 폴더 생성
		if(file!=null) {
			File dir=new File(this.fileUploadPath);
			if(!dir.exists()) {
				dir.mkdirs();
			}

			String originalFilelName=file.getOriginalFilename();
			String saveFileName = strUUID + originalFilelName;
			File saveFile = new File(this.fileUploadPath, saveFileName);
		
			try {
				file.transferTo(saveFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return saveFileName;

		}
		
		return null;

	}
	
	@Override
	public List<String> filesUp(MultipartHttpServletRequest files) {
		List<MultipartFile> fileList=files.getFiles("file");
		List<String> fileNames=new ArrayList<String>();
		
		for(MultipartFile file : fileList) {
			String fileName=this.fileUp(file);
			fileNames.add(fileName);
		}
		
		return fileNames;
	}

	@Override
	public int fileDelete(String fileName) {
		File delFile=new File(this.fileUploadPath, fileName);
		
		if(delFile.exists()) {
			delFile.delete();
		}

		return 0;
	}

}
