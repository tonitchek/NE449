# ne449 - programmation repartie



## TDM01 Découverte **UDP**
**Package/src**
- tdm1Ex1
 - ClientUdp.java
 - ServerUdp.java
**Commentaires**
Communication client/Server UDP. Utilisation des ```datagrameSocket```

## TDM02 Chenillard **UDP**
**Package/src**
- tdm2Ex1
 - Chenilliard.java
 - ClientUdp.java
 - COlorFrame.java
 - ServerUdp.java
**Commentaires**
Chenilliard, utilisation de UDP

**Package/src**
- tdm2Ex3Client
 - Chenilliard.java
 - ClientListen.java
 - ClientSend.java
 - COlorFrame.java
- tdm2Ex3Server
 - ChenilliardServer.java
 - Client.java
 - Server.java
 **Commentaires**
 Chenilliard autoadaptatif.
 Utilisation de différentes classes pour les fonctionnalités du client client.send("ACK"), ClientListen.waitForServerInstruction etc..
 Le server contrôle l'état des clients

## TDM03 Fin UDP + Découverte TCP
**Package/src**
- tdm3Src
 - ClientTCP.java
 - ServeurTCP.java
 **Commentaires**
Communications client/server en TCP. Sources sans modifs spéciales par rapport à celles qui nous ont été fournies.

## TDM04 Décodage contenu flux UDP et TCP
**Package/src**
- tdm4Ex1
 - ClientUdp.java
 - PlayClient.java
 **Commentaires**
Jouer avec le serveur UDP et réponse de la somme.

**Package/src**
- tdm4Ex3TCP
 - ClientTCP.java
 - PlayClientTCP.java
 - QUestion.java
 - tdm04-tcp.jar
 **Commentaires**
Jouer avec le serveur TCP et analyse des trames pour traiter les stream recu non complet

## TDM05 Transfert de fichiers par TCP
**Package/src**
- tdm5Ex1AB
 - FileCopy.java
- tdm5Ex1Yo
 - testCopy.java
 **Commentaires**
Copier un fichier avec FileInputStream et FileOutputStream

**Package/src**
- tdm5Ex2Client
 - BarProgress.java
 - ClientTCP.java
 - TransfertFileClient.java
- tdm5Ex2Server
 - TransFile.java
 - TransFileServer.java
 **Commentaires**
Copie d'un fichier entre un client et un serveur multithread avec bar de progression et avec pool thread getState

## TDM06 Gestion d'un buffer tournant
**Package/src**
- tdm6Ex1
 - AirbagEx1.java
- tdm6ex1Yo
 - Exo1.java
 - jFrame.java
- tdm6Ex2
 - AirbagEx2.java
- tdm6ex2Yo
 - Exo2.java
 **Commentaires**
Exercice de lecture et écriture avec des buffers de tailles différentes. Notions de **buffer offset**

## TDM07 Découverte des threads
**Package/src**
- tdm7Ex1
 - PiMonoThread.java
- tdm7Ex2
 - PiMultiThread.java
 - PiThread.java
- tdm7Ex3
 - TaskForce.java
 - ThreadSync.java
  **Commentaires**
Approximation de Pi avec threads

## TDM08 Le problème des philosophes
**Package/src**
- tdm8
 - Calculatrice.java
 - PhilosopheEx1.java
 - PhilososopheThread.java
  **Commentaires**
Thread avec priorisation des philosophes

## TDM09 Transfert de fichiers multithreadé
**Commentaires**
Voir TDM5
## TDM10 Découverte HTTP + Savoir prendre le bus
**Package/src**
- tdm10Yo
 - Bus2.java
 - Bus3.java
**Commentaires**
Bus2 avec exemple simpledateformat et bus 3 avec list

## TDM11 Réalisation d'un serveur de fichier HTTP
**Package/src**
- tdm11Yo
 - ClientTCP.java
 - ServeurTCP.java
 - SimpleHttpServer.java
**Commentaires**


## TDM12 Examen sur machine
