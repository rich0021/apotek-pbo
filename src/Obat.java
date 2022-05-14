import java.util.Scanner;

public abstract class Obat {
    
    public void tampilkan() {
        boolean lanjutkan = true;
        String pilihanUser;
        
        Scanner input = new Scanner(System.in);
        
        while(lanjutkan) {
            System.out.println("Selamat datang di Apotek Sauyunan\n");
            System.out.println("1.\tLihat obat yang tersedia");
            System.out.println("2.\tBeli data obat");
            
            System.out.print("\n\nPilihan anda: ");
            pilihanUser = input.next();

            switch (pilihanUser) {
                case "1":
                    System.out.println("\n===============");
                    System.out.println("List seluruh obat");
                    System.out.println("=================");
                    break;
                case "2":
                    System.out.println("\n===============");
                    System.out.println("Beli obat");
                    System.out.println("=================");
                    break;
                default:
                    System.err.println("\nInput salah\nSilakan masukkan input yang tersedia [1-2]");
            }
            System.out.print("Apakah anda ingin melanjutkan (y/n)? ");
            pilihanUser = input.next();

            if(pilihanUser.equalsIgnoreCase("n")) {
                System.exit(0);
            }
        }
    }
}
