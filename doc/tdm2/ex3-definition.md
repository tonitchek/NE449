#TDM2 Ex3 - Definition

## Main parameters
Server port listening: 8888
max client: 32
client port: 8800+index (0 <= index <= 31)

##Protocol
Client request for registration: "R<index>P<port>"
last client request for registration: "R<index>P<port>#"
Server response to client request for registration: "ACK"

Server instruction to client for red color: "C0"
Server instruction to client for red color: "C1"
Client response to server instruction for color: "ACK"

Server instruction to client for stop: "S"

## Client frame position
50*index+5

## Client command line arguments
0: index
1: server hostname
2: server port
3: listening port

## Server command line arguments
0: max client connection
1: listening port
