package edu.uef.baitap;

import java.io.Serializable;

public class BaiTap implements Serializable {
    private int id;
    private String tenMonHoc;
    private String date;
    private String tenBaiTap;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public BaiTap(int id, String tenMonHoc, String date, String tenBaiTap, byte[] image, String thoiHan, String chungLoai, int trangthai, int idUser) {
        this.id = id;
        this.tenMonHoc = tenMonHoc;
        this.date = date;
        this.tenBaiTap = tenBaiTap;
        this.image = image;
        this.thoiHan = thoiHan;
        this.chungLoai = chungLoai;
        this.trangthai = trangthai;
        this.idUser = idUser;
    }

    public BaiTap(String tenMonHoc, String date, String tenBaiTap, byte[] image, String thoiHan, String chungLoai, int trangthai, int idUser) {
        this.tenMonHoc = tenMonHoc;
        this.date = date;
        this.tenBaiTap = tenBaiTap;
        this.image = image;
        this.thoiHan = thoiHan;
        this.chungLoai = chungLoai;
        this.trangthai = trangthai;
        this.idUser = idUser;
    }

    private byte[] image;

    public BaiTap(int id, String tenMonHoc, String date, String tenBaiTap, String thoiHan, String chungLoai, int trangthai, int idUser) {
        this.id = id;
        this.tenMonHoc = tenMonHoc;
        this.date = date;
        this.tenBaiTap = tenBaiTap;
        this.thoiHan = thoiHan;
        this.chungLoai = chungLoai;
        this.trangthai = trangthai;
        this.idUser = idUser;
    }

    private String thoiHan;
    private String chungLoai;
    private int trangthai;
    private int idUser;

    public BaiTap(String tenMonHoc, String date, String tenBaiTap, String thoiHan, String chungLoai, int trangthai, int idUser) {
        this.tenMonHoc = tenMonHoc;
        this.date = date;
        this.tenBaiTap = tenBaiTap;
        this.thoiHan = thoiHan;
        this.chungLoai = chungLoai;
        this.trangthai = trangthai;
        this.idUser = idUser;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }



    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }



    public BaiTap() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTenBaiTap() {
        return tenBaiTap;
    }

    public void setTenBaiTap(String tenBaiTap) {
        this.tenBaiTap = tenBaiTap;
    }

    public String getThoiHan() {
        return thoiHan;
    }

    public void setThoiHan(String thoiHan) {
        this.thoiHan = thoiHan;
    }

    public String getChungLoai() {
        return chungLoai;
    }

    public void setChungLoai(String chungLoai) {
        this.chungLoai = chungLoai;
    }
}
