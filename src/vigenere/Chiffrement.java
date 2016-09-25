package vigenere;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Chiffrement {
	
	public void Chiffre(String text, String key, String fileName){

		StringBuffer sb = new StringBuffer(text);
		int j = 0;
		for(int i = 0; i < text.length(); i++)
		{
			int currentLetter = (int)text.charAt(i);
			if (currentLetter >= 97 && currentLetter <= 122){
				int decalage = (int)key.charAt(j % key.length()) - 97;
				int newCharCode = ((int)text.charAt(i) - 97 + decalage) % 26 + 97;
				sb.setCharAt(i, (char)newCharCode);
				j++;
			}
			
			
		}
		
		text = sb.toString();
		
		List<String> lines = Arrays.asList(text);
		Path file = Paths.get(fileName);
		
		Texte.ecriture(file, lines);
		
		System.out.println("Texte chiffré dans le fichier "+fileName);
		
		
	}
	
}
