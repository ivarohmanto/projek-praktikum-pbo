CREATE DATABASE db_pegawai;

USE db_pegawai;

CREATE TABLE admin (
    username VARCHAR(50),
    password VARCHAR(50)
);

INSERT INTO admin VALUES ('admin','123');

CREATE TABLE pegawai (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nama VARCHAR(100),
    jabatan VARCHAR(50),
    tipe VARCHAR(30),
    gaji DOUBLE
);