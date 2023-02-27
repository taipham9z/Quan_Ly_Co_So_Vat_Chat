package Form;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.List;
import Class.*;
public class DanhSachPhong extends JFrame{
    private JPanel mainPanel;
    private JTable tblPhong;
    private JButton btnTrangChu;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnReset;
    private JTextField txtTenPhong;
    private JTextField txtTenGV;
    private JTextField txtViTri;
    private JTextField txtMaPhong;
    static PhongDAO phongDAO = new PhongDAO();
    BufferedReader bufferedReader;
    Phong phongSelected;
    public DanhSachPhong(){
        setContentPane(mainPanel);
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
        PhongTable phongTable = new PhongTable();
        tblPhong.setModel(phongTable);
        btnTrangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrangChu().setVisible(true);
                dispose();
            }
        });
        tblPhong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                phongSelected = phongDAO.getPhongList().get(tblPhong.getSelectedRow());
                txtTenPhong.setText(phongSelected.getTenPhong());
                txtMaPhong.setText(phongSelected.getMaPhong());
                txtTenGV.setText(phongSelected.getGiaoVienQL());
                txtViTri.setText(phongSelected.getViTri());
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtTenPhong.getText().equals("")){
                    JOptionPane.showMessageDialog(DanhSachPhong.this, "Tên phòng không được bỏ trống");
                    return;
                }
                String tenPhong = txtTenPhong.getText();
                if(txtMaPhong.getText().equals("")){
                    JOptionPane.showMessageDialog(DanhSachPhong.this, "Mã phòng không được bỏ trống");
                    return;
                }
                String maPhong = txtMaPhong.getText();
                if(txtTenGV.getText().equals("")){
                    JOptionPane.showMessageDialog(DanhSachPhong.this, "Tên giáo viên không được bỏ trống");
                    return;
                }
                String tenGv = txtTenGV.getText();
                if(txtViTri.getText().equals("")){
                    JOptionPane.showMessageDialog(DanhSachPhong.this, "Vị trí không được bỏ trống");
                    return;
                }
                String viTri = txtViTri.getText();
                Phong a = new Phong(tenPhong, maPhong, tenGv, viTri);
                BufferedWriter bufferedWriter = null;
                String noiDung = "\nThêm phòng: " + tenPhong + "|" + java.time.LocalDate.now() + "|Không";
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
                    phongDAO.writeFileAppend(a, "src\\Data\\Phong.txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                init();
                PhongTable phongTable = new PhongTable();
                tblPhong.setModel(phongTable);
                txtTenPhong.setText("");
                txtMaPhong.setText("");
                txtTenGV.setText("");
                txtViTri.setText("");
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtTenPhong.getText().equals("")){
                    JOptionPane.showMessageDialog(DanhSachPhong.this, "Tên phòng không được bỏ trống");
                    return;
                }
                String tenPhong = txtTenPhong.getText();
                if(txtMaPhong.getText().equals("")){
                    JOptionPane.showMessageDialog(DanhSachPhong.this, "Mã phòng không được bỏ trống");
                    return;
                }
                String maPhong = txtMaPhong.getText();
                if(txtTenGV.getText().equals("")){
                    JOptionPane.showMessageDialog(DanhSachPhong.this, "Tên giáo viên không được bỏ trống");
                    return;
                }
                String tenGv = txtTenGV.getText();
                if(txtViTri.getText().equals("")){
                    JOptionPane.showMessageDialog(DanhSachPhong.this, "Vị trí không được bỏ trống");
                    return;
                }
                String viTri = txtViTri.getText();
                Phong a = new Phong(tenPhong, maPhong, tenGv, viTri);
                BufferedWriter bufferedWriter = null;
                String noiDung = "\nSửa phòng: " + tenPhong + "|" + java.time.LocalDate.now() + "|Không";
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
                    phongDAO.replacePhong(a, tblPhong.getSelectedRow());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                init();
                PhongTable phongTable = new PhongTable();
                tblPhong.setModel(phongTable);
                txtTenPhong.setText("");
                txtMaPhong.setText("");
                txtTenGV.setText("");
                txtViTri.setText("");
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedWriter bufferedWriter = null;
                String noiDung = "\nXoá phòng: " + phongSelected.getTenPhong() + "|" + java.time.LocalDate.now() + "|Không";
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
                    phongDAO.deletePhong(tblPhong.getSelectedRow());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                init();
                PhongTable phongTable = new PhongTable();
                tblPhong.setModel(phongTable);
                txtTenPhong.setText("");
                txtMaPhong.setText("");
                txtTenGV.setText("");
                txtViTri.setText("");


            }
        });
    }
    public static final String DATA_FILE_PATH = "src\\Data\\Phong.txt";
    File file = new File(DATA_FILE_PATH);
    String absolutePath = file.getAbsolutePath();
    public void loadDataFromFile() throws IOException {
        try {
            Phong x;
            bufferedReader = new BufferedReader(new FileReader(absolutePath));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new Phong(a[0], a[1], a[2], a[3]);
                phongDAO.getPhongList().add(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
        }
    }

    public void init() {
        phongDAO = new PhongDAO();
        try {
            loadDataFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static class PhongTable extends AbstractTableModel{
        private final String[] COLUMNS = {"Tên phòng", "Mã phòng","Tên giáo viên quản lý", "Vị trí"};
        private final List<Phong> phongList = phongDAO.getPhongList();

        @Override
        public int getRowCount() {
            return phongList.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> phongList.get(rowIndex).getTenPhong();
                case 1 -> phongList.get(rowIndex).getMaPhong();
                case 2 -> phongList.get(rowIndex).getGiaoVienQL();
                case 3 -> phongList.get(rowIndex).getViTri();
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
