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
public class CSVCTheoPhongForm extends JFrame{
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
    private static List<CSVC> csvcs;
    private CSVC csvcSelected;
    private List<CSVC> csvcList;
    private List<CSVC> csvcDefault;
    LichSuThayDoi lichSuThayDoi = new LichSuThayDoi();
    public CSVCTheoPhongForm(){
        setContentPane(mainPanel);
        setSize(1500, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        readFile("src\\Data\\LoaiPhong.txt");
        readFileCSVCDefault("src\\Data\\CSVC.txt");
        for (int i = 0; i < tenPhong.size(); i++) {
            comboboxPhong.addItem(tenPhong.get(i));
        }
        if(tenPhong.get(0).equals(comboboxPhong.getSelectedItem())){
            readFilePhong("src\\Data\\PhongThucHanh.txt");
            readFileCSVC("src\\Data\\CSVC.txt");
            comboBoxThem.removeAllItems();
            for (int i = 0; i < csvcs.size(); i++) {
                for (int j = 0; j < csvcList.size(); j++) {
                    if(csvcList.get(j).getMaTs().equals(csvcs.get(i).getMaTs())){
                        csvcList.remove(j);
                    }
                }
            }
            for (int i = 0; i < csvcList.size(); i++) {
                comboBoxThem.addItem(csvcList.get(i).getTenTs());
            }
        }
        if(tenPhong.get(1).equals(comboboxPhong.getSelectedItem())){
            readFilePhong("src\\Data\\PhongHocLyThuyet.txt");
            for (int i = 0; i < csvcs.size(); i++) {
                maCSVC = new ArrayList<>();
                maCSVC.add(csvcs.get(i).getMaTs());
            }
            readFileCSVC("src\\Data\\CSVC.txt");
            comboBoxThem.removeAllItems();
            for (int i = 0; i < csvcs.size(); i++) {
                for (int j = 0; j < csvcList.size(); j++) {
                    if(csvcList.get(j).getMaTs().equals(csvcs.get(i).getMaTs())){
                        csvcList.remove(j);
                    }
                }
            }
            for (int i = 0; i < csvcList.size(); i++) {
                comboBoxThem.addItem(csvcList.get(i).getTenTs());
            }
        }

        CSVCTheoPhongTable csvcTheoPhongTable = new CSVCTheoPhongTable();
        tblCSVCTheoPhong.setModel(csvcTheoPhongTable);
        tblCSVCTheoPhong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                csvcSelected = new CSVC();
                csvcSelected = csvcs.get(tblCSVCTheoPhong.getSelectedRow());
            }
        });
        comboboxPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tenPhong.get(0).equals(comboboxPhong.getSelectedItem())){
                    readFilePhong("src\\Data\\PhongThucHanh.txt");
                    for (int i = 0; i < csvcs.size(); i++) {
                        maCSVC = new ArrayList<>();
                        maCSVC.add(csvcs.get(i).getMaTs());
                    }
                    readFileCSVC("src\\Data\\CSVC.txt");
                    comboBoxThem.removeAllItems();
                    for (int i = 0; i < csvcs.size(); i++) {
                        for (int j = 0; j < csvcList.size(); j++) {
                            if(csvcList.get(j).getMaTs().equals(csvcs.get(i).getMaTs())){
                                csvcList.remove(j);
                            }
                        }
                    }
                    for (int i = 0; i < csvcList.size(); i++) {
                        comboBoxThem.addItem(csvcList.get(i).getTenTs());
                    }
                }
                if(tenPhong.get(1).equals(comboboxPhong.getSelectedItem())){
                    readFilePhong("src\\Data\\PhongHocLyThuyet.txt");
                    for (int i = 0; i < csvcs.size(); i++) {
                        maCSVC = new ArrayList<>();
                        maCSVC.add(csvcs.get(i).getMaTs());
                    }
                    readFileCSVC("src\\Data\\CSVC.txt");
                    comboBoxThem.removeAllItems();
                    for (int i = 0; i < csvcs.size(); i++) {
                        for (int j = 0; j < csvcList.size(); j++) {
                            if(csvcList.get(j).getMaTs().equals(csvcs.get(i).getMaTs())){
                                csvcList.remove(j);
                            }
                        }
                    }
                    for (int i = 0; i < csvcList.size(); i++) {
                        comboBoxThem.addItem(csvcList.get(i).getTenTs());
                    }
                }
                CSVCTheoPhongTable csvcTheoPhongTable = new CSVCTheoPhongTable();
                tblCSVCTheoPhong.setModel(csvcTheoPhongTable);
            }
        });

        btnTrangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CSVCForm().setVisible(true);
                dispose();
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < csvcDefault.size(); i++) {
                    if(csvcDefault.get(i).getTenTs().equals(comboBoxThem.getSelectedItem())){
                        csvcs.add(csvcDefault.get(i));
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
                csvcs.get(tblCSVCTheoPhong.getSelectedRow()).setSoLuong(csvcs.get(tblCSVCTheoPhong.getSelectedRow()).getSoLuong() + 1);
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
                String noiDung = "\nXoá CSVC " + csvcs.get(tblCSVCTheoPhong.getSelectedRow()).getTenTs() + "|" + java.time.LocalDate.now() + "|Không";
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
                csvcs.remove(tblCSVCTheoPhong.getSelectedRow());
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
            for (int i = 0; i < csvcs.size(); i++) {
                if(i==0){
                    String w = csvcs.get(i).getMaTs() + "|" + csvcs.get(i).getTenTs() + "|" + csvcs.get(i).getTinhTrang() + "|" + csvcs.get(i).getNuocSx() + "|" + csvcs.get(i).getNamSx() + "|" + csvcs.get(i).getDonViTinh() + "|" + csvcs.get(i).getNoiLuuTru() + "|" + csvcs.get(i).getThongSoKiThuat() + "|" + csvcs.get(i).getDonGia() + "|" + csvcs.get(i).getSoLuong();
                    writer.write(w);
                }
                else{
                    String w = "\n" + csvcs.get(i).getMaTs() + "|" + csvcs.get(i).getTenTs() + "|" + csvcs.get(i).getTinhTrang() + "|" + csvcs.get(i).getNuocSx() + "|" + csvcs.get(i).getNamSx() + "|" + csvcs.get(i).getDonViTinh() + "|" + csvcs.get(i).getNoiLuuTru() + "|" + csvcs.get(i).getThongSoKiThuat() + "|" + csvcs.get(i).getDonGia() + "|" + csvcs.get(i).getSoLuong();
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
        csvcs = new ArrayList<>();
        try {
            CSVC x = null;
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new CSVC(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], Integer.parseInt(a[8]), Integer.parseInt(a[9]));
                csvcs.add(x);
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
        csvcList = new ArrayList<>();
        try {
            CSVC x = null;
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new CSVC(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], Integer.parseInt(a[8]), Integer.parseInt(a[9]));
                csvcList.add(x);
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
        List<CSVC> test = new ArrayList<>();
        test = csvcList;
        for (int i = 0; i < maCSVC.size(); i++) {
            for (int j = 0; j < test.size(); j++) {
                if(csvcList.get(j).getMaTs().equals(maCSVC.get(i))){
                    csvcList.remove(j);
                }
            }
        }

    }
    public static class CSVCTheoPhongTable extends AbstractTableModel{

        private String[] COLUMNS = {"Mã tài sản", "Tên tài sản", "Tình trạng", "Nước sản xuất", "Năm sản xuất", "Đơn vị tính", "Nơi lưu trữ", "Thông số kĩ thuật", "Đơn giá", "Số lượng"};


        @Override
        public int getRowCount() {
            return csvcs.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> csvcs.get(rowIndex).getMaTs();
                case 1 -> csvcs.get(rowIndex).getTenTs();
                case 2 -> csvcs.get(rowIndex).getTinhTrang();
                case 3 -> csvcs.get(rowIndex).getNuocSx();
                case 4 -> csvcs.get(rowIndex).getNamSx();
                case 5 -> csvcs.get(rowIndex).getDonViTinh();
                case 6 -> csvcs.get(rowIndex).getNoiLuuTru();
                case 7 -> csvcs.get(rowIndex).getThongSoKiThuat();
                case 8 -> csvcs.get(rowIndex).getDonGia();
                case 9 -> csvcs.get(rowIndex).getSoLuong();
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
            CSVC x = null;
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new CSVC(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], Integer.parseInt(a[8]), Integer.parseInt(a[9]));
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
