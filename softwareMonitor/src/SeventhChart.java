import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
public class SeventhChart {     
    private int alreadyEvaluated;
    private String userEvaluation;
    private String path;
    
    public SeventhChart(String path){
        this.path=path;
        this.alreadyEvaluated=0;
    }
    
    public SeventhChart(SeventhChart other){
            this.path=other.path;
            this.userEvaluation=other.userEvaluation;
            this.alreadyEvaluated=other.alreadyEvaluated;                       
        }
    
    public int getAlreadyEvaluated(){
        return this.alreadyEvaluated;
    }
       
    
    public void computeMetrics(){
        
    }
    public void printChart(){
        Scanner readerOfSecondLaw=null;
        Scanner readerOfSixthLaw=null;
        Scanner inputReader;
        FileInputStream secondLaw;
        FileInputStream sixthLaw;
        String bufferForSecondLaw="";
        String bufferForSixthLaw="";
        String buffer;
        FileOutputStream seventhLaw=null;
        PrintWriter outputWriter;
        if(this.path==null){
            JOptionPane.showMessageDialog (null, "Please select a file", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
        try{
            readerOfSecondLaw=new Scanner(secondLaw=new FileInputStream(this.path+"\\SecondLaw.txt"));
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog (null, "Please evaluate the second law", "Message", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("You have not evaluated the second law, evaluate it please");
        }
        try{
            readerOfSixthLaw=new Scanner(sixthLaw=new FileInputStream(this.path+"\\SixthLaw.txt"));
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog (null, "Please evaluate the sixth law", "Message", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("You have not evaluated the sixth law,evaluate it please");
        }
        
        if(readerOfSecondLaw!=null){
            bufferForSecondLaw=readerOfSecondLaw.nextLine();
        }
        if(readerOfSixthLaw!=null){
            bufferForSixthLaw=readerOfSixthLaw.nextLine();
        }
        if(bufferForSecondLaw.equals("yes") && bufferForSixthLaw.equals("yes")){
            try{
                seventhLaw=new FileOutputStream(this.path+"\\SeventhLaw.txt");
            }
            catch(FileNotFoundException e){
                System.out.println("Could not create file");
            }
            outputWriter=new PrintWriter(seventhLaw);
            outputWriter.println("yes");
            System.out.println("Give additional comments");
            inputReader=new Scanner(System.in);
            buffer=inputReader.next();
            outputWriter.println(buffer);
            outputWriter.close();//edw
        }
        else{
           try{
                seventhLaw=new FileOutputStream(this.path+"\\SeventhLaw.txt");
            }
            catch(FileNotFoundException e){
                System.out.println("Could not create file");
            }
            outputWriter=new PrintWriter(seventhLaw);
            outputWriter.println("no");
            inputReader=new Scanner(System.in);
            buffer=inputReader.next();
            outputWriter.println(buffer);
            outputWriter.close();//edw
        }
       }
    }
    
    public String getAbsPath(){
        return this.path;
    }
}
