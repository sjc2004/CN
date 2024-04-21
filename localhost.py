import socket
localhost=socket.gethostname()
ip=socket.gethostbyname(localhost)
print(localhost)
print(ip)
