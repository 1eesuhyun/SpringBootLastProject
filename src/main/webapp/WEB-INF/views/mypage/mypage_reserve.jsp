<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/toast.css">
<script type="text/javascript">
 const ID='${sessionScope.userid}'
</script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<table class="table">
		<tr>
			<td class="text-center"><h3>예약 목록</h3></td>
		</tr>
	</table>
	<div id="reserveApp">
		<table class="table">
			<thead>
				<tr class="table-danger">
					<th class="text-center">예약번호</th>
					<th class="text-center">업체명</th>
					<th class="text-center"></th>
					<th class="text-center">예약일</th>
					<th class="text-center">예약시간</th>
					<th class="text-center">인원</th>
					<th class="text-center">등록일</th>
					<th class="text-center"></th>
				</tr>
				<tr v-for="(vo,i) in store.reserve_list" :key="i">
					<td class="text-center">{{vo.no}}</td>
					<td>{{vo.svo.title}}</td>
					<td class="text-center"><img :src="vo.svo.image1" style="width: 50px;height: 50px"></td>
					<td class="text-center">{{vo.rday}}</td>
					<td class="text-center">{{vo.rtime}}</td>
					<td class="text-center">{{vo.rinwon}}</td>
					<td class="text-center">{{vo.dbday}}</td>
					<td class="text-center">
						<button class="btn-xs btn-info" style="margin-left: 2px" v-if="vo.isReserve===0">예약대기</button>
						<button class="btn-xs btn-success" style="margin-left: 2px" v-else @click="store.reserveDetail(vo.no)">예약완료</button>
						<button class="btn-xs btn-danger" style="margin-left: 2px" @click="store.reserveRequest(vo.no)" v-if="vo.iscancel==0">취소요청</button>
						<button class="btn-xs btn-success" style="margin-left: 2px;" disabled v-else>취소대기</button>
					</td>
				</tr>
			</thead>
		</table>
		  <div v-if="store.isShow">
			<table class="table">
				<tbody>
				 <tr>
				  <th colspan="8"><h3>예약정보</h3></th>
				 </tr>
					<tr>
						<th class="text-center">예약번호</th>
						<td class="text-center">{{store.reserve_detail.no}}</td>
						<th class="text-center">예약일</th>
						<td class="text-center">{{store.reserve_detail.rday}}</td>
						<th class="text-center">예약시간</th>
						<td class="text-center">{{store.reserve_detail.rtime}}</td>
						<th class="text-center">예약인원</th>
						<td class="text-center">{{store.reserve_detail.rinwon}}</td>
					</tr>
					</tbody>
					</table>
					<table class="table">
					<tbody>
					<tr>
						<th><h3>맛집정보</h3></th>
					</tr>
					<tr>
						<td width="30%" class="text-center" rowspan="8"><img :src="store.reserve_detail.svo.image1" style="width: 200px; height: 150px;">
						</td>
						<td colspan="2"><h3>{{store.reserve_detail.svo.title }}</h3></td>
					</tr>
					<tr>
						<td width="15%" class="text-center">주소</td>
						<td width="55%">{{store.reserve_detail.svo.address }}</td>
					</tr>
					<tr>
					 <td colspan="2" class="text-right">
					  <button class="btn-sm btn-warning" @click="store.isShow=false">닫기</button>
					 </td>
					 </tr>
				</tbody>
			</table>
		  </div>
		  <jsp:include page="../commons/toast.jsp"></jsp:include>
	</div>
	<script src="/vue/axios.js"></script>
	<script src="/vue/reserve/mypageStore.js"></script>
	<script>
	 const {createPinia} = Pinia
	 const {createApp,onMounted} = Vue
	 
	 const app=createApp({
		 setup(){
			 const store=useMypageStore()
			 
			 onMounted(()=>{
				 store.dataRecv()
				 store.connect(ID)
			 })
			 
			 return {
				 store
			 }
		 }
	 })
	 app.use(createPinia())
	 app.mount('#reserveApp')
	</script>
</body>
</html>