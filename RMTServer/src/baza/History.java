package baza;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class History {
	ArrayList<Izraz> history;
	
	public History() {
		history = new ArrayList<Izraz>();
	}
	
	public void add(Izraz i) {
		history.add(i);
	}

	public ArrayList<Izraz> getHistory() {
		return history;
	}
	
	public void resetHistory() {
		history.clear();
	}

	@Override
	public String toString() {
		String h = "";
		for(int i = 0; i < history.size(); i++) {
			h += history.get(i).toString();
			h += "; ";
		}
		return h;
	}
	
	public void export() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("history.txt", "UTF-8");
			writer.println("Istorija kalkulacija");
			for(int i = 0; i < history.size(); i++) {
				writer.println(history.get(i));				
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
	}
}
