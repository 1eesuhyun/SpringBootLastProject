<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.post-headline {
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}
</style>
</head>
<body>
<div class="breadcumb-area" style="background-image: url(/img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>${name }</h2>
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
        <div class="container" >
            <div class="row">
             <c:forEach var="vo" items="${list }">
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-post wow fadeInUp" data-wow-delay="0.1s">
                        <div class="post-thumb">
                         <a href="/seoul/detail_before?contentid=${vo.contentid }&contenttype=${vo.contenttype}">
                            <img src="${vo.image1 }" style="width: 350px;height: 240px;" title="${vo.title }">
                         </a>   
                        </div>
                        <div class="post-content">
                            <div class="post-meta d-flex">
                                <div class="post-author-date-area d-flex">
                                    <!-- Post Author -->
                                    <div class="post-author">
                                        <a href="#">${vo.address }</a>
                                    </div>
                                    <!-- Post Date -->
                                    <!-- <div class="post-date">
                                        <a href="#">-</a>
                                    </div> -->
                                </div>
                                <div class="post-comment-share-area d-flex">
                                    <!-- Post Favourite -->
                                    <div class="post-favourite">
                                        <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> 10</a>
                                    </div>
                                </div>
                            </div>
                            <a href="/seoul/detail_before?contentid=${vo.contentid }&contenttype=${vo.contenttype}">
                                <h4 class="post-headline">${vo.title }</h4>
                            </a>
                        </div>
                    </div>
                </div>
			 </c:forEach>

                <div class="col-12">
                    <div class="pagination-area d-sm-flex mt-15">
                        <nav aria-label="#">
                            <ul class="pagination">
                            <c:if test="${startPage>1 }">
                             <li class="page-item">
                                    <a class="page-link" href="/seoul/list?page=${startPage-1 }&cno=${cno}"><i class="fa fa-angle-double-left" aria-hidden="true"> 이전</i></a>
                                </li>
                                </c:if>
                              <c:forEach var="i" begin="${startPage }" end="${endPage }">
                                <li class="page-item ${i==curpage?'active':''} ">
                                    <a class="page-link" href="/seoul/list?page=${i }&cno=${cno}">${i}</a>
                                </li>
                              </c:forEach>  
                               <c:if test="${endPage<totalpage }">
                                <li class="page-item">
                                    <a class="page-link" href="/seoul/list?page=${endPage+1 }&cno=${cno}">다음 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
                                </li>
                               </c:if>
                            </ul>
                        </nav>
                        <div class="page-status">
                            <p>${curpage } page / ${totalpage } pages</p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </section>

</body>
</html>