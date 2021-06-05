/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */

public class ThongKeoDAOIMPL  implements ThongKeDAO{
    ResultSet rs;
    Connection con;
    PreparedStatement ps;
    Helper.DBHelper db = new Helper.DBHelper();
    @Override
    public List<DoanhThu> getListbyDoanhThu() {

    List<DoanhThu> list  = new ArrayList<>();
         try {
          con = db.getCon();
            ps = con.prepareCall("Select MONTH(Date) as thang, SUM(Money) as tien from Revenue\n" +
            "group by   MONTH(Date)");
            rs = ps.executeQuery();
            while (rs.next()) {
               DoanhThu  doanhThu =  new DoanhThu();
               doanhThu.setThang(rs.getString("thang"));
               doanhThu.setTien(rs.getInt("tien"));
               list.add(doanhThu); 
            }
            ps.close();
            con.close();
            return list;
          
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
         return  null;
    }
  
    
}
