import java.util.Scanner;

public class SlidingWindowProtocol {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter window size: ");
        int w = scanner.nextInt();

        System.out.print("\nEnter number of frames to transmit: ");
        int f = scanner.nextInt();

        int[] frames = new int[f];
        System.out.print("\nEnter " + f + " frames: ");
        for (int i = 0; i < f; i++) {
            frames[i] = scanner.nextInt();
        }

        System.out.println("\nWith sliding window protocol the frames will be sent in the following manner (assuming no corruption of frames)\n");
        System.out.println("After sending " + w + " frames at each stage sender waits for acknowledgement sent by the receiver\n");

        for (int i = 0; i < f; i++) {
            if ((i + 1) % w == 0) {
                System.out.println(frames[i]);
                System.out.println("Acknowledgement of above frames sent is received by sender\n");
            } else {
                System.out.print(frames[i] + " ");
            }
        }

        if (f % w != 0) {
            System.out.println("\nAcknowledgement of above frames sent is received by sender");
        }

        scanner.close();
    }
}
