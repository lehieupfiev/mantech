<%@ include file="../layout/top.inc" %><compress:html jsCompressor="closure" compressJavaScript="true" compressCss="true" removeIntertagSpaces="true">

<c:forEach items="${listUser}" var="user">
	<tr>
		<td>${user.username}</td>
		<td>${user.role.name}</td>
		<td>${user.department.name}</td>
		<td>${user.firstName}</td>
		<td>${user.lastName}</td>
		<td>${user.gender}</td>
		<td>${user.homeAddress}</td>
		<td>${user.status}</td>
		<td>${user.regDate}</td>
	</tr>
</c:forEach>

</compress:html>