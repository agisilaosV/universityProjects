import java.io.*;
import java.util.*;
import org.jfree.data.category.*;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

public class FirstChart
{
	private ArrayList<Integer> dataStructuresChanged;
	private ArrayList<Integer> operationsChanged;
	private ArrayList<Integer> versionPerYear;
	private int alreadyEvaluated;
	private String userEvaluation;    // tha apothikeuei yes/no analoga me to an isxuei o nomos
	private ArrayList<History> versions;
	private String path;
	private ArrayList<Integer> y;

	public FirstChart(ArrayList<History> versionList, String path)
	{
		versions = new ArrayList<History>();
		dataStructuresChanged = new ArrayList<Integer>();
		operationsChanged = new ArrayList<Integer>();
		versionPerYear = new ArrayList<Integer>();
		y = new ArrayList<Integer>();
		this.path = path;
		for(int i = 0;i<versionList.size();i++)
			versions.add(new History(versionList.get(i)));
	}
	
	public int getAlreadyEvaluated()
	{
		return this.alreadyEvaluated;
	}
	
	public void computeMetrics()
	{
		int counter=1;
		ArrayList<Integer> years = new ArrayList<Integer>();
		int[] meres = new int[3];  //tha krataei thn epistrefomenh hmeromhnia
		for(int i=0;i<versions.size();i++)
		{
			dataStructuresChanged.add(versions.get(i).getDataStructuresAdded() + versions.get(i).getDataStructuresUpdated());
			operationsChanged.add(versions.get(i).getOperationsAdded() + versions.get(i).getOperationsUpdated());
			meres = versions.get(i).getDate();
			years.add(meres[2]);
		}
		for(int j=1;j<years.size();j++)
		{
			if(years.get(j)==years.get(j-1))
				counter++;
			else
			{
				versionPerYear.add(counter);
				y.add(years.get(j));
				counter = 1;
			}
		}	
	}
	
	public void printChart()
	{
		DefaultCategoryDataset datasetForDataStructuresChanged=new DefaultCategoryDataset();
        for(int i=0;i<versions.size();i++)
			datasetForDataStructuresChanged.setValue(dataStructuresChanged.get(i),"",""+versions.get(i).getId());
        JFreeChart myChartForDataStructuresChanged=ChartFactory.createBarChart("Data Structures Changed","Id","Numer of Changes",datasetForDataStructuresChanged,PlotOrientation.VERTICAL,true,true,false);
        ChartFrame frameForDataStructuresChanged=new ChartFrame("Demo",myChartForDataStructuresChanged);
        frameForDataStructuresChanged.setVisible(true);
        
        DefaultCategoryDataset datasetForOperationsChanged=new DefaultCategoryDataset();
        for(int i=0;i<versions.size();i++)
			datasetForOperationsChanged.setValue(operationsChanged.get(i),"",""+versions.get(i).getId());
        JFreeChart myChartForOperationsChanged=ChartFactory.createBarChart("Opeations Changed","Id","Numer of Changes",datasetForOperationsChanged,PlotOrientation.VERTICAL,true,true,false);
        ChartFrame frameForOperationsChanged=new ChartFrame("Demo",myChartForOperationsChanged);
        frameForOperationsChanged.setVisible(true);
        
        ArrayList<Integer> years = new ArrayList<Integer>();
        int[] meres = new int[3];  //tha krataei thn epistrefomenh hmeromhnia
        
        for(int i=0;i<versions.size();i++)
        {
            meres = versions.get(i).getDate();
            years.add(meres[2]);
        } 
        int i=0;
        DefaultCategoryDataset datasetForVersions=new DefaultCategoryDataset();
        for(int k=0;k<y.size();k++)
        {
	//		System.out.println("i is: "+k+" ekdoseis ana xrono"+versionPerYear.get(k));
            datasetForVersions.setValue(versionPerYear.get(k),"",""+y.get(k));
           // k++;
        }
        JFreeChart myChartForVersions=ChartFactory.createBarChart("Versions Per Year","Year","Number of Version",datasetForVersions,PlotOrientation.VERTICAL,true,true,false);
        ChartFrame frameForVersions=new ChartFrame("Demo",myChartForVersions);
        frameForVersions.setVisible(true);
	
		alreadyEvaluated = 1;
	}
     
        
        
        public String getAbsPath(){
            return this.path;
        }
}
	
