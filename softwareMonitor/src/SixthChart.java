import java.io.*;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
public class SixthChart {
    private ArrayList<Integer> numberOfOperations;
    private ArrayList<Integer> numberOfDataStructures;
    private String userEvaluation;
    private int alreadyEvaluated;
    private ArrayList<History> versions;
    private String path;
    
    public SixthChart(ArrayList<History> versionList,String path){
        this.path=path;
        numberOfOperations=new ArrayList<Integer>();
        numberOfDataStructures=new ArrayList<Integer>();
        versions=new ArrayList<History>();
        this.alreadyEvaluated=0;
        for(int i=0;i<versionList.size();i++){
            versions.add(new History(versionList.get(i)));
        }
    }
    
    public SixthChart(SixthChart other){
            this.path=other.path;
            this.userEvaluation=other.userEvaluation;
            this.alreadyEvaluated=other.alreadyEvaluated;
            for(int i=0;i<this.versions.size();i++){
                this.numberOfOperations.add(other.numberOfOperations.get(i));
                this.numberOfDataStructures.add(other.numberOfDataStructures.get(i));
                this.versions.add(other.versions.get(i));                
            }
            
        }

    
    public void computeMetrics(){
        int helperNumberOfOperations=versions.get(0).getTotalNumberOfOperations();
        int helperNumberOfDataStructures=versions.get(0).getTotalNumberOfDataStructures();
        numberOfOperations.add(helperNumberOfOperations);
        numberOfDataStructures.add(helperNumberOfDataStructures);
        for(int i=1;i<versions.size();i++){
            helperNumberOfOperations=helperNumberOfOperations+versions.get(i).getOperationsAdded()-versions.get(i).getOperationsDeleted();
            numberOfOperations.add(helperNumberOfOperations);
            helperNumberOfDataStructures=helperNumberOfDataStructures+versions.get(i).getDataStructuresAdded()-versions.get(i).getDataStructuresDeleted();
            numberOfDataStructures.add(helperNumberOfDataStructures);
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
        DefaultCategoryDataset datasetForNumberOfOperations=new DefaultCategoryDataset();
        DefaultCategoryDataset datasetForNumberOfDataStructures=new DefaultCategoryDataset();
        for(int i=0;i<versions.size();i++){
            datasetForNumberOfOperations.setValue(numberOfOperations.get(i),"",""+versions.get(i).getId());
            datasetForNumberOfDataStructures.setValue(numberOfDataStructures.get(i),"",""+versions.get(i).getId());
        }
        JFreeChart chartForNumberOfOperations=ChartFactory.createLineChart("Number of operations","versionID","number of operations",datasetForNumberOfOperations,PlotOrientation.VERTICAL,true,true,false);
        JFreeChart chartForNumberOfDataStructures=ChartFactory.createLineChart("Number of data structures","versionID","number of data structures",datasetForNumberOfDataStructures,PlotOrientation.VERTICAL,true,true,false);
        ChartFrame frameForNumberOfOperations=new ChartFrame("Number of operations chart",chartForNumberOfOperations);
        ChartFrame frameForNumberOfDataStructures=new ChartFrame("Number of data structures chart",chartForNumberOfDataStructures);
        frameForNumberOfOperations.setVisible(true);
        frameForNumberOfDataStructures.setVisible(true);
        this.alreadyEvaluated=1;    //ayto den eixe arxikh timh           
    }
    
    public String getAbsPath(){
        return this.path;
    }
}
