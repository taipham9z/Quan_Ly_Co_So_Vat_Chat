package Form;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import Class.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TimKiem extends JFrame{
    private JTable tblCSVC;
    private JTextField txtMaCSVC;
    private JTextField txtTenCSVC;
    private JButton btnTKTheoMa;
    private JButton btnTKTheoTen;
    private JPanel mainPanel;
    private JButton btnTrangChu;
    private JButton btnReset;
    private JTextField txtTinhTrang;
    private JTextField txtNuocSanXuat;
    private JButton btnTKTinhTrang;
    private JButton btnTKNuocSx;
    private static List<CSVC> csvc;
    private static List<CSVC> csvc1;
    public TimKiem(){
        setContentPane(mainPanel);
        setSize(1500, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        readFile();
        CSVCTable csvcTable = new CSVCTable();
        tblCSVC.setModel(csvcTable);
        btnTKTheoMa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                csvc1 = new ArrayList<>();
                for (int i = 0; i < csvc.size(); i++) {
                    if(txtMaCSVC.getText().equals(csvc.get(i).getMaTs())){
                        csvc1.add(csvc.get(i));
                    }
                }
                csvc = csvc1;
                CSVCTable csvcTable = new CSVCTable();
                tblCSVC.setModel(csvcTable);
            }
        });
        btnTKTheoTen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                csvc1 = new ArrayList<>();
                for (int i = 0; i < csvc.size(); i++) {
                    if(txtTenCSVC.getText().equals(csvc.get(i).getTenTs())){
                        csvc1.add(csvc.get(i));
                    }
                }
                csvc = csvc1;
                CSVCTable csvcTable = new CSVCTable();
                tblCSVC.setModel(csvcTable);
            }
        });
        btnTKTinhTrang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                csvc1 = new ArrayList<>();
                for (int i = 0; i < csvc.size(); i++) {
                    if(txtTinhTrang.getText().equals(csvc.get(i).getTinhTrang())){
                        csvc1.add(csvc.get(i));
                    }
                }
                csvc = csvc1;
                CSVCTable csvcTable = new CSVCTable();
                tblCSVC.setModel(csvcTable);
            }
        });
        btnTKNuocSx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                csvc1 = new ArrayList<>();
                for (int i = 0; i < csvc.size(); i++) {
                    if(txtNuocSanXuat.getText().equals(csvc.get(i).getNuocSx())){
                        csvc1.add(csvc.get(i));
                    }
                }
                csvc = csvc1;
                CSVCTable csvcTable = new CSVCTable();
                tblCSVC.setModel(csvcTable);
            }
        });
        btnTrangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CSVCForm().setVisible(true);
                dispose();
            }
        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFile();
                CSVCTable csvcTable = new CSVCTable();
                tblCSVC.setModel(csvcTable);
                txtMaCSVC.setText("");
                txtTenCSVC.setText("");
                txtNuocSanXuat.setText("");
                txtTinhTrang.setText("");
            }
        });
    }
    public void readFile(){
        BufferedReader reader = null;
        csvc = new ArrayList<>();
        try {
            CSVC x = null;
            reader = new BufferedReader(new FileReader( new File("src\\Data\\CSVC.txt")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] a = line.split("\\|");
                x = new CSVC(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], Integer.parseInt(a[8]), Integer.parseInt(a[9]));
                csvc.add(x);
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
    public static class CSVCTable extends AbstractTableModel {
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
