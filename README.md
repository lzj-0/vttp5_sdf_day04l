

IPaddress (IPv4)
- is like a block number but describes the server
- consists of 4 bytes (1 byte = 8 bit)
- 1 byte can range from 0 - 255

Server:
- Any client that wants to communicate with another program in a server will require knowing the IPaddress (block) and the port number (unit) (32 bit)
- IPaddress gets the client to the server, port numbers gets it to a program running in the server
- Each program in a server can only have unique port numbers
- programs inside a server only require port number

Protocols:
- TCP
    - requires a connection
    - there is a state (sending information sequentially maintains order)
- UDP
    - does not need connection (like sending a mail)
    - order of information can change

Threads:
- entry point: Runnable Interface
- Runnable only has 1 method: run()
- concurrent != parallel
- concurrent: workers alternate each other to complete jobs (time sharing)
- parallel: workers working at the same time
