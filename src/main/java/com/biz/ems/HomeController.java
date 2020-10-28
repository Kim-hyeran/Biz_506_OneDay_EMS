package com.biz.ems;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.biz.ems.model.EmsVO;
import com.biz.ems.service.EmsService;
import com.biz.ems.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@Autowired
	@Qualifier("emsService")
	private EmsService emsService;
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
				
		return "home";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<EmsVO> emsList = emsService.selectAll();
		
		model.addAttribute("EMS_LIST", emsList);
		
		return "/ems/list";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(Model model) {
		LocalDateTime ldt=LocalDateTime.now();
		String cd=DateTimeFormatter.ofPattern("yyyy-MM-dd").format(ldt);
		String ct=DateTimeFormatter.ofPattern("HH:mm").format(ldt);
		
		EmsVO emsVO=EmsVO.builder().s_date(cd).s_time(ct).build();
		
		model.addAttribute("EMSVO", emsVO);
		
		return "/ems/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	// required를 false로 설정해두면 insert 수행 시 파일을 첨부하지 않아도 오류가 발생하지 않는다.
	public String write(@ModelAttribute EmsVO emsVO, Model model, @RequestParam(value="file1", required=false) MultipartFile file1, @RequestParam(value="file2", required=false) MultipartFile file2) {
		log.debug(emsVO.toString());
		log.debug("*******************file1 : {}", file1.getOriginalFilename());
		log.debug("*******************file2 : {}", file2.getOriginalFilename());
		
		String file1Name=null;
		String file2Name=null;

		if(!file1.getOriginalFilename().isEmpty()) {
			file1Name=fileService.fileUp(file1);
			emsVO.setS_file1(file1Name);
		}
		if(!file2.getOriginalFilename().isEmpty()) {
			file2Name=fileService.fileUp(file2);
			emsVO.setS_file2(file2Name);
		}
		
		int ret=emsService.insert(emsVO);
		
		if(ret>0) {
			log.debug("*******************INSERT 수행 결과 : {}개 성공", ret);
		}
		
		model.addAttribute("EMSVO", emsVO);

		return "redirect:/list";
	}
	
	@RequestMapping(value="/detail/{ems_id}", method=RequestMethod.GET)
	public String detail(@PathVariable("ems_id") String ems_id, Model model) {
		long long_id=Long.valueOf(ems_id);
		EmsVO emsVO=emsService.findById(long_id);
		
		model.addAttribute("EMSVO", emsVO);
		
		return "/ems/detail";
	}
	
	@RequestMapping(value="/update/{ems_id}",method=RequestMethod.GET)
	public String update(@PathVariable("ems_id") String ems_id, Model model) {
		long long_id = Long.valueOf(ems_id);
		EmsVO emsVO=emsService.findById(long_id);
		
		model.addAttribute("EMSVO", emsVO);
				
		return "/ems/write";
	}
	
	@RequestMapping(value="/update/{ems_id}",method=RequestMethod.POST)
	public String update(EmsVO emsVO, @RequestParam(value="file1", required=false) MultipartFile file1, @RequestParam(value="file2", required=false) MultipartFile file2) {
		log.debug("UPDATE 요청 데이터 : {}", emsVO.toString());
		
		// 1. update할 데이터의 id값 추출
		long long_id=emsVO.getEms_id();
		
		// 2. DB에 저장된 데이터를 id값으로 select하여 추출
		EmsVO emsOldVO=emsService.findById(long_id);
		boolean file1_ex=file1.getOriginalFilename().isEmpty();
		
		// 3. upload된 file1이 있는지 검사해서 emsVO에 setting
		if(!file1_ex) {	// = file1_ex==false
			String file1_name=fileService.fileUp(file1);
			
			// 3-1. 업로드된 파일이 있을 경우(비어있지 않은 경우) 기존 파일을 삭제(fileDelete)하고 새 파일 담기
			if(emsOldVO.getS_file1()!=null&&!emsOldVO.getS_file1().isEmpty()) {
				fileService.fileDelete(emsOldVO.getS_file1());
			}
			emsVO.setS_file1(file1_name);
		// 3-2. 업로드된 파일이 없을 경우(비어있는 경우) 새 파일 그대로 담기
		} else if(file1_ex) {
			emsVO.setS_file1(emsOldVO.getS_file1());
		}
		
		boolean file2_ex=file2.getOriginalFilename().isEmpty();
		
		// 4. upload된 file2가 있는지 검사해서 emsVO에 setting
		if(!file2_ex) {
			String file2_name=fileService.fileUp(file2);
			
			if(emsOldVO.getS_file2()!=null&&!emsOldVO.getS_file2().isEmpty()) {
				fileService.fileDelete(emsOldVO.getS_file2());
			}
			emsVO.setS_file2(file2_name);
		} else if(file2_ex) {
			emsVO.setS_file2(emsOldVO.getS_file2());
		}
		
		int ret=emsService.update(emsVO);
		if(ret>0) {
			log.debug("*******************업데이트에 성공한 데이터 개수 : {}", ret);
		}
				
		return "redirect:/list";
	}
	
	@RequestMapping(value="/delete/{ems_id}",method=RequestMethod.GET)
	public String delete(@PathVariable("ems_id") String ems_id, Model model) {
		long long_id = Long.valueOf(ems_id);
		
		EmsVO emsVO=emsService.findById(long_id);
		
		if(emsVO.getS_file1()!=null) {
			fileService.fileDelete(emsVO.getS_file1());
		}
		
		if(emsVO.getS_file2()!=null) {
			fileService.fileDelete(emsVO.getS_file2());
		}
		
		int ret=emsService.delete(long_id);
		
		if(ret>0) {
			log.debug("삭제된 데이터 개수 : {}", ret);
		}
				
		return "redirect:/list";
	}
	
}
