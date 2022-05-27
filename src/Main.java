import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner username = new Scanner(System.in);
        Auth auth = new Auth();
        while(true){
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.print("Masukan Input : ");
            int input = username.nextInt();
            if (input == 1) {
                auth.login();
            }else if(input == 2){
                auth.register();
            }else{
                System.out.println("Masukan Pilihan Yang Benar");
            }
        }
    }
}
