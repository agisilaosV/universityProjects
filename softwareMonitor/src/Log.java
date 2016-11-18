import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
public class Log {
    private String path;
    private String anotherPath;
    private String pathForOpeningTheFile;//plhres path arxikou arxeiou pou dinei o xrhsths
    private GeneralMetrics myMetrics;
    
    public Log(String path1,String path2,String path3){
        this.path=path1;
        this.anotherPath=path2;
        this.pathForOpeningTheFile=path3;
        this.myMetrics=new GeneralMetrics(this.path,this.pathForOpeningTheFile);
    }
    
    public Log(Log other){
        this.path=other.path;
        this.anotherPath=other.anotherPath;
        this.pathForOpeningTheFile=other.pathForOpeningTheFile;
        this.myMetrics=new GeneralMetrics(other.path,other.pathForOpeningTheFile);  
    }
    
    public void createReport(){
        FileOutputStream outputStream=null;
                        PrintWriter outputWriter;
                        FileInputStream inputStream;
                        Scanner inputReader1=null;
                        Scanner inputReader2=null;
                        Scanner inputReader3=null;
                        Scanner inputReader4=null;
                        Scanner inputReader5=null;
                        Scanner inputReader6=null;
                        Scanner inputReader7=null;
                        Scanner inputReader8=null;
                        boolean allHaveNotBeenEvaluated=false;
                        try{
                            inputReader1=new Scanner(new FileInputStream(path+"\\FirstLaw.txt"));
                        }
                        catch(FileNotFoundException a){
                            JOptionPane.showMessageDialog (null, "Could not open FirstLaw.txt", "Message", JOptionPane.INFORMATION_MESSAGE);
                            allHaveNotBeenEvaluated=true;
                        }
                        try{
                            inputReader2=new Scanner(new FileInputStream(path+"\\SecondLaw.txt"));
                        }
                        catch(FileNotFoundException a){
                            JOptionPane.showMessageDialog (null, "Could not open SecondLaw.txt", "Message", JOptionPane.INFORMATION_MESSAGE);
                            allHaveNotBeenEvaluated=true;
                        }
                        try{
                            inputReader3=new Scanner(new FileInputStream(path+"\\ThirdLaw.txt"));
                        }
                        catch(FileNotFoundException a){
                            JOptionPane.showMessageDialog (null, "Could not open ThirdLaw.txt", "Message", JOptionPane.INFORMATION_MESSAGE);
                            allHaveNotBeenEvaluated=true;
                        }
                        try{
                            inputReader4=new Scanner(new FileInputStream(path+"\\FourthLaw.txt"));
                        }
                        catch(FileNotFoundException a){
                            JOptionPane.showMessageDialog (null, "Could not open FourthLaw.txt", "Message", JOptionPane.INFORMATION_MESSAGE);
                            allHaveNotBeenEvaluated=true;
                        }
                        try{
                            inputReader5=new Scanner(new FileInputStream(path+"\\FifthLaw.txt"));
                        }
                        catch(FileNotFoundException a){
                            JOptionPane.showMessageDialog (null, "Could not open FifthLaw.txt", "Message", JOptionPane.INFORMATION_MESSAGE);
                            allHaveNotBeenEvaluated=true;
                        }
                        try{
                            inputReader6=new Scanner(new FileInputStream(path+"\\SixthLaw.txt"));
                        }
                        catch(FileNotFoundException a){
                            JOptionPane.showMessageDialog (null, "Could not open SixthLaw.txt", "Message", JOptionPane.INFORMATION_MESSAGE);
                            allHaveNotBeenEvaluated=true;
                        }
                        try{
                            inputReader7=new Scanner(new FileInputStream(path+"\\SeventhLaw.txt"));
                        }
                        catch(FileNotFoundException a){
                            JOptionPane.showMessageDialog (null, "Could not open SeventhLaw.txt", "Message", JOptionPane.INFORMATION_MESSAGE);
                            allHaveNotBeenEvaluated=true;
                        }
                        try{
                            inputReader8=new Scanner(new FileInputStream(path+"\\EighthLaw.txt"));
                        }
                        catch(FileNotFoundException a){
                            JOptionPane.showMessageDialog (null, "Could not open EighthLaw.txt", "Message", JOptionPane.INFORMATION_MESSAGE);
                            allHaveNotBeenEvaluated=true;
                        }
                        if(allHaveNotBeenEvaluated==true){
                            JOptionPane.showMessageDialog (null, "Could not create report,some laws are missing", "Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{//new code
                            try{
                                outputStream=new FileOutputStream(anotherPath+"\\Report.txt");
                            }
                            catch(FileNotFoundException q){
                                System.out.println("Could not create file");
                            }
                            outputWriter=new PrintWriter(outputStream);
                            while(inputReader1.hasNextLine()){
                                outputWriter.println(inputReader1.nextLine());
                            }
                            while(inputReader2.hasNextLine()){
                                outputWriter.println(inputReader2.nextLine());
                            }
                            while(inputReader3.hasNextLine()){
                                outputWriter.println(inputReader3.nextLine());
                            }
                            while(inputReader4.hasNextLine()){
                                outputWriter.println(inputReader4.nextLine());
                            }
                            while(inputReader5.hasNextLine()){
                                outputWriter.println(inputReader5.nextLine());
                            }
                            while(inputReader6.hasNextLine()){
                                outputWriter.println(inputReader6.nextLine());
                            }
                            while(inputReader7.hasNextLine()){
                                outputWriter.println(inputReader7.nextLine());
                            }
                            while(inputReader8.hasNextLine()){
                                outputWriter.println(inputReader8.nextLine());
                            }
                            outputWriter.close();
        
        }
    }
    
    public void createFinalLog(){
         Scanner inputReader1=null;
         Scanner inputReader2=null;
         String buffer;
         String buffer1;
         PrintWriter outputWriter=null;
         myMetrics.computeMetrics();
         try{
                inputReader1=new Scanner(new FileInputStream(pathForOpeningTheFile));
            }
         catch(FileNotFoundException a){
                JOptionPane.showMessageDialog (null, "Could not open file", "Message", JOptionPane.INFORMATION_MESSAGE);
               }
        try{
                inputReader2=new Scanner(new FileInputStream(path+"\\Metrics.txt"));
            }
        catch(FileNotFoundException a){
                JOptionPane.showMessageDialog (null, "Could not open file", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        try{
            outputWriter=new PrintWriter(new FileOutputStream(path+"\\finalLog.txt"));
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog (null, "Could not create final log,some laws are missing", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
        outputWriter.println(inputReader1.nextLine());
        outputWriter.println(inputReader1.nextLine());
        outputWriter.println(inputReader1.nextLine());
        outputWriter.println(inputReader1.nextLine());
        outputWriter.println(inputReader1.nextLine()+";kati edw");
        while(inputReader1.hasNextLine()&& inputReader2.hasNextLine()){//ayto mesa sto 2o try isws
            outputWriter.println(inputReader1.nextLine()+inputReader2.nextLine());            
        }
        outputWriter.close();
    }
    
}
