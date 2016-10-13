<?php 
	// Define Database parameters
	$servername = "localhost";
	$username = "root";
	$password = // INSERT YOUR PASSWORD
	$dbName = // INSERT YOUR DATABASE'S NAME
	$dbTable = // INSERT THE USER TABLE'S NAME 
				
	// Connect to the database	
	function connect ()
	{		 
		global $servername, $username, $password, $dbName;
		
		$conn = mysqli_connect($servername, $username, $password, $dbName);
		// Check connection
		
		if (!$conn) {
			die("Connection failed: " . mysqli_connect_error());
		}
		return $conn;
	}
	
	// Disconnect database
	function disconnect ($conn)
	{
		mysqli_close($conn); 
	}

	function check_user ($conn, $user, $password)
	{
		global $dbTable;
		$sql = "SELECT * FROM `" . $dbTable . "` where username = '". $user . "' and password = '". $password . "'" ;
		
		$result = mysqli_query($conn, $sql);			

		if (mysqli_num_rows($result) > 0) {
			return 1;
		} 		
		else 
        {
			return 0;
		}			
	}

	//Function that gets all users info and print it on an HTML table.
        function get_user_list ($conn)
	{
		global $dbTable;
		$sql = "SELECT * FROM `" . $dbTable . "`" ;
		
		$result = mysqli_query($conn, $sql);			
		echo '<table class="table table-striped table-bordered table-hover">'; 
		echo "<tr><th>Username</th><th>Password</th><th>Name</th><th>Surname</th></tr>"; 
		if (mysqli_num_rows($result) > 0) {
			// output data of each row
			while($row = mysqli_fetch_assoc($result)) 
			{		
				echo "<tr><td>";
				echo $row['username'];
				echo "</td><td>";
				echo $row['password'];
				echo "</td><td>";
				echo $row['name'];
				echo "</td><td>";
				echo $row['surname'];
				echo "</td></tr>";
			}
		} 		
		else 
        {
			echo "0 results";
		}
		echo "</table>";			
	}

	//Function that handles the addition of a user.
        function add_user ($conn, $user, $password, $name, $surname)
	{
		global $dbTable;
		$sql = "INSERT INTO `" . $dbTable . "` (`username`, `password`, `name`, `surname`) VALUES ('". $user ."', '" . 
			$password . "', '" . $name . "', '" . $surname . "');";

		mysqli_query($conn, $sql);	
	
	}

	//Function that handles the removal of a user.
        function delete_user ($conn, $username)
	{
		global $dbTable;
		$sql = "DELETE FROM `" . $dbTable . "` where `username` ='" . $username ."'";
		
		mysqli_query($conn, $sql);	

	
	}

	//Function that handles user information modifications
        function modify_user ($conn, $old_user, $old_pswd, $user, $password, $name, $surname)
	{
		if(check_user($conn, $old_user, $old_pswd)){
			global $dbTable;
			$sql = "UPDATE `" . $dbTable . "` SET `username` = '"  . $user . "', `password` = '" . $password . "',
			 	`name` = '" . $name . "', `surname` = '" . $surname . "' where `username` = '" 
				. $old_user . "'";
			mysqli_query($conn, $sql);	
			echo "[INFO]: Modifications applied";	
		}else{
			echo "[ERROR]: User not found";
		}
	}

									
?> 
