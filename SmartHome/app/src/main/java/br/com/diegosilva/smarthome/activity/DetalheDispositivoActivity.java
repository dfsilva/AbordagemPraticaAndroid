package br.com.diegosilva.smarthome.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.diegosilva.smarthome.R;
import br.com.diegosilva.smarthome.dao.DispositivoDAO;
import br.com.diegosilva.smarthome.dominio.Acao;
import br.com.diegosilva.smarthome.dominio.Dispositivo;

public class DetalheDispositivoActivity extends AppCompatActivity {


    private DispositivoDAO dispositivoDAO;
    private Dispositivo dispositivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_dispositivo);

        final LinearLayout view = (LinearLayout) findViewById(R.id.activity_detalhe_dispositivo);

        Bundle b = getIntent().getExtras();
        Long idDispositivo = b.getLong("id_dispositivo");

        dispositivoDAO = new DispositivoDAO(this);
        dispositivo = dispositivoDAO.carregar(idDispositivo);

        TextView txTitulo = (TextView) findViewById(R.id.txTitulo);
        txTitulo.setText(dispositivo.titulo);
        TextView txCodigo = (TextView) findViewById(R.id.txCodigo);
        txCodigo.setText(dispositivo.codigo);

        LinearLayout painelAcoes = (LinearLayout) findViewById(R.id.painelAcoes);

        for(final Acao acao: dispositivo.acoes){
            Button bAcao = new Button(this);
            bAcao.setText(acao.titulo);
            bAcao.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            bAcao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(view, "Enviando código: "+acao.codigo, Snackbar.LENGTH_INDEFINITE).show();
                }
            });
            painelAcoes.addView(bAcao);
        }

    }
}
