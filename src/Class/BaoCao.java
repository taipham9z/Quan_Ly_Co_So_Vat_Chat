package Class;

import java.io.*;

public class BaoCao {
    private String noiDung;
    private String thoiGian;
    private String ghiChu;

    public BaoCao(String noiDung, String thoiGian, String ghiChu) {
        this.noiDung = noiDung;
        this.thoiGian = thoiGian;
        this.ghiChu = ghiChu;
    }

    public BaoCao() {
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
