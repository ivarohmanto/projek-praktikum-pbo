package model;

import interfacepegawai.PegawaiInterface;

public class PegawaiKontrak extends Pegawai
implements PegawaiInterface {

    private double potongan;

    public PegawaiKontrak(int id, String nama,
            String jabatan,
            double gaji,
            double potongan) {

        super(id,nama,jabatan,gaji);
        this.potongan = potongan;
    }

    @Override
    public double hitungGaji() {
        return gaji - potongan;
    }
}