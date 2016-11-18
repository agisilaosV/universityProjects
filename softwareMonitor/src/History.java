import java.io.*;
import java.util.*;
public class History    //mporei na prepei na fygei to public
{
	private int id;
	private String date;
	private int operationsAdded;
	private int operationsDeleted;
	private int operationsUpdated;
	private int dataStructuresAdded;
	private int dataStructuresDeleted;
	private int dataStructuresUpdated;
	private int totalNumberOfOperations;
	private int totalNumberOfDataStructures;
        private String name;
	
	public History(String lineFromFile,String path,String name,int ops,int ds){        //to path edw mporei na mhn xreiastei
            //kai to name tha pernei ektos apo thn grammh kathe fora
            String[] resultOfSplit=lineFromFile.split(";");
           this.id=Integer.parseInt(resultOfSplit[0].trim());
           this.date=resultOfSplit[1].trim();
           this.operationsAdded=Integer.parseInt(resultOfSplit[2].trim());
           System.out.println(operationsAdded);
           this.operationsDeleted=Integer.parseInt(resultOfSplit[3].trim());
           this.operationsUpdated=Integer.parseInt(resultOfSplit[4].trim());
           this.dataStructuresAdded=Integer.parseInt(resultOfSplit[5].trim());
           this.dataStructuresDeleted=Integer.parseInt(resultOfSplit[6].trim());
           this.dataStructuresUpdated=Integer.parseInt(resultOfSplit[7].trim());
           this.name=name;
           this.totalNumberOfOperations=ops;    //edw ypothetw oti einai to arxiko plithos leitourgiwn
           this.totalNumberOfDataStructures=ds;
        }
	
	public History(History other)
	{
		this.id = other.id;
		this.date = other.date;
		this.operationsAdded = other.operationsAdded;
		this.operationsDeleted = other.operationsDeleted;
		this.operationsUpdated = other.operationsUpdated;
		this.dataStructuresAdded = other.dataStructuresAdded;
		this.dataStructuresUpdated = other.dataStructuresUpdated;
		this.dataStructuresDeleted = other.dataStructuresDeleted;
		this.totalNumberOfOperations = other.totalNumberOfOperations;
		this.totalNumberOfDataStructures = other.totalNumberOfDataStructures;
                this.name=other.name;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public int[] getDate()  //edw mporoume na epistrefoume apeutheias to string xwris split
	{
		int[] returnedDate = {0,0,0};
		String[] buffer = this.date.split("/");
		returnedDate[0] = Integer.parseInt(buffer[0].trim());
		returnedDate[1] = Integer.parseInt(buffer[1].trim());
		returnedDate[2] = Integer.parseInt(buffer[2].trim());
		return returnedDate;
	}
	
	public int getOperationsAdded()
	{
		return this.operationsAdded;
	}
	
	public int getOperationsDeleted()
	{
		return this.operationsDeleted;
	}
	
	public int getOperationsUpdated()
	{
		return this.operationsUpdated;
	}
	
	public int getDataStructuresAdded()
	{
		return this.dataStructuresAdded;
	}
	
	public int getDataStructuresDeleted()
	{
		return this.dataStructuresDeleted;
	}
	
	public int getDataStructuresUpdated()
	{
		return this.dataStructuresUpdated;
	}
	
	public int getTotalNumberOfOperations()
	{
		return this.totalNumberOfOperations;
	}
	
	public int getTotalNumberOfDataStructures()
	{
		return this.totalNumberOfDataStructures;
	}
        
        public String getName(){
            return this.name;
        }
}
		
		
		
		
		
