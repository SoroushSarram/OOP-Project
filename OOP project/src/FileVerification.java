import java.io.*;
import java.util.Scanner;

//class ValidationOfFile
public class FileVerification 
{
	public static int count=0;
	
	//Creating the method processFilesForValidation for the input of the Latex file number
	public static void processFilesForValidation(int LatexFileNumber) 
	{//The body of this method is based on how the project instruction is stated

		//String Variables ** All elements that contains the files: **these variables/strings can be found in input bib files**
        String journal=null;
        String ISSN=null;
        String number=null;
        String title=null;
        String year=null;
        String month=null;
        String volume=null;
        String pages=null;
        String line;
        String keywords=null;
        String doi=null;
        String authorIEEE=null;
        String authorACM=null;
        String authorNJ=null;
        String problem = null;
        
        // Variables for the PrintWriters
        PrintWriter printWriterIEEE = null;
        PrintWriter printWriterACM = null;
        PrintWriter printWriterNJ = null;
        int countACM = 0;

        try 
        {
        	//Creating the BufferedReader class
            BufferedReader file = new BufferedReader(new FileReader("Latex" + LatexFileNumber + ".bib"));
            
            File ACMFile = new File("ACM" + LatexFileNumber + ".json");
            File IEEEFile = new File("IEEE" + LatexFileNumber + ".json");
            File NJFile = new File("NJ" + LatexFileNumber + ".json");
            
            printWriterIEEE = new PrintWriter(new FileOutputStream("IEEE" + LatexFileNumber + ".json",true));
            printWriterACM = new PrintWriter(new FileOutputStream("ACM" + LatexFileNumber + ".json",true));
            printWriterNJ = new PrintWriter(new FileOutputStream("NJ" + LatexFileNumber + ".json",true));
            
            
            while ((line = file.readLine()) != null) 
            {//A loop to check each line of each input bib file
                boolean lineError=true;
                if (line.equals("")) 
                {
                    //Do Nothing
                } 
                else if (line.contains("author={},")) 
                { //Checking 'author' field in input bib file and if it is empty, delete the 3 created output IEEE and ACM and NJ files
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "author";
                    lineError=false;
                    throw new FileInvalidException();//Throw/Show the error
                } 
                else if (line.contains("journal={},")) 
                { //Checking 'journal' field in input bib file and if it is empty, delete the 3 created output IEEE and ACM and NJ files
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    lineError=false;
                    problem = "journal";
                    throw new FileInvalidException();//Throw/Show the error
                } 
                else if (line.contains("title={},")) 
                { //Checking 'title' field in input bib file and if it is empty, delete the 3 created output IEEE and ACM and NJ files
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "title";
                    lineError=false;
                    throw new FileInvalidException();//Throw/Show the error
                } 
                else if (line.contains("year={},"))
                { //Checking 'year' field in input bib file and if it is empty, delete the 3 created output IEEE and ACM and NJ files
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "year";
                    throw new FileInvalidException();//Throw/Show the error
                }
                else if (line.contains("volume={},")) 
                { //Checking 'volume' field in input bib file and if it is empty, delete the 3 created output IEEE and ACM and NJ files
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "volume";
                    lineError=false;
                    throw new FileInvalidException();//Throw/Show the error
                } 
                else if (line.contains("pages={},")) 
                { //Checking 'pages' field in input bib file and if it is empty, delete the 3 created output IEEE and ACM and NJ files
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "pages";
                    lineError=false;
                    throw new FileInvalidException();//Throw/Show the error
                } 
                else if (line.contains("keywords={},")) 
                { //Checking 'keywords' field in input bib file and if it is empty, delete the 3 created output IEEE and ACM and NJ files
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "keywords";
                    lineError=false;
                    throw new FileInvalidException();//Throw/Show the error
                } 
                else if (line.contains("number={},")) 
                { //Checking 'number' field in input bib file and if it is empty, delete the 3 created output IEEE and ACM and NJ files
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "number";
                    lineError=false;
                    throw new FileInvalidException();//Throw/Show the error
                } 
                else if (line.contains("doi={},"))
                { //Checking 'doi' field in input bib file and if it is empty, delete the 3 created output IEEE and ACM and NJ files
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "doi";
                    lineError=false;
                    throw new FileInvalidException();//Throw/Show the error
                } 
                else if (line.contains("ISSN={},")) 
                { //Checking 'ISSN' field in input bib file and if it is empty, delete the 3 created output IEEE and ACM and NJ files
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "ISSN";
                    lineError=false;
                    throw new FileInvalidException();//Throw/Show the error
                } 
                else if (line.contains("month={},"))
                { //Checking 'month' field in input bib file and if it is empty, delete the 3 created output IEEE and ACM and NJ files
                    ACMFile.delete();
                    IEEEFile.delete();
                    NJFile.delete();
                    problem = "month";
                    lineError=false;
                    throw new FileInvalidException();//Throw/Show the error
                } 
                else if (line.contains("author={")&&lineError)
                {//If the input bib file has no error in its 'author' field, process the output for its 3 different type of outputs of IEEE and ACM and NJ - The body of this if statement is how the project instruction is asking us to process the 'author'
                    authorIEEE=line.substring(8,line.indexOf("}"));
                    authorACM=line.substring(8,line.indexOf("}"));
                    authorNJ=line.substring(8,line.indexOf("}"));
                    authorIEEE=authorIEEE.replaceAll(" and",",");
                    authorNJ=authorNJ.replaceAll("and","&");
                    if (line.contains("and")) 
                    {
                        authorACM=authorACM.substring(0,authorACM.indexOf("and")-1)+" et al";
                    } 
                    else 
                    {
                        authorACM=authorACM+ "et al";
                        countACM++;  
                    }                   
                } 
                else if (line.contains("title={")&&lineError) 
                {//If the input bib file has no error in its 'title' field, process the output for its 3 different type of outputs of IEEE and ACM and NJ - The body of this if statement is how the project instruction is asking us to process the 'title'
                    title=line.substring(7,line.indexOf("}"));    
                } 
                else if (line.contains("year={")&&lineError) 
                {//If the input bib file has no error in its 'year' field, process the output for its 3 different type of outputs of IEEE and ACM and NJ - The body of this if statement is how the project instruction is asking us to process the 'year'
                    year=line.substring(6,line.indexOf("}"));   
                } 
                else if (line.contains("journal={")&&lineError) 
                {//If the input bib file has no error in its 'journal' field, process the output for its 3 different type of outputs of IEEE and ACM and NJ - The body of this if statement is how the project instruction is asking us to process the 'journal'
                    journal=line.substring(9,line.indexOf("}"));   
                } 
                else if (line.contains("pages={")&&lineError) 
                {//If the input bib file has no error in its 'pages' field, process the output for its 3 different type of outputs of IEEE and ACM and NJ - The body of this if statement is how the project instruction is asking us to process the 'pages'
                    pages=line.substring(7,line.indexOf("}"));
                } 
                else if (line.contains("volume={")&&lineError) 
                {//If the input bib file has no error in its 'volume' field, process the output for its 3 different type of outputs of IEEE and ACM and NJ - The body of this if statement is how the project instruction is asking us to process the 'volume'
                    volume=line.substring(8,line.indexOf("}"));
                } 
                else if (line.contains("number={")&&lineError) 
                {//If the input bib file has no error in its 'number' field, process the output for its 3 different type of outputs of IEEE and ACM and NJ - The body of this if statement is how the project instruction is asking us to process the 'number'
                    number=line.substring(8,line.indexOf("}"));
                } 
                else if (line.contains("ISSN={")&&lineError) 
                {//If the input bib file has no error in its 'ISSN' field, process the output for its 3 different type of outputs of IEEE and ACM and NJ - The body of this if statement is how the project instruction is asking us to process the 'ISSN'
                    ISSN=line.substring(6,line.indexOf("}"));
                } 
                else if (line.contains("keywords={")&&lineError) 
                {//If the input bib file has no error in its 'keywords' field, process the output for its 3 different type of outputs of IEEE and ACM and NJ - The body of this if statement is how the project instruction is asking us to process the 'keywords'
                    keywords=line.substring(10,line.indexOf("}"));  
                } 
                else if (line.contains("doi={")&&lineError) 
                {//If the input bib file has no error in its 'doi' field, process the output for its 3 different type of outputs of IEEE and ACM and NJ - The body of this if statement is how the project instruction is asking us to process the 'doi'
                    doi=line.substring(5,line.indexOf("}"));              
                } 
                else if (line.contains("month={")&&lineError) 
                {//If the input bib file has no error in its 'month' field, process the output for its 3 different type of outputs of IEEE and ACM and NJ - The body of this if statement is how the project instruction is asking us to process the 'month'
                    month=line.substring(7,line.indexOf("}"));
                    printWriterIEEE.println(authorIEEE+". "+"\"" + title + "\"" +", " + journal + ", vol. " + volume +", no. " + number +", p. " + pages +", " + month +" "+ year +".\n");//Save the IEEE type output for of the input bib file
                    printWriterACM.println("["+countACM+"] "+ authorACM +". "+ title +". "+ year +". "+ journal +". "+ volume +", "+ number +" ("+ year +")"+", "+ pages +". DOI:https://doi.org/ "+ doi +".\n");//Save the ACM type output for of the input bib file
                    printWriterNJ.println(authorNJ +". "+ title +". "+ journal +". "+ volume +", "+ pages +"("+ year +")"+".\n");//Save the NJ type output for of the input bib file
                }
            }
            file.close();
        } 
        catch (FileNotFoundException e)
        { 	
            System.out.println("File Latex" + LatexFileNumber + ".bib not found! Program will terminate now.");
            System.exit(0);
        } 
        catch (FileInvalidException e) 
        {
            count++;
            System.out.println("Error: Detected empty field!");
            System.out.println("\nProblem detected with input file: Latex" + LatexFileNumber + ".bib");
            System.out.println(e.getMessage());
            System.out.println("File is Invalid: Field \"" + problem + "\" is Empty. Processing stopped at this point. Other empty fields may be present as well!\n");
        } 
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        } 
        finally 
        {
            printWriterIEEE.close();
            printWriterACM.close();
            printWriterNJ.close();
        }
    }
    
	//A function to read and print the content of the desired output file using BufferedReader
    public static void viewFile(BufferedReader bReader, String line, Scanner sc) throws FileNotFoundException,IOException
    {
        System.out.print("\nPlease enter the name of one of the files that you need to review:");//Asking user to enter his/her desired ouput file name so the application can show its content
        String FileView = sc.next();//Saving user answer in a variable
        bReader = new BufferedReader(new FileReader(FileView));//Trying to read the content of the user's entered output file name
        while((line=bReader.readLine())!=null) 
        {
            System.out.println(line);//Printing/Showing the content of the user's entered output file name
        }
        System.out.println("Merci!");
        System.exit(0);//Exiting the application at this point
    }
}