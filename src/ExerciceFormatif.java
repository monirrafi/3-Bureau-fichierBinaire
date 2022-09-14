/*
 * clé int 4 octets nom et prénom 20+20=40 salaire double 8 octets
final int TAILLE_ENREG = 52;
//Appel
fic.seek(getAdresse(clé));
public long getAdresse(int cle) {
long adr = (cle/100-1)* TAILLE_ENREG;
return adr;
}
Exemple : Entrer le numéro de l’employé : 400
adr = (400/100)-1 =3*52=156
Donc fic.seek(156);
 */
import java.io.* ;
public class ExerciceFormatif {

    static final int TAILLE_ENREG = 52;

    public static long getAdresse(int cle) {
        long adr = (cle/100-1)* TAILLE_ENREG;
        return adr;
    }

    public static void main(String[] args) throws IOException
    {

        File fichier = new File("employe.bin") ;

            RandomAccessFile donnee = new RandomAccessFile(fichier, "rw") ;
            int cle = 0 ;
            //String nom="" ;
            //String prenom="" ;
            //int compteur = 1 ;
            //double newSalaire = 0 ;
            //boolean sortie = false ;
            long adr =0;

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in)) ;
            //saisir
            System.out.println() ;   
            adr = getAdresse(cle);
            donnee.seek(adr++);
            
            donnee.writeInt(cle) ;
            donnee.writeChar(' ') ;
            System.out.println("Entrez le nom du nouvel employe") ;
            donnee.writeUTF(in.readLine()) ;
            donnee.writeChar(' ') ;
            System.out.println("Entrez le prenom du nouvel employe") ;
            donnee.writeUTF(in.readLine()) ;
            donnee.writeChar(' ') ;
            System.out.println("Entrez le salaire du nouvel employe") ;
            donnee.writeDouble(Double.parseDouble(in.readLine())) ;
            donnee.close();
            
            //afficher
            System.out.println() ;  
//            donnee.seek(0) ;
            
            for (int i = 0 ; i < donnee.length() ; i++)
                {
                try
                    {
                        System.out.print(donnee.readInt()) ;
                        System.out.print(donnee.readChar()) ;
                        System.out.print(donnee.readUTF()) ;
                        System.out.print(donnee.readChar()) ;
                        System.out.print(donnee.readUTF()) ;
                        System.out.print(donnee.readChar()) ;
                        System.out.print(donnee.readDouble()) ;
                        System.out.print(donnee.readChar()) ;                   
                    }
                catch(EOFException e)
                    {}
                }
                                
            System.out.println() ;
                           
            
    }
}
