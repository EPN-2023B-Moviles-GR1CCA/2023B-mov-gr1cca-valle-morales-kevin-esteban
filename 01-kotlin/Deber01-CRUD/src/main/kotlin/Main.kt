import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val aerolineasFile = File("aerolineas.txt")
    val vuelosFile = File("vuelos.txt")

    val aerolineaManager = AerolineaManager(aerolineasFile)
    val vueloManager = VueloManager(vuelosFile)

    val scanner = Scanner(System.`in`)

    while (true) {
        println("****RELACION AEROLINEA-VUELO****")
        println("Seleccione una opción:")
        println("1. Agregar Aerolínea")
        println("2. Agregar Vuelo")
        println("3. Actualizar ingresos anuales de Aerolínea")
        println("4. Actualizar cantidad de pasajeros de Vuelo")
        println("5. Eliminar Aerolínea")
        println("6. Eliminar Vuelo")
        println("7. Buscar Aerolinea")
        println("8. Buscar Vuelo")
        println("9. Mostrar Aerolínea con Vuelos")
        println("10. Salir")

        when (scanner.nextInt()) {
            1 -> {
                println("Ingrese el nombre de la aerolínea:")
                val nombre = scanner.next()
                println("¿Es internacional? (true/false):")
                val esInternacional = scanner.nextBoolean()
                println("Ingrese la cantidad de vuelos al día:")
                val cantidadVuelosDia = scanner.nextInt()
                println("Ingrese los ingresos anuales:")
                val ingresosAnuales = scanner.nextDouble()
                println("Ingrese la fecha de fundación (yyyy-MM-dd):")
                val fechaFundacionStr = scanner.next()
                val fechaFundacion = SimpleDateFormat("yyyy-MM-dd").parse(fechaFundacionStr)

                val aerolinea = Aerolinea(nombre, esInternacional, cantidadVuelosDia, ingresosAnuales, fechaFundacion)
                aerolineaManager.agregarAerolinea(aerolinea)
                println("Aerolínea agregada exitosamente.")
                println("\n")
            }
            2 -> {
                println("Ingrese el nombre de la aerolínea:")
                val nombreAerolinea = scanner.next()

                val aerolinea = aerolineaManager.buscarAerolineaPorNombre(nombreAerolinea)
                if (aerolinea != null) {
                    println("Aerolínea encontrada. Ingrese los datos del vuelo:")
                    println("Número de vuelo:")
                    val numeroVuelo = scanner.next()
                    println("¿El vuelo está retrasado? (true/false):")
                    val vueloRetrasado = scanner.nextBoolean()
                    println("Cantidad de pasajeros:")
                    val cantidadPasajeros = scanner.nextInt()
                    println("Precio:")
                    val precio = scanner.nextDouble()
                    println("Fecha de salida (yyyy-MM-dd):")
                    val fechaSalidaStr = scanner.next()
                    val fechaSalida = SimpleDateFormat("yyyy-MM-dd").parse(fechaSalidaStr)

                    val vuelo = Vuelo(numeroVuelo, vueloRetrasado, cantidadPasajeros, precio, fechaSalida)
                    aerolineaManager.agregarVueloAAerolinea(nombreAerolinea, vuelo)
                    vueloManager.agregarVuelo(vuelo)
                    println("Vuelo agregado a la aerolínea exitosamente.")
                    println("\n")
                } else {
                    println("Aerolínea no encontrada.")
                    println("\n")
                }
            }
            3 -> {
                println("Ingrese el nombre de la aerolínea para actualizar los ingresos anuales:")
                val nombreAerolinea = scanner.next()
                val aerolineaExistente = aerolineaManager.buscarAerolineaPorNombre(nombreAerolinea)
                if (aerolineaExistente != null) {
                    println("Ingrese los nuevos ingresos anuales:")
                    val nuevosIngresos = scanner.nextDouble()

                    aerolineaManager.actualizarIngresosAnuales(nombreAerolinea, nuevosIngresos)
                    println("Ingresos anuales actualizados.")
                } else {
                    println("La aerolínea '$nombreAerolinea' no existe.")
                }
                println("\n")
            }
            4 -> {
                println("Ingrese el número de vuelo para actualizar la cantidad de pasajeros:")
                val numeroVuelo = scanner.next()
                val vueloExistente = vueloManager.buscarVueloPorNumero(numeroVuelo)
                if (vueloExistente != null) {
                    println("Ingrese la nueva cantidad de pasajeros:")
                    val nuevaCantidad = scanner.nextInt()

                    vueloManager.actualizarCantidadPasajeros(numeroVuelo, nuevaCantidad)
                    println("Cantidad de pasajeros actualizada.")
                } else {
                    println("El vuelo '$numeroVuelo' no existe.")
                }
                println("\n")
            }
            5 -> {
                println("Ingrese el nombre de la aerolínea a eliminar:")
                val nombreEliminar = scanner.next()

                aerolineaManager.eliminarAerolinea(nombreEliminar)
                println("Aerolínea eliminada.")
                println("\n")
            }
            6 -> {
                println("Ingrese el número de vuelo a eliminar:")
                val numeroEliminar = scanner.next()

                vueloManager.eliminarVuelo(numeroEliminar)
                aerolineaManager.eliminarVueloDeAerolineas(numeroEliminar)
                println("Vuelo eliminado.")
                println("\n")
            }
            7 -> {
                println("Ingrese el nombre de la aerolínea a buscar:")
                val nombreBuscar = scanner.next()

                val aerolineaEncontrada = aerolineaManager.buscarAerolineaPorNombre(nombreBuscar)
                if (aerolineaEncontrada != null) {
                    println("Aerolínea encontrada:")
                    println("Nombre: ${aerolineaEncontrada.nombre}")
                    println("Es Internacional: ${aerolineaEncontrada.esInternacional}")
                    println("Cantidad de vuelos al día: ${aerolineaEncontrada.cantidadVuelosDia}")
                    println("Ingresos anuales: ${aerolineaEncontrada.ingresosAnuales}")
                    println("Fecha de fundación: ${SimpleDateFormat("yyyy-MM-dd").format(aerolineaEncontrada.fundacion)}")
                    println("\n")
                } else {
                    println("Aerolínea no encontrada.")
                    println("\n")
                }
            }
            8 -> {
                println("Ingrese el número de vuelo a buscar:")
                val numeroBuscar = scanner.next()

                val vueloEncontrado = vueloManager.buscarVueloPorNumero(numeroBuscar)
                if (vueloEncontrado != null) {
                    println("Vuelo encontrado:")
                    println("Número de vuelo: ${vueloEncontrado.numeroVuelo}")
                    println("¿Está retrasado?: ${vueloEncontrado.vueloRetrasado}")
                    println("Cantidad de pasajeros: ${vueloEncontrado.cantidadPasajeros}")
                    println("Precio: ${vueloEncontrado.precio}")
                    println("Fecha de salida: ${SimpleDateFormat("yyyy-MM-dd").format(vueloEncontrado.fechaSalida)}")
                    println("\n")
                } else {
                    println("Vuelo no encontrado.")
                    println("\n")
                }
            }
            9 -> {
                println("Ingrese el nombre de la aerolínea para ver sus vuelos:")
                val nombreAerolinea = scanner.next()

                val aerolinea = aerolineaManager.buscarAerolineaPorNombre(nombreAerolinea)
                if (aerolinea != null) {
                    println("Aerolínea: ${aerolinea.nombre}")
                    println("Vuelos:")

                    if (aerolinea.vuelos.isNotEmpty()) {
                        aerolinea.vuelos.forEachIndexed { index, vuelo ->
                            println("Vuelo ${index + 1}:")
                            println("Número de vuelo: ${vuelo.numeroVuelo}")
                            println("¿Está retrasado?: ${vuelo.vueloRetrasado}")
                            println("Cantidad de pasajeros: ${vuelo.cantidadPasajeros}")
                            println("Precio: ${vuelo.precio}")
                            println("Fecha de salida: ${SimpleDateFormat("yyyy-MM-dd").format(vuelo.fechaSalida)}")
                            println("\n")
                        }
                    } else {
                        println("No hay vuelos asignados a esta aerolínea.")
                        println("\n")
                    }
                } else {
                    println("Aerolínea no encontrada.")
                    println("\n")
                }
            }
            10 -> {
                println("Saliendo del programa...")
                return
            }
            else -> {
                println("Opción inválida. Por favor, seleccione una opción válida.")
                println("\n")
            }
        }
    }
}


