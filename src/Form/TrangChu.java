package Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrangChu extends JFrame{

    private JButton btnThongKeTS;
    private JButton btnPhieuChuyen;
    private JButton btnKhoLuuTru;
    private JButton btnQuanLy;
    private JButton btnDangXuat;
    private JPanel mainPanel;
    private JLabel labelTrangChu;
    private JButton btnBaoCao;
    private JButton btnDanhSachPhong;

    public TrangChu(){
        setContentPane(mainPanel);
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //labelTrangChu.setText("<html><font color='red'>Trang chủ</font></html>");
        btnThongKeTS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CSVCForm().setVisible(true);
                dispose();
            }
        });
        btnDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DangNhap().setVisible(true);
                dispose();
            }
        });
        btnPhieuChuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PhieuChuyenTaiSan().setVisible(true);
                dispose();
            }
        });
        btnKhoLuuTru.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new KhoLuuTruForm().setVisible(true);
                dispose();
            }
        });
        btnQuanLy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ThongTinTaiKhoan().setVisible(true);
                dispose();
            }
        });
        btnBaoCao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LichSuThayDoiForm().setVisible(true);
                dispose();
            }
        });
        btnDanhSachPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DanhSachPhong().setVisible(true);
                dispose();
            }
        });
    }
}
