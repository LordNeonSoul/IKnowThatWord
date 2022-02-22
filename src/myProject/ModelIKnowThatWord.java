package myProject;

import java.util.ArrayList;
import java.util.Random;

public class ModelIKnowThatWord {
    private FileManager fileManager;
    private Dictionary dictionary;
    private ModelUser modelUser;
    ArrayList<String> wordsToRemember;
    ArrayList<String> wordsByGame;
    ArrayList<String> wordsInLevel;
    private String userNameGame, word, wordToRemember;
    private int flag, flag2, hits, wordsLevel, level, passedLevels;
    private double wordsToRememberInGame;
    private boolean plusHit;
    boolean verifyUser;

    public ModelIKnowThatWord(String userName){
        dictionary = new Dictionary();
        verifyUser = false;
        userNameGame = userName;
        modelUser = new ModelUser(userNameGame);
        if(modelUser.isUser()){
            passedLevels = modelUser.getUserLevels();
        } else {
            modelUser.newUser();
            verifyUser = true;
            passedLevels = 0;
        }
        hits=0;
        flag=0;
        flag2=0;
        setActualLevel();
        setWordsToTheLevel();
        setWordsOnLevel();
    }

    private void setActualLevel(){
        hits=0;
        if(passedLevels<10) {
            level = passedLevels + 1;
            setWordsOnLevel();
            wordsByGame = dictionary.getWordsByGame(wordsLevel/2);
            wordsToRemember = dictionary.getWordsToRemember(wordsLevel/2);
            wordsInLevel = new ArrayList<>();
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
            case 1, 2 -> wordsToRememberInGame=0.7;
            case 3-> wordsToRememberInGame=0.75;
            case 4, 5 -> wordsToRememberInGame=0.8;
            case 6-> wordsToRememberInGame=0.85;
            case 7, 8 -> wordsToRememberInGame=0.90;
            case 9-> wordsToRememberInGame=0.95;
            case 10->wordsToRememberInGame=1;
        }
    }

    private void setPassedLevels(){
        if(hits>=wordsLevel*wordsToRememberInGame){
            passedLevels= modelUser.setUserLevels();
            setActualLevel();
            flag=0;
            flag2=0;
        } else {
            flag=0;
            flag2=0;
            hits=0;
            setWordsOnLevel();
            word="";
            wordsByGame=dictionary.getWordsByGame(wordsLevel/2);
            wordsToRemember=dictionary.getWordsToRemember(wordsLevel/2);
            wordsInLevel= new ArrayList<>();
            setWordsToTheLevel();
        }
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

    private boolean isAWordToRemember(String word){
        boolean isWord= false;
        for (int i = 0; i < wordsToRemember.size() ; i++) {
            if (wordsToRemember.get(i).equals(word)){
                isWord=true;
                break;
            }
        }
        return isWord;
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



    public int getHits(){
       return hits;
    }

    public int getPassedLevels(){
        return passedLevels;
    }

    public int getLevel(){
        return level;
    }

    public int getHitsLevel(){
        setWordsToRememberInGame();
        return (int) Math.ceil(wordsLevel*wordsToRememberInGame);
    }

    public boolean newUser() {
        return verifyUser;
    }

}
