package dao;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.string.no;

/**
 * Created by wagner on 20/09/2017.
 */

public class BancoD extends SQLiteOpenHelper {

    private static final String MEU_BANCO = "banco.db";
    private static final int VERSAO = 1;

    // construtor
    public BancoD(Context context) {
        super(context,MEU_BANCO,null,VERSAO);
    }

    public static class Usuarios{
        private static final String  TABELA = "usuario";
        private static final String _ID = "_id";
        private static final String NOME = "nome";
        private static final String LOGIN = "Login";
        private static final String SENHA = "senha";

        public static final String[] COLUNAS = new String[]{
                _ID, NOME, LOGIN, SENHA
        };

        public static class Tarefa{
            public static final String TABELA = "Tarefa";
            public static final String _ID = "_id";
            public static final String TAREFA = "Tarefa";
            public static final String DT_CRIACAO = "dt_criacao";
            public static final String DT_COMPLETADO = "dt_completado";

            public static final String[] COLUNAS = new String[]{
                  _ID,TAREFA,DT_CRIACAO,DT_COMPLETADO
            };
        }



    }


    @Override
    public void onCreate(SQLiteDatabase db){
        // Tabela de usuario
        db.execSQL("create table usuarios(_id integer primary key autoincrement, "+ "nome text not null , login text not null , senha text not null)" );

        // Tabela de Tarefa
        db.execSQL("create table tarefas(_id integer primary key autoincrement, "
                +"util Tarefa text not null, dt_criacao datetime default current_datetime, dt_completado date, ) ");
        //Cadastro ususario
        db.execSQL("insert into usuarios(nome,login,senha) values('Admin','admin','123')");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
