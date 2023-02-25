package Form;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.List;

import Class.PhieuChuyen;
import Class.PhieuChuyenDAO;

public class PhieuChuyenTaiSan extends JFrame {

    private JTextField txtNgayChuyen;
    private JTextField txtNoiNhan;
    private JTextField txtSoLuong;
    private JTextField txtMaPhieu;
    private JTextField txtMaTsVanChuyen;
    private JTextField txtGiaoVienChuyen;
    private JButton btnTrangChu;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnReset;
    private JTable tblPhieuChuyen;
    private JPanel mainPanel;
    static PhieuChuyenDAO phieuChuyenDAO;
    private BufferedReader bufferedReader;
    PhieuChuyen phieuChuyenSelected;

    public PhieuChuyenTaiSan() {
        setContentPane(mainPanel);
        setSize(1500, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
        PhieuChuyenTable phieuChuyenTable = new PhieuChuyenTable();
        tblPhieuChuyen.setModel(phieuChuyenTable);
        tblPhieuChuyen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                phieuChuyenSelected = phieuChuyenDAO.getPhieuChuyenList().get(tblPhieuChuyen.getSelectedRow());
                txtMaPhieu.setText(phieuChuyenSelected.getMaPhieu());
                txtMaTsVanChuyen.setText(phieuChuyenSelected.getMaTsChuyen());
                txtGiaoVienChuyen.setText(phieuChuyenSelected.getGiaoVienChuyen());
                txtNgayChuyen.setText(phieuChuyenSelected.getNgayChuyen());
                txtNoiNhan.setText(phieuChuyenSelected.getNoiNhan());
                txtSoLuong.setText(String.valueOf(phieuChuyenSelected.getSoLuong()));
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
                PhieuChuyenTable phieuChuyenTable = new PhieuChuyenTable();
                tblPhieuChuyen.setModel(phieuChuyenTable);
                txtMaPhieu.setText("");
                txtMaTsVanChuyen.setText("");
                txtGiaoVienChuyen.setText("");
                txtNgayChuyen.setText("");
                txtNoiNhan.setText("");
                txtSoLuong.setText("");
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaPhieu.getText().equals("")) {
                    JOptionPane.showMessageDialog(PhieuChuyenTaiSan.this, "Tên tài sản không bỏ trống");
                    return;
                }
                String maPhieu = txtMaPhieu.getText();
                if (txtMaTsVanChuyen.getText().equals("")) {
                    JOptionPane.showMessageDialog(PhieuChuyenTaiSan.this, "Tên nhân viên không bỏ trống");
                    return;
                }
                String maTsVanChuyen = txtMaTsVanChuyen.getText();

                if (txtGiaoVienChuyen.getText().equals("")) {
                    JOptionPane.showMessageDialog(PhieuChuyenTaiSan.this, "Chức vụ không bỏ trống");
                    return;
                }
                String giaoVienChuyen = txtGiaoVienChuyen.getText();
                if (txtNgayChuyen.getText().equals("")) {
                    JOptionPane.showMessageDialog(PhieuChuyenTaiSan.this, "Phòng ban không bỏ trống");
                    return;
                }
                String ngayChuyen = txtNgayChuyen.getText();
                if (txtNoiNhan.getText().equals("")) {
                    JOptionPane.showMessageDialog(PhieuChuyenTaiSan.this, "SDT không bỏ trống");
                    return;
                }
                String noiNhan = txtNoiNhan.getText();
                if (txtSoLuong.getText().equals("")) {
                    JOptionPane.showMessageDialog(PhieuChuyenTaiSan.this, "Địa chỉ không bỏ trống");
                    return;
                }
                int soLuong = Integer.parseInt(txtSoLuong.getText());

                PhieuChuyen phieuChuyen = new PhieuChuyen(maPhieu, maTsVanChuyen, giaoVienChuyen, ngayChuyen, noiNhan, soLuong);
                BufferedWriter bufferedWriter = null;
                String noiDung = "\nThêm phiếu: " + maPhieu + "|" + java.time.LocalDate.now() + "|Không";
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
                    phieuChuyenDAO.writeFile(phieuChuyen);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                init();
                PhieuChuyenTable phieuChuyenTable = new PhieuChuyenTable();
                tblPhieuChuyen.setModel(phieuChuyenTable);
                txtMaPhieu.setText("");
                txtMaTsVanChuyen.setText("");
                txtGiaoVienChuyen.setText("");
                txtNgayChuyen.setText("");
                txtNoiNhan.setText("");
                txtSoLuong.setText("");
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaPhieu.getText().equals("")) {
                    JOptionPane.showMessageDialog(PhieuChuyenTaiSan.this, "Tên tài sản không bỏ trống");
                    return;
                }
                String maPhieu = txtMaPhieu.getText();
                if (txtMaTsVanChuyen.getText().equals("")) {
                    JOptionPane.showMessageDialog(PhieuChuyenTaiSan.this, "Tên nhân viên không bỏ trống");
                    return;
                }
                String maTsVanChuyen = txtMaTsVanChuyen.getText();

                if (txtGiaoVienChuyen.getText().equals("")) {
                    JOptionPane.showMessageDialog(PhieuChuyenTaiSan.this, "Chức vụ không bỏ trống");
                    return;
                }
                String giaoVienChuyen = txtGiaoVienChuyen.getText();
                if (txtNgayChuyen.getText().equals("")) {
                    JOptionPane.showMessageDialog(PhieuChuyenTaiSan.this, "Phòng ban không bỏ trống");
                    return;
                }
                String ngayChuyen = txtNgayChuyen.getText();
                if (txtNoiNhan.getText().equals("")) {
                    JOptionPane.showMessageDialog(PhieuChuyenTaiSan.this, "SDT không bỏ trống");
                    return;
                }
                String noiNhan = txtNoiNhan.getText();
                if (txtSoLuong.getText().equals("")) {
                    JOptionPane.showMessageDialog(PhieuChuyenTaiSan.this, "Địa chỉ không bỏ trống");
                    return;
                }
                int soLuong = Integer.parseInt(txtSoLuong.getText());

                phieuChuyenSelected = new PhieuChuyen(maPhieu, maTsVanChuyen, giaoVienChuyen, ngayChuyen, noiNhan, soLuong);
                BufferedWriter bufferedWriter = null;
                String noiDung = "\nSửa phiếu mã: " + maPhieu + "|" + java.time.LocalDate.now() + "|Không";
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
                    phieuChuyenDAO.replacePhieuChuyen(phieuChuyenSelected, tblPhieuChuyen.getSelectedRow());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                init();
                PhieuChuyenTable phieuChuyenTable = new PhieuChuyenTable();
                tblPhieuChuyen.setModel(phieuChuyenTable);
                txtMaPhieu.setText("");
                txtMaTsVanChuyen.setText("");
                txtGiaoVienChuyen.setText("");
                txtNgayChuyen.setText("");
                txtNoiNhan.setText("");
                txtSoLuong.setText("");
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter bufferedWriter = null;
                    String noiDung = "\nXoá phiếu mã: " + phieuChuyenDAO.getPhieuChuyenList().get(tblPhieuChuyen.getSelectedRow()).getMaPhieu() + "|" + java.time.LocalDate.now() + "|Không";
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
                    phieuChuyenDAO.deletePhieuChuyen(tblPhieuChuyen.getSelectedRow());
                    init();
                    PhieuChuyenTable phieuChuyenTable = new PhieuChuyenTable();
                    tblPhieuChuyen.setModel(phieuChuyenTable);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static final String DATA_FILE_PATH = "src\\Data\\PhieuChuyen.txt";
    File file = new File(DATA_FILE_PATH);
    String absolutePath = file.getAbsolutePath();

    public void loadDataFromFile() throws IOException {
        try {
            PhieuChuyen x = null;
            bufferedReader = new BufferedReader(new FileReader(absolutePath));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new PhieuChuyen(a[0], a[1], a[2], a[3], a[4], Integer.parseInt(a[5]));
                phieuChuyenDAO.getPhieuChuyenList().add(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
        }
    }

    public void init() {
        phieuChuyenDAO = new PhieuChuyenDAO();
        try {
            loadDataFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class PhieuChuyenTable extends AbstractTableModel {
        private String[] COLUMNS = {"Mã phiếu", "Mã tài sản", "Giáo viên chuyển", "Ngày chuyển", "Nơi nhận", "Số lượng chuyển"};
        private List<PhieuChuyen> phieuChuyenList = phieuChuyenDAO.getPhieuChuyenList();

        @Override
        public int getRowCount() {
            return phieuChuyenList.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> phieuChuyenList.get(rowIndex).getMaPhieu();
                case 1 -> phieuChuyenList.get(rowIndex).getMaTsChuyen();
                case 2 -> phieuChuyenList.get(rowIndex).getGiaoVienChuyen();
                case 3 -> phieuChuyenList.get(rowIndex).getNgayChuyen();
                case 4 -> phieuChuyenList.get(rowIndex).getNoiNhan();
                case 5 -> phieuChuyenList.get(rowIndex).getSoLuong();
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
