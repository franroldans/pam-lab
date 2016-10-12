<!DOCTYPE html>
<html>	
	<head>
	<h3>Complete the following form to modify the user:</h3>
	<?php 	
		include 'database.php';
				
		$conn = connect ();
		$old_user = $_GET["old_user"];
		$old_pswd = $_GET["old_pswd"];
		$new_user = $_GET["new_user"];
		$new_pswd = $_GET["new_pswd"];
		$new_name = $_GET["new_name"];
		$new_surname = $_GET["new_surname"];
		if ($old_user && $old_pswd && $new_user && $new_pswd && $new_name && $new_surname)
		{
			modify_user($conn, $old_user, $old_pswd, $new_user, $new_pswd, $new_name, $new_surname);	
		}else{
			echo "[WARN]: You need to include all the values of the form!";
		}
		
		disconnect($conn); 				
	?> 
	</head>

	<body>
	<br>
	<small>(*)Insert the current values if you don't want to modify it.</small>
	<br>
	<form>
		Insert the current username:<br>
		<input type="text" name="old_user"><br><br>
		Insert the current password:<br>
		<input type="text" name="old_pswd"><br><br>
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
