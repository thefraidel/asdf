package Beleg_WS_15_16;

import java.util.*;
import Baustelle.Bauplatz;
import Baustelle.physical.*;
import java.awt.*;

public class Beleg1{
    // TODO BEGIN A0: Matrikelnummer(n) eintragen
    public static String bearbeiter = "4532592";
    // TODO END A0
    private static Baustrasse baustrasse;
    private static Lagerplatz lagerplatz1, lagerplatz2;

    //TODO BEGIN A1
     public static void main(String[]args){
        createUUID();
        
        initialisiereBaustelle();
        
        int zuf;
        zuf = (int)(Math.random()*(5-2+1)+2);
        
        simuliereBaustelle(zuf);
        
        erzeugeAusgabe(zuf);
        
        System.out.println(createUUID());
        
        }
    //TODO END A1
    
    private static void initialisiereBaustelle(){
        //Initialisiere Szenario (Strasse + 2 Lagerplaetze)
        baustrasse = new Baustrasse(0,100,400,100,40);
        baustrasse.AddSegment(700,90);       
        baustrasse.setVisible(true);
        
        lagerplatz1 = new Lagerplatz();
        lagerplatz1.setX(200);
        lagerplatz1.setY(60);
        lagerplatz1.setDx(80);
        lagerplatz1.setDy(40);
        lagerplatz1.setVisible(true);
        
        lagerplatz2 = new Lagerplatz();
        lagerplatz2.setX(440);
        lagerplatz2.setY(400);
        lagerplatz2.setDx(40);
        lagerplatz2.setDy(80);
        lagerplatz2.setVisible(true);
        
    }
    
    private static void simuliereBaustelle(int lkwAnzahl){
        // TODO BEGIN A2
        final double SANDDICHTE = 1.6; // Dichte in t/m^3
        final double ZIEGELDICHTE =1.9; // Dichte in t/m^3
        // TODO END A2
        
        boolean nochPlatz; //ist noch Platz im LKW?
      
        for(int i = 0; i < lkwAnzahl; i++){
            
            //TODO BEGIN A3 
            LKW lkw;//Variablenname fuer LKW: lkw
            lkw = new LKW(0,100,0); // diese Zeile ersetzen/aendern
            lkw.setMaxZuladung(25);
            lkw.setGeschwindigkeit(5);
            lkw.setVisible(true);
            //TODO END A3
                        
            nochPlatz = true; //Boolsche Variable die beschreibt, ob der LKW weitere Materialien aufnehmen kann
           
            //Do-While-Schleife die LKW mit Materialien belaedt, solange dieser noch Material aufnehmen kann
            
                   double ent;
                   ent = (double)(Math.random()*(1));
                
                  double gewichtIst;
                  gewichtIst=0;
                 
                   
            do{
                 Material material;
                 if(ent>=0.5){
                    material=new Material("Sand");
                    material.setVolumen((double)Math.random()*(0.15-0.08)+0.08);
                    material.setGewicht(material.getVolumen()*SANDDICHTE);
                     
                    }else{
                    
                    material=new Material("Ziegel");
                    material.setVolumen((double)Math.random()*(0.15-0.08)+0.08);
                    material.setGewicht(material.getVolumen()*ZIEGELDICHTE);
                        
                    
                    }
                        
                      
               
                 
                //TODO END A4
            
                
                //TODO BEGIN A5
                   if( gewichtIst+ material.getGewicht() <= lkw.getMaxZuladung()){  //TODO END A5
                     lkw.beladen(material);
                     gewichtIst= material.getGewicht()+gewichtIst;
                    } else{
                     nochPlatz = false;
                    
                    }
                
            } while(nochPlatz);
            
            //LKW faehrt zum ersten Lagerplatz   
            lkw.fahre(170);
            
            //Abladen von Material Sand am ersten Lagerplatz
            for(int j = 0; j < lkw.getMaterialAnzahl(); j++){
                Material material = lkw.getMaterialListe().get(j);
                if (material.getMatName() == "Sand"){
                     try{
                        Thread.sleep(300);
                    } catch (Exception e){;}
                    lkw.entladeAufLagerplatz(material, lagerplatz1);
                    lagerplatz1.visualisiereMaterial(material);
                }
            }
             
            //LKW faehrt zum zweiten Lagerplatz
            lkw.fahre(230);
            lkw.drehen(90);
            lkw.fahre(300);
            
            //Abladen von Material Ziegel am zweiten Lagerplatz
            for(int j = 0; j < lkw.getMaterialAnzahl(); j++){
                Material material = lkw.getMaterialListe().get(j);
                if (material.getMatName() == "Ziegel"){
                    try{
                        Thread.sleep(300);
                    } catch (Exception e){;}
                    lkw.entladeAufLagerplatz(material,lagerplatz2);
                    lagerplatz2.visualisiereMaterial(material);
                }
            }
                
            System.out.println("//gewichtIst (zur Kontrolle): "+gewichtIst);
            System.out.println("//MaterailAnzahl. "+lkw.getMaterialAnzahl());
            //LKW verlaesst Baustelle
            lkw.fahre(400);
            lkw.setVisible(false);
        }
    }
    
    private static void erzeugeAusgabe(int lkwAnzahl){
        //TODO A6
        System.out.println("Gefahrene LKW: " + lkwAnzahl);

        //Lagerplatz 1: Sand
        System.out.println("Lagerplatz 1: Sand");

        double gesamtGewicht1 = 0;
        double gesamtVolumen1 = 0;

        for (Material mat : lagerplatz1.getMaterialListe()) {
            gesamtGewicht1 += mat.getGewicht();
            gesamtVolumen1 += mat.getVolumen();
        }

        System.out.println("Gesamtvolumen: " + gesamtVolumen1);
        System.out.println("Gesamtgewicht: " + gesamtGewicht1);

        //Lagerplatz 2: Ziegel
        System.out.println("Lagerplatz : Ziegel");

        double gesamtGewicht2 = 0;
        double gesamtVolumen2 = 0;

        for (Material mat : lagerplatz2.getMaterialListe()) {
            gesamtGewicht2 += mat.getGewicht();
            gesamtVolumen2 += mat.getVolumen();
        }

        System.out.println("Gesamtvolumen: " + gesamtVolumen2);
        System.out.println("Gesamtgewicht: " + gesamtGewicht2);
        //TODO END A6
    }
     
    

    private static String createUUID() {
        String id = bearbeiter;   
        try {
            UUID uid = UUID.fromString(id);
            id = bearbeiter;
        }
        catch(Exception e) { UUID uuid = UUID.nameUUIDFromBytes(id.getBytes()); id = uuid.toString(); return id; }
        return id;
    }
   
}