package chatClient;


import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

import static chatClient.DataObjectHelper.createUser;

public class testApp {
    static public OkClient client = new OkClient();
    public static Gson gson = new Gson();
    private static String token = "";
    private static UserData user = new UserData();
    //Returns 1 if option is to quit or 0 otherwise
    public static int interpretChoice(int choice){
        Scanner scanner = new Scanner( System.in );
        String in = "";
        switch(choice){
            case 0:
                //TODO: verify URL
                System.out.println("Enter the URL: ");
                in = scanner.nextLine();
                client.setURL(in);
                return 0;
            case 1:
                System.out.println("Enter message to send: ");
                in = scanner.nextLine();
                try {
                    String res = client.sendPost(in);
                    System.out.print(res);
                }
                catch(IOException e){
                    e.printStackTrace();
                }
                return 0;
            case 2:
                user = createUser();
                try {
                    in = client.addUser(user);
                    System.out.println(in);
                    UserData testUser = gson.fromJson(in, UserData.class);
                    System.out.println("UserData token:");
                    System.out.println(testUser.token);
                    user.token = testUser.token;
                }
                catch(IOException e){
                    e.printStackTrace();
                }
                return 0;
            case 3:
                if (user != null){
                    DataObjectHelper.saveUser(user);
                }
                return 0;
            case 4:
                System.out.println("Enter username to load:");
                in = scanner.nextLine();
                UserData testUser = DataObjectHelper.loadUser(in);
                if (testUser == null){
                    System.out.println("Could not load user.");
                    return 0;
                }
                System.out.println("User data loaded:");
                System.out.println(gson.toJson(testUser));
                user = testUser;
                return 0;
            case 5:
                try {
                    String res = client.checkToken(user);
                    System.out.println("Token check response: ");
                    System.out.println(res);
                }
                catch(IOException e){
                    e.printStackTrace();
                }
                return 0;
            case 6:
                String jwt = JWTManager.testJWT();
                System.out.println("JWT out: ");
                try {
                    System.out.println(client.testJWT(jwt));
                }
                catch(IOException e){
                    e.printStackTrace();
                }
                return 0;
            default:
                System.out.println("Exitting...");
                return 1;
        }

    }

    public static void main(String [] args){
        //*****Test Gson and file storage *****//
        //TODO: encapsulate some of the below functionality in a class
        //source: https://github.com/google/gson/blob/master/UserGuide.md

        int choice = 0;
        Menu menu = new Menu();
        menu.addOption("Specify URL"); //0
        menu.addOption("Send Message"); //1
        menu.addOption("Create New User");
        menu.addOption("Save Current User");
        menu.addOption("Load User");
        menu.addOption("Check Token"); //5
        menu.addOption("Test JWT"); //6
        menu.addOption("Quit");

        int quit = 0;

        while (quit == 0){
            choice = menu.getChoice();
            quit = interpretChoice(choice);
        }

        /* Test sendGet
        String res = "";
        client.setURL("http://httpbin.org/");
        try {
            res = client.sendGet();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(res);
        */
    }

}
