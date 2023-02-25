package Class;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVCTheoKhoDAO {
    //Các file DAO y hịt nhau, giải thích tương tự
    List<CSVCTheoKho> CSVCTheoKhoList = new ArrayList<>();
    List<CSVCTheoKho> CSVCTheoKhos;
    public List<CSVCTheoKho> getTaiSanList(){
        return CSVCTheoKhoList;
    }
    //14 15 16 là đường dẫn mặc định cho file
    public static final String DATA_FILE_PATH = "src\\Data\\CSVC.txt";
    File file = new File(DATA_FILE_PATH);
    String absolutePath = file.getAbsolutePath();
    //thay thế CSVC, dùng để sửa danh sách CSVC khi có sự thay đổi
    public void replaceCSVC(CSVCTheoKho CSVCTheoKho, int n) throws IOException {
        BufferedReader reader = null;
        CSVCTheoKhos = new ArrayList<>();
        try {
            CSVCTheoKho x = null;
            reader = new BufferedReader(new FileReader(absolutePath));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new CSVCTheoKho(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], Integer.parseInt(a[8]), Integer.parseInt(a[9]));
                CSVCTheoKhos.add(x);
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
        CSVCTheoKhos.set(n, CSVCTheoKho);
        BufferedWriter writer = null;
        try{
            File file = new File(absolutePath);
            //writer = new FileWriter(file, true);
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < CSVCTheoKhos.size(); i++) {
                String w;
                if(i==0){
                    w = CSVCTheoKhos.get(i).getMaTs() + "|" + CSVCTheoKhos.get(i).getTenTs() + "|" + CSVCTheoKhos.get(i).getTinhTrang() + "|" + CSVCTheoKhos.get(i).getNuocSx() + "|" + CSVCTheoKhos.get(i).getNamSx() + "|" + CSVCTheoKhos.get(i).getDonViTinh() + "|" + CSVCTheoKhos.get(i).getNoiLuuTru() + "|" + CSVCTheoKhos.get(i).getThongSoKiThuat() + "|" + CSVCTheoKhos.get(i).getDonGia() + "|" + CSVCTheoKhos.get(i).getSoLuong();
                }
                else{
                    w =  "\n" + CSVCTheoKhos.get(i).getMaTs() + "|" + CSVCTheoKhos.get(i).getTenTs() + "|" + CSVCTheoKhos.get(i).getTinhTrang() + "|" + CSVCTheoKhos.get(i).getNuocSx() + "|" + CSVCTheoKhos.get(i).getNamSx() + "|" + CSVCTheoKhos.get(i).getDonViTinh() + "|" + CSVCTheoKhos.get(i).getNoiLuuTru() + "|" + CSVCTheoKhos.get(i).getThongSoKiThuat() + "|" + CSVCTheoKhos.get(i).getDonGia() + "|" + CSVCTheoKhos.get(i).getSoLuong();
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
    //sử dụng để xoá 1 CSVC được lưu trong file
    public void deleteCSVC(int n) throws IOException {
        BufferedReader reader = null;
        CSVCTheoKhos = new ArrayList<>();
        try {
            CSVCTheoKho x = null;
            reader = new BufferedReader(new FileReader(absolutePath));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new CSVCTheoKho(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], Integer.parseInt(a[8]), Integer.parseInt(a[9]));
                CSVCTheoKhos.add(x);
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
        CSVCTheoKhos.remove(n);
        BufferedWriter writer = null;
        try{
            File file = new File(absolutePath);
            //writer = new FileWriter(file, true);
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < CSVCTheoKhos.size(); i++) {
                if(i==0){
                    String w = CSVCTheoKhos.get(i).getMaTs() + "|" + CSVCTheoKhos.get(i).getTenTs() + "|" + CSVCTheoKhos.get(i).getTinhTrang() + "|" + CSVCTheoKhos.get(i).getNuocSx() + "|" + CSVCTheoKhos.get(i).getNamSx() + "|" + CSVCTheoKhos.get(i).getDonViTinh() + "|" + CSVCTheoKhos.get(i).getNoiLuuTru() + "|" + CSVCTheoKhos.get(i).getThongSoKiThuat() + "|" + CSVCTheoKhos.get(i).getDonGia() + "|" + CSVCTheoKhos.get(i).getSoLuong();
                    writer.write(w);
                }
                else{
                    String w = "\n" + CSVCTheoKhos.get(i).getMaTs() + "|" + CSVCTheoKhos.get(i).getTenTs() + "|" + CSVCTheoKhos.get(i).getTinhTrang() + "|" + CSVCTheoKhos.get(i).getNuocSx() + "|" + CSVCTheoKhos.get(i).getNamSx() + "|" + CSVCTheoKhos.get(i).getDonViTinh() + "|" + CSVCTheoKhos.get(i).getNoiLuuTru() + "|" + CSVCTheoKhos.get(i).getThongSoKiThuat() + "|" + CSVCTheoKhos.get(i).getDonGia() + "|" + CSVCTheoKhos.get(i).getSoLuong();
                    writer.write(w);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            writer.close();
        }
    }
    //xuất danh sách CSVC ra file
    public void writeFile(CSVCTheoKho CSVCTheoKho) throws IOException {
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            File file = new File(absolutePath);
            //writer = new FileWriter(file, true);
            writer = new BufferedWriter(new FileWriter(file, true));
            reader = new BufferedReader(new FileReader(file));
            if(reader.readLine() != null){
                writer.write(CSVCTheoKho.toString());
            }
            else {
                String w = CSVCTheoKho.getMaTs() + "|" + CSVCTheoKho.getTenTs() + "|" + CSVCTheoKho.getTinhTrang() + "|" + CSVCTheoKho.getNuocSx() + "|" + CSVCTheoKho.getNamSx() + "|" + CSVCTheoKho.getDonViTinh() + "|" + CSVCTheoKho.getNoiLuuTru() + "|" + CSVCTheoKho.getThongSoKiThuat() + "|" + CSVCTheoKho.getDonGia() + "|" + CSVCTheoKho.getSoLuong();
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
