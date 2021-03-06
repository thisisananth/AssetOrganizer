
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Video Gain</title>

   
   <!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="resources/css/bootstrap-theme.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resources/css/theme.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<link href="resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
  </head>

  <body role="document">

    <!-- Fixed navbar -->
    <div class="navbar navbar-inverse" role="navigation" style="margin-bottom: 0;">
      <div class="container" style="width: 970px;">
        <div class="navbar-header">
          <a class="navbar-brand" href="#"><img src="resources/images/video-gain-logo.png" alt=""/></a>
        </div>        
      </div>
    </div>
	
    <div class="container theme-showcase" role="main" style="width: 970px;">
      <div class="page-header no-border color-blue">
        <h1>Publish Wizard</h1>
      </div>
	  <div class="row" style="margin-left: 10px;">
	    <div class="col-sm-4 icon-set1 step1">
			<h3 class="list-group-item-heading"><a href="#" class="dotted-arrow" style="width: 82%">Ingest</a></h3>
		</div>
	    <div class="col-sm-4 icon-set1 step2">
			<h3 class="list-group-item-heading"><a href="#" class="dotted-arrow" style="width: 82%">Enrich</a></h3>
		</div>
	    <div class="col-sm-4 icon-set1 step3">
			<h3 class="list-group-item-heading"><a href="#" class="dotted-arrow" style="width: 82%">Publish</a></h3>
		</div>
	  </div>
	  <div class="row page-header">
		<div class="col-sm-2">
			<a class="icon-set2 img-rounded thumb-nav step1">Cloud</a>
			<a class=" icon-set2 img-rounded thumb-nav step2 on">Local</a>
			<a class=" icon-set2 img-rounded thumb-nav step3">Web</a>
		</div>
		<div class="col-sm-10 bg-white img-rounded" style="display: block;">
			<h3 align="center">Oops.!!!</h3>
			<br>
			<h4 align="center">Something went wrong.Please try again later.</h4>
			
			<!--
   			 Failed URL: ${url}
   			 Exception:  ${exception.message}
        	<c:forEach items="${exception.stackTrace}" var="ste">    ${ste} 
   			 </c:forEach>
   			 -->
			
		</div>
		
	  </div>
	  
	</div> 
	
	<footer clas="bs-docs-footer" style="height: 50px;">&nbsp;</footer>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <!--<script src="../../assets/js/docs.min.js"></script>-->
  </body>
</html>
