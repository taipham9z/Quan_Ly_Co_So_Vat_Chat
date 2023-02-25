package Form;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Class.*;
public class CSVCTheoPhong extends JFrame{
    private JTable tblCSVCTheoPhong;
    private JComboBox comboboxPhong;
    private JButton btnTrangChu;
    private JPanel mainPanel;
    private JButton btnThem;
    private JButton btnXoa;
    private JComboBox comboBoxThem;
    private JButton btnTangSoLuong;
    private List<String> tenPhong;
    private List<String> maCSVC = new ArrayList<>();
    private static List<CSVCTheoKho> csvcTheoKhos;
    private CSVCTheoKho csvcSelected;
    private List<CSVCTheoKho> csvcTheoKhoList;
    private List<CSVCTheoKho> csvcDefault;
    BaoCao baoCao = new BaoCao();
    public CSVCTheoPhong(){
        setContentPane(mainPanel);
        setSize(1500, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        readFile("src\\Data\\Phong.txt");
        readFileCSVCDefault("src\\Data\\CSVC.txt");
        for (int i = 0; i < tenPhong.size(); i++) {
            comboboxPhong.addItem(tenPhong.get(i));
        }
        if(tenPhong.get(0).equals(comboboxPhong.getSelectedItem())){
            readFilePhong("src\\Data\\PhongThucHanh.txt");
            readFileCSVC("src\\Data\\CSVC.txt");
            comboBoxThem.removeAllItems();
            for (int i = 0; i < csvcTheoKhos.size(); i++) {
                for (int j = 0; j < csvcTheoKhoList.size(); j++) {
                    if(csvcTheoKhoList.get(j).getMaTs().equals(csvcTheoKhos.get(i).getMaTs())){
                        csvcTheoKhoList.remove(j);
                    }
                }
            }
            for (int i = 0; i < csvcTheoKhoList.size(); i++) {
                comboBoxThem.addItem(csvcTheoKhoList.get(i).getTenTs());
            }
        }
        if(tenPhong.get(1).equals(comboboxPhong.getSelectedItem())){
            readFilePhong("src\\Data\\PhongHocLyThuyet.txt");
            for (int i = 0; i < csvcTheoKhos.size(); i++) {
                maCSVC = new ArrayList<>();
                maCSVC.add(csvcTheoKhos.get(i).getMaTs());
            }
            readFileCSVC("src\\Data\\CSVC.txt");
            comboBoxThem.removeAllItems();
            for (int i = 0; i < csvcTheoKhos.size(); i++) {
                for (int j = 0; j < csvcTheoKhoList.size(); j++) {
                    if(csvcTheoKhoList.get(j).getMaTs().equals(csvcTheoKhos.get(i).getMaTs())){
                        csvcTheoKhoList.remove(j);
                    }
                }
            }
            for (int i = 0; i < csvcTheoKhoList.size(); i++) {
                comboBoxThem.addItem(csvcTheoKhoList.get(i).getTenTs());
            }
        }

        CSVCTheoPhongTable csvcTheoPhongTable = new CSVCTheoPhongTable();
        tblCSVCTheoPhong.setModel(csvcTheoPhongTable);
        tblCSVCTheoPhong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                csvcSelected = new CSVCTheoKho();
                csvcSelected = csvcTheoKhos.get(tblCSVCTheoPhong.getSelectedRow());
            }
        });
        comboboxPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tenPhong.get(0).equals(comboboxPhong.getSelectedItem())){
                    readFilePhong("src\\Data\\PhongThucHanh.txt");
                    for (int i = 0; i < csvcTheoKhos.size(); i++) {
                        maCSVC = new ArrayList<>();
                        maCSVC.add(csvcTheoKhos.get(i).getMaTs());
                    }
                    readFileCSVC("src\\Data\\CSVC.txt");
                    comboBoxThem.removeAllItems();
                    for (int i = 0; i < csvcTheoKhos.size(); i++) {
                        for (int j = 0; j < csvcTheoKhoList.size(); j++) {
                            if(csvcTheoKhoList.get(j).getMaTs().equals(csvcTheoKhos.get(i).getMaTs())){
                                csvcTheoKhoList.remove(j);
                            }
                        }
                    }
                    for (int i = 0; i < csvcTheoKhoList.size(); i++) {
                        comboBoxThem.addItem(csvcTheoKhoList.get(i).getTenTs());
                    }
                }
                if(tenPhong.get(1).equals(comboboxPhong.getSelectedItem())){
                    readFilePhong("src\\Data\\PhongHocLyThuyet.txt");
                    for (int i = 0; i < csvcTheoKhos.size(); i++) {
                        maCSVC = new ArrayList<>();
                        maCSVC.add(csvcTheoKhos.get(i).getMaTs());
                    }
                    readFileCSVC("src\\Data\\CSVC.txt");
                    comboBoxThem.removeAllItems();
                    for (int i = 0; i < csvcTheoKhos.size(); i++) {
                        for (int j = 0; j < csvcTheoKhoList.size(); j++) {
                            if(csvcTheoKhoList.get(j).getMaTs().equals(csvcTheoKhos.get(i).getMaTs())){
                                csvcTheoKhoList.remove(j);
                            }
                        }
                    }
                    for (int i = 0; i < csvcTheoKhoList.size(); i++) {
                        comboBoxThem.addItem(csvcTheoKhoList.get(i).getTenTs());
                    }
                }
                CSVCTheoPhongTable csvcTheoPhongTable = new CSVCTheoPhongTable();
                tblCSVCTheoPhong.setModel(csvcTheoPhongTable);
            }
        });

        btnTrangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrangChu().setVisible(true);
                dispose();
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < csvcDefault.size(); i++) {
                    if(csvcDefault.get(i).getTenTs().equals(comboBoxThem.getSelectedItem())){
                        csvcTheoKhos.add(csvcDefault.get(i));
                        String noiDung = "\nThêm CSVC " + csvcDefault.get(i).getTenTs() + "|" + java.time.LocalDate.now() + "|Không";
                        BufferedWriter bufferedWriter = null;
                        try {
                            bufferedWriter = new BufferedWriter(new FileWriter(new File("src\\Data\\BaoCao.txt").getAbsoluteFile(), true));
                            bufferedWriter.write(noiDung);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }finally {
                            try {
                                bufferedWriter.close();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
                CSVCTheoPhongTable csvcTheoPhongTable = new CSVCTheoPhongTable();
                tblCSVCTheoPhong.setModel(csvcTheoPhongTable);
                if(tenPhong.get(0).equals(comboboxPhong.getSelectedItem())){
                    writeFile("src\\Data\\PhongThucHanh.txt");
                }
                if(tenPhong.get(1).equals(comboboxPhong.getSelectedItem())){
                    writeFile("src\\Data\\PhongHocLyThuyet.txt");
                }
            }
        });
        btnTangSoLuong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                csvcTheoKhos.get(tblCSVCTheoPhong.getSelectedRow()).setSoLuong(csvcTheoKhos.get(tblCSVCTheoPhong.getSelectedRow()).getSoLuong() + 1);
                CSVCTheoPhongTable csvcTheoPhongTable = new CSVCTheoPhongTable();
                tblCSVCTheoPhong.setModel(csvcTheoPhongTable);
                if(tenPhong.get(0).equals(comboboxPhong.getSelectedItem())){
                    writeFile("src\\Data\\PhongThucHanh.txt");

                }
                if(tenPhong.get(1).equals(comboboxPhong.getSelectedItem())){
                    writeFile("src\\Data\\PhongHocLyThuyet.txt");
                }
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedWriter bufferedWriter = null;
                String noiDung = "\nXoá CSVC " + csvcTheoKhos.get(tblCSVCTheoPhong.getSelectedRow()).getTenTs() + "|" + java.time.LocalDate.now() + "|Không";
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(new File("src\\Data\\BaoCao.txt").getAbsoluteFile(), true));
                    bufferedWriter.write(noiDung);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }finally {
                    try {
                        bufferedWriter.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                csvcTheoKhos.remove(tblCSVCTheoPhong.getSelectedRow());
                CSVCTheoPhongTable csvcTheoPhongTable = new CSVCTheoPhongTable();
                tblCSVCTheoPhong.setModel(csvcTheoPhongTable);
                if(tenPhong.get(0).equals(comboboxPhong.getSelectedItem())){
                    writeFile("src\\Data\\PhongThucHanh.txt");
                }
                if(tenPhong.get(1).equals(comboboxPhong.getSelectedItem())){
                    writeFile("src\\Data\\PhongHocLyThuyet.txt");
                }
            }
        });


    }
    public void writeFile(String filee){
        BufferedWriter writer = null;
        try{
            File file = new File(filee);
            //writer = new FileWriter(file, true);
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < csvcTheoKhos.size(); i++) {
                if(i==0){
                    String w = csvcTheoKhos.get(i).getMaTs() + "|" + csvcTheoKhos.get(i).getTenTs() + "|" + csvcTheoKhos.get(i).getTinhTrang() + "|" + csvcTheoKhos.get(i).getNuocSx() + "|" + csvcTheoKhos.get(i).getNamSx() + "|" + csvcTheoKhos.get(i).getDonViTinh() + "|" + csvcTheoKhos.get(i).getNoiLuuTru() + "|" + csvcTheoKhos.get(i).getThongSoKiThuat() + "|" + csvcTheoKhos.get(i).getDonGia() + "|" + csvcTheoKhos.get(i).getSoLuong();
                    writer.write(w);
                }
                else{
                    String w = "\n" + csvcTheoKhos.get(i).getMaTs() + "|" + csvcTheoKhos.get(i).getTenTs() + "|" + csvcTheoKhos.get(i).getTinhTrang() + "|" + csvcTheoKhos.get(i).getNuocSx() + "|" + csvcTheoKhos.get(i).getNamSx() + "|" + csvcTheoKhos.get(i).getDonViTinh() + "|" + csvcTheoKhos.get(i).getNoiLuuTru() + "|" + csvcTheoKhos.get(i).getThongSoKiThuat() + "|" + csvcTheoKhos.get(i).getDonGia() + "|" + csvcTheoKhos.get(i).getSoLuong();
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
    public void readFile(String file){
        BufferedReader reader = null;
        tenPhong= new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine()) != null) {
                tenPhong.add(line);
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
    }
    public void readFilePhong(String file){
        BufferedReader reader = null;
        csvcTheoKhos = new ArrayList<>();
        try {
            CSVCTheoKho x = null;
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new CSVCTheoKho(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], Integer.parseInt(a[8]), Integer.parseInt(a[9]));
                csvcTheoKhos.add(x);
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
    }
    public void readFileCSVC(String file){
        BufferedReader reader = null;
        csvcTheoKhoList = new ArrayList<>();
        try {
            CSVCTheoKho x = null;
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new CSVCTheoKho(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], Integer.parseInt(a[8]), Integer.parseInt(a[9]));
                csvcTheoKhoList.add(x);
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
        List<CSVCTheoKho> test = new ArrayList<>();
        test = csvcTheoKhoList;
        for (int i = 0; i < maCSVC.size(); i++) {
            for (int j = 0; j < test.size(); j++) {
                if(csvcTheoKhoList.get(j).getMaTs().equals(maCSVC.get(i))){
                    csvcTheoKhoList.remove(j);
                }
            }
        }

    }
    public static class CSVCTheoPhongTable extends AbstractTableModel{

        private String[] COLUMNS = {"Mã tài sản", "Tên tài sản", "Tình trạng", "Nước sản xuất", "Năm sản xuất", "Đơn vị tính", "Nơi lưu trữ", "Thông số kĩ thuật", "Đơn giá", "Số lượng"};


        @Override
        public int getRowCount() {
            return csvcTheoKhos.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> csvcTheoKhos.get(rowIndex).getMaTs();
                case 1 -> csvcTheoKhos.get(rowIndex).getTenTs();
                case 2 -> csvcTheoKhos.get(rowIndex).getTinhTrang();
                case 3 -> csvcTheoKhos.get(rowIndex).getNuocSx();
                case 4 -> csvcTheoKhos.get(rowIndex).getNamSx();
                case 5 -> csvcTheoKhos.get(rowIndex).getDonViTinh();
                case 6 -> csvcTheoKhos.get(rowIndex).getNoiLuuTru();
                case 7 -> csvcTheoKhos.get(rowIndex).getThongSoKiThuat();
                case 8 -> csvcTheoKhos.get(rowIndex).getDonGia();
                case 9 -> csvcTheoKhos.get(rowIndex).getSoLuong();
                default ->  "-";
            };
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(getValueAt(0, columnIndex) != null){
                return getValueAt(0, columnIndex).getClass();
            }else{
                return Object.class;
            }
        }
    }
    public void readFileCSVCDefault(String file){
        BufferedReader reader = null;
        csvcDefault = new ArrayList<>();
        try {
            CSVCTheoKho x = null;
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new CSVCTheoKho(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], Integer.parseInt(a[8]), Integer.parseInt(a[9]));
                csvcDefault.add(x);
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

    }
}
