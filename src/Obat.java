import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

public class Obat implements Crud{
    File fileObat = new File("C:/users/USER/apotek-pbo/file/obat.txt");
    
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

    public void hapusObat()  {
        try {
            // database asli
            File obat = new File("C:/Users/USER/apotek-pbo/file/obat.txt");
            FileReader fileInput = new FileReader(obat);
            BufferedReader bufferedInput = new BufferedReader(fileInput);
            //database sementara
            File tempDB = new File("C:/Users/USER/apotek-pbo/file/tempdb.txt");
            FileWriter fileOutput = new FileWriter(tempDB);
            BufferedWriter bufferOutput = new BufferedWriter(fileOutput);
            //showing database 
            readObat();
            //ambil user input untuk delete
            Scanner terminalInput = new Scanner(System.in);
            System.out.print("\nMasukan nomor obat yang akan dihapus: ");
            int deleteNum = terminalInput.nextInt();
            //looping untuk membaca tiap data baris  dan menskip data yang akan dihapus 
            int entryCounts = 0;
            String data = bufferedInput.readLine();
            while (data != null){
                entryCounts++;
                boolean isDelete = false;
                StringTokenizer st = new StringTokenizer(data,",");
                //tampilkan data yang ingin dihapus
                if(deleteNum == entryCounts){
                    System.out.println("data yang ingin anda hapus adalah: ");
                    System.out.println("---------------------------------");
                    System.out.println("nomor obat       : " + st.nextToken());
                    System.out.println("nama obat       : " + st.nextToken());
                    System.out.println("exp date       : " + st.nextToken());
                    isDelete = getYesorNo("apakah anda ingin menghapus obat ini? ");
                }
            
                if (isDelete){
                    //skip pindahkan data dari original ke sementara
                    System.out.println("data berhasil dihapus");
                }else{
                    // pindahkan data dari original ke sementara
                    bufferOutput.write(data);
                    bufferOutput.newLine();
                }
                data = bufferedInput.readLine();
                }
                // menulis data ke file
                bufferOutput.flush();
                bufferOutput.close();
                fileOutput.close();
                bufferedInput.close();
                fileInput.close();

                System.gc();
            
                //delete original file 
                obat.delete();
                //rename file sementara ke database
                tempDB.renameTo(obat);
        }catch (Exception e) {
            e.printStackTrace();
        }   
    }

    public void updateObat() {
        try {
            // ambil file/database ori
            File obat = new File("C:/Users/USER/apotek-pbo/file/obat.txt");
            FileReader fileInput = new FileReader(obat);
            BufferedReader bufferedInput = new BufferedReader(fileInput);
        
            // buat database/file sementara
            File tempFile = new File("C:/Users/USER/apotek-pbo/file/tempObat.txt");
            FileWriter fileOutput = new FileWriter(tempFile);
            BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);
            
            // tampilkan data
            readObat();

            // ambil user input
            Scanner input = new Scanner(System.in);
            System.out.print("\nMasukkan nomor obat yang akan diupdate: ");
            int updateNum = input.nextInt();
            
            // tampilkan data yang akan diupdate
            String data = bufferedInput.readLine();
            int numList = 0;

            while(data != null) {
                numList++;

                StringTokenizer st = new StringTokenizer(data, ",");

                if(updateNum == numList) {
                    System.out.println("\nData yang akan diupdate adalah:");
                    System.out.println("----------------------------------");
                    System.out.println("Nama obat             :" + st.nextToken());
                    System.out.println("Stok obat             :" + st.nextToken());
                    System.out.println("Tanggal expire obat   :" + st.nextToken());

                // update datanya

                    // mengambil input dari admin
                    String[] fieldData = {"nama", "stok", "tanggal expire"};
                    String[] tempData = new String[3];

                    // refresh token
                    st = new StringTokenizer(data, ",");
                    
                    for (int i = 0; i < fieldData.length; i++) {
                        boolean isUpdate = getYesorNo("Apakah anda ingin mengubah "+ fieldData[i] +" obat?");
                        
                        String dataAsli = st.nextToken();
                        if(isUpdate) {
                            // admin input
                            input = new Scanner(System.in);
                            System.out.print("Masukkan "+ fieldData[i] +" baru: ");
                            tempData[i] = input.nextLine();
                        } else {
                            tempData[i] = dataAsli;
                        }
                    }
                    
                    // tampilkan data baru
                    st = new StringTokenizer(data, ",");
                    
                    System.out.println("\nData baru ");
                    System.out.println("----------------------------------");
                    System.out.println("Nama obat             :" + st.nextToken() + " -> " + tempData[0]);
                    System.out.println("Stok obat             :" + st.nextToken() + " -> " + tempData[1]);
                    System.out.println("Tanggal expire obat   :" + st.nextToken() + " -> " + tempData[2]);
                    
                    boolean isUpdate = getYesorNo("Apakah anda ingin menyimpan data baru tersebut");
                    
                    if(isUpdate) {
                        String namaObat = tempData[0];
                        String stok = tempData[1];
                        String tanggalExpire = tempData[2];

                        bufferedOutput.write(namaObat + "," + stok + "," + tanggalExpire);
                    } else {
                        // copy data
                        bufferedOutput.write(data);
                    }
                } else {
                    // copy datanya
                    bufferedOutput.write(data);
                    
                }
                bufferedOutput.newLine();

                data = bufferedInput.readLine();
            }
            
            // menulis data ke file
            bufferedOutput.flush();
            bufferedOutput.close();
            fileOutput.close();
            bufferedInput.close();
            fileInput.close();

            System.gc();

            // delete original file
            obat.delete();
            // rename tempObat menjadi obat.txt
            tempFile.renameTo(obat);

        } catch (Exception e) {
            e.printStackTrace();
        }

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
