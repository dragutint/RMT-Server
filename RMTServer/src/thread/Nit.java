package thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;

import baza.DataBaseControler;
import baza.History;
import baza.Izraz;
import baza.User;
import calc.Calculator;

public class Nit extends Thread {
	Socket socket;
	PrintWriter socketOut;
	BufferedReader socketIn;
	DataBaseControler db = new DataBaseControler();
	History history = new History();
	boolean loggedIn = false;
	
	public Nit(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		System.out.println("Novi klijent se povezao, " + this.getName());
		
		try {
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream(), true);
			String meni = "Izaberi opciju: 1. Login, 2. Register, 3. Calculator, 4. Loggout, 5. History, 0. EXIT";
			String opcija;
			String poruka;
			socketOut.println(meni);
			
			while(true) {
				opcija = socketIn.readLine();
				
				switch (Integer.parseInt(opcija)) {					
				case 0:
					Thread.currentThread().interrupt();
					System.out.println("<SERVER> Gasim nit " + this.getName());
					break;
				case 1:
					socketOut.println("LOGIN FORM");
					String user = socketIn.readLine();
					String pass = socketIn.readLine();
					User u = new User(user, pass);
					
					
					if(db.proveriUsera(u)) {
						socketOut.println(true);
						System.out.println("Klijent [thread=" + this.getName() + "] se uspesno loginovao; " + u);
					} else {
						socketOut.println(false);
						System.out.println("Klijent [thread=" + this.getName() + "] je pokusao da se loginuje ali nije uspeo; " + u);
					}
					poruka = socketIn.readLine();
					
					if(poruka.equals("1")) {
						loggedIn = true;
						history.resetHistory();
					}
					break;
					
					
				case 2:
					String noviUsername;
					String noviPass;
					socketOut.println("REGISTRATION FORM");
					do {
						noviUsername = socketIn.readLine();
						if(!db.postoji(noviUsername)) {
							socketOut.println("1");
						} else {
							socketOut.println("Username vec postoji u bazi");
						}
						
					} while(db.postoji(noviUsername));
					
					do {
						noviPass = socketIn.readLine();
						if(dobraSifra(noviPass)) {
							socketOut.println("1");
						} else {
							socketOut.println("Password mora sadrzati barem jedno veliko slovo, bar jedan broj i minimum 8 karaktera");
						}
					} while(!dobraSifra(noviPass));
					
					
					User noviUser = new User(noviUsername, noviPass);
					db.dodaj(noviUser);
					System.out.println("Novi user je registrovan, " + this.getName() + ", podaci: [user=" + noviUsername + ", pass=" + noviPass + "]");
					
					break;
					
					
				case 3:
					socketOut.println("<SERVER> : Unesite matematicki izraz u obliku X*Y");
					
					String izraz = socketIn.readLine();
					String poruka1 = "";
					
					try {
						double x = Calculator.vratiX(izraz);
						double y = Calculator.vratiY(izraz);
						char operator = Calculator.vratiOperator(izraz);
						double resenje = Calculator.proveriIzraz(izraz);
						
						Izraz i = new Izraz(x, y, operator, resenje);
						System.out.println("Klijent " + this.getName() + " je izvrsio sledecu kalkulaciju: " + i);
						
						if(loggedIn) {
							history.add(i);
						}
						
						poruka1 += "Vase resenje je " + resenje;
					} catch (Exception e) {
						System.out.println(e.getMessage());
						poruka1 = "Doslo je do greske pri unosu";
					}
					
					socketOut.println(poruka1);
					break;

				case 4:
					socketOut.println("Logout");
					System.out.println("Korisnik se izlogovao, " + this.getName());
					poruka = socketIn.readLine();
					if(poruka.equals("1")) {
						loggedIn = false;
						history.resetHistory();
					} 
					break;
					
				case 5:
					System.out.println("Klijent " + this.getName() + " ispisuje istoriju kalkulacija");
					socketOut.println("History");
					socketOut.println(history);
					socketOut.println("Da li zelite istoriju da sacuvate kao fajl? da/ne");
					poruka = socketIn.readLine();
					if(poruka.equals("da") || poruka.equals("DA") || poruka.equals("Da")) {
						history.export();
						System.out.println("Klijent " + this.getName() + " je sacuvao istoriju kao fajl");
					}
					break;
				}
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private boolean dobraSifra(String sifra) {
		int brojac1 = 0;
		int brojac2 = 0;
		
		if(sifra.length() < 8) 
			return false;
		
		for(int i = 0; i < sifra.length(); i++) {
			if(Character.isUpperCase(sifra.charAt(i))) 
				brojac1++;
			
			if(Character.isDigit(sifra.charAt(i))) 
				brojac2++;	
		}
		
		if(brojac1 < 1 || brojac2 < 1) 
			return false;
		
		return true;
	}
}
