package calc;

public class Calculator {	
	public static double izracunaj(int x, int y, char operator) throws Exception {
		
		switch(operator) {
		case '+':
			return (double)x+y;
		case '-':
			return (double)x-y;
		case '*':
			return (double)x*y;
		case 'x':
			return (double)x*y;
		case '/':
			return (double)x/y;
		}
		
		throw new Exception("Operator lose unet");
	}
	
	public static double proveriIzraz(String izraz) throws Exception{
		int brojac = 0;
		int x = 0;
		int y = 0;
		int pola = 0; 
		
		for (int i = 0; i < izraz.length(); i++) {
			if( !Character.isDigit(izraz.charAt(i)) ) {
				brojac++;
			}
		}
		
		if(brojac > 1) {
			throw new Exception("Lose unet izraz");
		}
		
		
		for (int i = 0; i < izraz.length(); i++) {
			if( !Character.isDigit(izraz.charAt(i))) {
				pola = i;
			}
		}
		
		if(pola == 0) {
			throw new Exception("Nesto ne valja");
		}
		
		x = Integer.parseInt( izraz.substring(0, pola));
		y = Integer.parseInt( izraz.substring(pola + 1, izraz.length()));
		
		
		return izracunaj(x, y, izraz.charAt(pola));
	}
	
	public static double vratiX(String izraz) throws Exception{
		int pola = 0;
		
		for (int i = 0; i < izraz.length(); i++) {
			if( !Character.isDigit(izraz.charAt(i))) {
				pola = i;
			}
		}
		
		if(pola == 0) {
			throw new Exception("Nesto ne valja");
		}
		
		return Integer.parseInt( izraz.substring(0, pola));
	}
	
	public static double vratiY(String izraz) throws Exception {
		int pola = 0;
		
		for (int i = 0; i < izraz.length(); i++) {
			if( !Character.isDigit(izraz.charAt(i))) {
				pola = i;
			}
		}
		
		if(pola == 0) {
			throw new Exception("Nesto ne valja");
		}
		
		return Integer.parseInt( izraz.substring(pola + 1, izraz.length()));
	}
	
	public static char vratiOperator(String izraz) throws Exception {
		int pola = 0;
		
		for (int i = 0; i < izraz.length(); i++) {
			if( !Character.isDigit(izraz.charAt(i))) {
				pola = i;
			}
		}
		
		if(pola == 0) {
			throw new Exception("Nesto ne valja");
		}
		
		return izraz.charAt(pola);
	}
}
