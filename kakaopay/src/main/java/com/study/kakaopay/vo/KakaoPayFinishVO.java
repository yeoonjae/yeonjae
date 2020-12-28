package com.study.kakaopay.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KakaoPayFinishVO {
	private String aid;
	private String tid;
	private String cid;
	private String sid;
	private String partner_order_id;
	private String partner_user_id;
	private String payment_method_type;

	private KakaopayFinishAmountVO amount;
	private KakaopayFinishCardInfoVO card_info;

	private String item_name;
	private String item_code;
	private int quantity;
	private String created_at;
	private String approved_at;
	private String payload;
}