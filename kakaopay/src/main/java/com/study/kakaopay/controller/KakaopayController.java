package com.study.kakaopay.controller;

import java.net.URISyntaxException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.kakaopay.service.KakaoPayService;
import com.study.kakaopay.vo.KakaopayStartResponseVO;
import com.study.kakaopay.vo.KakaopayStartVO;

@Controller
@RequestMapping("/pay")
public class KakaopayController {

	@Autowired
	private KakaoPayService service;
	
	@GetMapping("/prepare")
	public String prepare() {
		return "pay/prepare";
	}
	
	@PostMapping("/prepare")
	public String prepare(@ModelAttribute KakaopayStartVO startVO) throws URISyntaxException {
		startVO.setPartner_order_id(UUID.randomUUID().toString());
		startVO.setPartner_user_id("aaaaaa");
		KakaopayStartResponseVO responseVO = service.kakaopay(startVO);
		return "redirect:"+responseVO.getNext_redirect_pc_url();
	}
}
