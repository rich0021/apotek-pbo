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
            String username = inputReader.nextLine();
            System.out.print("Masukan Password : ");
            String password = inputReader.nextLine();
            System.out.println("");
            if (myReader.hasNextLine()) {
                while (myReader.hasNextLine()) {
                    StringTokenizer data = new StringTokenizer(myReader.nextLine(), ",");
                    String name = data.nextToken();
                    String pw = data.nextToken();
                    String role = data.nextToken().toLowerCase();
                    if(username.equals(name) && password.equals(pw)){
                        if (role.equals("user")) {
                            myReader.close();
                            Pembeli user = new Pembeli();
                            user.Menu();
                            break;
                        }else{
                            Apoteker admin = new Apoteker();
                            admin.menu();
                            break;
                        }
                    }else{
                        if (!myReader.hasNextLine()) {
                            while (true) {
                                System.out.println("\nPassword Salah/Belum Registrasi, Harap Cobalagi\n");
                                System.out.println("1. Coba Login Lagi\n");
                                System.out.println("2. Registrasi\n");

                                int input = inputReader.nextInt();
                                if(input == 1){
                                    this.login();
                                    break;
                                }else if(input == 2){
                                    this.register();
                                    break;
                                }else{
                                    System.out.println("Masukan Input Yang Benar!!!");
                                }
                            }
                        }
                    }
                }
            }else{
                System.out.println("Akun Tidak Ada, Harus Register Dulu\n");
                this.register();
            }
        } catch (IOException e) {
            System.out.println("Terjadi Error.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void register() {
        try {
            Scanner inputReader = new Scanner(System.in);
            FileOutputStream myWriter = new FileOutputStream("C:/Users/MUTTAQIN/Documents/Java Project/apotek-pbo/file/user.txt", true);    
            System.out.println("=======================");
            System.out.println("        Register       ");
            System.out.println("=======================");
            System.out.print("Masukan Username : ");
            String username = inputReader.next();
            System.out.print("Masukan Password : ");
            String password = inputReader.next();
            String string = username+","+password+",user"+"\n";
            if (username.contains(",")) {
                String[] split = username.split(",");
                if (split[1].equals("admin")) {
                    string = split[0]+","+password+",admin"+"\n";
                }
            }
            myWriter.write(string.getBytes());
            myWriter.close();
            this.login();
        } catch (IOException e) {
            System.out.println("Terjadi Error.");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
