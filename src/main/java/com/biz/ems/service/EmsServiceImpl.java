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
	
//	@Autowired
//	private FileService fileService;

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
		
		return emsDao.insert(emsVO);
	}

	@Override
	public int update(EmsVO emsVO) {
		
		return emsDao.update(emsVO);
	}

	@Override
	public int delete(long ems_id) {
		
		return emsDao.delete(ems_id);
	}

	@Override
	public void insert(EmsVO emsVO, MultipartFile file) {
		
	}

}
