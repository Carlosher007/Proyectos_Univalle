
package vista;

import funcionalidadesAparte.BotonSinFondo;
import funcionalidadesAparte.metodosUtiles;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.Timer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * MINIPROYECTO 2 - Adosa2
 * @author Juan Sebastian Getial <getial.juan@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo 
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * Clase que representa la ventana del  de las instrucciones del juego
 */
public class VentanaComoJugar extends JFrame {

    //Sonido
    private File archivowav;
    private Clip clip;
    private AudioInputStream audioInputStream;

    //Ancho y alto de ventana
    private int anchoV = 700;
    private int largoV = 500;

    //Ruta absoluta
    private String rutaAbsoluta;

    //timer
    private Timer tiempo;
    private boolean sonidoInicializado = false;
    private double t = 0;

    //Fondo lbl
    private JLabel lblFondo;

    //Imagen Ejemplo
    private JLabel lblImagen;

    //Botones
    private JButton btnSiguiente;
    private JButton btnAtras;

    //Numero ventana
    private JLabel txtNumeroVentana;
    private int numVentana = 1;

    //Label mensaje
    private JTextPane txtTexto;

    //Label flecha
    private JLabel lblFlecha;

    //JButton salir
    private JButton btnSalir;

    //Contendero principal
    private Container contPrincipal;

    //Imagenes
    private ImageIcon imgFondo;
    private ImageIcon iconoFlecha;
    private ImageIcon iconoSalir;
    private ImageIcon iconoSalir2;
    private ImageIcon imgEjemplo;
    private ImageIcon iconoSiguiente;
    private ImageIcon iconoSiguiente2;
    private ImageIcon iconoAtras;
    private ImageIcon iconoAtras2;

    public VentanaComoJugar() {
        iniciarVentana();
        iniciarComponentes();
    }

    private void iniciarVentana() {
        setSize(anchoV, largoV);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image icon = new ImageIcon(getClass().getResource("/imagenes/iconoVentana.png")).getImage();
        setIconImage(icon);

        setTitle("Adosa2");

        setResizable(false);
    }

    private void iniciarComponentes() {
        //Ruta absoluta
        rutaAbsoluta = new File("").getAbsolutePath();

        //timer//
        tiempo = new Timer(100, new ManejadorDeEventosTiempo());
        tiempo.start();

        //Fondo //
        imgFondo = metodosUtiles.establecerIcon("\\src\\imagenes\\fondoComoJugar.png", anchoV,
                largoV);
        lblFondo = new JLabel(imgFondo);
        lblFondo.setBounds(0, 0, anchoV, largoV);
        lblFondo.setLayout(null);

        //Numero Ventana
        txtNumeroVentana = new JLabel("1/4");
        txtNumeroVentana.setFont(new Font("Tahoma", Font.BOLD, 24));
        txtNumeroVentana.setBounds(35, 20, 50, 30);

        //Imagen Ejemplo
        imgEjemplo = metodosUtiles.establecerIcon("\\src\\imagenes\\comoJugar1.png", 350, 270);
        lblImagen = new JLabel(imgEjemplo);
        lblImagen.setBounds(175, 20, 350, 270);

        //JLabel Siguiente
        iconoSiguiente = metodosUtiles.establecerIcon("\\src\\imagenes\\siguiente.png", 130, 40);
        iconoSiguiente2 = metodosUtiles.establecerIcon("\\src\\imagenes\\siguiente2.png", 130, 40);

        btnSiguiente = new BotonSinFondo();
        btnSiguiente.setIcon(iconoSiguiente);
        btnSiguiente.setRolloverIcon(iconoSiguiente2);
        btnSiguiente.setPressedIcon(iconoSiguiente2);
        btnSiguiente.setBounds(536, 140, 130, 40);

        //Boton Atras
        iconoAtras = metodosUtiles.establecerIcon("\\src\\imagenes\\atras.png", 130, 40);
        iconoAtras2 = metodosUtiles.establecerIcon("\\src\\imagenes\\atras2.png", 130, 40);

        btnAtras = new BotonSinFondo();
        btnAtras.setIcon(iconoAtras);
        btnAtras.setRolloverIcon(iconoAtras2);
        btnAtras.setPressedIcon(iconoAtras2);
        btnAtras.setBounds(20, 140, 130, 40);
        btnAtras.setVisible(false);

        //Flecha
        iconoFlecha = metodosUtiles.establecerIcon("\\src\\imagenes\\flecha.png", 70, 70);
        lblFlecha = new JLabel(iconoFlecha);
        lblFlecha.setBounds(509, 200, 70, 70);
        lblFlecha.setVisible(false);

        //Salir
        iconoSalir = metodosUtiles.establecerIcon("\\src\\imagenes\\salir.png", 70, 70);
        iconoSalir2 = metodosUtiles.establecerIcon("\\src\\imagenes\\salir2.png", 70, 70);

        btnSalir = new BotonSinFondo();
        btnSalir.setIcon(iconoSalir);
        btnSalir.setRolloverIcon(iconoSalir2);
        btnSalir.setPressedIcon(iconoSalir2);
        btnSalir.setBounds(600, 20, 70, 70);

        //Mensaje
        txtTexto = new JTextPane();
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(attribs, 22);
        StyleConstants.setFontFamily(attribs, "Tahoma");
        txtTexto.setParagraphAttributes(attribs, true);
        txtTexto.setText("""
                         En Adosa2 aparecen en pantalla una serie de baldosas.
                         Las baldosas van cambiando de 1 en 1 mostrando
                         distintos dise\u00f1os.
                         Podr\u00e1s saber qu\u00e9 baldosas cambia en cada momento
                         gracias a un reborde de color azul.""");
        txtTexto.setBounds(10, 300, 670, 300);
        txtTexto.setOpaque(false);
        txtTexto.setEditable(false);

        //Contenedor Principal//
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);

        //Añadiendo objetos
        contPrincipal.add(lblFondo);

        //Añadiendo objetos al lblFondo
        lblFondo.add(txtTexto);
        lblFondo.add(btnSalir);
        lblFondo.add(btnSiguiente);
        lblFondo.add(btnAtras);
        lblFondo.add(txtNumeroVentana);
        lblFondo.add(lblFlecha);
        lblFondo.add(lblImagen);

        //Eventos
        btnSiguiente.addMouseListener(new ManejadorDeEventos());
        btnAtras.addMouseListener(new ManejadorDeEventos());
        btnSalir.addMouseListener(new ManejadorDeEventos());

        btnSalir.addKeyListener(new ManejadoraEventosTeclado());
        btnSiguiente.addKeyListener(new ManejadoraEventosTeclado());
        btnAtras.addKeyListener(new ManejadoraEventosTeclado());

        btnSalir.requestFocus();

    }

//***********************************METODOS**********************************//
    private void iniciarVentana1() {
        numVentana = 1;

        txtNumeroVentana.setText("1/4");
        sonidoInicializado = false;
        t = 0;
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        imgEjemplo = metodosUtiles.establecerIcon("\\src\\imagenes\\comoJugar1.png", 350, 270);
        lblImagen.setIcon(imgEjemplo);
        lblFondo.add(lblImagen);

        lblFlecha.setVisible(false);

        btnAtras.setVisible(false);

        txtTexto.setText("""
                         En Adosa2 aparecen en pantalla una serie de baldosas.
                         Las baldosas van cambiando de 1 en 1 mostrando
                         distintos dise\u00f1os.
                         Podr\u00e1s saber qu\u00e9 baldosas cambia en cada momento
                         gracias a un reborde de color azul.""");

    }

    private void iniciarVentana2() {
        numVentana = 2;

        txtNumeroVentana.setText("2/4");

        t = 0;
        sonidoInicializado = false;
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        imgEjemplo = metodosUtiles.establecerIcon("\\src\\imagenes\\comoJugar2.png", 350, 270);
        lblImagen.setIcon(imgEjemplo);
        lblFondo.add(lblImagen);

        lblFlecha.setVisible(true);

        btnAtras.setVisible(true);

        txtTexto.setText("""
                         En el momento en el que veas en pantalla 2 baldosas
                         id\u00e9nticas, debes presionar r\u00e1pidamente el pulsador
                         blanco que aparece en la zona inferior derecha""");
    }

    private void iniciarVentana3() {
        numVentana = 3;

        txtNumeroVentana.setText("3/4");

        t = 0;
        sonidoInicializado = false;
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        imgEjemplo = metodosUtiles.establecerIcon("\\src\\imagenes\\comoJugar3.png", 350, 270);
        lblImagen.setIcon(imgEjemplo);
        lblFondo.add(lblImagen);

        btnSiguiente.setVisible(true);

        lblFlecha.setVisible(false);

        txtTexto.setText("""
                         Hay dos formas de presionar el pulsador: 
                         
                         - Pulsar directamente el bot\u00f3n blanco en pantalla
                         - Pulsar la barra de espacio del teclado""");

    }

    private void iniciarVentana4() {
        numVentana = 4;

        txtNumeroVentana.setText("4/4");

        t = 0;
        sonidoInicializado = false;
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        imgEjemplo = metodosUtiles.establecerIcon("\\src\\imagenes\\comoJugar4.png", 350, 270);
        lblImagen.setIcon(imgEjemplo);
        lblFondo.add(lblImagen);

        lblFlecha.setVisible(true);
        lblFlecha.setBounds(509, 20, 70, 70);
        lblFondo.add(lblFlecha);
        lblFondo.add(lblImagen);

        btnSiguiente.setVisible(false);

        txtTexto.setText("""
                         \u00a1OJO! Si no pulsas a tiempo perder\u00e1s vidas. A medida
                         que el juego avanza el ritmo al que cambian las
                         baldosas es mayor y tu tiempo de reaccci\u00f3n es menor
                         
                         \u00a1Ojo avizor y encuentra tus Adosa2!""");
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
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            System.err.println(e.getMessage());
        }
    }

    private void reproducirSonido(int ventana) {
        switch (ventana) {
            case 0 ->
                iniciarSonido("src\\sonidos\\boton.wav");
            case 1 ->
                iniciarSonido("src\\sonidos\\tuto1.wav");
            case 2 ->
                iniciarSonido("src\\sonidos\\tuto2.wav");
            case 3 ->
                iniciarSonido("src\\sonidos\\tuto3.wav");
            case 4 ->
                iniciarSonido("src\\sonidos\\tuto4.wav");
            default -> {
            }
        }
    }

//********************************CLASES************************************//
    private class ManejadorDeEventos extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == btnSiguiente) {
                switch (numVentana) {
                    case 1 ->
                        iniciarVentana2();
                    case 2 ->
                        iniciarVentana3();
                    case 3 ->
                        iniciarVentana4();
                    default -> {
                    }
                }
            } else if (e.getSource() == btnSalir) {
                dispose();
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                }
                reproducirSonido(0);
                VentanaInicial ventanaInicial = new VentanaInicial(1);
            } else if (e.getSource() == btnAtras) {
                switch (numVentana) {
                    case 2 ->
                        iniciarVentana1();
                    case 3 ->
                        iniciarVentana2();
                    case 4 ->
                        iniciarVentana3();
                    default -> {
                    }
                }
            }
        }

    }

    private class ManejadoraEventosTeclado implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getSource() == btnSalir || e.getSource() == btnAtras || e.getSource() == btnSiguiente) {
                switch (e.getKeyCode()) {
                    case 39 -> {
                        switch (numVentana) {
                            case 1 -> {
                                iniciarVentana2();
                            }
                            case 2 -> {
                                iniciarVentana3();
                            }
                            case 3 -> {
                                iniciarVentana4();
                            }

                            default -> {
                            }
                        }
                    }
                    case 10, 32 -> {
                        dispose();
                        if (clip != null && clip.isRunning()) {
                            clip.stop();
                        }
                        reproducirSonido(0);
                        VentanaInicial ventanaInicial = new VentanaInicial(1);
                    }
                    case 37 -> {
                        switch (numVentana) {
                            case 2 -> {
                                iniciarVentana1();
                            }
                            case 3 -> {
                                iniciarVentana2();
                            }
                            case 4 -> {
                                iniciarVentana3();
                            }

                            default -> {
                            }
                        }
                    }
                    default -> {
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    private class ManejadorDeEventosTiempo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //se aumenta el tiempo 1 segundo
            t += 0.1;
            if (t > 0.5) {
                if (numVentana == 1) {
                    if (!sonidoInicializado) {
                        reproducirSonido(1);
                        sonidoInicializado = true;
                    }
                }
                if (numVentana == 2) {
                    if (!sonidoInicializado) {
                        reproducirSonido(2);
                        sonidoInicializado = true;
                    }
                }
                if (numVentana == 3) {
                    if (!sonidoInicializado) {
                        reproducirSonido(3);
                        sonidoInicializado = true;
                    }
                }
                if (numVentana == 4) {
                    if (!sonidoInicializado) {
                        reproducirSonido(4);
                        sonidoInicializado = true;
                    }
                }
            }
        }
    }

}
