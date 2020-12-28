package com.study.kakaopay.service;

import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import com.study.kakaopay.vo.KakaoPayFinishVO;
import com.study.kakaopay.vo.KakaopayStartResponseVO;
import com.study.kakaopay.vo.KakaopayStartVO;

@Service
public interface KakaoPayService {
	KakaopayStartResponseVO kakaopay(KakaopayStartVO startVO) throws URISyntaxException;
	KakaoPayFinishVO approve(String partner_order_id, String partner_user_id, String pg_token, String tid) throws URISyntaxException;
}
