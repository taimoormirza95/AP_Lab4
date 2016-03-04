package spidercrawler;

import java.io.*;     
import java.util.*;   


public abstract class Spidercrawler implements Runnable{
	
    private  File F1;
    private  String str;


	static List<Recc> Files_ = new ArrayList<Recc>();
	static List<Str> Files_Names = new ArrayList<Str>();
	
	public Spidercrawler(File f1, String str)
	{
		this.F1 = f1;
		this.str = str;		
	}
	
    public static void main(String[] args) throws IOException {
    	
    
        Scanner console = new Scanner(System.in);
        System.out.print("Folder name ? : ");
        String DName = console.nextLine();
        File f = new File(DName);
        Crawling(f,"");
        System.out.println("To Search by File Name, press 1: ");
        System.out.println("to Search by Content, press 2: ");

        String Choice = console.nextLine();
        
        if(Choice.equals("1"))
        {
        	System.out.println("File to Search?: ");
        	String f_name= console.nextLine();
            Search_L(f_name);
        }
        if(Choice.equals("2"))
        {
            System.out.println("Enter Text Content: ");
        	String farig = console.nextLine();
        	Search_f(farig);
        }
    }
    
    
    private static void Search_L(String fileName)
    {
    	//System.out.println("Debugging the LIST\n");
    	int temp = 1;
    	for (ListIterator<Recc> tmp2 = Files_.listIterator(); tmp2.hasNext(); ) {
    	    Recc t2 = tmp2.next();
    	    if(t2.FileName.equals(fileName))
    	    {
    	    	System.out.println("File Found!");
    	    	System.out.println("Path is: " + t2.FilePath);
    	    	temp = 0;
    	    }
    	}
    	if(temp == 1)
    	{
    		System.out.println("Could not find the File .... :(  !");
    	}
    } 

  
    public static  void Crawling(File f, String t3) throws IOException {

    	Recc temp;
    	temp = new Recc();
    	temp.FileName = f.getName();
    	temp.FilePath = f.getAbsolutePath();
    	temp.FileSize = (int) ((f.length())/1024);
    	
    	if(f.getName().endsWith(".txt") && f.isFile())
    	{
            int readlimiter = 0;

            FileInputStream buf1 = new FileInputStream(f.getAbsoluteFile());
    	    BufferedReader buf2 = new BufferedReader(new InputStreamReader(buf1));

    	    String t4;

        	Str temp2 = null;
        	temp2 = new Str();
        	temp2.FileName = f.getName();
        	temp2.FilePath = f.getPath();
        	temp2.FileSize = "";
    	    while ((t4 = buf2.readLine()) != null) 
            {
    	    	readlimiter++;
    	    	temp2.FileSize = temp2.FileSize + t4;
    	    	if(readlimiter > 200)
    	    		break;
    	    }
    	    buf1.close();
    	    Files_Names.add(temp2);
    	}
    	
    	
    	Files_.add(temp);
    	
    	if (f.isDirectory()) {
        	// recursive case: directory
        	// print everything in the directory
        	File[] Heir = f.listFiles();
    		t3 += "    ";
    		Spidercrawler[] threads = new Spidercrawler[Heir.length];  
    		System.out.println(Heir.length);
        	for (int i = 0; i < Heir.length; i++) 
                {
                    Crawling(Heir[i], t3);
        	}
        }
    }
   
    private static void Search_f(String farig)
    {
    	int counter = 0;
    	for (ListIterator<Str> tmp2 = Files_Names.listIterator(); tmp2.hasNext(); ) 
        {
    	    Str t2 = tmp2.next();
    	    if(t2.FileSize.contains(farig))
    	    {
    	    	System.out.println("File has been found!");
    	    	System.out.println("Path : " + t2.FilePath);
    	    	counter = 1;
    	    	break;
    	    }
    	}
    	
    	if (counter == 0)
    		System.out.println("File couldnt be found!");
    	
    }
       
}
