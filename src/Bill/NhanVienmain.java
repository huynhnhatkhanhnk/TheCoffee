/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bill;

;



import Main.*;
import java.awt.Color;

import javax.swing.JFrame;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Login.LoginForm;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * s
 *
 * @author Manh
 */


public class NhanVienmain extends javax.swing.JFrame {

    /**
     * Creates new form Quanlykhachsan
     */
    ResultSet rs;
    Helper.DBHelper db = new Helper.DBHelper();
    SetImage img = new SetImage();
    Connection con = db.getCon();
    PreparedStatement ps;
    String hoten = "";

    public NhanVienmain(String name) {
        initComponents();
        banhang(name);
        setLocationRelativeTo(null);
        bill(name);
        hoten = name;
        clock();
        ImageIcon img = new ImageIcon("src//Icon//sanpham.png");
        this.setIconImage(img.getImage());

    }

    int a;
    int b;
     SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

    public void bill(String name) {

        txtEmpName.setText(name);
        con = db.getCon();
        try {
            String sql = "select * from Employee where NameEmp=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next() == true) {
                String image = rs.getString(9);
                img.setImageLabel(lbHinh, "image//" + image);
            }

        } catch (Exception e) {
            System.out.println("không thành công ");
        }
    }
             public void clock() {
        Thread clock = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Date t = new Date();
                        lbTime.setText(ft.format(t));
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        clock.start();
    }

    public void chitiethoadon(String name) {

    }

    public void banhang(String name) {

        Quanlybanhang nv = new Quanlybanhang(name);
        nv.setSize(900, 640);
        Hienthi.removeAll();
        Hienthi.add(nv);
        Hienthi.revalidate();
        Hienthi.repaint();
        banhang.setBackground(new Color(51, 51, 255));
        dangxuat.setBackground(new Color(51, 51, 51));
        gioithieu.setBackground(new Color(51, 51, 51));
        doimatkhau.setBackground(new Color(51, 51, 51));
        chitiethoadon.setBackground(new Color(51, 51, 51));
        tttk.setBackground(new Color(51, 51, 51));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Hienthi = new javax.swing.JPanel();
        Tieude = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        menu = new javax.swing.JPanel();
        banhang = new javax.swing.JPanel();
        ten = new javax.swing.JLabel();
        hinhanh = new javax.swing.JLabel();
        gioithieu = new javax.swing.JPanel();
        ten3 = new javax.swing.JLabel();
        hinhanh3 = new javax.swing.JLabel();
        tttk = new javax.swing.JPanel();
        ten11 = new javax.swing.JLabel();
        hinhanh11 = new javax.swing.JLabel();
        chitiethoadon = new javax.swing.JPanel();
        ten12 = new javax.swing.JLabel();
        hinhanh12 = new javax.swing.JLabel();
        doimatkhau = new javax.swing.JPanel();
        ten14 = new javax.swing.JLabel();
        hinhanh14 = new javax.swing.JLabel();
        dangxuat = new javax.swing.JPanel();
        ten15 = new javax.swing.JLabel();
        hinhanh15 = new javax.swing.JLabel();
        lbHinh = new javax.swing.JLabel();
        txtEmpName = new javax.swing.JLabel();
        lbTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Hienthi.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout HienthiLayout = new javax.swing.GroupLayout(Hienthi);
        Hienthi.setLayout(HienthiLayout);
        HienthiLayout.setHorizontalGroup(
            HienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        HienthiLayout.setVerticalGroup(
            HienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        jPanel1.add(Hienthi, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 900, 620));

        Tieude.setBackground(new java.awt.Color(0, 153, 255));
        Tieude.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("x");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("-");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout TieudeLayout = new javax.swing.GroupLayout(Tieude);
        Tieude.setLayout(TieudeLayout);
        TieudeLayout.setHorizontalGroup(
            TieudeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TieudeLayout.createSequentialGroup()
                .addContainerGap(839, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(32, 32, 32))
        );
        TieudeLayout.setVerticalGroup(
            TieudeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TieudeLayout.createSequentialGroup()
                .addGroup(TieudeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jPanel1.add(Tieude, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 920, 50));

        menu.setBackground(new java.awt.Color(51, 51, 51));

        banhang.setBackground(new java.awt.Color(51, 51, 51));
        banhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                banhangMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                banhangMousePressed(evt);
            }
        });
        banhang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ten.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ten.setForeground(new java.awt.Color(255, 255, 255));
        ten.setText("Bán hàng");
        banhang.add(ten, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 80, 20));

        hinhanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/8351436011604779598-32.png"))); // NOI18N
        banhang.add(hinhanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        gioithieu.setBackground(new java.awt.Color(51, 51, 51));
        gioithieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gioithieuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                gioithieuMousePressed(evt);
            }
        });
        gioithieu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ten3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ten3.setForeground(new java.awt.Color(255, 255, 255));
        ten3.setText("Giới thiệu phần mềm");
        gioithieu.add(ten3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 150, 20));

        hinhanh3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/3822184331605647683-32.png"))); // NOI18N
        gioithieu.add(hinhanh3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 40));

        tttk.setBackground(new java.awt.Color(51, 51, 51));
        tttk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tttkMousePressed(evt);
            }
        });
        tttk.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ten11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ten11.setForeground(new java.awt.Color(255, 255, 255));
        ten11.setText("Thông tin tài khoản");
        tttk.add(ten11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 140, 20));

        hinhanh11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/7947586491595453760-32.png"))); // NOI18N
        tttk.add(hinhanh11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        chitiethoadon.setBackground(new java.awt.Color(51, 51, 51));
        chitiethoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chitiethoadonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chitiethoadonMousePressed(evt);
            }
        });
        chitiethoadon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ten12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ten12.setForeground(new java.awt.Color(255, 255, 255));
        ten12.setText("Chi tiết hóa đơn");
        chitiethoadon.add(ten12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 120, 20));

        hinhanh12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/1859632791581424134-32.png"))); // NOI18N
        chitiethoadon.add(hinhanh12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        doimatkhau.setBackground(new java.awt.Color(51, 51, 51));
        doimatkhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                doimatkhauMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                doimatkhauMousePressed(evt);
            }
        });
        doimatkhau.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ten14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ten14.setForeground(new java.awt.Color(255, 255, 255));
        ten14.setText("Đổi mật khẩu");
        doimatkhau.add(ten14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 100, 20));

        hinhanh14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/2932273771606062170-32.png"))); // NOI18N
        doimatkhau.add(hinhanh14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        dangxuat.setBackground(new java.awt.Color(51, 51, 51));
        dangxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dangxuatMousePressed(evt);
            }
        });
        dangxuat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dangxuatKeyPressed(evt);
            }
        });
        dangxuat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ten15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ten15.setForeground(new java.awt.Color(255, 255, 255));
        ten15.setText("Đăng xuất");
        dangxuat.add(ten15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 80, 30));

        hinhanh15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/13723863511537355605-32.png"))); // NOI18N
        dangxuat.add(hinhanh15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lbHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHinh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtEmpName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtEmpName.setForeground(new java.awt.Color(255, 255, 255));
        txtEmpName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtEmpName.setText("ADMIN");

        lbTime.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTime.setForeground(new java.awt.Color(255, 0, 51));
        lbTime.setText("jLabel3");

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(banhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gioithieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tttk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(chitiethoadon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(doimatkhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dangxuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmpName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmpName)
                .addGap(30, 30, 30)
                .addComponent(banhang, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(tttk, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gioithieu, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chitiethoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doimatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        jPanel1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1105, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void banhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banhangMouseClicked

    }//GEN-LAST:event_banhangMouseClicked

    private void chitiethoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chitiethoadonMouseClicked

    }//GEN-LAST:event_chitiethoadonMouseClicked

    private void doimatkhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doimatkhauMouseClicked

    }//GEN-LAST:event_doimatkhauMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        a = evt.getX();
        b = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - a, y - b);
    }//GEN-LAST:event_formMouseDragged

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void banhangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banhangMousePressed
        banhang(hoten);


    }//GEN-LAST:event_banhangMousePressed

    private void gioithieuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gioithieuMousePressed
        GioiThieuBill ls = new GioiThieuBill();
        ls.setSize(900, 640);
        Hienthi.removeAll();
        Hienthi.add(ls);
        Hienthi.revalidate();
        Hienthi.repaint();
        gioithieu.setBackground(new Color(51, 51, 255));
        dangxuat.setBackground(new Color(51, 51, 51));
        banhang.setBackground(new Color(51, 51, 51));
        doimatkhau.setBackground(new Color(51, 51, 51));
        chitiethoadon.setBackground(new Color(51, 51, 51));
        tttk.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_gioithieuMousePressed

    private void chitiethoadonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chitiethoadonMousePressed
        Chitiethoadon hd = new Chitiethoadon();
        hd.setSize(900, 640);
        Hienthi.removeAll();
        Hienthi.add(hd);
        Hienthi.revalidate();
        Hienthi.repaint();
        chitiethoadon.setBackground(new Color(51, 51, 255));
        dangxuat.setBackground(new Color(51, 51, 51));
        gioithieu.setBackground(new Color(51, 51, 51));
        doimatkhau.setBackground(new Color(51, 51, 51));
        banhang.setBackground(new Color(51, 51, 51));
        tttk.setBackground(new Color(51, 51, 51));

    }//GEN-LAST:event_chitiethoadonMousePressed

    private void doimatkhauMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doimatkhauMousePressed
        DoimatkhauNV dmt = new DoimatkhauNV(hoten);
        dmt.setSize(900, 640);
        Hienthi.removeAll();
        Hienthi.add(dmt);
        Hienthi.revalidate();
        Hienthi.repaint();
        doimatkhau.setBackground(new Color(51, 51, 255));
        dangxuat.setBackground(new Color(51, 51, 51));
        gioithieu.setBackground(new Color(51, 51, 51));
        banhang.setBackground(new Color(51, 51, 51));
        chitiethoadon.setBackground(new Color(51, 51, 51));
        tttk.setBackground(new Color(51, 51, 51));

    }//GEN-LAST:event_doimatkhauMousePressed

    private void tttkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tttkMousePressed
        Thongtintaikhoan sp = new Thongtintaikhoan(hoten);
        sp.setSize(900, 640);
        Hienthi.removeAll();
        Hienthi.add(sp);
        Hienthi.revalidate();
        Hienthi.repaint();

        tttk.setBackground(new Color(51, 51, 255));
        dangxuat.setBackground(new Color(51, 51, 51));

        gioithieu.setBackground(new Color(51, 51, 51));
        doimatkhau.setBackground(new Color(51, 51, 51));
        chitiethoadon.setBackground(new Color(51, 51, 51));
        banhang.setBackground(new Color(51, 51, 51));


    }//GEN-LAST:event_tttkMousePressed

    private void gioithieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gioithieuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_gioithieuMouseClicked

    private void dangxuatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dangxuatKeyPressed

    }//GEN-LAST:event_dangxuatKeyPressed

    private void dangxuatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dangxuatMousePressed
        // TODO add your handling code here:
        dangxuat.setBackground(new Color(51, 51, 255));
        tttk.setBackground(new Color(51, 51, 51));
        gioithieu.setBackground(new Color(51, 51, 51));
        doimatkhau.setBackground(new Color(51, 51, 51));
        chitiethoadon.setBackground(new Color(51, 51, 51));
        banhang.setBackground(new Color(51, 51, 51));
        int click = JOptionPane.showConfirmDialog(null, "Đăng xuất ngay bây giờ?");
        if (click == 0) {
            this.setVisible(false);
            new LoginForm().setVisible(true);
        }
    }//GEN-LAST:event_dangxuatMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NhanVienmain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienmain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienmain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienmain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Hienthi;
    private javax.swing.JPanel Tieude;
    private javax.swing.JPanel banhang;
    private javax.swing.JPanel chitiethoadon;
    private javax.swing.JPanel dangxuat;
    private javax.swing.JPanel doimatkhau;
    private javax.swing.JPanel gioithieu;
    private javax.swing.JLabel hinhanh;
    private javax.swing.JLabel hinhanh11;
    private javax.swing.JLabel hinhanh12;
    private javax.swing.JLabel hinhanh14;
    private javax.swing.JLabel hinhanh15;
    private javax.swing.JLabel hinhanh3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbHinh;
    private javax.swing.JLabel lbTime;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel ten;
    private javax.swing.JLabel ten11;
    private javax.swing.JLabel ten12;
    private javax.swing.JLabel ten14;
    private javax.swing.JLabel ten15;
    private javax.swing.JLabel ten3;
    private javax.swing.JPanel tttk;
    private javax.swing.JLabel txtEmpName;
    // End of variables declaration//GEN-END:variables
}
