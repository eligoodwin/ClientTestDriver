package chatClient;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    //TODO: make array dynamic size
    private ArrayList<String> menuOptions = new ArrayList<String>();
    public int getChoice(){
        int result = 0;
        //display menu
        for (int i = 0; i < menuOptions.size(); i++){
            System.out.println(i + ": " + menuOptions.get(i));
        }
        //get user input
        Scanner scanner = new Scanner( System.in );
        String in = scanner.nextLine();
        result = Integer.parseInt( in );
        //TODO: validate input
        return result;
    }
    public void addOption(String option){
        menuOptions.add(option);
    }
}
