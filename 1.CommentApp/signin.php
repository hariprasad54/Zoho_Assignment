<?php
session_start();
include('db.php');
include("aes.php");
function alert($msg) {
    echo "<script type='text/javascript'>alert('$msg'); window.location.href='index.html';</script>";
}
if(isset($_POST['signin'])){
    $email = mysqli_real_escape_string($con,$_POST['email']);
    $password = mysqli_real_escape_string($con,$_POST['password']);
    $enc_pwd = encr($password);
    $q = mysqli_query($con,"SELECT * FROM users where password = '$enc_pwd' and email = '$email'");
    $r = mysqli_fetch_array($q);
    $rn = mysqli_num_rows($q);
    if($rn > 0){
        $_SESSION["email"] = $email;
        header('Location: comments.php');
    }
    else{
        echo alert("Invali Username or Password");
    }
}
?>