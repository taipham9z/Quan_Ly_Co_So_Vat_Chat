package Class;

public class KhoLuuTru {
    private String maKho;
    private String tenKho;
    private String maBoPhan;
    private String ghiChu;

    public KhoLuuTru() {
    }

    public KhoLuuTru(String maKho, String tenKho, String maBoPhan, String ghiChu) {
        this.maKho = maKho;
        this.tenKho = tenKho;
        this.maBoPhan = maBoPhan;
        this.ghiChu = ghiChu;
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public String getTenKho() {
        return tenKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public String getMaBoPhan() {
        return maBoPhan;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public void setMaBoPhan(String maBoPhan) {
        this.maBoPhan = maBoPhan;
    }

    @Override
    public String toString() {
        return "\n" + getMaKho() + "|" + getTenKho() + "|" + getMaBoPhan() + "|" + getGhiChu();
    }
}
