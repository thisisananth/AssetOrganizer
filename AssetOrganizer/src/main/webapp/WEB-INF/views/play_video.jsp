<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Asset Organizer</title>
	<link href="http://vjs.zencdn.net/4.5/video-js.css" rel="stylesheet">
<script src="http://vjs.zencdn.net/4.5/video.js"></script>
<script src=resources/js/videojs.watermark.js></script>
 <link href="resources/js/videojs.watermark.css" rel="stylesheet">
</head>
<body>
<h1>
	Welcome to Asset Organizer
</h1>
<video id="my_video_1" class="video-js vjs-default-skin" controls
 preload="auto" width="640" height="264" poster=""
 data-setup="{}">
 <source src="video/1 - 1 - Introduction to the Android Platform (18-19).mp4" type='video/mp4'>

</video>
<script>
// initialize video.js
var my_video_id = videojs('my_video_1');

// Set value to the plugin
my_video_id.watermark({
  file: 'resources/images/thumbnail.jpg',
  //file: 'http://www.videojs.com/img/logo.png',
  xpos: 100,
  ypos: 0,
  xrepeat: 0,
  opacity:0.5
  });
</script>

</body>
</html>
