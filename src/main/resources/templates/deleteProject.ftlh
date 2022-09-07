<!DOCTYPE html>
<html>
<body>
<B>Today&nbsp;</B>{Today}
<B>Add Projects</b><br>

<form method="POST" action="/Project/deleteProject" enctype="application/x-www-form-urlencoded">
	<select name="projectName" size=10>
	<#list projects as project>
		<option value=${project.projectId}>${project.projectName}</option>
	</#list>
	</select><br>
	<input type="submit" value="Delete" />
</form>
</body></html>