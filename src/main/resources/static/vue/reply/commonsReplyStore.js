const {defineStore} = Pinia

const useCommonsReplyStore=defineStore('commons_reply',{
	state:()=>({
		list:[],
		curpage:1,
		startPage:0,
		endPage:0,
		totalpage:0,
		cno:0,
		sessionId:'',
		msg:'',
		count:0,
		upReply:null,
		updateMsg:{},
		reReplyNo:null,
		replyMsg:{}
		// update
	}),
	getters:{
		// 페이지 출력
		range:(state)=>{
			const arr=[]
			for(let i=state.startPage;i<=state.endPage;i++)
			{
				arr.push(i)
			}
			return arr
		}
	},
	actions:{
		connect(id){
			const sock=new SockJS('/ws')
			this.stomp=Stomp.over(sock)
			// 구독 => 데이터를 받는 경우 => 어디에 출력
			/*
				this.stomp.connect({},()=>{},()=>{})
				                   -- ------ ------ -> error
								   |     | connectionCallback
								 headers
			*/
			
			this.stomp.connect({},()=>{
				this.stomp.subscribe('/sub/notice/'+id,msg=>{
					this.showToast(msg.body)
					this.dataRecv()
				})
			})
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
		},
		setPageData(data){
			this.list=data.list
			this.curpage=data.curpage
			this.startPage=data.startPage
			this.endPage=data.endPage
			this.totalpage=data.totalpage
			this.cno=data.cno
			this.count=data.count
		},
		pageChange(page){
			this.curpage=page
			this.commonsListData(this.cno)
		},
		async commonsListData(cno){ 
			this.cno=cno
			const res=await api.get('/commons/list_vue/',{
				 params:{
					page:this.curpage,
					cno:cno
				 }
			})
			this.setPageData(res.data)
		},
		async commonsInsert(msgRef){
			if(this.msg==='')
			{
				msgRef?.focus()
				return
			}
			const res=await api.post('/commons/insert_vue/',{
				cno:this.cno,
				msg:this.msg
			})
			this.setPageData(res.data)
			this.msg=''
		},
		// 수정
		// 삭제
		async commonsDelete(no){
			const res=await api.delete('/commons/delete_vue/',{
				params:{
					no:no,
					cno:this.cno,
					page:this.curpage
				}
			})
			this.setPageData(res.data)
		},
		toggleUpdate(no,msg){
			this.upReplyNo=this.upReplyNo===no?null:no
			this.updateMsg[no]=msg
		},
		async replyUpdate(no){
			const res=await api.post('/commons/update_vue/',{
					no:no,
					cno:this.cno,
					page:this.curpage,
					msg:this.updateMsg[no]
			})
			this.setPageData(res.data)
			this.upReplyNo=null
		},
		// reply
		toggleReply(no){
			this.reReplyNo=this.reReplyNo===no?null:no
			this.upReplyNo=null
		},
		async replyReply(no){
			const res=await api.post('/commons/reply_reply_insert_vue/',{
					no:no,
					cno:this.cno,
					page:this.curpage,
					msg:this.replyMsg[no]
			})
			this.setPageData(res.data)
			this.reReplyNo=null
		}
	}
})