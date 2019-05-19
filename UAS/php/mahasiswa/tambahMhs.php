<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
    $name = $_POST['name'];
    $nim = $_POST['nim'];
    $alamat = $_POST['alamat'];

    $sql = "INSERT INTO tb_mahasiswa (nama,nim,alamat) VALUES ('$name','$nim','$alamat')";

    require_once('koneksi.php');

    if(mysqli_query($con,$sql)){
        echo 'Berhasil Menambah Mahasiswa';
    }else{
        echo 'Gagal Menambah Mahasiswa';
    }

    mysqli_close($con);
}
?>