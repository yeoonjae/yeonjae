package com.study.kakaopay.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.study.kakaopay.vo.KakaopayStartResponseVO;
import com.study.kakaopay.vo.KakaopayStartVO;

@Service
public class KakaoPayServiceImpl implements KakaoPayService{

	@Override
	public KakaopayStartResponseVO kakaopay(KakaopayStartVO startVO) throws URISyntaxException {
		//도구생성
		RestTemplate template = new RestTemplate();
		
		//Header생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK admin_key");
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
		return responseVO;
	}

}
