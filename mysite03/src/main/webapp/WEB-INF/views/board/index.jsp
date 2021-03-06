<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<c:set var="count" value="${fn:length(list) }"/>
				<table class="tbl-ex">
				<tr>
						<th align=center>번호</th>
						<th align=center>제목${page} ${total} ${authUser.name }</th>
						<th align=center>글쓴이</th>
						<th align=center>조회수</th>
						<th align=center>작성일</th>
						<th>&nbsp;</th>
				</tr>
				<c:forEach items="${list}" var="vo" varStatus="status">
						<c:choose>
							<c:when test="${vo.depth==0}">
								<tr>		
									<td align=center>[${total-(page-1)*10-status.index}]</td>
									<c:if test="${vo.title eq vo.del}">
										<td>삭제된 글</td>
									</c:if>
									<c:if test="${vo.title ne vo.del}">
										<td ><a href="${pageContext.request.contextPath }/board/view?no=${vo.no}&page=${page}" style="test-align:left; padding-left:0px">${vo.title}</a></td>
									</c:if>
									
									<td align=center>${vo.writer}${del}</td>
									<td align=center>${vo.depth*10}</td>
									<td align=center>${vo.reg_date }</td>
									<c:if test="${vo.writer eq authUser.name}">
										<td><a href="${pageContext.request.contextPath }/board?a=deleteform&no=${vo.no}" class="del">삭제</a></td>
									</c:if>
									<c:if test="${vo.writer ne authUser.name}">
										<td>&nbsp;</td>
									</c:if>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>		
									<td align=center>[${total-(status.index+(page-1)*10)}]</td>
									<c:if test="${vo.title eq vo.del}">
										<td style="test-align:left;padding-left : ${vo.depth*20}px" ><img src="${pageContext.request.contextPath }/assets/images/reply.png" />삭제된 글</td>
									</c:if>
									<c:if test="${vo.title ne vo.del}">
										<td align=left><a href="${pageContext.request.contextPath }/board/view?no=${vo.no}&page=${page}" style="test-align:left;padding-left : ${vo.depth*20}px"><img src="${pageContext.request.contextPath }/assets/images/reply.png" />${vo.title}</a></td>
									</c:if>
									
									<td align=center>${vo.writer}</td>
									<td align=center>${vo.depth*10 }</td>
									<td align=center>${vo.date }</td>
									<c:if test="${vo.writer eq authUser.name}">
										<c:if test="${vo.title ne vo.del}">
											<td><a href="${pageContext.request.contextPath }/board?a=deleteform&no=${vo.no}" class="del">삭제</a></td>
										</c:if>
									</c:if>
									<c:if test="${vo.writer ne authUser.name}">
										<td>&nbsp;</td>
									</c:if>
								</tr>
							</c:otherwise>
						</c:choose>
					
				</c:forEach>
				</table>
				
			
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test="${1 ne index}">
							<li><a href="${pageContext.request.contextPath }/board?page=${page}&move=d">◀</a></li>
						</c:if>
					
						<c:forEach var="i" begin="${5*(index-1)+1}" end="${5*(index-1)+5}">
							<c:if test="${i<=totalpage}">
								<c:if test="${i eq page}">
									<li class="selected"><a href="${pageContext.request.contextPath }/board?page=${i}">${i}</a></li>
								</c:if>
								<c:if test="${i ne page}">
									<li ><a href="${pageContext.request.contextPath }/board?page=${i}">${i}</a></li>
								</c:if>
							</c:if>
						</c:forEach>
						<c:if test="${index ne lastidx}">
							<li><a href="${pageContext.request.contextPath }/board?page=${page}&move=u">▶</a></li>
						</c:if>
					</ul>


					
				</div>					
				<!-- pager 추가 -->
				<div class="bottom">
					
					<a href="${pageContext.request.contextPath }/board/write" id="new-book">
					글쓰기
					</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>