
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bill;

import Helper.DBHelper;
import Main.*;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manh
 */
public class Chitiethoadon extends javax.swing.JPanel {

    /**
     * Creates new form Khachhang
     */
    Vector vecKhongCTKM, vecKHV, vecCTKM, vecEmp;
    Connection con;
    ResultSet rsKhongCTKM, rsKHV, rsCTKM, rsEmp, rsInfoEmp, rs;
    PreparedStatement ps;
    DefaultTableModel tblModel = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; //To change body of generated methods, choose Tools | Templates.
        }

    };
    DBHelper db = new DBHelper();
    SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy"); //  định dạng này của việt nam
    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy"); // ngày + giờ VN
    NumberFormat formatter = new DecimalFormat("#,###"); // định dạng Dấu phẩy
    
    public Chitiethoadon() {
        initComponents();
        TableHistory();
        LoadEmpLoyee();
        ReloadTbl();
        LoadlbTotal();
        btnResetActionPerformed(null);

    }

    private void TableHistory() {
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
    }

    public void LoadEmpLoyee() {
        con = db.getCon();
        try {
            String url = "Select UsernameEmp from Employee"; // 
            ps = con.prepareStatement(url);
            rsEmp = ps.executeQuery();
            vecEmp = new Vector();
            while (rsEmp.next()) {
                vecEmp.add(rsEmp.getString("UsernameEmp"));// tìm kiếm hóa đơn theo Nhân viên
            }
            JTextField text = (JTextField) cbEmp.getEditor().getEditorComponent(); // để có thể viết lên đc trêm cbox
            text.setText(""); // để khoảng trắng trước khi load dữ  liệu lên cbox
            text.addKeyListener(new ComboListener(cbEmp, vecEmp)); // dể load tên nhân viên lên thanh textFIed
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
    }

    public void LoadlbTotal() { //   hàng tính tiền 
        int total = 0; // tiền tổng
        int line = tblHistory.getRowCount(); // đến số hàng
        for (int i = 0; i < line; i++) {
            String ThanhTien = (String) tblHistory.getValueAt(i, 10);  // lấy dữ liệu của hàng thứ 11 
            total += Integer.parseInt(ThanhTien.replaceAll(",", ""));//  công dồn tiền của hàng thứ 11 , ( Thanhtien .replaceAll có nghĩa là xóa dấu phẩy của thành tiền)
        }
        lbTotal.setText(formatter.format(total) + " VNĐ"); //  HIỂN THỊ giá tiền
    }

    public void ReloadTbl() {
        tblModel.getDataVector().removeAllElements(); // xóa tất cả dữ liệu của talble
        //select theo khách hàng VIP
        try {
            ps = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                    + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                    + "join Customer on OrderDetails.CusName=Customer.IDCus  "
                    + "where Orderdetails.CusName != 'Khách vãng lai'");
            // câu lên sql lấy dữ liệu của bản Order , OrderDetails, Product, Customer where Cusname khác khách vãi lai
            rsKHV = ps.executeQuery();
            while (rsKHV.next()) {
                vecKHV = new Vector();
                vecKHV.add(rsKHV.getString("IDOrder"));  // mã đơn hàng
                vecKHV.add(rsKHV.getString("IDProduct")); // mã sản phẩm
                vecKHV.add(rsKHV.getString("Quantity")); // số lượng ly
                vecKHV.add(formatter.format(rsKHV.getInt("Price"))); // định dạng lại tiên của đơn giá sản phẩm
                vecKHV.add(rsKHV.getString("NamePromo"));   // khuyến mãi
                vecKHV.add(rsKHV.getString("CusName"));
                vecKHV.add(rsKHV.getString("Discount"));
                vecKHV.add(rsKHV.getString("TimeOrder")); // thời gian 
                vecKHV.add(rsKHV.getString("DateOrder")); // ngày
                vecKHV.add(rsKHV.getString("UsernameEmp"));
                int quanKHV = rsKHV.getInt("Quantity"); // số lượng sản của khách hàng vip
                int priceKHV = rsKHV.getInt("Price"); //  tiền của sp    
                int discountKHV = rsKHV.getInt("Discount");  // giản giá bao nhiêu phần trăm
                int dismoneyKHV = (quanKHV * priceKHV * discountKHV) / 100;  // (số tiền đc giảm giá = SÔ lượng * TiềnSP * Số phần trĂM KHUYẾN MÃI) /100
                int totalKHV = (priceKHV * quanKHV) - dismoneyKHV;// TỒNG TIỀN = GIÁ SP * SỐ LƯỢNG - TIỀN GIẢM GIÁ
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
            // sql lấy thông tin của bản OrderDetails ,Product ,Promotions
            rsCTKM = ps.executeQuery(); ///
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtdate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        cbEmp = new javax.swing.JComboBox();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(850, 540));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(128, 128, 131));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 620));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CHI TIẾT HÓA ĐƠN");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 10, 880, 70));

        jLabel7.setBackground(new java.awt.Color(153, 153, 153));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Tài khoản nhân viên");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 150, 30));

        jLabel8.setBackground(new java.awt.Color(153, 153, 153));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("Ngày lập đơn hàng");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 140, 30));

        txtdate.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 190, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Tổng số tiền:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 90, 20));

        lbTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(255, 0, 0));
        lbTotal.setText("0 VNĐ");
        jPanel1.add(lbTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, -1, 20));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 840, 214));

        btnSearch.setBackground(new java.awt.Color(0, 0, 0));
        btnSearch.setText("Tìm kiếm");
        btnSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 90, 40));

        btnReset.setText("Làm mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel1.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 90, 40));

        btnPrint.setText("Lưu &  IN");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel1.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, 130, 40));

        cbEmp.setEditable(true);
        cbEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEmpActionPerformed(evt);
            }
        });
        jPanel1.add(cbEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 190, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

       SeachHoaDon();

    }//GEN-LAST:event_btnSearchActionPerformed
   
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        cbEmp.setSelectedIndex(-1); // trở về vị trí ban đầu
        ReloadTbl();
        LoadlbTotal();
        btnPrint.setEnabled(false);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed

        File file = new File("History.txt");
        file.delete();
        //Viết vào file txt
        try {
            Date now = new Date();
            Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("History.txt"), "UTF8"));
            b.write("Tân 3D COFFEE \r\n\r\n");
            b.write("Lê Quan Trung , Q.12, TPHCM\r\n");
            b.write("SĐT: 0968456212\r\n");
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
                String s6; // 
              
                if (!MKH.equals("Khách vãng lai")) { // so sánh của trên bảng so với nếu kvl thì sẽ 
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
        btnResetActionPerformed(evt);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void cbEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEmpActionPerformed
   private void SeachHoaDon(){
        String date = ft.format(txtdate.getDate()); //  lấy ngày hiện tại đổ lên table
        String name = (String) cbEmp.getSelectedItem();   // lấy vị trí 
        if (cbEmp.getSelectedIndex() == -1) { // nếu CbEmp ở  thì tên nhân viên chưa dc lấy
            JOptionPane.showMessageDialog(null, "Tài khoản nhân viên không được để trống.");
            btnPrint.setEnabled(false);
        } else {
            try {
                ps = con.prepareStatement("select * from OrderDetails  join [Order] on OrderDetails.IDOrder=[Order].IDOrder "
                        + "where UsernameEmp ='" + name + "' and DateOrder = '" + date + "'"); // tìm kiếm theo Tên nhân viên và thời gian
                rs = ps.executeQuery();
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Nhân viên " + name + " chưa bán được sản phẩm nào trong ngày " + date + ".");
                  //  btnResetActionPerformed(evt);
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
                        // chương trình khuyến mãi
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
                            int totalCTKM = (priceCTKM * quanCTKM) - dismoneyCTKM; // tiền của chương trình khuyến mai
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
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cbEmp;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tblHistory;
    private com.toedter.calendar.JDateChooser txtdate;
    // End of variables declaration//GEN-END:variables
}
