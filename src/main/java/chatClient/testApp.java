package chatClient;


import java.io.IOException;
import java.util.Scanner;

public class testApp {
    static public okClient client = new okClient();
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
