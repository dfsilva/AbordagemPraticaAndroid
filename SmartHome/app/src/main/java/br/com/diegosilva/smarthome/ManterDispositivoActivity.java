package br.com.diegosilva.smarthome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.diegosilva.smarthome.R;
import br.com.diegosilva.smarthome.fragments.ManterDispositivoFragment;

public class ManterDispositivoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manter_dispositivo);

        getSupportFragmentManager().beginTransaction().replace(R.id.conteudo, new ManterDispositivoFragment()).commit();
    }
}
