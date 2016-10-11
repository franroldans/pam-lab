<?php 
	// Define Database parameters
	$servername = //YOUR_SERVER_NAME;
	$username = //YOUR_USER_NAME;
	$password = //YOUR_PASSWORD;
	$dbName = //YOUT DB NAME;
				
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
		$sql = "SELECT * FROM `Users` where username = '". $user . "' and password = '". $password . "'" ;
		
		$result = mysqli_query($conn, $sql);			

		if (mysqli_num_rows($result) > 0) {
			// output data of each row
			while($row = mysqli_fetch_assoc($result)) 
			{
				echo "Welcome " . $user ;								
			}
		} 		
		else 
        {
			echo "0 results";
		}			
	}

	//Function that gets all users info and //TODO: print it on an HTML table.
        function get_user_list ($conn)
	{
		$sql = "SELECT * FROM `Users`" ;
		
		$result = mysqli_query($conn, $sql);			

		if (mysqli_num_rows($result) > 0) {
			// output data of each row
			while($row = mysqli_fetch_assoc($result)) 
			{		
			echo json_encode($row) . "<br>";			
			}
		} 		
		else 
        {
			echo "0 results";
		}			
	}

	//Function that handles the addition of a user.
        function add_user ($conn, $user, $password, $name, $surname)
	{
		$sql = "INSERT INTO `Users` (`username`, `password`, `name`, `surname`) VALUES ('". $user ."', '" . 
			$password . "', '" . $name . "', '" . $surname . "');";

		mysqli_query($conn, $sql);	
	
	}

	//Function that handles the removal of a user.
        function delete_user ($conn, $username)
	{
		$sql = "DELETE FROM `Users` where `username` ='" . $username ."'";
		
		mysqli_query($conn, $sql);	

	
	}

	//Function that handles user information modifications
        function modify_user ($conn, $old_user, $user, $password, $name, $surname)
	{
		
		$sql = "UPDATE `Users` SET `username` = '"  . $user . "', `password` = '" . $password . "',
		 	`name` = '" . $name . "', `surname` = '" . $surname . "' where `username` = '" 
			. $old_user . "'";
		
		mysqli_query($conn, $sql);		
	
	}

									
?> 
