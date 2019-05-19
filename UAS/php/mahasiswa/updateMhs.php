<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
    $id = $_POST['id'];
    $name = $_POST['name'];
    $nim = $_POST['nim'];
    $alamat = $_POST['alamat'];

    require_once('koneksi.php');

    $sql = "UPDATE tb_mahasiswa SET nama = '$name', nim = '$nim', alamat = '$alamat' WHERE id = '$id';";

    if(mysqli_query($con,$sql)){
        echo 'Berhasil Update Data Mahasiswa';
    }else{
        echo 'Gagal Update Data Pegawai';
    }
    mysqli_close($con);
}
?>