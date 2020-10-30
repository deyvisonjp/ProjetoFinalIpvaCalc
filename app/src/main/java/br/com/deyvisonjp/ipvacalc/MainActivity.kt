package br.com.deyvisonjp.ipvacalc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMapNorte: Button = findViewById(R.id.btnMapNorte)
        val btnMapNordeste: Button = findViewById(R.id.btnMapNordeste);
        val btnMapSudeste: Button = findViewById(R.id.btnMapSudeste);
        val btnMapSul: Button = findViewById(R.id.btnMapSul);
        val btnMapCentroOeste: Button = findViewById(R.id.btnMapCentroOeste);

        // Criando as checklists
        val listaNorte = arrayListOf<String>("Selecione seu Estado",  "Acre/Tocantins", "Pará", "Amapá/Amazonas/Roraima/Rondônia")
        val listaNordeste = arrayListOf("Selecione seu Estado", "Sergipe/Paraíba", "Alagoas/ Pernambuco/...")


        // Caso a escolha seja a região Norte
        btnMapNorte.setOnClickListener{
            // Criar ou acessar o arquivo de SharedPreferences
            val sharedPrefs = getSharedPreferences("regiao_norte",
                    Context.MODE_PRIVATE);

            // Criando uma referência para o editor do arquivo
            val editPrefs = sharedPrefs.edit();
            // editPrefs.putStringSet("NORTE", listaNorte) // Add
            editPrefs.apply() //Salva

            val mIntent = Intent(this, FormularioActivity::class.java)
            mIntent.putStringArrayListExtra("REGIAO", listaNorte)
            startActivity(mIntent)
            finishAffinity()
        }

    }
}