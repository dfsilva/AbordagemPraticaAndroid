package br.com.diegosilva.smarthome.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.diegosilva.smarthome.dao.AcaoDAO;
import br.com.diegosilva.smarthome.dao.DispositivoDAO;

/**
 * Created by 98379720172 on 30/09/16.
 */

public class SmartHomeSQLiteHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "smart_home.db";
    private static final int VERSAO_BANCO = 2;

    private static SmartHomeSQLiteHelper instance;


    private SmartHomeSQLiteHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DispositivoDAO.SCRIPT_CRIACAO);
        database.execSQL(AcaoDAO.SCRIPT_CRIACAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DispositivoDAO.NOME_TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + AcaoDAO.NOME_TABELA);

        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public static synchronized SmartHomeSQLiteHelper getHelper(Context context) {
        if (instance == null)
            instance = new SmartHomeSQLiteHelper(context);
        return instance;
    }
}