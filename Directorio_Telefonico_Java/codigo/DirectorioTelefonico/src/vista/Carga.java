package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;

/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Vista que sirve como pantalla de carga
 */
public class Carga extends JFrame {

    /**
     * Inicializamos los atributos de la clase
     */
    //Ancho y alto de ventana
    private int anchoV = 520;
    private int largoV = 350;

    //Contenedor Principal
    private Container contPrincipal;

    //Labels
    public JLabel lblMensaje;
    public JLabel lblPorcentaje;

    //Paneles
    private JPanel panelPrincipal;

    public JProgressBar progreso;

    /**
     * Constructor de la clase
     */
    public Carga() {
        iniciarComponentes();
        iniciarVentana();
    }

    private void iniciarVentana() {
        setSize(anchoV, largoV);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Directorio Telefonico");
        setResizable(false);

        Image icono = new ImageIcon(getClass().getResource("/imagenes/iconoVentana.png")).getImage();
        setIconImage(icono);

    }

    private void iniciarComponentes() {
        setUndecorated(true);

        //Contenedor Principal//
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);

        panelPrincipal = new JPanel();
        lblMensaje = new JLabel();
        lblMensaje.setForeground(new Color(231, 227, 227));
        lblPorcentaje = new JLabel();
        lblPorcentaje.setForeground(new Color(231, 227, 227));
        progreso = new JProgressBar();

        panelPrincipal.setBackground(new java.awt.Color(15, 15, 15));
        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMensaje.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 28)); // NOI18N
        lblMensaje.setText("Directorio Telefonico");
        panelPrincipal.add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 270, 70));

        lblPorcentaje.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        panelPrincipal.add(lblPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 80, 50));

        progreso.setBackground(new java.awt.Color(255, 255, 255));
        progreso.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        panelPrincipal.add(progreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 320, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

    }

}
