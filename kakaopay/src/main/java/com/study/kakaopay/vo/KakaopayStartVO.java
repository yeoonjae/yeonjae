package com.study.kakaopay.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//카카오페이 '결제 요청'을 보내기 위한 VO
@Builder @Data @AllArgsConstructor @NoArgsConstructor
public class KakaopayStartVO {
	
		private String partner_order_id; // 가맹점 주문번호 - 시퀀스 번호 생성 후 부여
		private String partner_user_id; // 가맹점 회원 id - 회원 이메일로 불러오기
		private String item_name;
		private int quantity; // 수량 : 1로 설정해놓기
		private int total_amount;
		
}
