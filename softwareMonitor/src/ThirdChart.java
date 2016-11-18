import java.io.*;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
public class ThirdChart {
    private ArrayList<Double> operationsGrowthRate;
    private ArrayList<Double> dataStructuresGrowthRate;
    private int alreadyEvaluated;
    private String userEvaluation;
    private ArrayList<History> versions;
    private String path;
    
    public ThirdChart(ArrayList<History> versionList,String path){
        this.path=path;
        operationsGrowthRate=new ArrayList<Double>();
        dataStructuresGrowthRate=new ArrayList<Double>();
        versions=new ArrayList<History>();
        this.alreadyEvaluated=0;
        for(int i=0;i<versionList.size();i++){
            versions.add(new History(versionList.get(i)));  //mhpws na vazame s emia grammh apo panw to new kai na to prosthetame meta
        }
    }
    
    
    public ThirdChart(ThirdChart other){
            this.path=other.path;
            this.userEvaluation=other.userEvaluation;
            this.alreadyEvaluated=other.alreadyEvaluated;
            for(int i=0;i<this.versions.size();i++){
                this.operationsGrowthRate.add(other.operationsGrowthRate.get(i));
                this.dataStructuresGrowthRate.add(other.dataStructuresGrowthRate.get(i));
                this.versions.add(other.versions.get(i));                
            }
            
        }
    
    
    public void computeMetrics(){
        for(int i=0;i<versions.size();i++){
            double helperOperationsGrowthRate=(versions.get(i).getOperationsAdded()-versions.get(i).getOperationsDeleted());
            operationsGrowthRate.add(helperOperationsGrowthRate);
            double helperDataStructuresGrowthRate=(versions.get(i).getDataStructuresAdded()-versions.get(i).getDataStructuresDeleted());
            dataStructuresGrowthRate.add(helperDataStructuresGrowthRate);
        }
    }
    
    public int getAlreadyEvaluated(){
        return this.alreadyEvaluated;
    }
    
    public void printChart(){
        Scanner inputReader=new Scanner(System.in);
        String aditionalComments;
        String buffer;
        FileOutputStream outputStream=null;
        PrintWriter outputWriter;
        DefaultCategoryDataset datasetForOperationsGrowthRate=new DefaultCategoryDataset();
        DefaultCategoryDataset datasetForDataStructuresGrowthRate=new DefaultCategoryDataset();
        for(int i=0;i<versions.size();i++){
            datasetForOperationsGrowthRate.setValue(operationsGrowthRate.get(i),"",""+versions.get(i).getId());
            datasetForDataStructuresGrowthRate.setValue(dataStructuresGrowthRate.get(i),"",""+versions.get(i).getId());
        }
        JFreeChart chartForOperationsGrowthRate=ChartFactory.createLineChart("Operations growth rate","versionID","growth",datasetForOperationsGrowthRate,PlotOrientation.VERTICAL,true,true,false);
        JFreeChart chartForDataStructuresGrowthRate=ChartFactory.createLineChart("Data structures growth rate","versionID","growth",datasetForDataStructuresGrowthRate,PlotOrientation.VERTICAL,true,true,false);
        ChartFrame frameForOperationsGrowthRate=new ChartFrame("Operations growth rate chart",chartForOperationsGrowthRate);
        ChartFrame frameForDataStructuresGrowthRate=new ChartFrame("Data structures growth rate chart",chartForDataStructuresGrowthRate);
        frameForOperationsGrowthRate.setVisible(true);
        frameForDataStructuresGrowthRate.setVisible(true);
        this.alreadyEvaluated=1;    //ayto den eixe arxikh timh     
    }
    
    public String getAbsPath(){
        return this.path;
    }
    
}
