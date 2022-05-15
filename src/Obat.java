import java.util.*;
import java.io.*;

public class Obat implements Crud{
    File fileObat = new File("C:/Users/MUTTAQIN/Documents/Java Project/apotek-pbo/file/obat.txt");
    ArrayList<String> arrayObat = new ArrayList<String>();
    
    public void verify(){
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
            int nomor = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(nomor +". "+data);
                nomor++;
            }
            myReader.close();
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
