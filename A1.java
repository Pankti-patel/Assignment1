import chiper.SubstitutionCipher;
import java.util.*;
import java.io.*;

public class A1 {
    public static void main(String[] args){
        ArrayList<Character> encryptKey = new ArrayList<Character>();
        Map<Character, Character> keys = new HashMap<Character, Character>();

        System.out.println("Choose any one method:");
        System.out.println("A: Enter Key manually");
        System.out.println("B: Match the language and get key created itself");

        Scanner option = new Scanner(System.in);
        System.out.print("Your choice: ");
        String opt = option.next();
        opt = opt.toUpperCase();

        switch(opt){
            case "A":
                SubstitutionCipher obj = new SubstitutionCipher();
                obj.getKey(encryptKey);

                // Creating Map for keys
                for(int i=0;i<26;i++){
                    char letter = (char)(65 + i);
                    char key_value = encryptKey.get(i);

                    keys.put(letter, key_value);
                }

                try  
                {  
                    //constructor of File class having file as argument  
                    File file=new File("encrypt.txt");   
                    //creates a buffer reader input stream  
                    BufferedReader br =new BufferedReader(new FileReader(file));   
                    int r=0; 
                    String fileName = ""; 
                    while((r=br.read())!=-1)  
                    {  
                        fileName = fileName + (char)r;  
                    }  
                    SubstitutionCipher obj2 = new SubstitutionCipher("Assignment1", keys);
                    obj2.cipherText(fileName);
                }  
                catch(Exception e)  
                {  
                    System.out.println("An error occurred.");
                    e.printStackTrace();  
                } 
            break;

            case "B":
                SubstitutionCipher obj3 = new SubstitutionCipher();
                try  
                {  
                    // Chiper Text
                    //constructor of File class having file as argument  
                    File file=new File("encrypt.txt");   
                    //creates a buffer reader input stream  
                    BufferedReader br =new BufferedReader(new FileReader(file));   
                    int r=0; 
                    String chiper = ""; 
                    while((r=br.read())!=-1)  
                    {  
                        chiper = chiper + (char)r;  
                    }
                    obj3.FrequencyOfChiperText(chiper);
                    // language A
                    //constructor of File class having file as argument  
                    File fileA=new File("languageA.txt");   
                    //creates a buffer reader input stream  
                    BufferedReader br1 =new BufferedReader(new FileReader(fileA));   
                    int r1=0; 
                    String name = ""; 
                    while((r1=br1.read())!=-1)  
                    {  
                        name = name + (char)r1;  
                    }
                    obj3.originalLanguage("Language A", name);
                    /* // language A
                    //constructor of File class having file as argument  
                    File fileB=new File("languageB.txt");   
                    //creates a buffer reader input stream  
                    BufferedReader br2 =new BufferedReader(new FileReader(fileB));   
                    int r2=0; 
                    String fileName = ""; 
                    while((r2=br2.read())!=-1)  
                    {  
                        fileName = fileName + (char)r2;  
                    }
                    obj3.originalLanguage("Language B", fileName); */
                }  
                catch(Exception e)  
                {  
                    System.out.println("An error occurred.");
                    e.printStackTrace();  
                }
            break;

            default:
                System.out.print("Please enter either A or B");
        }
    }
}
