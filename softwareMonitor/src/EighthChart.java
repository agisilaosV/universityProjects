import java.io.*;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
public class EighthChart {
    private String userEvaluation;
    private int alreadyEvaluated;
    private ArrayList<History> versions;
    private String path;
    private ArrayList<Double> sNext;
    private ArrayList<Integer> sCurrent;
    private ArrayList<Double> Epsilon; 
    private ArrayList<Double> helpingArrayList;
    
    public EighthChart(ArrayList<History> versionList,String path){
        this.path=path;
        sNext=new ArrayList<Double>();
        sCurrent=new ArrayList<Integer>();
        Epsilon=new ArrayList<Double>();
        helpingArrayList=new ArrayList<Double>();
        versions=new ArrayList<History>();
        this.alreadyEvaluated=0;
        for(int i=0;i<versionList.size();i++){
            versions.add(new History(versionList.get(i)));           
        }        
    }
    
    public EighthChart(EighthChart other){
            this.path=other.path;
            this.userEvaluation=other.userEvaluation;
            this.alreadyEvaluated=other.alreadyEvaluated;
            for(int i=0;i<this.versions.size();i++){
                this.sNext.add(other.sNext.get(i));
                this.sCurrent.add(other.sCurrent.get(i));
                this.versions.add(other.versions.get(i));  
                this.Epsilon.add(other.Epsilon.get(i));
                this.helpingArrayList.add(other.helpingArrayList.get(i));
            }
            
        }
    
    public void computeMetrics(){
        double sum=0;
        
        for(int k=0;k<versions.size();k++){
            double helper=versions.get(k).getTotalNumberOfOperations()-versions.get(0).getTotalNumberOfOperations();
            double anotherHelper=1/(float)Math.pow(versions.get(0).getTotalNumberOfOperations(),2);
            for(int i=0;i<versions.get(k).getId()-1;i++){ 
                anotherHelper=anotherHelper+1/(float)(Math.pow(versions.get(k).getTotalNumberOfOperations(),2));
            }
            helpingArrayList.add(helper/anotherHelper); //htan *
        //    System.out.println("Helper: "+helper);
        //    System.out.println("Another helper: "+anotherHelper);
            System.out.println("helpingArrayList: "+helpingArrayList.get(k));
        }        
        for(int i=0;i<versions.size();i++){
            sum=0;
            for(int j=0;j<versions.get(i).getId()-1;j++){                
                sum=sum+helpingArrayList.get(j);
            //    System.out.println("The sum: "+sum);
           //     System.out.println("helpingarray: "+helpingArrayList.get(j));
            }
            sum=sum/versions.get(i).getId();
            Epsilon.add(sum);            
        }
        for(int i=0;i<versions.size();i++){
            sCurrent.add(versions.get(i).getTotalNumberOfOperations());
            sNext.add(sCurrent.get(i)*Epsilon.get(i));
            System.out.println("The total number of operations for version"+versions.get(i).getId()+"is :"+sCurrent.get(i));
       //     System.out.println("sNext"+sNext.get(i));
        }
        //allos tropos
        /*
        for(int i=0;i<versions.size();i++){
            double value;
            value=versions.get(i).getTotalNumberOfOperations()-versions.get(0).getTotalNumberOfOperations();
            double helper=0;
            for(int j=0;j<i;j++){
                helper=helper+1/Math.pow(versions.get(j).getTotalNumberOfOperations(),2);
            }
            value=value/helper;
            helpingArrayList.add(value);
        }        
        for(int i=0;i<versions.size();i++){            
            for(int j=0;j<i;j++){
                sum=sum+helpingArrayList.get(j);
            }
            sum=sum/versions.get(i).getId();
            Epsilon.add(sum);
            sum=0.0;
        }
        for(int i=0;i<versions.size();i++){
            sCurrent.add(versions.get(i).getTotalNumberOfOperations());
            sNext.add(sCurrent.get(i)*Epsilon.get(i));
            System.out.println("epsilon"+Epsilon.get(i));
            System.out.println("s current"+sCurrent.get(i));
            System.out.println("s next"+sNext.get(i));
        }*/
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
        DefaultCategoryDataset datasetForSCurrent=new DefaultCategoryDataset();
    //    DefaultCategoryDataset datasetForSNext=new DefaultCategoryDataset();
        for(int i=0;i<versions.size();i++){
            datasetForSCurrent.setValue(sCurrent.get(i),"sCurrent",""+versions.get(i).getId());
            datasetForSCurrent.setValue(sNext.get(i),"sNext",""+versions.get(i).getId());
        }
        JFreeChart myChart=ChartFactory.createLineChart("Number of operations","versionID","number of operations",datasetForSCurrent,PlotOrientation.VERTICAL,true,true,false);
        ChartFrame frameForMyChart=new ChartFrame("Estimation of number of operations chart",myChart);
        frameForMyChart.setVisible(true);
        this.alreadyEvaluated=1;
        /*
        System.out.println("Does the system comply to the sixth law? Answer: yes/no");
        this.userEvaluation=inputReader.next();
        System.out.println("Give any additional comments.");
        aditionalComments=inputReader.next();
        try{
            outputStream=new FileOutputStream(this.path+"\\EighthLaw.txt");
        }
        catch(FileNotFoundException e){
            System.out.println("Could not create file");
        }
        outputWriter=new PrintWriter(outputStream);
        outputWriter.println(this.userEvaluation);
        outputWriter.println(aditionalComments);
        outputWriter.close();*/
    }
    
    public String getAbsPath(){
        return this.path;
    }
}
