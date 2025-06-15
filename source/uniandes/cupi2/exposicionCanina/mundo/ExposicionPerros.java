/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ExposicionPerros.java,v 1.5 2006/08/04 15:12:57 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_exposicionCanina
 * Autor: Mario Sánchez - 26/08/2005
 * Modificado por: Daniel Romero- 30/06/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.exposicionCanina.mundo;

import java.util.*; // Asegúrate de que esto está presente para Map, HashMap, ArrayList, Set, LinkedHashMap, HashSet

/**
 * Es la clase que se encarga de manejar, organizar, cargar y salvar los perros. <br>
 * <b>inv: </b> <br>
 * perros != null <br>
 * no hay dos perros con el mismo nombre
 */
public class ExposicionPerros
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el vector que contiene todos los perros
     */
    private ArrayList<Perro> perros; // Usar genéricos para ArrayList<Perro>

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo manejador de perros vacío.
     */
    public ExposicionPerros( )
    {
        perros = new ArrayList<Perro>( ); // Usar genéricos
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna una lista de perros. La lista que se retorna no es la misma que la almacenada en esta clase, pero si tiene el mismo orden.
     * @return Lista de perros
     */
    public ArrayList<Perro> darPerros( ) // Usar genéricos
    {
        ArrayList<Perro> copiaLista = new ArrayList<Perro>( perros ); // Usar genéricos
        return copiaLista;
    }

    /**
     * Organiza la lista de perros por raza usando el algoritmo de burbuja. <br>
     * <b>post: </b>La lista de perros está ordenada por raza (orden ascendente).
     */
    public void ordenarPorRaza( )
    {
        for( int i = perros.size( ); i > 0; i-- )
        {
            for( int j = 0; j < i - 1; j++ )
            {
                Perro p1 = perros.get( j ); // Sin cast si usas genéricos
                Perro p2 = perros.get( j + 1 ); // Sin cast si usas genéricos

                // Si es necesario se deben intercambiar p1 y p2
                if( p1.compararPorRaza( p2 ) > 0 )
                {
                    perros.set( j, p2 );
                    perros.set( j + 1, p1 );
                }
            }
        }
        verificarInvariante( );
    }

    /**
     * Organiza la lista de perros por nombre usando el algoritmo de burbuja. <br>
     * <b>post: </b>La lista de perros está ordenada por nombre (orden ascendente).
     */
    public void ordenarPorNombre( )
    {
        for( int i = perros.size( ); i > 0; i-- )
        {
            for( int j = 0; j < i - 1; j++ )
            {
                Perro p1 = perros.get( j ); // Sin cast si usas genéricos
                Perro p2 = perros.get( j + 1 ); // Sin cast si usas genéricos

                // Si es necesario se deben intercambiar p1 y p2
                if( p1.compararPorNombre( p2 ) > 0 )
                {
                    perros.set( j, p2 );
                    perros.set( j + 1, p1 );
                }
            }
        }
        verificarInvariante( );
    }
    /**
     * Organiza la lista de perros por puntos usando el algoritmo de inserción. <br>
     * <b>post: </b>La lista de perros está ordenada por puntos (orden ascendente).
     */
    public void ordenarPorPuntos( )
    {
        for( int i = 1; i < perros.size( ); i++ )
        {
            Perro porInsertar = perros.get( i ); // Sin cast si usas genéricos
            boolean termino = false;
            for( int j = i; j > 0 && !termino; j-- )
            {
                Perro actual = perros.get( j - 1 ); // Sin cast si usas genéricos
                if( actual.compararPorPuntos( porInsertar ) > 0 )
                {
                    perros.set( j, actual );
                    perros.set( j - 1, porInsertar );
                }
                else
                    termino = true;
            }
        }
        verificarInvariante( );
    }

    /**
     * Organiza la lista de perros por edad usando el algoritmo de selección. <br>
     * <b>post: </b>La lista de perros está ordenada por edad (orden ascendente).
     */
    public void ordenarPorEdad( )
    {
        int inicial;

        // En cada iteración se sabe que:
        // 1. Todos los valores antes de perros[inicial] están ordenados por edad
        // 2. No hay ningún valor después de perros[inicial-1] que sea menor que perros[inicial-1]
        // En cada iteración se busca el menor entre perros[inicial] y perros[final] y se ubica en perros[inicial]

        for( inicial = 0; inicial < perros.size( ); inicial++ )
        {
            int posicionMenor = inicial;
            Perro perroMenor = perros.get( inicial ); // Sin cast si usas genéricos

            // Buscar el perro de menor edad entre inicial y final
            for( int i = inicial + 1; i < perros.size( ); i++ )
            {
                Perro perroPosicion = perros.get( i ); // Sin cast si usas genéricos

                // El perro de la posición actual es menor que el menor encontrado hasta el momento
                if( perroPosicion.compararPorEdad( perroMenor ) < 0 )
                {
                    perroMenor = perroPosicion;
                    posicionMenor = i;
                }
            }

            if( posicionMenor != inicial )
            {
                Perro temp = perros.get( inicial ); // Sin cast si usas genéricos
                perros.set( inicial, perroMenor );
                perros.set( posicionMenor, temp );
            }

        }
        verificarInvariante( );
    }

    /**
     * Busca un perro según su nombre y retorna la posición en la que se encuentra.
     * @param nombre es el nombre del perro buscado - nombre!=null
     * @return Retorna la posición donde se encuentra un perro con el nombre dado. Si no se encuentra ningún perro con ese nombre retorna -1
     */
    public int buscarPerro( String nombre )
    {
        int posicion = -1;
        boolean termine = false;

        for( int i = 0; i < perros.size( ) && !termine; i++ )
        {
            Perro perroPosicion = perros.get( i ); // Sin cast si usas genéricos
            String nombrePerro = perroPosicion.darNombre( );

            // Los nombres son iguales
            if( nombrePerro.equalsIgnoreCase( nombre ) )
            {
                posicion = i;
                termine = true;
            }
        }

        return posicion;
    }

    /**
     * Busca un perro utilizando una búsqueda binaria. <br>
     * <b>pre: </b> La lista de perros se encuentra ordenada por nombre.
     * @param nombre es el nombre del perro que se va a buscar - nombre!=null
     * @return La posición del perro con el nombre dado. Si el perro no existe se retorna -1.
     */
    public int buscarBinarioPorNombre( String nombre )
    {
        int posicion = -1;
        int inicio = 0;
        int fin = perros.size( ) - 1;
        Perro aBuscar = new Perro( nombre, "", "", 1, 1 );
        while( inicio <= fin && posicion == -1 )
        {
            int medio = ( inicio + fin ) / 2;
            Perro mitad = perros.get( medio ); // Sin cast si usas genéricos
            if( mitad.compararPorNombre( aBuscar ) == 0 )
            {
                posicion = medio;
            }
            else if( mitad.compararPorNombre( aBuscar ) > 0 )
            {
                fin = medio - 1;
            }
            else
            {
                inicio = medio + 1;
            }
        }
        return posicion;
    }

    /**
     * Agrega un nuevo perro a la exposición. <br>
     * <b>post: </b> El perro fue agregado a la exposición si no existe otro perro con el mismo nombre.
     * @param nombreP es el nombre del perro - nombreP != null
     * @param razaP es la raza del perro - razaP != null
     * @param imagenP es la ruta a la imagen del perro - imagenP != null
     * @param puntosP son Los puntos del perro en la exposición - puntosP >= 0
     * @param edadP es la edad en meses del perro - edadP >= 0
     * @return True si el perro fue adicionado o false de lo contrario
     */
    public boolean agregarPerro( String nombreP, String razaP, String imagenP, int puntosP, int edadP )
    {
        int perroBuscado = buscarPerro( nombreP );
        boolean agregado = false;
        if( perroBuscado == -1 )
        {
            Perro nuevoPerro = new Perro( nombreP, razaP, imagenP, puntosP, edadP );
            perros.add( nuevoPerro );
            agregado = true;
        }

        verificarInvariante( );

        return agregado;
    }

    /**
     * Busca el perro que tenga el mayor puntaje en la exposición.
     * @return Retorna la posición donde se encuentra el perro con el mayor puntaje. Si no hay perros en la exposición se retorna -1
     */
    public int buscarPerroMayorPuntaje( )
    {
        int posicion = -1;

        if( !perros.isEmpty() ) // Usar isEmpty() es más idiomático
        {
            Perro pMayorPuntaje = perros.get( 0 ); // Sin cast si usas genéricos
            posicion = 0;
            for( int i = 1; i < perros.size( ); i++ )
            {
                Perro perroPosicion = perros.get( i ); // Sin cast si usas genéricos

                // Compara el perro actual con el que tiene el mayor puntaje hasta ahora
                // Si el perro en 'perroPosicion' tiene más puntos que 'pMayorPuntaje',
                // entonces 'compararPorPuntos' debería devolver -1.
                if( pMayorPuntaje.compararPorPuntos( perroPosicion ) < 0 )
                {
                    posicion = i;
                    pMayorPuntaje = perroPosicion;
                }
            }
        }

        return posicion;
    }

    /**
     * Busca el perro que tenga el menor puntaje en la exposición.
     * @return Retorna la posición donde se encuentra el perro con el menor puntaje. Si no hay perros en la exposición se retorna -1
     */
    public int buscarPerroMenorPuntaje( )
    {
        int posicion = -1;

        if( !perros.isEmpty() ) // Usar isEmpty()
        {
            Perro pMenorPuntaje = perros.get( 0 ); // Sin cast si usas genéricos
            posicion = 0;
            for( int i = 1; i < perros.size( ); i++ )
            {
                Perro perroPosicion = perros.get( i ); // Sin cast si usas genéricos

                // Compara el perro actual con el que tiene el menor puntaje hasta ahora
                // Si el perro en 'perroPosicion' tiene menos puntos que 'pMenorPuntaje',
                // entonces 'compararPorPuntos' debería devolver 1.
                if( pMenorPuntaje.compararPorPuntos( perroPosicion ) > 0 )
                {
                    posicion = i;
                    pMenorPuntaje = perroPosicion;
                }
            }
        }

        return posicion;
    }

    /**
     * Busca el perro que tenga la mayor edad.
     * @return Retorna la posición donde se encuentra el perro con la mayor edad. Si no hay perros en la exposición se retorna -1
     */
    public int buscarPerroMayorEdad( )
    {
        int posicion = -1;

        if( !perros.isEmpty() ) // Usar isEmpty()
        {
            Perro pMayorEdad = perros.get( 0 ); // Sin cast si usas genéricos
            posicion = 0;
            for( int i = 1; i < perros.size( ); i++ )
            {
                Perro perroPosicion = perros.get( i ); // Sin cast si usas genéricos

                // Compara el perro actual con el que tiene la mayor edad hasta ahora
                // Si el perro en 'perroPosicion' es más viejo que 'pMayorEdad',
                // entonces 'compararPorEdad' debería devolver -1.
                if( pMayorEdad.compararPorEdad( perroPosicion ) < 0 )
                {
                    posicion = i;
                    pMayorEdad = perroPosicion;
                }
            }
        }

        return posicion;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b>inv </b> perros != null y no hay dos perros con el mismo nombre
     */
    private void verificarInvariante( )
    {
        assert ( perros != null ) : "La lista de perros no debe ser null";
        assert ( !buscarPerrosConNombresRepetidos( ) ) : "Hay dos perros con el mismo nombre";
    }

    /**
     * Verifica si hay dos perros con el mismo nombre.
     * @return Retorna true si hay dos perros con el mismo nombre, retorna false en caso contrario
     */
    private boolean buscarPerrosConNombresRepetidos( )
    {
        // Se utiliza un HashSet para detectar duplicados de forma eficiente
        Set<String> nombresVistos = new HashSet<>();
        for( Perro perro : perros ) // Usar for-each loop y genéricos
        {
            if (!nombresVistos.add(perro.darNombre())) {
                // Si add devuelve false, significa que el nombre ya estaba en el conjunto
                return true;
            }
        }
        return false;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión y Métodos Auxiliares para Estadísticas
    // -----------------------------------------------------------------

    /**
     * Retorna el número total de perros en la exposición.
     * @return El número de perros.
     */
    public int getTotalPerros() {
        return perros.size();
    }

    /**
     * Calcula la edad promedio de todos los perros en la exposición.
     * @return La edad promedio en meses, o 0.0 si no hay perros.
     */
    public double getEdadPromedioPerros() {
        if (perros.isEmpty()) {
            return 0.0;
        }
        double sumaEdades = 0;
        for (Perro perro : perros) { // Usar for-each loop y genéricos
            sumaEdades += perro.darEdad();
        }
        return sumaEdades / perros.size();
    }

    /**
     * Calcula el puntaje promedio de todos los perros en la exposición.
     * @return El puntaje promedio, o 0.0 si no hay perros.
     */
    public double getPuntajePromedioPerros() {
        if (perros.isEmpty()) {
            return 0.0;
        }
        double sumaPuntos = 0;
        for (Perro perro : perros) { // Usar for-each loop y genéricos
            sumaPuntos += perro.darPuntos();
        }
        return sumaPuntos / perros.size();
    }

    /**
     * Encuentra la raza de perro más común en la exposición.
     * @return La raza que más se repite, o "N/A" si no hay perros.
     */
    public String getRazaMasComun() {
        if (perros.isEmpty()) {
            return "N/A";
        }

        Map<String, Integer> conteoRazas = new HashMap<>();
        for (Perro perro : perros) { // Usar for-each loop y genéricos
            String raza = perro.darRaza().toLowerCase(); // Normalizar
            conteoRazas.put(raza, conteoRazas.getOrDefault(raza, 0) + 1);
        }

        String razaMasComun = "N/A";
        int maxConteo = 0;

        for (Map.Entry<String, Integer> entry : conteoRazas.entrySet()) {
            if (entry.getValue() > maxConteo) {
                maxConteo = entry.getValue();
                razaMasComun = entry.getKey();
            }
            // Si hay un empate en el conteo, se mantiene el primero encontrado.
            // Para un manejo más sofisticado de empates, se necesitaría otra lógica.
        }
        // Capitalizar la primera letra para una mejor presentación
        if (!razaMasComun.equals("N/A") && razaMasComun.length() > 0) {
             return razaMasComun.substring(0, 1).toUpperCase() + razaMasComun.substring(1);
        }
        return razaMasComun;
    }

    /**
     * Ejecuta el punto de extensión 1: Calcula y retorna las estadísticas clave de la exposición.
     * @return Un mapa con los nombres de las estadísticas y sus valores.
     */
    public Map<String, String> metodo1()
    {
        Map<String, String> estadisticas = new LinkedHashMap<>(); // Mantiene el orden de inserción

        // Manejo del caso sin perros para evitar errores y mostrar "N/A"
        if (perros.isEmpty()) {
            estadisticas.put("Total de Perros", "0");
            estadisticas.put("Edad Promedio", "N/A");
            estadisticas.put("Puntaje Promedio", "N/A");
            estadisticas.put("Raza Más Común", "N/A");
            return estadisticas;
        }

        estadisticas.put("Total de Perros", String.valueOf(getTotalPerros()));
        estadisticas.put("Edad Promedio", String.format("%.2f meses", getEdadPromedioPerros()));
        estadisticas.put("Puntaje Promedio", String.format("%.2f puntos", getPuntajePromedioPerros()));
        estadisticas.put("Raza Más Común", getRazaMasComun());

        return estadisticas;
    }

    /**
     * Busca y retorna el perro con la menor edad en la exposición.
     * Si no hay perros, retorna null.
     * @return El perro más joven o null si la lista está vacía.
     */
    public Perro darPerroMasJoven() {
        if (perros == null || perros.isEmpty()) {
            return null;
        }

        Perro perroMasJoven = perros.get(0); // Asumimos que el primero es el más joven inicialmente
        for (int i = 1; i < perros.size(); i++) {
            Perro actual = perros.get(i);
            // Utilizamos el método compararPorEdad de la clase Perro.
            // Si 'actual' es más joven que 'perroMasJoven', compararPorEdad debería retornar -1.
            if (actual.compararPorEdad(perroMasJoven) == -1) {
                perroMasJoven = actual; // El perro actual es el nuevo más joven
            }
        }
        return perroMasJoven;
    }

    /**
     * Ejecuta el punto de extensión 2: Busca el perro más joven.
     * Este método es el que se conecta con el botón "Opción 2" de la interfaz.
     * @return Una cadena con la información detallada del perro más joven, o un mensaje si no hay perros.
     */
    public String metodo2() {
        Perro masJoven = darPerroMasJoven(); // Llama al método que encuentra al perro más joven
        if (masJoven != null) {
            // Formatea la información del perro más joven para mostrarla al usuario
            return "El perro más joven es:\n" +
                   "Nombre: " + masJoven.darNombre() + "\n" +
                   "Raza: " + masJoven.darRaza() + "\n" +
                   "Edad: " + masJoven.darEdad() + " meses\n" +
                   "Puntos: " + masJoven.darPuntos();
        } else {
            // Mensaje si no hay perros registrados
            return "No hay perros registrados para encontrar el más joven.";
        }
    }
}