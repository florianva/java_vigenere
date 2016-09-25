package vigenere;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Dechiffrement{
	
	public void Dechiffre (String text, String key, String fileName){
		
		StringBuffer sb = new StringBuffer(text);
		int j=0;
		for(int i = 0; i < text.length(); i++)
		{
			int current = (int)text.charAt(i);
			if (current >= 97 && current <= 122){
				
				int decalage = (int)key.charAt(j % key.length()) - 97; 
				
				int currentLetter = (int)text.charAt(i);
				if(currentLetter - decalage < 97){
					currentLetter += 26;
				}
				
				int newCharCode = (currentLetter - 97 - decalage) % 26 + 97;
				sb.setCharAt(i, (char)newCharCode);
				j++;
			}
		}
		
		text = sb.toString();
		List<String> lines = Arrays.asList(text);
		Path file = Paths.get(fileName);
		Texte.ecriture(file, lines);
		
		System.out.println("Texte déchiffré dans le fichier "+fileName);
	}

}
