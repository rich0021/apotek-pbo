import java.util.*;
import java.io.*;

public class Auth {
    File fileUser = new File("C:/Users/MUTTAQIN/Documents/Java Project/apotek-pbo/file/user.txt");


    public Auth(){
        if (!this.fileUser.exists()) {
            try {
                if (!this.fileUser.createNewFile()) {
                    System.out.println("File Gagal Dibuat");
                    System.exit(1);
                }

            } catch (IOException e) {
                System.out.println("Terjadi Error.");
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public void login() {
        try {
            Scanner myReader = new Scanner(this.fileUser);
            Scanner inputReader = new Scanner(System.in);
            System.out.println("=======================");
            System.out.println("         Login         ");
            System.out.println("=======================");
            System.out.print("Masukan Username : ");
            String username = inputReader.next();
            System.out.print("Masukan Password : ");
            String password = inputReader.next();
            inputReader.close();
            while (myReader.hasNextLine()) {
                StringTokenizer data = new StringTokenizer(myReader.nextLine(), ",");
                if(username == data.nextToken() && password == data.nextToken()){
                    System.out.println("Masuk");
                    break;
                }else{
                    continue;
                }
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("Terjadi Error.");
            e.printStackTrace();
            System.exit(1);
        }
    }


    public void register() {
        try {
            Scanner inputReader = new Scanner(System.in);
            System.out.println("=======================");
            System.out.println("        Register       ");
            System.out.println("=======================");
            System.out.print("Masukan Username : ");
            String username = inputReader.next();
            System.out.print("Masukan Password : ");
            String password = inputReader.next();
            inputReader.close();
            FileWriter myWriter = new FileWriter("C:/Users/MUTTAQIN/Documents/Java Project/apotek-pbo/file/user.txt");
            myWriter.write(username+","+password);
        } catch (IOException e) {
            System.out.println("Terjadi Error.");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
