package vista;

import clases.ModeloContactos;
import funcionalidades.BotonSinFondo;
import funcionalidades.PanelDeOpciones;
import funcionalidades.metodosUtiles;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Vista principal del directorio telefonico
 */
public class Principal extends JFrame {

    /**
     * Inicializamos los atributos de la clase
     */
    //Ancho y alto de ventana
    private int anchoV = 700;
    private int largoV = 500;

    //Iconos e Imagenes
    private ImageIcon imgFondo;
    private ImageIcon imgAgregarNorm;
    private ImageIcon imgAgregarRP;
    private ImageIcon imgAgregarPressed;
    private ImageIcon imgModificarNorm;
    private ImageIcon imgModificarRP;
    private ImageIcon imgModificarPressed;
    private ImageIcon imgEliminarNorm;
    private ImageIcon imgEliminarRP;
    private ImageIcon imgEliminarPressed;
    private ImageIcon imgListarNorm;
    private ImageIcon imgListarRP;
    private ImageIcon imgListarPressed;
    private ImageIcon imgActualizarNorm;
    private ImageIcon imgActualizarRP;
    private ImageIcon imgActualizarPressed;
    private ImageIcon imgRestaurarNorm;
    private ImageIcon imgRestaurarRP;
    private ImageIcon imgRestaurarPressed;
    private ImageIcon imgExportarNorm;
    private ImageIcon imgExportarRP;

    //Botones
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnListar;
    private JButton btnActualizar;
    private JButton btnExportar;
    private JButton btnRestaurar;

    //Contenedor Principal
    private Container contPrincipal;

    //Labels
    private JLabel lblFondo;

    //Paneles
    private JPanel panelBotones;
    private JPanel panelTabla;

    //Tabla
    protected JTable tablaContactos;
    private JScrollPane scrollTabla;

    //Funcionamiento Interno
    protected ModeloContactos modelo;

    //Constructor
    public Principal(ModeloContactos modelo) {
        //Si recibimos un modelo por parametro se lo asignamos al atributo interno de modelo
        modelo = new ModeloContactos();
        this.modelo = modelo;
        modelo.cargarContactos();
        iniciarComponentes();
        iniciarVentana();
    }

    public Principal() {
        modelo = new ModeloContactos();
        iniciarComponentes();
        iniciarVentana();
    }

    //Metodos
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

        //Añadir objetos al contenedor Principal
        contPrincipal.add(lblFondo);

        //Paneles
        panelBotones = new JPanel();
        panelBotones.setBounds(14, 10, 650, 140);
        panelBotones.setLayout(null);
        panelBotones.setOpaque(false);

        panelTabla = new JPanel();
        panelTabla.setBounds(14, 160, 650, 280);
        panelTabla.setLayout(null);
        panelTabla.setOpaque(false);

        panelTabla.setBorder(BorderFactory.createTitledBorder(null, "Contactos",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", 1, 13), new Color(26, 25, 25)));

        //Añadiendo objetos al lblFondo
        lblFondo.add(panelBotones);
        lblFondo.add(panelTabla);

        //Botones//
        btnModificar = new BotonSinFondo();
        btnModificar.setBounds(20, 30, 130, 40);

        btnAgregar = new BotonSinFondo();
        btnAgregar.setBounds(180, 30, 130, 40);

        btnEliminar = new BotonSinFondo();
        btnEliminar.setBounds(340, 30, 130, 40);

        btnListar = new BotonSinFondo();
        btnListar.setBounds(500, 30, 130, 40);

        btnActualizar = new BotonSinFondo();
        btnActualizar.setBounds(265, 100, 130, 40);

        btnExportar = new BotonSinFondo();
        btnExportar.setBounds(180, 100, 130, 40);

        btnRestaurar = new BotonSinFondo();
        btnRestaurar.setBounds(340, 100, 130, 40);

        //Iconos
        imgModificarNorm = metodosUtiles.establecerIcon("\\src\\imagenes\\btnModificarN.png", obtenerAnchoBoton(btnModificar), obtenerAltoBoton(btnModificar));
        imgModificarPressed = metodosUtiles.establecerIcon("\\src\\imagenes\\btnModificarP.png", obtenerAnchoBoton(btnModificar), obtenerAltoBoton(btnModificar));
        imgModificarRP = metodosUtiles.establecerIcon("\\src\\imagenes\\btnModificarR.png", obtenerAnchoBoton(btnModificar), obtenerAltoBoton(btnModificar));

        imgAgregarNorm = metodosUtiles.establecerIcon("\\src\\imagenes\\btnAgregarNorm.png", obtenerAnchoBoton(btnAgregar), obtenerAltoBoton(btnAgregar));
        imgAgregarPressed = metodosUtiles.establecerIcon("\\src\\imagenes\\btnAgregarPressed.png", obtenerAnchoBoton(btnAgregar), obtenerAltoBoton(btnAgregar));
        imgAgregarRP = metodosUtiles.establecerIcon("\\src\\imagenes\\btnAgregarRoll.png", obtenerAnchoBoton(btnAgregar), obtenerAltoBoton(btnAgregar));

        imgEliminarNorm = metodosUtiles.establecerIcon("\\src\\imagenes\\btnEliminarNorm.png", obtenerAnchoBoton(btnEliminar), obtenerAltoBoton(btnEliminar));
        imgEliminarPressed = metodosUtiles.establecerIcon("\\src\\imagenes\\btnEliminarPressed.png", obtenerAnchoBoton(btnEliminar), obtenerAltoBoton(btnEliminar));
        imgEliminarRP = metodosUtiles.establecerIcon("\\src\\imagenes\\btnEliminarRoll.png", obtenerAnchoBoton(btnEliminar), obtenerAltoBoton(btnEliminar));

        imgListarNorm = metodosUtiles.establecerIcon("\\src\\imagenes\\btnListarNorm.png", obtenerAnchoBoton(btnListar), obtenerAltoBoton(btnListar));
        imgListarPressed = metodosUtiles.establecerIcon("\\src\\imagenes\\btnListarPressed.png", obtenerAnchoBoton(btnListar), obtenerAltoBoton(btnListar));
        imgListarRP = metodosUtiles.establecerIcon("\\src\\imagenes\\btnListarRoll.png", obtenerAnchoBoton(btnListar), obtenerAltoBoton(btnListar));

        imgActualizarNorm = metodosUtiles.establecerIcon("\\src\\imagenes\\btnActualizarNorm.png", obtenerAnchoBoton(btnActualizar), obtenerAltoBoton(btnActualizar));
        imgActualizarPressed = metodosUtiles.establecerIcon("\\src\\imagenes\\btnActualizarPressed.png", obtenerAnchoBoton(btnActualizar), obtenerAltoBoton(btnActualizar));
        imgActualizarRP = metodosUtiles.establecerIcon("\\src\\imagenes\\btnActualizarRoll.png", obtenerAnchoBoton(btnActualizar), obtenerAltoBoton(btnActualizar));

        imgRestaurarNorm = metodosUtiles.establecerIcon("\\src\\imagenes\\btnRestaurarN.png", obtenerAnchoBoton(btnRestaurar), obtenerAltoBoton(btnRestaurar));
        imgRestaurarPressed = metodosUtiles.establecerIcon("\\src\\imagenes\\btnRestaurarP.png", obtenerAnchoBoton(btnRestaurar), obtenerAltoBoton(btnRestaurar));
        imgRestaurarRP = metodosUtiles.establecerIcon("\\src\\imagenes\\btnRestaurarR.png", obtenerAnchoBoton(btnRestaurar), obtenerAltoBoton(btnRestaurar));

        imgExportarNorm = metodosUtiles.establecerIcon("\\src\\imagenes\\btnExportarN.png", obtenerAnchoBoton(btnExportar), obtenerAltoBoton(btnExportar));
        imgExportarRP = metodosUtiles.establecerIcon("\\src\\imagenes\\btnExportarRP.png", obtenerAnchoBoton(btnExportar), obtenerAltoBoton(btnExportar));

        //Asignar iconos a botones
        btnModificar.setIcon(imgModificarNorm);
        btnModificar.setRolloverIcon(imgModificarRP);
        btnModificar.setPressedIcon(imgModificarPressed);

        btnAgregar.setIcon(imgAgregarNorm);
        btnAgregar.setRolloverIcon(imgAgregarRP);
        btnAgregar.setPressedIcon(imgAgregarPressed);

        btnEliminar.setIcon(imgEliminarNorm);
        btnEliminar.setRolloverIcon(imgEliminarRP);
        btnEliminar.setPressedIcon(imgEliminarPressed);

        btnListar.setIcon(imgListarNorm);
        btnListar.setRolloverIcon(imgListarRP);
        btnListar.setPressedIcon(imgListarPressed);

        btnActualizar.setIcon(imgActualizarNorm);
        btnActualizar.setRolloverIcon(imgActualizarRP);
        btnActualizar.setPressedIcon(imgActualizarPressed);

        btnExportar.setIcon(imgExportarNorm);
        btnExportar.setRolloverIcon(imgExportarRP);
        btnExportar.setPressedIcon(imgExportarRP);

        btnRestaurar.setIcon(imgRestaurarNorm);
        btnRestaurar.setRolloverIcon(imgRestaurarRP);
        btnRestaurar.setPressedIcon(imgRestaurarPressed);

        //Añadiendo objetos al panel de botones
        panelBotones.add(btnModificar);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);
        panelBotones.add(btnExportar);
        panelBotones.add(btnRestaurar);

        //Generamos la tabla con el modelo de contactos
        tablaContactos = new JTable();
        scrollTabla = new JScrollPane();
        scrollTabla.setBounds(10, 20, 630, 250);

        scrollTabla.setViewportView(tablaContactos);

        tablaContactos.setModel(modelo);

        tablaContactos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tablaContactos.setOpaque(false);
        scrollTabla.setOpaque(false);

        //Añadiendo objetos al panel de tabla
        panelTabla.add(scrollTabla);

        //Añadimos listeners
        btnModificar.addMouseListener(new ManejadorDeEventos());
        btnAgregar.addMouseListener(new ManejadorDeEventos());
        btnEliminar.addMouseListener(new ManejadorDeEventos());
        btnListar.addMouseListener(new ManejadorDeEventos());
        btnActualizar.addMouseListener(new ManejadorDeEventos());
        btnExportar.addMouseListener(new ManejadorDeEventos());
        btnRestaurar.addMouseListener(new ManejadorDeEventos());

    }

    /*Clase que se encarga de los eventos del mouse propios de la clase*/
    private class ManejadorDeEventos extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == btnModificar) {
                funcionBtnModificar();
            } else if (e.getSource() == btnAgregar) {
                funcionBtnAgregar();
            } else if (e.getSource() == btnEliminar) {
                funcionBtnEliminar();
            } else if (e.getSource() == btnListar) {
                funcionBtnListar();
            } else if (e.getSource() == btnExportar) {
                modelo.exportar();
            } else if (e.getSource() == btnRestaurar) {
                funcionBtnRestaurar();
            } else {
                PanelDeOpciones pnlop = new PanelDeOpciones();
                pnlop.mostrarMensajeDeDialogo("ERROR", null, "Ocurrio el error", "Error al presionar el boton", null);
            }
        }
    }

    //Funciones de los botones
    private void funcionBtnEliminar() {
        int fila = tablaContactos.getSelectedRow();
        if (fila >= 0) {
            modelo.eliminarContacto(fila);
            modelo.guardarEnArchivo();
            modelo.fireTableDataChanged();
            PanelDeOpciones pnlop = new PanelDeOpciones();
            pnlop.mostrarMensajeDeDialogo("INFORMACION", null, "Contacto eliminado correctamente", "Eliminado correctamentre", null);
        } else {
            PanelDeOpciones pnlop = new PanelDeOpciones();
            pnlop.mostrarMensajeDeDialogo("ERROR", null, "Seleccione el contacto a eliminar", "Error de selección", null);
        }
    }

    private void funcionBtnAgregar() {
        dispose();
        Agregar ventana = new Agregar(modelo);
    }

    private void funcionBtnModificar() {
        int fila = tablaContactos.getSelectedRow();
        if (fila == -1) {
            PanelDeOpciones pnlop = new PanelDeOpciones();
            pnlop.mostrarMensajeDeDialogo("ERROR", null, "Seleccione el contacto a modificar", "Error de selección", null);
        } else {
            dispose();
            Actualizar ventana = new Actualizar(modelo, fila,true);
        }
    }

    private void funcionBtnListar() {
        dispose();
        Listar ventana = new Listar(modelo);
    }

    private void funcionBtnRestaurar() {
        modelo.restaurar();
        try {
            PanelDeOpciones pnlop = new PanelDeOpciones();

            pnlop.mostrarMensajeDeDialogo("INFORMACION", null, "Espere unos segundos mientras se restaura", "Cargando...", null);
            Thread.sleep(6000);
            modelo.cargarContactos();
            modelo.fireTableDataChanged();
        } catch (Exception ex) {

        }
    }

    //Getter del modelo
    public ModeloContactos obtenerModelo() {
        return this.modelo;
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
