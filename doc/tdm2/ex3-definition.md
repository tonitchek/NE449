#TDM2 Ex3 - Definition

##Protocol
Client request for registration: "R\<index\>P\<port\>"  
last client request for registration: "R\<index\>P\<port\>#"  
Server response to client request for registration: "ACK"  

Server instruction to client for red color: "RED"  
Server instruction to client for green color: "GREEN"  
Client response to server instruction for color: "ACK"  

Server instruction to client for stop: "SHUTDOWN"  

## Client port
client port: 8800+index (0 <= index <= max client)

## Client frame position
50*index+5

## Client command line arguments
0: index  
1: server hostname  
2: server port  
3: listening port  
4: last client (=1 if last client or 0 or ignored otherwise)

## Server command line arguments
0: max client connection  
1: listening port  
