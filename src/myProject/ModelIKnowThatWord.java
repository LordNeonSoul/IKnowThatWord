package myProject;

import java.util.ArrayList;
import java.util.Random;

public class ModelIKnowThatWord {
    private FileManager fileManager;
    private Dictionary dictionary;
    private ArrayList<String> registeredUsers = new ArrayList<String>();
    private ArrayList<String> wordsToRemember = new ArrayList<String>();
    private ArrayList<String> wordsByGame = new ArrayList<String>();
    private ArrayList<String> wordsInLevel = new ArrayList<String>();
    private String userNameGame, userNameInto, word, wordToRemember;
    private int flag, flag2, hits, wordsLevel,wordsToRememberInGame, level, passedLevels;
    private boolean isAWordToRemember, plusHit;
    boolean verifyUser;

    ModelIKnowThatWord(String userNameInto){
        fileManager = new FileManager();
        dictionary = new Dictionary();
        verifyUser=false;
        this.userNameGame=userNameInto;
        if (isUser()){
            passedLevels= getUserLevels();
        }else{
            newUser();
            verifyUser=true;
            passedLevels=0;
        }
        hits=0;
        flag=0;
        flag2=0;
        setActualLevel();
        if (passedLevels==10)  {
            level = passedLevels;
            setWordsOnLevel();
            setWordsToRememberInGame();
            wordsByGame=dictionary.getWordsByGame(0);
            wordsToRemember=dictionary.getWordsToRemember(0);
            wordsInLevel= new ArrayList<>();
            setWordsToTheLevel();
        }
    }

    private boolean isAWordToRemember(String word){
        isAWordToRemember = false;
        for (int i = 0; i < wordsToRemember.size() ; i++) {
            if (wordsToRemember.get(i).equals(word)){
                isAWordToRemember=true;
                break;
            }
        }
        return isAWordToRemember;
    }

    private int searchUser(){
        int position=-1;
        for (int i = 0; i < registeredUsers.size()&&registeredUsers.get(i).length()!=0;i++) {
            String thisUser = registeredUsers.get(i).substring(0, registeredUsers.get(i).lastIndexOf(":"));
            if (thisUser.equals(userNameGame)){
                position=i;
                break;
            }
        }
        return position;
    }

    public boolean isUser() {
        boolean verifyRegister = false;
        if (searchUser()!=-1){
            verifyRegister=true;
        }
        return verifyRegister;
    }

    public void newUser() {
        fileManager.writeUsers(userNameGame+"-"+0);
    }

    public int setUserLevels(){
        if(getUserLevels()<10){
            fileManager.writeLevel(getUserLevels()+1,searchUser());
        }
        return getUserLevels();
    }

    private void setActualLevel(){
        hits=0;
        if(passedLevels<10) {
            level = passedLevels + 1;
            setWordsOnLevel();
            setWordsToRememberInGame();
            word = "";
            wordsByGame = dictionary.getWordsByGame(wordsLevel);
            wordsToRemember = dictionary.getWordsToRemember(wordsToRememberInGame);
            wordsInLevel = new ArrayList<>();
            setWordsToTheLevel();
        }
    }

    private void setPassedLevels(){
        if(hits>=wordsToRememberInGame){
            passedLevels= setUserLevels();
            setActualLevel();
            flag=0;
            flag2=0;
        } else {
            flag=0;
            flag2=0;
            hits=0;
            setWordsOnLevel();
            setWordsToRememberInGame();
            word="";
            wordsByGame=dictionary.getWordsByGame(wordsLevel);
            wordsToRemember=dictionary.getWordsToRemember(wordsToRememberInGame);
            wordsInLevel= new ArrayList<>();
           setWordsToTheLevel();
        }
    }

    private void setWordsToTheLevel(){

        ArrayList<String> wordsRemember=new ArrayList<>(wordsToRemember);
        ArrayList<String> wordsInTheLevel=new ArrayList<>(wordsLevel);
        while (wordsInLevel.size()<wordsLevel) {
            Random random = new Random();
            int randomized = random.nextInt(0,2);
            for (int i=0;i<=randomized&&wordsRemember.size()>0;i++){
                int rando=random.nextInt(0,wordsRemember.size());
                wordsInLevel.add(wordsRemember.get(rando));
                wordsRemember.remove(rando);
            }
            randomized = random.nextInt(0,3);
            for (int i=0;i<=randomized && wordsInTheLevel.size()>0;i++){
                int rando = random.nextInt(0,wordsInTheLevel.size());
                wordsInLevel.add(wordsInTheLevel.get(rando));
                wordsInTheLevel.remove(rando);
            }
        }
    }

    private void setWordsOnLevel(){
        switch (level){
            case 1-> wordsLevel=20;
            case 2-> wordsLevel=40;
            case 3-> wordsLevel=50;
            case 4-> wordsLevel=60;
            case 5-> wordsLevel=70;
            case 6-> wordsLevel=80;
            case 7-> wordsLevel=100;
            case 8-> wordsLevel=120;
            case 9-> wordsLevel=140;
            case 10->wordsLevel=200;
        }
    }

    private void setWordsToRememberInGame(){
        switch (level){
            case 1-> wordsToRememberInGame=14;
            case 2-> wordsToRememberInGame=28;
            case 3-> wordsToRememberInGame=38;
            case 4-> wordsToRememberInGame=48;
            case 5-> wordsToRememberInGame=56;
            case 6-> wordsToRememberInGame=68;
            case 7-> wordsToRememberInGame=90;
            case 8-> wordsToRememberInGame=108;
            case 9-> wordsToRememberInGame=133;
            case 10->wordsToRememberInGame=200;
        }
    }

    public void setHits(boolean answer){
        plusHit = isAWordToRemember(word);
        if (answer== plusHit){
            hits++;
        }
    }

    public String getWordToRemember() {
        wordToRemember="";
        if (flag2<wordsToRemember.size()){
            wordToRemember=wordsToRemember.get(flag2);
            flag2++;
        }
        return wordToRemember;
    }

    public String getWord(){
        if (flag<wordsInLevel.size()){
            word=wordsInLevel.get(flag);
            flag++;
        }else{
            setPassedLevels();
            word="";
        }
        return word;
    }

    public int getHits(){
        setWordsToRememberInGame();
        return wordsToRememberInGame;
    }

    public int getPassedLevels(){
        return passedLevels;
    }

    public int getLevel(){
        return level;
    }

    public int getUserLevels(){
        registeredUsers = fileManager.usersReader();
        String user = registeredUsers.get(searchUser());
        int levels = Integer.parseInt(user.substring(user.lastIndexOf(":")+1));
        return levels;
    }

}
