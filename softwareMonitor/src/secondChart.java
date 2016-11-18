import java.io.*;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import com.jgoodies.forms.layout.FormLayout;//new imports
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpec;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
public class secondChart {//edw mporei na prepei na fygei to public
    //na valoume kai to onoma tou logismikou pou epivlepoume
    private ArrayList<Double> operationsComplexity;
    private ArrayList<Double> maintenanceActions;
    private ArrayList<Double> dataStructuresComplexity;
    private ArrayList<Double> dataStructuresMaintenance;
    private int alreadyEvaluated;  //kapou na to arxikopoioume
    private String userEvaluation;
    private ArrayList<History> versions;
    private String path;
    
    public secondChart(ArrayList<History> versionList,String path){
        this.path=path;
        operationsComplexity=new ArrayList<Double>();
        maintenanceActions=new ArrayList<Double>();
        dataStructuresComplexity=new ArrayList<Double>();
        dataStructuresMaintenance=new ArrayList<Double>();
        versions=new ArrayList<History>();
        this.alreadyEvaluated=0;
        for(int i=0;i<versionList.size();i++){
            versions.add(new History(versionList.get(i)));  //assign return value to new variable            
        }
    }
    
    
    public secondChart(secondChart other){
            this.path=other.path;
            this.userEvaluation=other.userEvaluation;
            this.alreadyEvaluated=other.alreadyEvaluated;
            for(int i=0;i<this.versions.size();i++){
                this.operationsComplexity.add(other.operationsComplexity.get(i));
                this.maintenanceActions.add(other.maintenanceActions.get(i));
                this.versions.add(other.versions.get(i));
                this.dataStructuresComplexity.add(other.dataStructuresComplexity.get(i));
                this.dataStructuresMaintenance.add(other.dataStructuresMaintenance.get(i));
            }
            
        }
    
    public void computeMetrics(){        
        for(int i=0;i<versions.size();i++){
            double helperComplexityForOperations;
            double helperComplexityForDataStructures;
            if(versions.get(i).getOperationsAdded()==0){
                 helperComplexityForOperations=0;
            }
            else{
                 helperComplexityForOperations=(versions.get(i).getOperationsDeleted()+versions.get(i).getOperationsUpdated())/versions.get(i).getOperationsAdded();
            }
            operationsComplexity.add(helperComplexityForOperations);
            double helperMaintenance=versions.get(i).getOperationsDeleted()+versions.get(i).getOperationsUpdated();
            maintenanceActions.add(helperMaintenance);
            if(versions.get(i).getDataStructuresAdded()==0){
               helperComplexityForDataStructures=0;  
            }
            else{
               helperComplexityForDataStructures=(versions.get(i).getDataStructuresDeleted()+versions.get(i).getDataStructuresUpdated())/versions.get(i).getDataStructuresAdded(); 
            }
            dataStructuresComplexity.add(helperComplexityForDataStructures);
            double helperMaintenanceForDataStructures=versions.get(i).getDataStructuresDeleted()+versions.get(i).getDataStructuresUpdated();
            dataStructuresMaintenance.add(helperMaintenanceForDataStructures);            
        }
    }
    
    public int getAlreadyEvaluated(){
        return this.alreadyEvaluated;
    }
    
    public void printChart(){
        Scanner inputReader=new Scanner(System.in);
        String aditionalComments;   //pinaka apo string kalytera
        String buffer;
        FileOutputStream outputStream=null;
        PrintWriter outputWriter;
        DefaultCategoryDataset datasetForOperationsComplexity=new DefaultCategoryDataset();
        DefaultCategoryDataset datasetForDataStructuresComplexity=new DefaultCategoryDataset();
        DefaultCategoryDataset datasetForMaintenanceActions=new DefaultCategoryDataset();
        for(int i=0;i<versions.size();i++){
            datasetForOperationsComplexity.setValue(operationsComplexity.get(i),"",""+versions.get(i).getId());
            datasetForDataStructuresComplexity.setValue(dataStructuresComplexity.get(i),"",""+versions.get(i).getId());
            datasetForMaintenanceActions.setValue(maintenanceActions.get(i),"",""+versions.get(i).getId());
        }
        JFreeChart chartForOperationsComplexity=ChartFactory.createLineChart("Operations Complexity","versionID","Complexity",datasetForOperationsComplexity,PlotOrientation.VERTICAL,true,true,false);
        JFreeChart chartForDataStructuresComplexity=ChartFactory.createLineChart("Data Structures Complexity","versionID","Complexity",datasetForDataStructuresComplexity,PlotOrientation.VERTICAL,true,true,false);
        JFreeChart chartForMaintenanceActions=ChartFactory.createBarChart("Maintenance Actions","versionID","number of actions",datasetForDataStructuresComplexity,PlotOrientation.VERTICAL,true,true,false);
        ChartFrame frameForOperationsComplexity=new ChartFrame("Operations complexity chart",chartForOperationsComplexity);
        ChartFrame frameForDataStructuresComplexity=new ChartFrame("Data structures complexity chart",chartForDataStructuresComplexity);
        ChartFrame frameForMaintenanceActions=new ChartFrame("Maintenance actions chart",chartForMaintenanceActions);
        frameForOperationsComplexity.setVisible(true);
        frameForDataStructuresComplexity.setVisible(true);
        frameForMaintenanceActions.setVisible(true);
        this.alreadyEvaluated=1;
    }
    
    
    public String getAbsPath(){
        return this.path;
    }
}
