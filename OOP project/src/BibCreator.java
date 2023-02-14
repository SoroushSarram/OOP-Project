import java.io.*;
import java.util.Scanner;

//BibCreator class
public class BibCreator
{

	public static void main(String[] args)
	{	
		
		//Using scanner for the files 
		Scanner[] scanner = new Scanner[10]; //Creating scanner array to save the input bib files content in it later
		Scanner sc = new Scanner (System.in);

		//Initializing variable "i" to 1. Variable "i" represent the number of a file  
		int i = 1;
		
		//Creating a string variable to Find a specific file
		String FindFile="";
		
		//Using try and catch to Read Latex1.bib to Laxtex10.bib files
		try {
            for (i = 1; i< 11; i++)
            {
                scanner[i-1] = new Scanner(new FileInputStream("Latex" + i + ".bib"));//Reading input bib files and saving their content in scanner array with their corresponding number => e.g. Latex1.bib content would be saved in scanner[1] - Latex5.bib content would be saved in scanner[5]
            }
        } catch(FileNotFoundException e)
		{
            System.out.println("Could not open the input file Latex" + i + ".bib for reading. \nPlease check if the file exists! \nThe program will terminate after closing any opened files.");//The error to show if reading the input bib files and saving their content action fails
            System.exit(0);//Exit the application if the above error is being shown.
        }
        
        //Using PrintWriter to open the output of the files
        PrintWriter[] pWriter1 = new PrintWriter[10];//Creating PrintWriter array to be used for creating corresponding IEEE type output files for input files and saving/printing desired content in them later
        PrintWriter[] pWriter2 = new PrintWriter[10];//Creating PrintWriter array to be used for creating corresponding ACM type output files for input files and saving/printing desired content in them later
        PrintWriter[] pWriter3 = new PrintWriter[10];//Creating PrintWriter array to be used for creating corresponding NJ type output files for input files and saving/printing desired content in them later
        
        //Using try and catch to creating 3 new files
        try
        {
            for (i=1; i<11; i++)//A loop to create 3 output files with different type for each input bib file
            {
                FindFile = "IEEE";            
                pWriter1[i-1] = new PrintWriter(new FileOutputStream("IEEE" + i + ".json",true));//Creating IEEE type output for each input bib file => e.g. for Latex1.bib creates IEEE1.json - for Latex5.bib creates IEEE5.json

                FindFile = "ACM";
                pWriter2[i-1] = new PrintWriter(new FileOutputStream("ACM" + i + ".json",true));//Creating ACM type output for each input bib file => e.g. for Latex1.bib creates ACM1.json - for Latex5.bib creates ACM5.json
                
                FindFile = "NJ";
                pWriter3[i-1] = new PrintWriter(new FileOutputStream("NJ" + i + ".json",true));//Creating NJ type output for each input bib file => e.g. for Latex1.bib creates NJ1.json - for Latex5.bib creates NJ5.json
                pWriter1[i-1].close();
                pWriter2[i-1].close();
                pWriter3[i-1].close();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not create output file "+ FindFile + i +".json\n The application will now terminate!");//The error to show if creating the output fails
            System.exit(0);//Exit the application if the above error is being shown.
        }
        
        //By reaching this line, if no error has occured, we would have 30 ouput files created because  foreach input file we create 3 different ouput type => foreach input bib files we create 1 IEEE, 1 ACM, and 1 NJ output

        for(i=1; i<11; i++) //A loop to check if each input bib file is valid or not
        {
            FileVerification.processFilesForValidation(i);//Checking the validity of each input bib files by their corresponding number and doing the related change(stated in the project description) for each file based on the output type(IEEE or ACm or NJ) and save it in their output file=> e.g. i of 1 means we are checking Latex1.bib - i of 5 means we are checking Latex5.bib
        }
        
        System.out.println("A total of "+ FileVerification.count+" files were invalid. There are only " + (10 - FileVerification.count)+" valid files processed"); //Show a message for how many of the input bib files were valid and how many were not
        
        BufferedReader bReader = null;//Initializing a BufferedReader to read the desire output file content
        
        String line=null;
        
        //Using try and catch to see if the file exists and if it will open or to see if a file doesn't exist:  
        try
        {
        	FileVerification.viewFile(bReader,line,sc);//Reading the desired output file and save its content in the created BufferedReader to be able to show it in Java Console
        } 
        catch(FileNotFoundException e)
        {
            System.out.println("Could not open input file. File does not exist! Please check if file exists!");//Error to show if the desired output file was not existed => e.g. wrong name entered

            try
            {
                FileVerification.viewFile(bReader,line,sc);//Reading the desired output file and save its content in the created BufferedReader to be able to show it in Java Console
            } catch(FileNotFoundException ex)
            {
                System.out.println("Could not open the input file. Please check if the file exists!");//Error to show if the desired output file was not existed => e.g. wrong name entered
                System.out.println("The application will now terminate!");//Extra Error to show if the desired output file was not existed => e.g. wrong name entered
                System.exit(0);//Exit the application if the above error is being shown.
            }
            catch (IOException ex)
            {
                System.out.println("IO Exception occurred! The application will now terminate!");//Error to show if anything goes wrong with reading and showing the desired output file content 
                System.exit(0);//Exit the application if the above error is being shown.
            }
        }
        catch (IOException e)
        {
            System.out.println("IO Exception occurred! The application will now terminate!");//Error to show if anything goes wrong with reading and showing the desired output file content 
            System.exit(0);//Exit the application if the above error is being shown.
        }
	}
}