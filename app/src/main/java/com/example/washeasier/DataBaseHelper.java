package com.example.washeasier;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context) {
        super(context, "washEasier.db", null, 1);
    }

    //É chamado quando a db é usada pela primeira vez, possui o codigo para criar a db
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //criar as tabelas da base de dados
        String SQL_Estacao = "CREATE TABLE estacao(estacaoID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "nomeEmpresa VARCHAR(45) NOT NULL, " +
                "horarioServico VARCHAR(45) NOT NULL, " +
                "estLat FLOAT NOT NULL, " +
                "estLong FLOAT NOT NULL) ";

        String SQL_Servicos = "CREATE TABLE servicos(servicosID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, descricao VARCHAR(45) NOT NULL)";

        String SQL_Est_serv = "CREATE TABLE est_serv(preco VARCHAR(6) NOT NULL, FOREIGN KEY(estacaoID) REFERENCES estacao(estacaoID), " +
                "FOREIGN KEY(servicosID) REFERENCES servicos(servicosID))";

        sqLiteDatabase.execSQL(SQL_Estacao);
        sqLiteDatabase.execSQL(SQL_Servicos);
        sqLiteDatabase.execSQL(SQL_Est_serv);

        //inserir os dados na tabela estacao
        String SQL_Est1 = "INSERT INTO estacao(nomeEmpresa, horarioServico, estLat, estLong) VALUES('Box Car Care Center', " +
                "'Segunda a sábado das 8:30 ás 18:30', 37.74744819, -25.65610552)";
        String SQL_Est2 = "INSERT INTO estacao(nomeEmpresa, horarioServico, estLat, estLong) VALUES('Sprint Lavagem', " +
                "'Terça a sábado das 8:00 ás 20:00', 37.73656500, -25.68301032)";
        String SQL_Est3 = "INSERT INTO estacao(nomeEmpresa, horarioServico, estLat, estLong) VALUES('Galp São Gonçalo', " +
                "'Segunda a domindo das 0:00 ás 24:00', 37.74971235, -25.64976257)";
        String SQL_Est4 = "INSERT INTO estacao(nomeEmpresa, horarioServico, estLat, estLong) VALUES('296 Car Wash', " +
                "'Segunda a sábado das 9:00 ás 20:00', 37.74744819, -25.65610552)";

        sqLiteDatabase.execSQL(SQL_Est1);
        sqLiteDatabase.execSQL(SQL_Est2);
        sqLiteDatabase.execSQL(SQL_Est3);
        sqLiteDatabase.execSQL(SQL_Est4);


        //inserir os dados na tabela servicos
        String SQL_Serv1 = "INSERT INTO servicos(descricao) VALUES('Simples')";
        String SQL_Serv2 = "INSERT INTO servicos(descricao) VALUES('Especial')";
        String SQL_Serv3 = "INSERT INTO servicos(descricao) VALUES('Extra')";

        sqLiteDatabase.execSQL(SQL_Serv1);
        sqLiteDatabase.execSQL(SQL_Serv2);
        sqLiteDatabase.execSQL(SQL_Serv3);


        //inserir os dados na tabela est_serv
        String SQL_Est_Serv1 = "INSERT INTO est_serv(preco, estacaoID, servicosID) VALUES('4.70€', 1, 1)";
        String SQL_Est_Serv2 = "INSERT INTO est_serv(preco, estacaoID, servicosID) VALUES('7.70€', 1, 2)";
        String SQL_Est_Serv3 = "INSERT INTO est_serv(preco, estacaoID, servicosID) VALUES('8.70€', 1, 3)";

        String SQL_Est_Serv4 = "INSERT INTO est_serv(preco, estacaoID, servicosID) VALUES('4.50€', 2, 1)";
        String SQL_Est_Serv5 = "INSERT INTO est_serv(preco, estacaoID, servicosID) VALUES('7.50€', 2, 2)";
        String SQL_Est_Serv6 = "INSERT INTO est_serv(preco, estacaoID, servicosID) VALUES('8.50€', 2, 3)";

        String SQL_Est_Serv7 = "INSERT INTO est_serv(preco, estacaoID, servicosID) VALUES('4.95€', 3, 1)";
        String SQL_Est_Serv8 = "INSERT INTO est_serv(preco, estacaoID, servicosID) VALUES('5.95€', 3, 2)";
        String SQL_Est_Serv9 = "INSERT INTO est_serv(preco, estacaoID, servicosID) VALUES('7.45€', 3, 3)";

        String SQL_Est_Serv10 = "INSERT INTO est_serv(preco, estacaoID, servicosID) VALUES('4.60€', 4, 1)";
        String SQL_Est_Serv11 = "INSERT INTO est_serv(preco, estacaoID, servicosID) VALUES('7.60€', 4, 2)";
        String SQL_Est_Serv12 = "INSERT INTO est_serv(preco, estacaoID, servicosID) VALUES('8.60€', 4, 3)";

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

    }
}