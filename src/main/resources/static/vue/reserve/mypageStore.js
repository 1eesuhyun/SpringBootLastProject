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
			document.getElementById("toastMsg").innerText=message
			const toastEl=document.getElementById("reserveToast")
			const toast=new bootstrap.Toast(toastEl,{
				delay:5000
			})
			toast.show()
		}
	}
})