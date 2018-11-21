package net.dowster.school.datacomm.program4.client.Utils;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class JTextAreaWriter extends OutputStream
{
   private JTextArea textArea;

   public JTextAreaWriter(JTextArea textArea) {
      this.textArea = textArea;
   }

   @Override
   public void write(int b) throws IOException
   {
      textArea.append(Character.toString((char) b));
   }
}