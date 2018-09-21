<?php

// array for JSON response
$response = array();
 // check for required fields
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
    // mysql inserting a new row
$result = mysql_query("INSERT INTO malaysia(loc_name, state, description,latitude,longitude,url,contact,address)VALUES('$location', '$state', '$desc', '$lat', '$lon', '$url', '$contact', '$address')");

     if ($result) {
        $response["success"] = 1;
        $response["message"] = "New entry added.";
        echo "Data Successfully Inserted"; //return succces
       $rs = mysql_query("SELECT * FROM malaysia");
        echo "<table border='1'><tr><th>ID</th><th>Location</th><th>States</th><th>Description</th><th>Latitude</th><th>Longitude</th><th>URL</th><th>Contact</th><th>Address</th></tr>";
       while($row = mysql_fetch_array($rs)) {
           echo "<tr><td>".$row["pid"]."</td><td>".$row["loc_name"]."</td><td>".$row["state"]." </td><td>".$row["description"]." </td><td>".$row["latitude"]." </td><td>".$row["longitude"]." </td><td>".$row["url"]." </td><td>".$row["contact"]." </td><td>".$row["address"]." </td></tr>";
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
