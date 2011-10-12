<%@ include file="../layout/top.inc" %><compress:html jsCompressor="closure"
	compressJavaScript="true" compressCss="true" removeIntertagSpaces="true">

<c:forEach items="${listUser}" var="user">
	<tr class="gg-list-tr">
		<td><div class="gg-td-wrapper">${user.id}</div></td>
		<td>
			<div class="gg-td-wrapper">
				<strong>${user.username}</strong>
				<div class="gg-row-actions">
					<span><a href="<jtien:url address="/index"/>#edituser-${user.id}">Edit</a></span>
				</div>
			</div>
		</td>
		<td><div class="gg-td-wrapper">${user.role.name}</div></td>
		<td><div class="gg-td-wrapper">${user.department.name}</div></td>
		<td><div class="gg-td-wrapper">${user.firstName} ${user.lastName}</div></td>
		<td><div class="gg-td-wrapper">${user.gender == "M" ? "Male" : user.gender == "F" ? "Female" : "Gay"}</div></td>
		<td><div class="gg-td-wrapper">${user.status}</div></td>
	</tr>
</c:forEach>

</compress:html>