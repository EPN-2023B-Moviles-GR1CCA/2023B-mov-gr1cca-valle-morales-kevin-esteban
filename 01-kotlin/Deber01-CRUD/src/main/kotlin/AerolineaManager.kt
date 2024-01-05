import java.io.File
import java.text.SimpleDateFormat

class AerolineaManager(private val aerolineasFile: File) {
    private val aerolineas = mutableListOf<Aerolinea>()

    init {
        cargarAerolineasDesdeArchivo()
    }

    private fun cargarAerolineasDesdeArchivo() {
        aerolineasFile.forEachLine { line ->
            val datos = line.split(";")
            val nombre = datos[0]
            val esInternacional = datos[1].toBoolean()
            val cantidadVuelosDia = datos[2].toInt()
            val ingresosAnuales = datos[3].toDouble()
            val fundacion = SimpleDateFormat("yyyy-MM-dd").parse(datos[4])

            val aerolinea = Aerolinea(nombre, esInternacional, cantidadVuelosDia, ingresosAnuales, fundacion)
            aerolineas.add(aerolinea)
        }
    }

    private fun guardarAerolineasEnArchivo() {
        aerolineasFile.bufferedWriter().use { writer ->
            aerolineas.forEach { aerolinea ->
                val formattedDate = SimpleDateFormat("yyyy-MM-dd").format(aerolinea.fundacion)
                writer.write("${aerolinea.nombre};${aerolinea.esInternacional};${aerolinea.cantidadVuelosDia};${aerolinea.ingresosAnuales};$formattedDate\n")
            }
        }
    }

    fun agregarAerolinea(aerolinea: Aerolinea) {
        aerolineas.add(aerolinea)
        guardarAerolineasEnArchivo()
    }

    fun buscarAerolineaPorNombre(nombre: String): Aerolinea? {
        return aerolineas.find { it.nombre == nombre }
    }

    fun actualizarIngresosAnuales(nombre: String, nuevosIngresos: Double) {
        val aerolinea = buscarAerolineaPorNombre(nombre)
        aerolinea?.let {
            it.ingresosAnuales = nuevosIngresos
            guardarAerolineasEnArchivo()
        }
    }

    fun eliminarAerolinea(nombre: String) {
        val aerolinea = buscarAerolineaPorNombre(nombre)
        aerolinea?.let {
            aerolineas.remove(it)
            guardarAerolineasEnArchivo()
        }
    }
    fun agregarVueloAAerolinea(nombreAerolinea: String, vuelo: Vuelo) {
        val aerolinea = buscarAerolineaPorNombre(nombreAerolinea)
        aerolinea?.let {
            it.vuelos.add(vuelo)
            guardarAerolineasEnArchivo()
        }
    }
    fun eliminarVueloDeAerolineas(numeroVuelo: String) {
        val aerolineaConVuelo = aerolineas.find { aerolinea -> aerolinea.vuelos.any { it.numeroVuelo == numeroVuelo } }
        aerolineaConVuelo?.let {
            it.vuelos.removeAll { vuelo -> vuelo.numeroVuelo == numeroVuelo }
            guardarAerolineasEnArchivo()
            println("Vuelo $numeroVuelo eliminado de todas las aerolíneas.")
        } ?: println("El vuelo $numeroVuelo no está asociado a ninguna aerolínea.")
    }


}