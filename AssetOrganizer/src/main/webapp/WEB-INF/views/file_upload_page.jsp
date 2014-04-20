<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Asset Organizer - Upload Files</title>
<script
src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script>
$(document).ready(function() {
    //add more file components if Add is clicked
    $('#addFile').click(function() {
        var fileIndex = $('#fileTable tr').children().length/4;
       
        $('#fileTable').append(
                '<tr><td><label/>Select the Video file:</td><td>'+
                '   <input type="file" name="files['+ fileIndex +'].videoFile" />'+
                '</td><td><label/>Select the Thumbnail:</td><td>'+
                '   <input type="file" name="files['+ fileIndex +'].thumbnail" />'+
                '</td></tr>');
    });
     
});
</script>
</head>
<body>
<h1>Asset Organizer - Upload Files.</h1>

 
<form:form method="post" action="ingest"
        modelAttribute="uploadForm" enctype="multipart/form-data">
        
        
<form:errors path="*"  element="div" />
 
    <p>Select files to upload. Press Add button to add more file inputs.</p>
    
 
    
    <table id="fileTable">
        <tr>
       	     <td><label/>Select the Video file:</td>
        	<td><input name="files[0].videoFile" type="file"  /></td>
        	<td><label/>Select the Thumbnail:</td>
            <td><input name="files[0].thumbnail" type="file"  /></td>
        </tr>
        
    </table>
    <br/><input type="submit" value="Upload" />
    <br><input id="addFile" type="button" value="Add another file" />
</form:form>
</body>
</html>
