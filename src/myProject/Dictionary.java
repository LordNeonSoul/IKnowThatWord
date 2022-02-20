package myProject;

import java.util.ArrayList;
import java.util.Random;

public class Dictionary {
    private FileManager fileManager;
    private ArrayList<String> dictionary = new ArrayList<String>();
    private ArrayList<String> wordsToRemember = new ArrayList<String>();
    private ArrayList<String> wordsByGame = new ArrayList<String>();

    public Dictionary() {
        fileManager = new FileManager();
        dictionary = fileManager.lecturaFile();
    }

    public ArrayList<String> getWordsToRemember(int wordsQuantity) {
        if (wordsToRemember.size()>0){
            wordsToRemember= new ArrayList<>();
        }
        for (int i = 0; i < wordsQuantity; i++) {
            Random random = new Random();
            int dictionaryRandomIndex = random.nextInt(dictionary.size());
            wordsToRemember.add(dictionary.get(dictionaryRandomIndex));
            dictionary.remove(dictionaryRandomIndex);
        }
        return wordsToRemember;
    }

    public ArrayList<String> getWordsByGame(int wordsQuantity) {
        if (wordsByGame.size()>0){
            wordsByGame= new ArrayList<>();
        }
        for (int i = 0; i < wordsQuantity; i++) {
            Random random = new Random();
            int dictionaryRandomIndex = random.nextInt(dictionary.size());
            wordsByGame.add(dictionary.get(dictionaryRandomIndex));
            dictionary.remove(dictionaryRandomIndex);
        }
        return wordsByGame;
    }

}