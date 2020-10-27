package com.biz.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.biz.ems.mapper.EmsDao;
import com.biz.ems.model.EmsVO;

@Service("emsService")
public class EmsServiceImpl implements EmsService {
	
	@Autowired
	private EmsDao emsDao;
	
	@Autowired
	private FileService fileService;

	@Override
	public List<EmsVO> selectAll() {
		// TODO Auto-generated method stub
		return emsDao.selectAll();
	}

	@Override
	public EmsVO findById(long ems_id) {

		return emsDao.findById(ems_id);
	}

	@Override
	public int insert(EmsVO emsVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(EmsVO emsVO) {
		// TODO Auto-generated method stub
		return emsDao.update(emsVO);
	}

	@Override
	public int delete(long ems_id) {
		EmsVO emsVO=emsDao.findById(ems_id);
		
		String s_file1=emsVO.getS_file1();
		String s_file2=emsVO.getS_file2();
		
		if(s_file1!=null||s_file2!=null) {
			fileService.fileDelete(s_file1, s_file2);
		}
		
		return emsDao.delete(ems_id);
	}

	@Override
	public void insert(EmsVO emsVO, MultipartFile file) {
		String fileName = fileService.fileUp(file);
		emsVO.setS_file1(fileName);
		emsVO.setS_file2(fileName);
	}

}
