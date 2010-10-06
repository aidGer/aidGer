package de.aidger.view.utils;

import static de.aidger.utils.Translation._;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.Rotation;

/**
 * This class constructs various charts using JFreeChart.
 */
public class Charts {

    /**
     * Creates a 3D pie chart.
     * 
     * @param title
     *            the diagram title
     * @param dataset
     *            the dataset.
     * @param width
     *            the width of the chart as image
     * @param height
     *            the height of the chart as image
     * 
     * @return the 3D pie chart as image
     */
    public static ImageIcon createPieChart3D(String title, PieDataset dataset,
            int width, int height) {
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true,
            true, false);

        Font titleFont = UIManager.getFont("TitledBorder.font");

        chart.setBackgroundPaint(null);
        chart.getLegend().setBackgroundPaint(null);
        chart.getTitle().setFont(
            new Font(titleFont.getName(), titleFont.getStyle(), 14));
        chart.getTitle()
            .setPaint(UIManager.getColor("TitledBorder.titleColor"));
        chart.setBorderPaint(null);
        chart.getLegend().setBorder(0, 0, 0, 0);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setForegroundAlpha(0.9f);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setNoDataMessage(_("No data to display."));
        plot.setLabelGenerator(null);
        plot.setInsets(new RectangleInsets(10, 1, 5, 1));
        plot.setBackgroundPaint(null);
        plot.setOutlineVisible(false);
        plot.setDarkerSides(true);

        return new ImageIcon(chart.createBufferedImage(width, height));
    }

    /**
     * Creates a xy area chart.
     * 
     * @param title
     *            the diagram title
     * @param dataset
     *            the dataset.
     * @param width
     *            the width of the chart as image
     * @param height
     *            the height of the chart as image
     * @return the xy area chart as image
     */
    public static ImageIcon createXYAreaChart(String title, XYDataset dataset,
            int width, int height) {
        JFreeChart chart = ChartFactory.createXYAreaChart(title, "", "",
            dataset, PlotOrientation.VERTICAL, false, false, false);

        Font titleFont = UIManager.getFont("TitledBorder.font");

        chart.setBackgroundPaint(null);
        chart.getTitle().setFont(
            new Font(titleFont.getName(), titleFont.getStyle(), 14));
        chart.getTitle()
            .setPaint(UIManager.getColor("TitledBorder.titleColor"));
        chart.setBorderPaint(null);

        XYPlot plot = chart.getXYPlot();
        plot.setInsets(new RectangleInsets(10, 1, 5, 1));
        plot.setBackgroundPaint(null);
        plot.setOutlineVisible(false);
        plot.setNoDataMessage(_("No data to display."));

        ValueAxis domainAxis = new DateAxis();
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        domainAxis.setTickLabelFont(UIManager.getFont("RootPane.font"));

        ValueAxis rangeAxis = new NumberAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);

        plot.setForegroundAlpha(0.5f);

        return new ImageIcon(chart.createBufferedImage(width, height));
    }
}
