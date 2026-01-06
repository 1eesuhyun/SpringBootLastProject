<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Yummy Blog - Food Blog Template</title>
    <link rel="icon" href="/img/core-img/favicon.ico">
    <link href="/style.css" rel="stylesheet">
    <link href="/css/responsive/responsive.css" rel="stylesheet">
    
    <script src="https://unpkg.com/vue@3.3.4/dist/vue.global.js"></script>
    <script src="https://unpkg.com/vue-demi"></script>
    <script src="https://unpkg.com/pinia@2.1.7/dist/pinia.iife.prod.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
    <div id="preloader">
        <div class="yummy-load"></div>
    </div>
    <div id="pattern-switcher">
        Bg Pattern
    </div>
    <div id="patter-close">
        <i class="fa fa-times" aria-hidden="true"></i>
    </div>

    <jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="${main_jsp }"></jsp:include>
    <jsp:include page="footer.jsp"></jsp:include>

    <script src="/js/jquery/jquery-2.2.4.min.js"></script>
    <script src="/js/bootstrap/popper.min.js"></script>
    <script src="/js/bootstrap/bootstrap.min.js"></script>
    <script src="/js/others/plugins.js"></script>
    <script src="/js/active.js"></script>
</body>
