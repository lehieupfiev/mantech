<%@ include file="../../layout/top.inc"%>

<style type="text/css">
	.grid {margin-top: 15px; border: 1px solid #000; border-collapse: collapse;}
	.grid th, .grid td {border: 1px solid #000; border-collapse: collapse}
</style>

<div id="user_list_pagelet" class="g-pl">
	<form id="user-filter-form" method="post" action="/user/search">
		<input type="text" name="q" />
		<select name="f">
			<option value="1">UserName</option>
			<option value="2">Department</option>
		</select>
		<div id="filter-query-submit" class="g-b g-b-r">Filter</div>
		<div id="lnkShowAll" class="g-b g-b-b">Show All</div>
		<table class="gg-list-table users grid">
			<thead><tr><th>Username</th><th>Role</th><th>Deparment</th><th>First Name</th><th>Last Name</th><th>Gender</th><th>Address</th><th>Status</th><th>Register Date</th></tr></thead>
			<tfoot><tr><th>Username</th><th>Role</th><th>Deparment</th><th>First Name</th><th>Last Name</th><th>Gender</th><th>Address</th><th>Status</th><th>Register Date</th></tr></tfoot>
			<tbody id="the-list">
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
					<td>${user.regDate }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</form>
</div>