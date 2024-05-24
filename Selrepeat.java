#clientside 
import socket
import random

def main():
    try:
        v = [0] * 10
        n = 0
        rand = 0

        server_address = ('localhost', 8011)
        connection = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        connection.connect(server_address)
        print(f"Connected to {server_address}")

        p = int(connection.recv(1024).decode())
        print("No of frame is:", p)

        for i in range(p):
            v[i] = int(connection.recv(1024).decode())
            print(v[i])

        rand = random.randint(0, p-1)  # FRAME NO. IS RANDOMLY GENERATED
        v[rand] = -1

        for i in range(p):
            print("Received frame is:", v[i])

        for i in range(p):
            if v[i] == -1:
                print("Request to retransmit from packet no", i+1, "again!!")
                n = i
                connection.send(str(n).encode())
                break

        print()
        v[n] = int(connection.recv(1024).decode())
        print("Received frame is:", v[n])

        print("Quitting")
    except Exception as e:
        print(e)
    finally:
        connection.close()

if __name__ == "__main__":
    main()
 #Serverside 
  import socket

def main():
    try:
        a = [30, 40, 50, 60, 70, 80, 90, 100]

        server_address = ('localhost', 8011)
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_socket.bind(server_address)
        server_socket.listen(1)

        print("Waiting for connection...")
        client_socket, client_address = server_socket.accept()
        print(f"Connection established with {client_address}")

        print("The number of packets sent is:", len(a))
        client_socket.send(str(len(a)).encode())

        for i in range(len(a)):
            client_socket.send(str(a[i]).encode())

        k = int(client_socket.recv(1024).decode())
        client_socket.send(str(a[k]).encode())

    except Exception as e:
        print(e)
    finally:
        server_socket.close()

if __name__ == "__main__":
    main()

  
