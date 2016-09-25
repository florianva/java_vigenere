package vigenere;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Texte {
	
	public static String lecture(String filename) throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
	
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String text = sb.toString().toLowerCase();
		    return text.toLowerCase();
		    
		}
	}
	
	public static void ecriture(Path file,List<String> lines){
		
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
