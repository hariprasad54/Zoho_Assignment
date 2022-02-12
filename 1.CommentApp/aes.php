<?php
function encr($text){
    $cipher = "AES-128-CTR";
    $iv_length = openssl_cipher_iv_length($cipher);
    $opt = 0;
    $enc_iv = '9999888877776666';
    $enc_key = "CommentApplication";
    $enc_text = openssl_encrypt($text, $cipher,$enc_key, $opt, $enc_iv);
    return $enc_text;
}
function decr($text){
    $dec_iv = '9999888877776666';
    $d_key = "CommentApplication";
    $cipher = "AES-128-CTR";
    $opt = 0;
    $dec_text=openssl_decrypt ($text, $cipher,$d_key, $opt, $dec_iv);
    return $dec_text;
}
?>