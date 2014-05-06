<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Asset Organizer</title>
	<link href="resources/css/video-js.css" rel="stylesheet">
<script src="http://vjs.zencdn.net/4.5/video.js"></script>
<script src=resources/js/videojs.watermark.js></script>
<script src=resources/js/videojs-overlay.js></script>
 <link href="resources/css/videojs.watermark.css" rel="stylesheet">
 <link href="resources/css/videojs-overlay.css" rel="stylesheet">
 <!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="resources/css/bootstrap-theme.css" rel="stylesheet">
 <!-- Custom styles for this template -->
<link href="resources/css/theme.css" rel="stylesheet">
 <style type="text/css">
  .vjs-default-skin { color: #ffffff; }
  .vjs-default-skin .vjs-play-progress,
  .vjs-default-skin .vjs-volume-level { background-color: #13A0D8 }
  .vjs-default-skin .vjs-control-bar,
  .vjs-default-skin .vjs-big-play-button { background: #222c37 }
  .vjs-default-skin .vjs-slider { background: #656E79 }
  .vjs-poster{background-size:cover}
  .table{width:50%}
</style>
</head>
<body>


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
				<h1>Preview</h1>
			</div>

<video id="my_video_1" class="video-js vjs-default-skin vjs-big-play-centered" controls
 preload="none" width="900" height="372" poster="image/${video.thumbFileName}"
 data-setup="{}">
 <source src="video/${videoName }" type="video/mp4">
  <track kind="captions" src="captions.vtt" srclang="en" label="English" /></track>

</video>
<h2>${video.videoName }</h2>
<br>

<div class="table">
			  <table class="table">
				<thead>
				  <tr>
					<th>Category</th>
					<th>Genre</th>
					
					
					
</tr>
</thead>
<tr>
<td>${video.categoryName }</td>
<td>${video.genreName }</td>
</tr>
</table>
</div>




</div>
<script>
// initialize video.js
var my_video_id = videojs('my_video_1');


  
// Show the overlay text
videojs('my_video_1').overlay({
    content: '<strong>VG Player</strong>',
    overlays: [{
      content: '${playerName}',
      start: 'play',
      end: 'never',
      align: 'top-right'
    }]
  });
  
  
</script>

</body>
</html>
