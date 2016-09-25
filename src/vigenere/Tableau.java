package vigenere;

public class Tableau {
	
	public void affichage(){
	
		char [] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
		char[][] table = new char [alphabet.length][alphabet.length];
		for(int i = 0; i < alphabet.length; i++)
		{
		    table[i] = new char[alphabet.length];
		    
		    for(int j = 0; j < alphabet.length; j++)
		    {
		        table[i][j] = alphabet[(i+j) % alphabet.length];
		        
		    }
		    System.out.println(table[i]);
		}
		System.out.println("");
	}
	
}
