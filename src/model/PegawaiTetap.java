package model;

import interfacepegawai.PegawaiInterface;

public class PegawaiTetap extends Pegawai
implements PegawaiInterface {

    private double bonus;

    public PegawaiTetap(int id, String nama,
            String jabatan,
            double gaji,
            double bonus) {

        super(id,nama,jabatan,gaji);
        this.bonus = bonus;
    }

    @Override
    public double hitungGaji() {
        return gaji + bonus;
    }
}