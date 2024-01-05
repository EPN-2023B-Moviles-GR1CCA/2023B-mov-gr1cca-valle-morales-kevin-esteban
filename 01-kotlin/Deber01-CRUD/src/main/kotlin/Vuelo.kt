import java.util.*

data class Vuelo(
    val numeroVuelo: String,
    var vueloRetrasado: Boolean,
    var cantidadPasajeros: Int,
    var precio: Double,
    val fechaSalida: Date
)