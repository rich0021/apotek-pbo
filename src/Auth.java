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

    public void login() throws IOException {
        Scanner myReader = new Scanner(this.fileUser);
        Scanner inputReader = new Scanner(System.in);
        try {
            System.out.println("=======================");
            System.out.println("         Login         ");
            System.out.println("=======================");
            System.out.print("Masukan Username : ");
            String username = inputReader.nextLine();
            System.out.print("Masukan Password : ");
            String password = inputReader.nextLine();
            System.out.println("");
            while (myReader.hasNextLine()) {
                StringTokenizer data = new StringTokenizer(myReader.nextLine(), ",");
                String name = data.nextToken();
                String pw = data.nextToken();
                String role = data.nextToken().toLowerCase();
                if(username.equals(name) && password.equals(pw)){
                    if (role.equals("user")) {
                        Pembeli user = new Pembeli();
                        user.Menu();
                    }else{
                        System.out.println("Kamu Admin");
                        break;
                    }
                }else{
                    if (!myReader.hasNextLine()) {
                        System.out.println("\nPassword Salah, Harap Cobalagi\n");
                        this.login();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Terjadi Error.");
            e.printStackTrace();
            System.exit(1);
        }finally {
            myReader.close();
            inputReader.close();
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
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Terjadi Error.");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
