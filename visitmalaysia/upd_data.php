<?php
//update
// array for JSON response
$response = array();
 // check for required fields
    $pid = $_GET['pid'];
    $location = $_GET['location'];
    $state = $_GET['state'];
    $desc = $_GET['desc'];
    $lat = $_GET['lat'];
    $lon = $_GET['lon'];
    $url = $_GET['url'];
    $contact = $_GET['contact'];
    $address = $_GET['address'];

  require_once __DIR__ . '/db_connect.php';    // include db connect class
    $db = new DB_CONNECT();    // connecting to db
    // mysql update 

$result = mysql_query("UPDATE malaysia SET loc_name='$location', description='$desc',latitude='$lat',longitude='$lon',url='$url',contact='$contact',address='$address' WHERE pid = '$pid'");

     if ($result) {
        $response["success"] = 1;
        $response["message"] = "Data $pid updated.";
        echo "Data successfully updated"; //return succces
       $rs = mysql_query("SELECT * FROM malaysia WHERE pid= '$pid'");
        echo "<table border='1'><tr><th>ID</th><th>Location</th><th>States</th><th>Description</th><th>Latitude</th><th>Longitude</th><th>URL</th><th>Contact</th><th>Address</th></tr>";
       while($row = mysql_fetch_array($rs)) {
           echo "<tr><td>".$row["pid"]."</td><td>".$row["loc_name"]."</td><td>".$row["state"]." </td><td>".$row["description"]." </td><td>".$row["latitude"]." </td><td>".$row["longitude"]." </td><td>".$row["url"]." </td><td>".$row["contact"]." </td><td>".$row["address"]." </td></tr>";
$state = $row["state"];
       }
        echo "</table>";
        echo "<form action='show_data.php' method='get'> <input name='state' required='' size='8' type='text' value=$state readonly/><input type='submit' value='Back'/>";
     //echo "<button onclick='history.go(-1);'>Back </button>";

      } else {
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        //echo json_encode($response); //return error
        echo "Error";
        echo "<button onclick='history.go(-1);'>Back </button>";
    }



?>
