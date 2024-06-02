package it.davinci.simulazione;

import java.util.Scanner;
import java.util.Vector;

public class Switch
{
    private String nome; // Nome dello switch
    private String marca; // Marca dello switch
    private Integer tempoSimulazione; // Durata della simulazione (in secondi)
    public static Boolean isGUI; // Indica se la simulazione utilizza o meno la GUI
    public static Integer numPorte; // Numero di porte presenti nello switch
    private Vector<Porta> elencoPorte; // Elenco delle porte dello switch

    // Costruttore principale
    public Switch(String nome, String marca, Integer tempoSimulazione, Boolean isGUI, Integer numPorte)
    {
        this.nome = nome;
        this.marca = marca;
        this.tempoSimulazione = tempoSimulazione;
        Switch.isGUI = isGUI;
        Switch.numPorte = numPorte;
        this.elencoPorte = new Vector<Porta>(0, Switch.numPorte);  // Inizializzazione dell'elenco delle porte con dimensione fissa
        for(int i=0; i<Switch.numPorte; i++){
            this.elencoPorte.add(new Porta()); // Aggiunta delle porte all'elenco delle porte
        }
    }

    // Costruttore con elenco di porte già esistente
    public Switch(String nome, String marca, Integer tempoSimulazione, Boolean isGUI, Integer numPorte, Vector<Porta> elencoPorte)
    {
        this.nome = nome;
        this.marca = marca;
        this.tempoSimulazione = tempoSimulazione;
        Switch.isGUI = isGUI;
        Switch.numPorte = numPorte;
        this.elencoPorte = elencoPorte;
    }

    // Getter per l'attributo isGUI
    public Boolean getIsGUI()
    {
        return Switch.isGUI;
    }

    // Metodo per avviare la simulazione
    public void simula()
    {
        new Thread(() -> {
            Integer tempo = 1; // Tempo iniziale
            Double mediaGen = 0.0; // Media generica delle code
            while(tempo <= this.tempoSimulazione){ // Ciclo fino al termine della simulazione
                if(tempo == 1){ // Se tempo è 1
                    if(Switch.isGUI){ // Se la GUI è presente
                        GUI.textArea.append(String.format("Tempo (secondi): "+tempo)); // Stampa del tempo corrente nella GUI
                    }
                }
                else{
                    if(Switch.isGUI){
                        GUI.textArea.append(String.format("\n\nTempo (secondi): "+tempo));
                    }
                }
                System.out.println("Tempo (secondi): "+tempo); // Stampa del tempo corrente nel terminale
                Frame frame = new Frame(); // Creazione di un nuovo frame
                if(frame.getPresente()){ // Se il frame è presente
                    Integer portaDestinazione = frame.getPortaDestinazione(); // Ottieni la porta di destinazione del frame precedentemente creato
                    if(Switch.isGUI){
                        GUI.textArea.append(String.format("\n\nFrame aggiunto alla Porta " + (portaDestinazione + 1))); // Stampa dell'aggiunta del frame nella GUI
                    }
                    System.out.println("Frame Aggiunto alla Porta " + (portaDestinazione + 1)); // Stampa dell'aggiunta del frame nel terminale
                    this.elencoPorte.get(portaDestinazione).getCodaPorta().add(frame); // Aggiungi il frame alla coda generale della porta
                    this.elencoPorte.get(portaDestinazione).aggiungiFrameACodePriorita(); // Smista il frame nelle code di priorità
                }
                for(int i=0; i<Switch.numPorte; i++){ // Ciclo per ogni porta
                    Double mediaAudio = (double) elencoPorte.get(i).getCodaAudio().size() / tempo; // Calcola la lunghezza media della coda con priorità Audio
                    Double mediaVideo = (double) elencoPorte.get(i).getCodaVideo().size() / tempo; // Calcola la lunghezza media della coda con priorità Video
                    Double mediaDati = (double) elencoPorte.get(i).getCodaDati().size() / tempo; // Calcola la lunghezza media della coda con priorità Dati
                    mediaGen += (elencoPorte.get(i).getCodaAudio().size() + elencoPorte.get(i).getCodaVideo().size() + elencoPorte.get(i).getCodaDati().size()); // Aggiorna la media generale di tutte le code

                    if(Switch.isGUI){
                        GUI.textArea.append(String.format("\n\nLunghezza Media Porta " + (i+1) + ":")); // Stampa delle medie delle code nella GUI
                        GUI.textArea.append(String.format("\nLunghezze medie: "));
                        GUI.textArea.append(String.format("\nLunghezza media coda Audio: "+ mediaAudio));
                        GUI.textArea.append(String.format("\nLunghezza media coda Video: "+ mediaVideo));
                        GUI.textArea.append(String.format("\nLunghezza media coda Dati: "+ mediaDati));
                    }
                    System.out.println("Lunghezza Media Porta " + (i+1) + ":"); // Stampa delle medie delle code nel terminale
                    System.out.println("Lunghezze medie: ");
                    System.out.println("Lunghezza media coda Audio: " + mediaAudio);
                    System.out.println("Lunghezza media coda Video: " + mediaVideo);
                    System.out.println("Lunghezza media coda Dati: " + mediaDati);
                }
                tempo++; // Incremento del tempo
            }
            if(Switch.isGUI){
                GUI.textArea.append(String.format("\n\nSimulazione Finita! ")); // Stampa della fine della simulazione nella GUI
                GUI.textArea.append(String.format("\nLunghezza Media Generica: "+mediaGen/this.tempoSimulazione)); // Stampa la lunghezza media delle code nella GUI
                GUI.textArea.append(String.format("\nPremi il tasto invio "));
                GUI.textArea.setFocusable(true);
                GUI.textArea.requestFocus();
            }
            Scanner scanner = new Scanner(System.in);  // Scanner per l'input dell'utente

            System.out.println("Simulazione Finita!"); // Stampa della fine della simulazione nel terminale
            System.out.println("Lunghezza Media Generica: "+mediaGen/this.tempoSimulazione); // Stampa la lunghezza media delle code nel terminale
            System.out.print("Premi il tasto Invio (Enter): "); // Richiesta della pressione del tasto Invio (Enter)

            scanner.nextLine(); // Se viene premuto Invio (Enter) il programma prosegue

            System.out.print("Vuoi iniziare una nuova simulazione? (s/n): "); // Richiesta di una nuova simulazione
            String input = scanner.nextLine(); // Lettura della risposta

            if(input.equals("s") || input.equals("S")){ // Se si vuole iniziare una nuova simulazione
                Integer numPorte, tempoSimulazione;
                Boolean GUI = false;

                // Ciclo per ottenere un numero di porte valido
                do{
                    System.out.print("\nInserisci il numero di porte: ");
                    numPorte = scanner.nextInt(); // Lettura del numero di porte
                    if(numPorte <= 0 || numPorte > 20){ // Se il valore non è valido
                        System.out.print("\nInserisci un numero intero valido maggiore di 0 e minore di 20!");
                    }
                }while(numPorte <= 0 || numPorte > 20); // Ripeti fino a quando non viene inserito un valore valido

                scanner.nextLine(); // Consuma la rimanente linea di input

                // Richiesta all'utente se desidera usare la GUI
                System.out.print("Vuoi usare la GUI? (s/n): ");
                String risposta = scanner.nextLine(); // Lettura della risposta
                if(risposta.equals("s") || risposta.equals("S")){
                    GUI = true; // Imposta la GUI se l'utente risponde "s" o "S", altrimenti no
                }

                // Ciclo per ottenere un tempo di simulazione valido
                do{
                    System.out.print("Inserisci il tempo di simulazione: ");
                    tempoSimulazione = scanner.nextInt(); // Lettura del tempo di simulazione
                    if(tempoSimulazione <= 0 || tempoSimulazione > 60){ // Se il valore non è valido
                        System.out.println("Inserisci un numero intero valido maggiore di 0 e minore di 60!"+ '\n');
                    }
                }while(tempoSimulazione <= 0 || tempoSimulazione > 60); // Ripeti fino a quando non viene inserito un valore valido

                // Avvio della nuova simulazione con i parametri forniti precedentemente
                Test.iniziaSimulazione(tempoSimulazione, numPorte, GUI);
            }
            else{
                System.exit(0); // Uscita dal programma se non si vuole iniziare una nuova simulazione
            }

            try{
                Thread.sleep(1000); // Pausa di 1 secondo
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt(); // Gestione dell'interruzione del thread
            }
        }).start(); // Avvio del thread
    }
}