package Class;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TaiKhoan {
    private String tenDangNhap;
    private String matKhau;
    private String tenGiaoVien;
    private String phongQuanLy;
    private String ngayTao;
    private String trangThai;
    private String nhiemVu;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenDangNhap, String matKhau, String tenGiaoVien, String phongQuanLy, String ngayTao, String trangThai, String nhiemVu) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.tenGiaoVien = tenGiaoVien;
        this.phongQuanLy = phongQuanLy;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.nhiemVu = nhiemVu;
    }

    public String getTenGiaoVien() {
        return tenGiaoVien;
    }

    public void setTenGiaoVien(String tenGiaoVien) {
        this.tenGiaoVien = tenGiaoVien;
    }

    public String getNhiemVu() {
        return nhiemVu;
    }

    public void setNhiemVu(String nhiemVu) {
        this.nhiemVu = nhiemVu;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getPhongQuanLy() {
        return phongQuanLy;
    }

    public void setPhongQuanLy(String phongQuanLy) {
        this.phongQuanLy = phongQuanLy;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "\n" + getTenDangNhap() + "|" + getMatKhau() + "|" + getTenGiaoVien()  + "|" + getPhongQuanLy() + "|" + getNgayTao() + "|" + getTrangThai() + "|" + getNhiemVu();
    }

    public static final String DATA_FILE_PATH_QUAN_TRI = "src\\Data\\TaiKhoanQuanTri.txt";
    File file = new File(DATA_FILE_PATH_QUAN_TRI);
    String absolutePath = file.getAbsolutePath();
    public int readFileTaiKhoanQuanTri(){
        BufferedReader reader = null;
        int check = 0, i;
        try{
            reader = new BufferedReader(new FileReader(absolutePath));
            String line = "";
            while ((line = reader.readLine()) != null){
                check = 0;
                String[] split = line.split("\\|");
                if(tenDangNhap.equals(split[0]) && matKhau.equals(split[1])){
                    check = 1;
                    return check;
                }
            }
            return check;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 1;
    }
}
