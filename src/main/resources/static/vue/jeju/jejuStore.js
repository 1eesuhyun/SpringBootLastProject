const {defineStore} = Pinia
// store => 저장 공간(출력에 필요한 데이터를 모아서 관리)
const initalStore=()=>({
	list:[],
	curpage:1,
	totalpage:0,
	startPage:0,
	endPage:0,
	fd:'공원',
	selected:'12'
})
const useJejuStore=defineStore('jeju_find',{
	// 모든 컴포넌트에서 사용이 가능하게 공유 변수 설정
	state:initalStore,
	getters:{
		range:(state)=>{
			const arr=[]
			for(let i=state.startPage;i<=state.endPage;i++)
			{
				arr.push(i) // 맨 뒤에 값을 설정 
			}
			return arr
		}
	},
	actions:{
		// 서버로부터 요청 값 받기
		async jejuFindData(){
			const res=await api.get('/jeju/find_vue/',{
				params:{
					page:this.curpage,
					selected:this.selected,
					fd:this.fd
				}
			})
			this.setPageData(res.data)
		},
		setPageData(data){
			this.list=data.list
			this.curpage=data.curpage
			this.totalpage=data.totalpage
			this.endPage=data.endPage
			this.startPage=data.startPage
			this.address=data.address
			this.fd=data.fd
			this.selected=data.selected
		},
		pageChange(page){
			this.curpage=page
			this.jejuFindData()
		},
		find(findRef){
			if(this.fd==='')
			{
				findRef?.focus()
				return
			}
			this.curpage=1
			this.jejuFindData()
		}
	}
})