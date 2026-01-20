<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js"></script>
<style type="text/css">
.card {
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
	border: none;
	border-radius: 12px;
}

.card-header {
	font-weight: 700;
	font-size: 1.1rem;
}

#food_list {
	height: 600px;
	overflow-y: auto;
}

.food-item:hover {
	background-color: #f1f1f1;
	cursor: pointer;
}

img#food_poster {
	border-radius: 8px;
}

.r_select {
	height: 38px;
	font-size: 14px;
	border-radius: 6px;
	border: 1px solid #ddd;
}

.r_select:hover {
	border-color: #5cb85c;
	box-shadow: 0 0 5px rgba(92, 184, 92, 0.4);
}
.foodTitle{
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden;
	max-width: 180px;
}
.link:hover{
	cursor: pointer;
	background-color: #FC59;
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
						<h2>맛집 예약</h2>
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
	<section class="archive-area section_padding_80" id="reserve_app">
		<div class="container">
			<div class="row">
				<div class="container my-4">
					<div class="row g-4">
						<!-- 맛집 정보 -->
						<div class="col-md-4">
							<div class="card h-100">
								<div class="card-header bg-danger text-white text-center">맛집
									정보</div>
								<div class="card-body" id="food_list">
									<table class="table">
										<tr>
											<td class="text-center" colspan="2">
												<select class="form-control r_select" v-model="store.loc" @change="store.locChange">
													<option value="all">서울전체</option>
													<option v-for="d in store.loc_list" :value="d" >{{d}}</option>
												</select>
											</td>
										</tr>
										<tr v-for="(vo,index) in store.food_list" :key="index" class="link" @click="store.foodSelect(vo.contentid,vo.title,vo.image1)">
										 <td>
										  <img :src="vo.image1" style="width: 30px;height: 30px">
										 </td>
										 <td>
										  <div class="foodTitle" :title="vo.title">{{vo.title}}</div>
										 </td>
										</tr>
										<tr>
										 <td colspan="2" class="text-center">
										  <button class="btn-xs btn-primary" @click="store.prev()">이전</button>
										  {{store.curpage}} page / {{store.totalpage}} pages
										  <button class="btn-xs btn-primary" @click="store.next()">다음</button>
										 </td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<!-- 예약일 정보 -->
						<div class="col-md-5">
							<div class="card h-100">
								<div class="card-header bg-info text-white text-center">예약일 정보</div>
								<div class="card-body text-center" v-show="store.isDate">
									<div id="calendar"></div>
								</div>
							</div>
						</div>
						<!-- 예약 정보 -->
						<div class="col-md-3">
							<div class="card h-100">
								<div class="card-header bg-success text-white text-center">예약 정보</div>
								<div class="card-body text-center">
									<img id="food_poster" src="" alt="poster" style="display: none">
									<table class="table table-borderless text-start">
										<tbody>
										<tr>
											<td colspan="2" class="text-center">
												<img :src="store.image" style="width: 200px;height: 200px">
											</td>
										</tr>
											<tr>
												<td class="text-muted">업체명</td>
												<td >{{store.title}}</td>
											</tr>
											<tr>
												<td class="text-muted">예약일</td>
												<td >{{store.day}}</td>
											</tr>
											<tr>
												<td class="text-muted">예약시간</td>
												<td >{{store.time}}</td>
											</tr>
											<tr>
												<td class="text-muted">예약인원</td>
												<td >{{store.inwon}}</td>
											</tr>
										</tbody>
									</table>
								  <button type="button" class="btn btn-primary w-100" v-if="store.isReserveBtn" @click="store.reserveInsert">예약하기</button>
								</div>
							</div>
						</div>
					</div>
					<div class="row g-4 mt-3">
						<div class="col-md-8">
							<div class="card">
								<div class="card-header bg-primary text-white text-center">시간 정보</div>
								<div class="card-body text-center">
									<div class="d-flex justify-content-center gap-2 flex-wrap" v-show="store.isTime">
										 <span class="btn btn-sm btn-info" v-for="(t,i) in store.time_list" :key="i" style="margin-left: 2px" @click="store.timeSelect(t)">
										  {{t}}
										 </span>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="card">
								<div class="card-header bg-info text-white text-center">인원 정보</div>
								<div class="card-body text-center">
									<div class="d-flex justify-content-center gap-2 flex-wrap" v-if="store.isInwon">
										<span class="btn btn-sm btn-success" v-for="(t,i) in store.inwon_list" :key="i" style="margin-left: 2px" @click="store.inwonSelect(t)">
									  	{{t}}
									 	</span>
									 </div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="/vue/reserve/reserveStore.js"></script>
	<script src="/vue/axios.js"></script>
	<script>
	 const {createPinia} = Pinia
	 const {createApp,ref,onMounted,watch} = Vue
	 const app=Vue.createApp({
		 setup(){
			 const store=useReserveStore()
			 // 한번만 호출 => 생성자의 역할
			 onMounted(()=>{
				 store.dataRecv()
			 })
			 
			 watch(()=>store.no,(newVal)=>{
					if(!newVal) return
					
				 const calendar=new FullCalendar.Calendar(
					document.getElementById('calendar'),{
						initialView:'dayGridMonth',
						height:500,
						validRange:{
							start:new Date().toISOString().split("T")[0]
						},
						dateClick(info){
							alert("선택일 : "+info.dateStr)
							store.dateSelect(info.dateStr)
							store.isTime=true
							store.timeListData()
						}
					}
				 )	 
				 calendar.render()
			 })
			 
			 return {
				 store
			 }
		 }
	 })
	 app.use(createPinia())
	 app.mount('#reserve_app')
	</script>
</body>
</html>