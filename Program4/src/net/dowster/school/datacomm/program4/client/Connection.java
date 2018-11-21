/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dowster.school.datacomm.program4.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Handles a connection to the chat server and facilitates sending / receiving
 * chat messages to / from the server.
 * 
 * @author dowbr
 */
public class Connection
{

    String address;
    int port;

    Socket socket;
    Scanner inputScanner;
    PrintWriter socketWriter;

    public Connection(String address, int port) throws IOException
    {
        this.address = address;
        this.port = port;
        connect();
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected() && !socket.isClosed();
    }
    
    public void disconnect() throws IOException {
        this.socket.close();
        this.inputScanner = null;
        this.socketWriter = null;
    }
    
    public void connect() throws IOException {
        this.socket = new Socket(address, port);
        this.inputScanner = new Scanner(socket.getInputStream());
        this.socketWriter = new PrintWriter(socket.getOutputStream(), true);
    }

    public PrintWriter getSocketWriter() {
        return socketWriter;
    }

    public Scanner getInputScanner() {
        return inputScanner;
    }

    public Socket getSocket()
    {
        return this.socket;
    }
}
