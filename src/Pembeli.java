import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Pembeli extends Obat {
    public void Menu() {
        try {
            File obat = new File("C:/Users/USER/apotek-pbo/file/obat.txt");
            FileReader fileInput = new FileReader(obat);
            BufferedReader bufferInput = new BufferedReader(fileInput);
            
            Scanner inputUser = new Scanner(System.in);

            while(true) {
                System.out.println("Selamat datang di Apotek Sauyunan\n");
                super.readObat();
                System.out.println("\nKetik 0 untuk keluar dari program");
                System.out.println("");
                System.out.print("Silahkan Masukan Angka Obat Yang Akan Dibeli : ");
                int pilihanUser = inputUser.nextInt();

                String data = bufferInput.readLine();
                int numList = 0;

                while(data != null) {
                    numList++;
                    
                    StringTokenizer st = new StringTokenizer(data, ",");

                    if(pilihanUser == numList) {
                        System.out.println("\nObat yang akan dibeli adalah:");
                        System.out.println("----------------------------------");
                        System.out.println("Nama obat             :" + st.nextToken());
                        System.out.println("Stok obat             :" + st.nextToken());
                        System.out.println("Tanggal expire obat   :" + st.nextToken());

                        boolean isBeli = getYesorNo("Apakah anda ingin membeli obat ini?");

                        if(isBeli) {
                            System.out.println("Obat dibeli");
                            getYesorNo("Apakah anda masih ingin membeli obat?");
                        } else {
                            System.out.println("Gajadi beli");
                            System.exit(0);
                            
                            fileInput.close();
                            bufferInput.close();
                        }
                    }
                    data = bufferInput.readLine();
                }

                if(pilihanUser == 0) {
                    System.exit(0);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
