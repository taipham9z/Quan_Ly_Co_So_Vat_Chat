package Form;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;

import Class.*;
public class ThongKeCSVCTheoKho extends JFrame{

    private JPanel mainPanel;
    private JTextField txtMaTs;
    private JTextField txtDonViTinh;
    private JTextField txtTenTs;
    private JTextField txtTinhTrang;
    private JTextField txtNoiLuuTru;
    private JTextField txtThongSo;
    private JTextField txtNuocSx;
    private JTextField txtNamSx;
    private JTextField txtDonGia;
    private JTextField txtSoLuong;
    private JTable tblTaiSan;
    private JButton btnTrangChu;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnReset;
    private JComboBox comboBoxDsKho;
    private JComboBox comboBoxMaKho;
    private JButton btnTheoPhong;
    private JButton btnTimKiem;
    BufferedReader bufferedReader;
    static CSVCTheoKhoDAO CSVCTheoKhoDAO;
    CSVCTheoKho CSVCTheoKhoSelected;
    List<KhoLuuTru> khoLuuTrus;
    static List<CSVCTheoKho> csvc = new ArrayList<>();
    public ThongKeCSVCTheoKho(){
        setContentPane(mainPanel);
        setSize(1500, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Thống kê cơ sở vật chất trong trường");
        init();
        btnTheoPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CSVCTheoPhong().setVisible(true);
                dispose();
            }
        });
//        TaiSanTable taiSanTable = new TaiSanTable();
//        tblTaiSan.setModel(taiSanTable);
//        CSVCTheoKhoTable csvcTheoKhoTable = new CSVCTheoKhoTable();
//        tblTaiSan.setModel(csvcTheoKhoTable);
        readFile("src\\Data\\KhoLuuTru.txt");
        List<String> tenKho = new ArrayList<>();
        List<String> maKho = new ArrayList<>();
        for(int i = 0;i < khoLuuTrus.size() ; i++){
            tenKho.add(khoLuuTrus.get(i).getTenKho());
            maKho.add(khoLuuTrus.get(i).getMaKho());
        }
        for(int i = 0;i < tenKho.size() ; i++){
            comboBoxDsKho.addItem(tenKho.get(i));
            comboBoxMaKho.addItem(maKho.get(i));
        }

        for(int i = 0;i < maKho.size() ; i++){
            if(tenKho.get(i).equals(comboBoxDsKho.getSelectedItem())){
                for(int j = 0; j < CSVCTheoKhoDAO.getTaiSanList().size();j++){
                    if(maKho.get(i).equals(CSVCTheoKhoDAO.getTaiSanList().get(j).getNoiLuuTru())){
                        csvc.add(CSVCTheoKhoDAO.getTaiSanList().get(j));
                        comboBoxMaKho.setSelectedIndex(comboBoxDsKho.getSelectedIndex());
                    }
                }
            }
        }
        CSVCTheoKhoTable csvcTheoKhoTable = new CSVCTheoKhoTable();
        tblTaiSan.setModel(csvcTheoKhoTable);
        tblTaiSan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CSVCTheoKhoSelected = csvc.get(tblTaiSan.getSelectedRow());
                txtMaTs.setText(CSVCTheoKhoSelected.getMaTs());
                txtTenTs.setText(CSVCTheoKhoSelected.getTenTs());
                txtTinhTrang.setText(CSVCTheoKhoSelected.getTinhTrang());
                txtNuocSx.setText(CSVCTheoKhoSelected.getNuocSx());
                txtNamSx.setText(CSVCTheoKhoSelected.getNamSx());
                txtDonViTinh.setText(CSVCTheoKhoSelected.getDonViTinh());
                txtNoiLuuTru.setText(CSVCTheoKhoSelected.getNoiLuuTru());
                txtThongSo.setText(CSVCTheoKhoSelected.getThongSoKiThuat());
                txtDonGia.setText(String.valueOf(CSVCTheoKhoSelected.getDonGia()));
                txtSoLuong.setText(String.valueOf(CSVCTheoKhoSelected.getSoLuong()));
            }
        });
        comboBoxDsKho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(comboBoxDsKho.getSelectedItem());
                csvc.clear();
                for(int i = 0;i < maKho.size() ; i++){
                    if(tenKho.get(i).equals(comboBoxDsKho.getSelectedItem())){
                        for(int j = 0; j < CSVCTheoKhoDAO.getTaiSanList().size();j++){
                            if(maKho.get(i).equals(CSVCTheoKhoDAO.getTaiSanList().get(j).getNoiLuuTru())){
                                csvc.add(CSVCTheoKhoDAO.getTaiSanList().get(j));
                                comboBoxMaKho.setSelectedIndex(comboBoxDsKho.getSelectedIndex());
                            }
                        }
                    }

                }
                CSVCTheoKhoTable csvcTheoKhoTable = new CSVCTheoKhoTable();
                tblTaiSan.setModel(csvcTheoKhoTable);
            }


        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtMaTs.getText().equals("")){
                    JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Mã tài sản không được bỏ trống");
                    return;
                }
                String maTs = txtMaTs.getText();
                if(txtTenTs.getText().equals("")){
                    JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Tên tài sản không được bỏ trống");
                    return;
                }
                String tenTs = txtTenTs.getText();

                if(txtTinhTrang.getText().equals("")){
                    JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Tình trạng không được bỏ trống");
                    return;
                }
                String tinhTrang = txtTinhTrang.getText();
                if(txtNuocSx.getText().equals("")){
                    JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Nước sản xuất không được bỏ trống");
                    return;
                }
                String nuocSx = txtNuocSx.getText();
                if(txtNamSx.getText().equals("")){
                    JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Năm sản xuất không được bỏ trống");
                    return;
                }
                String namSx = txtNamSx.getText();
                if(txtDonViTinh.getText().equals("")){
                    JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Đơn vị tính không được bỏ trống");
                    return;
                }
                String donViTinh = txtDonViTinh.getText();
                if(txtNoiLuuTru.getText().equals("")){
                    JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Nơi lưu trữ không được bỏ trống");
                    return;
                }
                String noiLuuTru = txtNoiLuuTru.getText();
                if(txtThongSo.getText().equals("")){
                    JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Thông số kĩ thuật không được bỏ trống");
                    return;
                }
                String thongSo = txtThongSo.getText();
                if(txtDonGia.getText().equals("")){
                    JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Đơn giá không được bỏ trống bỏ trống");
                    return;
                }
                int donGia = Integer.parseInt(txtDonGia.getText());
                if(txtSoLuong.getText().equals("")){
                    JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Số lượng không được bỏ trống");
                    return;
                }
                int soLuong = Integer.parseInt(txtSoLuong.getText());

                CSVCTheoKho CSVCTheoKho = new CSVCTheoKho(maTs, tenTs, tinhTrang, nuocSx, namSx, donViTinh, noiLuuTru, thongSo, donGia, soLuong);
                BufferedWriter bufferedWriter = null;
                String noiDung = "\nThêm CSVC: " + tenTs + "|" + java.time.LocalDate.now() + "|Không";
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
                try {
                    CSVCTheoKhoDAO.writeFile(CSVCTheoKho);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                init();
                TaiSanTable taiSanTable = new TaiSanTable();
                tblTaiSan.setModel(taiSanTable);
                txtMaTs.setText("");
                txtTenTs.setText("");
                txtTinhTrang.setText("");
                txtNuocSx.setText("");
                txtNamSx.setText("");
                txtDonViTinh.setText("");
                txtNoiLuuTru.setText("");
                txtThongSo.setText("");
                txtDonGia.setText("");
                txtSoLuong.setText("");
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(txtMaTs.getText().equals("")){
                        JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Mã tài sản không được bỏ trống");
                        return;
                    }
                    String maTs = txtMaTs.getText();
                    if(txtTenTs.getText().equals("")){
                        JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Tên tài sản không được bỏ trống");
                        return;
                    }
                    String tenTs = txtTenTs.getText();

                    if(txtTinhTrang.getText().equals("")){
                        JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Tình trạng không được bỏ trống");
                        return;
                    }
                    String tinhTrang = txtTinhTrang.getText();
                    if(txtNuocSx.getText().equals("")){
                        JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Nước sản xuất không được bỏ trống");
                        return;
                    }
                    String nuocSx = txtNuocSx.getText();
                    if(txtNamSx.getText().equals("")){
                        JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Năm sản xuất không được bỏ trống");
                        return;
                    }
                    String namSx = txtNamSx.getText();
                    if(txtDonViTinh.getText().equals("")){
                        JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Đơn vị tính không được bỏ trống");
                        return;
                    }
                    String donViTinh = txtDonViTinh.getText();
                    if(txtNoiLuuTru.getText().equals("")){
                        JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Nơi lưu trữ không được bỏ trống");
                        return;
                    }
                    String noiLuuTru = txtNoiLuuTru.getText();
                    if(txtThongSo.getText().equals("")){
                        JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Thông số kĩ thuật không được bỏ trống");
                        return;
                    }
                    String thongSo = txtThongSo.getText();
                    if(txtDonGia.getText().equals("")){
                        JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Đơn giá không được bỏ trống bỏ trống");
                        return;
                    }
                    int donGia = Integer.parseInt(txtDonGia.getText());
                    if(txtSoLuong.getText().equals("")){
                        JOptionPane.showMessageDialog(ThongKeCSVCTheoKho.this, "Số lượng không được bỏ trống");
                        return;
                    }
                    int soLuong = Integer.parseInt(txtSoLuong.getText());
                    CSVCTheoKhoSelected = new CSVCTheoKho(maTs, tenTs, tinhTrang, nuocSx, namSx, donViTinh, noiLuuTru, thongSo, donGia, soLuong);
                    BufferedWriter bufferedWriter = null;
                    String noiDung = "\nSửa CSVC: " + tenTs + "|" + java.time.LocalDate.now() + "|Không";
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
                    CSVCTheoKhoDAO.replaceCSVC(CSVCTheoKhoSelected, tblTaiSan.getSelectedRow());
                    init();
                    TaiSanTable nhanVienTable = new TaiSanTable();
                    tblTaiSan.setModel(nhanVienTable);
                    txtMaTs.setText("");
                    txtTenTs.setText("");
                    txtTinhTrang.setText("");
                    txtNuocSx.setText("");
                    txtNamSx.setText("");
                    txtDonViTinh.setText("");
                    txtNoiLuuTru.setText("");
                    txtThongSo.setText("");
                    txtDonGia.setText("");
                    txtSoLuong.setText("");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter bufferedWriter = null;
                    String noiDung = "\nXoá CSVC: " + CSVCTheoKhoDAO.getTaiSanList().get(tblTaiSan.getSelectedRow()).getTenTs() + "|" + java.time.LocalDate.now() + "|Không";
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
                    CSVCTheoKhoDAO.deleteCSVC(tblTaiSan.getSelectedRow());
                    init();
                    TaiSanTable nhanVienTable = new TaiSanTable();
                    tblTaiSan.setModel(nhanVienTable);
                    txtMaTs.setText("");
                    txtTenTs.setText("");
                    txtTinhTrang.setText("");
                    txtNuocSx.setText("");
                    txtNamSx.setText("");
                    txtDonViTinh.setText("");
                    txtNoiLuuTru.setText("");
                    txtThongSo.setText("");
                    txtDonGia.setText("");
                    txtSoLuong.setText("");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaiSanTable nhanVienTable = new TaiSanTable();
                tblTaiSan.setModel(nhanVienTable);
                txtMaTs.setText("");
                txtTenTs.setText("");
                txtTinhTrang.setText("");
                txtNuocSx.setText("");
                txtNamSx.setText("");
                txtDonViTinh.setText("");
                txtNoiLuuTru.setText("");
                txtThongSo.setText("");
                txtDonGia.setText("");
                txtSoLuong.setText("");
            }
        });
        btnTrangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrangChu().setVisible(true);
                dispose();
            }
        });
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TimKiem().setVisible(true);
                dispose();
            }
        });
    }

    public static final String DATA_FILE_PATH_NHAN_VIEN = "src\\Data\\CSVC.txt";
    File file = new File(DATA_FILE_PATH_NHAN_VIEN);
    String absolutePath = file.getAbsolutePath();
    public void loadDataFromFile() throws IOException {
        try {
            CSVCTheoKho x = null;
            bufferedReader = new BufferedReader(new FileReader(absolutePath));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new CSVCTheoKho(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], Integer.parseInt(a[8]), Integer.parseInt(a[9]));
                CSVCTheoKhoDAO.getTaiSanList().add(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
        }
    }
    public void init() {
        CSVCTheoKhoDAO = new CSVCTheoKhoDAO();
        try {
            loadDataFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public void readFile(String file){
        BufferedReader reader = null;
        khoLuuTrus= new ArrayList<>();
        try {
            KhoLuuTru x = null;
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new KhoLuuTru(a[0], a[1], a[2], a[3]);
                khoLuuTrus.add(x);
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
    public static class TaiSanTable extends AbstractTableModel {
        private String[] COLUMNS = {"Mã tài sản", "Tên tài sản", "Tình trạng", "Nước sản xuất", "Năm sản xuất", "Đơn vị tính", "Nơi lưu trữ", "Thông số kĩ thuật", "Đơn giá", "Số lượng"};
        private List<CSVCTheoKho> CSVCTheoKhoList = CSVCTheoKhoDAO.getTaiSanList();

        @Override
        public int getRowCount() {
            return CSVCTheoKhoList.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> CSVCTheoKhoList.get(rowIndex).getMaTs();
                case 1 -> CSVCTheoKhoList.get(rowIndex).getTenTs();
                case 2 -> CSVCTheoKhoList.get(rowIndex).getTinhTrang();
                case 3 -> CSVCTheoKhoList.get(rowIndex).getNuocSx();
                case 4 -> CSVCTheoKhoList.get(rowIndex).getNamSx();
                case 5 -> CSVCTheoKhoList.get(rowIndex).getDonViTinh();
                case 6 -> CSVCTheoKhoList.get(rowIndex).getNoiLuuTru();
                case 7 -> CSVCTheoKhoList.get(rowIndex).getThongSoKiThuat();
                case 8 -> CSVCTheoKhoList.get(rowIndex).getDonGia();
                case 9 -> CSVCTheoKhoList.get(rowIndex).getSoLuong();
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
    public static class CSVCTheoKhoTable extends AbstractTableModel {
        private String[] COLUMNS = {"Mã tài sản", "Tên tài sản", "Tình trạng", "Nước sản xuất", "Năm sản xuất", "Đơn vị tính", "Nơi lưu trữ", "Thông số kĩ thuật", "Đơn giá", "Số lượng"};

        @Override
        public int getRowCount() {
            return csvc.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> csvc.get(rowIndex).getMaTs();
                case 1 -> csvc.get(rowIndex).getTenTs();
                case 2 -> csvc.get(rowIndex).getTinhTrang();
                case 3 -> csvc.get(rowIndex).getNuocSx();
                case 4 -> csvc.get(rowIndex).getNamSx();
                case 5 -> csvc.get(rowIndex).getDonViTinh();
                case 6 -> csvc.get(rowIndex).getNoiLuuTru();
                case 7 -> csvc.get(rowIndex).getThongSoKiThuat();
                case 8 -> csvc.get(rowIndex).getDonGia();
                case 9 -> csvc.get(rowIndex).getSoLuong();
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
}
