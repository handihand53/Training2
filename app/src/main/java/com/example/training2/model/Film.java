package com.example.training2.model;

public class Film {
    private String judul;
    private String tahun;

    public Film(){

    }

    public Film (String judul, String tahun){
        this.judul = judul;
        this.tahun = tahun;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }




}
