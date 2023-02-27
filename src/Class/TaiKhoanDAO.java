package Class;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {
    List<TaiKhoan> taiKhoanList = new ArrayList<>();
    public List<TaiKhoan> getTaiKhoanList(){
        return taiKhoanList;
    }
    public static final String DATA_FILE_PATH = "src\\Data\\TaiKhoanGiaoVien.txt";
    File file = new File(DATA_FILE_PATH);
    String absolutePath = file.getAbsolutePath();
    public void replaceTaiKhoan(TaiKhoan taiKhoan, int n) throws IOException {
        taiKhoanList.set(n, taiKhoan);
        writeFile(taiKhoanList, absolutePath);
    }
    public void deleteTaiKhoan(int n) throws IOException {
        taiKhoanList.remove(n);
        writeFile(taiKhoanList, absolutePath);
    }
    public void writeFile(List<TaiKhoan> taiKhoans, String absolutePath){
        BufferedWriter writer = null;
        try{
            File file = new File(absolutePath);
            //writer = new FileWriter(file, true);
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < taiKhoans.size(); i++) {
                if(i==0){
                    String w = taiKhoans.get(i).getTenDangNhap() + "|" + taiKhoans.get(i).getMatKhau() + "|" + taiKhoans.get(i).getTenGiaoVien()  + "|" + taiKhoans.get(i).getPhongQuanLy() + "|" + taiKhoans.get(i).getNgayTao() + "|" + taiKhoans.get(i).getTrangThai() + "|" + taiKhoans.get(i).getNhiemVu();
                    writer.write(w);
                }
                else{
                    String w = "\n" + taiKhoans.get(i).getTenDangNhap() + "|" + taiKhoans.get(i).getMatKhau()+ "|" + taiKhoans.get(i).getTenGiaoVien()  + "|" + taiKhoans.get(i).getPhongQuanLy() + "|" + taiKhoans.get(i).getNgayTao() + "|" + taiKhoans.get(i).getTrangThai() + "|" + taiKhoans.get(i).getNhiemVu();
                    writer.write(w);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void writeFileAppend(TaiKhoan taiKhoan) throws IOException {
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            File file = new File(absolutePath);
            //writer = new FileWriter(file, true);
            writer = new BufferedWriter(new FileWriter(file, true));
            reader = new BufferedReader(new FileReader(file));
            if(reader.readLine() != null){
                writer.write(taiKhoan.toString());
            }
            else {
                String w = taiKhoan.getTenDangNhap() + "|" + taiKhoan.getMatKhau()+ "|" + taiKhoan.getTenGiaoVien() + "|" + taiKhoan.getPhongQuanLy() + "|" + taiKhoan.getNgayTao() + "|" + taiKhoan.getTrangThai() + "|" + taiKhoan.getNhiemVu();
                writer.write(w);
            }
        } catch (Exception ex) {
            System.out.println("Tạo file test_text.txt có vấn đề: " + ex.getMessage());
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.getLocalizedMessage();
            } finally {
                writer.close();
            }
        }
    }
}
