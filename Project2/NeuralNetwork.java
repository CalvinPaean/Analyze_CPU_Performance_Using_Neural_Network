
/**
 * This program predicts the relative performance of CPU based on 
 * 7 features, using multilayer neural network. We can see that the smaller 
 * the threshold is, the longer time it takes to predict the performance.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Random;

public class NeuralNetwork {
	static String title;//file name
	static String errorTitle;//file storing the errors' values.
	static String weightTitle;//file of final weights
	static int dataNum;//the number of data points
	static double dataPoint[][];//store information of data points
	static double input[][];//store information of data inputs
	static double normalizedInput[][];//store normalized inputs
	static double trueOutput[];//store true outputs of data points
	static double normalizedOutput[];//store normalized outputs of data points
	static double weight[][];//store weights of each edge
	
	static  int FEAT = 7; //feature numbers
	static  double alpha = 0.5;//learning rate
	static  double threshold;//threshold of stopping the loop
	
	public static void main(String[] args){
		System.out.println("Please specify the input file's address: ");
		try{
			title = getFileName();
		}catch(Exception e){
			e.printStackTrace();
		}//end try-catch
		System.out.println("Please specify the value of threshold: ");
		try{
			BufferedReader bfrd = new BufferedReader(new InputStreamReader(System.in));
			String input = bfrd.readLine();
			threshold = Double.parseDouble(input);
		}catch(Exception e){
			e.printStackTrace();
		}//end-try-catch
		System.out.println("Please input the file address which holds errors' values(Do not worry. "
				+ "It will be automatically deleted in the end): ");
		try{
			BufferedReader bfrd = new BufferedReader(new InputStreamReader(System.in));
			errorTitle = bfrd.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}//end-try-catch
		
		System.out.println("Please input the file that stores the final weights: ");
		try{
			BufferedReader bfrd = new BufferedReader(new InputStreamReader(System.in));
			weightTitle = bfrd.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}//end-try-catch
		
		//If the file storing errors' values does not exist, create one.
		try{
			File tempFile = new File(errorTitle);
			if(!tempFile.exists())
				tempFile.createNewFile();
		}catch(Exception e){
			e.printStackTrace();
		}//end-try-catch
     
		initialize();

		double maxOutput=0;
		for(int i=0;i<dataNum;i++){
			trueOutput[i] = dataPoint[i][7];
			if(trueOutput[i]>maxOutput)
				maxOutput=trueOutput[i];
		}//update all true outputs and get the max-value of true outputs
		
		//normalize each output variable
		for(int i=0;i<dataNum;i++){
			normalizedOutput[i] = trueOutput[i]/maxOutput;
		}//end-normalize output variables
		
		//normalize each input variable 
		double maxFeature[] = new double[FEAT];
		for(int i=0;i<FEAT;i++){
			maxFeature[i] = input[0][i];
			for(int j=1;j<dataNum;j++){
				if(input[j][i]>maxFeature[i])
					maxFeature[i] = input[j][i];
			}//end-for
		}//end-for
		normalizedInput = new double[dataNum][FEAT];
		double[][] trainNormalizedInput = new double[dataNum-40][FEAT];//part of normalized inputs
		double[][] testNormalizedInput = new double[40][FEAT];
		for(int i=0;i<dataNum;i++){
			for(int j=0;j<FEAT;j++){
				normalizedInput[i][j] = input[i][j]/maxFeature[j];
				if(i<trainNormalizedInput.length)
					trainNormalizedInput[i][j] = normalizedInput[i][j];
				else
					testNormalizedInput[i-(dataNum-40)][j] = normalizedInput[i][j];
			}//end-for
		}//end-normalize input variables
			
		backPropLearning(trainNormalizedInput, normalizedOutput, weight);
		outputPrediction(testNormalizedInput, weight);
		writeIntoFile(weight);//output all final weights to a text file.
	}//end-main
	
	//output the final results of predicted performances in console.
	public static void outputPrediction(double[][] input, double[][] weight){
		System.out.println("The final predicted performances: ");
		for(int i=0;i<input.length;i++){
			double[] a = new double[11];
			double[] in = new double[11];
			for(int j=0;j<7;j++){
				a[j] = input[i][j];
			}
			for(int j=7;j<=10;j++){
				in[j] = sum1(weight, a, j);
				a[j] = g(in[j]);
			}
			System.out.println(i +": " + a[10]);//the final predicted performance
		/*	try{//output all results to a text file
				FileWriter writer = new FileWriter("D:\123.txt",true);
				writer.write(a[10]+"");
				writer.write("\r\n");
				writer.flush();
				writer.close();
			}catch(Exception e){
				e.printStackTrace();
			}//end try-catch
			*/
		}
	}//end-outputPrediction
	
	public static void backPropLearning(double input[][], double output[], double weight[][]){
		double in[] = new double[11];//input of each node
		double a[] = new double [11];//output of each node
		double[] delta = new double[11];//error of each node
		Random rand = new Random();//obtain random weights
		for(int i=0;i<weight.length;i++){
			for(int j=0;j<weight[i].length;j++){
				weight[i][j] =rand.nextDouble()*2;
			}//end-for
		}//initialize weights randomly

		int index=0;//counter
		double pre=Integer.MAX_VALUE;//if the i-th error value<= (i+1)-th, break;
		
		while(true){
			double totalError=0;//total errors
			for(int i=0;i<input.length;i++){//i is the index of data point
				
					for(int j=0;j<7;j++){//j is the index of feature of each data point
						a[j] = input[i][j];
					}//end-for
					
					for(int j=7;j<=10;j++){
						in[j] = sum1(weight, a, j);
						a[j] = g(in[j]);
					}//end-for
					
				//***back propagation***
					delta[10] = derivative_g(in[10]) * (output[i] - a[10]);
					totalError+= (output[i]-a[10])*(output[i]-a[10]);
					
					for(int m=7;m<10;m++){
						delta[m] = derivative_g(in[m]) * (weight[m][10] * delta[10]);
					}//end-for
					for(int m=0;m<7;m++){
						delta[m] = derivative_g(a[m]) * sum(weight, delta, m);
					}//end-for
					for(int m=0;m<weight.length;m++){
						for(int n=0;n<weight[m].length;n++){
							if(m<7 && n>6 && n<10){
								weight[m][n] += alpha *a[m]*delta[n];
							}
							if(m>6 && m<10 && n==10)
								weight[m][n] += alpha *a[m]*delta[n];
						}//end-for
					}//end-for
				}//end-for
			System.out.println(index++ +": " + (totalError)/2);
		//write all data into file storing errors' values so that 
		//LineChartDemo1.java can obtain data from it. 
			try{//output all results to a text file
				FileWriter writer = new FileWriter(errorTitle,true);
				writer.write(totalError/2+"");
				writer.write("\r\n");
				writer.flush();
				writer.close();
			}catch(Exception e){
				e.printStackTrace();
			}//end try-catch
			//end writing data into file 1.txt
			if((totalError/2<threshold) || (pre<=totalError))
				break;
			pre = totalError;
			}//end-while
		}//end-backPropLearning
	
	public static double sum(double weight[][], double delta[], int m){
		double result=0;
		for(int i=0;i<3;i++){
			result += weight[m][i+7]*delta[7+i];
		}//end-for
		return result; 
	}//end-sum
	
	public static double derivative_g(double x){
		return g(x)*(1-g(x));
	}//end-derivative_g
	
	public static double g(double m){
		return 1/(1+Math.exp(-m));
	}//end-g
	
	public static double sum1(double weight[][], double a[], int m){
		double result=0;
		if(m!=10){
			for(int i=0;i<7;i++){
				result += weight[i][m]*a[i];
			}//end-for
		}else{
			for(int i=7;i<10;i++){
				result += weight[i][m]*a[i];
			}//end-for
		}//end-if
		return result;
	}//end-sum1
	
	public static void initializeDataPoints(String title){
		try{
			FileReader fr = new FileReader(title);
			BufferedReader bfr = new BufferedReader(fr);
			String line=bfr.readLine();
			int index=0;
			while(line!=null){
				for(int i=0;i<FEAT+1;i++){
					dataPoint[index][i] = Double.parseDouble(line.split(",")[i+2]);
				}//end-for
				line=bfr.readLine();
				index++;
			}//end-while
			bfr.close();
			fr.close();
		}catch(Exception e){
			e.printStackTrace();
		}//end-try-catch
	}//end-initialize

	public static void writeIntoFile(double[][] weight){
		
		try{//output all weights to a text file
			File file = new File(weightTitle);// if file does not exist, create one.
			if(!file.exists())
				file.createNewFile();
			
			FileWriter writer = new FileWriter(weightTitle,true);
			writer.write("Threshold: " + threshold+"\r\n");
			for(int i=0;i<weight.length;i++){
				if(i<7){
					writer.write(weight[i][7] +", "+weight[i][8]+", "+weight[i][9]+", ");
				}else{
					writer.write("\r\n");
					writer.write(weight[i][10]+", ");
				}//end-if-else
			}//end-for
			writer.write("\r\n");
			writer.flush();
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}//end try-catch
	}
	
	public static void initialize(){
		dataNum = getDataLineNum(title);//get the number of data points
		input = new double[dataNum][FEAT];//initialize input
		trueOutput = new double[dataNum];//initialize trueOutput
		normalizedOutput=new double[dataNum];//initialize normalizedOutput
		weight = new double[10][11];//initialize weight	
		dataPoint = new double[dataNum][FEAT+1];//initialize dataPoint
		initializeDataPoints(title);//initialize dataPoint array
		for(int i=0;i<dataNum;i++){
			for(int j=0;j<7;j++){
				input[i][j] = dataPoint[i][j];
			}//end-for
		}//update all features of inputs
	}//end-initialize
	
	public static int getDataLineNum(String title){
		int num=0;
		try{
			FileReader fr = new FileReader(title);
			BufferedReader bfr = new BufferedReader(fr);
			while(bfr.readLine()!=null){
				num++;
			}//end-while
			bfr.close();
			fr.close();
		}catch(Exception e){
			e.printStackTrace();
		}//end-try-catch
		return num;
	}//end-getDataLineNum
	
	public static String getFileName()throws Exception{
		BufferedReader bfrd = new BufferedReader(new InputStreamReader(System.in));
		String input = bfrd.readLine();
		return input;
	}//end getFileName
}//end-class