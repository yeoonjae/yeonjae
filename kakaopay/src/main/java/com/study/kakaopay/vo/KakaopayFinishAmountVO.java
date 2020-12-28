package com.study.kakaopay.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class KakaopayFinishAmountVO {
	private int total;
	private int tax_free;
	private int vat;
	private int point;
	private int discount;
}
