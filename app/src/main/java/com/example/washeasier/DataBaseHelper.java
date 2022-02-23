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

        String SQL_Estacao = "CREATE TABLE estacao(estacaoID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "nomeEmpresa VARCHAR(45) NOT NULL, " +
                "horarioServico VARCHAR(45) NOT NULL, " +
                "estLat DECIMAL(10,8) NOT NULL, " +
                "estLong DECIMAL(10,8) NOT NULL) ";

        String SQL_Servicos = "CREATE TABLE servicos(servicosID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, descricao VARCHAR(45) NOT NULL)";

        sqLiteDatabase.execSQL(SQL_Estacao);
        sqLiteDatabase.execSQL(SQL_Servicos);

    }

    //É chamado quando a db é alterada
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
/*
CREATE TABLE IF NOT EXISTS `mydb`.`servicos` (
  `servicosID` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`servicosID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`est_serv`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`est_serv` (
  `estacaoID` INT NOT NULL,
  `servicosID` INT NOT NULL,
  `preco` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`estacaoID`, `servicosID`),
  INDEX `fk_est_serv_estacao_idx` (`estacaoID` ASC) VISIBLE,
  INDEX `fk_est_serv_servicos1_idx` (`servicosID` ASC) VISIBLE,
  CONSTRAINT `fk_est_serv_estacao`
    FOREIGN KEY (`estacaoID`)
    REFERENCES `mydb`.`estacao` (`estacaoID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_est_serv_servicos1`
    FOREIGN KEY (`servicosID`)
    REFERENCES `mydb`.`servicos` (`servicosID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;*/