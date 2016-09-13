#TDM1 - Prise en main de l'API socket JAVA

Lancement de wireshark, dans le terminal:
```bash
wireshark
```
## Exercice 1: Echange UDP avec l'outil netcat

Sur la première console, lancer l'écoute en UDP sur le port 2000. Il n'est pas nécessaire de préciser l'hôte.
-u permet de préciser que l'on utilise le protocol UDP

```bash
netcat -u -l -p 2000
```

Sur la deuxième console, ouvrir une connexion pour envoyer le message sur le port 2000

```bash
netcat -u localhost 2000
test
test
```
**Ordre de lancement**

Il faut avoir **lancé le terminal et l'écoute sur le port en premier lieu.**
Si l'envoi d'un message est réalisé sans "écoute" le message est perdu? L'hôte renvoi un **message d'erreur ICMP**.
Le port utilisé sur le terminal d'envoi est quelconque. (en effet, si un autre programme utilise déjà le port spécifié, le message n'est pas envoyé)

## Exercice 2: UDP avec une autre machine

```bash
netcat -u @IP 2000
test
test
```
**Conclusion wireshark**

On observe bien les paquets UDP et les messages en **clair**.

## Exercice 3: envoi de datagramme UDP avec java

- Reprise du programme depuis Chamilo.
- Copier/coller
- On ajoute les IOexception (try catch)
- On change le port

```java
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ClientUdp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientUdp client = new ClientUdp();
		try {
			client.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Catch exception");
			e.printStackTrace();
		}
	}

	/**
	 * Le client cree une socket, envoie un message au serveur
	 * et attend la reponse
	 *
	 */
	private void execute() throws IOException
	{
		//
		System.out.println("Demarrage du client ...");

		//Creation de la socket
		DatagramSocket socket = new DatagramSocket();

		// Creation et envoi du message
		InetSocketAddress adrDest = new InetSocketAddress("192.168.130.20", 3000);
		byte[] bufE = new String("hello les ados").getBytes();
		DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
		socket.send(dpE);
		System.out.println("Message envoye");


		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du client .");
	}

}


```

## Exercice 4: Réception de diagramme UDP

- reprise du programme serveur depuis Chamilo
- copier/coller
- Idem précedément, ajout des excetions
- changer numéro port
- recherche de méthodes datagram packet (javadoc)
- changement du message recu avec, ```dpR.getAddress()```

```java
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ServerUdp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerUdp server = new ServerUdp();
		try {
			server.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void execute() throws IOException
	{
		//
		System.out.println("Demarrage du serveur ...");

		// Le serveur se declare aupres de la couche transport
		// sur le port 3000
		DatagramSocket socket = new DatagramSocket(null);
		socket.bind(new InetSocketAddress(3000));

		// Attente du premier message
		byte[] bufR = new byte[2048];
		DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		socket.receive(dpR);
		String message = new String(bufR, dpR.getOffset(), dpR.getLength());
		System.out.println("Message recu = "+message+" From "+dpR.getAddress()+" "+dpR.getPort());

		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du serveur .");
	}

}

```

## Exercice 5: Emission / Réception

- Changer l'adresse IP et le port pour réaliser les tests.


# TDM2 - Prise en main de l'API socket JAVA

## Exercice1 afficher une fenetre de couleur JAVA


- il créer une nouvelle fenetre avec la taille 300x300
- il affecter une couleur de background
- il active la visualisation
- il éxecute le  pour faire une pause

```java
package fr.esisar;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;
public class ColorFrame
{
public static void main(String[] args) throws IOException,
InterruptedException
{
JFrame frame = new JFrame("Chenillard");
frame.setSize(300,300);
//
frame.getContentPane().setBackground(Color.GREEN);
frame.setVisible(true);
Thread.sleep(2000);
//
frame.getContentPane().setBackground(Color.RED);
frame.setVisible(true);
Thread.sleep(2000);
frame.getContentPane().setBackground(Color.GREEN);
frame.setVisible(true);
Thread.sleep(2000);
frame.dispose();
}
}
```

## Exercice2 - Chenillard UDP

Voir code..

## Exercice3 - Chenillard UDP avec un serveur


# TDM3 -Tcp

## Exercice1 manipulation avec l'outil netcat

Lancer le serveur TCP netcat **en premier**.
```bash
nc -l -p 3000
```
 Lancer le client TCP
```bash
nc localhost 3000
```
C'est à ce moment que le **handshake** se réalise. (voir graph)

- SYN
- SYNACK
- ACK

## Exercice2 - Envoi de segment TCP av ec un programme simple

Récupérer sources sur Chamilo
Ouverture d'un listen sur le PC
```bash
nc -l -p 5099
```
Lancer le programme ClientTCP.java

## Exercice3 - Reception de segment TCP avec un programme simple.

Récupérer sources sur Chamilo
Lancer le programme ServeurTCP.java
Ouverture d'un listen sur le PC
```bash
nc localhost 5099
message
```

Lorsque l'on test d'envoyer plusieurs messages avec entrée à la fin de chaque mesage, on s'apercoit que le nombre de connexion est limité à 4. (configuré dans le programme ligne 38)

```java
while(nbClient<4)
{
	....
}
```

# TDM4 - Communication avec server de jeu

## Exercice1 - UDP

- Reprise du fichier Client UDP

Lancer le serveur java avec l'archive téléchargée sur Chamilo
```bash
java -jar tdm04-udp.jar
```
Se connecter en UDP (ne pas oublier -u)
```bash
nc -u localhost 7001
JOUER
SCORE
```

Voir ports ouvert sur machine
```bash
netstat
```
