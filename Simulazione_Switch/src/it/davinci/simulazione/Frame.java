package it.davinci.simulazione;

import java.util.Random;

public class Frame
{
    private Boolean presente; // Indica se il frame è presente o no
    private Integer portaDestinazione; // Porta di destinazione del frame
    private Integer priorita; // Priorità del frame (1: Audio, 2: Video, 3: Dati)

    // Costruttore che genera casualmente un frame
    public Frame()
    {
        Random random = new Random();
        this.presente = random.nextBoolean(); // Assegna casualmente se il frame esiste o no
        if(this.presente){ // Se esiste
            this.portaDestinazione = random.nextInt(Switch.numPorte); // Assegna una porta di destinazione casuale tra quelle disponibili nello switch
            this.priorita = random.nextInt(3) + 1; // Assegna una priorità casuale (1-3)
        }
    }

    // Costruttore con parametri specifici
    public Frame(Boolean presente, Integer portaDestinazione, Integer priorita)
    {
        this.presente = presente;
        this.portaDestinazione = portaDestinazione;
        this.priorita = priorita;
    }

    // Getter per la presenza del frame
    public Boolean getPresente()
    {
        return this.presente;
    }

    // Getter per la porta di destinazione
    public Integer getPriorita()
    {
        return this.priorita;
    }

    // Getter per la priorità
    public Integer getPortaDestinazione()
    {
        return this.portaDestinazione;
    }
    /*
     * Priorità:
     * 3. Audio --> Massima priorità
     * 2. Video
     * 1. Altro --> Minima priorità
     */
}