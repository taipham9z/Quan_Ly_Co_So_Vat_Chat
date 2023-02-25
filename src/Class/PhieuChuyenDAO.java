package Class;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuChuyenDAO {
    List<PhieuChuyen> phieuChuyenList = new ArrayList<>();
    List<PhieuChuyen> phieuChuyens;
    public List<PhieuChuyen> getPhieuChuyenList(){
        return phieuChuyenList;
    }
    public static final String DATA_FILE_PATH= "src\\Data\\PhieuChuyen.txt";
    File file = new File(DATA_FILE_PATH);
    String absolutePath = file.getAbsolutePath();
    public void replacePhieuChuyen(PhieuChuyen phieuChuyen, int n) throws IOException {
        BufferedReader reader = null;
        phieuChuyens = new ArrayList<>();
        try {
            PhieuChuyen x = null;
            reader = new BufferedReader(new FileReader(absolutePath));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new PhieuChuyen(a[0], a[1], a[2], a[3], a[4], Integer.parseInt(a[5]));
                phieuChuyens.add(x);
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
        phieuChuyens.set(n, phieuChuyen);
        BufferedWriter writer = null;
        try{
            File file = new File(absolutePath);
            //writer = new FileWriter(file, true);
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < phieuChuyens.size(); i++) {
                String w;
                if(i==0){
                    w = phieuChuyens.get(i).getMaPhieu() + "|" + phieuChuyens.get(i).getMaTsChuyen() + "|" + phieuChuyens.get(i).getGiaoVienChuyen() + "|" + phieuChuyens.get(i).getNgayChuyen() + "|" + phieuChuyens.get(i).getNoiNhan() + "|" + phieuChuyens.get(i).getSoLuong();
                }
                else{
                    w =  "\n" + phieuChuyens.get(i).getMaPhieu() + "|" + phieuChuyens.get(i).getMaTsChuyen() + "|" + phieuChuyens.get(i).getGiaoVienChuyen() + "|" + phieuChuyens.get(i).getNgayChuyen() + "|" + phieuChuyens.get(i).getNoiNhan() + "|" + phieuChuyens.get(i).getSoLuong();
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
    public void deletePhieuChuyen(int n) throws IOException {
        BufferedReader reader = null;
        phieuChuyens = new ArrayList<>();
        try {
            PhieuChuyen x = null;
            reader = new BufferedReader(new FileReader(absolutePath));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new PhieuChuyen(a[0], a[1], a[2], a[3], a[4], Integer.parseInt(a[5]));
                phieuChuyens.add(x);
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
        phieuChuyens.remove(n);
        BufferedWriter writer = null;
        try{
            File file = new File(absolutePath);
            //writer = new FileWriter(file, true);
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
            writer.close();
        }
    }
    public void writeFile(PhieuChuyen phieuChuyen) throws IOException {
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
