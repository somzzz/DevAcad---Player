import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Client {

	public static void main(String[] args) {
		String hostName = "192.170.1.155";
		int portNumber = 8082;
		GameProtocol gp1 = new GameProtocol();
		Queue<String> ex = new LinkedList<String>();
		ex = gp1.deck();
		int nr = 0;
		while (!ex.isEmpty()){
			nr ++;
			System.out.println(ex.remove());
		}
		System.out.println(nr);
		try {
			Socket kkSocket = new Socket(hostName, portNumber);
			PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					kkSocket.getInputStream()));

			String fromServer, fromUser;
			GameProtocol gp = new GameProtocol();
			while ((fromServer = in.readLine()) != null) {
				System.out.println("Server: " + fromServer);
				if (fromServer.equals("Bye."))
					break;
				BufferedReader stdIn = new BufferedReader(
						new InputStreamReader(System.in));
				// fromUser = ;
				fromUser = gp.ProcessInput(fromServer);
				stdIn.readLine();
				if (fromUser.compareTo("Sleep") == 0) {
					Thread.sleep(4000);
					continue;
				}

				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					out.println(fromUser);
				}

			}

		} catch (Exception e) {
		}
	}
}
