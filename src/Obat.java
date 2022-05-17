import java.util.*;
import java.io.*;

public class Obat implements Crud{
    File fileObat = new File("C:/Users/MUTTAQIN/Documents/Java Project/apotek-pbo/file/obat.txt");
    ArrayList<String> arrayObat = new ArrayList<String>();
    

    public Obat(){
        if (!this.fileObat.exists()) {
            try {
                if (!this.fileObat.createNewFile()) {
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

    public void readObat() {
        try {
            Scanner myReader = new Scanner(this.fileObat);
            System.out.println("=====================================");
            System.out.println("| no |       Nama Obat       | stok |");
            System.out.println("=====================================");
            int nomor = 1;
            while (myReader.hasNextLine()) {
                StringTokenizer data = new StringTokenizer(myReader.nextLine(), ",");
                System.out.printf("| %-2d |", nomor);
                System.out.printf(" %-21s ", data.nextToken());
                System.out.printf("| %-4s |\n", data.nextToken());
                nomor++;
            }
            myReader.close();
            System.out.println("=====================================");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String tambahObat() {
        return "a";
    }

    public String hapusObat() {
        return "b";
    }

    public String updateObat() {
        return "c";
    }
}
