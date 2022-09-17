import java.io.*;
import java.util.ArrayList;

public class RandomAccess {
    public static String randomAcess(String fic,int debut,int fin,int nbreCharLignes,String deli) throws IOException {
        File fichier = new File(fic);
        String data = "";

        ArrayList<String> dialogLinesRead = new ArrayList<>();
        int byteParLigne = nbreCharLignes+2;

        try {
            RandomAccessFile raf = new RandomAccessFile(fichier, "rw");
            for(int i=debut;i<fin;i++){
                raf.seek(byteParLigne*i);
                data = raf.readLine();
                //data = String.valueOf(raf.readInt());
                //data += String.valueOf(raf.readUTF());
                //data += String.valueOf(raf.readUTF());
                //data += String.valueOf(raf.readDouble());
                dialogLinesRead.add(data);

            }
            raf.close();
        } catch (FileNotFoundException e) {
            System.out.println("erreur");
        }
        String returndata ="";
        for(String str:dialogLinesRead){
            returndata += str;
            returndata += deli;
        }
        return returndata;
    }
    public static void main(String[] args) throws IOException {
        String fichier = "emp.bin";
        String delimiter = "/////";
        String dialog = randomAcess(fichier,0,4,30,delimiter);
        System.out.println(dialog);
            
    }

    
    
}
