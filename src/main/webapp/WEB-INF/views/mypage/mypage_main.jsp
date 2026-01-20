<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
 .card{
      box-shadow:0 4px 10px rgba(0,0,0,0.05);
      border:none;
      border-radius:12px;
    }
    .card-header{
      font-weight:700;
      font-size:1.1rem;
    }
    #food_list{
      height:600px;
      overflow-y:auto;
    }
    .food-item:hover{
      background-color:#f1f1f1;
      cursor:pointer;
    }
    img#food_poster{
      border-radius:8px;
    }
</style>
</head>
<body>
<body>
<div class="breadcumb-area"
	style="background-image: url(/img/bg-img/breadcumb.jpg);">
	<div class="container h-100">
		<div class="row h-100 align-items-center">
			<div class="col-12">
				<div class="bradcumb-title text-center">
					<h2>마이 페이지</h2>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="breadcumb-nav">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<!-- 검색 바 -->
					</ol>
				</nav>
			</div>
		</div>
	</div>
</div>
<section class="archive-area section_padding_80">
	<div class="container">
		<div class="col-sm-3">
			<jsp:include page="../mypage/mypage_menu.jsp"></jsp:include>
		</div>
		<div class="col-sm-9">
			<jsp:include page="${mypage_jsp }"></jsp:include>
		</div>
	</div>
</section>
</body>
</html>