import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Player {
	
	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(8082);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(
						clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));) {
						
			    String inputLine, outputLine;
			            
			    // Initiate conversation with client
			    GameProtocol kkp = new GameProtocol();
			    outputLine = kkp.processInput(null);
			    out.println(outputLine);

			    while ((inputLine = in.readLine()) != null) {
			        outputLine = kkp.processInput(inputLine);
			        out.println(outputLine);
			        if (outputLine.equals("Bye."))
			        		break;
			    }
			    
			    // Client
			    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			       
	}
}

