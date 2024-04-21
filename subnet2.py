import ipaddress

def calculate_subnets(ip, num_subnets):
    # Determine the minimum number of bits required to cover the number of subnets
    required_bits = 0
    while (1 << required_bits) < num_subnets:
        required_bits += 1
    
    # Determine the base IP and the original prefix length
    network = ipaddress.ip_network(ip, strict=False)
    new_prefix_length = network.prefixlen + required_bits
    
    if new_prefix_length > 32:  # For IPv4 addresses
        raise ValueError("Too many subnets requested")
    
    # Calculate the new subnet mask
    subnet_mask = ipaddress.ip_network(f'0.0.0.0/{new_prefix_length}').netmask
    print(f"Subnet Mask: {subnet_mask}")
    
    # Generate each subnet's starting address
    subnets = list(network.subnets(new_prefix_length - network.prefixlen))
    for idx, subnet in enumerate(subnets):
        print(f"Subnet {idx + 1} Start Address: {subnet.network_address}")

# Example usage:
ip = '192.168.1.8/24'
num_subnets = 2
calculate_subnets(ip, num_subnets)
