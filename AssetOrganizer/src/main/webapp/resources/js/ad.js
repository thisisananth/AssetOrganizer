
//global variables

	var playerVal;

// Get the xml

function getAndShowAds(player,urlVal)
{
	
 playerVal = player;
 console.log(playerVal);
	
  $.ajax({
    type: "GET",
    url: urlVal,
    dataType: "xml",
    success: parseXml
  });
  
 
  
  
};

//parse the xml
function parseXml(xml)
{
	//find every Tutorial and print the author
	  $(xml).find("NonLinearAds").each(function()
	  {
		  $(this).find("NonLinear").each(function(){
			  
			  
			  	duration = $(this).attr("minSuggestedDuration");
			   imageUrl = $(this).find("StaticResource").text();
			   clickUrl = $(this).find("NonLinearClickThrough").text();
			   console.log("duration"+duration);
			   console.log("imageUrl"+imageUrl);
			   console.log("clickUrl"+clickUrl);
			   
			   setImageOverlay(imageUrl, clickUrl, duration);
			   return false;
		  });
	  });
	  
	 
}

function setImageOverlay(imageUrl,clickUrl,duration){
	
	var start_time =10;
	
	var split = duration.split(':');
	var hours = parseInt(split[0]);
	var mins = parseInt(split[1]);
	var seconds = parseInt(split[2]);
	endTime = hours*60*60 + mins*60 + seconds + parseInt(start_time);
	
	player.imageOverlay({
	    image_url: imageUrl,
	    click_url: clickUrl,
	    opacity: 1.0,
	    start_time: 10,
	    end_time:endTime,
	    height: '20%'
	  });
	
}


	 
	




