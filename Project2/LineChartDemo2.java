
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class LineChartDemo2 extends ApplicationFrame {

    private static final long serialVersionUID = -6354350604313079793L;
    /* synthetic */static Class class$demo$LineChartDemo2;

    public LineChartDemo2(String string) {
         super(string);
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
         setContentPane(jpanel);
    }
    
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        double[] comp1 = {3.068895181437563, 0.41849014363338355, 1.7457612901525108, -1.072885931992936, 1.34887688508634, 1.1545942304154517, -1.0928673614121542, 0.801740915855917, -0.45240121842682973, -0.5290747413746102, 1.0407893571603077, 1.4157968506602367, -0.3096582871373117, 1.0395087775174083, 0.13848683319738264, 0.2656114541158491, 0.394078601052223, 0.20171276779306802, -0.33297267492940114, 1.8092649076053386, 0.15717146774125512, -7.480389056034305, 0.8664460685601818, 0.1595695590521706};
       double[] comp2 = {2.018319404590006, 0.5285060509673671, 2.4429370174031297, 0.01575942410892352, 0.08716352836794658, -1.5998170002863852, -1.657367330213325, 1.5177646198131947, -1.1673063142855102, -0.4102739936505453, 0.30106480126776175, -0.6803318962773288, -0.07389039895083285, 0.9697464834795433, 0.8153226379793883, 0.9713073617633685, 0.9489746144386406, 0.3816388957190291, -1.250662341191915, 1.764016187050276, -0.6501337314940963, -3.453059579607419, 0.9257277536388722, -4.048235846779631};
        double[] comp3 ={0.9677050696130661, 0.34790988909321685, 0.46857161549405485, -0.06608713501686682, 1.6478435166989351, 0.4490712319405222, -1.0019606073023417, -2.469915093528045, -1.9972627899498956, -0.25800363430126455, -3.5988521136534346, -1.7705595514858359, 1.8111099698058224, 0.6056643182130607, 0.41968147428302033, 0.0026008244148482264, 2.5681691036445256, 0.9234045447394361, 0.9201591509449241, 2.029920094124157, 0.7806903782624406, 3.0916246231657736, 7.052500279518403, -17.937293724639815};
        double[] comp4={0.678690983373701, 0.6068863039134603, 0.524646535931502, -0.17063370526707775, 0.8628670472529243, 0.0759031696323422, -1.040609826070455, -2.5474263826866292, -1.9865953556753397, -0.3240081004553215, -3.5830423534353257, -1.6806000864403148, 1.806765987961375, 0.7876696424628783, 0.4899835643204287, 1.0098485289171895, 2.4174115922643193, 0.9828101024967129, 0.7431927715844727, 2.8409254065287715, 0.9983520891489454, 3.3501928486074566, 6.400930375056133, -17.533588571777887};
        double[] comp5={-2.303588050168534, -1.071318092847127, -1.6415469951438375, -3.5606258113467306, -1.6078861146336052, -0.4329588215459802, -3.45837174868928, -2.407584457364578, -1.699773589498611, -1.2001421049479393, -1.9917597691144382, -4.0500739008088456, -2.0873464162398805, -1.3360145383117643, -1.3880415715060503, -0.5455611948926716, 0.7390912328431472, 3.026047231181294, 9.532180610998084, 4.648891693869785, 4.358121493013824, 8.050091877820153, -22.78140558046731, 6.929937367880352};
        double[] comp6={0.8782608557089355, -1.743380771995503, -0.680876621048059, 7.69725590114782, 2.985210976970883, 1.8877990193962224, 3.072688792182264, -2.2497246050484336, -1.6812939656435737, 4.206084210803893, -3.74262647427343, -2.0186935124067555, 0.7154381142372433, -2.279765485828273, -1.2827617249981593, -7.348833171263777, 3.853123086128208, 1.3911331502343023, -8.75192315911962, 1.7963697672327623, 0.2637185657998494, 1.7376931053168096, 10.96651069192475, -20.572450299699547};
        //图例1，40个数据
        for (int i = 0; i < comp5.length; i++) {
            defaultcategorydataset.addValue(comp1[i],
                    "0.1First", String.valueOf( i));
        }
        for (int i = 0; i < comp5.length; i++) {
            defaultcategorydataset.addValue(comp2[i],
                    "0.1Second", String.valueOf( i));
        }
        for (int i = 0; i < comp5.length; i++) {
            defaultcategorydataset.addValue(comp3[i],
                    "0.01First", String.valueOf( i));
        }
        for (int i = 0; i < comp5.length; i++) {
            defaultcategorydataset.addValue(comp4[i],
                    "0.01Second", String.valueOf( i));
        }
        for (int i = 0; i < comp5.length; i++) {
            defaultcategorydataset.addValue(comp5[i],
                    "0.001First", String.valueOf( i));
        }
        for (int i = 0; i < comp5.length; i++) {
            defaultcategorydataset.addValue(comp6[i],
                    "0.001Second", String.valueOf( i));
        }
//*/        
        
        return defaultcategorydataset;
    }

    private static JFreeChart createChart(CategoryDataset categorydataset) {
        JFreeChart jfreechart = ChartFactory.createLineChart(
                "Weights comparison",// 图表标题
                "Edge", // 主轴标签（x轴）
                "Weight",// 范围轴标签（y轴）
                categorydataset, // 数据集
                PlotOrientation.VERTICAL,// 方向
                false, // 是否包含图例
                true, // 提示信息是否显示
                false);// 是否使用urls
         
        // 改变图表的背景颜色
        jfreechart.setBackgroundPaint(Color.white);
        
        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
        categoryplot.setBackgroundPaint(Color.lightGray);
        categoryplot.setRangeGridlinePaint(Color.white);
        categoryplot.setRangeGridlinesVisible(false);

        //修改范围轴。 我们将默认刻度值 （允许显示小数） 改成只显示整数的刻度值。
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        // 设置X轴上的Lable让其45度倾斜 
        CategoryAxis domainAxis = categoryplot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 设置X轴上的Lable让其45度倾斜     
         domainAxis.setLowerMargin(0.0);   // 设置距离图片左端距离 
         domainAxis.setUpperMargin(0.0);  // 设置距离图片右端距离 
        
          
          
        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot
                .getRenderer();
        lineandshaperenderer.setShapesVisible(true);
        lineandshaperenderer.setDrawOutlines(true);
        lineandshaperenderer.setUseFillPaint(true);
        lineandshaperenderer.setBaseFillPaint(Color.white);
        lineandshaperenderer.setSeriesStroke(0, new BasicStroke(3.0F));
        lineandshaperenderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));
        lineandshaperenderer.setSeriesShape(0, new Ellipse2D.Double(-5.0, -5.0,
                10.0, 10.0));
        lineandshaperenderer.setItemMargin(0.4); //设置x轴每个值的间距（不起作用？？）
        
        // 显示数据值
        DecimalFormat decimalformat1 = new DecimalFormat("##.##");// 数据点显示数据值的格式
        lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator(
                "{2}", decimalformat1));//  设置数据项标签的生成器
        lineandshaperenderer.setBaseItemLabelsVisible(true);// 基本项标签显示
        lineandshaperenderer.setBaseShapesFilled(true);// 在数据点显示实心的小图标
                
        
        return jfreechart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart(createDataset());

        try {
            ChartUtilities.saveChartAsJPEG(
                    new File("C:/weights-ALL.png"), //文件保存物理路径包括路径和文件名 
                 //    1.0f,    //图片质量 ，0.0f~1.0f
                     jfreechart, //图表对象 
                    1024,   //图像宽度 ，这个将决定图表的横坐标值是否能完全显示还是显示省略号
                    768);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    //图像高度 
                 
        return new ChartPanel(jfreechart);
    }

    public static void main(String[] strings) {
        LineChartDemo2 linechartdemo1 = new LineChartDemo2(
                "JFreeChart - Line Chart Demo 1");
        linechartdemo1.pack();
        RefineryUtilities.centerFrameOnScreen(linechartdemo1);
        linechartdemo1.setVisible(true);
    }

    /* synthetic */
    static Class class$(String string) {
        Class var_class;
        try {
            var_class = Class.forName(string);
        } catch (ClassNotFoundException classnotfoundexception) {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
        return var_class;
    }
}