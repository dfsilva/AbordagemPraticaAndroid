package br.com.diegosilva.smarthome.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.diegosilva.smarthome.R;
import br.com.diegosilva.smarthome.activity.ManterDispositivoActivity;
import br.com.diegosilva.smarthome.dao.DispositivoDAO;
import br.com.diegosilva.smarthome.dominio.Dispositivo;
import br.com.diegosilva.smarthome.fragment.adapter.DispositivoRecyclerViewAdapter;

public class ListarDispositivosFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private DispositivoDAO dao;

    public ListarDispositivosFragment() {
    }

    public static ListarDispositivosFragment newInstance(OnListFragmentInteractionListener listener) {
        ListarDispositivosFragment fragment = new ListarDispositivosFragment();
        fragment.mListener = listener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new DispositivoDAO(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dispositivo_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            List<Dispositivo> dispositivos = dao.listar();
            recyclerView.setAdapter(new DispositivoRecyclerViewAdapter(dispositivos, mListener));
            registerForContextMenu(recyclerView);
        }

        return view;
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int posicao = ((DispositivoRecyclerViewAdapter) recyclerView.getAdapter()).getPosicao();
        Dispositivo dispositivo = ((DispositivoRecyclerViewAdapter) recyclerView.getAdapter()).getDispositivo(posicao);

        switch (item.getItemId()) {
            case R.id.menu_editar:
                Intent intent = new Intent(getActivity(), ManterDispositivoActivity.class);
                Bundle b = new Bundle();
                b.putLong("id_dispositivo", dispositivo.id);
                intent.putExtras(b);
                startActivityForResult(intent, 1);

                break;
            case R.id.menu_excluir:
                try {
                    dao.excluir(dispositivo);
                    List<Dispositivo> dispositivos = dao.listar();
                    recyclerView.setAdapter(new DispositivoRecyclerViewAdapter(dispositivos, mListener));
                } catch (Exception e) {
                    Snackbar.make(getView(), "Erro ao excluir dispositivo", Snackbar.LENGTH_INDEFINITE).show();
                }
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            List<Dispositivo> dispositivos = dao.listar();
            recyclerView.setAdapter(new DispositivoRecyclerViewAdapter(dispositivos, mListener));
        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Dispositivo item);
    }
}
