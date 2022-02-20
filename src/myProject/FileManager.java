package myProject;

import com.sun.source.tree.TryTree;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    public static final String PATH_USERS="src/myProject/files/users.txt";
    public static final String PATH_WORDS="src/myProject/files/words.txt";
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;

    public ArrayList<String> lecturaFile(){
        ArrayList<String> words = new ArrayList<>();
        try {
            fileReader = new FileReader(PATH_WORDS);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while(line!=null){
                words.add(line);
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
        return words;
    }

    public ArrayList<String> usersReader(){
        ArrayList<String> users = new ArrayList<>();
        try {
            fileReader = new FileReader(PATH_USERS);
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

    public void writeUsers(String linea){
        try {
            fileWriter = new FileWriter(PATH_USERS,true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
