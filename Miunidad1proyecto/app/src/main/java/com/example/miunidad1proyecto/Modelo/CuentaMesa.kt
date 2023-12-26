package com.example.miunidad1proyecto.Modelo

class CuentaMesa(val items: List<ItemMesa>) {

    fun calcularTotal(precioPlato1: Double, precioPlato2: Double): Double {
        var total = 0.0
        for (item in items) {
            when (item.nombre) {
                "Plato1" -> total += item.cantidad * precioPlato1
                "Plato2" -> total += item.cantidad * precioPlato2

            }
        }
        return total
    }

    fun calcularPropina(total: Double, porcentajePropina: Double, aplicarPropina: Boolean): Double {
        return if (aplicarPropina) total * porcentajePropina else 0.0
    }

    fun calcularTotalConPropina(total: Double, propina: Double): Double {
        return total + propina
    }
}