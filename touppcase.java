// String Conversion server(to uppercase)

import java.net.*;

import java.io.*;

class TCPServer

{

public static void main(String args[])throws Exception

{

String clientSentence;

String capitalizedSentence;

ServerSocket welcomeSocket=new ServerSocket(2345);

while(true)

{

Socket connectionSocket=welcomeSocket.accept();

BufferedReader inFromClient=new BufferedReader(new

InputStreamReader(connectionSocket.getInputStream()));

DataOutputStream outToClient=new

DataOutputStream(connectionSocket.getOutputStream());

clientSentence=inFromClient.readLine();

capitalizedSentence=clientSentence.toUpperCase()+'\n';

outToClient.writeBytes(capitalizedSentence);

}

}

}

// String Conversion Client(to uppercase)

import java.net.*;

import java.io.*;

class TCPClient

{

public static void main(String args[])throws Exception

{

String sentence;

String modifiedSentence;

System.out.println("Enter the sentence:");

BufferedReader inFromUser=new BufferedReader(newInputStreamReader(System.in));

Socket clientSocket=new Socket("localhost",2345);

DataOutputStream outToServer=new

DataOutputStream(clientSocket.getOutputStream());

BufferedReader inFromServer=new BufferedReader(new

InputStreamReader(clientSocket.getInputStream()));

sentence=inFromUser.readLine();

outToServer.writeBytes(sentence+'\n');

modifiedSentence=inFromServer.readLine();

System.out.println("From Server: "+modifiedSentence);

clientSocket.close();

}

}/*Output:

First Open a command prompt window & run the server program in the following manner:

Prompt>java TCPServer and the server will run endlessly.

Then open another command prompt window & run the client program in the following manner:

Prompt> java TCPEchoClient & press enter in the keyboard. Then a prompt appears like the

following:

Enter the sentence:

If you write “hello” & press enter in the keyboard, the output will be:

From Server: HELLO*/
