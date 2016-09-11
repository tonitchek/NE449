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
