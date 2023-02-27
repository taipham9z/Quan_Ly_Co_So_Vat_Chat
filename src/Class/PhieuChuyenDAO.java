package Class;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuChuyenDAO {
    List<PhieuChuyen> phieuChuyenList = new ArrayList<>();
    public List<PhieuChuyen> getPhieuChuyenList(){
        return phieuChuyenList;
    }
    public static final String DATA_FILE_PATH= "src\\Data\\PhieuChuyen.txt";
    File file = new File(DATA_FILE_PATH);
    String absolutePath = file.getAbsolutePath();
    public void replacePhieuChuyen(PhieuChuyen phieuChuyen, int n) throws IOException {
        phieuChuyenList.set(n, phieuChuyen);
        writeFile(phieuChuyenList, absolutePath);
    }
    public void deletePhieuChuyen(int n) throws IOException {
        phieuChuyenList.remove(n);
        writeFile(phieuChuyenList, absolutePath);
    }
    public void writeFile(List<PhieuChuyen> phieuChuyens, String absolutePath){
        BufferedWriter writer = null;
        try{
            File file = new File(absolutePath);
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < phieuChuyens.size(); i++) {
                if(i==0){
                    String w = phieuChuyens.get(i).getMaPhieu() + "|" + phieuChuyens.get(i).getMaTsChuyen() + "|" + phieuChuyens.get(i).getGiaoVienChuyen() + "|" + phieuChuyens.get(i).getNgayChuyen() + "|" + phieuChuyens.get(i).getNoiNhan() + "|" + phieuChuyens.get(i).getSoLuong();
                    writer.write(w);
                }
                else{
                    String w = "\n" + phieuChuyens.get(i).getMaPhieu() + "|" + phieuChuyens.get(i).getMaTsChuyen() + "|" + phieuChuyens.get(i).getGiaoVienChuyen() + "|" + phieuChuyens.get(i).getNgayChuyen() + "|" + phieuChuyens.get(i).getNoiNhan() + "|" + phieuChuyens.get(i).getSoLuong();
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
    public void writeFileAppend(PhieuChuyen phieuChuyen) throws IOException {
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            File file = new File(absolutePath);
            //writer = new FileWriter(file, true);
            writer = new BufferedWriter(new FileWriter(file, true));
            reader = new BufferedReader(new FileReader(file));
            if(reader.readLine() != null){
                writer.write(phieuChuyen.toString());
            }
            else {
                String w = phieuChuyen.getMaPhieu() + "|" + phieuChuyen.getMaTsChuyen() + "|" + phieuChuyen.getGiaoVienChuyen() + "|" + phieuChuyen.getNgayChuyen() + "|" + phieuChuyen.getNoiNhan() + "|" + phieuChuyen.getSoLuong();
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
