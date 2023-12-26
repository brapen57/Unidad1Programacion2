package com.example.miunidad1proyecto

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.miunidad1proyecto.databinding.ActivityMainBinding
import com.example.miunidad1proyecto.Modelo.CuentaMesa
import com.example.miunidad1proyecto.Modelo.ItemMenu
import com.example.miunidad1proyecto.Modelo.ItemMesa

class MainActivity : AppCompatActivity() {

    private val precioPlato1 = 12000.0
    private val precioPlato2 = 10000.0
    private val porcentajePropina = 0.10

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val platos1EditText: EditText = binding.Platos1
        val platos2EditText: EditText = binding.Platos2
        val switch: Switch = binding.boton1
        val totalMonetarioTextView: TextView = binding.TotalMonetario
        val propinaTextView: TextView = binding.Propina
        val totalConPropinaTextView: TextView = binding.TotalConPropina

        platos1EditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                actualizarTotal()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        platos2EditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                actualizarTotal()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        switch.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            actualizarTotal()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun actualizarTotal() {
        val cantidadPlatos1 = binding.Platos1.text.toString().toDoubleOrNull() ?: 0.0
        val cantidadPlatos2 = binding.Platos2.text.toString().toDoubleOrNull() ?: 0.0

        val itemPlato1 = ItemMesa("Plato1", cantidadPlatos1.toInt())
        val itemPlato2 = ItemMesa("Plato2", cantidadPlatos2.toInt())

        val cuentaMesa = CuentaMesa(listOf(itemPlato1, itemPlato2))

        val totalPlatos = cuentaMesa.calcularTotal(precioPlato1, precioPlato2)
        val propina = cuentaMesa.calcularPropina(totalPlatos, porcentajePropina, binding.boton1.isChecked)
        val totalConPropina = cuentaMesa.calcularTotalConPropina(totalPlatos, propina)

        binding.TotalMonetario.text = "$${String.format("%.2f", totalPlatos)}"
        binding.Propina.text = "$${String.format("%.2f", propina)}"
        binding.TotalConPropina.text = "$${String.format("%.2f", totalConPropina)}"
    }
}