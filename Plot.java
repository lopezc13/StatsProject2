import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.jfree.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
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
		
		//double tempY=0;
		double tempY2 = 0;
		
		
		ArrayList<Double> z = new ArrayList<Double>();
		for(int y = this.min-1;y<this.max;y++)
			{
				z.add(this.y.get(y));
				
				
			}
		
		
		int bottom = this.min-1;
		int top = xWindow;
		int mid = xWindow/2;
		
		
		
		for (int j = 0; j < xWindow; j++) 
		{
		
		
			for (int i = bottom; i < top; i++) 
			{
			
				tempY2 = tempY2+ z.get(i);
				
			
			
			
			}
				bottom++;
				top++;
				System.out.println(tempY2/xWindow);
				this.y.set(mid, tempY2/xWindow);
				mid++;
				// reset temp 
				tempY2=0;
				
				
		}
		// move window up a step 
			
		
		/*
		
		for (int x = this.min-1;x<this.max;x++)
		{
			
			
			tempY2 = this.y.get(x);
			
			
			
			
			
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
			
			
			
			
			
			
			
		}this.y.set(xWindow, (tempY/(xWindow*2+1)));
		//System.out.println(tempY/(xWindow*2+1));
		System.out.println("five average"+ tempY2);
		*/
		
		
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
		
		
		int mid = (window/2); ;
		//int len = 0;
		
		
		// playing with length
//		if ((window % 2)>0) 
//		{
//			mid = (window/2)+1;
//			len = window/2;
//		}
		
		// a copy of the arraylist to make sure data is not corrupted in a single for loop 
		ArrayList<Double> z = new ArrayList<Double>();
			for(int y = this.min-1;y<this.max;y++)
				{
					z.add(this.y.get(y));
					
					
				}
		
		
		
		// fill apache dataset
		
		int bottom = this.min-1;
		int top = window;
			for (int i = 0; i < window; i++) 
			{
			
		
				for (int x = bottom;x<top;x++   )
					{	 // two seperate loops 	
					idea.addValue(z.get(x));
			
			
			
			
		
					}
		// move window up a step 
			bottom++;
			top++;
			this.y.set(mid, idea.getMean()); 
			mid++;
			
		//get mean here
		    }
		// we need to find where  to change value for smooth
		// update - change value at middle of window 
		
		
		
		
		//System.out.println(idea.getMin());
		
		
		
	}
	
	
	
	
	private XYDataset createDataset() {
	    XYSeriesCollection dataset = new XYSeriesCollection();

	    XYSeries series = new XYSeries("Y = X + 2");
	    series.add(2, 4);
	    series.add(8, 10);
	    series.add(10, 12);
	    series.add(13, 15);
	    series.add(17, 19);
	    series.add(18, 20);
	    series.add(21, 23);

	    //Add series to dataset
	    dataset.addSeries(series);
	    
	    return dataset;
	  }
	
	
	public double getX(int i) 
	{
		return this.x.get(i);
	}
	
	public double getY(int i)
	{
		return this.y.get(i);
	}
	
	
	
	public void historgam(int increment) 
	{
	int bottom = 0;
	int count = 0;
	int save = 0;
	int inc = increment ;
		
		for(int j = 0; j < this.max;j++) {
			
		
		for(int i = bottom ;i <this.max;i++)
		{
			
		if( this.y.get(i)<increment) 
		{
			
			//System.out.print(1);
			//System.out.println(increment);
			count++;
			save++;
		}
			
		
			
			
			
		}
		System.out.println("Increment is " + increment+ " count is "+ save);
		save = 0;
		bottom = count;
		System.out.println(" ");
		increment = increment + inc ;
		
		}
		
		
		
		
		
	}
	
	
	public static void main(String[] args) 
	{
		
		Plot temp = new Plot(1,10,1);
		temp.PlotPoints();
		temp.historgam(100);
		//temp.writeToCsv();
		//temp.saltPoints(5);
		//temp.printPlot();
		//temp.smoothPlot(5);
		//temp.writeToCsv();
		//temp.writeToCsv();
		//temp.printPlot();
		//temp.saltPoints(40);
		//temp.printPlot();
		//System.out.println("new section");
		//temp.printPlot();
		//temp.getRollingAverage(5); // smooth rolling avg for middle number
		//temp.printPlot();
		
		

	   
		//DescriptiveStatistics idea = new DescriptiveStatistics(4);
		
		
		
		
		
		
		
		
	}
}


