package chatClient;


import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

public class testApp {
    static public OkClient client = new OkClient();
    public static void interpretChoice(int choice){
        Scanner scanner = new Scanner( System.in );
        String in = "";
        switch(choice){
            case 0:
                //TODO: verify URL
                System.out.println("Enter the URL: ");
                in = scanner.nextLine();
                client.setURL(in);
                break;
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
                break;
            case 2:
                System.out.println("Exitting...");
                break;
        }

    }
    public static void main(String [] args){
        //*****Test Gson and file storage *****//
        //TODO: encapsulate some of the below functionality in a class
        //source: https://github.com/google/gson/blob/master/UserGuide.md
        Gson gson = new Gson();
        String json = "{\"token\":12345,\"issued\":\"01-01-00\",\"expires\":\"02-01-00\"}";
        TestToken token = gson.fromJson(json, TestToken.class);
        System.out.println("\n\n### token 1 ###");
        System.out.println(token.token);
        System.out.println(token.issued);
        System.out.println(token.expires);
        String json2 = gson.toJson(token);
        try {
            PrintWriter out = new PrintWriter("testToken.json");
            out.write(json2);
            out.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        String json3 = "";
        File file = new File("./testToken.json");
        //source:https://stackoverflow.com/questions/5868369/how-to-read-a-large-text-file-line-by-line-using-java
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                json3 = br.readLine();
            }
             catch (IOException e) {
                e.printStackTrace();
             }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        System.out.println("\n\nFile read in: ");
        System.out.println(json3);
        TestToken token2 = gson.fromJson(json, TestToken.class);
        System.out.println("\n\n### token 2 ###");
        System.out.println(token2.token);
        System.out.println(token2.issued);
        System.out.println(token2.expires);



        int choice = 0;
        Menu menu = new Menu();
        menu.addOption("Specify URL");
        menu.addOption("Send Message");
        menu.addOption("Quit");

        while (choice != 2){
            choice = menu.getChoice();
            interpretChoice(choice);
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
