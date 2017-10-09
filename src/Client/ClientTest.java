package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {
	//Port number is 7001
	private final static int PORT = 7001;
	
    public static void main(String[] args) {
    	//Where the server/host will be on
    	String hostName = "localhost";
    	
    	try {
    		//Open a socket
    		Socket skt = new Socket(hostName, PORT);
    		//Open an output stream to the socket
    		PrintWriter outToServer = new PrintWriter(skt.getOutputStream(), true); //Sets autoFlush() to true so you don't have to flush the output stream every time you print to server
    		//Open an input stream from the socket
    		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(skt.getInputStream()));
    		
    		//Read line from user input (input stream is from standard input)
    		BufferedReader stdInput = new BufferedReader(new InputStreamReader(System.in));
    		
    		String fromServer;
    		String fromUser;
    		
    		//While the input got from reading in from the server is not null
    		while((fromServer = inFromServer.readLine()) != null) {
    			//Print out the line from the server
    			System.out.println("Server says: " + fromServer);
    			//Check if the server sends "Turn ended." to break the input stream
    			if(fromServer.equals("Turn ended.")) break;
    			//Get line of input from the client
    			fromUser = stdInput.readLine();
    			//If the input from the user is not null then print it to the server
    			if(fromUser != null) {
    				System.out.println("Client says: " + fromUser); //Prints what the client says
    				outToServer.println(fromUser); //Actually prints to server
    			}
    		}
    	} catch (UnknownHostException uhe) {
    		//Can't find host
    		System.err.println("Don't know the h*ckin host " + hostName);
    		System.exit(1);
    	} catch (IOException ioe) {
    		//IO error
    		System.err.println("I/O error in connecting to " + hostName);
    		System.exit(1);
    	} 
    	
    }
}

