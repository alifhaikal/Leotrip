<?php
$response = array();
 require_once __DIR__ . '/db_connect.php';
$db = new DB_CONNECT();
if (isset($_GET["pid"])) {
    $pid = $_GET['pid'];
    $result = mysql_query("SELECT * FROM malaysia WHERE pid = '$pid'");
    if (!empty($result)) {
        if (mysql_num_rows($result) > 0) {
            $result = mysql_fetch_array($result);
            $destination = array();
            $destination["location"] = $result["loc_name"];
            $destination["description"] = $result["description"];
            $destination["phone"] = $result["contact"];
            $destination["url"] = $result["url"];
            $destination["address"] = $result["address"];


            $response["success"] = 1;
            $response["destination"] = array();
            array_push($response["destination"], $destination);
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No destination found";
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No destination found";
        echo json_encode($response);
    }
} else {
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
    echo json_encode($response);
}
?>
