package QueryObjects;
//source: https://github.com/google/gson/blob/master/UserGuide.md

import QueryObjects.UserData;
import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

public class DataObjectHelper {
    private static Scanner scanner = new Scanner( System.in );
    private static Gson gson = new Gson();
    private static PrintWriter out;

    public static UserData createUser(){
        UserData user = new UserData();
        //TODO: add checks to these fields
        System.out.println("Please enter a user name: ");
        String in = scanner.nextLine();
        user.username = in;

        System.out.println("Please enter a password: ");
        in = scanner.nextLine();
        user.password = in;

        System.out.println("Please enter an email: ");
        in = scanner.nextLine();
        user.email = in;
        //TODO: add IP address

        return user;
    }

    public static int saveUser(UserData user){
        String fileName = user.username + ".json";
        try {
            out = new PrintWriter(fileName);
            String json = gson.toJson(user);
            out.write(json);
            out.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File, " + fileName + ", could not be opened.");
            return 1;
        }
        return 0;
    }

    public static UserData loadUser(String username){
        String fileName = "./" + username + ".json";
        File file = new File(fileName);
        String json = "";
        //source:https://stackoverflow.com/questions/5868369/how-to-read-a-large-text-file-line-by-line-using-java
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                json = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
            return null;
        }
        UserData user = gson.fromJson(json, UserData.class);
        return user;
    }
}
