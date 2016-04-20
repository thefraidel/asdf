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
        zuf = (int)(Math.random()*(5-2)+2);
        
        simuliereBaustelle(zuf);
        
        erzeugeAusgabe(zuf);
        
        System.out.println(createUUID());
        
        }
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
        final double SANDDICHTE = 1.6; // Dichte in t/m^3
        final double ZIEGELDICHTE =1.9; // Dichte in t/m^3
        // TODO END A2
        
        boolean nochPlatz; //ist noch Platz im LKW?

        for(int i = 0; i < lkwAnzahl; i++){
            
            //TODO BEGIN A3 
            LKW lkw;//Variablenname für LKW: lkw
            lkw = new LKW(0,100,0); // diese Zeile ersetzen/aendern
            lkw.setMaxZuladung(25);
            lkw.setGeschwindigkeit(5);
            lkw.setVisible(true);
            //TODO END A3
                        
            nochPlatz = true; //Boolsche Variable die beschreibt, ob der LKW weitere Materialien aufnehmen kann
           
            //Do-While-Schleife die LKW mit Materialien belädt, solange dieser noch Material aufnehmen kann
            
            
            do{
                    //TODO BEGIN A4
                   
                     
                  int entsch;
                   
                   entsch= (int)(Math.random()*(1-0)+0);
                
                
                   Material material; 
                    
                       
                   

            
                                   
                                        
                if( entsch==0){
                    material = new Material("Sand");// diese Zeile ersetzen/aendern (evtl. doppelt für zwei verschiedene Materialien)
                     //material.setGewicht(0);
                    double vol;             
                    vol=(double)(Math.random()*(0.15-0.8)+0.8);
                    material.setGewicht(material.getGewicht()+vol*SANDDICHTE);
                
                }else{
                    material = new Material("Ziegel");
                   // material.setGewicht(0);
                    double vol;             
                    vol=(double)(Math.random()*(0.15-0.8)+0.8);
                    material.setGewicht(material.getGewicht()+vol*ZIEGELDICHTE);
                    }
            
                    
                
               
              
                    /*
                Material material;
                material=new Material("Sand");
                material.setGewicht(25);
                                         
                */
                //TODO END A4
            
                
                //TODO BEGIN A5
                if( lkw.getMaxZuladung()  <  material.getGewicht()){  //TODO END A5
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