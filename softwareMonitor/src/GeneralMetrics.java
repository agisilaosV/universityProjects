import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
public class GeneralMetrics {
    private String path;
    private String pathForOpeningTheFile;
    
    
    public GeneralMetrics(String path,String p){
       this.path=path;     
       this.pathForOpeningTheFile=p;
    }
    
    public void computeMetrics(){   //edw na valw na ta petaei se arxeio kai double ola me kefalaia
        int operationsChanges;//no need
        int dataStructuresChanges; //no need
        int numberOfOperations=0;
        ArrayList<Double> operationsGrowthRate=new ArrayList<Double>();
        ArrayList<Double> complexityForOperations=new ArrayList<Double>();
        ArrayList<Double> workRateForOperations=new ArrayList<Double>();
        int numberOfDataStructures=0;
        ArrayList<Double> dataStructuresGrowthRate=new ArrayList<Double>();
        ArrayList<Double> complexityForDataStructures=new ArrayList<Double>();
        ArrayList<Double> workRateForDataStructures=new ArrayList<Double>();
        //eksi metavlhtes gia to parsing mas
        ArrayList<Integer> operationsAdded=new ArrayList<Integer>();
        ArrayList<Integer> operationsDeleted=new ArrayList<Integer>();
        ArrayList<Integer> operationsUpdated=new ArrayList<Integer>();
        ArrayList<Integer> dataStructuresAdded=new ArrayList<Integer>();
        ArrayList<Integer> dataStructuresDeleted=new ArrayList<Integer>();
        ArrayList<Integer> dataStructuresUpdated=new ArrayList<Integer>();
        ArrayList<String> date=new ArrayList<String>();
        FileInputStream myFile;
        Scanner inputReader=null;
        String buffer1;
        int counter=0;
        PrintWriter outputWriter=null;
        try{
            outputWriter=new PrintWriter(new FileOutputStream(this.path+"\\Metrics.txt"));//h mhpws oxi
        }
        catch(FileNotFoundException e){
            System.out.println("error");
        }
        
        try{
            inputReader=new Scanner(myFile=new FileInputStream(pathForOpeningTheFile));
        }
        catch(FileNotFoundException e){
            System.out.println("Could not open file");
        }
        while(inputReader.hasNextLine()){   //prospername tnh prwth grammh
            String buffer2=inputReader.nextLine(); 
            break;
        }
        while(inputReader.hasNextLine()){   //sth deyterh kratame operations         
            buffer1=inputReader.nextLine();
            String[] resultOfSplit=buffer1.split(";");
            numberOfOperations=Integer.parseInt(resultOfSplit[1].trim()); 
            break;
        }
        while(inputReader.hasNextLine()){   //sthn trith kratame dataStructures
            String buffer2=inputReader.nextLine();
            String[] resultOfSplit=buffer2.split(";");
            numberOfDataStructures=Integer.parseInt(resultOfSplit[1].trim());
            break;
        }
        while(inputReader.hasNextLine()){   //prospername 2 grammes akoma
                String buffer2=inputReader.nextLine();                
                buffer2=inputReader.nextLine();                            
            break;            
        }
        while(inputReader.hasNextLine()){   //teliko parse tou arxeiou
           String buffer2=inputReader.nextLine();
           String[] resultOfSplit=buffer2.split(";"); 
           date.add(resultOfSplit[1].trim());
           operationsAdded.add(Integer.parseInt(resultOfSplit[2].trim()));
           operationsDeleted.add(Integer.parseInt(resultOfSplit[3].trim()));
           operationsUpdated.add(Integer.parseInt(resultOfSplit[4].trim()));
           dataStructuresAdded.add(Integer.parseInt(resultOfSplit[5].trim()));
           dataStructuresDeleted.add(Integer.parseInt(resultOfSplit[6].trim()));
           dataStructuresUpdated.add(Integer.parseInt(resultOfSplit[7].trim()));           
        }        
        
        for(int i=0;i<operationsAdded.size();i++){      //ypologismos twn metrikwn
            if(i==0){
                operationsGrowthRate.add(0.0);
                workRateForOperations.add(0.0);
                complexityForOperations.add(0.0);
                dataStructuresGrowthRate.add(0.0);
                workRateForDataStructures.add(0.0);
                complexityForDataStructures.add(0.0);
                outputWriter.println(numberOfOperations+";"+operationsGrowthRate.get(i)+";"+complexityForOperations.get(i)+";"+workRateForOperations.get(i)+";"+numberOfDataStructures+";"+dataStructuresGrowthRate.get(i)+";"+complexityForDataStructures.get(i)+";"+workRateForDataStructures.get(i));                
            }
            else{
                int previousNumberOfOperations=numberOfOperations;
                int previousNumberOfDataStructures=numberOfDataStructures;
                double days=this.computeDaysThatHavePassed(date.get(i-1),date.get(i) );
                numberOfOperations=numberOfOperations+operationsAdded.get(i)-operationsDeleted.get(i);
                numberOfDataStructures=numberOfDataStructures+dataStructuresAdded.get(i)-dataStructuresDeleted.get(i);
                operationsGrowthRate.add(((numberOfOperations-previousNumberOfOperations)/days));//edw dia days
                dataStructuresGrowthRate.add(((numberOfDataStructures-previousNumberOfDataStructures)/days));
                workRateForOperations.add(operationsAdded.get(i)+operationsDeleted.get(i)+operationsUpdated.get(i)/days);
                workRateForDataStructures.add(dataStructuresAdded.get(i)+dataStructuresDeleted.get(i)+dataStructuresUpdated.get(i)/days);                
                if(operationsAdded.get(i)!=0){  //yparxei periptwsh diaireshs me 0
                    complexityForOperations.add((operationsUpdated.get(i)+operationsDeleted.get(i))/operationsAdded.get(i)*1.0);
                }
                else{
                    complexityForOperations.add(0.0);
                }
                if(dataStructuresAdded.get(i)!=0){
                    complexityForDataStructures.add((dataStructuresUpdated.get(i)+dataStructuresDeleted.get(i))/dataStructuresAdded.get(i)*1.0);
                }
                else{
                complexityForDataStructures.add(0.0);
                }
                System.out.println("I am here bitch");
                outputWriter.println(numberOfOperations+";"+operationsGrowthRate.get(i)+";"+complexityForOperations.get(i)+";"+workRateForOperations.get(i)+";"+numberOfDataStructures+";"+dataStructuresGrowthRate.get(i)+";"+complexityForDataStructures.get(i)+";"+workRateForDataStructures.get(i));
                
                }           
        }
        outputWriter.close();
        computeDaysThatHavePassed(date.get(0),date.get(1));
        System.out.println("The first date is: "+date.get(0));
        System.out.println("The second date is: "+date.get(1));
        System.out.println("The number of operations is: "+numberOfOperations);
        System.out.println("The number of data structures is: "+numberOfDataStructures);
        System.out.println("The growth rate is : "+operationsGrowthRate.get(1));
        System.out.println("The complexity is: "+complexityForOperations.get(1));
    }
    //na diairw me to days panw
    public long computeDaysThatHavePassed(String date1,String date2){       //edw na valw na epistrefei long
        String fields[]=date1.split("/");    //prepei mhnas -1 gt ksekeiname apo to mhden
        String fields2[]=date2.split("/");
        int day1=Integer.parseInt(fields[0].trim());
        int month1=Integer.parseInt(fields[1].trim())-1;
        int year1=Integer.parseInt(fields[2].trim()) ;
        int day2=Integer.parseInt(fields2[0].trim());
        int month2=Integer.parseInt(fields2[1].trim())-1;
        int year2=Integer.parseInt(fields2[2].trim()) ;
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
