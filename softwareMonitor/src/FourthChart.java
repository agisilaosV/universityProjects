import java.io.*;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
public class FourthChart {
    private ArrayList<Double> operationsWorkRate;
    private ArrayList<Double> dataStructuresWorkRate;
    private int alreadyEvaluated;
    private String userEvaluation;
    private ArrayList<History> versions;
    private String path;
    
    public FourthChart(ArrayList<History> versionList,String path){
        this.path=path;
        operationsWorkRate=new ArrayList<Double>();
        dataStructuresWorkRate=new ArrayList<Double>();
        versions=new ArrayList<History>();
        this.alreadyEvaluated=0;
        for(int i=0;i<versionList.size();i++){
            versions.add(new History(versionList.get(i)));  //mhpws na vazame s emia grammh apo panw to new kai na to prosthetame meta
        }
    }
    
    public FourthChart(FourthChart other){
            this.path=other.path;
            this.userEvaluation=other.userEvaluation;
            this.alreadyEvaluated=other.alreadyEvaluated;
            for(int i=0;i<this.versions.size();i++){
                this.operationsWorkRate.add(other.operationsWorkRate.get(i));
                this.dataStructuresWorkRate.add(other.dataStructuresWorkRate.get(i));
                this.versions.add(other.versions.get(i));                
            }
            
        }
    
    public void computeMetrics(){
        //kai tou prwtou stoixeiou to work rate na mpei mhden
        //ara tha xreiastei mia extra methodos calculatedays
        operationsWorkRate.add(0.0);
        dataStructuresWorkRate.add(0.0);
        for(int i=1;i<versions.size();i++){     // /days prepei na prosthesw edw pera
            double days=this.computeDaysThatHavePassed(versions.get(i-1).getDate(),versions.get(i).getDate());// dia days edw
            double helperOperationsWorkRate=(versions.get(i).getOperationsAdded()+versions.get(i).getOperationsDeleted()+versions.get(i).getOperationsUpdated())/days;
            operationsWorkRate.add(helperOperationsWorkRate);
            double helperDataStructuresWorkRate=(versions.get(i).getDataStructuresAdded()+versions.get(i).getDataStructuresDeleted()+versions.get(i).getDataStructuresUpdated())/days;
            dataStructuresWorkRate.add(helperDataStructuresWorkRate);
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
        DefaultCategoryDataset datasetForOperationsWorkRate=new DefaultCategoryDataset();
        DefaultCategoryDataset datasetForDataStructuresWorkRate=new DefaultCategoryDataset();
        for(int i=0;i<versions.size();i++){
            datasetForOperationsWorkRate.setValue(operationsWorkRate.get(i),"",""+versions.get(i).getId());
            datasetForDataStructuresWorkRate.setValue(dataStructuresWorkRate.get(i),"",""+versions.get(i).getId());
        }
        JFreeChart chartForOperationsWorkRate=ChartFactory.createLineChart("Operations work rate","versionID","work rate (days)",datasetForOperationsWorkRate,PlotOrientation.VERTICAL,true,true,false);
        JFreeChart chartForDataStructuresWorkRate=ChartFactory.createLineChart("Data structures work rate","versionID","work rate (days)",datasetForDataStructuresWorkRate,PlotOrientation.VERTICAL,true,true,false);
        ChartFrame frameForOperationsWorkRate=new ChartFrame("Operations work rate chart",chartForOperationsWorkRate);
        ChartFrame frameForDataStructuresWorkRate=new ChartFrame("Data structures work rate chart",chartForDataStructuresWorkRate);
        frameForOperationsWorkRate.setVisible(true);
        frameForDataStructuresWorkRate.setVisible(true);
        this.alreadyEvaluated=1;    //ayto den eixe arxikh timh          
    }
    
    public String getAbsPath(){
        return this.path;
    }
    
    //na valw thn sunarthsh gia ton ypologismo twn hmerwn
    
    public long computeDaysThatHavePassed(int[] date1,int[] date2){
        int day1=date1[0];
        int month1=date1[1]-1;
        int year1=date1[2];
        int day2=date2[0];
        int month2=date2[1]-1;
        int year2= date2[2];
        Calendar calendar1=new GregorianCalendar(year1,month1,day1);
        Calendar calendar2=new GregorianCalendar(year2,month2,day2);
        long diff=calendar2.getTimeInMillis()-calendar1.getTimeInMillis();
        System.out.println("Test: "+calendar1.getTimeInMillis());
        System.out.println("Another Test: "+calendar2.getTimeInMillis());   //edw emeine mono na metatrepsw ta millisecond se meres
        long days=diff/(1000*60*60*24);
        System.out.println("The difference between the two dates is: "+days);
        return days;        
    }
}
