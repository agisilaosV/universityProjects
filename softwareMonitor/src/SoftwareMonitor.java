import java.awt.EventQueue;
import java.awt.Desktop;
import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import org.jfree.*;
import java.awt.BorderLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.FlowLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpec;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
public class SoftwareMonitor {

	private JFrame frmSoftwareEvolutionMonitor;
        private FirstChart myFirstChart;
        private secondChart mySecondChart;
        private ThirdChart myThirdChart;
        private FourthChart myFourthChart;
        private FifthChart myFifthChart;
        private SixthChart mySixthChart;
        private SeventhChart mySeventhChart;
        private EighthChart myEighthChart;
        private String pathForOpeningTheFile;
        private FileParsing myFileParsing;
        private ArrayList<History> versionList;
        private String pathForAll;
        private String pathForSaving; //gia to report
    //    private ArrayList<software> mySoftware;
        private int counter=-1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//edw na einai oles oi klaseis mas
            
            EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				SoftwareMonitor window = new SoftwareMonitor();
				window.frmSoftwareEvolutionMonitor.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
    }

	/**
	 * Create the application.
	 */
	public SoftwareMonitor() {
        //        mySoftware=new ArrayList<software>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSoftwareEvolutionMonitor = new JFrame();
		frmSoftwareEvolutionMonitor.setTitle("Software Evolution Monitor");
		frmSoftwareEvolutionMonitor.setBounds(100, 100, 500, 368);
		frmSoftwareEvolutionMonitor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmSoftwareEvolutionMonitor.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New...");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                            JFileChooser fileChoose=new JFileChooser();
			    FileNameExtensionFilter filter=new FileNameExtensionFilter(".txt","txt");
			    fileChoose.setFileFilter(filter);
			    int result=fileChoose.showOpenDialog(new JDialog());
			    File file=new File("");
			    if(result==JFileChooser.APPROVE_OPTION){
			         file=fileChoose.getSelectedFile();
			         JOptionPane.showMessageDialog(null, "You have chosen a file");
			     }
			     else if(result==JFileChooser.CANCEL_OPTION){
			         JOptionPane.showMessageDialog(null, "Wrong file");
			     }
                            pathForOpeningTheFile=fileChoose.getSelectedFile().getAbsolutePath();
                            System.out.println(pathForOpeningTheFile);
                            //Arxizei to parsing tou arxikou arxeiou mas
                            myFileParsing=new FileParsing(pathForOpeningTheFile);
                            myFileParsing.initSystemName();
                            myFileParsing.createDir();
                            pathForAll=myFileParsing.getAbsPath();
                            pathForSaving=pathForAll;//miso
                            versionList=new ArrayList<History>();                            
                            Scanner inputReader=null;
                            try{
                                inputReader=new Scanner(new FileInputStream(pathForOpeningTheFile));
                            }
                            catch(FileNotFoundException e){
                                System.out.println("File not found");
                            }
                            //Twra tha perasoume tis 4 prwtes grammes kratwnta oti xreiastoume
                            String buffer1=inputReader.nextLine();
                            String[] resultOfSplit=buffer1.split(";");
                            String buffer2=inputReader.nextLine();
                            String[] resultOfSplit1=buffer2.split(";");
                            int initialOperations=Integer.parseInt(resultOfSplit1[1].trim());
                            String buffer3=inputReader.nextLine();
                            String[] resultOfSplit2=buffer3.split(";");
                            int initialDataStructures=Integer.parseInt(resultOfSplit2[1].trim());
                            inputReader.nextLine();
                            String softwareName=resultOfSplit[1].trim();
                            System.out.println(softwareName);
                            inputReader.nextLine();
                            System.out.println("The initial operations are: "+initialOperations);
                            System.out.println("The initial data structures are: "+initialDataStructures);
                            while(inputReader.hasNextLine()){
                                String buffer=inputReader.nextLine();
                                System.out.println(buffer.trim());
                                History myHistory=new History(buffer.trim(),"dsf",softwareName,initialOperations,initialDataStructures);
                                versionList.add(myHistory);
                                break;//new code
                            }
                            int i=0;
                            while(inputReader.hasNextLine()){
                                i++;
                                String buffer=inputReader.nextLine();
                                String helpingBuffer=buffer;
                                System.out.println(buffer.trim());
                                String[] myResultOfSplit=helpingBuffer.split(";");
                                int opAdded=Integer.parseInt(myResultOfSplit[2].trim());
                                int opRemoved=Integer.parseInt(myResultOfSplit[3].trim());
                                int dsAdded=Integer.parseInt(myResultOfSplit[5].trim());                                
                                int dsRemoved=Integer.parseInt(myResultOfSplit[6].trim());
                                initialOperations=initialOperations+opAdded-opRemoved;
                            //    System.out.println("The initial operations are: "+initialOperations);
                                initialDataStructures=initialDataStructures+dsAdded-dsRemoved;
                                History myHistory=new History(buffer.trim(),"dsf",softwareName,initialOperations,initialDataStructures);
                            //    System.out.println(myHistory.getDataStructuresAdded());
                            //    System.out.println(myHistory.getDataStructuresDeleted());
                                versionList.add(myHistory);
                                
                            }
			}
		});
		mnFile.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Exit");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Exiting now");
				System.exit(1);
			}
		});
		mnFile.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu = new JMenu("Laws evaluation");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmFirstLaw = new JMenuItem("First Law");
                mntmFirstLaw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                String buffer=null;
                                String buffer1=null;
                                System.out.println("First Law evaluation requested");
                                try{
                                myFirstChart=new FirstChart(versionList,pathForAll);
                                myFirstChart.computeMetrics();
                                myFirstChart.printChart();                                                           
                                pathForSaving=myFirstChart.getAbsPath();
                                try {
                                    Thread.sleep(20000);    //perimenei 20deytera mexri o xrhsths na anoiksei ta parathyra
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(SoftwareMonitor.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                JOptionPane.showMessageDialog (null, "The system complies only if in every version there are changes in operations or datastructures or there is a version every year", "Message", JOptionPane.INFORMATION_MESSAGE);
                                buffer=JOptionPane.showInputDialog("Does the system comply to the first law?");
                                buffer1=JOptionPane.showInputDialog("Any additional comments?");
                                FileOutputStream outputStream=null;
                                PrintWriter outputWriter;
                                try{
                                    outputStream=new FileOutputStream(myFirstChart.getAbsPath()+"\\FirstLaw.txt");
                                }
                                catch(FileNotFoundException q){
                                    System.out.println("Could not create file");
                                }
                                outputWriter=new PrintWriter(outputStream);
                                outputWriter.println(buffer);
                                outputWriter.println(buffer1);
                                outputWriter.close();
                                }
                                catch(NullPointerException q){
                                    JOptionPane.showMessageDialog (null, "Please select a file", "Message", JOptionPane.INFORMATION_MESSAGE);
                                }                                
			}
		});
		mnNewMenu.add(mntmFirstLaw);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Second Law");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                System.out.println("Second Law evaluation requested");
                                try{
                                mySecondChart=new secondChart(versionList,pathForAll);
                                mySecondChart.computeMetrics();
                                mySecondChart.printChart(); //se ola ta chart mia getPath
                                                                
                                try {
                                    Thread.sleep(20000);    //perimenei 20deytera mexri o xrhsths na anoiksei ta parathyra
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(SoftwareMonitor.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                JOptionPane.showMessageDialog (null, "The system complies only if operations complexity or data structures complexity increases or if it does not have increasing rate", "Message", JOptionPane.INFORMATION_MESSAGE);
                                String buffer=JOptionPane.showInputDialog("Does the system comply to the second law?");//kati ginetai swsta edw
                                System.out.println(buffer);
                                String buffer1=JOptionPane.showInputDialog("Any additional comments?");
                                //twra emeine na dhmiourgoume edw to SecondLaw.txt copy paste ton kwdika
                                pathForSaving=mySecondChart.getAbsPath();
                                FileOutputStream outputStream=null;
                                PrintWriter outputWriter;
                                try{
                                    outputStream=new FileOutputStream(mySecondChart.getAbsPath()+"\\SecondLaw.txt");
                                }
                                catch(FileNotFoundException q){
                                    System.out.println("Could not create file");
                                }
                                outputWriter=new PrintWriter(outputStream);
                                outputWriter.println(buffer);
                                outputWriter.println(buffer1);
                                outputWriter.close();
                                }
                                catch(NullPointerException q){
                                    JOptionPane.showMessageDialog (null, "Please select a file", "Message", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }                                            
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Third Law");
                mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            System.out.println("Third Law evaluation requested");
                            try{
                            myThirdChart=new ThirdChart(versionList,pathForAll);
                            myThirdChart.computeMetrics();
                            myThirdChart.printChart();
                            try {
                                Thread.sleep(20000);    //perimenei 20deytera mexri o xrhsths na anoiksei ta parathyra
                            } catch (InterruptedException ex) {
                                Logger.getLogger(SoftwareMonitor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog (null, "The system complies only if software evolution is organized", "Message", JOptionPane.INFORMATION_MESSAGE);
                            String buffer=JOptionPane.showInputDialog("Does the system comply to the third law?");//kati ginetai swsta edw
                            System.out.println(buffer);
                            String buffer1=JOptionPane.showInputDialog("Any additional comments?");
                            FileOutputStream outputStream=null;
                            PrintWriter outputWriter;
                            try{
                                outputStream=new FileOutputStream(myThirdChart.getAbsPath()+"\\ThirdLaw.txt");
                            }
                            catch(FileNotFoundException q){
                                System.out.println("Could not create file");
                            }
                            outputWriter=new PrintWriter(outputStream);
                            outputWriter.println(buffer);
                            outputWriter.println(buffer1);
                            outputWriter.close(); 
                            }
                            catch(NullPointerException q){
                                JOptionPane.showMessageDialog (null, "Please select a file", "Message", JOptionPane.INFORMATION_MESSAGE);
                            }
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Fourth Law");
                mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            System.out.println("Fourth Law evaluation requested");
                            try{
                            myFourthChart=new FourthChart(versionList,pathForAll);
                            myFourthChart.computeMetrics();
                            myFourthChart.printChart();
                            try {
                                Thread.sleep(20000);    //perimenei 20deytera mexri o xrhsths na anoiksei ta parathyra
                            } catch (InterruptedException ex) {
                                Logger.getLogger(SoftwareMonitor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog (null, "The system complies only if operations workrate or data structures workrate is stable", "Message", JOptionPane.INFORMATION_MESSAGE);
                            String buffer=JOptionPane.showInputDialog("Does the system comply to the fourth law?");//kati ginetai swsta edw
                            System.out.println(buffer);
                            String buffer1=JOptionPane.showInputDialog("Any additional comments?");
                            FileOutputStream outputStream=null;
                            PrintWriter outputWriter;
                            try{
                                outputStream=new FileOutputStream(myFourthChart.getAbsPath()+"\\FourthLaw.txt");
                            }
                            catch(FileNotFoundException q){
                                System.out.println("Could not create file");
                            }
                            outputWriter=new PrintWriter(outputStream);
                            outputWriter.println(buffer);
                            outputWriter.println(buffer1);
                            outputWriter.close();
                            }
                            catch(NullPointerException q){
                                JOptionPane.showMessageDialog (null, "Please select a file", "Message", JOptionPane.INFORMATION_MESSAGE);
                            }
			}
		});
		mnNewMenu.add(mntmNewMenuItem_5);
		
		JMenuItem mntmFifthLaw = new JMenuItem("Fifth Law");
                mntmFifthLaw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            System.out.println("Fifth Law evaluation requested");
                            try{
                            myFifthChart=new FifthChart(versionList,pathForAll);
                            myFifthChart.computeMetrics();
                            myFifthChart.printChart();
                            try {
                                Thread.sleep(20000);    //perimenei 20deytera mexri o xrhsths na anoiksei ta parathyra
                            } catch (InterruptedException ex) {
                                Logger.getLogger(SoftwareMonitor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog (null, "The system complies only if versions with big increase are followed by versions with little,no or negative increase", "Message", JOptionPane.INFORMATION_MESSAGE);
                            String buffer=JOptionPane.showInputDialog("Does the system comply to the fifth law?");//kati ginetai swsta edw
                            System.out.println(buffer);
                            String buffer1=JOptionPane.showInputDialog("Any additional comments?");
                            FileOutputStream outputStream=null;
                            PrintWriter outputWriter;
                            try{
                                outputStream=new FileOutputStream(myFifthChart.getAbsPath()+"\\FifthLaw.txt");
                            }
                            catch(FileNotFoundException q){
                                System.out.println("Could not create file");
                            }
                            outputWriter=new PrintWriter(outputStream);
                            outputWriter.println(buffer);
                            outputWriter.println(buffer1);
                            outputWriter.close();
                            }
                            catch(NullPointerException q){
                                JOptionPane.showMessageDialog (null, "Please select a file", "Message", JOptionPane.INFORMATION_MESSAGE);
                            }
			}
		});
		mnNewMenu.add(mntmFifthLaw);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Sixth Law");
                mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            System.out.println("Sixth Law evaluation requested");
                            try{
                            mySixthChart=new SixthChart(versionList,pathForAll);
                            mySixthChart.computeMetrics();
                            mySixthChart.printChart();
                            try {
                                Thread.sleep(20000);    //perimenei 20deytera mexri o xrhsths na anoiksei ta parathyra
                            } catch (InterruptedException ex) {
                                Logger.getLogger(SoftwareMonitor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog (null, "The system complies only if there is increase", "Message", JOptionPane.INFORMATION_MESSAGE);
                            String buffer=JOptionPane.showInputDialog("Does the system comply to the sixth law?");//kati ginetai swsta edw
                            System.out.println(buffer);
                            String buffer1=JOptionPane.showInputDialog("Any additional comments?");
                            FileOutputStream outputStream=null;
                            PrintWriter outputWriter;
                            try{
                                outputStream=new FileOutputStream(mySixthChart.getAbsPath()+"\\SixthLaw.txt");
                            }
                            catch(FileNotFoundException q){
                                System.out.println("Could not create file");
                            }
                            outputWriter=new PrintWriter(outputStream);
                            outputWriter.println(buffer);
                            outputWriter.println(buffer1);
                            outputWriter.close();
                            }
                            catch(NullPointerException q){
                                JOptionPane.showMessageDialog (null, "Please select a file", "Message", JOptionPane.INFORMATION_MESSAGE);
                            }
			}
		});
		mnNewMenu.add(mntmNewMenuItem_6);
		
		JMenuItem mntmSeventhLaw = new JMenuItem("Seventh Law");
                mntmSeventhLaw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            System.out.println("Seventh Law evaluation requested");
                            try{
                            mySeventhChart=new SeventhChart(pathForAll);    //yphrxe ena versionList
                            mySeventhChart.computeMetrics();
                            mySeventhChart.printChart();
                            }
                            catch(NullPointerException q){
                                JOptionPane.showMessageDialog (null, "Please select a file", "Message", JOptionPane.INFORMATION_MESSAGE);
                            }
			}
		});
		mnNewMenu.add(mntmSeventhLaw);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Eighth Law");
                mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            System.out.println("Eighth Law evaluation requested");
                            try{
                            myEighthChart=new EighthChart(versionList,pathForAll);    
                            myEighthChart.computeMetrics();
                            myEighthChart.printChart();
                            try {
                                Thread.sleep(20000);    //perimenei 20deytera mexri o xrhsths na anoiksei ta parathyra
                            } catch (InterruptedException ex) {
                                Logger.getLogger(SoftwareMonitor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog (null, "The system complies only if we can predict the number of operations correctly", "Message", JOptionPane.INFORMATION_MESSAGE);
                            String buffer=JOptionPane.showInputDialog("Does the system comply to the eighth law?");//kati ginetai swsta edw
                            System.out.println(buffer);
                            String buffer1=JOptionPane.showInputDialog("Any additional comments?");
                            FileOutputStream outputStream=null;
                            PrintWriter outputWriter;
                            try{
                                outputStream=new FileOutputStream(myEighthChart.getAbsPath()+"\\EighthLaw.txt");
                            }
                            catch(FileNotFoundException q){
                                System.out.println("Could not create file");
                            }
                            outputWriter=new PrintWriter(outputStream);
                            outputWriter.println(buffer);
                            outputWriter.println(buffer1);
                            outputWriter.close();
                            }
                            catch(NullPointerException q){
                                JOptionPane.showMessageDialog (null, "Please select a file", "Message", JOptionPane.INFORMATION_MESSAGE);
                            }
			}
		});
		mnNewMenu.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_1 = new JMenu("Report");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Create report");
                mntmNewMenuItem_8.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog (null, "Creating report, click ok", "Message", JOptionPane.INFORMATION_MESSAGE);
                        Log myLog=new Log(pathForSaving,pathForAll,pathForOpeningTheFile);
                        myLog.createReport();
                        myLog.createFinalLog();
                        
                    }                    
                });
		mnNewMenu_1.add(mntmNewMenuItem_8);
		
		JMenu mnNewMenu_2 = new JMenu("Help");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmManual = new JMenuItem("Manual");
		mnNewMenu_2.add(mntmManual);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Get help online!");
		mnNewMenu_2.add(mntmNewMenuItem_9);
		frmSoftwareEvolutionMonitor.getContentPane().setLayout(null);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Welcome to the Software Evaluation Monitor! ");
		lblNewJgoodiesLabel.setBounds(65, 95, 349, 50);
		lblNewJgoodiesLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewJgoodiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmSoftwareEvolutionMonitor.getContentPane().add(lblNewJgoodiesLabel);
	}

}