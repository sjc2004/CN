import java.util.Scanner;

public class IPAddressClassifier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an IP address: ");
        String ip = scanner.nextLine();
        
        String networkClass = findClass(ip);
        System.out.println("Given IP address belongs to class: " + networkClass);
        
        if (!networkClass.equals("Invalid IP address format")) {
            separate(ip, networkClass);
        }
        
        scanner.close();
    }

    public static String findClass(String ip) {
        try {
            String[] parts = ip.split("\\.");
            int[] ipParts = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                ipParts[i] = Integer.parseInt(parts[i]);
            }

            if (ipParts[0] >= 0 && ipParts[0] <= 127) {
                return "A";
            } else if (ipParts[0] >= 128 && ipParts[0] <= 191) {
                return "B";
            } else if (ipParts[0] >= 192 && ipParts[0] <= 223) {
                return "C";
            } else if (ipParts[0] >= 224 && ipParts[0] <= 239) {
                return "D";
            } else if (ipParts[0] >= 240 && ipParts[0] <= 255) {
                return "E";
            } else {
                return "Invalid IP address format";
            }
        } catch (Exception e) {
            return "Invalid IP address format";
        }
    }

    public static void separate(String ip, String className) {
        String[] parts = ip.split("\\.");
        switch (className) {
            case "A":
                System.out.println("Network Address is: " + parts[0]);
                System.out.println("Host Address is: " + parts[1] + "." + parts[2] + "." + parts[3]);
                break;
            case "B":
                System.out.println("Network Address is: " + parts[0] + "." + parts[1]);
                System.out.println("Host Address is: " + parts[2] + "." + parts[3]);
                break;
            case "C":
                System.out.println("Network Address is: " + parts[0] + "." + parts[1] + "." + parts[2]);
                System.out.println("Host Address is: " + parts[3]);
                break;
            default:
                System.out.println("In this class, IP address is not divided into Network and Host ID");
                break;
        }
    }
}
