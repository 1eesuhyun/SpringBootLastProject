<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/map.css">
<style type="text/css">
.a-btn, .page-link:hover {
	cursor: pointer;
}
.a-btn:hover {
	color:white !important;
	background-color: black;
}
</style>
<script>
 const SESSION_ID='${sessionScope.userid}'
 const CNO='${param.contentid}'
</script>
</head>
<body>
	<div class="breadcumb-area"
		style="background-image: url(/img/bg-img/breadcumb.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="bradcumb-title text-center">
						<h2>${vo.title }</h2>
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
			<div class="row">
				<table class="table">
					<tbody>
						<tr>
							<td width="30%" class="text-center" rowspan="8"><img
								src="${vo.image1 }" style="width: 400px; height: 300px;">
							</td>
							<td colspan="2"><h3>${vo.title }</h3></td>
						</tr>
						<tr>
							<td width="15%" class="text-center">주소</td>
							<td width="55%">${vo.address }</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">안내</td>
							<td width="55%">${vo.fsvo.infocenter }</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">영업시간</td>
							<td width="55%">${vo.fsvo.opentime }</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">휴무일</td>
							<td width="55%">${vo.fsvo.restdate }</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">주차</td>
							<td width="55%">${vo.fsvo.parking }</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">주메뉴</td>
							<td width="55%">${vo.fsvo.firstmenu }</td>
						</tr>
						<tr>
							<td width="15%" class="text-center">부메뉴</td>
							<td width="55%">${vo.fsvo.treatmenu }</td>
						</tr>
					</tbody>
				</table>
				<table class="table">
					<tbody>
						<tr>
							<td>${vo.fsvo.msg }</td>
						</tr>
						<tr>
						<td class="text-right">
							<sec:authorize access="hasRole('USER')">
								<a href="/reserve/reserve_main" class="btn btn-sm btn-primary">예약</a>
						    </sec:authorize>
								<a href="javascript:history.back()" class="btn btn-sm btn-primary">목록</a>
						</td>
						</tr>
					</tbody>
				</table>
				<table class="table">
					<tbody>
						<tr>
							<td class="text-center">
								<div class="map_wrap">
									<div id="map"
										style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
									<div id="menu_wrap" class="bg_white">
										<div class="option">
											<div>
												<form onsubmit="searchPlaces(); return false;">
													키워드 : <input type="text" value="${addrs }" id="keyword"
														size="15">
													<button type="submit">검색하기</button>
												</form>
											</div>
										</div>
										<hr>
										<ul id="placesList"></ul>
										<div id="pagination"></div>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="comment">
					<div class="comment_area section_padding_50 clearfix" style="padding-top: 30px;">
						<h4 class="mb-30">댓글 ({{store.count}})개</h4>
						<ol>
							<li class="single_comment_area" v-for="(rvo,index) in store.list" :key="index">
								<div class="comment-wrapper d-flex">
									<div class="comment-author">
										<img src="/img/man.png" v-if="rvo.sex==='남자'"> <img src="/img/woman.png" v-else>
									</div>
									<div class="comment-content">
										<span class="comment-date text-muted">{{rvo.dbday}}</span>
										<h5>{{rvo.name}}</h5>
										<p>{{rvo.msg}}</p>
										<a class="a-btn" v-if="store.sessionId===rvo.id" @click="store.toggleUpdate(rvo.no,rvo.msg)">{{store.upReplyNo===rvo.no?'취소':'수정'}}</a>
										<a class="a-btn" v-if="store.sessionId===rvo.id" @click="store.commonsDelete(rvo.no)">삭제</a>
										<a class="a-btn" v-if="store.sessionId!==null" @click="store.toggleReply(rvo.no)">댓글</a>
										<div class="comment-form" style="padding-left: 130px;" v-if="store.reReplyNo===rvo.no">
										<form action="#" method="post">
											<textarea v-model="store.replyMsg[rvo.no]" cols="75" rows="5" placeholder="Message" style="float: left; display: inline-block;"></textarea>
											<button type="button" class="btn-primary" style="float: left; width: 100px; height: 120px; display: inline-block;" @click="store.replyReply(rvo.no)">댓글수정</button>
										</form>
									</div>
									</div>
								</div>
								<div class="leave-comment-area section_padding_50 clearfix" v-if="store.sessionId!==''">
									<div class="comment-form" style="padding-left: 130px;" v-if="store.upReplyNo===rvo.no">
										<form action="#" method="post">
											<textarea v-model="store.updateMsg[rvo.no]" cols="75" rows="5" placeholder="Message" style="float: left; display: inline-block;"></textarea>
											<button type="button" class="btn-primary" style="float: left; width: 100px; height: 120px; display: inline-block;" @click="store.replyUpdate(rvo.no)">댓글수정</button>
										</form>
									</div>
									<ol class="children" v-if="rvo.group_tab===1">
										<li class="single_comment_area">
											<div class="comment-wrapper d-flex" v-if="rvo.group_tab===1">
												<!-- Comment Meta -->
												<div class="comment-author">
													<img src="/img/man.png" v-if="rvo.sex=='남자'">
													<img src="/img/woman.png" v-else>
												</div>
												<!-- Comment Content -->
												<div class="comment-content">
													<span class="comment-date text-muted">{{rvo.dbday}}</span>
													<h5>{{rvo.name}}</h5>
													<p>{{rvo.msg}}</p>
													<a class="a-link" v-if="store.sessindId===rvo.id">수정</a>
													<a class="a-link" v-if="store.sessindId===rvo.id">삭제</a>
													<div class="comment-form">
														<form action="#" method="post">
															<textarea ref="msgRef" v-model="store.msg" cols="95" rows="5" placeholder="Message"
																style="float: left; display: inline-block;"></textarea>
															<button type="button" class="btn-primary" style="float: left; width: 100px; height: 120px; display: inline-block;"
																@click="store.commonsInsert(msgRef)">{{store.upReplyNo===rvo.no?'취소':'수정'}}</button>
														</form>
													</div>
												</div>
											</div>
										</li>
									</ol>
								</div>
							</li>
						</ol>
					</div>
					<div class="leave-comment-area section_padding_50 clearfix" v-if="store.sessionId!==''" style="padding-left: 150px;">
						<div class="comment-form">
							<form action="#" method="post">
								<textarea ref="msgRef" v-model="store.msg" cols="95" rows="5" placeholder="Message" style="float: left; display: inline-block;"></textarea>
								<button type="button" class="btn-primary" style="float: left; width: 100px; height: 120px; display: inline-block;" @click="store.commonsInsert(msgRef)">댓글쓰기</button>
							</form>
						</div>
					</div>
					<div class="col-12">
                    <div class="pagination-area d-sm-flex mt-15">
                        <nav aria-label="#">
                            <ul class="pagination">
                             <li class="page-item" v-if="store.startPage>1">
                                    <a class="page-link" @click="store.pageChange(store.startPage-1)"><i class="fa fa-angle-double-left" aria-hidden="true"> 이전</i></a>
                                </li>
                                <li v-for="i in store.range" :class="i===store.curpage?'page-item active':'page-item'">
                                    <a class="page-link" @click="store.pageChange(i)">{{i}}</a>
                                </li>
                                <li class="page-item" v-if="store.endPage<store.totalpage">
                                    <a class="page-link" @click="store.pageChange(store.endPage+1)">다음 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
                                </li>
                            </ul>
                        </nav>
                        <div class="page-status">
                            <p>{{store.curpage }} page / {{store.totalpage }} pages</p>
                        </div>
                    </div>
                </div>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e1bce25e48b8adc14d4c02bcf026654c&libraries=services"></script>
	<script src="/vue/map.js"></script>
	<script src="/vue/axios.js"></script>
	<script src="/vue/reply/commonsReplyStore.js"></script>
	<script>
	 const {createApp,onMounted,ref}=Vue
	 const {createPinia}=Pinia
	 const replyApp=createApp({
		 setup(){
			 const store=useCommonsReplyStore()
			 const msgRef=ref(null)
			 
			 onMounted(()=>{
				 store.sessionId=SESSION_ID
				 store.commonsListData(CNO)
			 })
			 
			 return {
				 store,
				 msgRef
			 }
		 }
		 
	 })
	 replyApp.use(createPinia())
	 replyApp.mount('#comment')
	</script>
</body>
</html>