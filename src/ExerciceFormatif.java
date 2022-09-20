import java.io.* ;
public class ExerciceFormatif {

            static RandomAccessFile donnee;
            static final int TAILLE_ENREG = 56;
            static File fichier = new File("emp.bin") ;
 
            public static String cadreMot(String mot, int size){
                String sortie="";
                if(mot.length() >= size){
                    sortie = mot.substring(0,size);
                }else{
                    sortie=mot;
                    for(int i=0;i<20-mot.length();i++){
                        sortie =sortie + " ";
                    }
                    
                }
                return sortie;
            }
            
            public static long getAdresse(int cle) {
                long adr=-1;
                if(cle%100 == 0){
                    adr = (cle/100-1)* TAILLE_ENREG;
                }
                return adr;
            }
            public static int getDernierCle(long lng) {
                    long l = 100*lng/TAILLE_ENREG;
                    return (int) l;
            }

            public static void afficher() throws IOException{
                int num;
                String nom, prenom;
                double salaire;
                donnee = new RandomAccessFile(fichier, "rw") ;
                System.out.println() ;  
                            
                try
                {
                donnee.seek(0) ;
                
                for (int i = 0 ; i < donnee.length() ; i++)
                    {
                            
                            donnee.seek(i*TAILLE_ENREG);

                            num = donnee.readInt();
                            nom = donnee.readUTF();
                            prenom = donnee.readUTF();
                            salaire = donnee.readDouble();
                            
                            if(num != -1){
                            System.out.print(num) ;
                            System.out.print(" ") ;
                            System.out.print(nom) ;
                            System.out.print(" ") ;
                            System.out.print(prenom) ;
                            System.out.print(" ") ;
                            System.out.print(salaire) ;
                            System.out.print("\n") ;
                            }
                        }
                    }
                    catch(EOFException e)
                        {}
                                    
                System.out.println() ;


            }
            public static void afficherUn(int num) throws IOException{
                
                String nom, prenom;
                double salaire;
                donnee = new RandomAccessFile(fichier, "rw") ;
                System.out.println() ;  
                            
                try
                {
                            
                            donnee.seek(getAdresse(num));

                            num = donnee.readInt();
                            nom = donnee.readUTF();
                            prenom = donnee.readUTF();
                            salaire = donnee.readDouble();
                            
                            if(num != -1){
                            System.out.print(num) ;
                            System.out.print(" ") ;
                            System.out.print(nom) ;
                            System.out.print(" ") ;
                            System.out.print(prenom) ;
                            System.out.print(" ") ;
                            System.out.print(salaire) ;
                            System.out.print("\n") ;
                            }
                        
                    }
                    catch(EOFException e)
                        {}
                                    
                System.out.println() ;


            }
        
        public static void main(String[] args) throws IOException
            {
            
                int numero = 0 ;
                int choix = 0 ;
                int compteur = 100 ;
                double moyenne = 0 ;
                double newSalaire = 0 ;
                boolean sortie = false ;
            
            
            donnee = new RandomAccessFile(fichier, "rw") ;
            donnee.seek(getAdresse(compteur)) ;
            donnee.writeInt(compteur) ;
            donnee.writeUTF(cadreMot("Tavares",20)) ;
            donnee.writeUTF(cadreMot("Antonio",20)) ;
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
                        System.out.println("5. Suprimer un employe") ;
                        System.out.println("6. Quitter") ;
                        System.out.println("===========================================================") ;  
                        System.out.print("Entrez votre choix : ") ;
         
                        choix = Integer.parseInt(in.readLine()) ;
                        }
                    catch(NumberFormatException e)
                        {}
                    }
                while(choix < 1 || choix > 6) ;
    
                switch(choix)
                    {
                    case 1 :
                        {
                            afficher();
                        }               
                    break ;
    
     
        
                    case 2 :
                        {
                        System.out.println() ;  
                        moyenne = 0 ;   
                        int cpt=0;
                        for (int i = 0 ; i < donnee.length() ; i++)
                            {
        
                            try
                                {
                                    donnee.seek(i*TAILLE_ENREG) ;
                                    donnee.readInt() ;
                                    donnee.readUTF();
                                    donnee.readUTF() ;
                                    moyenne += donnee.readDouble() ;
                                    cpt++;
                               
                                }
                            catch(EOFException e)
                                {}
                            }
                        
                        System.out.println("La moyenne des salaires est de : " + (moyenne/cpt)) ;
                                            
                        System.out.println() ;
                        }               
                    break ;
        
                    case 3 :
                        {
                        System.out.println() ;  
                        //compteur += 100 ;
                        donnee.seek(donnee.length()) ;
                        try
                            {
                                donnee.writeInt(getDernierCle(donnee.length())+100) ;
                                System.out.println("Entrez le nom du nouvel employe") ;
                                donnee.writeUTF(cadreMot(in.readLine(),20)) ;
                                System.out.println("Entrez le prenom du nouvel employe") ;
                                donnee.writeUTF(cadreMot(in.readLine(),20)) ;
                                System.out.println("Entrez le salaire du nouvel employe") ;
                                donnee.writeDouble(Double.parseDouble(in.readLine())) ;
                            }
                        catch(EOFException e)
                            {}
                                            
                        System.out.println() ;
                        System.out.println(getAdresse(compteur));
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
                        while(getAdresse(numero) ==-1) ;
                        
                        System.out.println("Entrez le montant du nouveau salaire") ;
                        newSalaire = Double.parseDouble(in.readLine()) ;
                        donnee.seek(getAdresse(numero));
                       
                        try
                            {
                                donnee.readInt();
                                donnee.readUTF() ;
                                donnee.readUTF() ;
                                donnee.writeDouble(newSalaire) ;
                            }
                        catch(EOFException e)
                            {}
                                            
                        System.out.println() ;
                        }               
                    break ;
                    case 5 :
                        {
                        System.out.println() ;  
                            
                        donnee.seek(0) ;
                        int cond =0;
                        do
                            {
                            System.out.println("Entrez le numéro de l'employé a qui vous voulez suprimer") ;
                            numero = Integer.parseInt(in.readLine()) ;
                            if(getAdresse(numero) == -1){
                                System.out.println("Le numero du employe n'existe pas !!!!") ;
                            }else{
                                cond=1;
                            }
                            }
                        while(cond ==0) ;
                        
                        
                        
                        try {
                            //donnee = new RandomAccessFile(fichier, "rw") ;
                            donnee.seek(getAdresse(numero)); 
                            donnee.writeInt(-1);
                            donnee.writeUTF(cadreMot(" ", 20));
                            donnee.writeUTF(cadreMot(" ", 20));
                            donnee.writeInt(0);
                        } catch (IOException e) {
                            System.out.println("Gros probléme! " + e.getMessage());
                        } finally {
                            //donnee.close();
                        }
                        System.out.println() ;
                        }               
                    break ;
                    
                    case 6 :
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
    
    
    
    