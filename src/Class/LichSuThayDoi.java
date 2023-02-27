package Class;

public class LichSuThayDoi {
    private String noiDung;
    private String thoiGian;
    private String ghiChu;

    public LichSuThayDoi(String noiDung, String thoiGian, String ghiChu) {
        this.noiDung = noiDung;
        this.thoiGian = thoiGian;
        this.ghiChu = ghiChu;
    }

    public LichSuThayDoi() {
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
