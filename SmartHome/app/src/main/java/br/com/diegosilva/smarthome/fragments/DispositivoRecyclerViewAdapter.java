package br.com.diegosilva.smarthome.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.diegosilva.smarthome.R;
import br.com.diegosilva.smarthome.dominio.Dispositivo;
import java.util.List;


public class DispositivoRecyclerViewAdapter
        extends RecyclerView.Adapter<DispositivoRecyclerViewAdapter.ViewHolder>{

    private final List<Dispositivo> dispositivos;
    private DispositivosFragment.OnListFragmentInteractionListener mListener;
    private int posicao;

    public DispositivoRecyclerViewAdapter(List<Dispositivo> dispositivos,
                                          DispositivosFragment.OnListFragmentInteractionListener listener) {
        this.dispositivos = dispositivos;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_dispositivo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.dispositivo = dispositivos.get(position);
        holder.txTitulo.setText(dispositivos.get(position).titulo);
        holder.txCodigo.setText(dispositivos.get(position).codigo);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.dispositivo);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosicao(holder.getAdapterPosition());
                return false;
            }
        });

    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    @Override
    public int getItemCount() {
        return dispositivos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        public final View mView;
        public final TextView txTitulo;
        public final TextView txCodigo;

        public Dispositivo dispositivo;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txTitulo = (TextView) view.findViewById(R.id.txTitulo);
            txCodigo = (TextView) view.findViewById(R.id.txCodigo);
            view.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle(R.string.selecione_acao);
            menu.add(Menu.NONE, R.id.menu_editar, 0, R.string.editar);
            menu.add(Menu.NONE, R.id.menu_excluir, 0, R.string.excluir);
        }

    }
}
