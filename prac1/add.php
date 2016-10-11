<!DOCTYPE html>
<html>	
	<head>
	<h3>Complete the following form to add a new user:</h3>
	<?php 	
		include 'database.php';
				
		$conn = connect ();
		$new_user = $_GET["new_user"];
		$new_pswd = $_GET["new_pswd"];
		$new_name = $_GET["new_name"];
		$new_surname = $_GET["new_surname"];
		if ($new_user && $new_pswd && $new_name && $new_surname)
		{
			add_user($conn, $new_user, $new_pswd, $new_name, $new_surname);	
		}else{
			echo "WARN: You need to include all the values of the form!";
		}
		
		disconnect($conn); 				
	?> 
	</head>

	<body>
	<br>
	<br>
	<form>
		New username:<br>
		<input type="text" name="new_user"><br><br>
		New password:<br>
		<input type="text" name="new_pswd"><br><br>
		New name:<br>
		<input type="text" name="new_name"><br><br>
		New surname:<br>
		<input type="text" name="new_surname"><br><br>
		<input type="submit" value="Submit"><br><br>
	</form>
	<p>
	<button type="button" onclick="location.href = 'prac1_index.php';">Return</button>
	</p>
	</body>
</html> 
