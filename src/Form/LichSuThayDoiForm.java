package Form;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Class.LichSuThayDoi;
public class LichSuThayDoiForm extends JFrame{
    private JTable tblBaoCao;
    private JPanel mainPanel;
    private JButton btnTrangChu;
    private static List<LichSuThayDoi> lichSuThayDoiList;
    public LichSuThayDoiForm(){
        setContentPane(mainPanel);
        setSize(1200, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        btnTrangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrangChu().setVisible(true);
                dispose();
            }
        });
        readFileBaoCao();
        BaoCaoTable baoCaoTable = new BaoCaoTable();
        tblBaoCao.setModel(baoCaoTable);

    }
    public void readFileBaoCao(){
        BufferedReader br = null;
        lichSuThayDoiList = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(new File("src\\Data\\BaoCao.txt")));
            String line = "";
            while((line = br.readLine()) !=null){
                String [] a = line.split("\\|");
                LichSuThayDoi x = new LichSuThayDoi(a[0], a[1], a[2]);
                lichSuThayDoiList.add(x);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static class BaoCaoTable extends AbstractTableModel{
        String [] columns = {"Nội dung", "Ngày", "Ghi chú"};

        @Override
        public int getRowCount() {
            return lichSuThayDoiList.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> lichSuThayDoiList.get(rowIndex).getNoiDung();
                case 1 -> lichSuThayDoiList.get(rowIndex).getThoiGian();
                case 2 -> lichSuThayDoiList.get(rowIndex).getGhiChu();
                default ->  "-";
            };
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
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
