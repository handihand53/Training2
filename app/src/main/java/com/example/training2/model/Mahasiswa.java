package com.example.training2.model;

public class Mahasiswa {
    private String nama;
    private String nim;
    private String notlp;

    public Mahasiswa(){

    }

    public Mahasiswa (String nama, String nim, String notlp){
        this.nama = nama;
        this.nim = nim;
        this.notlp = notlp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNotlp() {
        return notlp;
    }

    public void setNotlp(String notlp) {
        this.notlp = notlp;
    }


}
