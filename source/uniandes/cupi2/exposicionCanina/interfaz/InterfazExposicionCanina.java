/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazExposicionCanina.java,v 1.10 2010/03/29 20:47:53 lr.ruiz114 Exp $ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_exposicionCanina 
 * Autor: Mario S�nchez - 26/08/2005
 * Modificado por: Daniel Romero- 30/06/2006 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.exposicionCanina.interfaz;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import uniandes.cupi2.exposicionCanina.mundo.*;

/**
 * Es la clase principal de la interfaz
 */
public class InterfazExposicionCanina extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * La ruta del archivo con la informaci�n de los perros
     */
    public static final String ARCHIVO_PERROS = "./data/perros.txt";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la exposici�n de perros
     */
    private ExposicionPerros exposicion;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se muestra la lista de perros
     */
    private PanelListaPerros panelLista;

    /**
     * Es el panel donde se muestran los datos de un perro
     */
    private PanelDatosPerro panelDatos;

    /**
     * Es el panel donde se introducen los datos para agregar un perro
     */
    private PanelAgregarPerro panelAgregar;

    /**
     * Es el panel donde est�n los botones para los puntos de extensi�n
     */
    private PanelExtension panelExtension;

    /**
     * Es el panel donde est�n los botones para la realizaci�n de ordenamientos y b�squeda
     */
    private PanelBusquedaOrdenamientos panelBusquedaOrdenamientos;

    /**
     * Es el panel donde est�n las opciones para la realizaci�n de consultas
     */
    private PanelConsultas panelConsultas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la interfaz e inicializa todos sus componentes.
     * @param archivoPerros es el nombre del archivo de propiedades que contiene la informaci�n de los perros
     */
    public InterfazExposicionCanina( String archivoPerros )
    {
        exposicion = new ExposicionPerros( );
        cargarPerros( archivoPerros );

        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle( "Exposici�n Canina" );
        setSize( new Dimension( 700, 550 ) );
        setResizable( false );

        setLayout( new GridBagLayout( ) );

        JPanel panelSuperior = new JPanel( );
        panelSuperior.setLayout( new GridBagLayout( ) );

        panelLista = new PanelListaPerros( this );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 175;
        panelSuperior.add( panelLista, gbc );

        panelDatos = new PanelDatosPerro( );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 45;
        gbc.ipady = 40;
        panelSuperior.add( panelDatos, gbc );

        panelBusquedaOrdenamientos = new PanelBusquedaOrdenamientos( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.ipadx = 10;
        gbc.ipady = 100;
        panelSuperior.add( panelBusquedaOrdenamientos, gbc );

        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        getContentPane( ).add( panelSuperior, gbc );

        JPanel panelCentral = new JPanel( );
        panelCentral.setLayout( new GridBagLayout( ) );

        panelAgregar = new PanelAgregarPerro( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 35;
        gbc.ipadx = 20;
        panelCentral.add( panelAgregar, gbc );

        panelConsultas = new PanelConsultas( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 85;
        panelCentral.add( panelConsultas, gbc );

        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        getContentPane( ).add( panelCentral, gbc );

        panelExtension = new PanelExtension( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        getContentPane( ).add( panelExtension, gbc );
        actualizarLista( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza la lista de perros mostrada.
     */
    private void actualizarLista( )
    {
        panelLista.refrescarLista( exposicion.darPerros( ) );
    }

    /**
     * Ordena los perros por raza y actualiza la lista.
     */
    public void ordenarPorRaza( )
    {
        exposicion.ordenarPorRaza( );
        panelDatos.limpiarDatos( );
        actualizarLista( );
    }

    /**
     * Ordena los perros por altura y actualiza la lista.
     */
    public void ordenarPorPuntos( )
    {
        exposicion.ordenarPorPuntos( );
        panelDatos.limpiarDatos( );
        actualizarLista( );
    }

    /**
     * Ordena los perros por edad y actualiza la lista.
     */
    public void ordenarPorEdad( )
    {
        exposicion.ordenarPorEdad( );
        panelDatos.limpiarDatos( );
        actualizarLista( );
    }

    /**
     * Busca un perro usando el nombre y cuando lo encuentra lo selecciona en la lista y muestra sus datos.
     */
    public void buscar( )
    {
        String nombreBuscado = JOptionPane.showInputDialog( this, "Nombre del perro" );
        if( nombreBuscado != null )
        {
            int posicion = exposicion.buscarPerro( nombreBuscado );

            if( posicion != -1 )
            {
                actualizarLista( );
                panelLista.seleccionar( posicion );
                Perro p = ( Perro )exposicion.darPerros( ).get( posicion );
                verDatos( p );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "No se encontr� el perro" );
            }
        }
    }

    /**
     * Muestra los datos de un perro en el panel correspondiente.
     * @param perro es el perro del que se quieren ver los datos - perro != null
     */
    public void verDatos( Perro perro )
    {
        panelDatos.mostrarDatos( perro );
    }

    /**
     * Agrega un nuevo perro.
     * @param nombreP es el nombre del perro - nombreP != null
     * @param razaP es la raza del perro - razaP != null
     * @param imagenP La ruta a la imagen del perro - imagenP != null
     * @param alturaP La altura del perro - alturaP > 0
     * @param edadP La edad en meses del perro - edadP > 0
     */
    public void agregarPerro( String nombreP, String razaP, String imagenP, int alturaP, int edadP )
    {

        boolean agrego = exposicion.agregarPerro( nombreP, razaP, imagenP, alturaP, edadP );
        if( !agrego )
            JOptionPane.showMessageDialog( this, "No se pudo agregar el perro dado que el nombre " + nombreP + " ya lo tiene otro perro", "Error", JOptionPane.ERROR_MESSAGE );
        else
        {
            actualizarLista( );
            panelLista.seleccionar( exposicion.darPerros( ).size( ) - 1 );
        }

    }

    /**
     * Carga los perros iniciales de la exposici�n a partir de un archivo de propiedades.
     * @param archivo nombre del archivo de propiedades que contiene la informaci�n de los perros - archivo!=null
     */
    private void cargarPerros( String archivo )
    {

        try
        {
            FileInputStream fis = new FileInputStream( new File( archivo ) );
            Properties propiedades = new Properties( );
            propiedades.load( fis );

            // Cargar los perros
            String dato;
            String nombre;
            String raza;
            String imagen;
            int puntos;
            int edad;
            String aux;
            dato = "total.perros";
            aux = propiedades.getProperty( dato );
            int cantidad = Integer.parseInt( aux );

            for( int cont = 1; cont <= cantidad; cont++ )
            {
                // Carga un perro
                dato = "perro" + cont + ".nombre";
                nombre = propiedades.getProperty( dato );

                dato = "perro" + cont + ".raza";
                raza = propiedades.getProperty( dato );

                dato = "perro" + cont + ".raza";
                raza = propiedades.getProperty( dato );

                dato = "perro" + cont + ".imagen";
                imagen = propiedades.getProperty( dato );

                dato = "perro" + cont + ".puntos";
                aux = propiedades.getProperty( dato );
                puntos = Integer.parseInt( aux );

                dato = "perro" + cont + ".edad";
                aux = propiedades.getProperty( dato );
                edad = Integer.parseInt( aux );

                // S�lo se carga el perro si los datos son correctos
                if( nombre != null && raza != null && imagen != null && puntos >= 0 && edad > 0 )
                    exposicion.agregarPerro( nombre, raza, imagen, puntos, edad );
                fis.close( );
            }
        }
        catch( FileNotFoundException e )
        {
            e.printStackTrace( );
        }
        catch( IOException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Busca el perro ganador y muestra sus datos en el panel de datos.
     * 
     */
    public void buscarGanador( )
    {
        int posicion = exposicion.buscarPerroMayorPuntaje( );

        actualizarLista( );
        if( posicion != -1 )
        {
            panelLista.seleccionar( posicion );
            Perro p = ( Perro )exposicion.darPerros( ).get( posicion );
            verDatos( p );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "No hay perros registrados en la exposici�n" );
        }
    }

    /**
     * Busca el perro con menor puntaje en la exposici�n y muestra sus datos en el panel de datos
     * 
     */
    public void buscarMenorPuntaje( )
    {
        int posicion = exposicion.buscarPerroMenorPuntaje( );

        actualizarLista( );
        if( posicion != -1 )
        {
            panelLista.seleccionar( posicion );
            Perro p = ( Perro )exposicion.darPerros( ).get( posicion );
            verDatos( p );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "No hay perros registrados en la exposici�n" );
        }
    }

    /**
     * Busca el perro de mayor edad y muestra sus datos en el panel de datos
     * 
     */
    public void buscarMayorEdad( )
    {
        int posicion = exposicion.buscarPerroMayorEdad( );

        actualizarLista( );
        if( posicion != -1 )
        {
            panelLista.seleccionar( posicion );
            Perro p = ( Perro )exposicion.darPerros( ).get( posicion );
            verDatos( p );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "No hay perros registrados en la exposici�n" );
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * Muestra las estadísticas generales de la exposición en un nuevo diálogo.
     * Este método reemplaza la funcionalidad original de reqFuncOpcion1().
     * Para que funcione, el metodo1() en ExposicionPerros debe retornar un Map<String, String>.
     */
    public void reqFuncOpcion1( )
    {

        // 1. Obtener las estadísticas del mundo
        Map<String, String> estadisticas = exposicion.metodo1(); // Llama al método que ahora devuelve el Map

        // 2. Crear y configurar el JDialog
        JDialog dialogoEstadisticas = new JDialog(this, "Estadísticas Clave de la Exposición", true); // 'true' para hacerlo modal
        dialogoEstadisticas.setLayout(new BorderLayout());
        dialogoEstadisticas.setSize(400, 220); // Tamaño ajustado para 4 estadísticas

        // Panel para las estadísticas (usará GridLayout para organizar etiquetas)
        JPanel panelStats = new JPanel();
        // GridLayout: filas = número de estadísticas, 2 columnas, 5px de espacio horizontal y vertical
        panelStats.setLayout(new GridLayout(estadisticas.size(), 2, 5, 5));
        panelStats.setBorder(new EmptyBorder(10, 10, 10, 10)); // Margen interior

        // Iterar sobre el mapa y añadir JLabels al panel
        for (Map.Entry<String, String> entry : estadisticas.entrySet()) {
            JLabel lblNombre = new JLabel(entry.getKey() + ":");
            lblNombre.setFont(lblNombre.getFont().deriveFont(Font.BOLD, 14f));
            panelStats.add(lblNombre);

            JLabel lblValor = new JLabel(entry.getValue());
            lblValor.setFont(lblValor.getFont().deriveFont(Font.PLAIN, 14f));
            panelStats.add(lblValor);
        }
        
        dialogoEstadisticas.add(panelStats, BorderLayout.CENTER);

        // Botón de Cerrar
        JPanel panelBotonCerrar = new JPanel();
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dialogoEstadisticas.dispose()); // Cierra el diálogo al hacer clic
        panelBotonCerrar.add(btnCerrar);
        
        dialogoEstadisticas.add(panelBotonCerrar, BorderLayout.SOUTH);

        // Centrar el diálogo en la ventana principal
        dialogoEstadisticas.setLocationRelativeTo(this);
        // Hacer visible el diálogo
        dialogoEstadisticas.setVisible(true);
    }

    

    /**
     * Ejecuta el punto de extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String respuesta = exposicion.metodo2( );
        JOptionPane.showMessageDialog( this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicaci�n
     * @param args son los par�metros de ejecuci�n de la aplicaci�n. No deben usarse
     */
    public static void main( String[] args )
    {
        InterfazExposicionCanina iec = new InterfazExposicionCanina( ARCHIVO_PERROS );
        iec.setVisible( true );
    }
}