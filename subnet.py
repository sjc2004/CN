def generate_subnets(update_address, num_subnets):
    # Split the update address into IP and prefix length
    ip, prefix_length = update_address.split('/')
    prefix_length = int(prefix_length)

    # Convert the IP address to a list of integers
    ip_parts = list(map(int, ip.split('.')))

    # Calculate the subnet mask
    subnet_mask = [0, 0, 0, 0]
    for i in range(prefix_length):
        subnet_mask[i // 8] |= 1 << (7 - i % 8)

    # Calculate the increment value for each subnet
    increment = 2 ** (32 - prefix_length) // num_subnets

    # Print the subnet mask and starting address of each subnet
    for i in range(num_subnets):
        print(f"Subnet {i+1}:")
        
        # Calculate the starting address of the subnet
        subnet_address = [ip_parts[j] + (i * increment >> (24 - j * 8)) & 255 for j in range(4)]
        
        print("Subnet Mask:", '.'.join(map(str, subnet_mask)))
        print("Starting Address:", '.'.join(map(str, subnet_address)))
        print()

# Example usage
update_address = "192.168.0.0/24"  # Update this with your address
num_subnets = 4  # Update this with the number of subnets required
generate_subnets(update_address, num_subnets)
