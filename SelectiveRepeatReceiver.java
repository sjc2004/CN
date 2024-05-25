import java.io.*;
import java.net.*;
import java.util.Arrays;

public class SelectiveRepeatReceiver {
    private static final int PORT = 9876;
    private static final int WINDOW_SIZE = 4;

    private DatagramSocket socket;
    private int expectedSeqNum = 0;
    private String[] buffer;
    private boolean[] received;
    private int base = 0;

    public SelectiveRepeatReceiver() throws SocketException {
        socket = new DatagramSocket(PORT);
        buffer = new String[WINDOW_SIZE];
        received = new boolean[WINDOW_SIZE];
        Arrays.fill(received, false);
    }

    public void receive() throws IOException {
        while (true) {
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String receivedData = new String(packet.getData(), 0, packet.getLength());
            int seqNum = Integer.parseInt(receivedData.split(":")[0]);
            String data = receivedData.split(":")[1];

            if (seqNum >= base && seqNum < base + WINDOW_SIZE) {
                buffer[seqNum % WINDOW_SIZE] = data;
                received[seqNum % WINDOW_SIZE] = true;
                sendAck(seqNum);

                while (received[base % WINDOW_SIZE]) {
                    System.out.println("Delivered: " + buffer[base % WINDOW_SIZE]);
                    received[base % WINDOW_SIZE] = false;
                    base++;
                }
            } else if (seqNum < base) {
                sendAck(seqNum); // Ack old frames to prevent sender from waiting indefinitely
            }
        }
    }

    private void sendAck(int seqNum) throws IOException {
        String ack = String.valueOf(seqNum);
        byte[] buf = ack.getBytes();
        DatagramPacket ackPacket = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 5050);
        socket.send(ackPacket);
        System.out.println("Sent ACK for frame: " + seqNum);
    }

    public static void main(String[] args) throws IOException {
        SelectiveRepeatReceiver receiver = new SelectiveRepeatReceiver();
        receiver.receive();
    }
}
