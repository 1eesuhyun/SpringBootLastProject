<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- ****** Top Header Area Start ****** -->
    <div class="top_header_area">
        <div class="container">
            <div class="row">
                <div class="col-5 col-sm-6">
                    <!--  Top Social bar start -->
                    <div class="top_social_bar">
                        <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                        <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                        <a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
                        <a href="#"><i class="fa fa-skype" aria-hidden="true"></i></a>
                        <a href="#"><i class="fa fa-dribbble" aria-hidden="true"></i></a>
                    </div>
                </div>
                <!--  Login Register Area -->
                <div class="col-7 col-sm-6">
                    <div class="signup-search-area d-flex align-items-center justify-content-end">
                        <div class="login_register_area d-flex">
                          <sec:authorize access="!isAuthenticated()">
                            <div class="login">
                                <a href="/member/join">회원가입</a>
                            </div>
                           </sec:authorize>
                           <sec:authorize access="isAuthenticated()">
                             <div class="login">
                               <span style="color: purple;">🎉${sessionScope.username }님 로그인되었습니다🎉&nbsp;</span>
                             </div>
                           </sec:authorize>
                            <sec:authorize access="!isAuthenticated()">
                             <div class="register">
                                <a href="/member/login">로그인</a>
                             </div>
                            </sec:authorize>
                            <sec:authorize access="isAuthenticated()">
                             <div class="register">
                                <a href="/member/logout">로그아웃</a>
                             </div>
                            </sec:authorize>
                        </div>
                        <!-- Search Button Area -->
                        <!-- <div class="search_button">
                            <a class="searchBtn" href="#"><i class="fa fa-search" aria-hidden="true"></i></a>
                        </div>
                        Search Form
                        <div class="search-hidden-form">
                            <form action="#" method="get">
                                <input type="search" name="search" id="search-anything" placeholder="Search Anything...">
                                <input type="submit" value="" class="d-none">
                                <span class="searchBtn"><i class="fa fa-times" aria-hidden="true"></i></span>
                            </form>
                        </div> -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Top Header Area End ****** -->

    <!-- ****** Header Area Start ****** -->
    <header class="header_area">
        <div class="container">
            <div class="row">
                <!-- Logo Area Start -->
                <div class="col-12">
                    <div class="logo_area text-center">
                        <a href="/main" class="yummy-logo">Travel</a>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <nav class="navbar navbar-expand-lg">
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#yummyfood-nav" aria-controls="yummyfood-nav" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars" aria-hidden="true"></i> Menu</button>
                        <!-- Menu Area Start -->
                        <div class="collapse navbar-collapse justify-content-center" id="yummyfood-nav">
                            <ul class="navbar-nav" id="yummy-nav">
                                <li class="nav-item active">
                                    <a class="nav-link" href="/main">Home <span class="sr-only">(current)</span></a>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="yummyDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">서울여행</a>
                                    <div class="dropdown-menu" aria-labelledby="yummyDropdown">
                                        <a class="dropdown-item" href="/seoul/list?cno=12">관광지</a>
                                        <a class="dropdown-item" href="/seoul/list?cno=14">문화시설</a>
                                        <a class="dropdown-item" href="/seoul/list?cno=15">축제 & 공연</a>
                                        <a class="dropdown-item" href="/seoul/list?cno=32">숙박</a>
                                        <a class="dropdown-item" href="/seoul/list?cno=38">쇼핑</a>
                                        <a class="dropdown-item" href="/seoul/list?cno=39">음식</a>
                                    </div>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="yummyDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">부산여행</a>
                                    <div class="dropdown-menu" aria-labelledby="yummyDropdown">
                                        <a class="dropdown-item" href="/busan/list?cno=12">관광지</a>
                                        <a class="dropdown-item" href="/busan/list?cno=14">문화시설</a>
                                        <a class="dropdown-item" href="/busan/list?cno=15">축제 & 공연</a>
                                        <a class="dropdown-item" href="/busan/list?cno=32">숙박</a>
                                        <a class="dropdown-item" href="/busan/list?cno=38">쇼핑</a>
                                        <a class="dropdown-item" href="/busan/list?cno=39">음식</a>
                                    </div>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="yummyDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">제주여행</a>
                                    <div class="dropdown-menu" aria-labelledby="yummyDropdown">
                                        <a class="dropdown-item" href="/jeju/list?cno=12">관광지</a>
                                        <a class="dropdown-item" href="/jeju/list?cno=14">문화시설</a>
                                        <a class="dropdown-item" href="/jeju/list?cno=15">축제 & 공연</a>
                                        <a class="dropdown-item" href="/jeju/list?cno=32">숙박</a>
                                        <a class="dropdown-item" href="/jeju/list?cno=38">쇼핑</a>
                                        <a class="dropdown-item" href="/jeju/list?cno=39">음식</a>
                                    </div>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="yummyDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">검색</a>
                                    <div class="dropdown-menu" aria-labelledby="yummyDropdown">
                                        <a class="dropdown-item" href="/seoul/find">서울 여행 검색</a>
                                        <a class="dropdown-item" href="/busan/find">부산 여행 검색</a>
                                        <a class="dropdown-item" href="/jeju/find">제주 여행 검색</a>
                                    </div>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="yummyDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">커뮤니티</a>
                                    <div class="dropdown-menu" aria-labelledby="yummyDropdown">
                                        <a class="dropdown-item" href="/board/list">자유게시판</a>
                                        <a class="dropdown-item" href="#">공지사항</a>
                                        <sec:authorize access="isAuthenticated()">
                                         <a class="dropdown-item" href="/chatbot/chatbot">챗봇</a>
                                         <a class="dropdown-item" href="#">1:1 채팅</a>
                                         <a class="dropdown-item" href="#">그룹 채팅</a>
                                        </sec:authorize>
                                    </div>
                                </li>
                               <sec:authorize access="isAuthenticated()">
                                   <sec:authorize access="hasRole('USER')">
	                                <li class="nav-item">
	                                    <a class="nav-link" href="/mypage/mypage_main">마이페이지</a>
	                                </li>
	                               </sec:authorize>
	                               
	                               <sec:authorize access="hasRole('ADMIN')">
	                                <li class="nav-item">
	                                    <a class="nav-link" href="/admin/admin_main">관리자페이지</a>
	                                </li>
	                               </sec:authorize>
                                </sec:authorize>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- ****** Header Area End ****** -->
</body>
</html>