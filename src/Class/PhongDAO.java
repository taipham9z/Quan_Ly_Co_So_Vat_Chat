package Class;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhongDAO {
    List<Phong> phongList = new ArrayList<>(); //collection
    public List<Phong> getPhongList(){ //get collection
        return phongList;
    }
    public static final String DATA_FILE_PATH = "src\\Data\\Phong.txt"; //Đường dẫn tới file
    File file = new File(DATA_FILE_PATH); //tạo file theo đường dẫn
    String absolutePath = file.getAbsolutePath(); //lấy đường dẫn đầy đủ
    public void replacePhong(Phong phong, int n) throws IOException { // Sửa 1 CSVC
        phongList.set(n, phong);
        writeFile(phongList, absolutePath);
    }
    //sử dụng để xoá 1 CSVC được lưu trong file
    public void deletePhong(int n) throws IOException {
        phongList.remove(n);
        writeFile(phongList, absolutePath);
    }
    //xuất danh sách CSVC ra file
    public void writeFileAppend(Phong phong, String absolutePath) throws IOException {
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            File file = new File(absolutePath);
            writer = new BufferedWriter(new FileWriter(file, true));
            reader = new BufferedReader(new FileReader(file));
            if(reader.readLine() != null){
                writer.write(phong.toString());
            }
            else {
                String w = phong.getTenPhong() + "|" + phong.getMaPhong() +"|" + phong.getGiaoVienQL() + "|" + phong.getViTri();
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
    public void writeFile(List<Phong> phongs, String absolutePath){
        BufferedWriter writer = null;
        try{
            File file = new File(absolutePath);
            //writer = new FileWriter(file, true);
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < phongs.size(); i++) {
                if(i==0){
                    String w = phongs.get(i).getTenPhong() + "|" + phongs.get(i).getMaPhong() + "|" + phongs.get(i).getGiaoVienQL() + "|" + phongs.get(i).getViTri();
                    writer.write(w);
                }
                else{
                    String w = "\n" + phongs.get(i).getTenPhong()+ "|" + phongs.get(i).getMaPhong()+ "|" + phongs.get(i).getGiaoVienQL() + "|"+ phongs.get(i).getViTri();
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
