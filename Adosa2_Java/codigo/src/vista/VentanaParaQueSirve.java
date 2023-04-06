
package vista;

import funcionalidadesAparte.BotonSinFondo;
import funcionalidadesAparte.metodosUtiles;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * MINIPROYECTO 2 - Adosa2
 * @author Juan Sebastian Getial <getial.juan@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo <carlos.hernandez.agudelo@correounivalle.edu.co>
 * Clase que representa la ventana donde se muestra para que sirve el juego
 */
public class VentanaParaQueSirve extends JFrame {

    //ruta absoluta
    private String rutaAbsoluta;

     //Sonido
    private File archivowav;
    private Clip clip;
    private AudioInputStream audioInputStream;

    //ancho y largo de la ventana
    private int anchoV;
    private int largoV;

    //fondo
    private JLabel lblFondo;

    //contendero principal
    private Container contPrincipal;

    //jbuton
    private JButton btnSalir;

    //texto
    private JTextPane txtTexto;

    public VentanaParaQueSirve() {
        iniciarComponentes();
        iniciarVentana();
    }

    private void iniciarVentana() {
        setSize(anchoV, largoV);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Adosa");

        Image icon = new ImageIcon(getClass().
                getResource("/imagenes/iconoVentana.png")).getImage();
        setIconImage(icon);

        setResizable(false);
    }

    private void iniciarComponentes() {
        //Ruta absoluta
        rutaAbsoluta = new File("").getAbsolutePath();

        //ancho y largo
        anchoV = 700;
        largoV = 500;

        //btnSalir
        btnSalir = new BotonSinFondo();
        btnSalir.setIcon(metodosUtiles.
                establecerIcon("\\src\\imagenes\\salir.png", 80, 80));
        btnSalir.setRolloverEnabled(true);
        btnSalir.setRolloverIcon(metodosUtiles.
                establecerIcon("\\src\\imagenes\\salir2.png", 80, 80));
        btnSalir.setBounds(560, 20, 80, 80);
        btnSalir.addActionListener(new ManejadorDeEventos());

        //dfondo
        lblFondo = new JLabel(metodosUtiles.
        establecerIcon("\\src\\imagenes\\fondoComoJugar.png",anchoV,largoV));

        //texto
        txtTexto = new JTextPane();
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontFamily(attribs, "Tahoma");
        StyleConstants.setFontSize(attribs, 30);
        txtTexto.setParagraphAttributes(attribs, true);
        txtTexto.setText("Este juego pone en acción la habilidad para comparar "
            + "patrones visuales, entrenando además la atención a los "
            + "detalles y velocidad perceptiva. Estas capacidades son "
            + "relevantes cuando hay que decidir entre estímulos semejantes "
            + "y hay que hacerlo de forma rápida, por ejemplo al reconocer "
            + "fotografías, caras, objetos cotidianos o palabras especificas.");
        txtTexto.setBounds(10, 100, 670, 300);
        txtTexto.setOpaque(false);
        txtTexto.setEditable(false);

        //cont prinicpal
        contPrincipal = getContentPane();
        contPrincipal.setLayout(new GridLayout(1, 1));

        //se añaden los objetos
        contPrincipal.add(lblFondo);

        lblFondo.add(btnSalir);
        lblFondo.add(txtTexto);
        
        reproducirSonido("inicio");
    }

//********************************METODOS***********************************//
    public void reproducirSonido(String sonido) {
        switch (sonido) {
            case "boton" ->
                iniciarSonido("src\\sonidos\\boton.wav");
            case "inicio" ->
                iniciarSonido("src\\sonidos\\paraQueSirve.wav");
            default -> {
            }
        }
    }
    
    private void iniciarSonido(String filePath) {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }

        archivowav = new File(filePath);
        try {
            audioInputStream = AudioSystem.getAudioInputStream(archivowav);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException | IOException | 
                UnsupportedAudioFileException e) {
            System.err.println(e.getMessage());
        }
    }
    
///***************************CLASES**************************************//
    
    //clase manejadora de eventos
    private class ManejadorDeEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnSalir) {
                dispose();
                reproducirSonido("boton");
                VentanaInicial ventanaInicial = new VentanaInicial(2);
            }
        }

    }
}
