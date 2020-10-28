package com.biz.ems.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.ems.model.EmsVO;

@Service("fileService")
public class FileServiceImpl implements FileService {

	private final String rootFolder;

	public FileServiceImpl() {
		rootFolder = "C:/bizwork/workspace/upload";
	}

	@Override
	public String fileUp(MultipartFile file) {
		if (file == null) {
			return null;
		}

		File dir = new File(rootFolder);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		String strUUID = UUID.randomUUID().toString();
		String saveFileName = strUUID;

		File saveFile = new File(rootFolder, saveFileName);

		try {
			file.transferTo(saveFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// UUID가 부착된 파일 이름을 Controller
		return saveFileName;
	}

	@Override
	public boolean fileDelete(String s_file1, String s_file2) {
		boolean ret = false;

		File deleteFile = new File(rootFolder, s_file1);
		if (deleteFile.exists()) {
			ret = deleteFile.delete();
		}

		return false;
	}

	@Override
	public List<EmsVO> filesUp(MultipartHttpServletRequest files) {
		// TODO Auto-generated method stub
		return null;
	}

}
