package br.com.diegosilva.smarthome.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 98379720172 on 30/09/16.
 */

public class SmartHomeSQLiteHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "smart_home.db";
    private static final int VERSAO_BANCO = 0;

    private static SmartHomeSQLiteHelper instance;


    private SmartHomeSQLiteHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
//        database.execSQL(VideoDAO.TABELA_VIDEO_CRIACAO);
//        database.execSQL(NoticiaDAO.TABELA_CRIACAO);
//        database.execSQL(PlayerDAO.TABELA_CRIACAO);
//        database.execSQL(LogExecucaoDAO.TABELA_CRIACAO);
//        database.execSQL(PlaylistDAO.TABELA_CRIACAO);
//        database.execSQL(CaixaDAO.TABELA_CRIACAO);
//        database.execSQL(LogChamadorFilaDAO.TABELA_CRIACAO);
//        database.execSQL(MensagemControleDAO.TABELA_CRIACAO);
//        database.execSQL(LogMensagemControleDAO.TABELA_CRIACAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        db.execSQL("DROP TABLE IF EXISTS " + VideoDAO.NOME_TABELA);
//        db.execSQL("DROP TABLE IF EXISTS " + NoticiaDAO.NOME_TABELA);
//        db.execSQL("DROP TABLE IF EXISTS " + PlayerDAO.NOME_TABELA);
//        db.execSQL("DROP TABLE IF EXISTS " + LogExecucaoDAO.NOME_TABELA);
//        db.execSQL("DROP TABLE IF EXISTS " + PlaylistDAO.NOME_TABELA);
//        db.execSQL("DROP TABLE IF EXISTS " + CaixaDAO.NOME_TABELA);
//        db.execSQL("DROP TABLE IF EXISTS " + LogChamadorFilaDAO.NOME_TABELA);
//        db.execSQL("DROP TABLE IF EXISTS " + MensagemControleDAO.NOME_TABELA);
//        db.execSQL("DROP TABLE IF EXISTS " + LogMensagemControleDAO.NOME_TABELA);

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