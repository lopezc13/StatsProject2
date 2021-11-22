import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.jfree.*;
import org.apache.*;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Plot {

	private int max;
	private int min;
	private double range;
	private ArrayList<Double> x = new ArrayList<Double>();
	private ArrayList<Double> y = new ArrayList<Double>();
	
	
	
	public Plot(int min, int max, double range)
	{
		
		
		this.min = min;
		this.max = max;
		this.range = range;
		
		
		
		
		
		
	}
	
	
	public void PlotPoints()
	{
		
		
		double r = this.range;
		int minimal = this.min;
		int maximum = this.max;
		double tempR = r;
		ArrayList<Double> tempPlot = new ArrayList<Double>();
		ArrayList<Double> tempPlotX = new ArrayList<Double>();
		// function Y = 2x^2 + 5x -1
		
		for(int i = minimal-1;i<maximum;i++)
		{
		
			
			double Y = Math.pow(2*tempR,2) + (5*tempR) -1;
			//System.out.println("("+tempR+","+Y+")");
			
			tempPlot.add(Y);// Y Value
			tempPlotX.add(tempR);// X Values
			//System.out.println(r);
			tempR = tempR+r;
			
			
		}
		this.x = tempPlotX;
		this.y= tempPlot;
		
		
		
		
		
	}
	
	public void saltPoints(int yRange) 
	{
		double temp = 0;
		ArrayList<Double> z = new ArrayList<Double>();
		
		// a copy of the arraylist to make sure data is not corrupted in a single for loop 
		for(int y = this.min-1;y<this.max;y++)
		{
			z.add(this.y.get(y));
			
			
		}
		//System.out.print(z);
		
		
		for(int x = this.min-1;x<this.max;x++)
		{
			double rnd = Math.random() * yRange;
			int coin = (int) (Math.random() * 2);
			//System.out.println(rnd);
			//System.out.println(coin);
			
			if(coin == 0) 
			{
				temp = z.get(x)+rnd;
				
			} else if (coin ==1) 
			{
				temp = z.get(x)-rnd;
			}
			
			
			
			
			
			
			
			
			
			this.y.add(x, temp); 
			
		}
		
		
		
		
		
	}
	
	public void printPlot() 
	{
		for (int i = 0; i<10;i++) {
			System.out.println("("+this.x.get(i) +","+this.y.get(i)+")");
		}
	}
	
	
	
	public void smoothPlot(int xWindow) 
	{
		
		double tempY=0;
		for (int x = this.min-1;x<this.max;x++)
		{
			// need to work on boundary cases
			if(xWindow>this.y.size()) 
			{
				xWindow= this.y.size();
			}
			if(x<xWindow)
			{
				tempY = tempY +this.y.get(x);
				
				//83
			}
			if(x==xWindow){
				tempY = tempY +this.y.get(x);
				
			}
			if (x>xWindow&&x<xWindow+xWindow+1) 
			{
				tempY = tempY +this.y.get(x);
			}
			//this.y.add(xWindow, tempY/xWindow+1);
			
			
			
			
			
			
			
		}this.y.add(xWindow-1, (tempY/(xWindow*2+1)));
		//System.out.println(tempY/(xWindow*2+1));
		
		
		
	}
	
	public void test() 
	{
		this.PlotPoints();
		this.printPlot();
		this.saltPoints(5);
		this.printPlot();
		this.saltPoints(5);
		this.printPlot();
	}
	
	
	public void writeToCsv() 
	{
		 try (PrintWriter writer = new PrintWriter("plot.csv")) {

		      StringBuilder sb = new StringBuilder();
		      //sb.append("id");
		      
		      sb.append("Plot");
		      sb.append(',');
		     // sb.append( temp.y.toString());
		      sb.append('\n');
		      
		      
		      
		      sb.append("X");
		      sb.append(',');
		      sb.append( this.x.toString());
		      sb.append('\n');
		      
		      sb.append("Y");
		      sb.append(',');
		      sb.append( this.y.toString());
		      sb.append('\n');

		      writer.write(sb.toString());

		      System.out.println("done!");

		    } catch (FileNotFoundException e) {
		      System.out.println(e.getMessage());
		    }
			
			
	}
	
	
	// we are trying to smooth with rolling avg
	public void  getRollingAverage(int window) 
	{
		
		DescriptiveStatistics idea = new DescriptiveStatistics(window);
		
		
		
		// fill apache dataset
		for (int x = this.min-1;x<this.max;x++   )
		{	 // two seperate loops 	
			idea.addValue(this.y.get(x));
			
			
			
			if(x<window)
			System.out.println(idea.getElement(x));
		}
	
		
		
		
		
		
		
		//System.out.println(idea.getMin());
		
		
		
	}
	
	
	
	
	public static void main(String[] args) 
	{
		
		Plot temp = new Plot(1,10,1);
		temp.PlotPoints();
		//temp.saltPoints(5);
		temp.printPlot();
		//temp.writeToCsv();
		//temp.writeToCsv();
		//temp.printPlot();
		//temp.saltPoints(5);
		//temp.printPlot();
		temp.getRollingAverage(4);
		
		

	   
		//DescriptiveStatistics idea = new DescriptiveStatistics(4);
		
		
		
		
		
		
		
		
	}
}


