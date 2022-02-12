<?php
$servername = "localhost";
$username = "root";
$password = "";
$con = mysqli_connect($servername, $username, $password,'commentapp');
if (!$con) {
    die("Connection failed: " . mysqli_connect_error());
  }
?>