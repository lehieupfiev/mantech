<%@ include file="../layout/top.inc" %><compress:html jsCompressor="closure"
	compressJavaScript="true" compressCss="true" removeIntertagSpaces="true">

<c:forEach items="${listCategory}" var="category">
	<tr class="gg-list-tr">
		<td><div class="gg-td-wrapper">${category.id}</div></td>
		<td>
			<div class="gg-td-wrapper">
				<strong>${category.name}</strong>
				<div class="gg-row-actions">
					<span><a href="<jtien:url address="/index"/>#editcategory-${category.id}">Edit</a></span>
				</div>
			</div>
		</td>
	</tr>
</c:forEach>

</compress:html>