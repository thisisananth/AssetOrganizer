<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Asset Organizer</title>
	<link href="resources/css/video-js.css" rel="stylesheet">
<script src="http://vjs.zencdn.net/4.5/video.js"></script>
<script src=resources/js/videojs.watermark.js></script>
 <link href="resources/css/videojs.watermark.css" rel="stylesheet">
 <style type="text/css">
  .vjs-default-skin { color: #ffffff; }
  .vjs-default-skin .vjs-play-progress,
  .vjs-default-skin .vjs-volume-level { background-color: #13A0D8 }
  .vjs-default-skin .vjs-control-bar,
  .vjs-default-skin .vjs-big-play-button { background: #222c37 }
  .vjs-default-skin .vjs-slider { background: #656E79 }
</style>
</head>
<body>
<h1>
	Welcome to Asset Organizer
</h1>
<video id="my_video_1" class="video-js vjs-default-skin vjs-big-play-centered" controls
 preload="none" width="640" height="264" poster="image/${video.thumbFileName}"
 data-setup="{}">
 <source src="video/${videoName }" type="video/mp4">
  <track kind="captions" src="captions.vtt" srclang="en" label="English" /></track>

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
  opacity:0.3
  });
</script>

</body>
</html>
