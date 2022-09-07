<!DOCTYPE html>
<html>
<body>
<B>Today&nbsp;</B>${Today}
<B>Add Projects</b><br>

<form method="POST" action="/Project/addProject" enctype="application/x-www-form-urlencoded">
	Project id  <input type="text" name="projectId" placeholder="Enter project Id"/><br>
	Project name  <input type="text" name="projectName" placeholder="Enter project name"/><br>
	Start date  <input type="date" name="startDate" placeholder="Enter start date"/><br>
	End date  <input type="date" name="endDate" placeholder="Enter end date"/><br><br>
	<input type="submit" value="Add" />
</form>
</body></html>