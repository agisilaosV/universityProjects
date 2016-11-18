import java.io.*;
import java.util.*;
public class FileParsing {
    private String absolutePath;    //se poion katalogo apothikeuontai ta arxeia
    private FileInputStream file;
    private Scanner inputReader;
    private String name;    //name of system we are currently examining
    private String dirName;
    
    public FileParsing(String dirName){
        this.dirName=dirName;//na to koitaksoume meta ws pros to an tha kanoume file browser
        this.absolutePath="C:\\";
        try{
            this.inputReader=new Scanner(this.file=new FileInputStream(this.dirName));
            System.out.println("File opened");  
        }
        catch(FileNotFoundException e){
            System.out.println("Could not open the file, please try again!");//prepei na typwnetai mhnyma lathous
        }
    }
    
    public String getAbsPath(){   //always call it after createDir     
        return this.absolutePath;
    }
    
    public void initSystemName(){   //diavazoume thn prwth grammh tou arxeiou gia na paroume to onoma tou logismikou
        String buffer=null;
        buffer=this.inputReader.nextLine();
        String fields[]=buffer.split(";");
        this.name=fields[1].trim();
        this.absolutePath=this.absolutePath+this.name;
    }
    
    public void createDir(){        //dhmiourgia fakelou      
        File theDir=new File(absolutePath);
        if(!theDir.exists()){
            try{
                
                theDir.mkdir();
            }
            catch(SecurityException se){
                System.out.println("Directory not created!");
            }
        }
        else{
            System.out.println("evaluation");
        }
    }
    public String getName(){
        return this.name;
    }
    
}
