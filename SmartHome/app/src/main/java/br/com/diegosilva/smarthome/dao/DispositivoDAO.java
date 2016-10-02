package br.com.diegosilva.smarthome.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.diegosilva.smarthome.dominio.Dispositivo;
import br.com.diegosilva.smarthome.sqlite.SmartHomeSQLiteHelper;

/**
 * Created by 98379720172 on 30/09/16.
 */

public class DispositivoDAO {

    private SmartHomeSQLiteHelper dbHelper;

    public static final String NOME_TABELA = "dispositivo";

    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_TITULO = "titulo";
    public static final String COLUNA_CODIGO = "codito";

    public static final String SCRIPT_CRIACAO = "create table " + NOME_TABELA + "(" + COLUNA_ID + " integer primary key, " + COLUNA_TITULO
            + " text not null, "+ COLUNA_CODIGO+ " text null "+");";

    public static final String[] colunas = { COLUNA_ID, COLUNA_TITULO, COLUNA_CODIGO };

    public DispositivoDAO(Context context) {
        dbHelper = SmartHomeSQLiteHelper.getHelper(context);
    }

    public Dispositivo criar(String titulo, String codigo) {
        ContentValues values = new ContentValues();

        values.put(COLUNA_TITULO, titulo);
        values.put(COLUNA_CODIGO, codigo);

        SQLiteDatabase bd = dbHelper.getWritableDatabase();

        long id = bd.insert(NOME_TABELA, null, values);

        Cursor cursor = bd.query(NOME_TABELA, colunas, COLUNA_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        Dispositivo dispositivo = cursorParaDispositivo(cursor);
        cursor.close();
        return dispositivo;
    }

    public Dispositivo criar(Dispositivo dispositivo) {

        ContentValues values = new ContentValues();

        values.put(COLUNA_TITULO, dispositivo.titulo);
        values.put(COLUNA_CODIGO, dispositivo.codigo);

        SQLiteDatabase bd = dbHelper.getWritableDatabase();

        long id = bd.insert(NOME_TABELA, null, values);

        Cursor cursor = bd.query(NOME_TABELA, colunas, COLUNA_ID + " = " + id, null, null, null, null);

        cursor.moveToFirst();
        Dispositivo retorno = cursorParaDispositivo(cursor);
        cursor.close();

        return retorno;
    }

    public Dispositivo atualizar(Dispositivo dispositivo) {

        ContentValues values = new ContentValues();

        values.put(COLUNA_TITULO, dispositivo.titulo);
        values.put(COLUNA_CODIGO, dispositivo.codigo);

        SQLiteDatabase bd = dbHelper.getWritableDatabase();

        bd.update(NOME_TABELA, values, COLUNA_ID + " = ?", new String[] { String.valueOf(dispositivo.id) });

        Cursor cursor = bd.query(NOME_TABELA, colunas, COLUNA_ID + " = " + dispositivo.id, null, null, null, null);

        cursor.moveToFirst();
        Dispositivo novoDispositivo = cursorParaDispositivo(cursor);
        cursor.close();
        return novoDispositivo;
    }

    public void excluir(Dispositivo dispositivo) {
        long id = dispositivo.id;
        SQLiteDatabase bd = dbHelper.getWritableDatabase();

        bd.delete(NOME_TABELA, COLUNA_ID + " = " + id, null);
    }

    public void delete(int id) {
        SQLiteDatabase bd = dbHelper.getWritableDatabase();
        bd.delete(NOME_TABELA, COLUNA_ID + " = " + id, null);
    }

    public List<Dispositivo> all() {
        List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();

        SQLiteDatabase bd = dbHelper.getReadableDatabase();

        Cursor cursor = bd.query(NOME_TABELA, colunas, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Dispositivo video = cursorParaDispositivo(cursor);
            dispositivos.add(video);
            cursor.moveToNext();
        }

        cursor.close();

        return dispositivos;
    }

    public Dispositivo load(Long id) {

        SQLiteDatabase bd = dbHelper.getReadableDatabase();
        Cursor cursor = bd.query(NOME_TABELA, colunas, COLUNA_ID + " = " + id, null, null, null, null);

        Dispositivo caixa = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            caixa = cursorParaDispositivo(cursor);
        }

        cursor.close();
        return caixa;
    }

    private Dispositivo cursorParaDispositivo(Cursor cursor) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.id = cursor.getInt(0);
        dispositivo.titulo = cursor.getString(1);
        dispositivo.codigo = cursor.getString(2);

        return dispositivo;
    }


}
