import java.io.*;
 
public class EcrireBinaire {
    
    public static void main(String args[]) {
        DataOutputStream out;
        int nombre=0;
        
 
        try {
            out = new DataOutputStream(
                    new BufferedOutputStream(
                        new FileOutputStream("nombres2.bin")));
 
            for (int i = 0; i < 5; i++) {
                //nombre = (int)(Math.random() * 10 + 1);
                nombre++;
                out.writeInt( nombre );
                out.writeUTF(" ");
                out.writeUTF("monir\n");
            } 
 
            out.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        }
    }
}