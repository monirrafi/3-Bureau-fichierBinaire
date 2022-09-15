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
            int cle = 100 ;
            long adr;
            boolean sortie=false;
            int choix=0;

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in)) ;
            //saisir
            System.out.println() ;   
            donnee.seek(getAdresse(cle));
            
            donnee.writeLong(getAdresse(cle)); 
            System.out.println("Entrez le nom du nouvel employe") ;
            donnee.writeUTF(in.readLine()) ;
            System.out.println("Entrez le prenom du nouvel employe") ;
            donnee.writeUTF(in.readLine()) ;
            
            
            do
            {       
            do
                {
                try
                    {
                    System.out.println("Menu") ;
                    System.out.println("====\n") ;
                    System.out.println("1. Afficher les donnees") ;
                    System.out.println("2. Ajouter un employe") ;
                    System.out.println("3. Quitter") ;      
                    choix = Integer.parseInt(in.readLine()) ;
                    }
                catch(NumberFormatException e)
                    {}
                }
            while(choix < 1 || choix > 3) ;

            switch(choix)
                {
                case 1 :
                    {
                    System.out.println() ;  
                        
                    donnee.seek(0) ;
                    
                    for (int i = 0 ; i*52 < donnee.length() ; i++)
                        {
                        try
                            {
                                System.out.print(donnee.readLong()) ;
                                System.out.print(donnee.readUTF()) ;
                                System.out.print(donnee.readUTF()) ;
                            }
                        catch(EOFException e)
                            {}
                        }
                                        
                    System.out.println() ;
                    }               
                break ;

 
    
                case 2 :
                    {
                    System.out.println() ;  
                        
                    donnee.seek(getAdresse(cle)) ;
                    cle += 100 ;
                    
                    try
                        {
                            donnee.writeLong(getAdresse(cle));
                            System.out.println("Entrez le nom du nouvel employe") ;
                            donnee.writeUTF(in.readLine()) ;
                            System.out.println("Entrez le prenom du nouvel employe") ;
                            donnee.writeUTF(in.readLine()) ;
                        }
                    catch(EOFException e)
                        {}
                                        
                    System.out.println() ;
                    }               
                break ;

                case 3 :
                
                case 5 :
                    {
                    System.out.println() ;  
                    sortie = true ; 
                    }               
                break ; 
                
                }
            }
        while(sortie != true) ;
    
            donnee.close();
        System.exit(0) ;    
    }

}
