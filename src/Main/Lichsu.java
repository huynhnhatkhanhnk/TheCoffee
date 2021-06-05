
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Bill.ComboListener;
import Helper.DBHelper;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.RowSorterEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;






/**
 *
 * @author Manh
 */
public class Lichsu extends javax.swing.JPanel {
 
    /**
     * Creates new form Khachhang
     * 
     */
     Vector vecKhongCTKM, vecKHV, vecCTKM, vecEmp;
    Connection con;
    ResultSet rsKhongCTKM, rsKHV, rsCTKM, rsEmp, rsInfoEmp, rs;
    PreparedStatement ps;
    DefaultTableModel tblModel = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) { // chỉnh sửa hàng cột
            return false; //To change body of generated methods, choose Tools | Templates.
        }

    };
      DBHelper db = new DBHelper();
    SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("#,###");
    
    public Lichsu() {
        initComponents();
        con = db.getCon();
        tblModel.addColumn("Mã đơn hàng");
        tblModel.addColumn("Mã sản phẩm");
        tblModel.addColumn("Số lượng (Ly)");
        tblModel.addColumn("Đơn giá (VNĐ)");
        tblModel.addColumn("Tên CTKM");
        tblModel.addColumn("Mã khách hàng");
        tblModel.addColumn("Chiết khấu (%)");
        tblModel.addColumn("Thời gian");
        tblModel.addColumn("Ngày");
        tblModel.addColumn("TK nhân viên");
        tblModel.addColumn("Thành tiền (VNĐ)");
        tblHistory.setModel(tblModel);
        try {
            String url = "Select UsernameEmp from Employee";
            ps = con.prepareStatement(url);
            rsEmp = ps.executeQuery();
            vecEmp = new Vector();
            while (rsEmp.next()) {
                vecEmp.add(rsEmp.getString("UsernameEmp"));
            }
            JTextField text = (JTextField) cbEmp.getEditor().getEditorComponent();
            text.setText("");
            text.addKeyListener(new ComboListener(cbEmp, vecEmp));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        ReloadTbl();
        LoadlbTotal();
     //   btnResetActionPerformed(null);
    

    }
    public void LoadlbTotal() {
        int total = 0;
        int line = tblHistory.getRowCount();
        for (int i = 0; i < line; i++) {
            String ThanhTien = (String) tblHistory.getValueAt(i, 10);
          total += Integer.parseInt(ThanhTien.replaceAll(",", ""));
        }
        lbTotal.setText(formatter.format(total) + " VNĐ");
    }

    public void ReloadTbl() {
        tblModel.getDataVector().removeAllElements();
        //select theo khách hàng VIP
        try {
            ps = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                    + "join Customer on OrderDetails.CusName=Customer.IDCus  "
                    + "where Orderdetails.CusName != 'Khách vãng lai'");
            rsKHV = ps.executeQuery();
            while (rsKHV.next()) {
                vecKHV = new Vector();
                vecKHV.add(rsKHV.getString("IDOrder"));
                vecKHV.add(rsKHV.getString("IDProduct"));
                vecKHV.add(rsKHV.getString("Quantity"));
                vecKHV.add(formatter.format(rsKHV.getInt("Price")));
                vecKHV.add(rsKHV.getString("NamePromo"));
                vecKHV.add(rsKHV.getString("CusName"));
                vecKHV.add(rsKHV.getString("Discount"));
                vecKHV.add(rsKHV.getString("TimeOrder"));
                vecKHV.add(rsKHV.getString("DateOrder"));
                vecKHV.add(rsKHV.getString("UsernameEmp"));
                int quanKHV = rsKHV.getInt("Quantity");
                int priceKHV = rsKHV.getInt("Price");
                int discountKHV = rsKHV.getInt("Discount");
                int dismoneyKHV = (quanKHV * priceKHV * discountKHV) / 100;
                int totalKHV = (priceKHV * quanKHV) - dismoneyKHV;
                vecKHV.add(formatter.format(totalKHV));
                tblModel.addRow(vecKHV);
            }
            tblHistory.setModel(tblModel);
        } catch (Exception e) {
        }
        //select theo CTKM
        try {
            ps = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                    + "join Promotions on OrderDetails.NamePromo=Promotions.NamePromo");
            rsCTKM = ps.executeQuery();
            while (rsCTKM.next()) {
                vecCTKM = new Vector();
                vecCTKM.add(rsCTKM.getString("IDOrder"));
                vecCTKM.add(rsCTKM.getString("IDProduct"));
                vecCTKM.add(rsCTKM.getString("Quantity"));
                vecCTKM.add(formatter.format(rsCTKM.getInt("Price")));
                vecCTKM.add(rsCTKM.getString("NamePromo"));
                vecCTKM.add(rsCTKM.getString("CusName"));
                vecCTKM.add(rsCTKM.getString("DiscountPromo"));
                vecCTKM.add(rsCTKM.getString("TimeOrder"));
                vecCTKM.add(rsCTKM.getString("DateOrder"));
                vecCTKM.add(rsCTKM.getString("UsernameEmp"));
                int quanCTKM = rsCTKM.getInt("Quantity");
                int priceCTKM = rsCTKM.getInt("Price");
                int discountCTKM = rsCTKM.getInt("DiscountPromo");
                int dismoneyCTKM = (quanCTKM * priceCTKM * discountCTKM) / 100;
                int totalCTKM = (priceCTKM * quanCTKM) - dismoneyCTKM;
                vecCTKM.add(formatter.format(totalCTKM));
                tblModel.addRow(vecCTKM);
            }
            tblHistory.setModel(tblModel);
        } catch (SQLException ex) {
        }
        //select theo không áp dụng CTKM
        try {
            ps = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                    + "where NamePromo='Không có'");
            rsKhongCTKM = ps.executeQuery();
            while (rsKhongCTKM.next()) {
                vecKhongCTKM = new Vector();
                vecKhongCTKM.add(rsKhongCTKM.getString("IDOrder"));
                vecKhongCTKM.add(rsKhongCTKM.getString("IDProduct"));
                vecKhongCTKM.add(rsKhongCTKM.getString("Quantity"));
                vecKhongCTKM.add(formatter.format(rsKhongCTKM.getInt("Price")));
                vecKhongCTKM.add(rsKhongCTKM.getString("NamePromo"));
                vecKhongCTKM.add(rsKhongCTKM.getString("CusName"));
                vecKhongCTKM.add("0");
                vecKhongCTKM.add(rsKhongCTKM.getString("TimeOrder"));
                vecKhongCTKM.add(rsKhongCTKM.getString("DateOrder"));
                vecKhongCTKM.add(rsKhongCTKM.getString("UsernameEmp"));
                int quanKhongCTKM = rsKhongCTKM.getInt("Quantity");
                int priceKhongCTKM = rsKhongCTKM.getInt("Price");
                int totalKhongCTKM = priceKhongCTKM * quanKhongCTKM;
                vecKhongCTKM.add(formatter.format(totalKhongCTKM));
                tblModel.addRow(vecKhongCTKM);
            }
            tblHistory.setModel(tblModel);
        } catch (SQLException ex) {
        }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lammoi = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        xoa = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();
        cbEmp = new javax.swing.JComboBox();
        txtdate = new com.toedter.calendar.JDateChooser();
        lbTotal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(850, 540));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(128, 128, 131));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 620));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Lịch sử bán hàng");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 600, 40));

        jLabel5.setBackground(new java.awt.Color(153, 153, 153));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Ngày lập đơn hàng :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, 40));

        jLabel6.setBackground(new java.awt.Color(153, 153, 153));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Tài khoản nhân viên :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 170, 40));

        lammoi.setBackground(new java.awt.Color(0, 0, 0));
        lammoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lammoiMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Tìm kiếm");

        javax.swing.GroupLayout lammoiLayout = new javax.swing.GroupLayout(lammoi);
        lammoi.setLayout(lammoiLayout);
        lammoiLayout.setHorizontalGroup(
            lammoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );
        lammoiLayout.setVerticalGroup(
            lammoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(lammoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 80, 30));

        xoa.setBackground(new java.awt.Color(0, 0, 0));
        xoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xoaMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Làm Mới");

        javax.swing.GroupLayout xoaLayout = new javax.swing.GroupLayout(xoa);
        xoa.setLayout(xoaLayout);
        xoaLayout.setHorizontalGroup(
            xoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );
        xoaLayout.setVerticalGroup(
            xoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(xoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 80, 30));

        tblHistory.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblHistory);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 770, 280));

        cbEmp.setEditable(true);
        jPanel1.add(cbEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, 20));

        txtdate.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, -1, -1));

        lbTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(255, 0, 0));
        lbTotal.setText("0 VNĐ");
        jPanel1.add(lbTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Tổng số tiền:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, -1, -1));

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Print.png"))); // NOI18N
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel1.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 90, 60));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/excel.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, -1, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lammoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lammoiMouseClicked
        // TODO add your handling code here:
          String date = ft.format(txtdate.getDate());
        String name = (String) cbEmp.getSelectedItem();
        if (cbEmp.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Tài khoản nhân viên không được để trống.");
          //  btnPrint.setEnabled(false);
        } else {
            try {
                ps = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                        + "where UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
                rs = ps.executeQuery();
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Nhân viên " + name + " chưa bán được sản phẩm nào trong ngày " + date + ".");
                   // btnResetActionPerformed(evt);
                } else {
                    btnPrint.setEnabled(true);
                    tblModel.getDataVector().removeAllElements();
                    //select theo khách hàng VIP
                    try {
                        ps = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                                + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                                + "join Customer on OrderDetails.CusName=Customer.IDCus  "
                                + "where Orderdetails.CusName != 'Khách vãng lai' and UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
                        rsKHV = ps.executeQuery();
                        while (rsKHV.next()) {
                            vecKHV = new Vector();
                            vecKHV.add(rsKHV.getString("IDOrder"));
                            vecKHV.add(rsKHV.getString("IDProduct"));
                            vecKHV.add(rsKHV.getString("Quantity"));
                            vecKHV.add(formatter.format(rsKHV.getInt("Price")));
                            vecKHV.add(rsKHV.getString("NamePromo"));
                            vecKHV.add(rsKHV.getString("CusName"));
                            vecKHV.add(rsKHV.getString("Discount"));
                            vecKHV.add(rsKHV.getString("TimeOrder"));
                            vecKHV.add(rsKHV.getString("DateOrder"));
                            vecKHV.add(rsKHV.getString("UsernameEmp"));
                            int quanKHV = rsKHV.getInt("Quantity");
                            int priceKHV = rsKHV.getInt("Price");
                            int discountKHV = rsKHV.getInt("Discount");
                            int dismoneyKHV = (quanKHV * priceKHV * discountKHV) / 100;
                            int totalKHV = (priceKHV * quanKHV) - dismoneyKHV;
                            vecKHV.add(formatter.format(totalKHV));
                            tblModel.addRow(vecKHV);
                        }
                        tblHistory.setModel(tblModel);
                    } catch (SQLException e) {
                    }
                    //select theo CTKM
                    try {
                        ps = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                                + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                                + "join Promotions on OrderDetails.NamePromo=Promotions.NamePromo "
                                + "where UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
                        rsCTKM = ps.executeQuery();
                        while (rsCTKM.next()) {
                            vecCTKM = new Vector();
                            vecCTKM.add(rsCTKM.getString("IDOrder"));
                            vecCTKM.add(rsCTKM.getString("IDProduct"));
                            vecCTKM.add(rsCTKM.getString("Quantity"));
                            vecCTKM.add(formatter.format(rsCTKM.getInt("Price")));
                            vecCTKM.add(rsCTKM.getString("NamePromo"));
                            vecCTKM.add(rsCTKM.getString("CusName"));
                            vecCTKM.add(rsCTKM.getString("DiscountPromo"));
                            vecCTKM.add(rsCTKM.getString("TimeOrder"));
                            vecCTKM.add(rsCTKM.getString("DateOrder"));
                            vecCTKM.add(rsCTKM.getString("UsernameEmp"));
                            int quanCTKM = rsCTKM.getInt("Quantity");
                            int priceCTKM = rsCTKM.getInt("Price");
                            int discountCTKM = rsCTKM.getInt("DiscountPromo");
                            int dismoneyCTKM = (quanCTKM * priceCTKM * discountCTKM) / 100;
                            int totalCTKM = (priceCTKM * quanCTKM) - dismoneyCTKM;
                            vecCTKM.add(formatter.format(totalCTKM));
                            tblModel.addRow(vecCTKM);
                        }
                        tblHistory.setModel(tblModel);
                    } catch (SQLException ex) {
                    }
                    //select theo không áp dụng CTKM
                    try {
                        ps = con.prepareStatement("select * from OrderDetails join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                                + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                                + "where NamePromo='Không có' and UsernameEmp ='" + name + "' and DateOrder = '" + date + "'");
                        rsKhongCTKM = ps.executeQuery();
                        while (rsKhongCTKM.next()) {
                            vecKhongCTKM = new Vector();
                            vecKhongCTKM.add(rsKhongCTKM.getString("IDOrder"));
                            vecKhongCTKM.add(rsKhongCTKM.getString("IDProduct"));
                            vecKhongCTKM.add(rsKhongCTKM.getString("Quantity"));
                            vecKhongCTKM.add(formatter.format(rsKhongCTKM.getInt("Price")));
                            vecKhongCTKM.add(rsKhongCTKM.getString("NamePromo"));
                            vecKhongCTKM.add(rsKhongCTKM.getString("CusName"));
                            vecKhongCTKM.add("0");
                            vecKhongCTKM.add(rsKhongCTKM.getString("TimeOrder"));
                            vecKhongCTKM.add(rsKhongCTKM.getString("DateOrder"));
                            vecKhongCTKM.add(rsKhongCTKM.getString("UsernameEmp"));
                            int quanKhongCTKM = rsKhongCTKM.getInt("Quantity");
                            int priceKhongCTKM = rsKhongCTKM.getInt("Price");
                            int totalKhongCTKM = priceKhongCTKM * quanKhongCTKM;
                            vecKhongCTKM.add(formatter.format(totalKhongCTKM));
                            tblModel.addRow(vecKhongCTKM);
                        }
                        tblHistory.setModel(tblModel);
                    } catch (SQLException ex) {
                    }
                }
            } catch (SQLException ex) {
            }
        }
        LoadlbTotal();
    }//GEN-LAST:event_lammoiMouseClicked

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        //xóa file txt
        File file = new File("History.txt");
        file.delete();
        //Viết vào file txt
        try {
            Date now = new Date();
            Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("History.txt"), "UTF8"));
            b.write("THE COFFEE 3D\r\n\r\n");
            b.write("Địa chỉ: Công viên phần mềm Quang Trung, Q.12, TPHCM\r\n");
            b.write("SĐT: 0931068294\r\n");
            b.write("Thời gian: " + time.format(now) + "\r\n\r\n");
            b.write("\t\t\t\t\tBẢNG THỐNG KÊ BÁN HÀNG NGÀY " + ft.format(txtdate.getDate()) + "\r\n");
            b.write("Tài khoản: " + cbEmp.getSelectedItem() + "\r\n");
            try {
                ps = con.prepareStatement("Select * from Employee where UsernameEmp=?");
                ps.setString(1, (String) cbEmp.getSelectedItem());
                rsInfoEmp = ps.executeQuery();
                if (rsInfoEmp.next()) {
                    b.write("Tên nhân viên: " + rsInfoEmp.getString("NameEmp") + "\r\n\r\n");
                }
            } catch (SQLException ex) {
            }
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");
            b.write("Mã ĐH\tMã SP\tSố lượng (ly)\tĐơn giá (VNĐ)\tTên CTKM\tMã khách hàng\tChiết khấu (%)\tThời gian\tThành tiền (VNĐ)\r\n");
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");

            int line = tblHistory.getRowCount();
            for (int i = 0; i < line; i++) {
                String s1 = (String) tblHistory.getValueAt(i, 0);
                String s2 = (String) tblHistory.getValueAt(i, 1);
                String s3 = (String) tblHistory.getValueAt(i, 2);
                String s4 = (String) tblHistory.getValueAt(i, 3);
                String s5 = (String) tblHistory.getValueAt(i, 4);
                String MKH = (String) tblHistory.getValueAt(i, 5);
                String s6;
                if (!MKH.equals("Khách vãng lai")) {
                    s6 = (String) tblHistory.getValueAt(i, 5) + "\t";
                } else {
                    s6 = (String) tblHistory.getValueAt(i, 5);
                }
                String s7 = (String) tblHistory.getValueAt(i, 6);
                String s8 = (String) tblHistory.getValueAt(i, 7);
                String s11 = (String) tblHistory.getValueAt(i, 10);
                b.write(s1 + "\t" + s2 + "\t" + s3 + "\t\t" + s4 + "\t\t" + s5 + "\t" + s6 + "\t" + s7 + "\t\t" + s8 + "\t" + s11 + "\r\n");
            }
            b.write("--------------------------------------------------------------------------------------------------------------------------------\r\n");
            b.write("Tổng tiền: " + lbTotal.getText());
            b.close();
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        //Mở file txt
        Runtime run = Runtime.getRuntime();
        try {
            run.exec("notepad History.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
      //  btnResetActionPerformed(evt);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void xoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xoaMouseClicked
        // TODO add your handling code here:
          cbEmp.setSelectedIndex(-1);
        ReloadTbl();
        LoadlbTotal();
        btnPrint.setEnabled(false);
    }//GEN-LAST:event_xoaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         TableModel model = tblHistory.getModel();
        JFileChooser excelExportChooser = new JFileChooser();
        excelExportChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        excelExportChooser.setDialogTitle("Save Excel File");
        //filter the file
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "*.xls", "xls", "xlsx", "xlsn");
        excelExportChooser.addChoosableFileFilter(filter);
        excelExportChooser.setFileFilter(filter);
        int excelchooser = excelExportChooser.showSaveDialog(null);

        if (excelchooser == JFileChooser.APPROVE_OPTION) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("SalesReturnsDetails");

            Row row;
            Cell cell;

            try {
                // write the column headers
                row = sheet.createRow(0);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    cell = row.createCell(j);
                    cell.setCellValue(model.getColumnName(j));
                }

                for (int i = 0; i < model.getRowCount(); i++) {

                    row = sheet.createRow(i + 1);

                    for (int j = 0; j < model.getColumnCount(); j++) {
                        cell = row.createCell(j);
                        cell.setCellValue(model.getValueAt(i, j).toString());

                    }

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            FileOutputStream excelFIS;

            try {
                excelFIS = new FileOutputStream(excelExportChooser.getSelectedFile() + ".xlsx");
                workbook.write(excelFIS);

                workbook.close();
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(NewWorker.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
               // Logger.getLogger(NewWorker.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JComboBox cbEmp;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel lammoi;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tblHistory;
    private com.toedter.calendar.JDateChooser txtdate;
    private javax.swing.JPanel xoa;
    // End of variables declaration//GEN-END:variables
}
