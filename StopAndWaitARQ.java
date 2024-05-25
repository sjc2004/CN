import java.util.Scanner;

public class StopAndWaitARQ {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of frames to transmit: ");
        int f = scanner.nextInt();

        int[] frames = new int[f];
        System.out.print("\nEnter " + f + " frames: ");
        for (int i = 0; i < f; i++) {
            frames[i] = scanner.nextInt();
        }

        System.out.println("\nWith Stop-and-Wait ARQ protocol the frames will be sent in the following manner (assuming no corruption of frames)\n");

        for (int i = 0; i < f; i++) {
            System.out.println("Sending frame: " + frames[i]);
            System.out.println("Acknowledgement of frame " + frames[i] + " received by sender\n");
        }

        scanner.close();
    }
}
