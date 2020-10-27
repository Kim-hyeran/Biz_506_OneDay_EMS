<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<style>
		nav {
            margin: 10px auto;
            width: 100%;
        }

        nav  div.nav-box {
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
        }

       nav div.nav_btn {
           width: 60px;
           height: 60px;
           background: #f1f1f1;
           margin: 10px;
           border-radius: 30%;
           overflow: hidden;
           position: relative;
       }

       nav div.nav_btn i {
           color: #79e5cb;
           line-height: 60px;
           font-size: 1.7rem;
           text-align: center;
           transition: 0.2s linear;
       }

       nav div.nav_btn i:hover {
           transform: scale(1.2);
           color: #f1f1f1;
       }

       nav div.nav_btn::before {
           content: "";
           position: absolute;
           width: 120%;
           height: 120%;
           background: #79e5cb;
           transform: rotate(45deg);
           left: -110%;
           top: 90%;
       }

       nav div.nav_btn:hover::before {
           animation: aaa 0.7s 1;
           top: -10%;
           left: -10%;
       }

       @keyframes aaa{
           0%{
               left: -110%;
               top: 90%;
           }
           50%{
               left: 10%;
               top: -30%;
           }
           100%{
               top: -10%;
               left: -10%;
           }
       }
</style>
<script>
	document.addEventListener("DOMContentLoaded", function() {
		document.querySelector("#ems-home").addEventListener("click", function() {
			document.location.href="${rootPath}/"
		})
		document.querySelector("#ems-list").addEventListener("click", function() {
			document.location.href="${rootPath}/list"
		})
		document.querySelector("#ems-write").addEventListener("click", function() {
			document.location.href="${rootPath}/write"
		})
	})
</script>
<nav>
	<div class="nav-box">
         <div class="nav_btn" id="ems-home"><i class="fas fa-home"></i></div>
         <div class="nav_btn" id="ems-list"><i class="fas fa-envelope-open-text"></i></div>
         <div class="nav_btn" id="ems-write"><i class="fas fa-pencil-alt"></i></div>
     </div>
</nav>