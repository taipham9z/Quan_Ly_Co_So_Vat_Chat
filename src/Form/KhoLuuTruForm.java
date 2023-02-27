package Form;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.List;

import Class.KhoLuuTru;
import Class.KhoLuuTruDAO;


public class KhoLuuTruForm extends JFrame{

    private JTable tblKhoLuuTru;
    private JTextField txtMaKho;
    private JTextField txtMaBoPhan;
    private JTextField txtTenKho;
    private JTextField txtGhiChu;
    private JButton btnTrangChu;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnReset;
    private JPanel mainPanel;
    BufferedReader bufferedReader;
    static KhoLuuTruDAO khoLuuTruDAO;
    KhoLuuTru khoLuuTruSelected;

    public KhoLuuTruForm() {
        setContentPane(mainPanel);
        setSize(1500, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
        KhoLuuTruTable khoLuuTruTable = new KhoLuuTruTable(); // tạo 1 bảng dữ liệu, khai báo ở cuối hàm
        tblKhoLuuTru.setModel(khoLuuTruTable); //set JTable là bảng vừa tại
        tblKhoLuuTru.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { //thêm sự kiện khi click chuột vào 1 dòng trong bảng
                khoLuuTruSelected = khoLuuTruDAO.getKhoLuuTruList().get(tblKhoLuuTru.getSelectedRow());
                txtMaKho.setText(khoLuuTruSelected.getMaKho());
                txtTenKho.setText(khoLuuTruSelected.getTenKho());
                txtMaBoPhan.setText(khoLuuTruSelected.getMaBoPhan());
                txtGhiChu.setText(khoLuuTruSelected.getGhiChu());
            }
        });
        btnTrangChu.addActionListener(new ActionListener() { //thêm sự kiện khi click nút trang chủ
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrangChu().setVisible(true);
                dispose();
            }
        });
        btnReset.addActionListener(new ActionListener() { //thêm sự kiện khi click nút reset
            @Override
            public void actionPerformed(ActionEvent e) {
                KhoLuuTruTable khoLuuTruTable = new KhoLuuTruTable();
                tblKhoLuuTru.setModel(khoLuuTruTable);
                txtMaKho.setText("");
                txtTenKho.setText("");
                txtMaBoPhan.setText("");
                txtGhiChu.setText("");
            }
        });
        btnThem.addActionListener(new ActionListener() { //thêm sự kiện khi click nút thêm
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaKho.getText().equals("")) {
                    JOptionPane.showMessageDialog(KhoLuuTruForm.this, "Mã kho không được bỏ trống");
                    return;
                }
                String maKho = txtMaKho.getText();
                if (txtTenKho.getText().equals("")) {
                    JOptionPane.showMessageDialog(KhoLuuTruForm.this, "Tên kho không được bỏ trống");
                    return;
                }
                String tenKho = txtTenKho.getText();
                if (txtMaBoPhan.getText().equals("")) {
                    JOptionPane.showMessageDialog(KhoLuuTruForm.this, "Mã bộ phận không được bỏ trống");
                    return;
                }
                String maBoPhan = txtMaBoPhan.getText();
                if (txtGhiChu.getText().equals("")) {
                    JOptionPane.showMessageDialog(KhoLuuTruForm.this, "Ghi chú không được bỏ trống");
                    return;
                }
                String ghiChu = txtGhiChu.getText();
                BufferedWriter bufferedWriter = null;
                String noiDung = "\nThêm kho: " + tenKho + "|" + java.time.LocalDate.now() + "|Không";
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
                KhoLuuTru khoLuuTru = new KhoLuuTru(maKho, tenKho, maBoPhan, ghiChu);
                try {
                    khoLuuTruDAO.writeFileAppend(khoLuuTru);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                init();
                KhoLuuTruTable khoLuuTruTable = new KhoLuuTruTable();
                tblKhoLuuTru.setModel(khoLuuTruTable);
                txtMaKho.setText("");
                txtTenKho.setText("");
                txtMaBoPhan.setText("");
                txtGhiChu.setText("");
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaKho.getText().equals("")) {
                    JOptionPane.showMessageDialog(KhoLuuTruForm.this, "Mã kho không được bỏ trống");
                    return;
                }
                String maKho = txtMaKho.getText();
                if (txtTenKho.getText().equals("")) {
                    JOptionPane.showMessageDialog(KhoLuuTruForm.this, "Tên kho không được bỏ trống");
                    return;
                }
                String tenKho = txtTenKho.getText();
                if (txtMaBoPhan.getText().equals("")) {
                    JOptionPane.showMessageDialog(KhoLuuTruForm.this, "Mã bộ phận không được bỏ trống");
                    return;
                }
                String maBoPhan = txtMaBoPhan.getText();
                if (txtGhiChu.getText().equals("")) {
                    JOptionPane.showMessageDialog(KhoLuuTruForm.this, "Ghi chú không được bỏ trống");
                    return;
                }
                String ghiChu = txtGhiChu.getText();

                khoLuuTruSelected = new KhoLuuTru(maKho, tenKho, maBoPhan, ghiChu);
                BufferedWriter bufferedWriter = null;
                String noiDung = "\nSửa kho: " + tenKho + "|" + java.time.LocalDate.now() + "|Không";
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
                    khoLuuTruDAO.replaceKhoLuuTru(khoLuuTruSelected, tblKhoLuuTru.getSelectedRow());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                init();
                KhoLuuTruTable khoLuuTruTable = new KhoLuuTruTable();
                tblKhoLuuTru.setModel(khoLuuTruTable);
                txtMaKho.setText("");
                txtTenKho.setText("");
                txtMaBoPhan.setText("");
                txtGhiChu.setText("");
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter bufferedWriter = null;
                    String noiDung = "\nXoá kho: " + khoLuuTruDAO.getKhoLuuTruList().get(tblKhoLuuTru.getSelectedRow()).getTenKho() + "|" + java.time.LocalDate.now() + "|Không";
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
                    khoLuuTruDAO.deleteKhoLuuTru(tblKhoLuuTru.getSelectedRow());
                    init();
                    KhoLuuTruTable khoLuuTruTable = new KhoLuuTruTable();
                    tblKhoLuuTru.setModel(khoLuuTruTable);
                    txtMaKho.setText("");
                    txtTenKho.setText("");
                    txtMaBoPhan.setText("");
                    txtGhiChu.setText("");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static final String DATA_FILE_PATH = "src\\Data\\KhoLuuTru.txt";
    File file = new File(DATA_FILE_PATH);
    String absolutePath = file.getAbsolutePath();

    public void loadDataFromFile() throws IOException {
        try {
            KhoLuuTru x = null;
            bufferedReader = new BufferedReader(new FileReader(absolutePath));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new KhoLuuTru(a[0], a[1], a[2], a[3]);
                khoLuuTruDAO.getKhoLuuTruList().add(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
        }
    }

    public void init() {
        khoLuuTruDAO = new KhoLuuTruDAO();
        try {
            loadDataFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class KhoLuuTruTable extends AbstractTableModel { //khai báo class để tạo bảng dữ liệu kho lưu trữ
        private String[] COLUMNS = {"Mã kho", "Tên kho", "Mã bộ phận", "Ghi chú"};
        private List<KhoLuuTru> khoLuuTruList = khoLuuTruDAO.getKhoLuuTruList();

        @Override
        public int getRowCount() {
            return khoLuuTruList.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> khoLuuTruList.get(rowIndex).getMaKho();
                case 1 -> khoLuuTruList.get(rowIndex).getTenKho();
                case 2 -> khoLuuTruList.get(rowIndex).getMaBoPhan();
                case 3 -> khoLuuTruList.get(rowIndex).getGhiChu();
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
}
