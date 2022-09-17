import java.io.* ;
public class ExerciceFormatif {

    static final int TAILLE_ENREG = 52;

    public static long getAdresse(int cle) {
        long adr = (cle/100-1)* TAILLE_ENREG;
        return adr;
    }

    public static void main(String[] args) throws IOException
    {
        File fichier = new File("emp.bin") ;

        RandomAccessFile donnee = new RandomAccessFile(fichier, "rw") ;
        int choix = 0 ;
        int cle = 100 ;
        boolean sortie = false ;
        
        donnee.seek(getAdresse(cle)) ;
        donnee.writeInt(cle) ;
        donnee.writeUTF("Tavares") ;
        donnee.writeUTF("Antonio") ;
        donnee.writeDouble(5500.00) ;
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)) ;
        do
            {       
            do
                {
                try
                    {
                    System.out.println("===========================================================") ;  
                    System.out.println("                        Menu") ;
                    System.out.println("===========================================================") ;  
                    System.out.println("1. Afficher les donnees") ;
                    System.out.println("2. Trouver une adresse par un cle") ;
                    System.out.println("3. Ajouter un employe") ;
                    System.out.println("4. Quitter") ;      
                    System.out.println("===========================================================") ;  
                    System.out.print("Entrez votre choix : ") ;
                    choix = Integer.parseInt(in.readLine()) ;
                    }
                catch(NumberFormatException e)
                    {}
                }
            while(choix < 1 || choix > 4) ;

            switch(choix)
                {
                case 1 :
                    {
                    System.out.println() ;  
                    
                    for (int i = 0 ; i < donnee.length() ; i++)
                        {
                        try
                            {
                                donnee.seek(i*52);
                                System.out.print(donnee.readInt()) ;
                                System.out.print(" ") ;
                                System.out.print(donnee.readUTF()) ;
                                System.out.print(" ") ;
                                System.out.print(donnee.readUTF()) ;
                                System.out.print(" ") ;
                                System.out.print(donnee.readDouble()) ;
                                System.out.print("\n") ;
                            }
                        catch(EOFException e)
                            {}
                        }
                                        
                    System.out.println() ;
                    }               
                break ;

 
    
                case 2 :
                    {
                    System.out.println("===========================================================") ;  
                    System.out.print("Entrez le cle : ") ;
                    int choixCle = Integer.parseInt(in.readLine());
                    
                    System.out.println("===========================================================") ;  
                    System.out.println("L'adresse de " + choixCle + " est " + getAdresse(choixCle)) ;
                    System.out.println("===========================================================") ;  
                }               
                break ;
    
                case 3 :
                    {
                    System.out.println() ;  
                    cle += 100 ;
                    donnee.seek(getAdresse(cle)) ;
                    
                    try
                        {
                            donnee.writeInt(cle) ;
                            System.out.println("Entrez le nom du nouvel employe") ;
                            donnee.writeUTF(in.readLine()) ;
                            System.out.println("Entrez le prenom du nouvel employe") ;
                            donnee.writeUTF(in.readLine()) ;
                            System.out.println("Entrez le salaire du nouvel employe") ;
                            donnee.writeDouble(Double.parseDouble(in.readLine())) ;
                        }
                    catch(EOFException e)
                        {}
                                        
                    System.out.println() ;
                    System.out.println(getAdresse(cle));
                    }               
                break ;

                case 4 :
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
