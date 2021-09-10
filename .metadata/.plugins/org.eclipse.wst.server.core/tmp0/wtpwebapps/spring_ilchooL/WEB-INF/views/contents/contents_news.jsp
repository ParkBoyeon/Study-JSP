<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath }/assets/js/jquery.min.js"></script>
<!-- Bootstrap CSS -->
<link rel="shortcut icon" href="${pageContext.request.contextPath }/assets/ico/favicon.png" />
<link rel="apple-touch-icon-precomposed"
	href="${pageContext.request.contextPath }/assets/ico/apple-touch-icon-144-precomposed.png" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/assets/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/assets/css/bootstrap-theme.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/plugins/sweetalert/sweetalert2.min.css" />
<!-- validate 플러그인 참조 -->
<script src="${pageContext.request.contextPath }/assets/plugins/validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/plugins/validate/additional-methods.min.js"></script>
<!-- sweetalert 플러그인 참조 -->
<script src="${pageContext.request.contextPath }/assets/plugins/sweetalert/sweetalert2.min.js"></script>


<script src="${pageContext.request.contextPath }/assets/js/bootstrap.min.js" /></script>
<script src="${pageContext.request.contextPath }/assets/js/bootstrap.js" /></script>
</head>
<body>
	
	<div class="container">
		<div class="row">
			<div class="col-md-1 col-sm-12">
				<input type="text" id="datepicker" placeholder="Date"
					class="form-control">
			</div>

			<div class="col-md-11 col-sm-12 news_graph">
				<div id="graph">
					<img src="../assets/img/contents_news_graph.png" />
				</div>
			</div>
			
			<div class="col-xs-12 news_ex">
				<div id="line">
					<div class="circle_grey"></div>
					<div class="circle"></div>
					<p>키워드를 선택하시면 시간대별 주요 뉴스를 확인할 수 있습니다.</p>
				</div>
				<div>
					<h3>${keyword}</h3>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="col-lg-4 col-md-6 col-sm-12 news_box">
						<a data-toggle="modal" href="#news_modal1"> <span
							class="news_contents_box"> <span id="text"> ${item.title1}
							</span> <span id="text_date"> ${item.pubDate1}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-sm-12 news_box">
						<a data-toggle="modal" href="#news_modal2"> <span
							class="news_contents_box"> <span id="text"> ${title2}
							</span> <span id="text_date"> ${pubDate2}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-sm-12 news_box">
						<a data-toggle="modal" href="#news_modal3"> <span
							class="news_contents_box"> <span id="text"> ${title3}
							</span> <span id="text_date"> ${pubDate3}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-sm-12 news_box">
						<a data-toggle="modal" href="#news_modal4"> <span
							class="news_contents_box"> <span id="text"> ${title4}
							</span> <span id="text_date"> ${pubDate4}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-sm-12 news_box">
						<a data-toggle="modal" href="#news_modal5"> <span
							class="news_contents_box"> <span id="text"> ${title5}
							</span> <span id="text_date"> ${pubDate5}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-sm-12 news_box">
						<a data-toggle="modal" href="#news_modal6"> <span
							class="news_contents_box"> <span id="text"> ${title6}
							</span> <span id="text_date"> ${pubDate6}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-sm-12 news_box">
						<a data-toggle="modal" href="#news_modal7"> <span
							class="news_contents_box"> <span id="text"> ${title7}
							</span> <span id="text_date"> ${pubDate7}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-sm-12 news_box">
						<a data-toggle="modal" href="#news_modal8"> <span
							class="news_contents_box"> <span id="text"> ${title8}
							</span> <span id="text_date"> ${pubDate8}
							</span>
						</span>
						</a>
					</div>
				
					<div class="col-lg-4 col-md-6 col-sm-12 news_box">
						<a data-toggle="modal" href="#news_modal9"> <span
							class="news_contents_box"> <span id="text"> ${title9}
							</span> <span id="text_date"> ${pubDate9}
							</span>
						</span>
						</a>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- .modal -->
	<div id="news_modal1" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str1}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
				
					${element1}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal2" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str2}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
					
					${element2}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal3" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str3}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
					
					${element3}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal4" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str4}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
					
					${element4}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal5" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str5}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
				
					${element5}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal6" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str6}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
				
					${element6}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal7" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str7}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
				
					${element7}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal8" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str8}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
				
					${element8}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- .modal -->
	<div id="news_modal9" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="news_modalLabel" aria-hidden="true">
		<!-- .modal-dialog -->
		<div class="modal-dialog">
			<!-- .modal-content -->
			<div class="modal-content" id="modal-content">
				<!-- 제목 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h3 class="modal-title" id="myModalLabel">${str9}</h3>
				</div>
				<!-- 내용 -->
				<div class="modal-body">
				
					${element9}
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>
</html>