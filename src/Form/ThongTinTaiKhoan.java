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
public class ThongTinTaiKhoan extends JFrame{
    private JTable tblTaiKhoan;
    private JButton btnTrangChu;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnReset;
    private JTextField txtTenDangNhap;
    private JTextField txtNgayTao;
    private JTextField txtTrangThai;
    private JPasswordField txtMatKhau;
    private JPanel mainPanel;
    private JTextField txtTenGiaoVien;
    private JTextArea txtNhiemVu;
    private JComboBox comboBoxPhongQL;
    static TaiKhoanDAO taiKhoanDAO;
    BufferedReader bufferedReader;
    TaiKhoan taiKhoanSelected;
    List<Phong> phongs = new ArrayList<>();

    public ThongTinTaiKhoan() {
        setContentPane(mainPanel);
        setSize(1500, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        readFilePhong();
        for (int i = 0; i < phongs.size(); i++) {
            comboBoxPhongQL.addItem(phongs.get(i).getTenPhong());
        }
        init();
        TaiKhoanTable taiKhoanTable = new TaiKhoanTable();
        tblTaiKhoan.setModel(taiKhoanTable);
        tblTaiKhoan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                taiKhoanSelected = taiKhoanDAO.getTaiKhoanList().get(tblTaiKhoan.getSelectedRow());
                txtTenDangNhap.setText(taiKhoanSelected.getTenDangNhap());
                txtMatKhau.setText(taiKhoanSelected.getMatKhau());
                txtTenGiaoVien.setText(taiKhoanSelected.getTenGiaoVien());
                comboBoxPhongQL.setSelectedItem(taiKhoanSelected.getPhongQuanLy());
                txtNgayTao.setText(taiKhoanSelected.getNgayTao());
                txtTrangThai.setText(taiKhoanSelected.getTrangThai());
                txtNhiemVu.setText(taiKhoanSelected.getNhiemVu());
            }
        });

        comboBoxPhongQL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFilePhong();
                for (int i = 0; i < phongs.size(); i++) {
                    comboBoxPhongQL.addItem(phongs.get(i).getTenPhong());
                }

            }
        });
        btnTrangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrangChu().setVisible(true);
                dispose();
            }
        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaiKhoanTable taiKhoanTable = new TaiKhoanTable();
                tblTaiKhoan.setModel(taiKhoanTable);
                txtTenDangNhap.setText("");
                txtMatKhau.setText("");
                txtTenGiaoVien.setText("");
                comboBoxPhongQL.setSelectedItem("");
                txtNgayTao.setText("");
                txtTrangThai.setText("");
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTenDangNhap.getText().equals("")) {
                    JOptionPane.showMessageDialog(ThongTinTaiKhoan.this, "Tên đăng nhập không được bỏ trống");
                    return;
                }
                String tenDangNhap = txtTenDangNhap.getText();
                if (txtMatKhau.getPassword() == null) {
                    JOptionPane.showMessageDialog(ThongTinTaiKhoan.this, "Mật khẩu không được bỏ trống");
                    return;
                }
                String matKhau = String.valueOf(txtMatKhau.getPassword());
                if (comboBoxPhongQL.getSelectedItem().equals("")) {
                    JOptionPane.showMessageDialog(ThongTinTaiKhoan.this, "Phòng quản lý không được bỏ trống");
                    return;
                }
                String phongQuanLy = comboBoxPhongQL.getSelectedItem().toString();
                if (txtNgayTao.getText().equals("")) {
                    JOptionPane.showMessageDialog(ThongTinTaiKhoan.this, "Ngày tạo không được bỏ trống");
                    return;
                }
                String ngayTao = txtNgayTao.getText();
                if (txtTrangThai.getText().equals("")) {
                    JOptionPane.showMessageDialog(ThongTinTaiKhoan.this, "Trạng thái không được bỏ trống");
                    return;
                }
                String trangThai = txtTrangThai.getText();
                if (txtTenGiaoVien.getText().equals("")) {
                    JOptionPane.showMessageDialog(ThongTinTaiKhoan.this, "Tên giáo viên không được bỏ trống");
                    return;
                }
                String tenGiaoVien = txtTenGiaoVien.getText();
                if (txtNhiemVu.getText().equals("")) {
                    JOptionPane.showMessageDialog(ThongTinTaiKhoan.this, "Nhiệm vụ không được bỏ trống");
                    return;
                }
                String nhiemVu = txtNhiemVu.getText();
                TaiKhoan taiKhoan = new TaiKhoan(tenDangNhap, matKhau,tenGiaoVien, phongQuanLy, ngayTao, trangThai, nhiemVu);
                BufferedWriter bufferedWriter = null;
                String noiDung = "\nThêm tài khoản: " + tenDangNhap + "|" + java.time.LocalDate.now() + "|Không";
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
                    taiKhoanDAO.writeFileAppend(taiKhoan);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                init();
                TaiKhoanTable taiKhoanTable = new TaiKhoanTable();
                tblTaiKhoan.setModel(taiKhoanTable);
                txtTenDangNhap.setText("");
                txtMatKhau.setText("");
                txtNgayTao.setText("");
                txtTrangThai.setText("");
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTenDangNhap.getText().equals("")) {
                    JOptionPane.showMessageDialog(ThongTinTaiKhoan.this, "Tên đăng nhập không được bỏ trống");
                    return;
                }
                String tenDangNhap = txtTenDangNhap.getText();
                if (txtMatKhau.getPassword() == null) {
                    JOptionPane.showMessageDialog(ThongTinTaiKhoan.this, "Mật khẩu không được bỏ trống");
                    return;
                }
                String matKhau = String.valueOf(txtMatKhau.getPassword());
                if (comboBoxPhongQL.getSelectedItem().equals("")) {
                    JOptionPane.showMessageDialog(ThongTinTaiKhoan.this, "Phòng quản lý không được bỏ trống");
                    return;
                }
                String phongQuanLy = comboBoxPhongQL.getSelectedItem().toString();
                if (txtNgayTao.getText().equals("")) {
                    JOptionPane.showMessageDialog(ThongTinTaiKhoan.this, "Ngày tạo không được bỏ trống");
                    return;
                }
                String ngayTao = txtNgayTao.getText();
                if (txtTrangThai.getText().equals("")) {
                    JOptionPane.showMessageDialog(ThongTinTaiKhoan.this, "Trạng thái không được bỏ trống");
                    return;
                }
                String trangThai = txtTrangThai.getText();
                if (txtTenGiaoVien.getText().equals("")) {
                    JOptionPane.showMessageDialog(ThongTinTaiKhoan.this, "Tên giáo viên không được bỏ trống");
                    return;
                }
                String tenGiaoVien = txtTenGiaoVien.getText();
                if (txtNhiemVu.getText().equals("")) {
                    JOptionPane.showMessageDialog(ThongTinTaiKhoan.this, "Nhiệm vụ không được bỏ trống");
                    return;
                }
                String nhiemVu = txtNhiemVu.getText();
                BufferedWriter bufferedWriter = null;
                String noiDung = "\nSửa tài khoản: " + tenDangNhap + "|" + java.time.LocalDate.now() + "|Không";
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
                taiKhoanSelected = new TaiKhoan(tenDangNhap, matKhau, tenGiaoVien, phongQuanLy, ngayTao, trangThai, nhiemVu);
                try {
                    taiKhoanDAO.replaceTaiKhoan(taiKhoanSelected, tblTaiKhoan.getSelectedRow());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                init();
                TaiKhoanTable taiKhoanTable = new TaiKhoanTable();
                tblTaiKhoan.setModel(taiKhoanTable);
                txtTenDangNhap.setText("");
                txtMatKhau.setText("");
                txtNgayTao.setText("");
                txtTrangThai.setText("");
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter bufferedWriter = null;
                    String noiDung = "\nXoá tài khoản: " + taiKhoanDAO.getTaiKhoanList().get(tblTaiKhoan.getSelectedRow()) + "|" + java.time.LocalDate.now() + "|Không";
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
                    taiKhoanDAO.deleteTaiKhoan(tblTaiKhoan.getSelectedRow());
                    init();
                    TaiKhoanTable taiKhoanTable = new TaiKhoanTable();
                    tblTaiKhoan.setModel(taiKhoanTable);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static final String DATA_FILE_PATH_NHAN_VIEN = "src\\Data\\TaiKhoanGiaoVien.txt";
    File file = new File(DATA_FILE_PATH_NHAN_VIEN);
    String absolutePath = file.getAbsolutePath();

    public void loadDataFromFile() throws IOException {
        try {
            TaiKhoan x = null;
            bufferedReader = new BufferedReader(new FileReader(absolutePath));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new TaiKhoan(a[0], a[1], a[2], a[3], a[4], a[5], a[6]);
                taiKhoanDAO.getTaiKhoanList().add(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
        }
    }

    public void init() {
        taiKhoanDAO = new TaiKhoanDAO();
        try {
            loadDataFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class TaiKhoanTable extends AbstractTableModel {
        private String[] COLUMNS = {"Tên đăng nhập", "Tên giáo viên",  "Phòng quản lý", "Ngày tạo", "Trạng thái", "Nhiệm vụ"};
        private List<TaiKhoan> taiKhoanList = taiKhoanDAO.getTaiKhoanList();

        @Override
        public int getRowCount() {
            return taiKhoanList.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> taiKhoanList.get(rowIndex).getTenDangNhap();
                case 1 -> taiKhoanList.get(rowIndex).getTenGiaoVien();
                case 2 -> taiKhoanList.get(rowIndex).getPhongQuanLy();
                case 3 -> taiKhoanList.get(rowIndex).getNgayTao();
                case 4 -> taiKhoanList.get(rowIndex).getTrangThai();
                case 5 -> taiKhoanList.get(rowIndex).getNhiemVu();
                default -> "-";
            };
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getValueAt(0, columnIndex) != null) {
                return getValueAt(0, columnIndex).getClass();
            } else {
                return Object.class;
            }
        }

    }
    public void readFilePhong(){
        try {
            bufferedReader = new BufferedReader(new FileReader("src\\Data\\Phong.txt"));
            String line = "";
            while((line = bufferedReader.readLine())!=null){
                String []a = line.split("\\|");
                phongs.add(new Phong(a[0], a[1], a[2], a[3]));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
