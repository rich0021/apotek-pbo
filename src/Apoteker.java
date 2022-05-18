import java.util.Scanner;

public class Apoteker extends Obat {
    
    public void menu() {
        Scanner input = new Scanner(System.in);
        boolean lanjutkan = true;
        
        while(lanjutkan) {
            System.out.println("=============================");
            System.out.println("Halaman admin apotek Sauyunan");
            System.out.println("=============================\n");
            System.out.println("[1] Tambah Obat");
            System.out.println("[2] Hapus Obat");
            System.out.println("[3] Update Obat");
            System.out.println("[4] Keluar");
            System.out.print("Masukkan input anda: ");
            String inputAdmin = input.next();
    
            switch (inputAdmin) {
                case "1":
                    // tampil menu tambah obat
                    System.out.println("================");
                    System.out.println("Tambah data obat");
                    System.out.println("================");
                    super.tambahObat();
                    break;
                case "2":
                    // tampil menu hapus obat
                    break;
                case "3":
                    // tampil menu update obat
                    System.out.println("================");
                    System.out.println("Update data obat");
                    System.out.println("================");
                    super.updateObat();
                    break;
                case "4":
                    // program beres / masuk ke program utama
                    System.exit(0);
                    break;
                default:
                    System.err.println("Input tidak valid");
            }
            
            lanjutkan = super.getYesorNo("Apakah anda ingin melanjutkan");
        }
    }
}
