package it.davinci.simulazione;

import java.util.Vector;

public class Porta
{
    private Vector<Frame> codaPorta; // Coda generale dei frame in ingresso
    private Vector<Frame> codaAudio; // Coda dei frame con priorità Audio
    private Vector<Frame> codaVideo; // Coda dei frame con priorità Video
    private Vector<Frame> codaDati; // Coda dei frame con priorità Dati

    // Costruttore generale
    public Porta()
    {
        this.codaPorta = new Vector<Frame>();
        this.codaAudio = new Vector<Frame>();
        this.codaVideo = new Vector<Frame>();
        this.codaDati = new Vector<Frame>();
    }

    // Costruttore con code frame già esistenti
    public Porta(Vector<Frame> codaPorta, Vector<Frame> codaAudio,
                 Vector<Frame> codaVideo, Vector<Frame> codaDati)
    {
        this.codaPorta = codaPorta;
        this.codaAudio = codaAudio;
        this.codaVideo = codaVideo;
        this.codaDati = codaDati;
    }

    // Getter per la coda generale
    public Vector<Frame> getCodaPorta()
    {
        return this.codaPorta;
    }

    // Getter per la coda Audio
    public Vector<Frame> getCodaAudio()
    {
        return this.codaAudio;
    }

    // Getter per la coda Video
    public Vector<Frame> getCodaVideo()
    {
        return this.codaVideo;
    }

    // Getter per la coda Dati
    public Vector<Frame> getCodaDati()
    {
        return this.codaDati;
    }

    // Metodo per smistare i frame nelle code di priorità
    public Boolean aggiungiFrameACodePriorita(){
        // Se la coda generale è vuota, ritorna false
        if(this.codaPorta.isEmpty()){
            return false;
        }
        // Processa ogni frame nella coda generale e lo smista nella coda di priorità appropriata
        while(!this.codaPorta.isEmpty()){
            switch(this.codaPorta.firstElement().getPriorita()){
                case 1: // Priorità Audio
                    if(Switch.isGUI){
                        GUI.textArea.append(String.format("\nFrame con priorità Audio aggiunto alla coda Audio"));
                    }
                    System.out.println("Frame con priorità Audio aggiunto alla coda Audio");

                    this.codaAudio.add(this.codaPorta.firstElement());
                    this.codaPorta.remove(this.codaPorta.firstElement());
                    break;
                case 2: // Priorità Video
                    if(Switch.isGUI){
                        GUI.textArea.append(String.format("\nFrame con priorità Video aggiunto alla coda Video"));
                    }
                    System.out.println("Frame con priorità Video aggiunto alla coda Video");

                    this.codaVideo.add(this.codaPorta.firstElement());
                    this.codaPorta.remove(this.codaPorta.firstElement());
                    break;
                case 3: // Priorità Dati
                    if(Switch.isGUI){
                        GUI.textArea.append(String.format("\nFrame con priorità Dati aggiunto alla coda Dati"));
                    }
                    System.out.println("Frame con priorità Dati aggiunto alla coda Dati");

                    this.codaDati.add(this.codaPorta.firstElement());
                    this.codaPorta.remove(this.codaPorta.firstElement());
                    break;
            }
        }
        return true;
    }
}