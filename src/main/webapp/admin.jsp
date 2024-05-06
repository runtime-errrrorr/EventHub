<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EventHub-Admin</title>
<link rel="stylesheet" href="admin.css">
</head>
<body>
	<input type ="hidden" id ="status" value="<%= request.getAttribute("status")%>">
  <header>
    <h1>EventHub - Event Management</h1>
    <p>Welcome!</p>
  </header>
  <main>
    <section class="hero">
      <h2>What magic are you creating?</h2>
    </section>
    <section class="event-form">
      <form action="InfoServlet" method="post">
        <label for="eventName">Event Name:</label>
        <input type="text" id="ename" name="ename" placeholder="Enter a captivating event name" required>

        <label for="eventDate">Date:</label>
        <input type="date" name="edate" id="edate" required>

        <label for="eventTime">Time:</label>
        <input type="time" name="etime" id="etime" required>

        <label for="venue">Venue:</label>
        <input type="text" name="evenue" id="evenue" placeholder="Physical location or online platform" required>

        <label for="eventDescription">Event Description:</label>
        <textarea id="edesc" name="edesc" placeholder="Describe your event in detail" required></textarea>

        <button type="submit">Save Event</button>
        <button type="reset">Cancel</button>
      </form>
    </section>
  </main>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	<script type="text/javascript">
	var status = document.getElementById("status").value;
	if(status === "success") {
		swal("Congrats", "Info Posted successfully", "success");	
	}
	</script>
</body>
</html>
