import java.util.*;

public class Pembeli extends Obat {
    public void Menu() {
        Scanner inputUser = new Scanner(System.in);
        System.out.println("Selamat datang di Apotek Sauyunan\n");
        super.readObat();
        System.out.println("");
        System.out.print("Silahkan Masukan Angka Obat Yang Akan Dibeli : ");
        String pilihanUser = inputUser.next();
    }
}
