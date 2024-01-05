import java.io.File
import java.text.SimpleDateFormat

class VueloManager(private val vuelosFile: File) {
    private val vuelos = mutableListOf<Vuelo>()

    init {
        cargarVuelosDesdeArchivo()
    }

    private fun cargarVuelosDesdeArchivo() {
        vuelosFile.forEachLine { line ->
            val datos = line.split(";")
            val numeroVuelo = datos[0]
            val vueloRetrasado = datos[1].toBoolean()
            val cantidadPasajeros = datos[2].toInt()
            val precio = datos[3].toDouble()
            val fechaSalida = SimpleDateFormat("yyyy-MM-dd").parse(datos[4])

            val vuelo = Vuelo(numeroVuelo, vueloRetrasado, cantidadPasajeros, precio, fechaSalida)
            vuelos.add(vuelo)
        }
    }

    private fun guardarVuelosEnArchivo() {
        vuelosFile.bufferedWriter().use { writer ->
            vuelos.forEach { vuelo ->
                val formattedDate = SimpleDateFormat("yyyy-MM-dd").format(vuelo.fechaSalida)
                writer.write("${vuelo.numeroVuelo};${vuelo.vueloRetrasado};${vuelo.cantidadPasajeros};${vuelo.precio};$formattedDate\n")
            }
        }
    }

    fun agregarVuelo(vuelo: Vuelo) {
        vuelos.add(vuelo)
        guardarVuelosEnArchivo()
    }

    fun buscarVueloPorNumero(numero: String): Vuelo? {
        return vuelos.find { it.numeroVuelo == numero }
    }

    fun actualizarCantidadPasajeros(numero: String, nuevaCantidad: Int) {
        val vuelo = buscarVueloPorNumero(numero)
        vuelo?.let {
            it.cantidadPasajeros = nuevaCantidad
            guardarVuelosEnArchivo()
        }
    }

    fun eliminarVuelo(numero: String) {
        val vuelo = buscarVueloPorNumero(numero)
        vuelo?.let {
            vuelos.remove(it)
            guardarVuelosEnArchivo()
        }
    }
}