<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Asset Organizer - Publish</title>
</head>
<body>
	<h1>Asset Organizer - Publish your files</h1>
	<form:form method="post" action="publish"
		modelAttribute="enrichForm">
		<form:errors path="*" element="div" />
		<table style="table-layout:fixed" id="enrichTable">
			<tr>
				<th><label>File Name:</label></th>
				<th><label>Video Name:</label></th>
				<th><label>Category:</label></th>
				<th><label>Genre:</label></th>
			</tr>
			<c:forEach varStatus="counter" var="video" items="${enrichForm.videos}">

				<tr>
					<td style="word-wrap:break-word" ><input name="videos[${counter.index}].fileName" type="text" width="30" 
						readonly="readonly" value="${video.fileName}" /></td>
					<td><input name="videos[${counter.index}].videoName" type="text" value="${video.videoName}" width="30" readonly="readonly" /></td>
					<td><input name="${video.categoryId}" type="text" value="${categories[video.categoryId]}" width="20" readonly="readonly" /></td>
					<td><input name="${video.genreId}" type="text" value="${genres[video.genreId]}" width="20" readonly="readonly" /></td>
					
					<td><input name="videos[${counter.index}].categoryId" type="hidden" value="${video.categoryId}" width="20" readonly="readonly" /></td>
					<td><input name="videos[${counter.index}].genreId" type="hidden" value="${video.genreId}" width="20" readonly="readonly" /></td>
					<td><input type="hidden" name="videos[${counter.index}].thumbName" value="${video.thumbName}"></td>

				</tr>

			</c:forEach>
		</table>
	<br/><input type="submit" value="Publish" />
	</form:form>
</body>