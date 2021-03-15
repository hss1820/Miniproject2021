package kr.co.softsoldesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Homecontroller {
	
	//클라이언트가 어디서든 주소를 요청하며 String Home()을 호출함(강제로)
	@RequestMapping(value="/", method=RequestMethod.GET) //어디서든 / 불러오는
	public String Home() {
		
		return "index";
	}

}
