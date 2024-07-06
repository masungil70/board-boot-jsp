<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
		<div id="wrapper">
			<div class="container">
		    	<form action="/board/list.do" id="frm" method="get">
				    <div class="col-md-12 mt-4">
				    	<button type="button" class="btn btn-danger" onclick="fnDelete()">게시물삭제</button>
				        <button type="button" class="btn btn-primary" onclick="javascript:location.href='/board/writeForm.do'">게시물등록</button>
				        <div class="float-right">
				        	<button type="button" class="btn btn-primary" onclick="javascript:location.href='/logout.do'">로그아웃</button>	
				        </div>
				        <table class="table table-striped table-horizontal table-bordered mt-3">
				            <thead class="thead-strong">
				                <tr>
				                    <th width="5%"><input type="checkbox" id="chkAll"></th>
				                    <th width="10%">순번</th>
				                    <th width="">제목</th>
				                    <th width="20%">작성자</th>
				                    <th width="10%">조회수</th>
				                    <th width="20%">등록일시</th>
				                </tr>
				            </thead>
				            <tbody id="tbody">
				            	<c:forEach var="item" items="${list}" varStatus="status">
				            	<tr >
									<td>
										<input type="checkbox" name="deleteId" value="${item.id}">
									</td>
									<td>
										<span>${status.count}</span>
									</td>
									<td>
										<a href="<c:url value='/board/view.do?id=${item.id}'/>"><span>${item.title}</span></a>
									</td>
									<td>
										<span>${item.register_id}</span>
									</td>
									<td>
										<span>${item.read_cnt}</span>
									</td>
									<td>
										<span>${item.register_time}</span>
									</td>
								<tr>
				            	</c:forEach>
				            </tbody>
				        </table>
				    </div>
			    </form>
		    </div>
	    </div>
	    <script th:inline="javascript">
	    	// header checkbox event
	    	$("#chkAll").click(function() {
	    		if (this.checked) {
	    			$("input[name='deleteId']").prop("checked", true);
	    		} else {
	    			$("input[name='deleteId']").prop("checked", false);
	    		}
	    	});
	    	
	    	// body checkbox event
	    	$("input[name='deleteId']").click(function() {
	    		let delInpLen = $("input[name='deleteId']").length;
	    		let delInpChkLen = $("input[name='deleteId']:checked").length;
	    		
	    		if (delInpLen == delInpChkLen) {
	    			$("#chkAll").prop("checked", true);
	    		} else {
	    			$("#chkAll").prop("checked", false);
	    		}
	    	});
	    
			function fnDelete() {
				let delInpChkLen = $("input[name='deleteId']:checked").length;
				
				if (delInpChkLen > 0) {
					if (confirm("삭제하시겠습니까?")) {
						let frm = $("#frm");
						frm.attr("action","/board/deletes.do");
						frm.attr("method","post");
						frm.submit();
					}
				} else {
					alert("삭제할 항목을 선택해주세요");
				}
			}
		</script>
</body>
</html>