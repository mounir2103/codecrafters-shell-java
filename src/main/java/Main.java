import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String builtin = "echoexittype";

        while(true){
            System.out.print("$ ");
            String command = scanner.nextLine();
            if(command.equals("exit")){
                break;
            }else if(command.startsWith("echo ")){
                System.out.println(command.substring(5));
            }else if(command.startsWith("type ")){
                if (builtin.contains(command.substring(5))) {
                    System.out.println(command.substring(5) + " is a shell builtin");
                }else{
                    System.out.println(command.substring(5) + " command not found");
                }
            }else{
                System.out.println(command + ": command not found");
            }
        }
        
    }
}
