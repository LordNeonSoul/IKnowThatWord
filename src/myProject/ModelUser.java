package myProject;

import java.util.ArrayList;

public class ModelUser {
    private FileManager fileManager;
    private ArrayList<String> registeredUsers;
    private String userName;

    public ModelUser (String nameTag){
        fileManager = new FileManager();
        registeredUsers = new ArrayList<String>();
        registeredUsers = fileManager.usersReader();
        userName = nameTag;
    }

    private int searchUser(){
        int position=-1;
        int i = 0;
        for ( i = 0; i < registeredUsers.size()&&registeredUsers.get(i).length()!=0;i++) {
            String thisUser = registeredUsers.get(i).substring(0, registeredUsers.get(i).lastIndexOf("-"));
            if (thisUser.equals(userName)){
                position=i;
                break;
            }
            i++;
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

    public int setUserLevels(){
        if(getUserLevels()<10){
            fileManager.writeLevel(getUserLevels()+1,searchUser());
        }else{
            fileManager.writeLevel(0,searchUser());
        }
        return getUserLevels();
    }



    public int getUserLevels(){
        String user = registeredUsers.get(searchUser());
        int levels = Integer.parseInt(user.substring(user.lastIndexOf("-")+1));
        return levels;
    }

    public void newUser() {
        fileManager.writeUsers(userName+"-"+0);
        registeredUsers.add(userName+"-"+0);
    }

}
