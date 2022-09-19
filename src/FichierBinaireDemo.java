/*********************************************************

    Sur les fichiers  a accès aléatoire

/********************************************************/

import java.io.* ;
//import java.util.* ;

public class FichierBinaireDemo
    {
        static final int TAILLE_ENREG = 52;

        public static long getAdresse(int cle) {
            long adr = (cle/100-1)* TAILLE_ENREG;
            return adr;
        }
    
    public static void main(String[] args) throws IOException
        {
        File fichier = new File("emp123.bin") ;

        RandomAccessFile donnee = new RandomAccessFile(fichier, "rw") ;
        int numero = 0 ;
        int choix = 0 ;
        int compteur = 100 ;
        double moyenne = 0 ;
        double newSalaire = 0 ;
        boolean sortie = false ;
        
        donnee.seek(getAdresse(compteur)) ;
        donnee.writeInt(compteur) ;
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
                    System.out.println("Menu") ;
                    System.out.println("====\n") ;
                    System.out.println("1. Afficher les donnees") ;
                    System.out.println("2. Calculer le salaire moyens") ;
                    System.out.println("3. Ajouter un employe") ;
                    System.out.println("4. Modifier le salaire d'un employe") ;
                    System.out.println("5. Quitter") ;
                    System.out.println("===========================================================") ;  
                    System.out.print("Entrez votre choix : ") ;
     
                    choix = Integer.parseInt(in.readLine()) ;
                    }
                catch(NumberFormatException e)
                    {}
                }
            while(choix < 1 || choix > 5) ;

            switch(choix)
                {
                case 1 :
                    {
                    System.out.println() ;  
                        
                    donnee.seek(0) ;
                    
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
                    System.out.println() ;  
                    moyenne = 0 ;   
                    
                    for (int i = 0 ; i < compteur ; i++)
                        {
    
                        try
                            {
                                donnee.seek(i*52) ;
                                donnee.readInt() ;
                                donnee.readUTF();
                                donnee.readChar() ;
                                donnee.readUTF() ;
                                donnee.readChar() ;
                                moyenne += donnee.readDouble() ;
                                donnee.readChar() ;                 
                            }
                        catch(EOFException e)
                            {}
                        }
                    
                    System.out.println("La moyenne des salaires est de : " + (moyenne/compteur)) ;
                                        
                    System.out.println() ;
                    }               
                break ;
    
                case 3 :
                    {
                    System.out.println() ;  
                        
                    donnee.seek(donnee.length()) ;
                    compteur += 1 ;
                    
                    try
                        {
                            donnee.writeInt(compteur) ;
                            donnee.writeChar(' ') ;
                            System.out.println("Entrez le nom du nouvel employe") ;
                            donnee.writeUTF(in.readLine()) ;
                            donnee.writeChar(' ') ;
                            System.out.println("Entrez le prenom du nouvel employe") ;
                            donnee.writeUTF(in.readLine()) ;
                            donnee.writeChar(' ') ;
                            System.out.println("Entrez le salaire du nouvel employe") ;
                            donnee.writeDouble(Double.parseDouble(in.readLine())) ;
                            donnee.writeChar('\n') ;                    
                        }
                    catch(EOFException e)
                        {}
                                        
                    System.out.println() ;
                    }               
                break ;

                case 4 :
                    {
                    System.out.println() ;  
                        
                    donnee.seek(0) ;
                    
                    do
                        {
                        System.out.println("Entrez le numéro de l'employé a qui vous voulez change le salaire") ;
                        numero = Integer.parseInt(in.readLine()) ;
                        }
                    while(numero < 0 || numero > compteur) ;
                    
                    System.out.println("Entrez le montant du nouveau salaire") ;
                    newSalaire = Double.parseDouble(in.readLine()) ;
                    
                    int numDonnee = 0 ;
                    
                    try
                        {
                        for (int i = 0 ; i < compteur ; i++)
                            {
                            numDonnee = donnee.readInt() ;  
                                    
                            if(numero == numDonnee)
                                {
                                donnee.readChar() ;
                                donnee.readUTF() ;
                                donnee.readChar();
                                donnee.readUTF() ;
                                donnee.readChar() ;
                                donnee.writeDouble(newSalaire) ;
                                donnee.readChar() ; 
                                }
                            else
                                {
                                donnee.readChar() ;
                                donnee.readUTF() ;
                                donnee.readChar();
                                donnee.readUTF() ;
                                donnee.readChar() ;
                                donnee.readDouble() ;
                                donnee.readChar() ;                             
                                }   
                            }           
                        }
                    catch(EOFException e)
                        {}
                                        
                    System.out.println() ;
                    }               
                break ;
                
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



