import java.util.Scanner;

public class NetworkSubnetCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter IP address (dotted decimal notation): ");
            String ipAddress = scanner.nextLine();

            if (!isValidIPAddress(ipAddress)) {
                throw new IllegalArgumentException("Invalid IP address format");
            }

            System.out.print("Enter the required subnet in CIDR notation (e.g., 24): ");
            int subnetBits = scanner.nextInt();

            if (subnetBits < 0 || subnetBits > 32) {
                throw new IllegalArgumentException("Subnet bits should be between 0 and 32");
            }

            System.out.println("Starting Network ID of each subnetwork:");
            System.out.println("Subnet 0: " + getNetworkID(ipAddress, subnetBits));

            int numSubnets = (int) Math.pow(2, 32 - subnetBits);
            for (int i = 1; i < numSubnets; i++) {
                String subnetID = getNetworkID(ipAddress, subnetBits).substring(0, ipAddress.lastIndexOf('.') + 1) + i;
                System.out.println("Subnet " + i + ": " + subnetID);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static boolean isValidIPAddress(String ipAddress) {
        String[] octets = ipAddress.split("\\.");
        if (octets.length != 4) {
            return false;
        }

        try {
            for (String octet : octets) {
                int octetValue = Integer.parseInt(octet);
                if (octetValue < 0 || octetValue > 255) {
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static String getNetworkID(String ipAddress, int subnetBits) {
        String[] octets = ipAddress.split("\\.");
        int[] ipOctets = new int[4];
        for (int i = 0; i < 4; i++) {
            ipOctets[i] = Integer.parseInt(octets[i]);
        }

        // Convert IP address octets to binary
        StringBuilder ipBinary = new StringBuilder();
        for (int octet : ipOctets) {
            String binaryOctet = Integer.toBinaryString(256 + octet);
            ipBinary.append(binaryOctet.substring(binaryOctet.length() - 8));
        }

        // Determine the network address by applying subnet mask
        String networkIDBinary = ipBinary.substring(0, 32 - subnetBits) + "0".repeat(subnetBits);

        // Convert network ID back to decimal format
        StringBuilder networkIDDecimal = new StringBuilder();
        for (int i = 0; i < 32; i += 8) {
            int octetDecimal = Integer.parseInt(networkIDBinary.substring(i, i + 8), 2);
            networkIDDecimal.append(octetDecimal);
            if (i != 24) {
                networkIDDecimal.append('.');
            }
        }

        return networkIDDecimal.toString();
    }
}
