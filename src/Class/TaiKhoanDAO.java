package Class;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {
    List<TaiKhoan> taiKhoanList = new ArrayList<>();
    List<TaiKhoan> taiKhoans;
    public List<TaiKhoan> getTaiKhoanList(){
        return taiKhoanList;
    }
    public static final String DATA_FILE_PATH = "src\\Data\\TaiKhoanGiaoVien.txt";
    File file = new File(DATA_FILE_PATH);
    String absolutePath = file.getAbsolutePath();
    public void replaceTaiKhoan(TaiKhoan taiKhoan, int n) throws IOException {
        BufferedReader reader = null;
        taiKhoans = new ArrayList<>();
        try {
            TaiKhoan x = null;
            reader = new BufferedReader(new FileReader(absolutePath));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new TaiKhoan(a[0], a[1], a[2], a[3], a[4], a[5], a[6]);
                taiKhoans.add(x);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        taiKhoans.set(n, taiKhoan);
        BufferedWriter writer = null;
        try{
            File file = new File(absolutePath);
            //writer = new FileWriter(file, true);
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < taiKhoans.size(); i++) {
                String w;
                if(i==0){
                    w = taiKhoans.get(i).getTenDangNhap() + "|" + taiKhoans.get(i).getMatKhau()+ "|" + taiKhoans.get(i).getTenGiaoVien() + "|" + taiKhoans.get(i).getPhongQuanLy() + "|" + taiKhoans.get(i).getNgayTao() + "|" + taiKhoans.get(i).getTrangThai() + "|" + taiKhoans.get(i).getNhiemVu();
                }
                else{
                    w =  "\n" + taiKhoans.get(i).getTenDangNhap() + "|" + taiKhoans.get(i).getMatKhau() + "|" + taiKhoans.get(i).getTenGiaoVien()  + "|" + taiKhoans.get(i).getPhongQuanLy() + "|" + taiKhoans.get(i).getNgayTao() + "|" + taiKhoans.get(i).getTrangThai() + "|" + taiKhoans.get(i).getNhiemVu();
                }
                writer.write(w);
            }
            writer.flush();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            writer.close();
        }
    }
    public void deleteTaiKhoan(int n) throws IOException {
        BufferedReader reader = null;
        taiKhoans = new ArrayList<>();
        try {
            TaiKhoan x = null;
            reader = new BufferedReader(new FileReader(absolutePath));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new TaiKhoan(a[0], a[1], a[2], a[3], a[4], a[5], a[6]);
                taiKhoans.add(x);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        taiKhoans.remove(n);
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
            writer.close();
        }
    }
    public void writeFile(TaiKhoan taiKhoan) throws IOException {
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
