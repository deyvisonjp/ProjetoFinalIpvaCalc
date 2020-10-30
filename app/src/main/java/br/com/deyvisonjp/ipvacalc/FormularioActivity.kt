package br.com.deyvisonjp.ipvacalc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class FormularioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        // Referenciando os componetes da Activity -------------------------------------------------
        val edtFormularioVeiculo: EditText = findViewById(R.id.edtFormularioVeiculo)
        val edtFormularioValor: EditText = findViewById(R.id.edtFormularioValor)
        val spnFormularioEstado: Spinner = findViewById(R.id.spnFormularioEstado)
        val btnFormularioCalc: Button = findViewById(R.id.btnFormularioCalc)

        // SPINNER ---------------------------------------------------------------------------------
        // Estados Norte
        val listaEstadosNorte = arrayListOf("Selecione o estado",
                "Acre", "Tocantins", "Pará", "Amapá", "Amazonas", "Roraima", "Rondônia"
        )
        // Criar num adaptador para o spinner
        val estadoAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item, //Layout
            listaEstadosNorte
        )
        spnFormularioEstado.adapter = estadoAdapter

        btnFormularioCalc.setOnClickListener {
            val tituloVeiculo = edtFormularioVeiculo.text.toString().trim()
            val valorVeiculo = edtFormularioValor.text.toString().trim()
            val estado = spnFormularioEstado.selectedItem.toString()

            // Validando campos obrigatórios
            if (valorVeiculo.isEmpty() || estado == listaEstadosNorte[0]){
                Toast.makeText(this, "Os campos de valor e estado são obrigatórios!", Toast.LENGTH_LONG).show()
            } else {

                // Salvando dados no SharedPreferences
                val sharedPref = getSharedPreferences("cadastro_$tituloVeiculo", Context.MODE_PRIVATE)

                val editPrefs = sharedPref.edit()

                editPrefs.putString("VEICULO", tituloVeiculo)
                editPrefs.putString("VALOR", valorVeiculo)
                editPrefs.putString("ESTADO", estado)

                editPrefs.apply() //Save

                val mIntent = Intent(this, ResultadoActivity::class.java)
                mIntent.putExtra("INTENT_VEICULO", tituloVeiculo)
                startActivity(mIntent)
                finishAffinity()

            }
        }

    }
}

/* Pegando Estados de acordo com a escolha do mapa
val estados = intent.getStringArrayListExtra("REGIAO")
// Acessar o arquivo de Shared Preferences
val sharedPrefs = getSharedPreferences("regiao_norte", Context.MODE_PRIVATE)
// Recupera os dados
val listaDeEstados = arrayListOf(sharedPrefs.getString("REGIAO", ""))
*/