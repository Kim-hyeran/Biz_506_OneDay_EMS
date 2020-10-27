<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<style>
    form#write-form {
        width: 60%;
        margin: 10px auto;
    }

    form#write-form label {
        display: inline-block;
        width: 18%;
        padding: 4px;
        margin: 4px;
        text-align: right;
        font-size: 0.9rem;
    }

    form#write-form input {
        display: inline-block;
        width: 70%;
        padding: 4px;
        margin: 4px;
        outline: none;
        border: none;
        border-bottom: 1px solid #ddd;
    }

    div#content-box {
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 5px 0;
    }

    form#write-form textarea {
        width: 80%;
        height: 200px;
        outline: none;
        border: 1px solid #ddd;
    }

    div#s_file {
        text-align: center;
    }

    div#s_file input {
        border: none;
        width: 80%;
    }

    div.button-box {
        text-align: center;
        margin: 10px 0;
    }

    form#write-form .button-box button {
        cursor: pointer;
        outline: none;
        border: none;
        padding: 13px;
        margin: 5px;
        border-radius: 30%;
        font-weight: bold;
    }

    form#write-form .button-box button#list {
        background-color: #f1f1f1;
        color: #79e5cb;
    }

    form#write-form .button-box button#save {
        background-color: #79e5cb;
        color: #f1f1f1;
    }
</style>
<script>
	document.addEventListener("DOMContentLoaded", function() {
		document.querySelector("#list").addEventListener("click", function() {
			document.location.href="${rootPath}/list"
		})
	})
</script>
<body>
    <form:form id="write-form" method="POST" enctype="multipart/form-data">
    	<div>
			<form:input path="ems_id" value="${EMSVO.ems_id}" type="hidden" />
		</div>
		<div>
			<label>작성일자</label>
			<form:input path="s_date" type="date" />
		</div>
		<div>
			<label>작성시각</label>
			<form:input path="s_time" type="time" />
		</div>
		<div>
			<label>발신주소</label>
			<form:input path="from_email" type="email" placeholder="sample@email.com" />
        </div>
        <div>
			<label>수신주소</label>
			<form:input path="to_email" type="email" placeholder="sample@email.com" />
		</div>
		<div>
			<label>제목</label>
			<form:input path="s_subject" placeholder="제목을 입력하세요" />
		</div>
		<div id="content-box">
			<textarea id="s_content" name="s_content" placeholder="내용을 입력하세요"></textarea>
		</div>
		<div id="s_file">
			<form:input type="file" path="file" accept="image/*" />
			<form:input type="file" path="file" accept="image/*" />
		</div>

		<div class="button-box">
			<button type="button" id="list">취소</button>
			<button type="submit" id="save">저장</button>
		</div>
</form:form>