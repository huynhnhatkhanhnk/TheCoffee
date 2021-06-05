/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Administrator
 */
public class QuanLyThongKeController {
    private ThongKeService  thongKeService =null;
    public QuanLyThongKeController(){
        this.thongKeService = new ThongKeServiceImpl();
    }
    public void setDataToChart1(JPanel jpnItem) {
        List<DoanhThu> listItem = thongKeService.getListDoanhThu();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (DoanhThu item : listItem) {
                dataset.addValue(item.getTien(), "tiền", item.getThang());
            }
        }
         JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ Doanh Thu".toUpperCase(),
                "Thời gian tính bằng tháng", "Tiền",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
    
}
