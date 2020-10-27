<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<style>
    table {
        border-collapse: collapse;
        text-align: center;
        width: 50%;
        margin: 0 auto;
    }

    tr th {
        border-bottom: 1px solid #ddd;
        padding: 5px;
    }
    
    tr td {
    	padding: 10px 0;
        cursor: pointer;
    }

    tbody tr:hover {
        background-color: #f1f1f1;
    }
</style>
<script>
/*
	document.addEventListener("DOMContentLoaded", function() {
		document.querySelector("table").addEventListener("click", function(event) {
			let tag_name = event.target.tagName
			if(tag_name === "TD") {
				let id=event.target.closest("TR").dataset.seq
				if(id) {
					document.location.href = "${rootPath}/ems/detail/" + id
				}
			}
		})
	})
*/
</script>
<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>NO</th>
			<th>수신인</th>
			<th>제목</th>
			<th>발송일자</th>
			<th>발송시각</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty EMS_LIST}">
		  <tr><td colspan="5">데이터가 없습니다</td></tr>
		</c:if>
		<c:forEach items="${EMS_LIST}" var="vo" varStatus="i">
		<tr class="ems-tr" data-seq="${vo.ems_id}">
			<td>${i.count}</td>
			<td>${vo.to_email}</td>
			<td data-seq="${vo.ems_id}" class="bbs-subject">
				${vo.s_subject}
			</td>
			<td>${vo.s_date}</td>
			<td>${vo.s_time}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>