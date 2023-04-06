package clases;

import funcionalidades.PanelDeOpciones;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 * MINIPROYECTO 3 - Directorio Telefonico
 *
 * @author Sebastian Idrobo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @author Carlos Andres Hernandez Agudelo
 * <carlos.hernandez.agudelo@correounivalle.edu.co>
 * @function Clase que sirve como modelo para la tabla de contactos en las clases de
 * vista, y que permite la interaccion directa entre la vista y la lista contactos de la
 * logica
 */
public class ModeloContactos extends AbstractTableModel {

    private ListaDeContactos contactos;

    public ModeloContactos() {
        contactos = new ListaDeContactos();
        contactos.cargarContactos();

    }

    public void cargarContactos() {
        contactos.cargarContactos();
    }

    @Override
    public int getRowCount() {
        return contactos.total();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contacto aux = contactos.obtener(rowIndex);
        switch (columnIndex) {
            case 0:
                return aux.getTipoContacto();
            case 1:
                return aux.getFecha();
            case 2:
                return aux.getIdenficiacion();
            case 3:
                return aux.getNombres();
            case 4:
                return aux.getApellidos();
            case 5:
                String direcciones = "";
                for (int i = 0; i < aux.getDirecciones().size(); i++) {
                    Direccion auxDir = aux.getDirecciones().get(i);
                    if (i == aux.getDirecciones().size() - 1) {
                        direcciones += auxDir.getBarrio() + "-" + auxDir.getCiudad();
                    } else {
                        direcciones += auxDir.getBarrio() + "-" + auxDir.getCiudad() + ",";
                    }
                }
                return direcciones;
            default:
                String telefonos = "";
                for (int j = 0; j < aux.getTelefonos().size(); j++) {
                    Telefono auxTel = aux.getTelefonos().get(j);
                    if (j == aux.getTelefonos().size() - 1) {
                        telefonos += auxTel.getNumero() + "-" + auxTel.getTipo();
                    } else {
                        telefonos += auxTel.getNumero() + "-" + auxTel.getTipo() + ",";
                    }
                }
                return telefonos;
        }
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "TIPO";
            case 1:
                return "FECHA";
            case 2:
                return "IDENTIFICACIÓN";
            case 3:
                return "NOMBRES";
            case 4:
                return "APELLIDOS";
            case 5:
                return "DIRECCIONES";
            default:
                return "TELEFONOS";

        }
    }

    @Override
    public Class getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            default:
                return String.class;

        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    @Override
    public void setValueAt(Object value, int fila, int columna) {

        boolean modificar = true;

        Contacto aux = contactos.obtener(fila);
        switch (columna) {
            case 0:
                aux.setTipoContacto((String) value);
                break;
            case 1:
                if (formatoFechaNacimiento((String) value)) {
                    aux.setFecha((String) value);
                } else {
                    PanelDeOpciones po = new PanelDeOpciones();
                    po.mostrarMensajeDeDialogo("INFORMACION", null, "El formato de la fecha no es el correcto, recuerde que el formato es \"DD/MM/YEAR\" . Ejemplo: 20/08/2003 ", "Formato invalido", null);
                    modificar = false;
                }
                break;
            case 2:

                if (formatoNumeroIdentificacion(value + "")) {
                    try {
                        aux.setIdenficiacion((int) value);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Ingrese una identificación en formato correcto", "Dato ingresado invalido", JOptionPane.ERROR_MESSAGE, null);
                        modificar = false;
                    }
                } else {
                    PanelDeOpciones po = new PanelDeOpciones();
                    po.mostrarMensajeDeDialogo("INFORMACION", null, "El formato de la identificacion no es correcto, como maximo son 10 caracteres", "Formato invalido", null);
                    modificar = false;
                }

                break;
            case 3:

                if (formatoNombreOApellido((String) value)) {
                    aux.setNombres((String) value);
                } else {
                    PanelDeOpciones po = new PanelDeOpciones();
                    po.mostrarMensajeDeDialogo("INFORMACION", null, "El formato del nonbre no es correcto, debe tener como minimo 2 caracteres y maximo 25 ", "Formato invalido", null);
                    return;
                }
                break;
            case 4:
                if (formatoNombreOApellido((String) value)) {
                    aux.setApellidos((String) value);
                } else {
                    PanelDeOpciones po = new PanelDeOpciones();
                    po.mostrarMensajeDeDialogo("INFORMACION", null, "El formato del apellido no es correcto, debe tener como minimo 2 caracteres y maximo 25 ", "Formato invalido", null);
                    return;
                }
                break;
            case 5:
                ArrayList<Direccion> ArrayDireccionesFinal = new ArrayList<>();

                LinkedList<String> LKDirecciones = new LinkedList();
                LKDirecciones.add((String) value);

                boolean cambiarDirecciones = true;

                LinkedList<String> ArrayDirecciones = new LinkedList();
                try {
                    for (int j = 0; j < LKDirecciones.size(); j++) {
                        String linea2 = LKDirecciones.get(j);
                        StringTokenizer tokens2 = new StringTokenizer(linea2, ",");

                        while (tokens2.hasMoreTokens()) {
                            ArrayDirecciones.add(tokens2.nextToken());
                        }
                        for (int k = 0; k < ArrayDirecciones.size(); k++) {
                            String linea3 = ArrayDirecciones.get(k);
                            StringTokenizer tokens3 = new StringTokenizer(linea3, "-");
                            String barrio = tokens3.nextToken();
                            String ciudad = tokens3.nextToken();
                            if (!formatoBarioCiudad(barrio)) {
                                JOptionPane.showMessageDialog(null, "El formato del barrio es invalido, deben haber como minimo 2 carcateres y maximo 15", "Formato invalido", JOptionPane.INFORMATION_MESSAGE);
                                cambiarDirecciones = false;
                                modificar = false;
                            } else if (!formatoBarioCiudad(ciudad)) {
                                JOptionPane.showMessageDialog(null, "El formato de la ciudad es invalido, deben haber como minimo 2 carcateres y maximo 15", "Formato invalido", JOptionPane.INFORMATION_MESSAGE);
                                cambiarDirecciones = false;
                                modificar = false;
                            } else {
                                Direccion miDireccion = new Direccion(barrio, ciudad);
                                ArrayDireccionesFinal.add(miDireccion);
                            }
                        }
                    }
                } catch (Exception ex) {
                    cambiarDirecciones = false;
                    JOptionPane.showMessageDialog(null, "Hubo un error al digitar las direcciones, recuerde que el formato es : \"barrio1-ciudad1,barrio2-ciudad2,...\"  ", "Error", JOptionPane.ERROR_MESSAGE, null);
                    modificar = false;
                }

                if (cambiarDirecciones) {
                    aux.setDirecciones(ArrayDireccionesFinal);
                }
                break;
            case 6:

                boolean cambiarTelefonos = true;

                ArrayList<Telefono> ArrayTelefonoFinal = new ArrayList<>();

                LinkedList<String> LKTelefonos = new LinkedList();
                LKTelefonos.add((String) value);

                LinkedList<String> ArrayTelefonos = new LinkedList();

                try {
                    for (int j = 0; j < LKTelefonos.size(); j++) {
                        String linea = LKTelefonos.get(j);
                        StringTokenizer tokens = new StringTokenizer(linea, ",");

                        while (tokens.hasMoreTokens()) {
                            ArrayTelefonos.add(tokens.nextToken());
                        }
                        for (int k = 0; k < ArrayTelefonos.size(); k++) {
                            String linea2 = ArrayTelefonos.get(k);
                            StringTokenizer tokens2 = new StringTokenizer(linea2, "-");
                            String numeroS = tokens2.nextToken();
                            if (!formatoTelefono(numeroS)) {
                                PanelDeOpciones po = new PanelDeOpciones();
                                po.mostrarMensajeDeDialogo("INFORMACION", null, "El formato del telefono es invalido, como minimo deben haber 7 numeros y como maximo 10", "Formato invalido", null);
                                cambiarTelefonos = false;
                            }
                            int numero = 0;
                            try {
                                numero = Integer.parseInt(numeroS);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Ingrese un numero de telefono correctamente", "Dato ingresado invalido", JOptionPane.ERROR_MESSAGE, null);
                                cambiarTelefonos = false;
                            }
                            String tipo = tokens2.nextToken();
                            if (!"Movil".equals(tipo) && !"Casa".equals(tipo) && !"Oficina".equals(tipo) && !"Familiar".equals(tipo)) {
                                cambiarTelefonos = false;
                                modificar = false;
                                JOptionPane.showMessageDialog(null, "Recuerde que los tipos de telefono son: \"Movil\",\"Casa\",\"Familiar\" y \"Oficina\" ", "Dato ingresado invalido", JOptionPane.ERROR_MESSAGE, null);
                                break;
                            }
                            Telefono miTelefono = new Telefono(numero, tipo);
                            ArrayTelefonoFinal.add(miTelefono);
                        }
                    }
                } catch (Exception ex) {
                    cambiarTelefonos = false;
                    JOptionPane.showMessageDialog(null, "Hubo un error al digitar el telefono, recuerde que el formato es : \"numero1-tipo1,numero2-tipo2,...\"  ", "Error", JOptionPane.ERROR_MESSAGE, null);
                    modificar = false;
                }

                if (cambiarTelefonos) {
                    aux.setTelefonos(ArrayTelefonoFinal);
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Ocurrio un error", "Error", JOptionPane.ERROR_MESSAGE, null);
                break;

        }
        guardarEnArchivo();
        fireTableCellUpdated(fila, columna);

        if (modificar) {
            JOptionPane.showMessageDialog(null, "La tabla se modifico correctamente", "Actualizacion de tabla", JOptionPane.INFORMATION_MESSAGE, null);
        }
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

    public void agregarContacto(Contacto contacto) {
        contactos.agregar(contacto);
        this.fireTableDataChanged();
    }

    public void eliminarContacto(int indice) {
        contactos.eliminar(indice);
        this.fireTableDataChanged();
    }

    public void guardarEnArchivo() {
        contactos.guardarEnArchivo();
    }

    public ListaDeContactos getContactos() {
        return contactos;
    }

    public void setContactos(ListaDeContactos contactos) {
        this.contactos = contactos;
    }

    public void exportar() {
        contactos.exportar();
    }

    public void restaurar() {
        contactos.restaurar();
    }

}
