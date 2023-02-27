package Class;

public class Phong {

    private String tenPhong;
    private String maPhong;
    private String giaoVienQL;
    private String viTri;

    public Phong() {
    }

    public Phong(String tenPhong, String maPhong, String giaoVienQL, String viTri) {
        this.tenPhong = tenPhong;
        this.maPhong = maPhong;
        this.giaoVienQL = giaoVienQL;
        this.viTri = viTri;
    }

    public String getGiaoVienQL() {
        return giaoVienQL;
    }

    public void setGiaoVienQL(String giaoVienQL) {
        this.giaoVienQL = giaoVienQL;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    @Override
    public String toString() {
        return "\n" + tenPhong + "|" + maPhong + "|" + giaoVienQL+ "|" + viTri;
    }
}
