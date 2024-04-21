def findNetAndHost(ip_with_cidr):
    try:
        ip, cidr = ip_with_cidr.split("/")
        ip = ip.split(".")
        ip = [int(i) for i in ip]
        
        # Calculate subnet mask from CIDR notation
        subnet_mask = [0, 0, 0, 0]
        for i in range(int(cidr)):
            subnet_mask[i // 8] |= (1 << (7 - i % 8))

        # Applying bitwise AND operation to find network address
        net_id = [str(ip[i] & subnet_mask[i]) for i in range(4)]
        host_id = [str(ip[i] & (255 - subnet_mask[i])) for i in range(4)]

        return ".".join(net_id), ".".join(host_id)
    except:
        return "Invalid IP address format"


if __name__ == "__main__":
    ip_with_cidr = input("Enter an IP address in the format '123.23.4.5/23': ")

    net_id, host_id = findNetAndHost(ip_with_cidr)
    print("Network ID: ", net_id)
    print("Host ID: ", host_id)
