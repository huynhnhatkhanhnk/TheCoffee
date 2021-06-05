/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bill;

import Helper.DBHelper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tung
 */
public class Quanlybanhang extends javax.swing.JPanel{

    /**
     * Creates new form BillForm
     */
    ResultSet rs, rsEmp, rsIDOrder;
    Vector vec, rowHis;
    PreparedStatement ps;
    DefaultComboBoxModel cbModel;
    DefaultTableModel tblModel, tblModelHis;
    DBHelper db = new DBHelper();
    Connection con = db.getCon();
    SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    SimpleDateFormat ftnow = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat ftNgay = new SimpleDateFormat("dd/MM/yyyy");
    NumberFormat formatter = new DecimalFormat("#,###");

    public Quanlybanhang(String EmpName) {
       
        initComponents();
        clock();
        MahoadonLoad();
        txtEmpName.setText(EmpName);
        tblModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tblModel.addColumn("Mã");
        tblModel.addColumn("Tên sản phẩm");
        tblModel.addColumn("Nhóm");
        tblModel.addColumn("Kích thước");
        tblModel.addColumn("Đơn giá (VNĐ)");
        tblModel.addColumn("Số lượng (ly)");
        tblModel.addColumn("Thành tiền (VNĐ)");
        tblBill.setModel(tblModel);
        try {
            String url = "Select DISTINCT ProductName from Product Join ProductType on Product.IDType=ProductType.IDType";
            ps = con.prepareStatement(url);
            rs = ps.executeQuery();
            vec = new Vector();
            while (rs.next()) {
                vec.add(rs.getString("ProductName"));
            }
            JTextField text = (JTextField) cbProduct.getEditor().getEditorComponent();
            text.setText("");
            text.addKeyListener(new ComboListener(cbProduct, vec));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        ReloadCombobox();
    }

    private Quanlybanhang() {
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

    public void intomoney() {
        int price, totalprice = 0;
        int count = tblBill.getRowCount();
        for (int i = 0; i < count; i++) {
            price = Integer.parseInt((String) tblBill.getValueAt(i, 6));
            totalprice += price;
        }
        txtTotal.setText(formatter.format(totalprice)); //set giá trị cho txtOrder bằng totalprice
    }

    public void LoadTblFromDB() {
        try {
            String url = "Select * from Product Join ProductType on Product.IDType=ProductType.IDType where Product.ProductName=? and ProductType.Size=?";
            ps = con.prepareStatement(url);
            ps.setString(1, (String) cbProduct.getSelectedItem());
            ps.setString(2, (String) cbSize.getSelectedItem());
            rs = ps.executeQuery();
            int price, quantity, into;
            if (rs.next()) {
                vec = new Vector();
                price = Integer.parseInt(rs.getString("Price"));
                quantity = Integer.parseInt(spQuantity.getValue().toString());
                into = price * quantity;
                vec.add(rs.getString("IDProduct"));
                vec.add(rs.getString("ProductName"));
                vec.add(rs.getString("TypeName"));
                vec.add(rs.getString("Size"));
                vec.add(rs.getString("Price"));
                vec.add(spQuantity.getValue());
                vec.add(String.valueOf(into));
                tblModel.addRow(vec);
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi:: Không tìm thấy sản phẩm");
                cbProduct.grabFocus();
            }
            tblBill.setModel(tblModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
    }

    public void setText(boolean b) {
        txtGuest.setEnabled(b);
    }

    public void UpdatetxtDis1() {
        int Dis;
        NumberFormat formatter = new DecimalFormat("#,###");
        //tính Discount
        String Order = txtTotal.getText().replaceAll(",", "");
        Dis = (Integer.parseInt(txtDis1.getText()) * Integer.parseInt(Order)) / 100;
        txtDis2.setText(formatter.format(Dis));
        //tính total
        int total = Integer.parseInt(Order) - Dis;
        txtPay.setText(formatter.format(total));
    }

    public void ReloadCombobox() {
        cbCTKM.removeAllItems();
        cbCTKM.addItem("Không có");
        cbCTKM.addItem("Khách hàng VIP");
        try {
            Date now = new Date();
            ps = con.prepareStatement("select * from Promotions where StartPromo <= ? and EndPromo >= ?");
            ps.setString(1, ftnow.format(now));
            ps.setString(2, ftnow.format(now));
            rs = ps.executeQuery();
            while (rs.next()) {
                cbCTKM.addItem(rs.getString(2));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
    }

    public void PanelOnOff(boolean b) {
        lbNhap.setVisible(b);
        txtID.setVisible(b);
        lbIDError.setVisible(b);
        pnInformation.setVisible(b);
    }

    public void PressPrintandSave(String Name) {
        int line = tblBill.getRowCount();
        //Thêm từng value vào trong database
        try {
            ps = con.prepareStatement("Insert into [Order] values(convert(varchar(20),getdate(),103),convert(varchar(20),getdate(),108),?)");
            
            ps.setString(1, Name);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ 1");
        }
        MahoadonLoad();
       for (int i = 0; i < line; i++) {
            String IDProduct = (String) tblModel.getValueAt(i, 0);
            String quantity = String.valueOf(tblModel.getValueAt(i, 5));
            try {
                String in = "Insert into OrderDetails values(?,?,?,?,?)";
                ps = con.prepareStatement(in);
                ps.setString(1, txtIDBill.getText());
                ps.setString(2, IDProduct);
                if (cbCTKM.getSelectedItem().equals("Khách hàng VIP")) {
                    ps.setString(3, lbIDCus.getText());
                } else {
                    ps.setString(3, "Khách vãng lai");
                }
                ps.setInt(4, Integer.parseInt(quantity));
                ps.setString(5, (String) cbCTKM.getSelectedItem());
                ps.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi 101: Không thể kết nối đến máy chủ 2");
            }
        }
        //kiểm tra số tiền ngày hôm nay và set lại giá trị
        String pay = txtPay.getText().replaceAll(",", "");
        try {
            ps = con.prepareStatement("SELECT * from Revenue where Date=convert(date,getdate(),103)");
            rs = ps.executeQuery();
            if (rs.next()) {
                int money1 = Integer.parseInt(rs.getString("Money"));
                int money2 = money1 + Integer.parseInt(pay);
                ps = con.prepareStatement("update Revenue set Money=" + money2 + " where Date=convert(date,getdate(),103)");
                ps.executeUpdate();
            } else {
                ps = con.prepareStatement("insert into Revenue values(convert(date,getdate(),103)," + pay + ")");
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ aa");
        }
        //Viết vào file txt
        int guest = Integer.parseInt(txtGuest.getText());
        try {
            Date now = new Date();
            Writer bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("History//" + hihi.getText().trim() + ".txt"), "UTF8"));
            bw.write("\t\t\t Tân 3D COFFEE   \r\n\r\n");
            bw.write("\t\t590 Lê Quan Trung , Q.12, TPHCM\r\n");
            bw.write("\t\t\tSĐT: 0968456212\r\n\r\n");
            bw.write("\t\t\tHÓA ĐƠN BÁN HÀNG\r\n\r\n");
         bw.write("Mã hóa đơn: " + txtIDBill.getText() + "\r\n");
            bw.write("Thời gian: " + ft.format(now) + "\r\n");
            bw.write("NHÂN VIÊN: " + txtEmpName.getText() + "\r\n");
            bw.write("------------------------------------------------------------\r\n");
            bw.write("Mã\tKích thước\tSố lượng\tĐơn giá\tThành tiền\r\n");
            bw.write("-----------------------------------------------------------\r\n");
            //Ghi sản phẩm
            int quantotal = 0;
            for (int i = 0; i < line; i++) {
                String id = (String) tblModel.getValueAt(i, 0);
                String name = (String) tblModel.getValueAt(i, 1);
                String size = (String) tblModel.getValueAt(i, 3);
                String price = String.valueOf(tblModel.getValueAt(i, 4));
                String quantity = String.valueOf(tblModel.getValueAt(i, 5));
                String intomoney = String.valueOf(tblModel.getValueAt(i, 6));
                bw.write((i + 1) + ". " + name + "\r\n");
                bw.write(id + "\t" + size + "\t\t" + quantity + "\t\t" + price + "\t" + intomoney + "\r\n\r\n");
                quantotal += Integer.parseInt(quantity);
            }
            bw.write("------------------------------------------------------------\r\n");
            bw.write("Tổng cộng:\t\t" + quantotal + "\t\t\t" + txtTotal.getText() + " VNĐ\r\n");
            bw.write("\t\tChiết khấu:\t" + txtDis1.getText() + "%\t\t-" + txtDis2.getText() + " VNĐ\r\n");
            bw.write("\t\t--------------------------------------------\r\n");
            bw.write("\t\tThành tiền:\t\t\t" + txtPay.getText() + " VNĐ\r\n");
            bw.write("\t\t--------------------------------------------\r\n");
            bw.write("\t\tTiền khách đưa:\t\t\t" + formatter.format(guest) + " VNĐ\r\n");
            bw.write("\t\tTiền trả lại:\t\t\t" + txtRepay.getText() + " VNĐ\r\n");
            bw.write("------------------------------------------------------------\r\n");
            bw.write("Chương trình khuyến mãi: ");
            if (cbCTKM.getSelectedItem().equals("Không có")) {
                bw.write("Không có.\r\n");
            } else if (cbCTKM.getSelectedItem().equals("Khách hàng VIP")) {
                bw.write("Thành viên quán.\r\n");
                bw.write("-----Thông tin thành viên-----\r\n");
                bw.write("Mã thẻ: " + lbIDCus.getText() + "\r\n");
                bw.write("Tên thành viên: " + lbNameCus.getText() + "\r\n");
                bw.write("Ngày đăng ký: " + lbDateCus.getText() + "\r\n");
                bw.write("Số lượng cũ: " + lbQuantityCus.getText() + " ly.\r\n");
                bw.write("Số ly mới mua: " + quantotal + " ly.\r\n");
                bw.write("Chiết khấu (tính theo số lượng cũ): " + lbDisCus.getText() + "\r\n");
            } else {
                bw.write((String) cbCTKM.getSelectedItem() + "\r\n");
            }
            bw.write("------------------------------------------------------------\r\n");
            bw.write("Mật khẩu Wifi: motdentam\r\n");
            bw.write("---------------------CÁM ƠN QUÝ KHÁCH!----------------------");
            bw.close();
            //update số ly và chiết khấu vào bảng customer
            int quannew = Integer.parseInt(lbQuantityCus.getText()) + quantotal;
            if (quannew < 10) {
                try {
                    ps = con.prepareStatement("Update Customer set Quantity=?,Discount=? where IDCus=?");
                    ps.setInt(1, quannew);
                    ps.setInt(2, 0);
                    ps.setString(3, lbIDCus.getText());
                    ps.executeUpdate();
                } catch (Exception e) {
                }
            } else if (quannew >= 10 && quannew < 20) {
                try {
                    ps = con.prepareStatement("Update Customer set Quantity=?,Discount=? where IDCus=?");
                    ps.setInt(1, quannew);
                    ps.setInt(2, 5);
                    ps.setString(3, lbIDCus.getText());
                    ps.executeUpdate();
                } catch (Exception e) {
                }
            } else if (quannew >= 20 && quannew < 30) {
                try {
                    ps = con.prepareStatement("Update Customer set Quantity=?,Discount=? where IDCus=?");
                    ps.setInt(1, quannew);
                    ps.setInt(2, 10);
                    ps.setString(3, lbIDCus.getText());
                    ps.executeUpdate();
                } catch (Exception e) {
                }
            } else if (quannew >= 30 && quannew < 40) {
                try {
                    ps = con.prepareStatement("Update Customer set Quantity=?,Discount=? where IDCus=?");
                    ps.setInt(1, quannew);
                    ps.setInt(2, 15);
                    ps.setString(3, lbIDCus.getText());
                    ps.executeUpdate();
                } catch (Exception e) {
                }
            } else if (quannew >= 40 && quannew < 50) {
                try {
                    ps = con.prepareStatement("Update Customer set Quantity=?,Discount=? where IDCus=?");
                    ps.setInt(1, quannew);
                    ps.setInt(2, 20);
                    ps.setString(3, lbIDCus.getText());
                    ps.executeUpdate();
                } catch (Exception e) {
                }
            } else if (quannew >= 50) {
                try {
                    ps = con.prepareStatement("Update Customer set Quantity=?,Discount=? where IDCus=?");
                    ps.setInt(1, quannew);
                    ps.setInt(2, 25);
                    ps.setString(3, lbIDCus.getText());
                    ps.executeUpdate();
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
        //Mở file txt
        Runtime run = Runtime.getRuntime();
        try {
            run.exec("notepad History//" + hihi.getText().trim() + ".txt");
        } catch (IOException e) {
        }

        // set lại bảng, combobox và textbox
        tblModel.getDataVector().removeAllElements();
        tblBill.revalidate();
        setText(false);
        hihi.setEnabled(false);
        cbCTKM.setSelectedIndex(0);
        txtPay.setText("0");
        txtTotal.setText("0");
        txtGuest.setText("0");
        txtRepay.setText("0");
        ResetPnInfor();
        txtID.setText("");
        lbIDError.setText("");
        btnPrint.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBill = new javax.swing.JTable();
        btnDel = new javax.swing.JButton();
        lbTime = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cbCTKM = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        lbNhap = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        pnInformation = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbIDCus = new javax.swing.JLabel();
        lbNameCus = new javax.swing.JLabel();
        lbQuantityCus = new javax.swing.JLabel();
        lbDisCus = new javax.swing.JLabel();
        lbDateCus = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbIDError = new javax.swing.JLabel();
        lbNgayKM = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbSolve = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDis1 = new javax.swing.JTextField();
        txtGuest = new javax.swing.JTextField();
        txtPay = new javax.swing.JTextField();
        txtDis2 = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnPrint = new javax.swing.JButton();
        hihi = new javax.swing.JLabel();
        lbLoiGia = new javax.swing.JLabel();
        txtRepay = new javax.swing.JTextField();
        txtIDBill = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtEmpName = new javax.swing.JTextField();
        cbProduct = new javax.swing.JComboBox();
        spQuantity = new javax.swing.JSpinner();
        cbSize = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();

        jMenuItem4.setText("jMenuItem4");

        jMenuItem5.setText("jMenuItem5");

        jMenuItem1.setText("jMenuItem1");

        jMenu4.setText("jMenu4");

        jMenuItem2.setText("jMenuItem2");

        setBackground(new java.awt.Color(255, 255, 255));

        tblBill.setBackground(new java.awt.Color(0, 255, 0));
        tblBill.setSelectionBackground(new java.awt.Color(255, 0, 0));
        tblBill.getTableHeader().setReorderingAllowed(false);
        tblBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBill);

        btnDel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDel.setForeground(new java.awt.Color(255, 0, 0));
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/thungrac.png"))); // NOI18N
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        lbTime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbTime.setForeground(new java.awt.Color(255, 0, 0));
        lbTime.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 51));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Bán Hàng");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        cbCTKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCTKMActionPerformed(evt);
            }
        });
        cbCTKM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbCTKMKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 102));
        jLabel12.setText("Chương trình khuyến mãi:");

        lbNhap.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbNhap.setText("Nhập mã thẻ:");

        txtID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtID.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIDCaretUpdate(evt);
            }
        });

        pnInformation.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 204));
        jLabel13.setText("Thông tin khách hàng:");

        jLabel14.setText("Mã thẻ:");

        jLabel15.setText("Họ và tên:");

        jLabel16.setText("Số ly đã mua:");

        jLabel17.setText("Được giảm:");

        lbIDCus.setForeground(new java.awt.Color(204, 0, 51));
        lbIDCus.setText("...");

        lbNameCus.setForeground(new java.awt.Color(204, 0, 51));
        lbNameCus.setText("...");

        lbQuantityCus.setForeground(new java.awt.Color(204, 0, 51));
        lbQuantityCus.setText("...");

        lbDisCus.setForeground(new java.awt.Color(204, 0, 51));
        lbDisCus.setText("...");

        lbDateCus.setForeground(new java.awt.Color(204, 0, 51));
        lbDateCus.setText("...");

        jLabel19.setText("Ngày đăng ký:");

        javax.swing.GroupLayout pnInformationLayout = new javax.swing.GroupLayout(pnInformation);
        pnInformation.setLayout(pnInformationLayout);
        pnInformationLayout.setHorizontalGroup(
            pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(pnInformationLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbIDCus))
                    .addGroup(pnInformationLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNameCus))
                    .addGroup(pnInformationLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbQuantityCus))
                    .addGroup(pnInformationLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDisCus))
                    .addGroup(pnInformationLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDateCus)))
                .addContainerGap(221, Short.MAX_VALUE))
        );
        pnInformationLayout.setVerticalGroup(
            pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lbIDCus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lbNameCus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDateCus)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbQuantityCus)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lbDisCus))
                .addGap(5, 5, 5))
        );

        lbIDError.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbIDError.setForeground(new java.awt.Color(255, 0, 0));
        lbIDError.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbIDError.setToolTipText("");

        lbNgayKM.setForeground(new java.awt.Color(255, 0, 0));
        lbNgayKM.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 0, 51));
        jLabel9.setText("Thanh toán");

        jLabel6.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/tien1.png"))); // NOI18N
        jLabel6.setText("Tổng cộng:");

        jLabel5.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/phantram.png"))); // NOI18N
        jLabel5.setText("Chiết khấu:");

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/tien4.png"))); // NOI18N
        jLabel8.setText("Thành tiền:");

        jLabel4.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/tien2.png"))); // NOI18N
        jLabel4.setText("Tiền khách đưa:");

        jLabel7.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/tien4.png"))); // NOI18N
        jLabel7.setText("Tiền trả lại:");

        jLabel10.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 51, 0));
        jLabel10.setText("%");

        txtDis1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDis1.setForeground(new java.awt.Color(0, 0, 204));
        txtDis1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDis1.setText("0");
        txtDis1.setDisabledTextColor(new java.awt.Color(51, 0, 204));
        txtDis1.setEnabled(false);

        txtGuest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtGuest.setForeground(new java.awt.Color(0, 0, 204));
        txtGuest.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGuest.setText("0");
        txtGuest.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtGuestCaretUpdate(evt);
            }
        });
        txtGuest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGuestActionPerformed(evt);
            }
        });

        txtPay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPay.setForeground(new java.awt.Color(255, 0, 0));
        txtPay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPay.setText("0");
        txtPay.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtPay.setEnabled(false);
        txtPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPayActionPerformed(evt);
            }
        });

        txtDis2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDis2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDis2.setDisabledTextColor(new java.awt.Color(0, 0, 204));
        txtDis2.setEnabled(false);

        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");
        txtTotal.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTotal.setDisabledTextColor(new java.awt.Color(0, 0, 204));
        txtTotal.setEnabled(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        btnPrint.setBackground(new java.awt.Color(0, 0, 0));
        btnPrint.setFont(new java.awt.Font("Sitka Subheading", 1, 24)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("Lưu và In");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        hihi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hihi.setForeground(new java.awt.Color(204, 204, 204));
        hihi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/bill.png"))); // NOI18N
        hihi.setText("Mã hóa đơn:");

        lbLoiGia.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lbLoiGia.setForeground(new java.awt.Color(255, 0, 0));
        lbLoiGia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        txtRepay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtRepay.setForeground(new java.awt.Color(0, 0, 204));
        txtRepay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtRepay.setText("0");
        txtRepay.setDisabledTextColor(new java.awt.Color(0, 0, 204));
        txtRepay.setEnabled(false);

        txtIDBill.setText("O");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(19, 19, 19)
                        .addComponent(txtDis1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDis2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(28, 28, 28)
                        .addComponent(txtPay))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(28, 28, 28)
                        .addComponent(txtGuest))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hihi)
                            .addComponent(jLabel7))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRepay)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtIDBill)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbSolve)
                        .addGap(146, 146, 146)
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbLoiGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbSolve))
                    .addComponent(jLabel9))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(txtDis1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDis2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtGuest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbLoiGia)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(txtRepay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hihi)
                    .addComponent(txtIDBill)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbIDError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNgayKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbCTKM, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbNhap, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNgayKM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbNhap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lbIDError, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Sitka Heading", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("Tên nhân viên:");

        jLabel1.setFont(new java.awt.Font("Sitka Heading", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Tên sản phẩm");

        txtEmpName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtEmpName.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtEmpName.setEnabled(false);

        cbProduct.setEditable(true);

        spQuantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        spQuantity.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        cbSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nhỏ", "Vừa", "Lớn" }));

        jLabel11.setFont(new java.awt.Font("Sitka Heading", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 204));
        jLabel11.setText("Kích thước:");

        jLabel3.setFont(new java.awt.Font("Sitka Heading", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Số lượng:");

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/giohang.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmpName)
                    .addComponent(cbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel3))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spQuantity)
                    .addComponent(cbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(556, 556, 556))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(spQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(900, 900, 900)
                        .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)))
                .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        int line = tblBill.getRowCount();
        for (int i = 0; i < line; i++) {
            if (tblBill.getValueAt(i, 1).equals(cbProduct.getSelectedItem()) && tblBill.getValueAt(i, 3).equals(cbSize.getSelectedItem())) {
                int quanCu = (int) tblBill.getValueAt(i, 5);
                int quanMoi = (int) spQuantity.getValue();
                int quanTotal = quanCu + quanMoi;
                spQuantity.setValue(quanTotal);
                tblModel.removeRow(i);
                break;
            }
        }
        LoadTblFromDB();
        cbProduct.setSelectedIndex(-1);
        spQuantity.setValue(1);
        cbSize.setSelectedIndex(0);
        intomoney();
        UpdatetxtDis1();
        if (tblBill.getRowCount() > 0) {
            setText(true);
        } else {
            setText(false);
        }
        btnPrint.setEnabled(false);
        hihi.setEnabled(false);
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtGuestCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGuestCaretUpdate
        int Repay;
        //tính Discount
        while (true) {
            if (txtGuest.getText().trim().equals("")) {
                lbLoiGia.setText("Khách hàng chưa đưa tiền.");
                txtRepay.setText("0");
                btnPrint.setEnabled(false);
                hihi.setEnabled(false);
                return;
            } else if (!txtGuest.getText().trim().matches("\\d+")) {
                lbLoiGia.setText("Tiền có dạng số.");
                txtRepay.setText("0");
                btnPrint.setEnabled(false);
                hihi.setEnabled(false);
                return;
            } else {
                lbLoiGia.setText("");
                btnPrint.setEnabled(false);
                hihi.setEnabled(false);
                break;
            }
        }
        String total = txtPay.getText().replaceAll(",", "");
        Repay = Integer.parseInt(txtGuest.getText()) - Integer.parseInt(total);
        txtRepay.setText(formatter.format(Repay));
        if (Repay < 0) {
            lbLoiGia.setText("Khách hàng chưa đưa đủ tiền.");
            btnPrint.setEnabled(false);
            hihi.setEnabled(false);
            txtRepay.setText("0");
        } else if (Integer.parseInt(txtGuest.getText()) == 0) {
            btnPrint.setEnabled(false);
            hihi.setEnabled(false);
            txtRepay.setText("0");
        } else {
            lbLoiGia.setText("");
            btnPrint.setEnabled(true);
            hihi.setEnabled(true);
        }
    }//GEN-LAST:event_txtGuestCaretUpdate

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int line = tblBill.getSelectedRow();
        tblModel.removeRow(line);
        intomoney();
        UpdatetxtDis1();
        if (tblBill.getRowCount() > 0) {
            setText(true);
        } else {
            setText(false);
        }
        txtPay.setText("0");
        txtTotal.setText("0");
        txtGuest.setText("0");
        txtRepay.setText("0");
        btnPrint.setEnabled(false);
        hihi.setEnabled(false);
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        if (cbCTKM.getSelectedItem().equals("Khách hàng VIP")) {
            while (true) {
                if (txtID.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Mã thẻ VIP không được để trống!");
                    txtID.grabFocus();
                    return;
                } else if(!txtID.getText().trim().equals("") && !lbIDError.getText().equals("Thành công.")) {
                    JOptionPane.showMessageDialog(null, "Mã thẻ VIP chưa đúng, vui lòng nhập lại!");
                    txtID.grabFocus();
                    return;
                } else{
                    break;
                }
            }
        }
        
        try {
           
                ps = con.prepareStatement("Select * from Employee where NameEmp=?");
                ps.setString(1, txtEmpName.getText());
                rsEmp = ps.executeQuery();
                if (rsEmp.next()) {
                    PressPrintandSave(rsEmp.getString(1));
                } else{
                    JOptionPane.showMessageDialog(null, "Tên nhân viên không tồn tại.  nhân viên ");
                
            } 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ nhân viên");
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtIDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIDCaretUpdate
        btnPrint.setEnabled(false);
        hihi.setEnabled(false);
        if (txtID.getText().trim().equals("")) {
            txtDis1.setText("0");
            lbIDError.setText("Vui lòng nhập mã thẻ.");
            lbIDError.setForeground(Color.red);
            ResetPnInfor();
        } else {
            while (true) {
                if (!txtID.getText().trim().matches("\\d+")) {
                    lbIDError.setText("Mã thẻ dạng số.");
                    lbIDError.setForeground(Color.red);
                    txtDis1.setText("0");
                    ResetPnInfor();
                    return;
                } else {
                    break;
                }
            }
            try {
                ps = con.prepareStatement("Select * from Customer where IDCus=?");
                ps.setString(1, txtID.getText());
                rs = ps.executeQuery();
                if (!rs.next()) {
                    lbIDError.setText("Mã thẻ không tồn tại!");
                    lbIDError.setForeground(Color.red);
                    txtDis1.setText("0");
                    ResetPnInfor();
                } else {
                    lbIDError.setText("Thành công.");
                    lbIDError.setForeground(Color.BLUE);
                    lbIDCus.setText(rs.getString(1));
                    lbNameCus.setText(rs.getString(3));
                    lbDateCus.setText(rs.getString(4));
                    lbQuantityCus.setText(rs.getString(7));
                    lbDisCus.setText(rs.getString(8) + "%");
                    txtDis1.setText(rs.getString(8));
                    System.out.println("Thành Công");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
                System.out.println("ko thanh cong");
            }
        }
        UpdatetxtDis1();
    }//GEN-LAST:event_txtIDCaretUpdate

    private void cbCTKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCTKMActionPerformed
        btnPrint.setEnabled(false);
        hihi.setEnabled(false);
        if (cbCTKM.getSelectedItem().equals("Không có")) {
            txtDis1.setText("0");
            lbNgayKM.setText("");
            PanelOnOff(false);
            UpdatetxtDis1();
            ResetPnInfor();
            txtID.setText("");
            lbIDError.setText("");
        } else if (cbCTKM.getSelectedItem().equals("Khách hàng VIP")) {
            txtDis1.setText("0");
            lbNgayKM.setText("");
            UpdatetxtDis1();
            PanelOnOff(true);
        } else {
            PanelOnOff(false);
            ResetPnInfor();
            txtID.setText("");
            lbIDError.setText("");
            try {
                ps = con.prepareStatement("Select * from Promotions where NamePromo=?");
                ps.setString(1, (String) cbCTKM.getSelectedItem());
                rs = ps.executeQuery();
                if (rs.next()) {
                    Date start = ftnow.parse(rs.getString(4));
                    Date end = ftnow.parse(rs.getString(5));
                    txtDis1.setText(rs.getString(3));
                    lbNgayKM.setText(ftNgay.format(start) + " - " + ftNgay.format(end));
                    UpdatetxtDis1();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
            } catch (ParseException ex) {
                Logger.getLogger(Quanlybanhang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbCTKMActionPerformed

    private void tblBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillMouseClicked

    }//GEN-LAST:event_tblBillMouseClicked

    private void cbCTKMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbCTKMKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_F5){
            ReloadCombobox();
        }
    }//GEN-LAST:event_cbCTKMKeyPressed

    private void txtGuestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGuestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGuestActionPerformed

    private void txtPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPayActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnPrint;
    private javax.swing.JComboBox cbCTKM;
    private javax.swing.JComboBox cbProduct;
    private javax.swing.JComboBox cbSize;
    private javax.swing.JLabel hihi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDateCus;
    private javax.swing.JLabel lbDisCus;
    private javax.swing.JLabel lbIDCus;
    private javax.swing.JLabel lbIDError;
    private javax.swing.JLabel lbLoiGia;
    private javax.swing.JLabel lbNameCus;
    private javax.swing.JLabel lbNgayKM;
    private javax.swing.JLabel lbNhap;
    private javax.swing.JLabel lbQuantityCus;
    private javax.swing.JLabel lbSolve;
    private javax.swing.JLabel lbTime;
    private javax.swing.JPanel pnInformation;
    private javax.swing.JSpinner spQuantity;
    private javax.swing.JTable tblBill;
    private javax.swing.JTextField txtDis1;
    private javax.swing.JTextField txtDis2;
    private javax.swing.JTextField txtEmpName;
    private javax.swing.JTextField txtGuest;
    private javax.swing.JTextField txtID;
    private javax.swing.JLabel txtIDBill;
    private javax.swing.JTextField txtPay;
    private javax.swing.JTextField txtRepay;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
  private  void MahoadonLoad(){
      try {
          con = db.getCon();
            ps = con.prepareStatement("select * from [Order]");
            rs = ps.executeQuery();
            while(rs.next()){
            txtIDBill.setText(rs.getString(1));
            }
      } catch (Exception e) {
      }
  }
    private void ResetPnInfor() {
        lbIDCus.setText("...");
        lbNameCus.setText("...");
        lbDateCus.setText("...");
        lbQuantityCus.setText("...");
        lbDisCus.setText("...");
    }
}
