/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dowster.school.datacomm.program1;

/**
 *
 * @author dowbr
 */
public class ChatMessage {
    public String sender;
    public String message;
    
    public ChatMessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }
    
    public String getSender() {
        return this.sender;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.sender);
        sb.append(": ");
        sb.append(this.message);
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }
}
