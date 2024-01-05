import java.util.*

data class Aerolinea(
    val nombre: String,
    val esInternacional: Boolean,
    var cantidadVuelosDia: Int,
    var ingresosAnuales: Double,
    val fundacion: Date,
    val vuelos: MutableList<Vuelo> = mutableListOf()
)