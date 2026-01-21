<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table">
		<tr>
			<td class="text-center"><h3>공지 수정</h3></td>
		</tr>
	</table>
	<form action="/admin/notice_update_ok" method="post">
		<table class="table">
			<tr>
				<th width="15%" class="table-success">공지 종류</th>
				<td width="85%"><select name="type" class="input-sm">
						<option ${vo.type=='일반공지'?'selectd':'' }>일반공지</option>
						<option ${vo.type=='이벤트공지'?'selectd':'' }>이벤트공지</option>
						<option ${vo.type=='여행공지'?'selectd':'' }>여행공지</option>
						<option ${vo.type=='날씨공지'?'selectd':'' }>날씨공지</option>
						<option ${vo.type=='추천공지'?'selectd':'' }>추천공지</option>
				</select></td>
			</tr>
			<tr>
				<th width="15%" class="table-success">제목</th>
				<td width="85%"><input type="text" name="subject" size="60" class="input-sm" value="${vo.subject }">
					<input type="hidden" name="no" value="${vo.no }">
				</td>
			</tr>
			<tr>
				<th width="15%" class="table-success">내용</th>
				<td width="85%"><textarea rows="10" cols="60" name="content">${vo.content }</textarea>
				</td>
			</tr>
			<tr>
				<td class="text-center" colspan="2">
					<button type="submit" class="btn btn-sm btn-info">수정</button>
					<button type="button" class="btn btn-sm btn-info" onclick="javascript:history.back()">취소</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>