<?php

// array for JSON response
$response = array();
 // check for required fields
   $location = $_GET['location'];
    $state = $_GET['state'];

  require_once __DIR__ . '/db_connect.php';    // include db connect class
    $db = new DB_CONNECT();    // connecting to db
    // mysql inserting a new row
if (isset($_GET["state"])){
   $result = mysql_query("SELECT * FROM malaysia WHERE state = '$state'");
}else{
$result = mysql_query("SELECT * FROM malaysia");
    
}
     if ($result) {
        echo "<table border='1'><tr><th>PID</th><th>Location</th><th>States</th><th>Description</th><th>Latitude</th><th>Longitude</th><th>URL</th><th>Contact</th><th>Address</th><th>Update</th></tr>";
       while($row = mysql_fetch_array($result)) {
           //echo $row['loc_name'];

           echo "<form action='upd_data.php' method='get'><tr><td><input name='pid' required='' size='8' type='text' value=".$row["pid"]." readonly/></td><td><textarea cols='15' name='location' rows='2'>".$row["loc_name"]."</textarea></td><td>".$row["state"]." </td><td><textarea cols='50' name='desc' rows='4'>".$row["description"]."</textarea> </td><td> <input name='lat' required='' value=".$row["latitude"]." size='15' type='text' /></td><td> <input name='lon' required='' value=".$row["longitude"]." size='15' type='text' /></td><td><input name='url' size='30' type='text' value=".$row["url"]."></input> </td><td><input name='contact'  size='15' type='text' value=".$row["contact"]."> </input></td><td><textarea cols='50' name='address' rows='4'>".$row["address"]."</textarea> </td><td><input type='submit' value='Update'/></td></tr></form>";
       }
        echo "</table>";
       echo "<button onclick='history.go(-1);'>Back </button>";

      } else {
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        //echo json_encode($response); //return error
        echo "Error";
        echo "<button onclick='history.go(-1);'>Back </button>";
      }

?>
