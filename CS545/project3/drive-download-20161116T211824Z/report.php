<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Marathon Runners Report</title>    
	<link rel="stylesheet" href="report.css">
</head>
<body>
    <h1>Marathon Runners Report</h1>
<?php
	$UPLOAD_DIR = '_apics_';
	$COMPUTER_DIR = '/home/jadrn034/public_html/proj3/_apics_';
	$server = 'opatija.sdsu.edu:3306';
	$user = 'jadrn034';
	$password = 'suitcase';
	$database = 'jadrn034';
	
	if(!($db = mysqli_connect($server, $user, $password, $database)))
        echo "ERROR: Connection failed: ".mysqli_error($db);
	else {
		$sql = "select * from person group by category order by lname;";
		$result = mysqli_query($db, $sql);
		if(!$result)
			echo "ERROR in query" .mysqli_error($db);
			echo "<table>\n";
			echo "<tr><td>Profile Picture</td><td>First Name</td><td>Last Name</td><td>Age</td><td>Experience Level</td></tr>";
		while($row = mysqli_fetch_assoc($result)) {
			echo "<tr>";
			echo "<td><img src='$UPLOAD_DIR/$row[filename]' width='200px' /></td>";
			echo "<td>$row[fname]</td>";
			echo "<td>$row[lname]</td>";
			$bday = new DateTime($row['dob']);
			$today = new DateTime('00:00:00');
			$diff = $today->diff($bday);
			echo "<td>$diff->y</td>";
			echo "<td>$row[experience]</td>";
			echo "</tr>\n";	
		}
		mysqli_close($db);	
	}
?>
</table>
    
</body>
</html>