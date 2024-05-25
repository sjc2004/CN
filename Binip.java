import java.util.Scanner;

public class Binip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a classful IP address in binary format (e.g., 1000.11.111.000000): ");
        String binaryIP = scanner.nextLine();

        // Split the binary IP into its parts
        String[] binaryParts = binaryIP.split("\\.");

        if (binaryParts.length != 4) {
            System.out.println("Invalid IP format.");
            return;
        }

        // Convert binary parts to decimal
        int[] decimalParts = new int[4];
        for (int i = 0; i < 4; i++) {
            decimalParts[i] = Integer.parseInt(binaryParts[i], 2);
        }

        // Determine the class of the IP address
        char ipClass = getClass(decimalParts[0]);

        if (ipClass == 'X') {
            System.out.println("Invalid IP class.");
            return;
        }

        // Get the network ID and host ID in binary and decimal formats
        String networkIDBinary = "";
        String hostIDBinary = "";
        String networkIDDecimal = "";
        String hostIDDecimal = "";

        switch (ipClass) {
            case 'A':
                networkIDBinary = binaryParts[0];
                hostIDBinary = binaryParts[1] + "." + binaryParts[2] + "." + binaryParts[3];
                networkIDDecimal = decimalParts[0] + ".0.0.0";
                hostIDDecimal = decimalParts[1] + "." + decimalParts[2] + "." + decimalParts[3];
                break;
            case 'B':
                networkIDBinary = binaryParts[0] + "." + binaryParts[1];
                hostIDBinary = binaryParts[2] + "." + binaryParts[3];
                networkIDDecimal = decimalParts[0] + "." + decimalParts[1] + ".0.0";
                hostIDDecimal = decimalParts[2] + "." + decimalParts[3];
                break;
            case 'C':
                networkIDBinary = binaryParts[0] + "." + binaryParts[1] + "." + binaryParts[2];
                hostIDBinary = binaryParts[3];
                networkIDDecimal = decimalParts[0] + "." + decimalParts[1] + "." + decimalParts[2] + ".0";
                hostIDDecimal = String.valueOf(decimalParts[3]);
                break;
        }

        // Output the results
        System.out.println("Class: " + ipClass);
        System.out.println("Network ID (Binary): " + networkIDBinary);
        System.out.println("Host ID (Binary): " + hostIDBinary);
        System.out.println("Network ID (Decimal): " + networkIDDecimal);
        System.out.println("Host ID (Decimal): " + hostIDDecimal);
    }

    private static char getClass(int firstOctet) {
        if (firstOctet >= 0 && firstOctet <= 127) {
            return 'A';
        } else if (firstOctet >= 128 && firstOctet <= 191) {
            return 'B';
        } else if (firstOctet >= 192 && firstOctet <= 223) {
            return 'C';
        } else {
            return 'X'; // Invalid class
        }
    }
}
