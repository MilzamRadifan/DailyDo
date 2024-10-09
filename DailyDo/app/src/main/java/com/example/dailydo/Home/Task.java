package com.example.dailydo.Home;

public class Task {
  String judul, tanggal, keterangan;
  int gambar;

  public Task(String judul, String tanggal, String keterangan, int gambar) {
    this.judul = judul;
    this.tanggal = tanggal;
    this.keterangan = keterangan;
    this.gambar = gambar;
  }

  public String getJudul() {
    return judul;
  }

  public void setJudul(String judul) {
    this.judul = judul;
  }

  public String getTanggal() {
    return tanggal;
  }

  public void setTanggal(String tanggal) {
    this.tanggal = tanggal;
  }

  public int getGambar() {
    return gambar;
  }

  public void setGambar(int gambar) {
    this.gambar = gambar;
  }

  public String getKeterangan() {
    return keterangan;
  }

  public void setKeterangan(String keterangan) {
    this.keterangan = keterangan;
  }
}
