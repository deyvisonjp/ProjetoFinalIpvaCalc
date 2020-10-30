package br.com.deyvisonjp.ipvacalc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val txvResultCarro: TextView = findViewById(R.id.txvResultCarro)
        val txvValor: TextView = findViewById(R.id.txvResultValorCarro)
        val txvResultValorIPVA: TextView = findViewById(R.id.txvResultValorIPVA)
        val txvResultEstado: TextView = findViewById(R.id.txvResultEstado)
        val btnFormularioCalc: Button = findViewById(R.id.btnFormularioCalc)

        // Recuperando dados de SharedPref
        val veiculo = intent.getStringExtra("INTENT_VEICULO")
        val sharedPrefs = getSharedPreferences("cadastro_$veiculo", Context.MODE_PRIVATE)

        val tituloVeiculo = sharedPrefs.getString("VEICULO", "Veículo não encontrado")
        val valorVeiculo = sharedPrefs.getString("VALOR", "")
        val estado = sharedPrefs.getString("ESTADO", "")

        txvResultCarro.text = "$tituloVeiculo"
        txvValor.text = "$valorVeiculo"
        txvResultEstado.text = "$estado"

        var valorV = "$valorVeiculo".toFloat()

        // Lógica para calcular as porcentagens
        if (estado == "Acre" || estado == "Tocantins") {
            var calcIPVA: Double = (valorV * 0.02)
            txvResultValorIPVA.setText(String.format("%.2f", calcIPVA));
        } else if (estado == "Pará") {
            var calcIPVA: Double = (valorV * 0.025)
            txvResultValorIPVA.setText(String.format("%.2f", calcIPVA));
        } else {
            var calcIPVA: Double = (valorV * 0.03)
            txvResultValorIPVA.setText(String.format("%.2f", calcIPVA));
        }

        btnFormularioCalc.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

}