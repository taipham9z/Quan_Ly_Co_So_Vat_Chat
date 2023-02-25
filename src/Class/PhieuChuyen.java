package Class;

public class PhieuChuyen {
    private String maPhieu;
    private String maTsChuyen;
    private String giaoVienChuyen;
    private String ngayChuyen;
    private String noiNhan;
    private int soLuong;

    public PhieuChuyen() {
    }

    public PhieuChuyen(String maPhieu, String maTsChuyen, String giaoVienChuyen, String ngayChuyen, String noiNhan, int soLuong) {
        this.maPhieu = maPhieu;
        this.maTsChuyen = maTsChuyen;
        this.giaoVienChuyen = giaoVienChuyen;
        this.ngayChuyen = ngayChuyen;
        this.noiNhan = noiNhan;
        this.soLuong = soLuong;
    }

    public String getMaTsChuyen() {
        return maTsChuyen;
    }

    public void setMaTsChuyen(String maTsChuyen) {
        this.maTsChuyen = maTsChuyen;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getGiaoVienChuyen() {
        return giaoVienChuyen;
    }

    public void setGiaoVienChuyen(String giaoVienChuyen) {
        this.giaoVienChuyen = giaoVienChuyen;
    }

    public String getNgayChuyen() {
        return ngayChuyen;
    }

    public void setNgayChuyen(String ngayChuyen) {
        this.ngayChuyen = ngayChuyen;
    }

    public String getNoiNhan() {
        return noiNhan;
    }

    public void setNoiNhan(String noiNhan) {
        this.noiNhan = noiNhan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "\n" + getMaPhieu() + "|" + getMaTsChuyen() + "|" + getGiaoVienChuyen() + "|" + getNgayChuyen() + "|" + getNoiNhan() + "|" + getSoLuong();

    }
}
