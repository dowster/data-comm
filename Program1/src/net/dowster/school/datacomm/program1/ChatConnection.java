/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dowster.school.datacomm.program1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Handles a connection to the chat server and facilitates sending / receiving
 * chat messages to / from the server.
 * 
 * @author dowbr
 */
public class ChatConnection 
{
    
    String address;
    int port;
    
    Socket socket;
    Scanner inputScanner;
    PrintStream printer;
    
    public ChatConnection(String address, int port) throws IOException 
    {
        this.address = address;
        this.port = port;
        connect();
    }
    
    public ChatMessage getMessage() 
    {
        if(inputScanner != null && inputScanner.hasNextLine()) 
        {
            return new ChatMessage("Server", inputScanner.nextLine());
        }
        else 
        {
            return null;
        }
    }
    
    public void sendMessage(ChatMessage message) {
        printer.println(message.getMessage());
    }
    
    public boolean isConnected() {
        return socket != null && socket.isConnected() && !socket.isClosed();
    }
    
    public void disconnect() throws IOException {
        this.socket.close();
        this.inputScanner = null;
        this.printer = null;
    }
    
    public void connect() throws IOException {
        this.socket = new Socket(address, port);
        this.inputScanner = new Scanner(socket.getInputStream());
        this.printer = new PrintStream(socket.getOutputStream());
    }
    
}
