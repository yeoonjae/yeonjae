package com.study.kakaopay.service;

import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import com.study.kakaopay.vo.KakaopayStartResponseVO;
import com.study.kakaopay.vo.KakaopayStartVO;

@Service
public interface KakaoPayService {
	KakaopayStartResponseVO kakaopay(KakaopayStartVO startVO) throws URISyntaxException;
}
