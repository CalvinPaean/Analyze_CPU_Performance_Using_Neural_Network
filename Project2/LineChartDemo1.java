
/**
 * The program will obtain the data created by NeuralNetwork.java and 
 * plot them into charts. This program uses JFreeChart library. So your computer
 * should install this library first and it is free on the website ---
 * [http://www.jfree.org/jfreechart/]
 */
import java.awt.BasicStroke;  
import java.awt.Color;  
import java.awt.Dimension;  
import java.awt.geom.Ellipse2D;  
import java.io.BufferedReader;
import java.io.File;  
import java.io.FileReader;
import java.io.IOException;  
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;  
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
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;  
import org.jfree.ui.ApplicationFrame;  
import org.jfree.ui.RefineryUtilities;  

public class LineChartDemo1 extends ApplicationFrame {  
	static String eTitle;//store the file containing all error data
	
	//constructor
    public LineChartDemo1(String string) {  
         super(string);  
        JPanel jpanel = createDemoPanel();  
        jpanel.setPreferredSize(new Dimension(500, 270));  
         setContentPane(jpanel);  
    }//end-LineChartDemo1
  
    //create a data set to be plotted
    private static CategoryDataset createDataset() {  
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();  
        //obtain data from the file created by Neural network  
        int lineNum=0;//line number
        //get line number of the file
        try{
			FileReader fr = new FileReader(eTitle);
			BufferedReader bfr = new BufferedReader(fr);
			String line=bfr.readLine();
			while(line!=null){
				lineNum++;
				line  = bfr.readLine();
			}//end-while
			bfr.close();
			fr.close();
		}catch(Exception e){
			e.printStackTrace();
		}//end-try-catch
        System.out.println(lineNum);
        
        double[] dataPoints = new double[21];
        int index=0;
        int in = 0;
        try{
			FileReader fr = new FileReader(eTitle);
			BufferedReader bfr = new BufferedReader(fr);
			String line=bfr.readLine();
			while(line!=null){
				if((in++)%(lineNum/dataPoints.length)==0 && index<dataPoints.length)
					dataPoints[index++] = Double.parseDouble(line);
				line = bfr.readLine();
			}//end-while
			bfr.close();
			fr.close();
		}catch(Exception e){
			e.printStackTrace();
		}//end-try-catch
        
        for(int i=0;i<dataPoints.length;i++){
        	System.out.println(dataPoints[i]);
        }//end-for
        
        //20 data
        for(int i=0;i<dataPoints.length;i++){  
            defaultcategorydataset.addValue(dataPoints[i],  
                    "Epoch",String.valueOf((lineNum/dataPoints.length)*i) );  
        }//end-for 
        //delete all the contents in the error-data file.
        try{
        	PrintWriter writer = new PrintWriter(eTitle);
        	writer.print("");
        	writer.flush();
        	writer.close();
        }catch(Exception e){
        	e.printStackTrace();
        }//end-try-catch
        return defaultcategorydataset;  
    } //end-createDataset 
  
    //create a chart with specific requirements
    private static JFreeChart createChart(CategoryDataset categorydataset) {  
        JFreeChart jfreechart = ChartFactory.createLineChart(  
                "Epoch-error chart",//title
                "Epoch", // （x-axis）  
                "Errors",// （y-axis）  
                categorydataset, // data set
                PlotOrientation.VERTICAL,// direction 
                false,true,false);
           
        // background color
        jfreechart.setBackgroundPaint(Color.white);  
          
        //plot the chart
        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();  
        categoryplot.setBackgroundPaint(Color.white);  //background color
        categoryplot.setRangeGridlinePaint(Color.white);  //grid line color
        categoryplot.setRangeGridlinesVisible(false);  //grid line not visible 

        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
        numberaxis.setRange (0.00, 0.10);//set the range of y axis
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());  
            
        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); //the text of x-axis can be easily read
         domainAxis.setLowerMargin(0.0);   
         domainAxis.setUpperMargin(0.0); 

        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();  
        lineandshaperenderer.setShapesVisible(true);  
        lineandshaperenderer.setDrawOutlines(true);  
        lineandshaperenderer.setUseFillPaint(true);  
        lineandshaperenderer.setBaseFillPaint(Color.white);  //fill color
        lineandshaperenderer.setSeriesStroke(0, new BasicStroke(3.0F));  
        lineandshaperenderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));  
        lineandshaperenderer.setSeriesShape(0, new Ellipse2D.Double(-5.0, -5.0, 10.0, 10.0));  
        lineandshaperenderer.setItemMargin(0.4);  
        DecimalFormat decimalformat1 = new DecimalFormat("#.#####");// format of displaying data  
        lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator(  
                "{2}", decimalformat1));
        lineandshaperenderer.setBaseItemLabelsVisible(true);
        lineandshaperenderer.setBaseShapesFilled(true);  
        return jfreechart;  
    }  //end-createChart
  
    //create a panel to show the chart
    public static JPanel createDemoPanel() {  
        JFreeChart jfreechart = createChart(createDataset());   
        try {  
            ChartUtilities.saveChartAsJPEG(  
                    new File("D:/Threshold_0.001_Chart-2.png"), //address of storing chart  
                     1.0f,    //quality of chart ，0.0f~1.0f  
                     jfreechart, 
                    1024,  
                    768);  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }            
        return new ChartPanel(jfreechart);  
    }//end createDemoPanel  
  
    public static void main(String[] strings) {  
    	System.out.println("Please input the file address which holds errors' values: ");
		try{
			BufferedReader bfrd = new BufferedReader(new InputStreamReader(System.in));
			eTitle = bfrd.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}//end-try-catch
		
        LineChartDemo1 linechartdemo1 = new LineChartDemo1(  
                "Threshold_0.001_Chart-2");  
        linechartdemo1.pack();  
        RefineryUtilities.centerFrameOnScreen(linechartdemo1);  
        linechartdemo1.setVisible(true);  
        
        //delete the file that stores the errors' values.
        try{
        	File file = new File(eTitle);
        	file.delete();
        }catch(Exception e){
        	e.printStackTrace();
        }
    }  //end-main
  
    /* synthetic */  
    static Class class$(String string) {  
        Class var_class;  
        try {  
            var_class = Class.forName(string);  
        } catch (ClassNotFoundException classnotfoundexception) {  
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());  
        }  
        return var_class;  
    }//end-class$  
}  //end-class