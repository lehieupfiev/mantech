<%@ include file="../layout/top.inc"%><compress:html
	jsCompressor="closure" compressJavaScript="true" compressCss="true"
	removeIntertagSpaces="true">

<c:forEach items="${listAll}" var="complaint">
	<tr>
		<td>${complaint.id}</td>
		<td>${complaint.user.username}</td>
		<td>${complaint.equipment.name}</td>
		<td>${complaint.title}</td>
		<td>${complaint.content}</td>
		<td>${complaint.priority.name}</td>
		<td>${complaint.status.name}</td>
		<td>${complaint.createDate}</td>
		<td>${complaint.endDate}</td>
	</tr>
</c:forEach>

</compress:html>