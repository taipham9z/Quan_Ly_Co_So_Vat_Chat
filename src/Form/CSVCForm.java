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
public class CSVCForm extends JFrame{

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
    private JButton btnTheoPhong;
    private JButton btnTimKiem;
    BufferedReader bufferedReader;
    static CSVCDAO CSVCDAO;
    CSVC CSVCSelected;
    List<KhoLuuTru> khoLuuTrus;
    public CSVCForm(){
        setContentPane(mainPanel);
        setSize(1500, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Thống kê cơ sở vật chất trong trường");
        init();
        TaiSanTable nhanVienTable = new TaiSanTable();
        tblTaiSan.setModel(nhanVienTable);
        btnTheoPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CSVCTheoPhongForm().setVisible(true);
                dispose();
            }
        });
//        TaiSanTable taiSanTable = new TaiSanTable();
//        tblTaiSan.setModel(taiSanTable);
//        CSVCTheoKhoTable csvcTheoKhoTable = new CSVCTheoKhoTable();
//        tblTaiSan.setModel(csvcTheoKhoTable);
        readFile("src\\Data\\KhoLuuTru.txt");
        tblTaiSan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CSVCSelected = CSVCDAO.getTaiSanList().get(tblTaiSan.getSelectedRow());
                txtMaTs.setText(CSVCSelected.getMaTs());
                txtTenTs.setText(CSVCSelected.getTenTs());
                txtTinhTrang.setText(CSVCSelected.getTinhTrang());
                txtNuocSx.setText(CSVCSelected.getNuocSx());
                txtNamSx.setText(CSVCSelected.getNamSx());
                txtDonViTinh.setText(CSVCSelected.getDonViTinh());
                txtNoiLuuTru.setText(CSVCSelected.getNoiLuuTru());
                txtThongSo.setText(CSVCSelected.getThongSoKiThuat());
                txtDonGia.setText(String.valueOf(CSVCSelected.getDonGia()));
                txtSoLuong.setText(String.valueOf(CSVCSelected.getSoLuong()));
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtMaTs.getText().equals("")){
                    JOptionPane.showMessageDialog(CSVCForm.this, "Mã tài sản không được bỏ trống");
                    return;
                }
                String maTs = txtMaTs.getText();
                if(txtTenTs.getText().equals("")){
                    JOptionPane.showMessageDialog(CSVCForm.this, "Tên tài sản không được bỏ trống");
                    return;
                }
                String tenTs = txtTenTs.getText();

                if(txtTinhTrang.getText().equals("")){
                    JOptionPane.showMessageDialog(CSVCForm.this, "Tình trạng không được bỏ trống");
                    return;
                }
                String tinhTrang = txtTinhTrang.getText();
                if(txtNuocSx.getText().equals("")){
                    JOptionPane.showMessageDialog(CSVCForm.this, "Nước sản xuất không được bỏ trống");
                    return;
                }
                String nuocSx = txtNuocSx.getText();
                if(txtNamSx.getText().equals("")){
                    JOptionPane.showMessageDialog(CSVCForm.this, "Năm sản xuất không được bỏ trống");
                    return;
                }
                String namSx = txtNamSx.getText();
                if(txtDonViTinh.getText().equals("")){
                    JOptionPane.showMessageDialog(CSVCForm.this, "Đơn vị tính không được bỏ trống");
                    return;
                }
                String donViTinh = txtDonViTinh.getText();
                if(txtNoiLuuTru.getText().equals("")){
                    JOptionPane.showMessageDialog(CSVCForm.this, "Nơi lưu trữ không được bỏ trống");
                    return;
                }
                String noiLuuTru = txtNoiLuuTru.getText();
                if(txtThongSo.getText().equals("")){
                    JOptionPane.showMessageDialog(CSVCForm.this, "Thông số kĩ thuật không được bỏ trống");
                    return;
                }
                String thongSo = txtThongSo.getText();
                if(txtDonGia.getText().equals("")){
                    JOptionPane.showMessageDialog(CSVCForm.this, "Đơn giá không được bỏ trống bỏ trống");
                    return;
                }
                int donGia = Integer.parseInt(txtDonGia.getText());
                if(txtSoLuong.getText().equals("")){
                    JOptionPane.showMessageDialog(CSVCForm.this, "Số lượng không được bỏ trống");
                    return;
                }
                int soLuong = Integer.parseInt(txtSoLuong.getText());

                CSVC CSVC = new CSVC(maTs, tenTs, tinhTrang, nuocSx, namSx, donViTinh, noiLuuTru, thongSo, donGia, soLuong);
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
                    CSVCDAO.writeFileAppend(CSVC, "src\\Data\\CSVC.txt");
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
                        JOptionPane.showMessageDialog(CSVCForm.this, "Mã tài sản không được bỏ trống");
                        return;
                    }
                    String maTs = txtMaTs.getText();
                    if(txtTenTs.getText().equals("")){
                        JOptionPane.showMessageDialog(CSVCForm.this, "Tên tài sản không được bỏ trống");
                        return;
                    }
                    String tenTs = txtTenTs.getText();

                    if(txtTinhTrang.getText().equals("")){
                        JOptionPane.showMessageDialog(CSVCForm.this, "Tình trạng không được bỏ trống");
                        return;
                    }
                    String tinhTrang = txtTinhTrang.getText();
                    if(txtNuocSx.getText().equals("")){
                        JOptionPane.showMessageDialog(CSVCForm.this, "Nước sản xuất không được bỏ trống");
                        return;
                    }
                    String nuocSx = txtNuocSx.getText();
                    if(txtNamSx.getText().equals("")){
                        JOptionPane.showMessageDialog(CSVCForm.this, "Năm sản xuất không được bỏ trống");
                        return;
                    }
                    String namSx = txtNamSx.getText();
                    if(txtDonViTinh.getText().equals("")){
                        JOptionPane.showMessageDialog(CSVCForm.this, "Đơn vị tính không được bỏ trống");
                        return;
                    }
                    String donViTinh = txtDonViTinh.getText();
                    if(txtNoiLuuTru.getText().equals("")){
                        JOptionPane.showMessageDialog(CSVCForm.this, "Nơi lưu trữ không được bỏ trống");
                        return;
                    }
                    String noiLuuTru = txtNoiLuuTru.getText();
                    if(txtThongSo.getText().equals("")){
                        JOptionPane.showMessageDialog(CSVCForm.this, "Thông số kĩ thuật không được bỏ trống");
                        return;
                    }
                    String thongSo = txtThongSo.getText();
                    if(txtDonGia.getText().equals("")){
                        JOptionPane.showMessageDialog(CSVCForm.this, "Đơn giá không được bỏ trống bỏ trống");
                        return;
                    }
                    int donGia = Integer.parseInt(txtDonGia.getText());
                    if(txtSoLuong.getText().equals("")){
                        JOptionPane.showMessageDialog(CSVCForm.this, "Số lượng không được bỏ trống");
                        return;
                    }
                    int soLuong = Integer.parseInt(txtSoLuong.getText());
                    CSVCSelected = new CSVC(maTs, tenTs, tinhTrang, nuocSx, namSx, donViTinh, noiLuuTru, thongSo, donGia, soLuong);
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
                    CSVCDAO.replaceCSVC(CSVCSelected, tblTaiSan.getSelectedRow());
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
                    String noiDung = "\nXoá CSVC: " + CSVCDAO.getTaiSanList().get(tblTaiSan.getSelectedRow()).getTenTs() + "|" + java.time.LocalDate.now() + "|Không";
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
                    CSVCDAO.deleteCSVC(tblTaiSan.getSelectedRow());
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
            CSVC x = null;
            bufferedReader = new BufferedReader(new FileReader(absolutePath));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new CSVC(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], Integer.parseInt(a[8]), Integer.parseInt(a[9]));
                CSVCDAO.getTaiSanList().add(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
        }
    }
    public void init() {
        CSVCDAO = new CSVCDAO();
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
        private List<CSVC> CSVCList = CSVCDAO.getTaiSanList();

        @Override
        public int getRowCount() {
            return CSVCList.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> CSVCList.get(rowIndex).getMaTs();
                case 1 -> CSVCList.get(rowIndex).getTenTs();
                case 2 -> CSVCList.get(rowIndex).getTinhTrang();
                case 3 -> CSVCList.get(rowIndex).getNuocSx();
                case 4 -> CSVCList.get(rowIndex).getNamSx();
                case 5 -> CSVCList.get(rowIndex).getDonViTinh();
                case 6 -> CSVCList.get(rowIndex).getNoiLuuTru();
                case 7 -> CSVCList.get(rowIndex).getThongSoKiThuat();
                case 8 -> CSVCList.get(rowIndex).getDonGia();
                case 9 -> CSVCList.get(rowIndex).getSoLuong();
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
