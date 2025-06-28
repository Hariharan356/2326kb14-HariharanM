package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task2_Read_Text_File {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Hariharan\\eclipse-workspace\\JAVA\\src\\Day4\\TextFile.txt"; // File path.
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
