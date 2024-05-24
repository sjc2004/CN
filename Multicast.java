// Server side program

import java.io.*;

import java.net.*;

import java.util.Date;

public class MulticastDateServer

{

Public static void main(String args[])throws IOException, InterruptedException

{

DatagramSocket socket=new DatagramSocket(5050);

// create socket in an available port

for(int i=0;i<10;i++)

{

byte[] buf=new Date().toString().getBytes();

//prepare data to send

InetAddress group=InetAddress.getByName(“230.0.0.1”);

DatagramPacket packet=new DatagramPacket(buf,buf.length,group,1313);

socket.send(packet);

//send a packet to a multicast group address and a port

Thread.sleep(1500);

}

socket.close();

}

}

//Client side program

import java.io.*;

import java.net.*;

import java.util.Date;

public class MulticastDateClient

{

Public static void main(String args[])throws IOException

{

MulticastSocket socket=new MulticastSocket(1313);

InetAddress group=InetAddress.getByName(“230.0.0.1”);

socket.joinGroup(group);

//create a multcast socket and join a group

for(int i=0;i<10;i++){

byte[] buf=new byte[256];

DatagramPacket packet=new DatagramPacket(buf,buf.length);

socket.receive(packet);

//empty packet to receive group data

String received=new String(packet.getData());

System.out.println(“Current server time: ”+received);

//print the answer

}

socket.leaveGroup(group);

socket.close();

}

}//Output:

//Current server time is: Mon Jul 23 17:15:06 GMT 2018
