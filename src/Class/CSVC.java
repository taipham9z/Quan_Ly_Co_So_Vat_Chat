package Class;

public class CSVC {
    private String maTs;
    private String tenTs;
    private String tinhTrang;
    private String nuocSx;
    private String namSx;
    private String donViTinh;
    private String noiLuuTru;
    private String thongSoKiThuat;
    private int donGia;
    private int soLuong;

    public CSVC() {

    }

    public CSVC(String maTs, String tenTs, String tinhTrang, String nuocSx, String namSx, String donViTinh, String noiLuuTru, String thongSoKiThuat, int donGia, int soLuong) {
        this.maTs = maTs;
        this.tenTs = tenTs;
        this.tinhTrang = tinhTrang;
        this.nuocSx = nuocSx;
        this.namSx = namSx;
        this.donViTinh = donViTinh;
        this.noiLuuTru = noiLuuTru;
        this.thongSoKiThuat = thongSoKiThuat;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public String getMaTs() {
        return maTs;
    }

    public void setMaTs(String maTs) {
        this.maTs = maTs;
    }

    public String getTenTs() {
        return tenTs;
    }

    public void setTenTs(String tenTs) {
        this.tenTs = tenTs;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getNuocSx() {
        return nuocSx;
    }

    public void setNuocSx(String nuocSx) {
        this.nuocSx = nuocSx;
    }

    public String getNamSx() {
        return namSx;
    }

    public void setNamSx(String namSx) {
        this.namSx = namSx;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getNoiLuuTru() {
        return noiLuuTru;
    }

    public void setNoiLuuTru(String noiLuuTru) {
        this.noiLuuTru = noiLuuTru;
    }

    public String getThongSoKiThuat() {
        return thongSoKiThuat;
    }

    public void setThongSoKiThuat(String thongSoKiThuat) {
        this.thongSoKiThuat = thongSoKiThuat;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "\n" + getMaTs() + "|" + getTenTs() + "|" + getTinhTrang() + "|" + getNuocSx() + "|" + getNamSx() + "|" + getDonViTinh() + "|" + getNoiLuuTru() + "|" + getThongSoKiThuat() + "|" + getDonGia() + "|" + getSoLuong();
    }
}
