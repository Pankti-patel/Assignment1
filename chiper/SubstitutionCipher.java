package chiper;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.*;
import java.util.stream.Collectors;

public class SubstitutionCipher {
    ArrayList<Character> insertKey = new ArrayList<Character>();
    Map<Character, Character> keys = new HashMap<Character, Character>();
    LinkedHashMap<Character, Integer> result = new LinkedHashMap<Character, Integer>();
    LinkedHashMap<Character, Integer> result_chiper = new LinkedHashMap<Character, Integer>();
    
    String name;
    String decodedString = "";

    public SubstitutionCipher(){}

    public SubstitutionCipher(String name, Map<Character, Character> keys){
        this.name = name;
        this.keys = keys;
    }

    public void FrequencyOfChiperText(String encrypt){
        int size = encrypt.length();
            int[] freq_filename = new int[size];
            char[] str_filename = encrypt.toCharArray();
            
            for(int i = 0; i<size; i++){
                freq_filename[i] = 1;
                for(int j = i+1; j<size; j++){
                    if(str_filename[i] == str_filename[j]){
                        freq_filename[i]++;
                        str_filename[j] = '0';
                    }
                }
            }
            // Sort the array
            Map<Character, Integer> sorting_filename = new HashMap<Character, Integer>();  
            for(int i = 0; i <freq_filename.length; i++) 
            {  
                if(str_filename[i] != ' ' && str_filename[i] != '0')  
                sorting_filename.put(str_filename[i],freq_filename[i]);  
            } 
            sorting_filename.entrySet().stream().sorted(Map.Entry.<Character, Integer>comparingByKey());
            sorting_filename.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

            result_chiper = sorting_filename.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public void originalLanguage(String name, String fileName){
        // For Language B
        int size = fileName.length();
            int[] freq_filename = new int[size];
            char[] str_filename = fileName.toCharArray();
            
            for(int i = 0; i<size; i++){
                freq_filename[i] = 1;
                for(int j = i+1; j<size; j++){
                    if(str_filename[i] == str_filename[j]){
                        freq_filename[i]++;
                        str_filename[j] = '0';
                    }
                }
            }
            // Sort the array
            Map<Character, Integer> sorting_filename = new HashMap<Character, Integer>();  
            for(int i = 0; i <freq_filename.length; i++) 
            {  
                if(str_filename[i] != ' ' && str_filename[i] != '0')  
                sorting_filename.put(str_filename[i],freq_filename[i]);  
            } 
            sorting_filename.entrySet().stream().sorted(Map.Entry.<Character, Integer>comparingByKey());
            sorting_filename.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

            result = sorting_filename.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
            matchLanguage();

    }


    public void getKey(ArrayList<Character> encryptKey){
        
        char letter;
        System.out.println("Do enter Capital Alphabet");
        // Process to call enter each character from the user itself and store it in the array.
        for(int i = 0; i<26; i++){ // Setting the value 26 because there are 26 characters in total
            letter = (char)(65 + i);
            
            Scanner myObj = new Scanner(System.in);
            System.out.print(letter + " - ");
            String decodeKey = myObj.next();
            decodeKey = decodeKey.toUpperCase();

            if(decodeKey == null){
                System.out.println("Please enter a charater");
                break;
            }
            else{
                char key = decodeKey.charAt(0);
                encryptKey.add(key);
            }
            
        }
        KeyIsValid();
    }

    //Validating that whether there is any duplicate key or not as well as creating a map for key
    public boolean KeyIsValid(){
        if(insertKey == null){
            return false;
        }
        else{
            // Code: Checking whether any key is duplicate or not and if yes then changing it.
            for(int i = 0; i<insertKey.size(); i++){
                for(int j =i+1; j<insertKey.size(); j++){
                    if(insertKey.get(i) == insertKey.get(j)){
                        System.out.println("Value of index " + i + " and " + j + " is " + insertKey.get(i) + ". Hence equal. Please Change it.");
                        // Found a duplicate thats why changing the value of jth element.
                        Scanner Obj2 = new Scanner(System.in);
                        System.out.print("Enter new value at index "+j+ ": ");
                        String UpdatedKey = Obj2.next();
                        char newValue = UpdatedKey.charAt(0);
                        insertKey.set(j,newValue);
                    }
                }
            }
            return true;
        }    
    }

    public boolean cipherText(String encryptedText){
        String decoded = "";
        if(insertKey == null){
            return false;
        }
        else{
            int size = encryptedText.length();
        
            for(char i : encryptedText.toCharArray()){
                if(i == ' '){
                    decoded = decoded + ' ';
                }
                else if(i == ';'){
                    decoded = decoded + ';';
                } 
                else if(i == ':'){
                    decoded = decoded + ':';
                }
                else if(i == '?'){
                    decoded = decoded + '?';
                }
                else if(i == '.'){
                    decoded = decoded + '.';
                }
                else{
                    char encryptedLetter = i;
                    char decryptedLetter = keys.get(encryptedLetter);
                    decoded = decoded + decryptedLetter;
                }
            }

            System.out.println(decoded);
            return true;
        }
        
    } 
    
    public double matchLanguage(){
        int[] values = new int[result.size()];
        AtomicInteger counter = new AtomicInteger(0);
        result.values().stream().forEach(
            value -> {
                values[counter.getAndIncrement()] = value;
            }
        );
        
        int[] chiperValues = new int[result_chiper.size()];
        AtomicInteger chiperCounter = new AtomicInteger(0);
        result_chiper.values().stream().forEach(
            value -> {
                chiperValues[chiperCounter.getAndIncrement()] = value;
            }
        );
        
        int[] diff = new int[result_chiper.size()];
        int sizeOfChiper = chiperValues.length;
        int sizeOfLanguage = values.length;
        int sum = 0;
        int difference = 0;
        if(sizeOfChiper<=sizeOfLanguage){
            for(int k=0; k<sizeOfChiper;k++){
                difference = values[k] - chiperValues[k];
                diff[k] = Math.abs(difference);
            }
            
            for(int l=0; l<sizeOfChiper;l++){
                sum = sum + diff[l];
            }
        }
        else{
            for(int k=0; k<sizeOfLanguage;k++){
                difference = values[k] - chiperValues[k];
                diff[k] = Math.abs(difference);
            }
            for(int l=0; l<sizeOfLanguage;l++){
                sum = sum + diff[l];
            }
        }
        System.out.println(sum);
        return sum;
    }
}
