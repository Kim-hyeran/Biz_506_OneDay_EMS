package com.biz.ems;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String write() {
		
		return "/ems/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(EmsVO emsVO, @RequestParam(name="file", required=false) MultipartFile file) {
		log.debug(emsVO.toString());
		
		emsService.insert(emsVO, file);

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
		
		model.addAttribute("EMSVO",emsService.findById(long_id));
				
		return "/ems/list";
	}
	
}
