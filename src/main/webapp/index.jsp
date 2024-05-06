<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="index.css">
<link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
</head>
<body>
	<input type ="hidden" id ="status" value="<%= request.getAttribute("status")%>">
	<div class="main">  	
		<input type="checkbox" id="chk" aria-hidden="true">

		<div class="signup">
			<form action="RegisterServlet" method="post">
				<label for="chk" aria-hidden="true">Sign up</label>
				<input type="text" id="username" name="username" placeholder="User name" required="">
				<input type="password" id="password" name="password" placeholder="Password" required="">
				<select id="role" name="role" required="">
					<option value="" disabled selected>Select role</option>
					<option value="Student">Student</option>
					<option value="Admin">Admin</option>
				</select>
				<button type="submit">Sign up</button>
			</form>
		</div>

		<div class="login">
			<form action="LoginServlet" method="post">
				<label for="chk" aria-hidden="true">Login</label>
				<input type="text" id="username" name="username" placeholder="User name" required="">
				<input type="password" id="password" name="password" placeholder="Password" required="">
				<select id="role" name="role" required="">
					<option value="" disabled selected>Select role</option>
					<option value="Student">Student</option>
					<option value="Admin">Admin</option>
				</select>
				<button type="submit">Login</button>
			</form>
		</div>
	</div>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	<script type="text/javascript">
	var status = document.getElementById("status").value;
	if(status === "success") {
		swal("Congrats", "Account Created Successfully", "success");	
	}
	else
	{
		swal("Sorry","Wrong username or password","failed");
	}
	</script>

</body>
</html>