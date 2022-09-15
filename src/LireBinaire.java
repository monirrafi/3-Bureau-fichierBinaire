import java.io.*;
 
public class LireBinaire {
    
    public static void main(String args[]) {
        
        DataInputStream in;
        int nombre;
 
        try {
            RandomAccessFile donnee = new RandomAccessFile("nombres2.bin", "rw") ;
            //in = new DataInputStream(
              //      new BufferedInputStream(
                //        new FileInputStream("nombres2.bin")));
 
            //while (true) {
                //nombre = donnee.readInt();
                //System.out.print(nombre + ", ");
            for(int i=0;i<donnee.length();i++){
                System.out.print(donnee.readInt());
                System.out.print(donnee.readChar());
                System.out.print(donnee.readUTF());
            }
        } catch (EOFException eof) {
            // fin normale de la lecture
            System.out.println();
        } catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        }
    }
}