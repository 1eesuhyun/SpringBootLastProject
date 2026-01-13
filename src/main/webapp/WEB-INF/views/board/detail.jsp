<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
 .a-btn{
 	cursor: pointer;
 }
 .a-btn:hover {
	color: white !important;
	background-color: black;
}
</style>
<script>
 const SESSION_ID='${sessionScope.userid}'
 const BNO='${param.no}'
</script>
</head>
<body>
	<div class="breadcumb-area"
		style="background-image: url(/img/bg-img/breadcumb.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="bradcumb-title text-center">
						<h2>상세보기</h2>
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

						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<section class="archive-area section_padding_80">
		<div class="container">
			<div class="row justify-content-center">
				<table class="table">
					<tbody>
						<tr>
							<th width="20%" class="text-center table-danger">번호</th>
							<td width="30%">${vo.no }</td>
							<th width="20%" class="text-center table-danger">작성일</th>
							<td width="30%">${vo.dbday }</td>
						</tr>
						<tr>
							<th width="20%" class="text-center table-danger">이름</th>
							<td width="30%">${vo.name }</td>
							<th width="20%" class="text-center table-danger">조회수</th>
							<td width="30%">${vo.hit }</td>
						</tr>
						<tr>
							<th width="20%" class="text-center table-danger">제목</th>
							<td colspan="3" class="text-left">${vo.subject }</td>
						</tr>
						<tr>
							<td colspan="4" class="text-left" valign="top" height="200">
								<pre
									style="white-space: pre-wrap; border: none; background-color: white">${vo.content }</pre>
							</td>
						</tr>
					</tbody>
				</table>
				<table class="table">
					<tr>
						<td class="text-right"><a href="/board/update?no=${vo.no }"
							class="btn btn-sm btn-outline-secondary">수정</a> <a
							href="/board/delete?no=${vo.no }"
							class="btn btn-sm btn-outline-danger">삭제</a> <a
							href="/board/list" class="btn btn-sm btn-outline-dark">목록</a></td>
					</tr>
				</table>
				<!-- Comment Area Start -->
			<div id="comment">
				<div class="comment_area section_padding_50 clearfix" style="padding-top: 30px;">
					<h4 class="mb-30">댓글 ({{store.count}})개</h4>

					<ol>
						<!-- Single Comment Area -->
						<li class="single_comment_area" v-for="(rvo,index) in store.list" :key="index">
							<div class="comment-wrapper d-flex">
								<!-- Comment Meta -->
								<div class="comment-author">
									<img src="/img/man.png" v-if="rvo.sex==='남자'">
									<img src="/img/woman.png" v-else>
								</div>
								<!-- Comment Content -->
								<div class="comment-content">
									<span class="comment-date text-muted">{{rvo.dbday}}</span>
									<h5>{{rvo.name}}</h5>
									<p>{{rvo.msg}}</p>
									<a class="a-btn" v-if="store.sessionId===rvo.id" @click="store.toggleUpdate(rvo.no,rvo.msg)">{{store.upReplyNo===rvo.no?'취소':'수정'}}</a>
									<a class="a-btn" v-if="store.sessionId===rvo.id" @click="store.replyDelete(rvo.no)">삭제</a>
								</div>
							</div>
							<div class="leave-comment-area section_padding_50 clearfix" v-if="store.sessionId!==''">
			                    <div class="comment-form" style="padding-left: 130px;" v-if="store.upReplyNo===rvo.no">
			                        <form action="#" method="post" >
			                           <textarea v-model="store.updateMsg[rvo.no]" cols="75" rows="5" placeholder="Message" style="float: left;display: inline-block;"></textarea>
			                           <button type="button" class="btn-primary" style="float: left;width: 100px;height: 120px;display: inline-block;" @click="store.replyUpdate(rvo.no)">댓글수정</button>
			                        </form>
			                    </div>
                			</div>
						</li>
					</ol>
				</div>
				<div class="leave-comment-area section_padding_50 clearfix" v-if="store.sessionId!==''">
                    <div class="comment-form">
                        <form action="#" method="post" >
                           <textarea ref="msgRef" v-model="store.msg" cols="95" rows="5" placeholder="Message" style="float: left;display: inline-block;"></textarea>
                           <button type="button" class="btn-primary" style="float: left;width: 100px;height: 130px;display: inline-block;" @click="store.replyInsert(msgRef)">댓글쓰기</button>
                        </form>
                    </div>
                </div>
              </div>
               <script src="/vue/axios.js"></script>
               <script src="/vue/reply/boardReplyStore.js"></script>
                <script>
                 const {createApp,onMounted,ref}=Vue
                 const {createPinia}=Pinia
                 const commentApp=createApp({
                	 setup(){
                		 const store=useBoardReplyStore()
                		 const msgRef=ref(null)
                		 onMounted(()=>{
                			 store.sessionId=SESSION_ID
                			 store.replyListData(BNO)
                		 }) // useEffect(()=>{})
                		 return {
                			 store,
                			 msgRef
                		 }
                	 }
                 })
                 commentApp.use(createPinia())
                 commentApp.mount('#comment')
                </script>
			</div>
		</div>
	</section>
</body>
</html>