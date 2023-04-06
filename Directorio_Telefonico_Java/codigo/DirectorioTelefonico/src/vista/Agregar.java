package vista;

import clases.Contacto;
import clases.Direccion;
import clases.ModeloContactos;
import clases.Telefono;
import funcionalidades.BotonSinFondo;
import funcionalidades.PanelDeOpciones;
import funcionalidades.metodosUtiles;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.StyleConstants;

/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Vista para agregar uno o varios contactos
 */
public class Agregar extends JFrame {

    /**
     * Inicializamos los atributos de la clase
     */
    //Ancho y alto de ventana
    private int anchoV = 700;
    private int largoV = 600;

    //Iconos e Imagenes
    private ImageIcon imgFondo;
    private ImageIcon imgAgregarNorm;
    private ImageIcon imgAgregarRP;
    private ImageIcon imgAgregarPressed;
    private ImageIcon imgCancelarNorm;
    private ImageIcon imgCancelarRP;
    private ImageIcon imgCancelarPressed;
    private ImageIcon imgPlusNorm;
    private ImageIcon imgPlusRP;
    private ImageIcon imgPlusPressed;
    protected ImageIcon imgLoading1;
    protected ImageIcon imgLoading2;
    protected ImageIcon imgLoading3;
    protected ImageIcon imgLoading4;

    //Botones
    private JButton btnAgregar, btnSalir, btnPlus, btnPlus2;

    //Contenedor Principal
    private Container contPrincipal;

    //Labels
    private JLabel lblFondo;
    private JLabel lblTitulo;
    private JLabel lblTipoContacto, lblFechaNacimiento, lblNombres, lblApellidos, lblIdentificacion, lblTelefono, lblBarrio, lblCiudad, lblTipoTelefono;
    protected JLabel lblLoading;

    //TextField
    private JTextField campoFechaNacimiento, campoNombres, campoApellidos, campoIdentificacion, campoTelefono, campoBarrio, campoCiudad;

    //ComboBox
    private JComboBox<String> comboTipoContacto, comboTipoTelefono;
    private DefaultComboBoxModel<String> modeloComboTipoContacto, modeloComboTipoTelefono;

    //Paneles
    private JPanel panelTipoContacto;
    private JPanel panelDatosPersonales;
    private JPanel panelDatosDeContacto;
    private JPanel panelVivienda;
    private JPanel panelBotones;

    //Funcionamiento Interno
    protected ModeloContactos modelo;
    protected boolean seHaModificado;

    protected ArrayList<Telefono> telefonos;
    protected ArrayList<Direccion> direcciones;

    /*Constructor*/
    public Agregar(ModeloContactos modelo) {
        modelo = new ModeloContactos();
        this.modelo = modelo;
        modelo.cargarContactos();

        telefonos = new ArrayList<>();
        direcciones = new ArrayList<>();

        seHaModificado = false;

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
        //Contenedor Principal//
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);

        //Fondo//
        imgFondo = metodosUtiles.establecerIcon("\\src\\imagenes\\fondoInicio.png",
                anchoV, largoV);
        lblFondo = new JLabel(imgFondo);
        lblFondo.setBounds(0, 0, anchoV, largoV);

        contPrincipal.add(lblFondo);

        //Titulo
        Font fuente = new Font("Tahoma", 1, 26);

        lblTitulo = new JLabel();
        lblTitulo.setText("Agregar Un Contacto");
        lblTitulo.setAlignmentX(StyleConstants.ALIGN_CENTER);
        lblTitulo.setFont(fuente);
        lblTitulo.setBounds(235, 15, 400, 30);

        //Panel
        panelTipoContacto = new JPanel();
        panelTipoContacto.setBounds(12, 45, 650, 70);
        panelTipoContacto.setLayout(null);
        panelTipoContacto.setOpaque(false);

        /*Añadimos a panelTipoDeContacto*/
        lblTipoContacto = new JLabel("Tipo de Contacto:");
        lblTipoContacto.setAlignmentX(StyleConstants.ALIGN_LEFT);
        lblTipoContacto.setFont(new Font("Tahoma", 0, 14));
        lblTipoContacto.setBounds(10, 10, 120, 60);

        modeloComboTipoContacto = new DefaultComboBoxModel<>();
        comboTipoContacto = new JComboBox<>(modeloComboTipoContacto);
        modeloComboTipoContacto.addElement("Empleado");
        modeloComboTipoContacto.addElement("Estudiante");
        modeloComboTipoContacto.addElement("Profesor");
        comboTipoContacto.setBounds(135, 28, 100, 26);

        panelTipoContacto.add(lblTipoContacto);
        panelTipoContacto.add(comboTipoContacto);

        //Panel
        panelDatosPersonales = new JPanel();
        panelDatosPersonales.setBounds(12, 120, 650, 130);
        panelDatosPersonales.setLayout(null);
        panelDatosPersonales.setOpaque(false);
        panelDatosPersonales.setBorder(BorderFactory.createTitledBorder(null, "Datos personales",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", 1, 13), new Color(26, 25, 25)));

        lblFechaNacimiento = new JLabel("Fecha de nacimiento: ");
        lblFechaNacimiento.setAlignmentX(StyleConstants.ALIGN_LEFT);
        lblFechaNacimiento.setFont(new Font("Tahoma", 0, 14));
        lblFechaNacimiento.setBounds(10, 10, 150, 60);

        campoFechaNacimiento = new JTextField(20);
        campoFechaNacimiento.setFont(new Font("Tahoma", 0, 14));
        campoFechaNacimiento.setBounds(155, 28, 150, 26);

        lblIdentificacion = new JLabel("Núm. Identifiacion: ");
        lblIdentificacion.setAlignmentX(StyleConstants.ALIGN_LEFT);
        lblIdentificacion.setFont(new Font("Tahoma", 0, 14));
        lblIdentificacion.setBounds(350, 10, 150, 60);

        campoIdentificacion = new JTextField(20);
        campoIdentificacion.setFont(new Font("Tahoma", 0, 14));
        campoIdentificacion.setBounds(475, 28, 150, 26);

        lblNombres = new JLabel("Nombres: ");
        lblNombres.setAlignmentX(StyleConstants.ALIGN_LEFT);
        lblNombres.setFont(new Font("Tahoma", 0, 14));
        lblNombres.setBounds(10, 60, 150, 60);

        campoNombres = new JTextField(20);
        campoNombres.setFont(new Font("Tahoma", 0, 14));
        campoNombres.setBounds(155, 78, 150, 26);

        lblApellidos = new JLabel("Apellidos: ");
        lblApellidos.setAlignmentX(StyleConstants.ALIGN_LEFT);
        lblApellidos.setFont(new Font("Tahoma", 0, 14));
        lblApellidos.setBounds(350, 60, 150, 60);

        campoApellidos = new JTextField(20);
        campoApellidos.setFont(new Font("Tahoma", 0, 14));
        campoApellidos.setBounds(475, 78, 150, 26);

        panelDatosPersonales.add(lblFechaNacimiento);
        panelDatosPersonales.add(campoFechaNacimiento);
        panelDatosPersonales.add(lblIdentificacion);
        panelDatosPersonales.add(campoIdentificacion);
        panelDatosPersonales.add(lblNombres);
        panelDatosPersonales.add(campoNombres);
        panelDatosPersonales.add(lblApellidos);
        panelDatosPersonales.add(campoApellidos);

        //Panel
        panelDatosDeContacto = new JPanel();
        panelDatosDeContacto.setBounds(14, 265, 650, 100);
        panelDatosDeContacto.setLayout(null);
        panelDatosDeContacto.setOpaque(false);
        panelDatosDeContacto.setBorder(BorderFactory.createTitledBorder(null, "Datos de contacto",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", 1, 13), new Color(26, 25, 25)));

        lblTelefono = new JLabel("Telefono: ");
        lblTelefono.setAlignmentX(StyleConstants.ALIGN_LEFT);
        lblTelefono.setFont(new Font("Tahoma", 0, 14));
        lblTelefono.setBounds(10, 10, 150, 60);

        campoTelefono = new JTextField(20);
        campoTelefono.setFont(new Font("Tahoma", 0, 14));
        campoTelefono.setBounds(155, 28, 150, 26);

        lblTipoTelefono = new JLabel("Tipo de Telefono: ");
        lblTipoTelefono.setAlignmentX(StyleConstants.ALIGN_LEFT);
        lblTipoTelefono.setFont(new Font("Tahoma", 0, 14));
        lblTipoTelefono.setBounds(350, 10, 150, 60);

        modeloComboTipoTelefono = new DefaultComboBoxModel<>();
        comboTipoTelefono = new JComboBox<>(modeloComboTipoTelefono);
        modeloComboTipoTelefono.addElement("Movil");
        modeloComboTipoTelefono.addElement("Casa");
        modeloComboTipoTelefono.addElement("Oficina");
        modeloComboTipoTelefono.addElement("Familiar");
        comboTipoTelefono.setBounds(465, 26, 100, 26);

        btnPlus = new BotonSinFondo();
        btnPlus.setBounds(590, 56, 40, 40);

        imgPlusNorm = metodosUtiles.establecerIcon("\\src\\imagenes\\btnPlusN.png", obtenerAnchoBoton(btnPlus), obtenerAltoBoton(btnPlus));
        imgPlusPressed = metodosUtiles.establecerIcon("\\src\\imagenes\\btnPlusP.png", obtenerAnchoBoton(btnPlus), obtenerAltoBoton(btnPlus));
        imgPlusRP = metodosUtiles.establecerIcon("\\src\\imagenes\\btnPlusR.png", obtenerAnchoBoton(btnPlus), obtenerAltoBoton(btnPlus));

        btnPlus.setIcon(imgPlusNorm);
        btnPlus.setRolloverIcon(imgPlusRP);
        btnPlus.setPressedIcon(imgPlusPressed);

        panelDatosDeContacto.add(lblTelefono);
        panelDatosDeContacto.add(campoTelefono);
        panelDatosDeContacto.add(lblTipoTelefono);
        panelDatosDeContacto.add(comboTipoTelefono);
        panelDatosDeContacto.add(btnPlus);

        //Panel
        panelVivienda = new JPanel();
        panelVivienda.setBounds(14, 370, 650, 100);
        panelVivienda.setLayout(null);
        panelVivienda.setOpaque(false);
        panelVivienda.setBorder(BorderFactory.createTitledBorder(null, "Vivienda",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", 1, 13), new Color(26, 25, 25)));

        lblBarrio = new JLabel("Barrio: ");
        lblBarrio.setAlignmentX(StyleConstants.ALIGN_LEFT);
        lblBarrio.setFont(new Font("Tahoma", 0, 14));
        lblBarrio.setBounds(10, 10, 150, 60);

        campoBarrio = new JTextField(20);
        campoBarrio.setFont(new Font("Tahoma", 0, 14));
        campoBarrio.setBounds(155, 28, 150, 26);

        lblCiudad = new JLabel("Ciudad: ");
        lblCiudad.setAlignmentX(StyleConstants.ALIGN_LEFT);
        lblCiudad.setFont(new Font("Tahoma", 0, 14));
        lblCiudad.setBounds(350, 10, 150, 60);

        campoCiudad = new JTextField(20);
        campoCiudad.setFont(new Font("Tahoma", 0, 14));
        campoCiudad.setBounds(475, 28, 150, 26);

        btnPlus2 = new BotonSinFondo();
        btnPlus2.setBounds(590, 56, 40, 40);

        btnPlus2.setIcon(imgPlusNorm);
        btnPlus2.setRolloverIcon(imgPlusRP);
        btnPlus2.setPressedIcon(imgPlusPressed);

        panelVivienda.add(lblBarrio);
        panelVivienda.add(campoBarrio);
        panelVivienda.add(lblCiudad);
        panelVivienda.add(campoCiudad);
        panelVivienda.add(btnPlus2);

        //Panel
        panelBotones = new JPanel();
        panelBotones.setBounds(14, 475, 650, 80);
        panelBotones.setLayout(null);
        panelBotones.setOpaque(false);

        btnAgregar = new BotonSinFondo();
        btnAgregar.setBounds(470, 10, 70, 70);

        btnSalir = new BotonSinFondo();
        btnSalir.setBounds(570, 10, 70, 70);

        imgAgregarNorm = metodosUtiles.establecerIcon("\\src\\imagenes\\btnAceptarN.png", obtenerAnchoBoton(btnAgregar), obtenerAltoBoton(btnAgregar));
        imgAgregarPressed = metodosUtiles.establecerIcon("\\src\\imagenes\\btnAceptarP.png", obtenerAnchoBoton(btnAgregar), obtenerAltoBoton(btnAgregar));
        imgAgregarRP = metodosUtiles.establecerIcon("\\src\\imagenes\\btnAceptarR.png", obtenerAnchoBoton(btnAgregar), obtenerAltoBoton(btnAgregar));

        imgCancelarNorm = metodosUtiles.establecerIcon("\\src\\imagenes\\btnCancelarN.png", obtenerAnchoBoton(btnSalir), obtenerAltoBoton(btnSalir));
        imgCancelarPressed = metodosUtiles.establecerIcon("\\src\\imagenes\\btnCancelarP.png", obtenerAnchoBoton(btnSalir), obtenerAltoBoton(btnSalir));
        imgCancelarRP = metodosUtiles.establecerIcon("\\src\\imagenes\\btnCancelarR.png", obtenerAnchoBoton(btnSalir), obtenerAltoBoton(btnSalir));

        imgLoading1 = metodosUtiles.establecerIcon("\\src\\imagenes\\loading1.png", 70, 70);
        imgLoading2 = metodosUtiles.establecerIcon("\\src\\imagenes\\loading2.png", 70, 70);
        imgLoading3 = metodosUtiles.establecerIcon("\\src\\imagenes\\loading3.png", 70, 70);
        imgLoading4 = metodosUtiles.establecerIcon("\\src\\imagenes\\loading4.png", 70, 70);

        lblLoading = new JLabel();
        lblLoading.setIcon(imgLoading1);
        lblLoading.setBounds(20, 8, 70, 70);
        lblLoading.setVisible(false);

        btnAgregar.setIcon(imgAgregarNorm);
        btnAgregar.setRolloverIcon(imgAgregarRP);
        btnAgregar.setPressedIcon(imgAgregarPressed);

        btnSalir.setIcon(imgCancelarNorm);
        btnSalir.setRolloverIcon(imgCancelarRP);
        btnSalir.setPressedIcon(imgCancelarPressed);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnSalir);
        panelBotones.add(lblLoading);

        //Añadiendo objetos al lblFondo
        lblFondo.add(lblTitulo);
        lblFondo.add(panelTipoContacto);
        lblFondo.add(panelDatosPersonales);
        lblFondo.add(panelDatosDeContacto);
        lblFondo.add(panelVivienda);
        lblFondo.add(panelBotones);

        btnAgregar.addActionListener(new GestorEventos());
        btnSalir.addActionListener(new GestorEventos());
        btnPlus.addActionListener(new GestorEventos());
        btnPlus2.addActionListener(new GestorEventos());

    }

    /*Eventos de la ventena*/
    private class GestorEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnAgregar) {
                funcionBtnAgregar();
            } else if (e.getSource() == btnSalir) {
                funcionBtnSalir();
            } else if (e.getSource() == btnPlus) {
                funcionBtnPlus1();
            } else if (e.getSource() == btnPlus2) {
                funcionBtnPlus2();
            } else {
                PanelDeOpciones po = new PanelDeOpciones();
                po.mostrarMensajeDeDialogo("ERROR", null, "Boton sin implementar", "Evento desconocido", null);
            }
        }

    }

    //Funciones de los botones
    private void funcionBtnAgregar() {
        String fechaNacimiento, nombres, apellidos, tipoContacto;
        fechaNacimiento = campoFechaNacimiento.getText();
        nombres = campoNombres.getText();
        apellidos = campoApellidos.getText();
        tipoContacto = (String) comboTipoContacto.getSelectedItem();

        if (("".equals(nombres)) || ("".equals(apellidos)) || ("".equals(fechaNacimiento))) {
            PanelDeOpciones po = new PanelDeOpciones();
            po.mostrarMensajeDeDialogo("INFORMACION", null, "Todos los campos son requeridos", "Campos vacios", null);
            return;
        }

        if (!formatoFechaNacimiento(fechaNacimiento)) {
            PanelDeOpciones po = new PanelDeOpciones();
            po.mostrarMensajeDeDialogo("INFORMACION", null, "El formato de la fecha no es el correcto, recuerde que el formato es \"DD/MM/YEAR\" . Ejemplo: 20/08/2003 ", "Formato invalido", null);
            return;
        }

        if (!formatoNombreOApellido(nombres)) {
            PanelDeOpciones po = new PanelDeOpciones();
            po.mostrarMensajeDeDialogo("INFORMACION", null, "El formato del nonbre no es correcto, debe tener como minimo 2 caracteres y maximo 25 ", "Formato invalido", null);
            return;
        }

        if (!formatoNombreOApellido(apellidos)) {
            PanelDeOpciones po = new PanelDeOpciones();
            po.mostrarMensajeDeDialogo("INFORMACION", null, "El formato del apellido no es correcto, debe tener como minimo 2 caracteres y maximo 25 ", "Formato invalido", null);
            return;
        }

        int identificacion = 0;

        if (!formatoNumeroIdentificacion(campoIdentificacion.getText())) {
            PanelDeOpciones po = new PanelDeOpciones();
            po.mostrarMensajeDeDialogo("INFORMACION", null, "El formato de la identificacion no es correcto, como maximo son 10 caracteres", "Formato invalido", null);
            return;
        }

        try {
            identificacion = Integer.parseInt(campoIdentificacion.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La identificacion debe ser un valor valido", "Formato de identificacion", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (identificacion == 0) {
            JOptionPane.showMessageDialog(null, "La identificacion debe ser un valor valido", "Formato de identificacion", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (telefonos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese al menos un dato de contacto", "Falta de datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (direcciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese al menos una vivienda", "Falta de datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Contacto c = new Contacto(tipoContacto, fechaNacimiento, identificacion, nombres, apellidos, direcciones, telefonos);

        modelo.agregarContacto(c);
        modelo.guardarEnArchivo();
        modelo.fireTableDataChanged();

        direcciones.clear();
        telefonos.clear();
        campoApellidos.setText("");
        campoBarrio.setText("");
        campoCiudad.setText("");
        campoFechaNacimiento.setText("");
        campoIdentificacion.setText("");
        campoNombres.setText("");
        campoTelefono.setText("");

        seHaModificado = true;

        JOptionPane.showMessageDialog(null, "Contacto agregado con exito", "Contacto agregado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void funcionBtnSalir() {
        if (!seHaModificado) {
            PanelDeOpciones pnlop = new PanelDeOpciones();
            int opcion = pnlop.confirmarOpcion(null, "No has agregado a ningún contacto, ¿Seguro que deseas salir? ", "Seleccione una opcion", "QUESTION");
            System.out.println(opcion);
            if (opcion == 0) {
                dispose();
                Principal v = new Principal(modelo);
            } else {
            }
        } else {
            dispose();
            try {
                JOptionPane.showMessageDialog(null, "Espere mientras carga los contactos", "Espere", JOptionPane.INFORMATION_MESSAGE);
                Thread.sleep(4000);
                Principal v = new Principal(modelo);

            } catch (Exception ex) {
            }
        }
    }

    private void funcionBtnPlus1() {
        String tipoTelefono;
        tipoTelefono = (String) comboTipoTelefono.getSelectedItem();

        if ("".equals(campoTelefono.getText())) {
            JOptionPane.showMessageDialog(null, "Ingrese un numero de telefono", "Campo vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (!formatoTelefono(campoTelefono.getText())) {
            PanelDeOpciones po = new PanelDeOpciones();
            po.mostrarMensajeDeDialogo("INFORMACION", null, "El formato del telefono es invalido, como minimo deben haber 7 numeros y como maximo 10", "Formato invalido", null);
            return;
        }

        int numTel = 0;

        try {
            numTel = Integer.parseInt(campoTelefono.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El telefono debe ser un valor valido", "Formato de telefono", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (numTel == 0) {
            JOptionPane.showMessageDialog(null, "El telefono debe ser un valor valido", "Formato de telefono", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Telefono miTelefono = new Telefono(numTel, tipoTelefono);
        telefonos.add(miTelefono);

        campoTelefono.setText("");

        JOptionPane.showMessageDialog(null, "Se agrego correctamente el telefono #" + telefonos.size(), "Telefono agregado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void funcionBtnPlus2() {
        String barrio, ciudad;

        barrio = campoBarrio.getText();
        ciudad = campoCiudad.getText();

        if ("".equals(barrio)) {
            JOptionPane.showMessageDialog(null, "Ingrese el barrio", "Campo vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if ("".equals(ciudad)) {
            JOptionPane.showMessageDialog(null, "Ingrese la ciudad", "Campo vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (!formatoBarioCiudad(ciudad)) {
            JOptionPane.showMessageDialog(null, "El formato de la ciudad es invalido, deben haber como minimo 2 carcateres y maximo 15", "Formato invalido", JOptionPane.INFORMATION_MESSAGE);

        }
        
        if (!formatoBarioCiudad(barrio)) {
            JOptionPane.showMessageDialog(null, "El formato del barrio es invalido, deben haber como minimo 2 carcateres y maximo 15", "Formato invalido", JOptionPane.INFORMATION_MESSAGE);

        }

        Direccion miDir = new Direccion(barrio, ciudad);
        direcciones.add(miDir);

        campoBarrio.setText("");
        campoCiudad.setText("");

        JOptionPane.showMessageDialog(null, "Se agrego correctamente la direccion #" + direcciones.size(), "Direccion agregada", JOptionPane.INFORMATION_MESSAGE);

    }

    private boolean formatoNumeroIdentificacion(String identifiacion) {
        return identifiacion.length() <= 10;
    }

    private boolean formatoNombreOApellido(String linea) {
        return !(linea.length() < 2 || linea.length() > 25);
    }

    private boolean formatoTelefono(String telefono) {
        return !(telefono.length() < 7 || telefono.length() > 10);
    }

    private boolean formatoBarioCiudad(String linea) {
        return !(linea.length() < 2 || linea.length() > 15);
    }

    private boolean formatoFechaNacimiento(String fecha) {
        int dia;
        int mes;
        int year;

        String sdia;
        String smes;
        String syear;

        //Ahora debemos sacar los datos de las direcciones
        try {
            StringTokenizer tokens = new StringTokenizer(fecha, "/");

            sdia = (tokens.nextToken());
            smes = (tokens.nextToken());
            syear = (tokens.nextToken());

            if (tokens.hasMoreTokens()) {
                return false;
            }

            if ((sdia + "").length() != 2
                    || (smes + "").length() != 2
                    || (syear + "").length() != 4 //                   
                    || Character.getNumericValue((sdia + "").charAt(0)) < 0
                    || Character.getNumericValue((sdia + "").charAt(0)) > 3
                    || Character.getNumericValue((smes + "").charAt(0)) < 0
                    || Character.getNumericValue((smes + "").charAt(0)) > 1
                    || (Character.getNumericValue((smes + "").charAt(0)) == 1 && Character.getNumericValue((smes + "").charAt(1)) > 2)
                    || Character.getNumericValue((smes + "").charAt(1)) < 0) {
                return false;
            }

            dia = Integer.parseInt(sdia);
            mes = Integer.parseInt(smes);
            year = Integer.parseInt(syear);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Metodos para obtener el ancho y alto de un boton en el contenedor
    private int obtenerAnchoBoton(JButton btn) {
        int ancho = (int) btn.getBounds().getWidth();
        return ancho;
    }

    private int obtenerAltoBoton(JButton btn) {
        int alto = (int) btn.getBounds().getHeight();
        return alto;
    }

}
