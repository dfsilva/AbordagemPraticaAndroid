package br.com.diegosilva.smarthome.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import br.com.diegosilva.smarthome.R;
import br.com.diegosilva.smarthome.dominio.Dispositivo;

import java.util.ArrayList;
import java.util.List;

public class DispositivosFragment extends Fragment {


    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;

    public DispositivosFragment() {
    }

    public static DispositivosFragment newInstance(OnListFragmentInteractionListener listener) {
        DispositivosFragment fragment = new DispositivosFragment();
        fragment.mListener = listener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dispositivo_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            List<Dispositivo> dispositivos = new ArrayList<>();
            recyclerView.setAdapter(new DispositivoRecyclerViewAdapter(dispositivos, mListener));
            registerForContextMenu(recyclerView);
        }

        return view;
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int posicao = ((DispositivoRecyclerViewAdapter)recyclerView.getAdapter()).getPosicao();

        switch (item.getItemId()) {
            case R.id.menu_editar:
                // do your stuff
                break;
            case R.id.menu_excluir:
                // do your stuff
                break;
        }
        return super.onContextItemSelected(item);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Dispositivo item);
    }
}
