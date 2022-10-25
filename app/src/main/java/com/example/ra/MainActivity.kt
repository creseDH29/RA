package com.example.ra

import android.R
import android.os.Bundle
import android.util.ArrayMap
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.ra.Model.MainViewModel
import java.time.format.TextStyle

class MainActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val objeto_wiew_Model: MainViewModel by viewModels()

        setContent {
            Column(modifier = Modifier.fillMaxHeight(), horizontalAlignment =Alignment.CenterHorizontally){
                //Ponemos el titulo
                Box(modifier = Modifier
                    .background(Color.Magenta)
                    .size(width = 400.dp, height = 50.dp), contentAlignment = Alignment.Center) {
                    Text(text = "Cotizador de Autos Nuevos",color = Color(59, 56, 56), fontWeight = FontWeight(20))
                }
                //ponemos las imagenes
               // Image(
                   // painterResource(id = com.example.ra.R.drawable.logo6),
                    //contentDescription = "Img de prueba",
                    //contentScale = ContentScale.Crop,
                    //modifier = Modifier
                        //.padding(0.dp)
                        //.size(100.dp)
                //)
                Column() {

                        //ponemos un Texfiel para Estatura
                        Text("Nombre " , color = Color(205, 92, 92), textAlign = TextAlign.Left)
                        var text=remember{  mutableStateOf("")}
                        TextField(value =text.value ,
                            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            onValueChange ={
                                text.value=it
                            }
                        )
                    Row(horizontalArrangement = Arrangement.SpaceAround) {
                        //ponemos espinner marca
                        Text(text = "Marca", color = Color(205, 92, 92))
                        generarSpinnerMarca(objeto_wiew_Model,"")

                    }
                    Row(horizontalArrangement = Arrangement.Center) {
                        //ponemos el spiner Enganche
                        Text(text = "Enganche", color = Color(205, 92, 92))
                        generarSpinnerEnganche(objeto_wiew_Model,"")
                    }
                    Row(horizontalArrangement = Arrangement.Center) {
                        //pnemos spiner Financiamiento Anual
                        Column() {
                            Text(text = "Financiamiento", color = Color(205, 92, 92))
                             Text(text = "Anual", color = Color(205, 92, 92))

                        }
                        generarSpinnerFinanciamiento(objeto_wiew_Model,"")
                    }
                    Column() {
                        Row()
                        {
                            Button(onClick = {
                                objeto_wiew_Model.CalcularNomCliente(text.value)
                                text.value=""
                            }, modifier = Modifier.background(color = Color.White), shape = CutCornerShape(4.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                            ) {
                                Icon(
                                    Icons.Filled.Refresh,
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(ButtonDefaults.IconSize)
                                )
                                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                Text("Cotizar")
                            }
                            //botton Reset
                            Button(onClick = {
                                objeto_wiew_Model.Reset()
                                text.value=""
                            }, modifier = Modifier.background(color = Color.White), shape = CutCornerShape(4.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                            ) {
                                Icon(
                                    Icons.Filled.Refresh,
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(ButtonDefaults.IconSize)
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text("Reset")
                            }
                        }
                        //ponemos el boton para calcular
                        Text("Cliente: "+objeto_wiew_Model.nobreCliente.value,color = Color(205, 92, 92))
                        Text("Marca: " +objeto_wiew_Model.marcaAuto.value,color = Color(205, 92, 92))
                        Text("Enganche: " +objeto_wiew_Model.signoP.value+objeto_wiew_Model.Enganche.value,color = Color(205, 92, 92))
                        Text("Monto a financiar:" +objeto_wiew_Model.Financiamiento.value,color = Color(205, 92, 92))
                        Text("Financiamiento a: " +objeto_wiew_Model.año.value+objeto_wiew_Model.text+objeto_wiew_Model.Tasaint.value+objeto_wiew_Model.text2,color = Color(205, 92, 92))
                        Text("Monto de interes por: " +objeto_wiew_Model.año.value+objeto_wiew_Model.añoString.value+objeto_wiew_Model.MontoPorAños.value,color = Color(205, 92, 92))
                        Text("Monto a financiar mas interes="+objeto_wiew_Model.Financiamiento.value+objeto_wiew_Model.SignoMas.value+objeto_wiew_Model.MontoPorAños.value+objeto_wiew_Model.SigIgual.value+objeto_wiew_Model.SignoPeso+objeto_wiew_Model.MontoFinaInt.value,color = Color(205, 92, 92))
                        Text("Pagos mensuales por $"+objeto_wiew_Model.PagoMensual.value,color = Color(205, 92, 92))
                        Text("Pago Total (Enganche + Financiamiento)= $"+objeto_wiew_Model.Enganche.value+objeto_wiew_Model.SignoMas.value+objeto_wiew_Model.MontoFinaInt.value+objeto_wiew_Model.SigIgual.value+objeto_wiew_Model.PagoTotal.value,color = Color(205, 92, 92))

                    }

                }
            }
        }
    }

}
// funcion para generar el spiner Marca
@Composable
fun generarSpinnerMarca(objeto:MainViewModel,texto:String)
{
    var expanded by remember { mutableStateOf(false) }
    val suggestions = arrayOf(
        arrayOf(0,"HondaAcord: $678026.22",678026.22) ,
        arrayOf(1,"Vw Touareg: $879266.15",879266.15),
        arrayOf(2,"BMWX5 $1025366.87",1025366.87),
        arrayOf(3,"Mazda CX7 $988641.02",988641.02),
        )
    //val suggestions = listOf("Honda Acord:  $678,026.22","Vw Touareg:  $879,266.15","BMWX5:  $1,025,366.87 ","Mazda CX7  $988,641.02")
    Box() {
        Button(onClick = { expanded = !expanded },colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan)){

            Text ("Selecciona un Auto")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            suggestions.forEach { label->
                DropdownMenuItem(onClick = {
                    expanded = false
                    //pasamos las funciones calcular sexo
                    objeto.CalcularMarcaAuto(label[1].toString())
                    objeto.preciNor(label[2].toString())
                }) {
                    Text(text = label[1].toString())
                }
            }
        }

    }
}
//realisamos el spiner Enganche
@Composable
fun generarSpinnerEnganche(objeto:MainViewModel,texto:String)
{
    var expanded by remember { mutableStateOf(false) }
    val suggestions = arrayOf(
        arrayOf(0,"(20%)",20),
        arrayOf(1,"(40%)",40),
        arrayOf(2,"(60%)",60),
    )
    Box() {
        Button(onClick = { expanded = !expanded },colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan)){
            Text ("Seleccina Enganche")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {

            suggestions.forEach { label->
                DropdownMenuItem(onClick = {
                    expanded = false
                    //pasamos las funciones calcular sexo
                    objeto.agragarsigporce(label[1].toString())
                    objeto.CalcularEnganche(label[2].toString())
                }) {
                    Text(text = label[1].toString())
                }
            }
        }

    }
}
//realizamos el espiner Financiamiento Anual
@Composable
fun generarSpinnerFinanciamiento(objeto:MainViewModel,texto:String)
{
    var expanded by remember { mutableStateOf(false) }
    val suggestions = arrayOf(
        arrayOf(0,1,"1 Años 7.5%"," años=",7.5,"+","="),
        arrayOf(1,2,"2 Años 9.5%"," años=",9.5,"+","="),
        arrayOf(2,3,"3 años 10.3%"," años=",10.3,"+","="),
        arrayOf(3,4,"4 años 12.6%"," años=",12.6,"+","="),
        arrayOf(4,5,"5 años 13.5%"," años=",13.5,"+","="),
        )
    Box() {
        Button(onClick = { expanded = !expanded },colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan)){
            Text ("Financiamiento")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            suggestions.forEach { label->
                DropdownMenuItem(onClick = {
                    expanded = false
                    //pasamos las funciones calcular sexo
                    objeto.CalcularFinanciamiento(label[2].toString(),label[3].toString())
                    objeto.GetAño(label[1].toString(),label[4].toString(),label[5].toString(),label[6].toString())
                }) {
                    Text(text = label[2].toString())
                }
            }
        }

    }
}
