package br.com.diegosilva.smarthome.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.diegosilva.smarthome.dominio.Acao;
import br.com.diegosilva.smarthome.dominio.Dispositivo;
import br.com.diegosilva.smarthome.sqlite.SmartHomeSQLiteHelper;

/**
 * Created by 98379720172 on 30/09/16.
 */

public class AcaoDAO {

    private SmartHomeSQLiteHelper dbHelper;

    public static final String NOME_TABELA = "acao";

    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_TITULO = "titulo";
    public static final String COLUNA_CODIGO = "codito";
    public static final String COLUNA_ID_DISPOSITIVO = "id_dispositivo";

    public static final String SCRIPT_CRIACAO = "create table " + NOME_TABELA + "(" + COLUNA_ID + " integer primary key, " + COLUNA_TITULO
            + " text not null, "+ COLUNA_CODIGO+ " text not null, "+ COLUNA_ID_DISPOSITIVO+ " integer not null "+");";

    public static final String[] colunas = { COLUNA_ID, COLUNA_TITULO, COLUNA_CODIGO, COLUNA_ID_DISPOSITIVO};

    public AcaoDAO(Context context) {
        dbHelper = SmartHomeSQLiteHelper.getHelper(context);
    }


    public long criar(Acao acao, SQLiteDatabase bd) {

        if(bd == null)
            bd = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_TITULO, acao.titulo);
        values.put(COLUNA_CODIGO, acao.codigo);
        values.put(COLUNA_ID_DISPOSITIVO, acao.dispositivo.id);

        long id = bd.insert(NOME_TABELA, null, values);

        return id;
    }

    public int excluirPeloDispositivo(long idDispositivo) {
        SQLiteDatabase bd = dbHelper.getWritableDatabase();
        return bd.delete(NOME_TABELA, COLUNA_ID_DISPOSITIVO + " = " + idDispositivo, null);
    }

    public List<Acao> carregarPeloDispositivo(Long idDispositivo, SQLiteDatabase bd) {

        List<Acao> acoes = new ArrayList<>();

        if(bd == null)
            bd = dbHelper.getReadableDatabase();

        Cursor cursor = bd.query(NOME_TABELA, colunas, COLUNA_ID_DISPOSITIVO + " = " + idDispositivo, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Acao acao = cursorParaAcao(cursor);
            acoes.add(acao);
            cursor.moveToNext();
        }

        cursor.close();
        return acoes;
    }



    private Acao cursorParaAcao(Cursor cursor) {
        Acao acao = new Acao();
        acao.id = cursor.getLong(0);
        acao.titulo = cursor.getString(1);
        acao.codigo = cursor.getString(2);
        acao.dispositivo = new Dispositivo(cursor.getInt(3));

        return acao;
    }


}
