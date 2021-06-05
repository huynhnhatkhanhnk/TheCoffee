/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class DBHelper {

    Connection con;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public DBHelper() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost;databaseName=caphe1";
            con = DriverManager.getConnection(url, "sa", "123");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 100:: Không tìm thấy lớp");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 102:: Cấu hình bị trống");
        } catch (Exception e) {
        }
    }

}
