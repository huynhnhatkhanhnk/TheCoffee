/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

;



import Login.LoginForm;
import java.awt.Color;
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


public class Quanlyadmin extends javax.swing.JFrame {

    public Quanlyadmin() {
        initComponents();
        setLocationRelativeTo(null);
        nhavien1();
        clock();    
        ImageIcon img = new ImageIcon("src//Icon//sanpham.png");
        this.setIconImage(img.getImage());

    }
    int a;
    int b;
 SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    public void nhavien1() {

        Nhanvien nv = new Nhanvien();
        nv.setSize(900, 640);
        Hienthi.removeAll();
        Hienthi.add(nv);
        Hienthi.revalidate();
        Hienthi.repaint();
        nhanvien.setBackground(new Color(51, 51, 255));
        lichsu.setBackground(new Color(51, 51, 51));
        doanhthu.setBackground(new Color(51, 51, 51));
        khanhhang.setBackground(new Color(51, 51, 51));
        khuyenmai.setBackground(new Color(51, 51, 51));
        sanpham.setBackground(new Color(51, 51, 51));
        dangxuat.setBackground(new Color(51, 51, 51));
        gioithieu.setBackground(new Color(51, 51, 51));
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nhanvien = new javax.swing.JPanel();
        ten = new javax.swing.JLabel();
        hinhanh = new javax.swing.JLabel();
        lichsu = new javax.swing.JPanel();
        ten3 = new javax.swing.JLabel();
        hinhanh3 = new javax.swing.JLabel();
        sanpham = new javax.swing.JPanel();
        ten11 = new javax.swing.JLabel();
        hinhanh11 = new javax.swing.JLabel();
        khanhhang = new javax.swing.JPanel();
        ten13 = new javax.swing.JLabel();
        hinhanh13 = new javax.swing.JLabel();
        khuyenmai = new javax.swing.JPanel();
        ten14 = new javax.swing.JLabel();
        hinhanh14 = new javax.swing.JLabel();
        doanhthu = new javax.swing.JPanel();
        ten16 = new javax.swing.JLabel();
        hinhanh16 = new javax.swing.JLabel();
        gioithieu = new javax.swing.JPanel();
        ten17 = new javax.swing.JLabel();
        hinhanh17 = new javax.swing.JLabel();
        dangxuat = new javax.swing.JPanel();
        ten15 = new javax.swing.JLabel();
        hinhanh15 = new javax.swing.JLabel();
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
            .addGap(0, 610, Short.MAX_VALUE)
        );

        jPanel1.add(Hienthi, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 900, 610));

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
        menu.setFocusCycleRoot(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinhanh/login-icon.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ADMIN");

        nhanvien.setBackground(new java.awt.Color(51, 51, 51));
        nhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nhanvienMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nhanvienMousePressed(evt);
            }
        });
        nhanvien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ten.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ten.setForeground(new java.awt.Color(255, 255, 255));
        ten.setText("Nh??n Vi??n");
        nhanvien.add(ten, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 80, 20));

        hinhanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/8025287921598811056-32.png"))); // NOI18N
        nhanvien.add(hinhanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lichsu.setBackground(new java.awt.Color(51, 51, 51));
        lichsu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lichsuMousePressed(evt);
            }
        });
        lichsu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ten3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ten3.setForeground(new java.awt.Color(255, 255, 255));
        ten3.setText("L???ch s??? b??n h??ng");
        lichsu.add(ten3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 120, 20));

        hinhanh3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/1859632791581424134-32.png"))); // NOI18N
        lichsu.add(hinhanh3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        sanpham.setBackground(new java.awt.Color(51, 51, 51));
        sanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sanphamMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sanphamMousePressed(evt);
            }
        });
        sanpham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ten11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ten11.setForeground(new java.awt.Color(255, 255, 255));
        ten11.setText("Qu???n l?? s???n ph???m");
        sanpham.add(ten11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 130, 20));

        hinhanh11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/10287517231536062003-32.png"))); // NOI18N
        sanpham.add(hinhanh11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        khanhhang.setBackground(new java.awt.Color(51, 51, 51));
        khanhhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                khanhhangMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                khanhhangMousePressed(evt);
            }
        });
        khanhhang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ten13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ten13.setForeground(new java.awt.Color(255, 255, 255));
        ten13.setText("Kh??ch h??ng");
        khanhhang.add(ten13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 140, 20));

        hinhanh13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/2960511651580123543-32.png"))); // NOI18N
        khanhhang.add(hinhanh13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        khuyenmai.setBackground(new java.awt.Color(51, 51, 51));
        khuyenmai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                khuyenmaiMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                khuyenmaiMousePressed(evt);
            }
        });
        khuyenmai.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ten14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ten14.setForeground(new java.awt.Color(255, 255, 255));
        ten14.setText("Khuy???n m??i");
        khuyenmai.add(ten14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 100, 20));

        hinhanh14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/16034119301605654874-32.png"))); // NOI18N
        khuyenmai.add(hinhanh14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        doanhthu.setBackground(new java.awt.Color(51, 51, 51));
        doanhthu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                doanhthuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                doanhthuMousePressed(evt);
            }
        });
        doanhthu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ten16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ten16.setForeground(new java.awt.Color(255, 255, 255));
        ten16.setText("Doanh Thu");
        doanhthu.add(ten16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 80, 20));

        hinhanh16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/6572811661553508672-32.png"))); // NOI18N
        doanhthu.add(hinhanh16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        gioithieu.setBackground(new java.awt.Color(51, 51, 51));
        gioithieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                gioithieuMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                gioithieuMouseReleased(evt);
            }
        });

        ten17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ten17.setForeground(new java.awt.Color(255, 255, 255));
        ten17.setText("Gi???i thi???u");

        hinhanh17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/3822184331605647683-32.png"))); // NOI18N

        javax.swing.GroupLayout gioithieuLayout = new javax.swing.GroupLayout(gioithieu);
        gioithieu.setLayout(gioithieuLayout);
        gioithieuLayout.setHorizontalGroup(
            gioithieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gioithieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhanh17)
                .addGap(29, 29, 29)
                .addComponent(ten17, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gioithieuLayout.setVerticalGroup(
            gioithieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gioithieuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gioithieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hinhanh17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ten17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dangxuat.setBackground(new java.awt.Color(51, 51, 51));
        dangxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dangxuatMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dangxuatMouseReleased(evt);
            }
        });

        ten15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ten15.setForeground(new java.awt.Color(255, 255, 255));
        ten15.setText("????ng xu???t");

        hinhanh15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/13723863511537355605-32.png"))); // NOI18N

        javax.swing.GroupLayout dangxuatLayout = new javax.swing.GroupLayout(dangxuat);
        dangxuat.setLayout(dangxuatLayout);
        dangxuatLayout.setHorizontalGroup(
            dangxuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dangxuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hinhanh15)
                .addGap(29, 29, 29)
                .addComponent(ten15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dangxuatLayout.setVerticalGroup(
            dangxuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dangxuatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dangxuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ten15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hinhanh15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbTime.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTime.setForeground(new java.awt.Color(255, 0, 51));
        lbTime.setText("jLabel3");

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lichsu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(khanhhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(khuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(doanhthu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gioithieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dangxuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(sanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lichsu, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(khanhhang, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(khuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gioithieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );

        jPanel1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1105, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhanvienMouseClicked

    }//GEN-LAST:event_nhanvienMouseClicked

    private void sanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanphamMouseClicked
        SanPham sp = new SanPham();
        sp.setSize(900, 640);
        Hienthi.removeAll();
        Hienthi.add(sp);
        Hienthi.revalidate();
        Hienthi.repaint();
    }//GEN-LAST:event_sanphamMouseClicked

    private void khanhhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khanhhangMouseClicked

    }//GEN-LAST:event_khanhhangMouseClicked

    private void khuyenmaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khuyenmaiMouseClicked

    }//GEN-LAST:event_khuyenmaiMouseClicked

    private void doanhthuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doanhthuMouseClicked

    }//GEN-LAST:event_doanhthuMouseClicked

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

    private void nhanvienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhanvienMousePressed
        nhavien1();

    }//GEN-LAST:event_nhanvienMousePressed

    private void lichsuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lichsuMousePressed
        Lichsu ls = new Lichsu();
        ls.setSize(900, 640);
        Hienthi.removeAll();
        Hienthi.add(ls);
        Hienthi.revalidate();
        Hienthi.repaint();
        nhanvien.setBackground(new Color(51, 51, 51));
        lichsu.setBackground(new Color(51, 51, 255));
        doanhthu.setBackground(new Color(51, 51, 51));
        khanhhang.setBackground(new Color(51, 51, 51));
        khuyenmai.setBackground(new Color(51, 51, 51));
        sanpham.setBackground(new Color(51, 51, 51));
        dangxuat.setBackground(new Color(51, 51, 51));
        gioithieu.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_lichsuMousePressed

    private void khanhhangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khanhhangMousePressed
        ThanhVien tv = new ThanhVien();
        tv.setSize(900, 640);
        Hienthi.removeAll();
        Hienthi.add(tv);
        Hienthi.revalidate();
        Hienthi.repaint();
        nhanvien.setBackground(new Color(51, 51, 51));
        lichsu.setBackground(new Color(51, 51, 51));
        doanhthu.setBackground(new Color(51, 51, 51));
        khanhhang.setBackground(new Color(51, 51, 255));
        khuyenmai.setBackground(new Color(51, 51, 51));
        sanpham.setBackground(new Color(51, 51, 51));
        dangxuat.setBackground(new Color(51, 51, 51));
        gioithieu.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_khanhhangMousePressed

    private void khuyenmaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khuyenmaiMousePressed
        Khuyenmai dmt = new Khuyenmai();
        dmt.setSize(900, 640);
        Hienthi.removeAll();
        Hienthi.add(dmt);
        Hienthi.revalidate();
        Hienthi.repaint();
        nhanvien.setBackground(new Color(51, 51, 51));
        lichsu.setBackground(new Color(51, 51, 51));
        doanhthu.setBackground(new Color(51, 51, 51));
        khanhhang.setBackground(new Color(51, 51, 51));
        khuyenmai.setBackground(new Color(51, 51, 255));
        sanpham.setBackground(new Color(51, 51, 51));
        dangxuat.setBackground(new Color(51, 51, 51));
        gioithieu.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_khuyenmaiMousePressed

    private void sanphamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanphamMousePressed
        SanPham sp = new SanPham();
        sp.setSize(900, 640);
        Hienthi.removeAll();
        Hienthi.add(sp);
        Hienthi.revalidate();
        Hienthi.repaint();
        nhanvien.setBackground(new Color(51, 51, 51));
        lichsu.setBackground(new Color(51, 51, 51));
        doanhthu.setBackground(new Color(51, 51, 51));
        khanhhang.setBackground(new Color(51, 51, 51));
        khuyenmai.setBackground(new Color(51, 51, 51));
        sanpham.setBackground(new Color(51, 51, 255));
        dangxuat.setBackground(new Color(51, 51, 51));
        gioithieu.setBackground(new Color(51, 51, 51));

    }//GEN-LAST:event_sanphamMousePressed

    private void doanhthuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doanhthuMousePressed
        // TODO add your handling code here:

        Doanhthu dt = new Doanhthu();
        dt.setSize(900, 640);
        Hienthi.removeAll();
        Hienthi.add(dt);
        Hienthi.revalidate();
        Hienthi.repaint();
        nhanvien.setBackground(new Color(51, 51, 51));
        lichsu.setBackground(new Color(51, 51, 51));
        doanhthu.setBackground(new Color(51, 51, 255));
        khanhhang.setBackground(new Color(51, 51, 51));
        khuyenmai.setBackground(new Color(51, 51, 51));
        sanpham.setBackground(new Color(51, 51, 51));
        dangxuat.setBackground(new Color(51, 51, 51));
        gioithieu.setBackground(new Color(51, 51, 51));

    }//GEN-LAST:event_doanhthuMousePressed

    private void dangxuatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dangxuatMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_dangxuatMouseReleased

    private void dangxuatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dangxuatMousePressed
        // TODO add your handling code here:
        nhanvien.setBackground(new Color(51, 51, 51));
        lichsu.setBackground(new Color(51, 51, 51));
        doanhthu.setBackground(new Color(51, 51, 51));
        khanhhang.setBackground(new Color(51, 51, 51));
        khuyenmai.setBackground(new Color(51, 51, 51));
        sanpham.setBackground(new Color(51, 51, 51));
        dangxuat.setBackground(new Color(51, 51, 255));
        gioithieu.setBackground(new Color(51, 51, 51));
        int click = JOptionPane.showConfirmDialog(null, "????ng xu???t ngay b??y gi????");
        if (click == 0) {
            this.setVisible(false);
            new LoginForm().setVisible(true);

        }

    }//GEN-LAST:event_dangxuatMousePressed

    private void gioithieuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gioithieuMousePressed
        // TODO add your handling code here:
        Gioithieu dt = new Gioithieu();
        dt.setSize(900, 640);
        Hienthi.removeAll();
        Hienthi.add(dt);
        Hienthi.revalidate();
        Hienthi.repaint();
        gioithieu.setBackground(new Color(51, 51, 255));
        nhanvien.setBackground(new Color(51, 51, 51));
        lichsu.setBackground(new Color(51, 51, 51));
        doanhthu.setBackground(new Color(51, 51, 51));
        khanhhang.setBackground(new Color(51, 51, 51));
        khuyenmai.setBackground(new Color(51, 51, 51));
        sanpham.setBackground(new Color(51, 51, 51));
        dangxuat.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_gioithieuMousePressed

    private void gioithieuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gioithieuMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_gioithieuMouseReleased

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
            java.util.logging.Logger.getLogger(Quanlyadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quanlyadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quanlyadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quanlyadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Quanlyadmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Hienthi;
    private javax.swing.JPanel Tieude;
    private javax.swing.JPanel dangxuat;
    private javax.swing.JPanel doanhthu;
    private javax.swing.JPanel gioithieu;
    private javax.swing.JLabel hinhanh;
    private javax.swing.JLabel hinhanh11;
    private javax.swing.JLabel hinhanh13;
    private javax.swing.JLabel hinhanh14;
    private javax.swing.JLabel hinhanh15;
    private javax.swing.JLabel hinhanh16;
    private javax.swing.JLabel hinhanh17;
    private javax.swing.JLabel hinhanh3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel khanhhang;
    private javax.swing.JPanel khuyenmai;
    private javax.swing.JLabel lbTime;
    private javax.swing.JPanel lichsu;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel nhanvien;
    private javax.swing.JPanel sanpham;
    private javax.swing.JLabel ten;
    private javax.swing.JLabel ten11;
    private javax.swing.JLabel ten13;
    private javax.swing.JLabel ten14;
    private javax.swing.JLabel ten15;
    private javax.swing.JLabel ten16;
    private javax.swing.JLabel ten17;
    private javax.swing.JLabel ten3;
    // End of variables declaration//GEN-END:variables
}

