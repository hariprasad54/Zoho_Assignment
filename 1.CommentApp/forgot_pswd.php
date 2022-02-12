<?php
include('db.php');
include("aes.php");
function alert($msg) {
    echo "<script type='text/javascript'>alert('$msg'); window.location.href='index.html';</script>";
}

if(isset($_POST['reset'])){
    $email = mysqli_real_escape_string($con,$_POST['email']);
    $secretkey = mysqli_real_escape_string($con,$_POST['secretkey']);
    $enc_secret = encr($secretkey);
    $q = mysqli_query($con,"SELECT * FROM users where secretkey = '$enc_secret' and email = '$email'");
    $r = mysqli_fetch_array($q);
    $rn = mysqli_num_rows($q);
    if($rn > 0){
        $q1 = mysqli_query($con, "SELECT password from users where email = '$email'");
        $row = mysqli_fetch_array($q1);
        $dec_pass = decr($row["password"]);
        echo alert("your password is {$dec_pass}");
    }
    else{
        echo alert("Invali email or secretkey");
    }
}
?>
<html lang="en">
    <head>
        <title>Comment Page</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link type="text/css" rel="stylesheet" href="style.css">
    </head>
    <body>
        <div class="cardmain">
            <div class="cardbody">
                <div class="headmain">
                <h1 class="heading">Forgot Password</h1>
                </div>
                <form action="" method="post">
                    <div class="fg">
                    <label>Email</label>
                    <input type="text" class="form-control" name ="email">
                    </div>
                    <div class="fg">
                    <label>Secret Key</label>
                    <input type="password" class="form-control" name="secretkey">
                    </div>
                    <div class="fg">
                    <button type="submit" class="btn btn-primary btn-block" name="reset">Sign In</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>