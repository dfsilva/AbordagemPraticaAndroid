package br.com.diegosilva.smarthome.activity;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.diegosilva.smarthome.R;

public class DetalheDispositivoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_dispositivo);


        TextView txTitulo = (TextView) findViewById(R.id.txTitulo);
        TextView txCodigo = (TextView) findViewById(R.id.txCodigo);

        LinearLayout painelAcoes = (LinearLayout) findViewById(R.id.painelAcoes);

    }
}
