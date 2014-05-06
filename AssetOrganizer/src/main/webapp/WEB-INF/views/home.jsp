<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
	
	
<style type="text/css">

.bar {
    height: 18px;
    background: green;
    

}

 .spacer {
    margin-top: 15px; /* define margin as you see fit */
}

.show{
display:block;
}

.hide{
display:none;
}




</style>
</head>

<body role="document">

	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse" role="navigation"
		style="margin-bottom: 0;">
		<div class="container" style="width: 970px;">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><img
					src="resources/images/video-gain-logo.png" alt="" /></a>
			</div>
		</div>
	</div>
	
		<div class="container theme-showcase" role="main"
			style="width: 970px;">
			<div class="page-header no-border color-blue">
				<h1>Publish Wizard</h1>
			</div>
			<div class="row" style="margin-left: 10px;">
				<div class="col-sm-4 icon-set1 step1 on">
					<h3 class="list-group-item-heading">
						<a href="#" class="dotted-arrow on" style="width: 82%">Ingest</a>
					</h3>
				</div>
				<div class="col-sm-4 icon-set1 step2">
					<h3 class="list-group-item-heading">
						<a href="#" class="dotted-arrow " style="width: 82%">Enrich</a>
					</h3>
				</div>
				<div class="col-sm-4 icon-set1 step3">
					<h3 class="list-group-item-heading">
						<a href="#"  class="dotted-arrow" style="width: 82%">Publish</a>
					</h3>
				</div>
			</div>

			<div class="row page-header">
				<div class="col-sm-2">
					<a class="icon-set2 img-rounded thumb-nav step1">Cloud</a> <a
						class=" icon-set2 img-rounded thumb-nav step2 on">Local</a> <a
						class=" icon-set2 img-rounded thumb-nav step3">Web</a>
				</div>



				<div class="col-sm-10 bg-white img-rounded" style="display: block;">
				
				
					
					
						

					<div class="text-center  spacer" style="padding-top: 100px">
					 
						<span >
						<label class="lead">Upload video files   </label>	
						<button class="btn btn-info text-center" id="browse" type="button">
							<i class="fa fa-cloud-upload"></i> Browse
						</button>
						</span>
						
						
						<div id="progress">
							
							<div class="bar" style="width:0%;"></div>
						</div>
					
						<h5  id="progText" class="hide">Uploading...</h5>
					
						<input style="display: none;" id="fileupload" type="file" name="files[]"
							data-url="video/upload" accept="video/mp4">
						
						
						
						
						
					</div>
					
					<div class="table-responsive  spacer">
			 		 <table class="table table-bordered hide" id="uploaded-files" >
						
							<tr>
								<th>File Name</th>
								<th>File Size</th>
								<th>File Type</th>
								
							</tr>
						</table>
					</div>	


				</div>
			</div>
			<div class="text-center button-alignment">
				<!--<button class="btn btn-default" type="button"><i class="fa fa-arrow-left"></i> Back</button>-->
				<button style="display: none;" class="btn btn-success" type="button"
					onClick="window.location.href='page2.html';">
					<i class="fa fa-floppy-o"></i> Save
				</button>
				<button type="submit" id="nextButton" class="btn btn-success hide" type="button"
					onClick="window.location.href='ingest';">
					Next <i class="fa fa-arrow-right"></i>  
				</button>
			</div>
		</div>

	
	<footer clas="bs-docs-footer" style="height: 50px;">&nbsp;</footer>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>


	<script>
		$('#browse').bind("click", function() {

			$('#fileupload').click();
		});
	</script>
	<script src="resources/js/vendor/jquery.ui.widget.js"></script>
<script src="resources/js/jquery.iframe-transport.js"></script>
<script src="resources/js/jquery.fileupload.js"></script>
<script>
$(function () {
    
    	
	'use strict';

        $('#fileupload').fileupload({
            add: function(e, data) {
                    var uploadErrors = [];
                    var acceptFileTypes = /^video\/(mp4)$/i;
                    if(data.originalFiles[0]['type'].length && !acceptFileTypes.test(data.originalFiles[0]['type'])) {
                        uploadErrors.push('Please upload only video files in mp4 format');
                    }
                    if(data.originalFiles[0]['size'].length && data.originalFiles[0]['size'] > 153600) {
                        uploadErrors.push('Filesize is too big');
                    }
                    if(uploadErrors.length > 0) {
                        alert(uploadErrors.join("\n"));
                   
                    } else {
                        data.submit();
                    }
            },
        dataType: 'json',
 
        done: function (e, data) {
            $("tr:has(td)").remove();
            $.each(data.result, function (index, file) {
            	
 
                $("#uploaded-files").append(
                        $('<tr/>')
                        .append($('<td/>').text(file.fileName))
                        .append($('<td/>').text(file.fileSize))
                        .append($('<td/>').text(file.fileType))
                       
                        )//end $("#uploaded-files").append()
                        
                        
                        
            
                        
                
            }); 
            $("#uploaded-files:has(tr)").removeClass("hide");
           	if($('#uploaded-files tr').length > 0){
           		$('#nextButton').removeClass("hide");
           		$("#progText").addClass("hide");
           		
           	  $('#progress .bar').css('width','0%');

           	}
        },
 
        progressall: function (e, data) {
        	
        	$('#progText').removeClass("hide");
        	$('#browse').addClass("hide");//Upload only one file
        	$('.lead').addClass("hide");
        	
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                progress + '%'
            );
            $('#progText').text('Uploading...'+ progress+'%');
            if(progress == 100){
            	$('#progText').text('Uploaded Successfully');
             }
        }
    });
});
</script>

	<script src="resources/js/bootstrap.min.js"></script>
	<!--<script src="../../assets/js/docs.min.js"></script>-->
</body>
</html>
