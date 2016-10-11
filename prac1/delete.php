<!DOCTYPE html>
<html>	
	<head>
	<h3>Complete the following form to delete a user:</h3>
	<?php 	
		include 'database.php';
				
		$conn = connect ();
		$user = $_GET["user"];
		if ($user)
		{
			delete_user($conn, $user);	
		}else{
			echo "WARN: You need to include all the values of the form!";
		}
		
		disconnect($conn); 				
	?> 
	</head>

	<body>
	<br>
	<form>
		Username:<br>
		<input type="text" name="user"><br><br>
		<input type="submit" value="Submit"><br><br>
	</form>
	<p>
	<button type="button" onclick="location.href = 'prac1_index.php';">Return</button>
	</p>
	</body>
</html> 
