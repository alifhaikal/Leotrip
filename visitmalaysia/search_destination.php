<?php

/*
 * Following code will list all the products
 */

// array for JSON response
$response = array();

// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

$vstate = $_GET['state'];
// get all products from products table

$result = mysql_query("SELECT * FROM malaysia WHERE state = '$vstate'");

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["destination"] = array();

    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $destination = array();
        $destination["pid"] = $row["pid"];
        $destination["loc_name"] = $row["loc_name"];
        $destination["latitude"] = $row["latitude"];
        $destination["longitude"] = $row["longitude"];
        $destination["description"] = $row["description"];
        
        // push single product into final response array
        array_push($response["destination"], $destination);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No destination found";

    // echo no users JSON
    echo json_encode($response);
}
?>