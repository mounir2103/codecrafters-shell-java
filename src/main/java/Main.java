import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        List<String> builtin = Arrays.asList("exit", "echo", "type", "pwd");

        while(true){
            System.out.print("$ ");
            String command = scanner.nextLine();

            if(command.equals("exit")){
                break;
            }
            else if(command.equals("pwd")){
                System.out.println(System.getProperty("user.dir"));
            }

            else if(command.startsWith("echo ")){
                System.out.println(command.substring(5));
            }

            else if(command.startsWith("type ")){
                String cmd = command.substring(5);

                if (builtin.contains(cmd)) {
                    System.out.println(cmd + " is a shell builtin");
                }
                else{
                    String pathEnv = System.getenv("PATH");
                    String[] dirs = pathEnv.split(File.pathSeparator);

                    boolean found = false;

                    for(String dir : dirs){
                        File file = new File(dir, cmd);
                        if(file.exists() && file.canExecute()){
                            System.out.println(cmd + " is " + file.getPath());
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        System.out.println(cmd + ": not found");
                    }
                }
            }
            
            else{
                String[] parts = command.split("\\s+");
                String cmd = parts[0];

                String pathEnv = System.getenv("PATH");
                String[] dirs = pathEnv.split(File.pathSeparator);

                boolean found = false;

                for(String dir : dirs){
                    File file = new File(dir, cmd);
                    if(file.exists() && file.canExecute()){
                        ProcessBuilder pb = new ProcessBuilder(parts);
                        pb.inheritIO();
                        Process process = pb.start();
                        process.waitFor();
                        found = true;
                        break;
                    }
                }
                if(!found){
                    System.out.println(cmd + ": not found");
                }
                
            }
        }
        
    }
}
