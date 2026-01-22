const {defineStore} = Pinia
const useMypageStore=defineStore('mypage',{
	state:()=>({
		reserve_list:[],
		reserve_detail:{},
		isShow:false,
		stomp:null
	}),
	actions:{
		connect(id){
			const sock=new SockJS("/ws")
			this.stomp=Stomp.over(sock)
			this.stomp.connect({},()=>{
				this.stomp.subscribe('/sub/notice/'+id,(msg)=>{
					this.showToast(msg.body)
					this.dataRecv()
				})
			})
		},
		async dataRecv(){
			const res=await api.get('/mypage/reserve_list/')
			console.log(res.data)
			this.reserve_list=res.data
		},
		async reserveRequest(no){
			const res=await api.get('/mypage/reserve_cancel_vue/',{
				params:{
					no:no
				}
			})
			console.log(res.data)
			this.reserve_list=res.data
		},
		async reserveDetail(no){
			const res=await api.get('/mypage/reserve_detail_vue/',{
				params:{
					no:no	
				}
			})
			this.reserve_detail=res.data
			this.isShow=true
		},
		showToast(message){
		  const toast = document.getElementById("reserveToast")
		  const toastMsg = document.getElementById("toastMsg")

		  toastMsg.innerText = message;
		  toast.classList.add("show");

		  // 3초 후 자동 닫힘
		  
		  setTimeout(() => {
		     hideToast()
		  }, 5000);
		}
	}
})
function hideToast() {
	const toast = document.getElementById("reserveToast");
	toast.classList.remove("show");
}