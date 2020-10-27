package com.biz.ems.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.ems.model.EmsVO;

@Service("fileService")
public class FileServiceImpl implements FileService {

	@Override
	public String fileUp(MultipartFile file) {
		String fileName = file.getOriginalFilename();

		String rootPath = System.getProperty("catalina.home");

		File dir = new File(rootPath, "tmpFolder");
		if (!dir.exists()) {
			dir.mkdirs();
		}

		File serverSaveFile = new File(dir.getAbsolutePath(), fileName);

		FileOutputStream outFile;
		
		try {
			outFile = new FileOutputStream(serverSaveFile);
			BufferedOutputStream outStream = new BufferedOutputStream(outFile);

			byte[] fileData = file.getBytes();

			outStream.write(fileData);
			outStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean fileDelete(String s_file1, String s_file2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EmsVO> filesUp(MultipartHttpServletRequest files) {
		// TODO Auto-generated method stub
		return null;
	}

}
