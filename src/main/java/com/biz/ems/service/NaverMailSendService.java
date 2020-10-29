package com.biz.ems.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.biz.ems.model.EmsVO;

@Service
public class NaverMailSendService {

	@Autowired
	private JavaMailSender xMail;
	
	@Autowired
	private String winPath;
	
	public void sendMail(EmsVO emsVO) {
		
		String uploadFolder=winPath;
		
		String from_email=emsVO.getFrom_email();
		String to_email=emsVO.getTo_email();
		
		String s_file1=emsVO.getS_file1();
		String s_file2=emsVO.getS_file2();
		
		/*
		 * Mime Type
		 * 인터넷 TCP/IP를 통해서 주고받는 문서(파일) 등은 고유의 형식을 갖는데, 이를 Mime Type이라고 한다.
		 * Hyper Text Markup tag로 만들어진 문서를 HTML DOC type이라고 하며, Web browser를 통해서 내용을 확인할 수 있다.
		 * Mime Type을 지정해 둔 문서는 인터넷으로부터 받았을 때 즉시 내용을 확인할 수 있다.
		 * 
		 * 지금 xMail을 통해서 만드는 Mime Type은 email viewer에서 확인할 수 있는 문서 형식이다.
		 * 이 문서는 메모장이나 Web Browser로 보면 그 내용을 정확히 확인하기 어렵다.
		 * 대표적을 xMail Mime type 문서를 볼 수 있는 어플리케이션에는 outlook이 있고, 각 스마트 기기의 고유 Email App 또한 해당된다.
		 * 
		 */
		MimeMessage message=xMail.createMimeMessage();
		
		// Mime Type으로 만들어진 문서를 인터넷으로 전송하는 데 여러가지 기능을 수행해주는 도구
		MimeMessageHelper mHelper=null;
		
		try {
			// 두 번째 값을 true로 설정 : HTML을 적용한 메시지를 보낼 수 있다.
			// 두 번째 값이 true일 때 메시지에 이미지 파일을 적용하여 전송할 수 있다.
			mHelper=new MimeMessageHelper(message, true, "UTF-8");
			
			mHelper.setFrom(from_email);
			mHelper.setTo(to_email);
			mHelper.setSubject(emsVO.getS_subject());
			
			// 두 번째 값을 true로 설정 : HTML을 적용한 메시지를 보낼 수 있다.
			mHelper.setText(emsVO.getS_content(), true);
			
			FileSystemResource rs=null;
			if(s_file1!=null&&!s_file1.isEmpty()) {
				rs=new FileSystemResource(new File(uploadFolder, s_file1));
				mHelper.addAttachment(s_file1, rs);
			}
			if(s_file2!=null&&!s_file2.isEmpty()) {
				rs=new FileSystemResource(new File(uploadFolder, s_file2));
				mHelper.addAttachment(s_file2, rs);
			}
			
			xMail.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
