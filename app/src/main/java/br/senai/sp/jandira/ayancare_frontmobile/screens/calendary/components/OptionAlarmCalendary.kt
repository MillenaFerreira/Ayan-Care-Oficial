package br.senai.sp.jandira.ayancare_frontmobile.screens.calendary.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.AlarmeUnitariosResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.AlarmesResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.Alarme
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.alarmes.service.AlarmeUnitario
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile.sqlite.repository.PacienteRepository
import br.senai.sp.jandira.ayancare_frontmobile.viewModel.user.ViewModelMedicamentos
import br.senai.sp.jandira.ayancare_frontmobile.viewModel.user.ViewModelMestreMedicamentos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun OptionAlarmCalendary(
    localStorage: Storage
) {

    val context = LocalContext.current

    val array = PacienteRepository(context = context).findUsers()

    val paciente = array[0]
    var id = paciente.id.toLong()

//    val lista_alarme = localStorage.lerValor(context, "lista_alarmes")
//    Log.i("dddd", "CalendaryScreen: $lista_alarme")
//    val listaAlarmes = lista_alarme?.split(",")?.toList()

    var listaAlarmes = ViewModelMestreMedicamentos().lista

    Log.i("ddde", "CalendaryScreen: ${listaAlarmes}")
    Log.i("ddde", "CalendaryScreen: ${listaAlarmes}")
    Log.i("Tag", "O tipo da variável é: ${listaAlarmes!!.javaClass}")

    if (listaAlarmes.isEmpty()){
        Column (
            modifier = Modifier
                .height(300.dp)
                .padding(start = 15.dp, end = 15.dp)
        ){
            Text(
                text = "Rotina de hoje",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(400),
                color = Color(0xFF35225F)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Icon(
                    imageVector = Icons.Default.DeveloperMode,
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = "Não existe nenhum\n alarme no momento",
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center
                )
            }
        }
    }else{
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        ){
            Text(
                text = "Rotina de hoje",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(400),
                color = Color(0xFF35225F)
            )
            Spacer(modifier = Modifier.height(10.dp))

                for (alarme in listaAlarmes) {
                    val medication = alarme.medicamento
                    val time = alarme.horario
                    val status = alarme.status
                    CardCalendary(
                        value = time,
                        title = "Alarme",
                        subtitle = " x ${medication}", //${alarme.quantidade_retirada}${alarme.medida_sigla}
                        status = status,
                        width = 75
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

        }
    }
}
