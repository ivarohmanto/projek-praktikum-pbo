package controller;

import dao.PegawaiDAO;

public class PegawaiController {

    PegawaiDAO dao = new PegawaiDAO();

    public void tambahData(
            String nama,
            String jabatan,
            String tipe,
            double gaji) {

        dao.tambahPegawai(
                nama,
                jabatan,
                tipe,
                gaji
        );
    }
}