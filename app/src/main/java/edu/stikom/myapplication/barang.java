package edu.stikom.myapplication;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity public class barang {
    @Id (autoincrement = true) private Long idbarang;
    @Index (unique = true) private String nama;
    private int harga;

    //stop klik palu diatas, biar muncul tulisan dibawah!
    @Generated(hash = 315776812)
    public barang(Long idbarang, String nama, int harga) {
        this.idbarang = idbarang;
        this.nama = nama;
        this.harga = harga;
    }
    @Generated(hash = 744589180)
    public barang() {
    }
    public Long getIdbarang() {
        return this.idbarang;
    }
    public void setIdbarang(Long idbarang) {
        this.idbarang = idbarang;
    }
    public String getNama() {
        return this.nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public int getHarga() {
        return this.harga;
    }
    public void setHarga(int harga) {
        this.harga = harga;
    }
}
