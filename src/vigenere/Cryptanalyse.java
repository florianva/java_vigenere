package vigenere;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Cryptanalyse {
	
	
	
	
	public String Analyse(String fileName) throws FileNotFoundException, IOException{
		
		int nbtotal = 0;
		int nblettre [] = new int[26];
		int taille = 0;
		float max = 0;
		ArrayList<String> elements = new ArrayList<>();
		
		
		//Récupération du texte + initialisation du tableau pour le comptage des lettres
		String text = Texte.lecture(fileName);
	    for (int i=0; i<26; i++){
	    	nblettre[i]=0;
	    }
	    
	    
	    //Calcul du nombre d'occurence de chaque lettre + calcul du nombre total de lettres dans le texte
	    for (int i=0;i<text.length();i++){
	    	int currentLetter = (int)text.charAt(i);
	    	if (currentLetter >= 97 && currentLetter <= 122){
	    		nblettre[currentLetter-97]++;
	    		nbtotal++;
	    		elements.add(Character.toString((char)currentLetter));
	    		
	    	}
	    	
	    }
	    
	    System.out.println("Nombre de lettres dans le texte : "+nbtotal);
	    
	    
	    //Récupération de l'indice de coincidence des lettres 1/1
	    float coincidence = indice(nblettre, nbtotal);
	    System.out.println("Indice de coincidence : "+coincidence);
	    
	    
	    //Récupération de l'indice de coincidence des lettres 1/n + Taille probable de la clé
	    for (int espace = 2 ; espace <=10;espace++){
		    float kas = Kasiski(elements,espace);
		    
		    if (kas > max){
		    	max = kas;
		    	taille = espace;
		    }
		    
		    System.out.println("Indice 1/"+espace+" = "+kas);
	    }
	    System.out.println("Taille probable de la clé : "+taille);
	    
	    //Récupération de la clé de dechiffrement
	    return getKey(nbtotal,taille,elements);
	    
	}

	
	public String getKey(int nbtotal,int taille, ArrayList<String> elements){

		int entier=0;
		int modulo = nbtotal%taille;
		float frequence;
		int key[] = new int[taille];
		
		
		//Calcul du nombre de lettres à mettre dans chaque sous-texte
		for (int t=0; t<taille;t++){
			entier = nbtotal/taille;
			if(modulo>0){
				
				entier = entier+1;
				modulo--;
			}
			//Initalisation des variables en fonction du calcul précédent
			String decoup[] = new String[entier];
			int nblettre [] = new int[26];
			float frequenceTable [] = new float[26];
			float frequenceMax = 0;
			Integer lettre = 0;
			
			//Dans chaque sous-texte on ajoute une lettre et on incrémente le compteur de cette lettre
			for (int i=0;i<entier;i++){
				decoup[i]=elements.get(t+((taille)*i));
				nblettre[elements.get(t+((taille)*i)).charAt(0)-97]++;
			}
			
			//Pour chaque lettres recupérées, on calcule la fréquence dans chaque sous-texte et on récupère la plus élevée
			for(int i=0;i<26;i++){
		    	frequence = ((float)nblettre[i]/entier)*100;
		    	frequenceTable[i]=frequence;
		    	if(frequence>frequenceMax){
		    		frequenceMax = frequence;
		    		lettre = i;
		    		
		    	}
		    }
			int ascii = lettre+97;
			key[t]= ascii;
		}
		
		
		//On soustrait la la lettre la plus présente dans chaque sous partie par la lettre la plus utilisé dans la langue supposé
		for(int i=0; i<key.length;i++){
			//Lettre E = 4
			key[i]=key[i]-4;
			if(key[i]<97){
				key[i] = key[i]+26;
			}
			
		}
		
		//On retourne la clé
		StringBuilder builder = new StringBuilder();
		for (int value : key) {
		    builder.append((char)value);
		}
		String cle = builder.toString();
		System.out.println("clé : "+cle);
		
		
		return cle;
		
	}
	
	
	//Calcule de l'indice de coincidence
	public float indice(int nblettre [], int nbtotal){
		float co = 0;
		for (int i = 0; i<26 ;i++){
			
	    	co = co+(((float)nblettre[i]/nbtotal)*((float)(nblettre[i]-1)/(nbtotal-1)));
	    	
	    }
	    return co;
	}
	
	//Test de Kasiski
	public float Kasiski(ArrayList<String> elements, int espace){
		int nblettre [] = new int[26];
		int nbtotal = 0;
		
		for (int i = 0; i < elements.size(); i=i+espace) {
	    	int currentLetter = (int)elements.get(i).charAt(0);
	    	if (currentLetter >= 97 && currentLetter <= 122){
	    		nblettre[currentLetter-97]++;
	    		nbtotal++;
	    	}
	    }
		return indice(nblettre, nbtotal);
		
	}
	

	
}
