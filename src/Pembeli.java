import java.util.*;

public class Pembeli extends Obat {
    public void Menu() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Selamat datang di Apotek Sauyunan\n");
        System.out.println("=================");
        System.out.println("List Seluruh Obat");
        System.out.println("=================\n");
        super.readObat();
        System.out.println("");
        System.out.print("Silahkan Masukan Angka Obat Yang Akan Dibeli : ");
        String pilihanUser = input.next();
        
    }
}
