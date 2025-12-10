<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file= "/WEB-INF/views/includes/header.jsp" %>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&family=Gothic+A1&family=Gugi&family=Hi+Melody&family=Jua&family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&family=Nanum+Brush+Script&family=Nanum+Gothic+Coding&family=Nanum+Pen+Script&family=Orbit&family=Poor+Story&family=Ubuntu:ital,wght@0,300;0,400;0,500;0,700;1,300;1,400;1,500;1,700&family=Yeon+Sung&display=swap" rel="stylesheet">

<div class="row justify-content-center">
   <div class="col-lg-12">
      <div class="card shadow mb-4">
         <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Board List</h6>
         </div>
         
         <div class="card-body">
            <table class="table table-boardered" id="dataTable">
               <thead>
                  <th>Bno</th>
                  <th>Title</th>
                  <th>Writer</th>
                  <th>RegDate</th>
               </thead>
               <tbody class="tbody">
                  <c:forEach var="board" items="${dto.boardDTOList}">
                     <tr data-bno=${board.bno}>
                        <td><a href='/board/read/${board.bno }'>
                        	<c:out value="${board.bno}" /></a></td>
                        <td><a href='/board/read/${board.bno }'>
                        	<c:out value="${board.title}" /></a></td>
                        
                        <td><c:out value="${board.writer}" /></td>
                        <td><c:out value="${board.createdDate}" /></td>
                     </tr>
                  </c:forEach>
                  
               </tbody>
            </table>
            
            <!--  화면에 페이지 번호 출력 -->
            <div class="d-flex justify-content-center">
            	<ul class="pagination">
            	
            		<c:if test="${dto.prev}">
				    <li class="page-item">
				    	<a class="page-link" href="${dto.start -1 }" tabindex="-1">Previous</a></li>
				    </c:if>
				     
				    <c:forEach var="num" items="${dto.pageNums}">
				    <li class="page-item ${dto.page == num ? 'active':'' } ">
				    	<a class="page-link" href="${num}"> ${num} </a></li>
				    </c:forEach>

					<c:if test="${dto.next}">
				    <li class="page-item">
				      <a class="page-link" href="${dto.end + 1 }">Next</a></li>
				    </c:if>
				</ul>
			</div>
            
         </div>   
      </div>
   </div>
</div>

<div class="modal" tabindex="-1" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p><span id="modalResult"></span>번 글이 등록되었습니다.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save changes</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<!-- 
   defer 속성 설명
     - HTML 문서를 먼저 모두 파싱한 뒤에 app.js를 실행하도록 지연(load defer)시키는 옵션
     - 스크립트 다운로드는 병렬로 진행되지만, 실행은 DOM 생성이 끝난 후 처리됨
     - DOM 요소를 찾는 코드(document.querySelector 등)가 안정적으로 실행됨
     - 화면 렌더링을 차단(block)하지 않아 성능이 좋아짐
     - 여러 개의 defer 스크립트는 HTML에 작성된 순서대로 실행됨
-->
<script type="text/javascript" defer="defer">

	// 게시물 등록시 모달 표시
	const result = '${result}'
	
	const myModal = new bootstrap.Modal(document.getElementById('myModal'))
		
	if(result){
		document.getElementById('modalResult').innerText = result
		myModal.show()
	}
	
	// 페이징 이벤트 처리
	const pagingDiv = document.querySelector(".pagination")
	
	pagingDiv.addEventListener("click", (e) => {
		e.preventDefault()
		e.stopPropagation()
		
		const target = e.target
		
		// console.log(target)
		
		const targetPage = target.getAttribute("href")
		
		const size = ${dto.size}|| 10 // BoardListPagingDT의 size
		
		const params = new URLSearchParams({
			page: targetPage,
			size: size
		});
		
		self.location=`/board/list?\${params.toString()}`
		// JavaScript 백틱(`) 이스케이프 => \ 사용
		// 백틱(`) 안에서 \${} 하면 ${params.toString()} 문자열 그대로 나옴
		
	}, false)
	
</script>


<%@ include file= "/WEB-INF/views/includes/footer.jsp" %>