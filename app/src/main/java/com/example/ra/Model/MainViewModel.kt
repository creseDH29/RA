package com.example.ra.Model

import androidx.compose.material.Text
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    //variabla para guardar el nombre del cliente
    private val _nomCliente = mutableStateOf("")
    public val nobreCliente: State<String> = _nomCliente

    //variabla para guardar marca del auto
    private val _marcaAuto = mutableStateOf("")
    public val marcaAuto: State<String> = _marcaAuto

    //variabla para gurdar el precioUInicial del auto
    private val _precioInicial = mutableStateOf(0.0)
    public val precioInicial: State<Double> = _precioInicial

    // variable para agregar ssigno %
    private val _signoP = mutableStateOf("")
    val signoP: State<String> = _signoP

    // variable poara guardar el enganche en pesos
    private val _enganche = mutableStateOf(0.0)
    val Enganche: State<Double> = _enganche

    //variable para gurdar el financiamiento
    private val _financiamiento = mutableStateOf(0.0)
    val Financiamiento: State<Double> = _financiamiento

    //variable para mostrar el text financiamiento en pantalla
    private val _financiText = mutableStateOf("")
    val FinanciText: State<String> = _financiText

    //Variable para mostrar el año en texto en pantalla
    private val _año = mutableStateOf("")
    val añoString: State<String> = _año

    //variable para calcular la tasa de interes que se seleccione por el cliente
    private val _tasain = mutableStateOf(0.0)
    val Tasaint: State<Double> = _tasain

    //ariable paea gusrdar el año que se selecciones por el cliente
    private val _getaño = mutableStateOf(0)
    val año: State<Int> = _getaño

    //variable para calcular monto de interes por años
    private val _montoporaños = mutableStateOf(0.0)
    val MontoPorAños: State<Double> = _montoporaños

    //------------------------------------------------------
    //signos
    val SignoPeso = "$"
    private val _signomas = mutableStateOf("")
    val SignoMas: State<String> = _signomas
    private val _sigigual = mutableStateOf("")
    val SigIgual: State<String> = _sigigual

    //----------------------------------------------------
    //variable para calcular monto a financiar + intereses
    private val _montofinaint = mutableStateOf(0.0)
    val MontoFinaInt: State<Double> = _montofinaint

    // variable para calcular cuanto pagara mensual
    private val _pagomensual = mutableStateOf(0.0)
    val PagoMensual: State<Double> = _pagomensual

    // variable para calcular pago tolal
    private val _pagototal = mutableStateOf(0.0)
    val PagoTotal: State<Double> = _pagototal

    // funcion paracalcular noCliente
    fun CalcularNomCliente(clien: String) {
        _nomCliente.value = clien
    }

    //funcio para calcular marcaAuto
    fun CalcularMarcaAuto(marcaaut: String) {
        _marcaAuto.value = marcaaut
    }

    // funcion para guardar precio inicial del auto Y enganche del auto
    fun preciNor(precioinicialauto: String) {
        val precioinicialauto = precioinicialauto.toDouble()
        _precioInicial.value = precioinicialauto.toDouble()

    }

    fun CalcularEnganche(porceEnga: String) {
        val porcen1 = porceEnga
        val getporc = porcen1.toDouble()
        var value = 100
        val porce2 = _precioInicial.value / value
        val valo = porce2 * getporc
        _enganche.value = valo
    }

    fun agragarsigporce(signo: String) {
        _signoP.value = signo

    }

    //variable para calcular financiamiento  prioridad
    fun CalcularFinanciamiento(anoPorc: String, año: String) {
        _financiText.value = anoPorc
        _año.value = año
        //calculamos precio inicial del auto
        _financiamiento.value = precioInicial.value - _enganche.value
    }
    val text = " años, tasa de"
    val text2 = "%"
    //funcion para recibir año signomas signo igual
    fun GetAño(year: String, tasain: String, signoma: String, signoigual: String) {
        val yera1 = year.toInt()
        _getaño.value = yera1
        _tasain.value = tasain.toDouble()
        _signomas.value = signoma
        _sigigual.value = signoigual
        val valueunoporc = Financiamiento.value / 100
        val R = (valueunoporc * _tasain.value) * year.toDouble()
        _montoporaños.value = R
        _montofinaint.value = _financiamiento.value + _montoporaños.value

        if (_getaño.value == 1) {
            _pagomensual.value = _montofinaint.value / 12

        } else if (_getaño.value == 2) {
            _pagomensual.value = _montofinaint.value / 24
        } else if (_getaño.value == 3) {
            _pagomensual.value = _montofinaint.value / 36
        } else if (_getaño.value == 4) {
            _pagomensual.value = _montofinaint.value / 48
        } else if (_getaño.value == 5) {
            _pagomensual.value = _montofinaint.value / 60
        }
        _pagototal.value = _enganche.value + _montofinaint.value
    }
    fun Reset() {
        _marcaAuto.value = ""
        _enganche.value = 0.0
        _financiamiento.value = 0.0
        _año.value = ""
        _tasain.value = 0.0
        _montofinaint.value = 0.0
        _pagomensual.value = 0.0
        _pagototal.value = 0.0
        _signoP.value = ""
        _getaño.value = 0
        _montoporaños.value = 0.0
    }

}