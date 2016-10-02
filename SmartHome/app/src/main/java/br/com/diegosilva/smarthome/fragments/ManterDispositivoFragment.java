package br.com.diegosilva.smarthome.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.diegosilva.smarthome.R;
import br.com.diegosilva.smarthome.dao.DispositivoDAO;
import br.com.diegosilva.smarthome.dominio.Acao;
import br.com.diegosilva.smarthome.dominio.Dispositivo;

/**
 * Created by diego on 01/10/16.
 */

public class ManterDispositivoFragment extends Fragment {

    private LinearLayout painelAcoes;
    private TextInputLayout ltTitulo;
    private EditText edtTitulo;
    private TextInputLayout ltCodigo;
    private EditText edtCodigo;
    private DispositivoDAO dao;

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

        dao = new DispositivoDAO(getContext());

        painelAcoes = (LinearLayout) view.findViewById(R.id.painelAcoes);

        ltTitulo = (TextInputLayout) view.findViewById(R.id.ltTitulo);
        edtTitulo = (EditText) view.findViewById(R.id.edtTitulo);
        ltCodigo = (TextInputLayout) view.findViewById(R.id.ltCodigo);
        edtCodigo = (EditText) view.findViewById(R.id.edtCodigo);

        FrameLayout btnAdicionarAcao = (FrameLayout) view.findViewById(R.id.btnAdicionarAcao);
        btnAdicionarAcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarLinhaAcao();
            }
        });

        Button btnSalvar = (Button) view.findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
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

    public boolean formValido(){
        boolean retorno = true;



        return retorno;
    }

    private void salvar(){

        if(formValido()){

            Dispositivo dispositivo = new Dispositivo();
            dispositivo.titulo = edtTitulo.getText().toString();
            dispositivo.codigo = edtCodigo.getText().toString();

            int qtdAcoes = painelAcoes.getChildCount();

            for(int i = 0; i < qtdAcoes; i++){
                View v = painelAcoes.getChildAt(i);
                EditText edtTitulo = (EditText) v.findViewById(R.id.edtTituloAcao);
                EditText edtCodigo = (EditText) v.findViewById(R.id.edtCodigoAcao);
                Acao acao = new Acao();
                acao.titulo = edtTitulo.getText().toString();
                acao.codigo = edtCodigo.getText().toString();
                dispositivo.acoes.add(acao);
            }

            if(dispositivo.id != 0){
                atualizar(dispositivo);
            }else{
                criar(dispositivo);
            }
        }
    }

    private void atualizar(Dispositivo dispositivo){
        try {
            dao.atualizar(dispositivo);
            getActivity().onBackPressed();
        }catch (Exception e){
            Log.e(ManterDispositivoFragment.class.getName(), e.getMessage(), e);
            Snackbar.make(getView(), "Erro ao atualizar o dispositivo", Snackbar.LENGTH_LONG).show();
        }
    }

    private void criar(Dispositivo dispositivo){
        try {
            dao.criar(dispositivo);
            getActivity().onBackPressed();
        }catch (Exception e){
            Log.e(ManterDispositivoFragment.class.getName(), e.getMessage(), e);
            Snackbar.make(getView(), "Erro ao criar o dispositivo", Snackbar.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();

        if (i == R.id.salvar) {
            salvar();
        }

        return true;
    }


}
