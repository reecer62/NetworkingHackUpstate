package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Protocol.ApplicationProtocol;

public class MultiServerThread extends Thread {
	//Instance variable of type Socket
	private Socket skt = null;
	
	public MultiServerThread(Socket skt) {
		//Constructs a new Thread object "Thread(String name)"
		super("TestMultiServerThread");
		//Set that Thread object to have the new socket
		this.skt = skt;
	}
	
	public void run() {
		try {
			//Open a writer on client's stream
			PrintWriter outToClient = new PrintWriter(skt.getOutputStream(), true);
			//Open a reader on client's stream
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			
			String inputLine;
			String outputLine;
			
			//Create a protocol so the client and the server can communicate
			ApplicationProtocol ap = new ApplicationProtocol();
			//Process input
			outputLine = ap.processInput(null);
			//Print the processed input that application protocol handled, to the client
			outToClient.println(outputLine);
			
			//While still reading input from client
			while((inputLine = inFromClient.readLine()) != null) {
				//Set a string outputLine to get the processed input application protocol handles
				outputLine = ap.processInput(inputLine);
				//Print that outputLine to the client
				outToClient.println(outputLine);
				//If that outputLine equals "!quit", then break reading from client
				if(outputLine.equals("!quit.")) break;
			}
			//Close the socket when done
			skt.close();
		} catch (IOException ioe) {
			System.err.println("IO Error occurred.");
		}
	}
	
}
