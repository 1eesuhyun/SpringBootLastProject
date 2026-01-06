const {createApp,onMounted,ref}=Vue
 const {createPinia}=Pinia
 const seoulApp=createApp({
	 setup(){
		 // store읽기
		 const store=useSeoulStore()
		 const addressRef=ref(null)
		 
		 // ref
		 // onMounted() => 시작하자마자 딱 한번 수행
		 onMounted(()=>{
			 store.seoulFindData()
		 })
		 //return
		 return {
			 store,addressRef
		 }
	 }
 })
 seoulApp.use(createPinia())
 seoulApp.mount('#seoul_find')