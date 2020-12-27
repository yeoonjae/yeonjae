package com.study.kakaopay;

import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.kakaopay.service.KakaoPayService;
import com.study.kakaopay.vo.KakaopayStartResponseVO;
import com.study.kakaopay.vo.KakaopayStartVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//여기서 하려는 테스트는 Spring을 연동시킨 테스트
@RunWith(SpringJUnit4ClassRunner.class)//spring과 JUnit4를 연동
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})//설정 파일 위치 정보
@WebAppConfiguration//웹과 관련된 설정을 무시
public class Test02 {
	
	@Autowired
	private KakaoPayService kakaoPayService;
	
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
		
		KakaopayStartResponseVO vo = kakaoPayService.kakaopay(startVO);
		log.info("responseVO = {}",vo);
	}
}
