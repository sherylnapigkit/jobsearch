<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="include.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta charset="utf-8">
    <script src="${contextPath}/js/search.js"></script>
    <link href="${contextPath}/css/jobs.css" rel="stylesheet" type="text/css">
	<title>Job Results</title>
</head>
<body class="container" style="width: 60%">
            
            <div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 colxs-12">
					<div class="wrapper">
						<c:forEach items="${jobSearchResult.jobs}" var="job" varStatus="ctr">
						<div class="panel panel-primary">
						  <div class="panel-heading">
						    <h3 class="panel-title">${job.companyLongName} : ${job.name}</h3>
						  </div>
						  <div class="panel-body">
						    	Locations: ${job.locations}	<br>
						    	Published: ${job.publicationDate} <br><br>
						    	${job.contents}		
						  </div>
						</div>
						</c:forEach>
            		</div>
            	</div>
            </div>

			<input type="hidden" value="${jobSearchResult.currentPage}">
			<input type="hidden" value="${jobSearchResult.uri}">
            <div align="center">
            	<button class="btn btn-success" onclick="goBack();">Back</button>
	            
            </div>
			
</body>
</html>