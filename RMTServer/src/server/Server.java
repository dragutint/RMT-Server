package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import thread.Nit;

public class Server {
	public static void main(String[] args) throws IOException {		
//		ServerSocket serverSocket = new ServerSocket(9090);	
		
		ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));	
		System.out.println("<SERVER> Cekam konekciju");
		ArrayList<Nit> niti = new ArrayList<Nit>();
		
		while (true) {
			Socket socket = serverSocket.accept();
			Nit nit = new Nit(socket);
			nit.start();
			
			niti.add(nit);
		}
		
	}	
}
