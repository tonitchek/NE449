# Definition

## Protocol
- client sends "filename.ext" to the server
- if file does not exist on server, it sends an error message CAN (cancel, 0x18). Otherwise, it starts a thread to transfer the file to client
- the thread opens the file and sends 1024 bytes each 500ms until end of file
- client receives each 1024 bytes each 500ms and write it to the local file
- thread sends EOT byte (0x4) to finish the transmission and closes the file + socket
- client receive EOT byte, close the file + socket

## Client
### Args
\<host\> :IP address or hostname of the server
\<port\> :port of the server

## Server
### Args
\<port\> :listening port
\<client max\> :maximum number of client to accept

