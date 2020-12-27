package com.study.kakaopay.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//최초의 결제요청에 대한 응답을 담을 VO
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class KakaopayStartResponseVO {
	private String tid;
	private String next_redirect_app_url;
	private String next_redirect_pc_url;
	private String created_at;
}
