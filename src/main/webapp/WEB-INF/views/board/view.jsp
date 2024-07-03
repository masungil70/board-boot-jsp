<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html >
<html>
<head>
	<title>Board List</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<!-- 게시판 css 추가 -->
    <link rel="stylesheet" href="<c:url value='/resources/css/board.css'/>">
	<!--부트스트랩 css 추가 -->
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
    <!--jQuery 추가 -->
    <script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
    <!--부트스트랩 script 추가 -->
  	<script src="<c:url value='/resources/js/bootstrap.min.js'/>/"></script>
  	<!-- jQuery Validation Plugin 추가 -->
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
</head>
<body>
   	<!-- layout Content -->
	<div class="container">
		<h1>게시물 상세보기.</h1>
		<form id="frm" action="/board/updateForm.do" method="post" >
			<input type="hidden" name="id" value="${boardResponse.id}">
			<div class="mb-3">
			    <label class="form-label font-weight-bold"><span class="text-danger">* </span>제목</label>
			    <input type="text" class="form-control" name="title" value="${boardResponse.title}" required readonly="readonly">
			</div>
			<div class="mb-3">
		    	<label class="form-label font-weight-bold"><span class="text-danger">* </span>내용</label>
		    	<textarea class="form-control" rows="5" name="content" required readonly="readonly">${boardResponse.content}</textarea>
			</div>
			<div class="mb-3">
			    <label class="form-label font-weight-bold"><span class="text-danger">* </span>작성자</label>
			    <input type="text" class="form-control" name="register_id" value="${boardResponse.register_id}" readonly="readonly">
			</div>
			<div class="mb-3">
			    <label class="form-label font-weight-bold"><span class="text-danger">* </span>조회수</label>
			    <input type="text" class="form-control" name="read_cnt" value="${boardResponse.read_cnt}" readonly="readonly">
			</div>
			<div class="clearfix mt-5">
				<div class="">
					<button type="button" class="btn btn-success" onclick="javascript:location.href='/board/list.do'">이전</button>
					<button type="button" class="btn btn-primary" onclick="fnSubmit();">수정</button>
					<button type="button" class="btn btn-danger" onclick="fnViewDelete()">삭제</button>
				</div>
			</div>
		</form>
	</div>
    <script th:inline="javascript">
	    let frm = $("#frm");
		
		function fnViewDelete() {
			if (confirm("삭제하시겠습니까?")) {
				frm.attr("action","/board/delete.do");
				frm.submit();
			}
		}
		
		function fnSubmit() {
			if (confirm("수정하시겠습니까?")) {
				frm.submit();
			}
	    }
	    
	    $(function() {
	    	frm.validate({
	            messages: {
	            	title: {
	                    required : "제목은 필수 입력입니다."
	                }
	            },
	            submitHandler: function (form) {
            		form.submit();
	            }
	        });
	    });
	</script>
</body>	
</html>