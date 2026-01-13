<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/chat.css"> 
</head>
<body>
<div class="breadcumb-area" style="background-image: url(/img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>챗봇</h2>
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
                            
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80">
        <div class="container">
            <div class="row" style="width: 800px;margin: 0px auto">
		<div class="chatcontainer">
		    <div class="header">
		        <h1>ChatBot</h1>
		    </div>
		    <div class="chat-box" id="chatBox">
		        <div class="message assistant">
		            <div class="message-content">
		                안녕하세요! 무엇을 도와드릴까요?
		            </div>
		        </div>
		    </div>
		    <div class="input-area">
		        <div class="input-group">
		            <input type="text" id="messageInput" placeholder="메시지를 입력하세요..." />
		            <button id="sendButton">전송</button>
		        </div>
		    </div>
		</div>
   </div>
   </div>
   </section>
</body>
</html>