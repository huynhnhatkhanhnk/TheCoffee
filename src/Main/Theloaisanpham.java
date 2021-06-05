/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Helper.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manh
 */
public class Theloaisanpham extends javax.swing.JFrame {

    DefaultTableModel tblModelLoaiSP;
    DefaultComboBoxModel cbModelLoaiSP;
    Connection conLoaiSP, conSP;
    ResultSet rsSP, rsLoaiSP, rsIDPr, rsTen, rsTK, rsAdd1;
    DBHelper db = new DBHelper();
    PreparedStatement psSP, psLoaiSP, psSP1, psTen, psTK, psAdd1, psAdd2;
    Vector rowSP, rowLoaiSP, vecTK, vecTen, rowTen;

    ;
    /**
     * Creates new form TheLoaiDichvu
     */
      int a;
    int b;

    public Theloaisanpham() {
        initComponents();
        conLoaiSP = db.getCon();
        tblModelLoaiSP = new DefaultTableModel();
        tblModelLoaiSP.addColumn("ID");
        tblModelLoaiSP.addColumn("Loại");
        tblModelLoaiSP.addColumn("Kích cỡ");
        tblLoaiSP.setModel(tblModelLoaiSP);
        loadDataLoaiSP();
    }

    private void loadDataLoaiSP() {
        try {
            conLoaiSP = db.getCon();
            String sql = "select * from ProductType";
            psLoaiSP = conLoaiSP.prepareStatement(sql);
            rsLoaiSP = psLoaiSP.executeQuery();
            while (rsLoaiSP.next()) {
                rowLoaiSP = new Vector();
                rowLoaiSP.add(rsLoaiSP.getString(1));
                rowLoaiSP.add(rsLoaiSP.getString(2));
                rowLoaiSP.add(rsLoaiSP.getString(3));
                tblModelLoaiSP.addRow(rowLoaiSP);
            }
            tblLoaiSP.setModel(tblModelLoaiSP);
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        thoat = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ten = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNameType = new javax.swing.JTextField();
        txtIDProductType = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLoaiSP = new javax.swing.JTable();
        btnThemLoaiSP = new javax.swing.JPanel();
        btnAddLoaiSP = new javax.swing.JLabel();
        btnXoaLoaiSP = new javax.swing.JPanel();
        tbnXoaLoaiSP = new javax.swing.JLabel();
        btnSuaLoaiSP = new javax.swing.JPanel();
        UpdateLoaiSP = new javax.swing.JLabel();
        btnResetLoaiSP = new javax.swing.JPanel();
        ResetForm = new javax.swing.JLabel();
        cbSizeType = new javax.swing.JComboBox<>();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Họ và Tên", "Ngày Sinh", "Giới tính", "Tài Khoản", "Mật Khẩu", "Số Điện Thoại", "Email", "Đia Chỉ"
            }
        ));
        jTable2.setFocusable(false);
        jTable2.setGridColor(new java.awt.Color(0, 0, 0));
        jTable2.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable2.setRowHeight(25);
        jTable2.setShowVerticalLines(false);
        jScrollPane2.setViewportView(jTable2);

        jToolBar1.setRollover(true);

        jTextField1.setText("jTextField1");

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

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("-");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        thoat.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        thoat.setForeground(new java.awt.Color(255, 255, 255));
        thoat.setText("x");
        thoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thoatMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Thể Loại Sản Phẩm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(31, 31, 31)
                .addComponent(thoat)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 50));

        ten.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setBackground(new java.awt.Color(153, 153, 153));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Kích cỡ                                        :");

        jLabel7.setBackground(new java.awt.Color(153, 153, 153));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Tên Loại Sản Phẩm                     :");

        jLabel8.setBackground(new java.awt.Color(153, 153, 153));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("Mã Loại  Sản Phẩm                     :");

        txtNameType.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtNameType.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNameType.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtNameType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameTypeActionPerformed(evt);
            }
        });
        txtNameType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameTypeKeyPressed(evt);
            }
        });

        txtIDProductType.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txtIDProductType.setCaretColor(new java.awt.Color(255, 255, 255));
        txtIDProductType.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtIDProductType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDProductTypeActionPerformed(evt);
            }
        });
        txtIDProductType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIDProductTypeKeyPressed(evt);
            }
        });

        tblLoaiSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblLoaiSP.setFocusable(false);
        tblLoaiSP.setGridColor(new java.awt.Color(0, 0, 0));
        tblLoaiSP.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblLoaiSP.setRowHeight(25);
        tblLoaiSP.setShowVerticalLines(false);
        tblLoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiSPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblLoaiSP);

        btnThemLoaiSP.setBackground(new java.awt.Color(0, 0, 0));

        btnAddLoaiSP.setBackground(new java.awt.Color(255, 255, 255));
        btnAddLoaiSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAddLoaiSP.setForeground(new java.awt.Color(255, 255, 255));
        btnAddLoaiSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddLoaiSP.setText("Thêm");
        btnAddLoaiSP.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                btnAddLoaiSPAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        btnAddLoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddLoaiSPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnThemLoaiSPLayout = new javax.swing.GroupLayout(btnThemLoaiSP);
        btnThemLoaiSP.setLayout(btnThemLoaiSPLayout);
        btnThemLoaiSPLayout.setHorizontalGroup(
            btnThemLoaiSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAddLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        btnThemLoaiSPLayout.setVerticalGroup(
            btnThemLoaiSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAddLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        btnXoaLoaiSP.setBackground(new java.awt.Color(0, 0, 0));
        btnXoaLoaiSP.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                btnXoaLoaiSPAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        tbnXoaLoaiSP.setBackground(new java.awt.Color(255, 255, 255));
        tbnXoaLoaiSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbnXoaLoaiSP.setForeground(new java.awt.Color(255, 255, 255));
        tbnXoaLoaiSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tbnXoaLoaiSP.setText("Xóa");
        tbnXoaLoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnXoaLoaiSPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnXoaLoaiSPLayout = new javax.swing.GroupLayout(btnXoaLoaiSP);
        btnXoaLoaiSP.setLayout(btnXoaLoaiSPLayout);
        btnXoaLoaiSPLayout.setHorizontalGroup(
            btnXoaLoaiSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbnXoaLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        btnXoaLoaiSPLayout.setVerticalGroup(
            btnXoaLoaiSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbnXoaLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        btnSuaLoaiSP.setBackground(new java.awt.Color(0, 0, 0));

        UpdateLoaiSP.setBackground(new java.awt.Color(255, 255, 255));
        UpdateLoaiSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpdateLoaiSP.setForeground(new java.awt.Color(255, 255, 255));
        UpdateLoaiSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        UpdateLoaiSP.setText("Sửa");
        UpdateLoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateLoaiSPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnSuaLoaiSPLayout = new javax.swing.GroupLayout(btnSuaLoaiSP);
        btnSuaLoaiSP.setLayout(btnSuaLoaiSPLayout);
        btnSuaLoaiSPLayout.setHorizontalGroup(
            btnSuaLoaiSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(UpdateLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        btnSuaLoaiSPLayout.setVerticalGroup(
            btnSuaLoaiSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(UpdateLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        btnResetLoaiSP.setBackground(new java.awt.Color(0, 0, 0));
        btnResetLoaiSP.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                btnResetLoaiSPAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        ResetForm.setBackground(new java.awt.Color(255, 255, 255));
        ResetForm.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ResetForm.setForeground(new java.awt.Color(255, 255, 255));
        ResetForm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ResetForm.setText("Làm mới");
        ResetForm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResetFormMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnResetLoaiSPLayout = new javax.swing.GroupLayout(btnResetLoaiSP);
        btnResetLoaiSP.setLayout(btnResetLoaiSPLayout);
        btnResetLoaiSPLayout.setHorizontalGroup(
            btnResetLoaiSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ResetForm, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        btnResetLoaiSPLayout.setVerticalGroup(
            btnResetLoaiSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ResetForm, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        cbSizeType.setEditable(true);
        cbSizeType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhỏ", "Vừa", "Lớn" }));

        javax.swing.GroupLayout tenLayout = new javax.swing.GroupLayout(ten);
        ten.setLayout(tenLayout);
        tenLayout.setHorizontalGroup(
            tenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tenLayout.createSequentialGroup()
                .addGroup(tenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tenLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(tenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIDProductType, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                            .addComponent(txtNameType)))
                    .addGroup(tenLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(tenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tenLayout.createSequentialGroup()
                                .addGroup(tenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tenLayout.createSequentialGroup()
                                        .addComponent(btnThemLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(68, 68, 68)
                                        .addComponent(btnXoaLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tenLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(tenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cbSizeType, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnSuaLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(54, 54, 54)))
                                .addComponent(btnResetLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))))
                .addGap(52, 52, 52))
        );
        tenLayout.setVerticalGroup(
            tenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tenLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(tenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSizeType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(tenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        jPanel1.add(ten, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 530, 410));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void thoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thoatMouseClicked

        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Bạn có muốn thoát?",
                "FrameToClose",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            setVisible(false);
            dispose();
        } else {
            setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_thoatMouseClicked

    private void txtNameTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameTypeActionPerformed

    private void txtNameTypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameTypeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameTypeKeyPressed

    private void txtIDProductTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDProductTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDProductTypeActionPerformed

    private void txtIDProductTypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDProductTypeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDProductTypeKeyPressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - a, y - b);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        a = evt.getX();
        b = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void btnAddLoaiSPAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_btnAddLoaiSPAncestorAdded

    }//GEN-LAST:event_btnAddLoaiSPAncestorAdded

    private void btnResetLoaiSPAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_btnResetLoaiSPAncestorAdded
        // TODO add your handling code here:
        txtIDProductType.setText("");
        txtNameType.setText("");
        cbSizeType.setSelectedIndex(0);//re set combobox
        txtIDProductType.setEnabled(true);
        btnXoaLoaiSP.setEnabled(false);
        btnSuaLoaiSP.setEnabled(false);
        btnThemLoaiSP.setEnabled(true);

    }//GEN-LAST:event_btnResetLoaiSPAncestorAdded

    private void btnXoaLoaiSPAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_btnXoaLoaiSPAncestorAdded
        // TODO add your handling code here:
        //   JOptionPane.showMessageDialog(this, "haha");
    }//GEN-LAST:event_btnXoaLoaiSPAncestorAdded

    private void tbnXoaLoaiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnXoaLoaiSPMouseClicked
        // TODO add your handling code here:
        String xoa = "select * from Product where IDType=?"; // Tìm kiếm sản phẩm thEO id loại sản phẩm

        try {
            psLoaiSP = conLoaiSP.prepareStatement(xoa);
            psLoaiSP.setString(1, txtIDProductType.getText());
            rsLoaiSP = psLoaiSP.executeQuery();
            if (!rsLoaiSP.next()) { // nếu mà khác
                int click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa loại sản phẩm không?");
                if (click == 0) {
                    try {
                        String sql = "Delete ProductType where IDType=?";
                        psLoaiSP = conLoaiSP.prepareStatement(sql);
                        psLoaiSP.setString(1, txtIDProductType.getText());
                        psLoaiSP.executeUpdate();
                        tblModelLoaiSP.getDataVector().removeAllElements();
                        loadDataLoaiSP();
                        restTable();
                        JOptionPane.showMessageDialog(null, "Xóa loại sản phẩm thành công");
                    } catch (Exception e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa loại sản phẩm không thành công");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sản phẩm còn tồn tại, không thể xóa");
            }
        } catch (SQLException ex) {
        }
        tblModelLoaiSP.getDataVector().removeAllElements();
        loadDataLoaiSP();

    }//GEN-LAST:event_tbnXoaLoaiSPMouseClicked
    private void ValidateADD() {
        int line = tblModelLoaiSP.getRowCount();// lấy vị trí hàng trùng nhau
        while (true) {
            for (int i = 0; i < line; i++) {
                if (txtIDProductType.getText().trim().equals(tblModelLoaiSP.getValueAt(i, 0))) {
                    JOptionPane.showMessageDialog(this, "ID trùng");
                    txtIDProductType.grabFocus();
                    return;
                }
            }
            if (txtIDProductType.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "ID không được để trống");
                txtIDProductType.grabFocus();
                return;
            } else if (txtNameType.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Tên Sản phẩm không được bỏ trống!");
                txtNameType.grabFocus();
                return;
            } else {
                break;
            }
        }
    }
    private void btnAddLoaiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddLoaiSPMouseClicked
        // TODO add your handling code here:

        int click = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm loại sản phẩm không?");
        if (click == 0) {
            try {
                ValidateADD();

                String sql = "insert into ProductType values(?,?,?)";
                psLoaiSP = conLoaiSP.prepareStatement(sql);
                psLoaiSP.setString(1, txtIDProductType.getText());
                psLoaiSP.setString(2, txtNameType.getText());
                psLoaiSP.setString(3, (String) cbSizeType.getSelectedItem());// Lấy vị trí trên combox
                psLoaiSP.executeUpdate();
            //    tblModelLoaiSP.getDataVector().removeAllElements(); // 
                loadDataLoaiSP();
                //  btnResetActionPerformed(null);
                JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm thành công");
            } catch (Exception e) {
            }
        }

    }//GEN-LAST:event_btnAddLoaiSPMouseClicked

    private void tblLoaiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiSPMouseClicked
        // TODO add your handling code here:
        btnXoaLoaiSP.setEnabled(true);
        btnSuaLoaiSP.setEnabled(true);
        btnThemLoaiSP.setEnabled(false);
        int line1 = tblLoaiSP.getSelectedRow();
        String ID1 = (String) tblModelLoaiSP.getValueAt(line1, 0); // lấy vị trí của hàng
        String Name1 = (String) tblModelLoaiSP.getValueAt(line1, 1);
        String Size = (String) tblModelLoaiSP.getValueAt(line1, 2);
        //gan du lieu vao textfile
        txtIDProductType.setText(ID1); // sau đó đỗ lên hàng
        txtNameType.setText(Name1);
        cbSizeType.setSelectedItem(Size); // vị trí của combox
        txtIDProductType.setEnabled(false);
    }//GEN-LAST:event_tblLoaiSPMouseClicked

    private void ResetFormMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetFormMouseClicked
        // TODO add your handling code here:
        txtIDProductType.setText("");
        txtNameType.setText("");
        cbSizeType.setSelectedIndex(0);//re set combobox
        txtIDProductType.setEnabled(true);
        btnXoaLoaiSP.setEnabled(false);
        btnSuaLoaiSP.setEnabled(false);
        btnThemLoaiSP.setEnabled(true);
    }//GEN-LAST:event_ResetFormMouseClicked

    private void UpdateLoaiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateLoaiSPMouseClicked
        // TODO add your handling code here:
        //  JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa loại sản phẩm không?");

        try {
            String sql = "update ProductType set TypeName=?, Size=? where IDType=?";
            psLoaiSP = conLoaiSP.prepareStatement(sql);
            psLoaiSP.setString(1, txtNameType.getText());
            psLoaiSP.setString(2, (String) cbSizeType.getSelectedItem()); // L
            psLoaiSP.setString(3, txtIDProductType.getText());
            psLoaiSP.executeUpdate();
            tblModelLoaiSP.getDataVector().removeAllElements();
            loadDataLoaiSP();
            //  btnResetActionPerformed(null);
            JOptionPane.showMessageDialog(null, "Sửa loại sản phẩm thành công");
            tblModelLoaiSP.getDataVector().removeAllElements();
            loadDataLoaiSP();
        } catch (Exception e) {
        }

    }//GEN-LAST:event_UpdateLoaiSPMouseClicked

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
            java.util.logging.Logger.getLogger(Theloaisanpham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Theloaisanpham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Theloaisanpham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Theloaisanpham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Theloaisanpham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ResetForm;
    private javax.swing.JLabel UpdateLoaiSP;
    private javax.swing.JLabel btnAddLoaiSP;
    private javax.swing.JPanel btnResetLoaiSP;
    private javax.swing.JPanel btnSuaLoaiSP;
    private javax.swing.JPanel btnThemLoaiSP;
    private javax.swing.JPanel btnXoaLoaiSP;
    private javax.swing.JComboBox<String> cbSizeType;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblLoaiSP;
    private javax.swing.JLabel tbnXoaLoaiSP;
    private javax.swing.JPanel ten;
    public javax.swing.JLabel thoat;
    public static javax.swing.JTextField txtIDProductType;
    public static javax.swing.JTextField txtNameType;
    // End of variables declaration//GEN-END:variables
  private void restTable() {
        txtIDProductType.setText("");
        txtNameType.setText("");
        cbSizeType.setSelectedIndex(0);
    }

}
