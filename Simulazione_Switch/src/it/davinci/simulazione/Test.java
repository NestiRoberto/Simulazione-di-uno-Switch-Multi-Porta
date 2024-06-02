package it.davinci.simulazione;

import javax.swing.*;

public class Test
{
    public static void iniziaSimilazioneInput(JTextField inputPorteCampo, JTextField inputTempoCampo, JFrame finestra){
        String inputPorte = inputPorteCampo.getText(); // Prendi l'input del campo e assegnalo ad una variabile
        String inputTempo = inputTempoCampo.getText(); // Prendi l'input del campo e assegnalo ad una variabile
        if(numeroValido(inputPorte) && Integer.parseInt(inputPorte) > 0 && Integer.parseInt(inputPorte) <= 20 &&
                numeroValido(inputTempo) && Integer.parseInt(inputTempo) > 0 && Integer.parseInt(inputTempo) <= 60){ // Se nel primo campo sono presenti numeri compresi tra 0 e 20 e se nel secondo campo sono presenti numeri compresi tra 0 e 60
            Integer numPorte = Integer.parseInt(inputPorte);
            Integer tempoSimulazione = Integer.parseInt(inputTempo);
            iniziaSimulazione(tempoSimulazione, numPorte, true); // Esegue la funzione iniziaSimulazione con i dati inseriti dei campi di input
            finestra.dispose(); // Chiude la finestra delle impostazioni dopo aver premuto il pulsante Inizia
        }
        else{ // Altrimenti
            if(!numeroValido(inputPorte) || Integer.parseInt(inputPorte) <= 0 || Integer.parseInt(inputPorte) > 20){ //Se nel primo campo di input sono presenti numeri che non sono compresi tra 0 e 20
                JOptionPane.showMessageDialog(finestra,
                        "Inserisci un numero intero valido maggiore di 0 e minore o uguale a 20 per il numero di porte!",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE); // Mostra un messaggio d'errore
            }
            if(!numeroValido(inputTempo) || Integer.parseInt(inputTempo) <= 0 || Integer.parseInt(inputTempo) > 60){ //Se nel secondo campo di input sono presenti numeri che non sono compresi tra 0 e 60
                JOptionPane.showMessageDialog(finestra,
                        "Inserisci un numero intero valido maggiore di 0 e minore o uguale a 60 per il tempo di simulazione!",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE); // Mostra un messaggio d'errore
            }
        }
    }

    public static boolean numeroValido(String input){ // Verifica che la stringa inserita sia un numero attraverso parseInt
        try{
            Integer.parseInt(input); // Se questa operazione viene effettuata correttamente ritorna true
            return true;
        }
        catch(NumberFormatException e){ // Se questa operazione non viene effettuata correttamente poiché la stringa inserita non è un numero ritorna false
            return false;
        }
    }

    public static void iniziaSimulazione(Integer tempoSimulazione, Integer numPorte, Boolean usaGUI){
        Switch mySwitch; // Crea istanza Switch
        if(usaGUI){ //se useGUI è true
            mySwitch = new Switch("Switch", "Cisco", tempoSimulazione, true, numPorte); // Crea un'istanza Switch con i valori inseriti grazie all'input
        }
        else{ // Altrimenti crea manuale l'istanza
            mySwitch = new Switch("Switch", "Cisco", 5, false, 5); // Esempio di valore predefinito
        }

        if(mySwitch.getIsGUI()){ // Se la GUI è presente, crea la GUI di mySwitch
            GUI.creaPaginaSimulazione();
        }

        mySwitch.simula(); // Esegui funzione simula di mySwitch
    }

    public static void main(String[] args){
        Boolean usaGUI = true; // Vuoi usare la GUI o no?
        if(usaGUI){ // Se si crea la finestra iniziale
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    GUI.creaPaginaImpostazioni();
                }
            });
        }
        else{ // Altrimenti inizia la simulazione usando solo il terminale
            iniziaSimulazione(0, 0, false); // Passa tutto a 0 perché non usiamo i valori da GUI
        }
    }
}