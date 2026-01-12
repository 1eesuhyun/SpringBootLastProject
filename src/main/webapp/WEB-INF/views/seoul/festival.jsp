<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="breadcumb-area" style="background-image: url(/img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>${vo.title }</h2>
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
    
    <section class="archive-area section_padding_80">
        <div class="container" >
            <div class="row">
              <table class="table">
               <tbody>
                <tr>
                 <td width="30%" class="text-center" rowspan="8">
                  <img src="${vo.image1 }" style="width: 400px;height: 300px;">
                 </td>
                 <td colspan="2"><h3>${vo.title }</h3></td>
                </tr>
                <tr>
                 <td width="15%" class="text-center">주소</td>
                 <td width="55%">${vo.address }</td>
                </tr>
                <tr>
                 <td width="15%" class="text-center">입장료</td>
                 <td width="55%">${vo.fvo.usetime }</td>
                </tr>
                <tr>
                 <td width="15%" class="text-center">소요시간</td>
                 <td width="55%">${vo.fvo.spendtime }</td>
                </tr>
                <tr>
                 <td width="15%" class="text-center">이벤트 기간</td>
                 <td width="55%">${vo.fvo.eventstartdate }~${vo.fvo.eventenddate }</td>
                </tr>
                <tr>
                 <td width="15%" class="text-center">상영시간</td>
                 <td width="55%">${vo.fvo.playtime}</td>
                </tr>
                <tr>
                 <td width="15%" class="text-center">연령제한</td>
                 <td width="55%">${vo.fvo.agelimit }</td>
                </tr>
               </tbody>
              </table>
              <table class="table">
               <tbody>
                <tr>
                 <td>${vo.fvo.msg }</td>
                </tr>
                <tr>
                 <td class="text-right">
                  <a href="javascript:history.back()" class="btn btn-sm btn-primary">목록</a>
                 </td>
                </tr>
               </tbody>
              </table>
            </div>
        </div>
    </section>        
</body>
</html>