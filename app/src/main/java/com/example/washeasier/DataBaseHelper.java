package com.example.washeasier;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "WashEasier.db";
    public static final int DATABASE_VERSION = 3;
    public static final String ESTACAO = "ESTACAO";
    public static final String ESTACAOID = "ESTACAOID";
    public static final String NOME_EMPRESA = "NOME_EMPRESA";
    public static final String HORARIO_SERVICO = "HORARIO_SERVICO";
    public static final String EST_LAT = "EST_LAT";
    public static final String EST_LONG = "EST_LONG";
    public static final String SERVICOS = "SERVICOS";
    public static final String SERVICOSID = "SERVICOSID";
    public static final String DESCRICAO = "DESCRICAO";
    public static final String EST_SERV = "EST_SERV";
    public static final String PRECO = "PRECO";
    public static final String ESTACAO_ESTACAOID = "ESTACAO_ESTACAOID";
    private static final String SERVICOS_SERVICOSID = "SERVICOS_SERVICOSID";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //É chamado quando a db é usada pela primeira vez, possui o codigo para criar a db
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //criar as tabelas da base de dados
        String SQL_Estacao = "CREATE TABLE " + ESTACAO + "(" + ESTACAOID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME_EMPRESA + " VARCHAR(45), " +
                HORARIO_SERVICO + " VARCHAR(45), " +
                EST_LAT + " DECIMAL(2,8), " +
                EST_LONG + " DECIMAL(2,8)) ";

        String SQL_Servicos = "CREATE TABLE " + SERVICOS + "(" + SERVICOSID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DESCRICAO + " VARCHAR(45))";

        String SQL_Est_serv = "CREATE TABLE " + EST_SERV + "(" + ESTACAO_ESTACAOID + " INTEGER, " +
                SERVICOS_SERVICOSID + " INTEGER," +
                PRECO + " VARCHAR(6), " +
                "FOREIGN KEY( " + ESTACAO_ESTACAOID + " ) REFERENCES " + ESTACAO + " ( " + ESTACAOID + " ), " +
                "FOREIGN KEY( " + SERVICOS_SERVICOSID + ") REFERENCES " + SERVICOS + "( " + SERVICOSID + " ))";

        sqLiteDatabase.execSQL(SQL_Estacao);
        sqLiteDatabase.execSQL(SQL_Servicos);
        sqLiteDatabase.execSQL(SQL_Est_serv);

        //inserir os dados na tabela estacao
        String SQL_Est1 = "INSERT INTO " + ESTACAO + "(" + NOME_EMPRESA + ", " + HORARIO_SERVICO + ", " + EST_LAT + ", " + EST_LONG + ") VALUES('Box Car Care Center', " +
                "'Segunda a sábado das 8:30 ás 18:30', 37.74744819, -25.65610552)";
        String SQL_Est2 = "INSERT INTO " + ESTACAO + "(" + NOME_EMPRESA + ", " + HORARIO_SERVICO + ", " + EST_LAT + ", " + EST_LONG + ") VALUES('Sprint Lavagem', " +
                "'Terça a sábado das 8:00 ás 20:00', 37.73656500, -25.68301032)";
        String SQL_Est3 = "INSERT INTO " + ESTACAO + "(" + NOME_EMPRESA + ", " + HORARIO_SERVICO + ", " + EST_LAT + ", " + EST_LONG + ") VALUES('Galp São Gonçalo', " +
                "'Segunda a domindo das 0:00 ás 24:00', 37.74971235, -25.64976257)";
        String SQL_Est4 = "INSERT INTO " + ESTACAO + "(" + NOME_EMPRESA + ", " + HORARIO_SERVICO + ", " + EST_LAT + ", " + EST_LONG + ") VALUES('296 Car Wash', " +
                "'Segunda a sábado das 9:00 ás 20:00', 37.75282916, -25.65432334)";

        sqLiteDatabase.execSQL(SQL_Est1);
        sqLiteDatabase.execSQL(SQL_Est2);
        sqLiteDatabase.execSQL(SQL_Est3);
        sqLiteDatabase.execSQL(SQL_Est4);


        //inserir os dados na tabela servicos
        String SQL_Serv1 = "INSERT INTO " + SERVICOS + "(" + DESCRICAO + ") VALUES('Simples')";
        String SQL_Serv2 = "INSERT INTO " + SERVICOS + "(" + DESCRICAO + ") VALUES('Especial')";
        String SQL_Serv3 = "INSERT INTO " + SERVICOS + "(" + DESCRICAO + ") VALUES('Extra')";

        sqLiteDatabase.execSQL(SQL_Serv1);
        sqLiteDatabase.execSQL(SQL_Serv2);
        sqLiteDatabase.execSQL(SQL_Serv3);


        //inserir os dados na tabela est_serv
        String SQL_Est_Serv1 = "INSERT INTO " + EST_SERV + "(" + PRECO + ", " + ESTACAO_ESTACAOID + ", " + SERVICOS_SERVICOSID + ") VALUES('4.70€', 1, 1)";
        String SQL_Est_Serv2 = "INSERT INTO " + EST_SERV + "(" + PRECO + ", " + ESTACAO_ESTACAOID + ", " + SERVICOS_SERVICOSID + ") VALUES('7.70€', 1, 2)";
        String SQL_Est_Serv3 = "INSERT INTO " + EST_SERV + "(" + PRECO + ", " + ESTACAO_ESTACAOID + ", " + SERVICOS_SERVICOSID + ") VALUES('8.70€', 1, 3)";

        String SQL_Est_Serv4 = "INSERT INTO " + EST_SERV + "(" + PRECO + ", " + ESTACAO_ESTACAOID + ", " + SERVICOS_SERVICOSID + ") VALUES('4.50€', 2, 1)";
        String SQL_Est_Serv5 = "INSERT INTO " + EST_SERV + "(" + PRECO + ", " + ESTACAO_ESTACAOID + ", " + SERVICOS_SERVICOSID + ") VALUES('7.50€', 2, 2)";
        String SQL_Est_Serv6 = "INSERT INTO " + EST_SERV + "(" + PRECO + ", " + ESTACAO_ESTACAOID + ", " + SERVICOS_SERVICOSID + ") VALUES('8.50€', 2, 3)";

        String SQL_Est_Serv7 = "INSERT INTO " + EST_SERV + "(" + PRECO + ", " + ESTACAO_ESTACAOID + ", " + SERVICOS_SERVICOSID + ") VALUES('4.95€', 3, 1)";
        String SQL_Est_Serv8 = "INSERT INTO " + EST_SERV + "(" + PRECO + ", " + ESTACAO_ESTACAOID + ", " + SERVICOS_SERVICOSID + ") VALUES('5.95€', 3, 2)";
        String SQL_Est_Serv9 = "INSERT INTO " + EST_SERV + "(" + PRECO + ", " + ESTACAO_ESTACAOID + ", " + SERVICOS_SERVICOSID + ") VALUES('7.45€', 3, 3)";

        String SQL_Est_Serv10 = "INSERT INTO " + EST_SERV + "(" + PRECO + ", " + ESTACAO_ESTACAOID + ", " + SERVICOS_SERVICOSID + ") VALUES('4.60€', 4, 1)";
        String SQL_Est_Serv11 = "INSERT INTO " + EST_SERV + "(" + PRECO + ", " + ESTACAO_ESTACAOID + ", " + SERVICOS_SERVICOSID + ") VALUES('7.60€', 4, 2)";
        String SQL_Est_Serv12 = "INSERT INTO " + EST_SERV + "(" + PRECO + ", " + ESTACAO_ESTACAOID + ", " + SERVICOS_SERVICOSID + ") VALUES('8.60€', 4, 3)";

        sqLiteDatabase.execSQL(SQL_Est_Serv1);
        sqLiteDatabase.execSQL(SQL_Est_Serv2);
        sqLiteDatabase.execSQL(SQL_Est_Serv3);
        sqLiteDatabase.execSQL(SQL_Est_Serv4);
        sqLiteDatabase.execSQL(SQL_Est_Serv5);
        sqLiteDatabase.execSQL(SQL_Est_Serv6);
        sqLiteDatabase.execSQL(SQL_Est_Serv7);
        sqLiteDatabase.execSQL(SQL_Est_Serv8);
        sqLiteDatabase.execSQL(SQL_Est_Serv9);
        sqLiteDatabase.execSQL(SQL_Est_Serv10);
        sqLiteDatabase.execSQL(SQL_Est_Serv11);
        sqLiteDatabase.execSQL(SQL_Est_Serv12);

    }

    //É chamado quando a db é alterada
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ESTACAO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SERVICOS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EST_SERV);

        onCreate(sqLiteDatabase);
    }


    //vai buscar os dados da tabela ESTACAO
    public List<EstacaoModel> getEstacao(){
        List<EstacaoModel> returnEstacao = new ArrayList<>();

        String queryEstacao = "SELECT * FROM " + ESTACAO;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryEstacao, null);

        if(cursor.moveToFirst()){
            do{
                int estacaoID = cursor.getInt(0);
                String nomeEstacao = cursor.getString(1);
                String horarioServico = cursor.getString(2);
                float estLat = cursor.getFloat(3);
                float estLong = cursor.getFloat(4);

                EstacaoModel estacaoModel = new EstacaoModel(estacaoID, nomeEstacao, horarioServico, estLat, estLong);
                returnEstacao.add(estacaoModel);
            }while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return returnEstacao;
    }

    //vai buscar os dados da tabela EST_SERV
    public List<Est_ServModel> getEst_Serv(){
        List<Est_ServModel> returnEst_Serv = new ArrayList<>();

        String queryEst_Serv = "SELECT * FROM " + EST_SERV;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryEst_Serv, null);

        if (cursor.moveToFirst()){
            do{
                int estacaoID = cursor.getInt(0);
                int servicosID = cursor.getInt(1);
                String preco = cursor.getString(2);

                Est_ServModel est_servModel = new Est_ServModel(estacaoID, servicosID, preco);
                returnEst_Serv.add(est_servModel);
            }while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return returnEst_Serv;
    }

    //vai buscar os dados da tabela SERVICOS
    public List<ServicosModel> getServicos(){
        List<ServicosModel> returnServicos = new ArrayList<>();

        String queryServicos = "SELECT * FROM " + SERVICOS;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryServicos, null);

        if (cursor.moveToFirst()){
            do{
                int servicosID = cursor.getInt(0);
                String descricao = cursor.getString(1);

                ServicosModel servicosModel = new ServicosModel(servicosID, descricao);
                returnServicos.add(servicosModel);
            }while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return returnServicos;
    }
}