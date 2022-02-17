package myProject;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;

    public ArrayList<String> usersReader(){
        ArrayList<String> users = new ArrayList<>();
        try {
            fileReader = new FileReader("src/files/Users.txt");
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while(line!=null){
                users.add(line);
                line = input.readLine();
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}
