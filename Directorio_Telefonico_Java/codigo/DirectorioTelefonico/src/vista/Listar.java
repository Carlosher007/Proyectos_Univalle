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
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Vista para listar todos los contactos
 */
public class Listar extends JFrame {

    /**
     * Inicializamos los atributos de la clase
     */
    //Ancho y alto de ventana
    private int anchoV = 700;
    private int largoV = 700;

    //Iconos e Imagenes
    private ImageIcon imgFondo;
    private ImageIcon imgModificarNorm;
    private ImageIcon imgModificarRP;
    private ImageIcon imgModificarPressed;
    private ImageIcon imgEliminarNorm;
    private ImageIcon imgEliminarRP;
    private ImageIcon imgEliminarPressed;
    private ImageIcon imgCancelarNorm;
    private ImageIcon imgCancelarRP;
    private ImageIcon imgCancelarPressed;

    //Botones
    private JButton btnCancelar;
    private JButton btnModificar;
    private JButton btnEliminar;

    //Contenedor Principal
    private Container contPrincipal;

    //Labels
    private JLabel lblFondo;
    private JLabel lblTitulo;

    //Paneles
    private JPanel panelTitulo;
    private JPanel panelBotones;
    private JPanel panelTabla;

    //Tabla
    private JTable tablaContactos;
    private JScrollPane scrollTabla;

    //Funcionamiento Interno
    protected ModeloContactos modelo;

    public Listar(ModeloContactos modelo) {
        modelo = new ModeloContactos();
        this.modelo = modelo;
        modelo.cargarContactos();
        iniciarComponentes();
        iniciarVentana();
    }

    private void iniciarVentana() {
        setSize(anchoV, largoV);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Directorio Telefonico");
        setResizable(true);

        Image icono = new ImageIcon(getClass().getResource("/imagenes/iconoVentana.png")).getImage();
        setIconImage(icono);
    }

    private void iniciarComponentes() {

        //Contenedor Principal
        contPrincipal = getContentPane();
        contPrincipal.setLayout(null);

        //Fondo
        imgFondo = metodosUtiles.establecerIcon("\\src\\imagenes\\fondoInicio.png",
                anchoV, largoV);
        lblFondo = new JLabel(imgFondo);
        lblFondo.setBounds(0, 0, anchoV, largoV);

        //Añadir objetos al contenedor Principal
        contPrincipal.add(lblFondo);

        //Paneles
        panelTitulo = new JPanel();
        panelTitulo.setBounds(14, 10, 650, 60);
        panelTitulo.setLayout(null);
        panelTitulo.setOpaque(false);

        panelTabla = new JPanel();
        panelTabla.setBounds(14, 80, 650, 500);
        panelTabla.setLayout(null);
        panelTabla.setOpaque(false);

        panelTabla.setBorder(BorderFactory.createTitledBorder(null, "Contactos",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Tahoma", 1, 13), new Color(26, 25, 25)));

        panelBotones = new JPanel();
        panelBotones.setBounds(14, 580, 650, 60);
        panelBotones.setLayout(null);
        panelBotones.setOpaque(false);

        //Añadiendo objetos al lblFondo
        lblFondo.add(panelTitulo);
        lblFondo.add(panelBotones);
        lblFondo.add(panelTabla);

        //Titulo
        lblTitulo = new JLabel("Lista de contactos");
        Font myFont2 = new Font("Arial", Font.BOLD, 28);
        lblTitulo.setFont(myFont2);
        lblTitulo.setBounds(10, 10, 620, 40);

        tablaContactos = new JTable();
        scrollTabla = new JScrollPane();
        scrollTabla.setBounds(15, 20, 620, 460);

        scrollTabla.setViewportView(tablaContactos);

        tablaContactos.setModel(modelo);

        tablaContactos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tablaContactos.getModel());
        tablaContactos.setRowSorter(sorter);

        //Como organizar tabla 
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);

        tablaContactos.setOpaque(false);
        scrollTabla.setOpaque(false);

        //Añadiendo objtos al panel de tabla
        panelTabla.add(scrollTabla);

        //Botones
        btnModificar = new BotonSinFondo();
        btnModificar.setBounds(15, 20, 120, 40);

        btnEliminar = new BotonSinFondo();
        btnEliminar.setBounds(520, 20, 120, 40);

        btnCancelar = new BotonSinFondo();
        btnCancelar.setBounds(600, 10, 40, 40);

        //Iconos
        imgModificarNorm = metodosUtiles.establecerIcon("\\src\\imagenes\\btnModificarN.png", obtenerAnchoBoton(btnModificar), obtenerAltoBoton(btnModificar));
        imgModificarPressed = metodosUtiles.establecerIcon("\\src\\imagenes\\btnModificarP.png", obtenerAnchoBoton(btnModificar), obtenerAltoBoton(btnModificar));
        imgModificarRP = metodosUtiles.establecerIcon("\\src\\imagenes\\btnModificarR.png", obtenerAnchoBoton(btnModificar), obtenerAltoBoton(btnModificar));

        imgEliminarNorm = metodosUtiles.establecerIcon("\\src\\imagenes\\btnEliminarNorm.png", obtenerAnchoBoton(btnEliminar), obtenerAltoBoton(btnEliminar));
        imgEliminarPressed = metodosUtiles.establecerIcon("\\src\\imagenes\\btnEliminarPressed.png", obtenerAnchoBoton(btnEliminar), obtenerAltoBoton(btnEliminar));
        imgEliminarRP = metodosUtiles.establecerIcon("\\src\\imagenes\\btnEliminarRoll.png", obtenerAnchoBoton(btnEliminar), obtenerAltoBoton(btnEliminar));

        imgCancelarNorm = metodosUtiles.establecerIcon("\\src\\imagenes\\btnCancelarN.png", obtenerAnchoBoton(btnCancelar), obtenerAltoBoton(btnCancelar));
        imgCancelarPressed = metodosUtiles.establecerIcon("\\src\\imagenes\\btnCancelarP.png", obtenerAnchoBoton(btnCancelar), obtenerAltoBoton(btnCancelar));
        imgCancelarRP = metodosUtiles.establecerIcon("\\src\\imagenes\\btnCancelarR.png", obtenerAnchoBoton(btnCancelar), obtenerAltoBoton(btnCancelar));

        //Asignar iconos
        btnModificar.setIcon(imgModificarNorm);
        btnModificar.setRolloverIcon(imgModificarRP);
        btnModificar.setPressedIcon(imgModificarPressed);

        btnEliminar.setIcon(imgEliminarNorm);
        btnEliminar.setRolloverIcon(imgEliminarRP);
        btnEliminar.setPressedIcon(imgEliminarPressed);

        btnCancelar.setIcon(imgCancelarNorm);
        btnCancelar.setRolloverIcon(imgCancelarRP);
        btnCancelar.setPressedIcon(imgCancelarPressed);

        //Añadiendo objetos al panel principal
        panelTitulo.add(lblTitulo);
        panelTitulo.add(btnCancelar);
        panelTabla.add(scrollTabla);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);

        //Añadimos listeners
        btnModificar.addMouseListener(new ManejadorDeEventos());
        btnEliminar.addMouseListener(new ManejadorDeEventos());
        btnCancelar.addMouseListener(new ManejadorDeEventos());

    }

    private class ManejadorDeEventos extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == btnModificar) {
                int fila = tablaContactos.getSelectedRow();
                if (fila == -1) {
                    PanelDeOpciones pnlop = new PanelDeOpciones();
                    pnlop.mostrarMensajeDeDialogo("ERROR", null, "Seleccione el contacto a modificar", "Error de selección", null);
                } else {
                    dispose();
                    Actualizar ventana = new Actualizar(modelo, fila,false);
                }
            } else if (e.getSource() == btnEliminar) {
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
            } else if (e.getSource() == btnCancelar) {
                dispose();
                try {
                    JOptionPane.showMessageDialog(null, "Espere mientras carga los contactos", "Espere", JOptionPane.INFORMATION_MESSAGE);
                    Thread.sleep(3000);
                    Principal v = new Principal(modelo);

                } catch (Exception ex) {

                }
            } else {
                PanelDeOpciones pnlop = new PanelDeOpciones();
                pnlop.mostrarMensajeDeDialogo("ERROR", null, "Ocurrio el error", "Error al presionar el boton", null);
            }
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
