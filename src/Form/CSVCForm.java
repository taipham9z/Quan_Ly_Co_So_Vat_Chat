package Form;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import java.util.List;

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
        setContentPane(mainPanel); //set nội dụng hiển thị là 1 panel chính
        setSize(1500, 700); //set kích thước
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Đặt hành động mặc định khi người dùng đóng cửa sổ
        setLocationRelativeTo(null);//set vị trí luôn ở chính giữa màn hình
        btnTrangChu.setBackground(Color.GREEN);
        btnTheoPhong.setBackground(Color.GREEN);
        setTitle("Thống kê cơ sở vật chất trong trường");//set tiêu đề
        init(); // dùng để đưa dữ liệu vào
        TaiSanTable nhanVienTable = new TaiSanTable(); //tạo 1 biến theo class được định nghĩa từ trước
        tblTaiSan.setModel(nhanVienTable); // hiển thị bảng dữ liệu theo biến vừa tạo
        btnTheoPhong.addActionListener(new ActionListener() { //bắt sự kiện cho nút Theo phòng
            @Override
            public void actionPerformed(ActionEvent e) {
                new CSVCTheoPhongForm().setVisible(true); //chuyển sang màn hình Theo phòng
                dispose(); //đóng cửa sổ hiện tại
            }
        });
        tblTaiSan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { //bắt sự kiện khi click chuột vào 1 doòng trong bảng
                CSVCSelected = CSVCDAO.getTaiSanList().get(tblTaiSan.getSelectedRow()); //gán csvc được chọn vào biến
                //set các ô text là thông tin của csvc được chọn
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
        btnThem.addActionListener(new ActionListener() { //bắt sự kiện nút Thêm
            @Override
            public void actionPerformed(ActionEvent e) {
                //kiểm tra các ô text không được bỏ trống
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
                //tạo biến csvc lưu lại những thông tin từ ô text
                CSVC CSVC = new CSVC(maTs, tenTs, tinhTrang, nuocSx, namSx, donViTinh, noiLuuTru, thongSo, donGia, soLuong);
                //ghi ra báo cáo
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
                //bắt đầu ghi ra file
                try {
                    CSVCDAO.writeFileAppend(CSVC, "src\\Data\\CSVC.txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // reset lại dữ liệu
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
        btnSua.addActionListener(new ActionListener() { //bắt sự kiện nút Sửa
            @Override
            public void actionPerformed(ActionEvent e) {
                //kiểm tra các ô text không bỏ trống
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
                    // gán csvc được chọn từ dữ liệu các ô text
                    CSVCSelected = new CSVC(maTs, tenTs, tinhTrang, nuocSx, namSx, donViTinh, noiLuuTru, thongSo, donGia, soLuong);
                    //ghi ra báo cáo
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
                    //bắt đầu thay thế trong file
                    CSVCDAO.replaceCSVC(CSVCSelected, tblTaiSan.getSelectedRow());
                    //reset lại dữ liệu
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
        btnXoa.addActionListener(new ActionListener() { //bắt sự kiện nút Xoá
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //xuất báo cáo
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
                    //bắt đầu xoá dữ liệu trong file
                    CSVCDAO.deleteCSVC(tblTaiSan.getSelectedRow());
                    //reset dữ liệu
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
        btnReset.addActionListener(new ActionListener() { //bắt sự kiện nút reset
            @Override
            public void actionPerformed(ActionEvent e) {
                //cập nhật lại dữ liệu bảng, đưa các ô text về trống
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
        btnTrangChu.addActionListener(new ActionListener() { //bắt sự kiện nút trang chủ
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrangChu().setVisible(true); //hiển thị màn hình trang chủ
                dispose(); //thoát màn hình trước
            }
        });
        btnTimKiem.addActionListener(new ActionListener() { //bắt sự kiện nút tìm kiếm
            @Override
            public void actionPerformed(ActionEvent e) {
                new TimKiem().setVisible(true); //hiển thị màn hình tìm kiếm
                dispose(); //thoát màn hình trước
            }
        });
    }

    public static final String DATA_FILE_PATH_NHAN_VIEN = "src\\Data\\CSVC.txt";
    File file = new File(DATA_FILE_PATH_NHAN_VIEN);
    String absolutePath = file.getAbsolutePath();
    public void loadDataFromFile() throws IOException { //lấy dữ liệu từ file
        try {
            CSVC x = null;
            bufferedReader = new BufferedReader(new FileReader(absolutePath));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) { //trong khi line ko rỗng, thì tiếp tục đọc file
                String[] a = line.split("\\|");
                x = new CSVC(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], Integer.parseInt(a[8]), Integer.parseInt(a[9]));
                CSVCDAO.getTaiSanList().add(x); //lưu dữ liệu vừa đọc vào list
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close(); //đóng file
        }
    }
    public void init() { // lấy dữ liệu từ file và làm mới lớp DAO
        CSVCDAO = new CSVCDAO();
        try {
            loadDataFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public static class TaiSanTable extends AbstractTableModel { //khai báo class bảng kế thừa lớp AbstractTableModel
        //khai báo tên các cột
        private String[] COLUMNS = {"Mã tài sản", "Tên tài sản", "Tình trạng", "Nước sản xuất", "Năm sản xuất", "Đơn vị tính", "Nơi lưu trữ", "Thông số kĩ thuật", "Đơn giá", "Số lượng"};
        private List<CSVC> CSVCList = CSVCDAO.getTaiSanList(); //dữ liệu cho các hàng

        @Override
        public int getRowCount() {
            return CSVCList.size();
        } //trả về số hàng

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        } //trả về số cột

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) { //giá trị tại các cột
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
        } //tên các cột

//        @Override
//        public Class<?> getColumnClass(int columnIndex) {
//            if(getValueAt(0, columnIndex) != null){
//                return getValueAt(0, columnIndex).getClass();
//            }else{
//                return Object.class;
//            }
//        }

    }
}
