package br.com.diegosilva.smarthome.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.diegosilva.smarthome.R;
import br.com.diegosilva.smarthome.fragment.ManterDispositivoFragment;

public class ManterDispositivoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manter_dispositivo);

        getSupportFragmentManager().beginTransaction().replace(R.id.conteudo, ManterDispositivoFragment.newInstance(getIntent().getExtras())).commit();

    }
}
