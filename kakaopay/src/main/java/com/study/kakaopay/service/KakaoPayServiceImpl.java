package com.study.kakaopay.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.study.kakaopay.vo.KakaoPayFinishVO;
import com.study.kakaopay.vo.KakaopayStartResponseVO;
import com.study.kakaopay.vo.KakaopayStartVO;



@Service
public class KakaoPayServiceImpl implements KakaoPayService{

	
	public static final String CID = "TC0ONETIME";
	
	
	@Override
	public KakaopayStartResponseVO kakaopay(KakaopayStartVO startVO) throws URISyntaxException {
		//도구생성
		RestTemplate template = new RestTemplate();
		
		//Header생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK e50a7f6a4d11bdb17860ebea078db261");
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//Body생성
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", CID);
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
		return responseVO;
	}

	@Override
	public KakaoPayFinishVO approve(String partner_order_id, String partner_user_id, String pg_token, String tid) throws URISyntaxException {
		//1.도구 생성
		RestTemplate template = new RestTemplate();
		
		//2.Header 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK e50a7f6a4d11bdb17860ebea078db261");
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		//3.Body 생성
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", CID);
		body.add("partner_order_id", partner_order_id);
		body.add("partner_user_id", partner_user_id);
		body.add("tid", tid);
		body.add("pg_token", pg_token);
		
		//4.Header와 Body를 합성
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body,headers);

		//5.주소 정의
		URI uri = new URI("https://kapi.kakao.com/v1/payment/approve");

		//6.모든 준비가 완료되었으므로 template을 이용하여 요청을 전송
		KakaoPayFinishVO finishVO = template.postForObject(uri, entity, KakaoPayFinishVO.class);
		
		return finishVO;
	}

}
