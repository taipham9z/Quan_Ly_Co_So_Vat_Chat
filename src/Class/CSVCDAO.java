package Class;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
//Các lớp DAO chức năng tương tự nhau, gồm thêm, sửa, xoá, writeFile, writeFileAppend (Do mỗi loại đầu vào đầu ra khác nhau nên cần viết lại)
public class CSVCDAO {
    List<CSVC> CSVCList = new ArrayList<>(); //collection
    public List<CSVC> getTaiSanList(){ //get collection
        return CSVCList;
    }
    public static final String DATA_FILE_PATH = "src\\Data\\CSVC.txt"; //Đường dẫn tới file
    File file = new File(DATA_FILE_PATH); //tạo file theo đường dẫn
    String absolutePath = file.getAbsolutePath(); //lấy đường dẫn đầy đủ
    public void replaceCSVC(CSVC CSVC, int n) throws IOException { // Sửa 1 CSVC
        CSVCList.set(n, CSVC);
        writeFile(CSVCList, absolutePath);
    }
    public void deleteCSVC(int n) throws IOException { //Xoá 1 CSVC
        CSVCList.remove(n);
        writeFile(CSVCList, absolutePath);
    }
    //xuất danh sách CSVC ra file
    public void writeFileAppend(CSVC CSVC, String absolutePath) throws IOException { //ghi đè file
        BufferedWriter writer = null;
        BufferedReader reader = null;
        //bắt lỗi
        try {
            File file = new File(absolutePath); //tạo File
            writer = new BufferedWriter(new FileWriter(file, true)); //biến ghi file
            reader = new BufferedReader(new FileReader(file)); //biến đọc file
            if(reader.readLine() != null){ //check xem file rỗng ko
                writer.write(CSVC.toString()); //ghi csvc ra file
            }
            else { //nếu không rỗng thì ghi ra file như dưới (để đồng bộ kiểu lưu trong file, ko thì lỗi)
                String w = CSVC.getMaTs() + "|" + CSVC.getTenTs() + "|" + CSVC.getTinhTrang() + "|" + CSVC.getNuocSx() + "|" + CSVC.getNamSx() + "|" + CSVC.getDonViTinh() + "|" + CSVC.getNoiLuuTru() + "|" + CSVC.getThongSoKiThuat() + "|" + CSVC.getDonGia() + "|" + CSVC.getSoLuong();
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
                writer.close(); //đóng file
            }
        }
    }
    public void writeFile(List<CSVC> CSVCS, String absolutePath){ //ghi file
        BufferedWriter writer = null;
        try{
            File file = new File(absolutePath); //tạo file theo đường dẫn
            writer = new BufferedWriter(new FileWriter(file)); //biến ghi file
            for (int i = 0; i < CSVCS.size(); i++) { //vòng lặp, để ghi 1 list csvc
                if(i==0){
                    String w = CSVCS.get(i).getMaTs() + "|" + CSVCS.get(i).getTenTs() + "|" + CSVCS.get(i).getTinhTrang() + "|" + CSVCS.get(i).getNuocSx() + "|" + CSVCS.get(i).getNamSx() + "|" + CSVCS.get(i).getDonViTinh() + "|" + CSVCS.get(i).getNoiLuuTru() + "|" + CSVCS.get(i).getThongSoKiThuat() + "|" + CSVCS.get(i).getDonGia() + "|" + CSVCS.get(i).getSoLuong();
                    writer.write(w);
                }
                else{
                    String w = "\n" + CSVCS.get(i).getMaTs() + "|" + CSVCS.get(i).getTenTs() + "|" + CSVCS.get(i).getTinhTrang() + "|" + CSVCS.get(i).getNuocSx() + "|" + CSVCS.get(i).getNamSx() + "|" + CSVCS.get(i).getDonViTinh() + "|" + CSVCS.get(i).getNoiLuuTru() + "|" + CSVCS.get(i).getThongSoKiThuat() + "|" + CSVCS.get(i).getDonGia() + "|" + CSVCS.get(i).getSoLuong();
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
}
