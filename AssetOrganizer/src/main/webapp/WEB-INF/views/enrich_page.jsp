<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Asset Organizer - Enrich</title>
</head>
<body>
	<h1>Asset Organizer - Enrich your files</h1>
	<form:form method="post" action="enrich" modelAttribute="enrichForm">
		<form:errors path="*" element="div" />
		<table id="enrichTable">
			<tr>
				<th><label>File Name:</label></th>
				<th><label>Video Name:</label></th>
				<th><label>Category:</label></th>
				<th><label>Genre:</label></th>
			</tr>
			<c:forEach varStatus="counter" var="video"
				items="${enrichForm.videos}">

				<tr>
					<td><input name="videos[${counter.index}].fileName"
						type="text" readonly="readonly" value="${video.fileName}" /></td>
					<td><input name="videos[${counter.index}].videoName"
						type="text" /></td>
					<td><select name="videos[${counter.index}].categoryId">
							<option selected="selected" value="-1">Please
								select</option>
							<c:forEach items="${categories}" var="category">
								<option value="${category.key}">${category.value}</option>
							</c:forEach>

					</select></td>
					<td><select name="videos[${counter.index}].genreId">
							<option selected="selected" value="-1">Please
								select</option>
							<c:forEach items="${genres}" var="genre">
								<option value="${genre.key}">${genre.value}</option>
							</c:forEach>

					</select></td>
					<td><input type="hidden" name="videos[${counter.index}].thumbName" value="${video.thumbName}"></td>

				</tr>

			</c:forEach>
		</table>
		<br />
		<input type="submit" value="Enrich" />
	</form:form>
</body>