<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/toast.css">
</head>
<body>
	<div class="toast-container position-fixed bottom-0 end-0 p-3">
		<div id="reserveToast" class="toast">
			<div class="toast-header bg-success text-white">
				<strong class="me-auto">알림</strong>
				<button type="button" class="btn-close" data-bs-dismiss="toast"></button>
			</div>
			<div class="toast-body" id="toastMsg"></div>
		</div>
	</div>
</body>
</html>