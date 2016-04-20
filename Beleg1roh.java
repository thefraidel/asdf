package Beleg_WS_15_16;

import java.util.*;
import Baustelle.Bauplatz;
import Baustelle.physical.*;
import java.awt.*;

public class Beleg1{
    // TODO BEGIN A0: Matrikelnummer(n) eintragen
    public static String bearbeiter = "1234567 und 7654321";
    // TODO END A0
    private static Baustrasse baustrasse;
    private static Lagerplatz lagerplatz1, lagerplatz2;

    //TODO BEGIN A1
     
    //TODO END A1
    
    private static void initialisiereBaustelle(){
        //Initialisiere Szenario (Strasse + 2 Lagerplätze)
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
        final double SANDDICHTE = 0; // Dichte in t/m^3
        final double ZIEGELDICHTE = 0; // Dichte in t/m^3
        // TODO END A2
        
        boolean nochPlatz; //ist noch Platz im LKW?

        for(int i = 0; i < lkwAnzahl; i++){
            
            //TODO BEGIN A3 
            LKW lkw;//Variablenname für LKW: lkw
            lkw = new LKW(); // diese Zeile ersetzen/aendern
            //TODO END A3
                        
            nochPlatz = true; //Boolsche Variable die beschreibt, ob der LKW weitere Materialien aufnehmen kann
            
            //Do-While-Schleife die LKW mit Materialien belädt, solange dieser noch Material aufnehmen kann
            do{
                //TODO BEGIN A4
                Material material;
                material = new Material(); // diese Zeile ersetzen/aendern (evtl. doppelt für zwei verschiedene Materialien)
                //TODO END A4
            
                
                //TODO BEGIN A5
                if(  0 == 0 ){  //TODO END A5
                    lkw.beladen(material);
                } else{
                    nochPlatz = false;
                }
                
            } while(nochPlatz);
            
            //LKW fährt zum ersten Lagerplatz   
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
             
            //LKW fährt zum zweiten Lagerplatz
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
            
            //LKW verlässt Baustelle
            lkw.fahre(400);
            lkw.setVisible(false);
        }
    }
    
    private static void erzeugeAusgabe(int lkwAnzahl){
        //TODO A6
         
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