/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class ThongKeServiceImpl implements ThongKeService{
  private ThongKeDAO thongKeDAO = null;
  public ThongKeServiceImpl(){
      this.thongKeDAO = new ThongKeoDAOIMPL();
  }
    @Override
    public List<DoanhThu> getListDoanhThu() {
        return thongKeDAO.getListbyDoanhThu();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
