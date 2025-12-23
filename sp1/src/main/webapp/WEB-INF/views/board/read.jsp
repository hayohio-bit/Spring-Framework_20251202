<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<link href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css" rel="stylesheet">

<style>
* {font-family: 'Pretendard Variable', Pretendard, -apple-system, sans-serif !important;}
a {font-family: 'Pretendard Variable', 'Noto Sans KR';}
h1,h2,h3,h4,h5,h6 {
    font-family: 'Pretendard Variable', Pretendard, sans-serif !important;
    font-weight: 700;
}
</style>

<div class="row justify-content-center">
  <div class="col-lg-12">
    <div class="card shadow mb-4">
      <div class="card-header py-3">
        <h6 class="m-0 fw-bold text-primary">Board Read</h6>
      </div>
      <div class="card-body">
      
        <div class="mb-3 input-group input-group-lg">
          <span class="input-group-text">Bno</span>
          <!-- 게시글 번호 표시 -->
          <input type="text" class="form-control" value="<c:out value='${board.bno}'/>" readonly>
        </div>

        <div class="mb-3 input-group input-group-lg">
          <span class="input-group-text">Title</span>
          <!-- 제목 표시 -->
          <input type="text" name="title" class="form-control" value="<c:out value='${board.title}'/>" readonly>
        </div>

        <div class="mb-3 input-group input-group-lg">
          <span class="input-group-text">Content</span>
          <!-- 내용 표시 -->
          <textarea class="form-control" name="content" rows="3" readonly><c:out value="${board.content}"/></textarea>
        </div>

        <div class="mb-3 input-group input-group-lg">
          <span class="input-group-text">Writer</span>
          <!-- 작성자 표시 -->
          <input type="text" name="writer" class="form-control" value="<c:out value='${board.writer}'/>" readonly>
        </div>

        <div class="mb-3 input-group input-group-lg">
          <span class="input-group-text">RegDate</span>
          <!-- 작성일 표시 -->
          <input type="text" name="regDate" class="form-control" value="<c:out value='${board.createdDate}'/>" readonly>
        </div>

        <div class="float-end">
          <!-- 목록 화면으로 돌아갈 URL 생성 (페이징/검색 정보 유지) -->
          <c:url var="readUrl" value="/board/list">
            <c:param name="page" value="${page}" />
            <c:param name="size" value="${size}" />
            <c:if test="${not empty types}">
              <c:param name="types" value="${types}" />
            </c:if>
            <c:if test="${not empty keyword}">
              <c:param name="keyword" value="${keyword}" />
            </c:if>
          </c:url>

          <!-- LIST 버튼: 위에서 만든 readUrl로 이동 -->
          <a href='${readUrl}'>
            <button type="button" class="btn btn-info btnList">LIST</button>
          </a>
           
          <!-- delFlag가 false일 때만 MODIFY 버튼 노출 -->
          <c:if test="${!board.delFlag}">
            <a href='/board/modify/${board.bno}' class="btn">
              <button type="button" class="btn btn-warning btnModify">MODIFY</button>
            </a>
          </c:if>
        </div>

      </div>
    </div>
  </div>
</div>

<!-- 댓글 작성 카드 -->
<div class="col-lg-12">
  <div class="card shadow mb-4">
    <div class='m-4'>
      <!-- 댓글 작성 폼 -->
      <form id="replyForm" class="mt-4">
        <!-- 게시글 번호 hidden 처리 -->
        <input type="hidden" name="bno" value="${board.bno}" />

        <div class="mb-3 input-group input-group-lg">
          <span class="input-group-text">Replyer</span>
          <!-- 댓글 작성자 입력 -->
          <input type="text" name="replyer" class="form-control" required />
        </div>

        <div class="mb-3 input-group">
          <span class="input-group-text">Reply Text</span>
          <!-- 댓글 내용 입력 -->
          <textarea name="replyText" class="form-control" rows="3" required></textarea>
        </div>

        <div class="text-end">
          <!-- 댓글 등록 버튼 -->
          <button type="submit" class="btn btn-primary addReplyBtn">
            Submit Reply
          </button>
        </div>
      </form>
      <!-- 댓글 작성 폼 끝 -->
    </div>
  </div>
</div>

<!-- 댓글 목록 + 페이징 카드 -->
<div class="col-lg-12">
  <div class="card shadow mb-4">
    <div class='m-4'>
      <!-- 댓글 목록 -->
      <ul class="list-group replyList">
        <!-- 안내용 기본 아이템 (JS가 새로운 li로 덮어쓸 예정) -->
        <li class="list-group-item">
          <div class="d-flex justify-content-between">
            <div>
              <strong>번호</strong> - 댓글 내용
            </div>
            <div class="text-muted small">
              작성일
            </div>
          </div>
          <div class="mt-1 text-secondary small">
            작성자
          </div>
        </li>
      </ul>
      <!-- 댓글 목록 끝 -->

      <!-- 페이징 (초기 더미; JS가 실제 페이지 버튼으로 교체 예정) -->
      <div aria-label="댓글 페이지 네비게이션" class="mt-4">
        <ul class="pagination justify-content-center">
          <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1">이전</a>
          </li>
          <li class="page-item active">
            <a class="page-link" href="#">1</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">2</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">3</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">다음</a>
          </li>
        </ul>
      </div>
      <!-- 페이징 끝 -->
      
      <!-- 댓글 모달창 -->
      <div class="modal fade" id="replyModal" tabindex="-1" 
      	aria-labelledby="replyModalLabel" aria-hidden="true">
      	<div class="modal-dialog">
      		<div class="modal-content">
      		
      		<div class="modal-header">
      			<h5 class="modal-title" id="replyModalLabel"> 댓글 수정 / 삭제</h5>
      			<button type="button" class="btn-close" data-bs-dismiss="modal"
      				aria-label="Close"></button>
      		</div>
      		
      		<div class="modal-body">
      		
      			<form id="replyModForm">
      				<input type="hidden" name="rno" value="33">
      				<div class="mb-3">
      					<label for="replyText" class="form-label">댓글 내용</label>
      					<input type="text" name="replyText" id="replyText" 
      						class="form-control" value="Reply Text"/>
      				</div>
      				
      			</form>
      			
      		</div>
      		
      		<div class="modal-footer">
      			<button type="button" class="btn btn-primary btnReplyMod">수정</button>
      			<button type="button" class="btn btn-danger btnReplyDel">삭제</button>
      			<button type="button" class="btn btn-secondary" 
      			data-bs-dismiss="modal">닫기</button>
      		</div>
      		
      		</div>
      	</div>
      </div>
      
    </div>
  </div>
</div>

<!-- axios 라이브러리 로드 -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<script>

// ------------ 공통 DOM 요소 / 상태 ------------

// 댓글 작성 폼 요소
const replyForm = document.querySelector("#replyForm")

// 댓글 등록 버튼에 클릭 이벤트 등록
document.querySelector(".addReplyBtn").addEventListener("click", e => {

  e.preventDefault()         // 폼 기본 submit(새로고침) 막기
  e.stopPropagation()        // 이벤트 전파 막기

  const formData = new FormData(replyForm)  // 폼 데이터를 FormData 객체로 만들기

  axios.post("/replies", formData).then(res => {   // /replies로 댓글 등록 요청

    console.log("------- server response -------")
    console.log(res)          // 서버 응답을 콘솔에 출력
    replyForm.reset()         // 폼 초기화

    getReplies(1,true)        // 댓글 목록을 다시 불러오기 (goLast=true)
  })
}, false)


// 현재 게시글 번호 (JSP에서 넘어온 값)
const bno = ${board.bno};

// 현재 댓글 페이지 번호
let currentPage = 1

// 한 페이지에 보여줄 댓글 개수
let currentSize = 10


// ------------ 댓글 목록을 서버에서 가져오는 함수 ------------

function getReplies(pageNum, goLast) {

  axios.get(`/replies/\${bno}/list`, {      // /replies/{bno}/list 호출
    params : {
      page : pageNum || currentPage,       // pageNum이 있으면 그걸, 없으면 현재 페이지
      size : currentSize                   // 페이지 크기
    }
  }).then(res => {

    const data = res.data                  // 응답 JSON 데이터
    console.log(data)                      // 디버깅용 출력

    const {totalCount, page, size} = data  // 전체 개수, 현재 페이지, 페이지 크기 분해

    // goLast가 true이고, 아직 마지막 페이지가 아닐 때
    if(goLast && (totalCount > (page * size ))) {

      // 마지막 페이지 번호 계산
      const lastPage = Math.ceil( totalCount / size )
 
      // 마지막 페이지로 다시 조회
      getReplies(lastPage)
 
    } else {
      // 현재 페이지/사이즈 상태 업데이트
      currentPage = page
      currentSize = size

      // 화면에 댓글/페이징 출력
      printReplies(data)
    }
  })
}

// 페이지 처음 로딩 시, 1페이지 기준으로 댓글을 불러오되 goLast=true로 호출
getReplies(1, true)

// 댓글 리스트가 들어갈 <ul> 요소
const replyList = document.querySelector(".replyList")


// ------------ 화면에 댓글 + 페이징을 그리는 함수 ------------

function printReplies(data) {

  // 서버에서 온 데이터 구조 분해
  const {replyDTOList, page, size, prev, next, start, end, pageNums} = data

  let liStr = ''    // 댓글 li들을 이어붙일 문자열

  // 댓글 하나씩 li로 만들기
  for(const replyDTO of replyDTOList) {

	  // 삭제된 댓글은 li 자체를 만들지 않음
	  //if(replyDTO.delflag === true || replyDTO.delFlag === true){
		//continue
	 // }

    liStr += `<li class="list-group-item" data-rno="\${replyDTO.rno}">
<div class="d-flex justify-content-between">
<div>
<strong>\${replyDTO.rno}</strong> - \${replyDTO.replyText}
</div>
<div class="text-muted small">\${replyDTO.replyDate}
</div>
</div>
<div class="mt-1 text-secondary small">\${replyDTO.replyer}
</div>
</li>`
  } //end for

  // 완성된 li HTML을 댓글 목록에 삽입
  replyList.innerHTML = liStr

  // -------- 댓글 페이징 처리 --------
  let pagingStr = ''

  // 이전 페이지 블록이 있으면 "이전" 버튼 추가
  if(prev) {
    pagingStr += `<li class="page-item">
<a class="page-link" href="\${start -1}" tabindex="-1">
이전</a>
</li>`
  }

  // pageNums에 있는 각 페이지 번호에 대해 li 생성
  for(let i of pageNums) {
    pagingStr += `<li class="page-item \${i === page ? 'active':''}">
<a class="page-link" href="\${i}">\${i}</a>
</li>`
  }

  // 다음 페이지 블록이 있으면 "다음" 버튼 추가
  if(next) {
    pagingStr += `<li class="page-item">
<a class="page-link" href="\${end + 1}">다음</a>
</li>`
  }

  // 만들어진 페이징 HTML을 .pagination에 삽입
  document.querySelector(".pagination").innerHTML = pagingStr

  // 페이징 영역에 클릭 이벤트 등록 (이벤트 위임)
  document.querySelector(".pagination").addEventListener("click", e => {

    e.stopPropagation()    // 전파 막기
    e.preventDefault()     // a 태그 기본 이동 막기

    const target = e.target

    const href = target.getAttribute("href")   // 클릭한 링크의 href (페이지 번호)

    if(!href){
      return          // href 없으면 아무 작업 안 함
    }

    console.log(href) // 어떤 페이지를 클릭했는지 로그

    getReplies(href)  // 해당 페이지 번호로 댓글 다시 조회

  }, false)
}



// -------- 댓글 클릭 이벤트 --------

const replyModal = new bootstrap.Modal(document.querySelector("#replyModal"))

const replyModForm = document.querySelector("#replyModForm")

replyList.addEventListener("click", e => {

	//가장 가까운 상위 li 요소 찾음
	const targetLi = e.target.closest("li")
	
	const rno = targetLi.getAttribute("data-rno")
	
	if(!rno){
		return
	}
	
	axios.get(`/replies/\${rno}`).then(res => {

		const targetReply = res.data

		console.log(targetReply)
		
		if(targetReply.delflag === false){

			replyModForm.querySelector("input[name = 'rno']").value = targetReply.rno
			
			replyModForm.querySelector("input[name = 'replyText']").value = targetReply.replyText
			
			replyModal.show()

		}else {
			alert("삭제된 댓글은 조회할 수 없습니다. ")
		}
		
	})
	
	
}, false)


// -------- 댓글 삭제 --------

document.querySelector(".btnReplyDel").addEventListener("click", e => {

	e.preventDefault()
	e.stopPropagation()
	
	const formData = new FormData(replyModForm)
	
	const rno = formData.get("rno")

	console.log("rno : " + rno)
	
	axios.delete(`/replies/\${rno}`).then(res => {
		const data = res.data
		console.log(data)
		
		replyModal.hide()
		
		// 1) 현재 페이지 기준으로 다시 목록 조회
	    axios.get(`/replies/${bno}/list`, {
	      params: {
	        page: currentPage,
	        size: currentSize
	      }
	    }).then(listRes => {
	
	      const data = listRes.data
	      const { replyDTOList, totalCount } = data

      // 2) 현재 페이지에 댓글이 하나도 없고, 전체 댓글이 0이 아니며
      //    현재 페이지가 1보다 클 때 → 이전 페이지로 이동
	      if (replyDTOList.length === 0 && totalCount > 0 && currentPage > 1) {
	        getReplies(currentPage - 1)
	      } else {
	        getReplies(currentPage)
	      }
	    })
	})
}, false)


// -------- 댓글 수정 --------

document.querySelector(".btnReplyMod").addEventListener("click", e => {

	e.preventDefault()
	e.stopPropagation()
	
	const formData = new FormData(replyModForm)
	
	const rno = formData.get("rno")
	
	console.log("rno : " + rno)
	
	axios.put(`/replies/\${rno}`, formData).then(res => {
		const data = res.data
		console.log(data)
		
		replyModal.hide()
		
		getReplies(currentPage)
		
})
	
}, false)



</script>

<%@ include file="/WEB-INF/views/includes/footer.jsp" %>
