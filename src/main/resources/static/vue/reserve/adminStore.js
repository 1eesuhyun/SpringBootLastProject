const {defineStore} = Pinia
const useAdminStore=defineStore('admin',{
	state:()=>({
		reserve_list:[],
	}),
	actions:{
		async dataRecv(){
			const res=await api.get('/admin/reserve_list/')
				console.log(res.data)
				this.reserve_list=res.data
		},
		async reserveOk(no,id){
			const res=await api.get('/admin/reserve_ok_vue/',{
				params:{
					no:no, 
					id:id
				}
			})
				console.log(res.data)
				this.reserve_list=res.data
		},
		async reserveDelete(no,id){
			const res=await api.get('/admin/reserve_delete_vue/',{
				params:{
					no:no,
					id:id
				}
			})
				console.log(res.data)
				this.reserve_list=res.data
		}
	}
})