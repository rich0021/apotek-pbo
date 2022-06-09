import java.io.File;
import java.util.*;

public class Pembeli extends Obat {
    File obat = new File("C:/Users/MUTTAQIN/Documents/Java Project/apotek-pbo/file/obat.txt");
    
    public void Menu() {
        try {
            Scanner inputUser = new Scanner(System.in);
            Scanner myreader = new Scanner(this.obat);

            int numList = 0;

            while(true) {
                System.out.println("Selamat datang di Apotek Sauyunan\n");
                super.readObat();
                System.out.println("\nKetik 0 untuk keluar dari program");
                System.out.println("");
                System.out.print("Silahkan Masukan Angka Obat Yang Akan Dibeli : ");
                int pilihanUser = inputUser.nextInt();
                
                while(myreader.hasNextLine()) {
                    numList++;
                    String data = myreader.nextLine();
                    String[] split = data.split(",");
                    if(pilihanUser == numList) {
                        System.out.println("\nObat yang akan dibeli adalah:");
                        System.out.println("----------------------------------");
                        System.out.println("Nama obat             :" + split[0]);
                        System.out.println("Stok obat             :" + split[1]);
                        System.out.println("Tanggal expire obat   :" + split[2]);

                        boolean isBeli = getYesorNo("Apakah anda ingin membeli obat ini?");

                        if(isBeli) {
                            if (ValidasiStok(split[0])) {
                                System.out.println("\nObat dibeli");
                                numList = 0;
                                break;
                            }else{
                                System.out.println("\nObat Habis");
                                numList = 0;
                                break;
                            }
                        } else {
                            System.out.println("Gajadi beli");
                            myreader.close();
                            System.exit(0);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
