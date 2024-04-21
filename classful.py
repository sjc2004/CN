def findClass(ip):
    try:
        ip = ip.split(".")
        ip = [int(i) for i in ip]
        
        if ip[0] >= 0 and ip[0] <= 127:
            return "A"
        elif ip[0] >= 128 and ip[0] <= 191:
            return "B"
        elif ip[0] >= 192 and ip[0] <= 223:
            return "C"
        elif ip[0] >= 224 and ip[0] <= 239:
            return "D"
        else:
            return "E"
    except:
        return "Invalid IP address format"

def separate(ip, className):
    if className == "A":
        print("Network Address is : ", ip[0])
        print("Host Address is : ", ".".join(ip[1:4]))
    elif className == "B":
        print("Network Address is : ", ".".join(ip[0:2]))
        print("Host Address is : ", ".".join(ip[2:4]))
    elif className == "C":
        print("Network Address is : ", ".".join(ip[0:3]))
        print("Host Address is : ", ip[3])
    else:
        print("In this Class, IP address is not divided into Network and Host ID")

if __name__ == "__main__":
    ip = input("Enter an IP address: ")
    networkClass = findClass(ip)
    print("Given IP address belongs to class : ", networkClass)
    
    if networkClass != "Invalid IP address format":
        ip = ip.split(".")
        separate(ip, networkClass)
