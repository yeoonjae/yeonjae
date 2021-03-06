package com.study.kakaopay;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.study.kakaopay.vo.KakaopayStartResponseVO;
import com.study.kakaopay.vo.KakaopayStartVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test01 {

	@Test
	public void test() throws URISyntaxException {
		//필요데이터를 변수 선언
		KakaopayStartVO startVO = KakaopayStartVO.builder()
							.partner_order_id(UUID.randomUUID().toString())
							.partner_user_id("cba")
							.item_name("아이패드 프로 4세대")
							.quantity(4)
							.total_amount(120000)
							.build();
		
		log.info("start");
		
		//도구생성
		RestTemplate template = new RestTemplate();
		
		//Header생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK e50a7f6a4d11bdb17860ebea078db261");
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//Body생성
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", "TC0ONETIME");
		body.add("partner_order_id", startVO.getPartner_order_id());
		body.add("partner_user_id", startVO.getPartner_user_id());
		body.add("item_name", startVO.getItem_name());
		body.add("quantity", String.valueOf(startVO.getQuantity()));
		body.add("total_amount", String.valueOf(startVO.getTotal_amount()));
		body.add("tax_free_amount", "0");
		body.add("approval_url", "http://localhost:8080/kakaopay/pay/success");
		body.add("cancel_url", "http://localhost:8080/kakaopay/pay/cancle");
		body.add("fail_url", "http://localhost:8080/kakaopay/pay/fail");
		
		//Header와 Body합치기
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body,headers);
		
		//주소정의
		URI uri = new URI("https://kapi.kakao.com/v1/payment/ready");
		
		//template을 이용하여 요청을 전송
		KakaopayStartResponseVO responseVO = template.postForObject(uri, entity, KakaopayStartResponseVO.class);
		log.info("responseVO = {}",responseVO);
		
		log.info("finish");
	}
}
