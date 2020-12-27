<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<h1>결제페이지</h1>

<form action="prepare" method="post">
	<input type="text" name="item_name" placeholder="상품명" required>
	<br><br>
	<input type="text" name="quantity" placeholder="수량" required>
	<br><br>
	<input type="text" name="total_amount" placeholder="총 금액" required>
	<br><br>
	<input type="submit" value="결제하기">
</form>