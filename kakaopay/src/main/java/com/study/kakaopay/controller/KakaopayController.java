package com.study.kakaopay.controller;

import java.net.URISyntaxException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.kakaopay.service.KakaoPayService;
import com.study.kakaopay.vo.KakaoPayFinishVO;
import com.study.kakaopay.vo.KakaopayStartResponseVO;
import com.study.kakaopay.vo.KakaopayStartVO;

import lombok.extern.slf4j.Slf4j;

@Controller @Slf4j
@RequestMapping("/pay")
public class KakaopayController {

	@Autowired
	private KakaoPayService service;
	
	@GetMapping("/prepare")
	public String prepare() {
		return "pay/prepare";
	}
	
	@PostMapping("/prepare")
	public String prepare(@ModelAttribute KakaopayStartVO startVO,HttpSession session) throws URISyntaxException {
		startVO.setPartner_order_id(UUID.randomUUID().toString());
		startVO.setPartner_user_id("aaaaaa");
		KakaopayStartResponseVO responseVO = service.kakaopay(startVO);
		
		log.info("partner_order_id={}",startVO.getPartner_order_id());
		log.info("partner_user_id={}",startVO.getPartner_user_id());
		
		//session에 데이터 추가
		session.setAttribute("partner_order_id", startVO.getPartner_order_id());
		session.setAttribute("partner_user_id", startVO.getPartner_user_id());
		session.setAttribute("tid", responseVO.getTid());
		
		return "redirect:"+responseVO.getNext_redirect_pc_url();
	}
	
	//결제 성공시
	@GetMapping("/success")
	public String success(@RequestParam String pg_token,HttpSession session,Model model) throws URISyntaxException {
		String partner_order_id = (String)session.getAttribute("partner_order_id");
		String partner_user_id = (String)session.getAttribute("partner_user_id");
		String tid = (String)session.getAttribute("tid");
		
		log.info("partner_order_id={}",partner_order_id);
		log.info("partner_user_id={}",partner_user_id);
		log.info("tid={}",tid);
		log.info("pg_token={}",pg_token);
		
		KakaoPayFinishVO finishVO = service.approve(partner_order_id, partner_user_id, pg_token, tid);
		model.addAttribute("finishVO", finishVO);
		
		return "pay/success";
	}
}
