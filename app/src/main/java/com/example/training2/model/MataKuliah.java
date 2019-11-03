package com.example.training2.model;

public class MataKuliah {

    private String matkul;
    private String namaDosen;
    private int sks;

    public MataKuliah(){ }

    public MataKuliah(String matkul, String namaDosen, int sks) {
        this.matkul=matkul;
        this.sks=sks;
        this.namaDosen=namaDosen;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }
}
