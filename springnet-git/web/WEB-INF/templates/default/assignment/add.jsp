<%@ include file="../layout/top.inc" %><compress:html jsCompressor="closure" compressJavaScript="true" compressCss="true" removeIntertagSpaces="true">

<style>
	.grid {border: 1px solid #000; border-collapse: collapse;}
	.grid th, .grid td { border-collapse: collapse; padding: 1px 2px;}
	.grid .row:hover {background-color: #eee; cursor: pointer;}
</style>

<style type = "text/css">
	#form { float: left; margin-top: 20px}
	#details { margin: 20px 450px; }
	#details h2 {text-align: center; font-style: italic;}
	#details .row strong {margin-top: 0; }
	.row { margin-bottom:12px; }
	.row strong { display:inline-block; margin-top:4px; width:90px; vertical-align:top; font-weight:bold; }
</style>

<div id="pagelet_addassignment" class="g-pl">
	<div id="addassignment-box" class="box wrap">
		<h2>Add New Assignment</h2>
		<div id="form">
			<form id="addassignment-form" class="g-f" method="post" action="/assignment/addSave" >
				<div class="row">
					<label>
						<strong>Begin Date: </strong>
						<input type="text" name="beginDate"/>
					</label>
				</div>
				<div class="row">
					<label>
						<strong>Duration: </strong>
						<input type="text" name="duration"/>
					</label>
				</div>
				<div class="row">
					<label>
						<strong></strong>
					</label>
				</div>
				<div class="row">
					<label>
						<strong>Technician Name</strong>		
					</label>
				</div>
				<div>
					<table class="grid">
						<c:forEach items="${technicians}" var="tech">
							<tr>
								<td><input type="checkbox" name="userId" value="${tech.id}" /></td>
								<td>${tech.id}</td><td>${tech.firstName}</td><td>${tech.lastName}</td>
								<td>${tech.status}</td>
							<tr>
						</c:forEach>
					</table>
				</div>
				<div>
					<input type="hidden" name="compId" value="${compId}" />
				</div>
				
				<div id="btnAdd" class="g-b g-b-r">Add new assignment</div>
				<div id="btnReset" class="g-b g-b-b">Reset</div>
			</form>
		</div>
	</div>
</div>

</compress:html>