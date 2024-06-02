package it.davinci.simulazione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI
{
    public static JTextArea textArea;

    public GUI()
    {
        GUI.textArea = new JTextArea();
    }

    public static void creaPaginaImpostazioni(){
        //Finestra/Processo Impostazioni
        JFrame finestra = new JFrame("Simulazione Switch - Impostazioni"); // Nome del processo
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Esci alla pressione di "X" in alto a destra
        finestra.setSize(465, 220); // Dimensione finestra (465x220)
        finestra.setLocationRelativeTo(null); // Finestra al centro dello schermo

        //Titolo
        JLabel titolo = new JLabel("Simulazione di N Switch"); // Nome del titolo
        titolo.setFont(new Font("Arial", Font.BOLD, 24)); // Imposta Font
        titolo.setHorizontalAlignment(SwingConstants.CENTER); // Imposta asse X (centro)
        titolo.setVerticalAlignment(SwingConstants.TOP); // Imposta asse y (alto)
        titolo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Aggiunta del bordo nero solo vero il basso
        finestra.add(titolo, BorderLayout.NORTH); // Aggiungi titolo in cima alla finestra

        // Imposta icona alla finestra/processo
        try{
            ImageIcon icon = new ImageIcon("./Switch.png");
            finestra.setIconImage(icon.getImage()); // Imposta icona
        }
        catch(Exception e){ // Se si verifica un errore e non trova l'immagine nella root del progetto
            System.out.println("Errore nel caricamento dell'icona: " + e.getMessage()); // Stampo errore ma vado avanti
        }

        JPanel panel = new JPanel(); // Crea pannello generale
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Gli elementi che verranno aggiunti al pannello verranno disposti uno sotto l'altro

        JPanel inputPanel = new JPanel(); // Crea pannello input
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS)); // Gli elementi che verranno aggiunti al pannello verranno disposti uno sotto l'altro

        // Input - Numero di porte nello switch
        JLabel inputPorte = new JLabel("Numero di porte nello switch:"); // Titolo dell'input
        inputPorte.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra il titolo orizzontalmente
        JTextField inputPorteCampo = new JTextField(3); // Lunghezza del campo di input
        inputPorteCampo.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputPorteCampo.getPreferredSize().height)); // Larghezza variabile, altezza fissa

        // Input - Tempo di simulazione
        JLabel inputTempo = new JLabel("Tempo di Simulazione (secondi):"); // Titolo dell'input
        inputTempo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra il titolo orizzontalmente
        JTextField inputTempoCampo = new JTextField(3); // Lunghezza del campo di input
        inputTempoCampo.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputTempoCampo.getPreferredSize().height)); // Larghezza flessibile, altezza fissa

        // Bottone Inizia
        JButton bottoneInizia = new JButton("Inizia"); // Titolo del Bottone
        bottoneInizia.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra il pulsante orizzontalmente
        bottoneInizia.setPreferredSize(new Dimension(200, 50)); //Dimensioni del Bottone

        // Listener del tasto Enter della Tastiera per inputPorteCampo e inputTempoCampo
        KeyAdapter keyAdapter = new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){ // Se viene premuto il tasto Enter
                    Test.iniziaSimilazioneInput(inputPorteCampo, inputTempoCampo, finestra); // Esegui funzione iniziaSimualzioneInput
                }
            }
        };

        inputPorteCampo.addKeyListener(keyAdapter); // Aggiungi il Listener a al campo dell'input del porte
        inputTempoCampo.addKeyListener(keyAdapter); // Aggiungi il Listener a al campo dell'input del tempo

        bottoneInizia.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ // Se il bottone viene premuto con il cursore (click)
                Test.iniziaSimilazioneInput(inputPorteCampo, inputTempoCampo, finestra); // Esegui funzione iniziaSimualazioneInput
            }
        });

        // Aggiungi componenti al pannello dell'input
        inputPanel.add(inputPorte);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Crea spazio tra label e campo di input successivo
        inputPanel.add(inputPorteCampo);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Crea spazio tra campo di input di Porte e label successiva
        inputPanel.add(inputTempo);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Crea spazio tra label e campo di input del Tempo
        inputPanel.add(inputTempoCampo);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Crea spazio tra il campo di input del Tempo e il bottoneInizia
        inputPanel.add(bottoneInizia);

        panel.add(inputPanel); // Aggiungi al pannello l'intero inputPanel
        finestra.add(panel); // Aggiungi il pannello alla finestra
        finestra.setVisible(true); // Rendi la finestra/processo visibile
    }

    public static void creaPaginaSimulazione(){
        // Finestra/Processo Simulazione
        JFrame fr = new JFrame("Simulazione Switch"); // Nome del processo
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Esci alla pressione di "X" in alto a destra
        fr.setSize(1280, 720); // Dimensione finestra (1280x720)
        fr.setLocationRelativeTo(null); // Finestra al centro dello schermo

        // Titolo
        JLabel titolo = new JLabel("Simulazione Switch a "+Switch.numPorte+" Porte - Gestione di Rete"); // Nome del titolo
        titolo.setFont(new Font("Arial", Font.BOLD, 24)); // Imposta Font
        titolo.setHorizontalAlignment(SwingConstants.CENTER); // Imposta asse X (centro)
        titolo.setVerticalAlignment(SwingConstants.TOP); // Imposta asse y (alto)
        titolo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Aggiunta del bordo nero solo vero il basso
        fr.add(titolo, BorderLayout.NORTH); // Aggiungi titolo in cima alla finestra

        // Imposta icona alla finestra/processo
        try{
            ImageIcon icon = new ImageIcon("./Switch.png");
            fr.setIconImage(icon.getImage()); // Imposta icona
        }
        catch(Exception e){ // Se si verifica un errore e non trova l'immagine nella root del progetto
            System.out.println("Errore nel caricamento dell'icona: " + e.getMessage()); // Stampo errore ma vado avanti
        }

        // Creazione dell'area di testo
        textArea = new JTextArea(20, 40); // Area di testo con dimensioni specificate
        Font font1 = new Font("Calibri", Font.BOLD, 16); // Imposta font all'area di testo
        textArea.setFont(font1); // Imposta il font all'area di testo

        // Aggiunta di un riquadro di scorrimento all'area di testo
        JScrollPane scrollPane = new JScrollPane(textArea);
        fr.getContentPane().add(scrollPane, BorderLayout.CENTER); // Aggiunge l'area di testo un riquadro di scorrimento alla finestra

        fr.setVisible(true); // Rende visibile la finestra

        // Listener del tasto Enter della Tastiera per la richiesta di una nuova simulazione
        textArea.addKeyListener(new KeyAdapter(){
            // Funzione eseguita quando un tasto viene premuto sulla tastiera
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){ // Se il tasto premuto è 'Enter' (Invio)
                    SwingUtilities.invokeLater(() -> {
                        // Mostra un messaggio per confermare se iniziare una nuova simulazione o no
                        Integer response = JOptionPane.showOptionDialog(fr,
                                "Vuoi iniziare una nuova simulazione?", // Testo del messaggio
                                "Nuova Simulazione", // Titolo del messaggio
                                JOptionPane.YES_NO_OPTION, // Opzioni disponibili nel messaggio
                                JOptionPane.QUESTION_MESSAGE, // Tipo di messaggio
                                null, // Nessuna icona personalizzata
                                new Object[]{"Si", "No"}, // Testi dei pulsanti
                                "Si"); // Pulsante predefinito
                        if(response == JOptionPane.YES_OPTION){
                            creaPaginaImpostazioni(); // Se si sceglie "Si", viene creata la pagina delle impostazioni
                        }
                        else{
                            System.exit(0); // Se si sceglie "No", l'applicazione viene chiusa
                        }
                    });
                }
            }
        });
    }
}