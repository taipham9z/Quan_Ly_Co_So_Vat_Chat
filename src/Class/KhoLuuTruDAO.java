package Class;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KhoLuuTruDAO {
    List<KhoLuuTru> khoLuuTruList = new ArrayList<>();
    public List<KhoLuuTru> getKhoLuuTruList(){
        return khoLuuTruList;
    }
    public static final String DATA_FILE_PATH = "src\\Data\\KhoLuuTru.txt";
    File file = new File(DATA_FILE_PATH);
    String absolutePath = file.getAbsolutePath();
    public void replaceKhoLuuTru(KhoLuuTru khoLuuTru, int n) throws IOException {
        khoLuuTruList.set(n, khoLuuTru);
        writeFile(khoLuuTruList, absolutePath);
    }
    public void deleteKhoLuuTru(int n) throws IOException {
        khoLuuTruList.remove(n);
        writeFile(khoLuuTruList, absolutePath);
    }
    public void writeFile(List<KhoLuuTru> khoLuuTrus, String absolutePath){
        BufferedWriter writer = null;
        try{
            File file = new File(absolutePath);
            //writer = new FileWriter(file, true);
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < khoLuuTrus.size(); i++) {
                String w;
                if(i==0){
                    w = khoLuuTrus.get(i).getMaKho() + "|" + khoLuuTrus.get(i).getTenKho() + "|" + khoLuuTrus.get(i).getMaBoPhan() + "|" + khoLuuTrus.get(i).getGhiChu();
                }
                else{
                    w =  "\n" + khoLuuTrus.get(i).getMaKho() + "|" + khoLuuTrus.get(i).getTenKho() + "|" + khoLuuTrus.get(i).getMaBoPhan() + "|" + khoLuuTrus.get(i).getGhiChu();
                }
                writer.write(w);
            }
            writer.flush();
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
    public void writeFileAppend(KhoLuuTru khoLuuTru) throws IOException {
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            File file = new File(absolutePath);
            //writer = new FileWriter(file, true);
            writer = new BufferedWriter(new FileWriter(file, true));
            reader = new BufferedReader(new FileReader(file));
            if(reader.readLine() != null){
                writer.write(khoLuuTru.toString());
            }
            else {
                String w = khoLuuTru.getMaKho() + "|" + khoLuuTru.getTenKho() + "|" + khoLuuTru.getMaBoPhan() + "|" + khoLuuTru.getGhiChu();
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
