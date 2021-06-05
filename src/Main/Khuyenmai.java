
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Khuyenmai extends javax.swing.JPanel {

    Vector row;
    Connection con;
    ResultSet rs;
    PreparedStatement ps;
    DefaultTableModel tblModel;
    Helper.DBHelper db = new Helper.DBHelper();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");

    public Khuyenmai() {
        initComponents();
        con = db.getCon();
        tblModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModel.addColumn("Mã");
        tblModel.addColumn("Tên chương trình");
        tblModel.addColumn("Chiết khấu (%)");
        tblModel.addColumn("Ngày bắt đầu");
        tblModel.addColumn("Ngày kết thúc");
        tblModel.addColumn("Mô tả");
        tblPromo.setModel(tblModel);
        ReloadTbl();
//        btnResetActionPerformed(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnReset = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtDis = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        DateStart = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        DateEnd = new com.toedter.calendar.JDateChooser();
        lbID1 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        btnDel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPromo = new javax.swing.JTable();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(850, 540));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(128, 128, 131));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 620));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thông tin chương trình");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, -1));

        btnReset.setBackground(new java.awt.Color(0, 0, 0));
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnResetMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Làm mới");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout btnResetLayout = new javax.swing.GroupLayout(btnReset);
        btnReset.setLayout(btnResetLayout);
        btnResetLayout.setHorizontalGroup(
            btnResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnResetLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnResetLayout.setVerticalGroup(
            btnResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnResetLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 290, 80, 30));

        btnUpdate.setBackground(new java.awt.Color(0, 0, 0));
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUpdateMousePressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Cập nhật");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout btnUpdateLayout = new javax.swing.GroupLayout(btnUpdate);
        btnUpdate.setLayout(btnUpdateLayout);
        btnUpdateLayout.setHorizontalGroup(
            btnUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnUpdateLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnUpdateLayout.setVerticalGroup(
            btnUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnUpdateLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 80, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 102));
        jLabel6.setText("Chiếu khấu (%):");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setText("Tên chương trình (có thể ghi dấu):");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        txtName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtName.setDisabledTextColor(new java.awt.Color(0, 0, 204));
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 260, -1));

        txtDis.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtDis, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 140, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 102));
        jLabel5.setText("Ngày bắt đầu:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        DateStart.setDateFormatString("yyyy/MM/dd");
        jPanel1.add(DateStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 102));
        jLabel7.setText("Ngày kết thúc:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        DateEnd.setDateFormatString("yyyy/MM/dd");
        jPanel1.add(DateEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, -1, -1));

        lbID1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbID1.setForeground(new java.awt.Color(102, 0, 102));
        lbID1.setText("Mã:");
        jPanel1.add(lbID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        lbID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbID.setForeground(new java.awt.Color(255, 0, 0));
        lbID.setText("Tự động");
        jPanel1.add(lbID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 102));
        jLabel8.setText("Mô tả:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, -1, -1));

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtDescription.setRows(5);
        txtDescription.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(txtDescription);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 330, 160));

        btnDel.setBackground(new java.awt.Color(0, 0, 0));
        btnDel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDelMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Xóa ");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout btnDelLayout = new javax.swing.GroupLayout(btnDel);
        btnDel.setLayout(btnDelLayout);
        btnDelLayout.setHorizontalGroup(
            btnDelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnDelLayout.setVerticalGroup(
            btnDelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(btnDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 290, 80, -1));

        btnAdd.setBackground(new java.awt.Color(0, 0, 0));
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAddMousePressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Thêm");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnAddLayout = new javax.swing.GroupLayout(btnAdd);
        btnAdd.setLayout(btnAddLayout);
        btnAddLayout.setHorizontalGroup(
            btnAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAddLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnAddLayout.setVerticalGroup(
            btnAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAddLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, 80, -1));

        tblPromo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPromo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPromoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPromo);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 820, 170));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMousePressed
        // TODO add your handling code here:
        String name = txtName.getText().replaceAll("\\s+", " ");
        String descript = txtDescription.getText();
        String start = ft.format(DateStart.getDate());
        String end = ft.format(DateEnd.getDate());
        while (true) {
            if (name.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Tên chương trình không được bỏ trống.");
                txtName.grabFocus();
                return;
            } else if (name.length() > 50) {
                JOptionPane.showMessageDialog(null, "Độ dài tối đa của tên chương trình là 50 ký tự.");
                txtName.grabFocus();
                return;
            } else {
                break;
            }
        }
        while (true) {
            if (txtDis.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Chiết khấu không được bỏ trống.");
                txtDis.grabFocus();
                return;
            } else if (!txtDis.getText().trim().matches("[0-9]+") || Integer.parseInt(txtDis.getText().trim()) > 100) {
                JOptionPane.showMessageDialog(null, "Chiết khấu phải là số nguyên dương và <= 100.");
                txtDis.grabFocus();
                return;
            } else {
                break;
            }
        }
        while (true) {
            if (start.compareTo(end) >= 0) {
                JOptionPane.showMessageDialog(null, "Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc.");
                return;
            } else {
                break;
            }
        }
        while (true) {
            if (descript.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Mô tả chương trình không được bỏ trống.");
                txtDescription.grabFocus();
                return;
            } else {
                break;
            }
        }
        try {
            ps = con.prepareStatement("Insert into Promotions values(?,?,?,?,?)");
            ps.setString(1, name);
            ps.setInt(2, Integer.parseInt(txtDis.getText().trim()));
            ps.setString(3, start);
            ps.setString(4, end);
            ps.setString(5, txtDescription.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm chương trình khuyến mãi thành công.");
            ReloadTbl();
           // btnResetActionPerformed(evt);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
    }//GEN-LAST:event_btnAddMousePressed

    private void btnResetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnResetMousePressed

    private void tblPromoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPromoMouseClicked
        // TODO add your handling code here:
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(true);
        btnDel.setEnabled(true);
        txtName.setEnabled(false);
        lbID.setVisible(true);
        lbID1.setVisible(true);
        try {
            int line = tblPromo.getSelectedRow();
            Date start = ft.parse((String) tblPromo.getValueAt(line, 3));
            Date end = ft.parse((String) tblPromo.getValueAt(line, 4));
            txtName.setText((String) tblPromo.getValueAt(line, 1));
            txtDis.setText((String) tblPromo.getValueAt(line, 2));
            lbID.setText((String) tblPromo.getValueAt(line, 0));
            txtDescription.setText((String) tblPromo.getValueAt(line, 5));
            DateStart.setDate(start);
            DateEnd.setDate(end);
        } catch (ParseException ex) {
        }
    }//GEN-LAST:event_tblPromoMouseClicked

    private void btnUpdateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnUpdateMousePressed

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        // TODO add your handling code here:
        String descript = txtDescription.getText();
        String start = ft.format(DateStart.getDate());
        String end = ft.format(DateEnd.getDate());
        while (true) {
            if (txtDis.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Chiết khấu không được bỏ trống.");
                txtDis.grabFocus();
                return;
            } else if (!txtDis.getText().trim().matches("[0-9]+") || Integer.parseInt(txtDis.getText().trim()) > 100) {
                JOptionPane.showMessageDialog(null, "Chiết khấu phải là số nguyên và nhỏ hơn 100.");
                txtDis.grabFocus();
                return;
            } else {
                break;
            }
        }
        while (true) {
            if (start.compareTo(end) >= 0) {
                JOptionPane.showMessageDialog(null, "Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc.");
                return;
            } else {
                break;
            }
        }
        while (true) {
            if (descript.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Mô tả chương trình không được bỏ trống.");
                txtDescription.grabFocus();
                return;
            } else {
                break;
            }
        }
        try {
            ps = con.prepareStatement("Update Promotions set DiscountPromo=?, StartPromo=?, EndPromo=?, Description=? where IDPromo=?");
            ps.setString(1, txtDis.getText().trim());
            ps.setString(2, start);
            ps.setString(3, end);
            ps.setString(4, txtDescription.getText());
            ps.setString(5, lbID.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật chương trình thành công.");
            ReloadTbl();
//            btnResetActionPerformed(evt);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
    }//GEN-LAST:event_btnUpdateMouseClicked

    private void btnDelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelMousePressed

    private void btnDelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelMouseClicked
        // TODO add your handling code here:
        int click = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa chương trình này?");
        if (click == 0) {
            try {
                ps = con.prepareStatement("Delete from Promotions where IDPromo=?");
                ps.setString(1, lbID.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Xóa chương trình thành công.");
                ReloadTbl();
     //           btnResetActionPerformed(evt);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi 101: Không thể kết nối đến máy chủ");
            }
        }
    }//GEN-LAST:event_btnDelMouseClicked

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        // TODO add your handling code here:
        txtName.setText("");
        txtDis.setText("");
        txtDescription.setText("");
        btnDel.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnAdd.setEnabled(true);
        lbID.setVisible(false);
        lbID1.setVisible(false);
        txtName.setEnabled(true);
    }//GEN-LAST:event_btnResetMouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        String name = txtName.getText().replaceAll("\\s+", " ");
        String descript = txtDescription.getText();
        String start = ft.format(DateStart.getDate());
        String end = ft.format(DateEnd.getDate());
        while (true) {
            if (name.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Tên chương trình không được bỏ trống.");
                txtName.grabFocus();
                return;
            } else if (name.length() > 50) {
                JOptionPane.showMessageDialog(null, "Độ dài tối đa của tên chương trình là 50 ký tự.");
                txtName.grabFocus();
                return;
            } else {
                break;
            }
        }
        while (true) {
            if (txtDis.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Chiết khấu không được bỏ trống.");
                txtDis.grabFocus();
                return;
            } else if (!txtDis.getText().trim().matches("[0-9]+") || Integer.parseInt(txtDis.getText().trim()) > 100) {
                JOptionPane.showMessageDialog(null, "Chiết khấu phải là số nguyên dương và <= 100.");
                txtDis.grabFocus();
                return;
            } else {
                break;
            }
        }
        while (true) {
            if (start.compareTo(end) >= 0) {
                JOptionPane.showMessageDialog(null, "Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc.");
                return;
            } else {
                break;
            }
        }
        while (true) {
            if (descript.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Mô tả chương trình không được bỏ trống.");
                txtDescription.grabFocus();
                return;
            } else {
                break;
            }
        }
        try {
            ps = con.prepareStatement("Insert into Promotions values(?,?,?,?,?)");
            ps.setString(1, name);
            ps.setInt(2, Integer.parseInt(txtDis.getText().trim()));
            ps.setString(3, start);
            ps.setString(4, end);
            ps.setString(5, txtDescription.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm chương trình khuyến mãi thành công.");
            ReloadTbl();
           //ds btnResetActionPerformed(evt);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void ReloadTbl() {
        tblModel.getDataVector().removeAllElements();
        try {
            ps = con.prepareStatement("select * from Promotions");
            rs = ps.executeQuery();
            while (rs.next()) {
                row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
                tblModel.addRow(row);
            }
            tblPromo.setModel(tblModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateEnd;
    private com.toedter.calendar.JDateChooser DateStart;
    private javax.swing.JPanel btnAdd;
    private javax.swing.JPanel btnDel;
    private javax.swing.JPanel btnReset;
    private javax.swing.JPanel btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbID1;
    private javax.swing.JTable tblPromo;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtDis;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables


}
