package com.study.kakaopay;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test01 {

	@Test
	public void test() throws URISyntaxException {
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
		body.add("partner_order_id", "partner_order_id");
		body.add("partner_user_id", "partner_user_id");
		body.add("item_name", "아이패드 프로 4세대");
		body.add("quantity", "1");
		body.add("total_amount", "1280");
		body.add("tax_free_amount", "0");
		body.add("approval_url", "https://localhost:8080/kakaopay/pay/success");
		body.add("cancel_url", "https://localhost:8080/kakaopay/pay/cancle");
		body.add("fail_url", "https://localhost:8080/kakaopay/pay/fail");
		
		//Header와 Body합치기
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body,headers);
		
		//주소정의
		URI uri = new URI("https://kapi.kakao.com/v1/payment/ready");
		
		//template을 이용하여 요청을 전송
		template.postForLocation(uri, entity);
		
		
		log.info("finish");
	}
}
