package src;
import java.util.Scanner;

//import src.AskAIAssistant;
public class pro1 
{
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        System.out.println("*****************************");

        System.out.println("    AI Calculator   ");

        System.out.println("*****************************");

        System.out.println("Ask a question");

        String userQuestion = input.nextLine();

        System.out.println("*****************************");

        pro2.ask(userQuestion);

        input.close();
    }
    
}
