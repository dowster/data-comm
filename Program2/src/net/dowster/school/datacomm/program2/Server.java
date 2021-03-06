package net.dowster.school.datacomm.program2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Cipher server for program 2. Encrypts a message and sends it back to the
 * client.
 */
public class Server {

    private static final int SERVER_PORT = 5764;
    public static final String LOG_PATH = "C:\\logs\\net\\dowster\\school\\" +
          "datacomm\\program2\\log.txt";

    private FileWriter logFile;
    private PrintWriter logWriter;

    private ServerSocket serverSocket;

    /**
     * Create the server object and start it up.
     * @param args unused
     * @throws IOException Someting very bad happened and we're just gonna shut
     *                     it all down. mmkay?
     */
    public static void main(String[] args) throws IOException
    {
        Server server = new Server();
        server.run();
    }

    /**
     * Creates an instance of server, sets up the socket connection and
     * opens the log file.
     *
     * @throws IOException if issue creating the socket connection or opening
     *                     the log file.
     */
    private Server() throws IOException {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(SERVER_PORT));

            logFile = new FileWriter(LOG_PATH, true);
            logWriter = new PrintWriter(logFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Run the socket server handling code. Each connection will get its own
     * serverThread to run on.
     * @throws IOException
     */
    private void run()
    {
        Socket socket;
        try
        {
            while ((socket = serverSocket.accept()) != null)
            {
                ServerThread serverThread = new ServerThread(socket, logWriter);
                serverThread.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace(logWriter);
        }
    }
}
