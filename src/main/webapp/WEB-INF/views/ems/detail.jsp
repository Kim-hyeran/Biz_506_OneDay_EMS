<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<style>
	section#detail-body {
		width: 50%;
		margin: 20px auto;
		display: flex;
	}
	
	section#detail-body article {
		width: 100%;
	}
	
	section#detail-body div {
		margin: 5px;
		padding: 3px;
	}
	
	section#detail-body .title {
		display: inline-block;
		padding-bottom: 0;
		font-size: 1.3rem;
		font-weight: bold;
		border-bottom: 5px solid #79e5cb;
	}
	
	section#detail-body .to_email {
		padding-bottom: 10px;
		border-bottom: 1px solid #ddd;
	}
	
	section#detail-button {
		text-align: center;
		margin: 10px 0;
	}
	
	section#detail-button button {
		cursor: pointer;
		outline: none;
		border: none;
		padding: 13px;
		margin: 5px;
		border-radius: 30%;
		font-weight: bold;
	}
	
	section#detail-button button.delete {
		background-color: #f1f1f1;
		color: #79e5cb;
	}
	
	section#detail-button button.update {
		background-color: #79e5cb;
		color: #f1f1f1;
	}
</style>
<script>
	document.addEventListener("DOMContentLoaded", function () {
		let trash = document.querySelector(".delete")
		trash.addEventListener("click", function () {
			if(confirm("삭제할까요?")) {
				document.location.href="${rootPath}/delete/${EMSVO.ems_id}"
				return false
			}
		})
	})
</script>
<section id="detail-body">
	<article>
		<div class="title">${EMSVO.s_subject}</div>
		<div class="date-time">${EMSVO.s_date} | ${EMSVO.s_time}</div>
		<div class="from_email">발신 : ${EMSVO.from_email}</div>
		<div class="to_email">수신 : ${EMSVO.to_email}</div>
		<div class="content">${EMSVO.s_content}</div>
		<div class="image">${EMSVO.s_file1}</div>
		<div class="image">${EMSVO.s_file2}</div>
	</article>
</section>
<section id="detail-button">
	<a href="${rootPath}/update/${EMSVO.ems_id}"><button class="update">수정</button></a>
	<button class="delete">삭제</button>
</section>