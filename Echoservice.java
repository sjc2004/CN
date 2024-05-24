//Server program

import java.net.*;

import java.io.*;

public class TCPEchoServer

{

private static final int BUFSIZE=80;

public static void main(String args[])throws IOException

{

if(args.length!=1)

throw new IllegalArgumentException("Parameter(s):<Port>");

int servPort=Integer.parseInt(args[0]);

ServerSocket servSock=new ServerSocket(servPort);

int recvMsgSize;

byte[] byteBuffer=new byte[BUFSIZE];

for(;;)

{

Socket clntSock=servSock.accept();

InputStream in=socket.getInputStream();

OutputStream out=socket.getOutputStream();

while((recvMsgSize=in.read(byteBuffer))!=-1)

out.write(byteBuffer,0,recvMsgSize);

clntSock.close();

}

}

}

//Client program

import java.net.*;

import java.io.*;

public class TCPEchoClient

{

public static void main(String args[])throws IOException

{

if((args.length<2)||(args.length>3))

throw newIllegalArgumentException("Parameter(s):<server><word>[<port>]");

String server=args[0];

byte[] byteBuffer=args[1].getBytes();

int servPort=(args.length==3)?Integer.parseInt(args[2]):7;

Socket socket=new Socket(server,servPort);

System.out.println("Connected to server...sending echo string");

InputStream in=socket.getInputStream();

OutputStream out=socket.getOutputStream();

out.write(byteBuffer);

int totalBytesRcvd=0;

int bytesRcvd;

while(totalBytesRcvd<byteBuffer.length)

{

if((bytesRcvd=in.read(byteBuffer,totalBytesRcvd,byteBuffer.length-totalBytesRcvd))==-1)

throw new SocketException("Connection closed prematurely");

totalBytesRcvd+=bytesRcvd;

}

System.out.println("Received :"+new String(byteBuffer));

socket.close();

}

}
/*
Output:

First Open a command prompt window & run the server program in the following manner:

Prompt>java TCPEchoServer 7/ Any other port number beyond 1023 and the server will run

endlessly.

Then open another command prompt window & run the client program in the following manner:

Prompt> java TCPEchoClient 127.0.0.1 Hello press enter in the keyboard if you have given port

number for running the server as 7 or then give the same port number that you have given for

running the server other than 7

The output will be:

Connected to server…sending echo string

Received: Hello*/
