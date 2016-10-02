package br.com.diegosilva.smarthome.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 98379720172 on 30/09/16.
 */

public class Dispositivo {

    public long id;
    public String titulo;
    public String codigo;
    public List<Acao> acoes = new ArrayList<>();

    public Dispositivo(){

    }

    public Dispositivo(long id){
        this.id = id;
    }

}
