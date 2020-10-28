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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@Autowired
	@Qualifier("emsService")
	private EmsService emsService;
	
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
		
		int ret=emsService.insert(emsVO);
		
		if(ret>0) {
			log.debug("INSERT 수행 결과 : {}개 성공", ret);
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
	public String update(EmsVO emsVO) {
		log.debug("UPDATE 요청 데이터 : {}", emsVO.toString());
		
		int ret=emsService.update(emsVO);
		if(ret>0) {
			log.debug("업데이트에 성공한 데이터 개수 : {}", ret);
		}
				
		return "redirect:/list";
	}
	
	@RequestMapping(value="/delete/{ems_id}",method=RequestMethod.GET)
	public String delete(@PathVariable("ems_id") String ems_id, Model model) {
		long long_id = Long.valueOf(ems_id);
		int ret=emsService.delete(long_id);
		
		if(ret>0) {
			log.debug("삭제된 데이터 개수 : {}", ret);
		}
				
		return "redirect:/list";
	}
	
}
