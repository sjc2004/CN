#MulticastDateServer.py
import socket
import struct
import time
from datetime import datetime

MCAST_GRP = '230.0.0.1'
MCAST_PORT = 1313
MULTICAST_TTL = 2

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
sock.setsockopt(socket.IPPROTO_IP, socket.IP_MULTICAST_TTL, MULTICAST_TTL)

for _ in range(10):
    message = datetime.now().strftime('%Y-%m-%d %H:%M:%S').encode('utf-8')
    sock.sendto(message, (MCAST_GRP, MCAST_PORT))
    print(f"Sent: {message.decode('utf-8')}")
    time.sleep(1.5)

sock.close()
#MulticastDateClient.py
import socket
import struct

MCAST_GRP = '230.0.0.1'
MCAST_PORT = 1313

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
sock.bind(('', MCAST_PORT))

mreq = struct.pack("4sl", socket.inet_aton(MCAST_GRP), socket.INADDR_ANY)
sock.setsockopt(socket.IPPROTO_IP, socket.IP_ADD_MEMBERSHIP, mreq)

for _ in range(10):
    data, _ = sock.recvfrom(256)
    print(f"Current server time: {data.decode('utf-8')}")

sock.setsockopt(socket.IPPROTO_IP, socket.IP_DROP_MEMBERSHIP, mreq)
sock.close()
