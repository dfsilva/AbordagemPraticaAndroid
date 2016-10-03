package br.com.diegosilva.smarthome.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.diegosilva.smarthome.dominio.Acao;
import br.com.diegosilva.smarthome.dominio.Dispositivo;

/**
 * Created by 98379720172 on 30/09/16.
 */

public class DispositivoDAO {

    private SQLiteHelper dbHelper;
    private Context context;

    public static final String NOME_TABELA = "dispositivo";

    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_TITULO = "titulo";
    public static final String COLUNA_CODIGO = "codito";

    public static final String SCRIPT_CRIACAO = "create table " + NOME_TABELA + "(" + COLUNA_ID + " integer primary key, " + COLUNA_TITULO
            + " text not null, " + COLUNA_CODIGO + " text null " + ");";

    public static final String[] colunas = {COLUNA_ID, COLUNA_TITULO, COLUNA_CODIGO};

    public DispositivoDAO(Context context) {
        dbHelper = SQLiteHelper.getHelper(context);
        this.context = context;
    }

    public long criar(Dispositivo dispositivo) throws Exception {

        SQLiteDatabase bd = dbHelper.getWritableDatabase();
        bd.beginTransaction();

        try {
            ContentValues values = new ContentValues();

            values.put(COLUNA_TITULO, dispositivo.titulo);
            values.put(COLUNA_CODIGO, dispositivo.codigo);

            long id = bd.insert(NOME_TABELA, null, values);
            dispositivo.id = id;

            if (!dispositivo.acoes.isEmpty()) {
                AcaoDAO acaoDAO = new AcaoDAO(context);

                for (Acao acao : dispositivo.acoes) {
                    acao.dispositivo = dispositivo;
                    acao.id = acaoDAO.criar(acao, bd);
                }
            }

            bd.setTransactionSuccessful();
            return id;
        } catch (Exception e) {
            throw e;
        } finally {
            bd.endTransaction();
        }
    }

    public Dispositivo atualizar(Dispositivo dispositivo) throws Exception {

        SQLiteDatabase bd = dbHelper.getWritableDatabase();
        bd.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(COLUNA_TITULO, dispositivo.titulo);
            values.put(COLUNA_CODIGO, dispositivo.codigo);

            bd.update(NOME_TABELA, values, COLUNA_ID + " = ?", new String[]{String.valueOf(dispositivo.id)});

            AcaoDAO acaoDAO = new AcaoDAO(context);

            acaoDAO.excluirPeloDispositivo(dispositivo.id, bd);

            for (Acao acao : dispositivo.acoes) {
                acao.dispositivo = dispositivo;
                acao.id = acaoDAO.criar(acao, bd);
            }

            bd.setTransactionSuccessful();

            return dispositivo;
        } catch (Exception e) {
            throw e;
        } finally {
            bd.endTransaction();
        }
    }


    public void excluir(Dispositivo dispositivo) throws Exception {
        long id = dispositivo.id;
        SQLiteDatabase bd = dbHelper.getWritableDatabase();
        bd.beginTransaction();
        try {
            AcaoDAO acaoDAO = new AcaoDAO(context);
            acaoDAO.excluirPeloDispositivo(dispositivo.id, bd);

            bd.delete(NOME_TABELA, COLUNA_ID + " = " + id, null);

            bd.setTransactionSuccessful();

        } catch (Exception e) {
            throw e;
        } finally {
            bd.endTransaction();
        }
    }


    public List<Dispositivo> listar() {
        List<Dispositivo> dispositivos = new ArrayList<>();
        SQLiteDatabase bd = dbHelper.getReadableDatabase();
        Cursor cursor = bd.query(NOME_TABELA, colunas, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Dispositivo dispositivo = cursorParaDispositivo(cursor);
            dispositivos.add(dispositivo);
            cursor.moveToNext();
        }

        cursor.close();

        return dispositivos;
    }

    public Dispositivo carregar(Long id) {

        SQLiteDatabase bd = dbHelper.getReadableDatabase();
        Cursor cursor = bd.query(NOME_TABELA, colunas, COLUNA_ID + " = " + id, null, null, null, null);

        Dispositivo dispositivo = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            dispositivo = cursorParaDispositivo(cursor);
            dispositivo.acoes = new AcaoDAO(context).carregarPeloDispositivo(dispositivo.id, bd);
        }

        cursor.close();
        return dispositivo;
    }

    private Dispositivo cursorParaDispositivo(Cursor cursor) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.id = cursor.getLong(0);
        dispositivo.titulo = cursor.getString(1);
        dispositivo.codigo = cursor.getString(2);

        return dispositivo;
    }


}
