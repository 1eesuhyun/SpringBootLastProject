<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table">
		<tr>
			<td class="text-center"><h3>공지사항 상세보기</h3></td>
		</tr>
	</table>
	<table class="table">
		<tr>
		  <th width="20%" class="text-center">번호</th>
		  <td width="30%" class="text-center">${vo.no }</td>
		  <th width="20%" class="text-center">작성일</th>
		  <td width="30%" class="text-center">${vo.regdate }</td>
		</tr>
		<tr>
		  <th width="20%" class="text-center">이름</th>
		  <td width="30%" class="text-center">${vo.name }</td>
		  <th width="20%" class="text-center">조회수</th>
		  <td width="30%" class="text-center">${vo.hit }</td>
		</tr>
		<tr>
		  <th width="20%" class="text-center">제목</th>
		  <td colspan="3">${vo.subject}</td>
		</tr>
		<tr>
		 <td colspan="4" class="text-left" valign="top" height="200">
		  <pre style="white-space: pre-wrap;background-color: white;border: none;">${vo.content}</pre>
		</tr>
			<tr>
				<td class="text-right" colspan="4"><a href="/admin/notice_update?no=${vo.no }" class="btn btn-sm btn-primary">수정</a>
				 <a class="btn btn-sm btn-danger" href="/admin/notice_delete?no=${vo.no }">삭제</a>
				 <a href="/admin/notice_list" class="btn btn-sm btn-info">목록</a></td>
			</tr>
		<c:if test="${vo.filecount>0 }">
			<c:forTokens items="${vo.filename }" delims="," var="img">
			 <tr>
			  <td class="text-center" colspan="4">
			   <img src="/upload/${img }" style="width: 250px;height: 250px">
			  </td>
			 </tr>
			</c:forTokens>
		</c:if>
	</table>
</body>
</html>