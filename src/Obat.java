import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

public class Obat implements Crud{
    File fileObat = new File("C:/Users/USER/apotek-pbo/file/obat.txt");
    ArrayList<String> arrayObat = new ArrayList<String>();
    
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

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
            System.out.println("| no |\tnama obat\t|\tstok\t|");
            int nomor = 1;
            while (myReader.hasNextLine()) {
                StringTokenizer data = new StringTokenizer(myReader.nextLine(), ",");
                System.out.println("  " + nomor + "\t" + data.nextToken() + "\t\t" + data.nextToken());
                nomor++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void tambahObat() {
        try {
            FileWriter fileOutput = new FileWriter("C:/Users/USER/apotek-pbo/file/obat.txt", true);
            BufferedWriter bufferOutput = new BufferedWriter(fileOutput);
            
            Scanner input = new Scanner(System.in);
            String namaObat, stok;
            String tanggalExpire = formatter.format(date);

            System.out.print("Masukkan nama obat: ");
            namaObat = input.nextLine();
            System.out.print("Masukkan stok obat: ");
            stok = input.nextLine();
            System.out.print("Masukkan tanggal expire obat, format(dd-mm-yy): ");
            tanggalExpire = getTahun();
            
            System.out.println("\nData yang akan dimasukkan");
            System.out.println("-------------------------");
            System.out.println("Nama obat : " + namaObat);
            System.out.println("Stok obat : " + stok);
            System.out.println("tanggal expire : " + tanggalExpire);

            boolean tambah = getYesorNo("Apakah anda ingin memasukkan data tersebut?");

            if(tambah) {
                bufferOutput.write(namaObat + "," + stok + "," + tanggalExpire);
                bufferOutput.newLine();
                bufferOutput.flush();
                bufferOutput.close();

                System.out.println("Data berhasil ditambahkan");
                System.out.println("---------");
                readObat();
            }
            
            bufferOutput.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String hapusObat() {
        return "b";
    }

    public String updateObat() {
        return "c";
    }

    public boolean getYesorNo(String message){
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("\n"+message+" (y/n)? ");
        String pilihanAdmin = terminalInput.next();

        while(!pilihanAdmin.equalsIgnoreCase("y") && !pilihanAdmin.equalsIgnoreCase("n")) {
            System.err.println("Pilihan anda bukan y atau n");
            System.out.print("\n"+message+" (y/n)? ");
            pilihanAdmin = terminalInput.next();
        }

        return pilihanAdmin.equalsIgnoreCase("y");
    }

    public String getTahun() {
        boolean tahunValid = false;
        Scanner input = new Scanner(System.in);
        String inputTahun = input.nextLine();

            while(!tahunValid) {
                try {
                    formatter.parse(inputTahun);
                    tahunValid = true;
                } catch (Exception e) {
                    System.err.println("Input tidak sesuai format, format(dd-mm-yy)");
                    System.out.print("Masukkan tanggal expire obat lagi:");
                    inputTahun = input.nextLine();
                }
            }

        return inputTahun;
    }
}
