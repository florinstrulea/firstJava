/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package exorpg;

import java.util.Scanner;

import exorpg.RPG.*;
import java.util.ArrayList;
//import java.util.Math

//import javax.swing.JOptionPane;
//import javafx.application.Application;

public class App {

    static BasicItem[] armes = new BasicItem[5];
    static BasicItem[] potions = new BasicItem[2];
    static BasicItem[] armures = new BasicItem[2];

    public static void main(String[] args) {

        Arme a1 = new Arme("Weapon: sabre laser", 30, 0.5f);
        Arme a2 = new Arme("Weapon: fourchette magique", 20, 0.35f);
        Arme a3 = new Arme("Weapon: poigneé en acier", 25, 0.25f);
        Arme a4 = new Arme("Weapon: Lance pierre", 25, 0.15f);
        Arme a5 = new Arme("Weapon: Caresse du diable", 25, 0.55f);
        Armure arm1 = new Armure("Shield: force shield");
        arm1.setDefense(15);
        Armure arm2 = new Armure("Shield: plaque en bois");
        arm1.setDefense(5);
        PotionSoin p1 = new PotionSoin("Potion:Life giver");
        p1.setPvRendu(25);
        PotionSoin p2 = new PotionSoin("Potion: Eau de vie");
        p2.setPvRendu(20);
        armes[0] = a1;
        armes[1] = a2;
        armes[2] = a3;
        armes[3] = a4;
        armes[4] = a5;
        potions[0] = p1;
        potions[1] = p2;
        armures[0] = arm1;
        armures[1] = arm2;
        Personnage paul = new Personnage("Jean-Paul");
        Personnage[] monstres = new Personnage[2];

        paul.setPv(100);
        paul.setForce(10);

        monstres[0] = new Personnage("Slime", 25, 3);
        monstres[0].setArmor(new Armure("Aucune", 0));
        monstres[0].setEquipedWeapon(new Arme("Crachat", 2, 0.1f));

        monstres[1] = new Personnage("Gobelin", 230, 6);
        monstres[1].setArmor(new Armure("Côte de maill", 15));
        monstres[1].setEquipedWeapon(new Arme("Dague", 10, 0.05f));

        // System.out.println("Vous incarnez le héro suivant " + paul);
        // System.out.println("Il a " + paul.getForce() + " Force");

        Arme epee = new Arme("Epee rouillée", 10, 0.2f);
        Armure carton = new Armure("Cartons sctochés", 10);

        Arme dague = new Arme("Dague en fer", 15, 0.25f);

        paul.setArmor(carton);
        paul.setEquipedWeapon(epee);

        paul.ajouterItem(dague);
        dague.equip(paul);

        Personnage toto = new Personnage("Toto");
        toto.setArmor(carton);

        // int i = 0;
        // for (Personnage personnage : monstres) {
        // combattre(paul, personnage);
        // System.out.println(i++);
        // }
        combattre(monstres[1]);
    }

    public static void combattre(Personnage monster) {
        int i = 0;
        System.out.println("What is your name young padawan");
        // String name = JOptionPane.showInputDialog("What is your name young padawan");
        Scanner scan = new Scanner(System.in);
        Personnage p1 = new Personnage(scan.nextLine());
        // Personnage p1 = new Personnage(name);
        p1.setInventaire(initInventaireHero());
        p1.setPv(200);
        p1.setArmor((Armure) armures[1]);
        p1.setEquipedWeapon((Arme) armes[1]);
        Personnage p2 = monster;
        p2.setInventaire(initInventaireVillain());
        System.out.println("This is your inventory");
        // JOptionPane.showMessageDialog(null, "This is your inventory");
        showInventory(p1);

        System.out.println("Vous rencontrez l'ennemi " + p2.getNom());
        System.out.println(p2.getNom() + " a l'arme " + p2.getEquipedWeapon().getNom() + " qui peut provoquer "
                + p2.getEquipedWeapon().getDegats());

        while (p1.getPv() > 0 && p2.getPv() > 0)

        {
            if (i % 2 == 0) {
                while (p1.getArmor() == null) {
                    System.out.println("Vous avez :");
                }

                System.out.println(p1.getPv() + " points de vie");
                System.out.println("Que voulez vous faire");
                System.out.println("Action:");
                System.out.println("0: Attaquer");
                System.out.println("1: Consommer une potion");
                if (scan.nextInt() == 0) {
                    equiped(p1, scan);
                    p1.attaquer(p2);
                } else {
                    System.out.println("Vous avez 2 potions dans votre inventaire");

                    showInventory(p1);

                    System.out.println("Quel potion choisissez vous ?");
                    while (scan.nextInt() < p1.getInventaire().size() - 2
                            || scan.nextInt() > p1.getInventaire().size() - 1) {
                        System.out.println("Les potions se retrouvent entre " + (int) (p1.getInventaire().size() - 2)
                                + " et " + (int) (p1.getInventaire().size() - 1) + " Quel potion choisissez vous ?");
                    }
                    PotionSoin potion = (PotionSoin) p1.getInventaire().get(scan.nextInt());
                    potion.consommer(p1);
                    p1.getInventaire().remove(scan.nextInt());
                }

            }

            else {
                p2.attaquer(p1);
            }

            i++;
        }

        System.out.println("Le vainqueur est : " + ((p1.getPv() > 0) ? p1 : p2));
        scan.close();
    }

    public static ArrayList<BasicItem> initInventaireHero() {
        ArrayList<BasicItem> monInventaire = new ArrayList<>();
        for (BasicItem arme : armes) {
            monInventaire.add(arme);
        }
        for (BasicItem armure : armures)
            monInventaire.add(armure);

        for (BasicItem potion : potions)
            monInventaire.add(potion);
        return monInventaire;
    }

    public static ArrayList<BasicItem> initInventaireVillain() {
        ArrayList<BasicItem> monInventaire = new ArrayList<>();
        int randomWeaponNb = (int) (Math.random() * (2 - 0 + 1) + 0);
        int randomShieldNb = (int) (Math.random() * (2 - 0 + 1) + 0);
        monInventaire.add(armes[randomWeaponNb]);
        monInventaire.add(armes[randomShieldNb]);

        return monInventaire;
    }

    public static void equiped(Personnage personnage, Scanner scan) {
        System.out.println(
                "Chosissez une arme ou armure à equiper ? ");
        showInventory(personnage);
        String resp = scan.nextLine();
        if (resp.compareTo("Y") == 0) {
            int itemNb = scan.nextInt();
            BasicItem el = personnage.getInventaire().get(itemNb);

            if (el instanceof Arme)
                personnage.equip((Arme) personnage.getInventaire().get(itemNb));
            else if (el instanceof Armure)
                personnage.equip((Armure) personnage.getInventaire().get(itemNb));
            else
                equiped(personnage, scan);
        }

    }

    public static void showInventory(Personnage p1) {
        System.out.println("This is your inventory");
        int j = 0;

        // String text = "";
        for (BasicItem element : p1.getInventaire()) {
            // text += j + ":" + element.getNom() + "\n";
            System.out.println(j + ":" + element.getNom());
            j++;
        }
        // JOptionPane.showMessageDialog(null, text);
    }

}