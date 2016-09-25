package vigenere;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Operations {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String dechiffreFileName = "texteClair.txt";
		String chiffreFileName = "texteChiffre.txt";
		
		String analyseReadFileName = "boulot1.txt";
		String analyseWriteFileName = "cryptanalyse.txt";
		
		Tableau t = new Tableau();
		Chiffrement c = new Chiffrement();
		Dechiffrement d = new Dechiffrement();
		Cryptanalyse ca = new Cryptanalyse();
		
		//Affichage du tableau de vigenère dans la console
		t.affichage();
		
		//Exercice 1 : Chiffrement
		c.Chiffre(" ;toto3!  ", "cle",chiffreFileName);
		
		//Exercice 2 : Dechiffrement
		d.Dechiffre("vzxq8	.", "cle",dechiffreFileName);
		
		//Exercice 3 : Cryptanalyse
		String text = Texte.lecture(analyseReadFileName);
		String key = ca.Analyse(analyseReadFileName);
		
		d.Dechiffre(text, key,analyseWriteFileName);
	}
	
}
