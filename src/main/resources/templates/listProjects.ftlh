<!DOCTYPE html>
<html>
<style>
table, th, tr, td{
border: 3px solid black
}
</style>
<body>
<B>List Of Projects</b><br>
<table>
<b><tr><td>Project ID</td><td>Project Name</td><td>Start Date</td><td>End Date</td></tr></b>
	<!-- list is similar to for each loop in FreeMarker.
	
	projects is a collection. It iterates through this collection from 1st to last manner. -->
	<#list projects as project>
		<tr><td>${project.projectId}</td><td>${project.projectName}</td><td>${project.startDate}</td>
		<td>${project.endDate}</td></tr>
	</#list>
</table>
</body></html>