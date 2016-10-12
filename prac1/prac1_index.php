<!DOCTYPE html>
<html>
	<header>
	<h1>SIMPLE USER MANAGER</h1><br>
	In that page there is a list of the current users, 
	and you can add and delete users, as well as modify
	an existing user.
	<br> 
	</header>

	<body>
	
	<p>
	<b>Current List of Users:</b>
	<br><br>
	<?php 	
		include 'database.php';
				
		$conn = connect ();

		get_user_list ($conn);	

		disconnect($conn);
	?> 
	</p>

	<p>
	<b>Click Here to add a user to the database:</b>
	<button type="button" onclick="location.href = 'add.php?new_user=&new_pswd=&new_name=&new_surname=';">Add User</button>
	</p>

	<p>
	<b>Click Here to delete a user to the database:</b>
	<button type="button" onclick="location.href = 'delete.php?user=';">Delete User</button>
	</p>

	<p>
	<b>Click Here to modify information about a user:</b>
	<button type="button" onclick="location.href = 'modify.php?old_user=&old_pswd=&new_user=&new_pswd=&new_name=&new_surname=';">Modify User</button>
	</p>
	</body>
</html> 
