/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

/**
 *
 * @author Administrator
 */
public class DoanhThu {

    private String Thang;
    private int Tien;

    public DoanhThu() {
    }

    public DoanhThu(String Thang, int Tien) {
        this.Thang = Thang;
        this.Tien = Tien;
    }

    public String getThang() {
        return Thang;
    }

    public void setThang(String Thang) {
        this.Thang = Thang;
    }

    public int getTien() {
        return Tien;
    }

    public void setTien(int Tien) {
        this.Tien = Tien;
    }

}
