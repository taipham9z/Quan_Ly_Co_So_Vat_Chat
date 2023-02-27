package Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

import Class.TaiKhoan;

public class DangNhap extends JFrame {

    private JPanel panel1;
    private JTextField txtTenDangNhap;
    private JPasswordField txtMatKhau;
    private JButton btnDangNhap;

    public DangNhap() {
        setContentPane(panel1); //set màn hình
        setSize(550, 250); //set kích thước màn hình
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //set chức năng đóng
        setLocationRelativeTo(null); //set vị trí là chính giữa màn hình khi xuất hiện (nếu không có sẽ xuất hiện ở góc trên bên trái)
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(DangNhap.this);
        btnDangNhap.addActionListener(new ActionListener() { //thêm sự kiện cho nút đăng nhập
            @Override
            public void actionPerformed(ActionEvent e) {
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setTenDangNhap(txtTenDangNhap.getText());
                taiKhoan.setMatKhau(String.valueOf(txtMatKhau.getPassword()));
                int check = taiKhoan.readFileTaiKhoanQuanTri(); //hàm kiểm tra tài khoản xem có đúng tài khoản quản trị không, đúng gán = 1
                if (check != 1) {
                    JOptionPane.showMessageDialog(DangNhap.this, "Sai tên đăng nhập hoặc mật khẩu"); //hiển thị bảng thông báo với nội dung trong message
                } else {
                    TrangChu trangChu = new TrangChu();
                    trangChu.setVisible(true); //hiển thị màn hình trang chủ
                    dispose(); //thoát màn hình hiện tại
                }
            }

        });
    }
}
