package br.com.diegosilva.smarthome.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.diegosilva.smarthome.R;
import br.com.diegosilva.smarthome.dominio.Dispositivo;

/**
 * Created by diego on 01/10/16.
 */

public class ManterDispositivoFragment extends Fragment {

    private LinearLayout painelAcoes;

    public ManterDispositivoFragment() {

        setHasOptionsMenu(true);
    }


    public static ManterDispositivoFragment newInstance(int columnCount) {
        ManterDispositivoFragment fragment = new ManterDispositivoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manter_dispositivo, container, false);

        painelAcoes = (LinearLayout) view.findViewById(R.id.painelAcoes);

        FrameLayout btnAdicionarAcao = (FrameLayout) view.findViewById(R.id.btnAdicionarAcao);
        btnAdicionarAcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarLinhaAcao();
            }
        });

        adicionarLinhaAcao();
        return view;
    }

    private void adicionarLinhaAcao(){
        final View linhaAcaoIncluir = getLayoutInflater(getArguments()).inflate(R.layout.linha_acao_incluir, null);
        FrameLayout btnExcluir = (FrameLayout) linhaAcaoIncluir.findViewById(R.id.btnExcluir);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                painelAcoes.removeView(linhaAcaoIncluir);
            }
        });

        painelAcoes.addView(linhaAcaoIncluir);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.manter_dispositivo, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();

        if (i == R.id.salvar) {
            //salvar
        }

        return true;
    }


}
