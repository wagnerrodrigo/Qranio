package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

/**
 * Created by wagner on 20/09/2017.
 */

public class UsusarioDAO {
    private BancoD bancoD;
    private SQLiteDatabase database;

    public UsusarioDAO(Context context){
        bancoD = new BancoD(context);
    }

    private  SQLiteDatabase getDatabase(){
        if (database == null){
            database = bancoD.getWritableDatabase();
        }
        return database;
    }

    private Usuario criarUsuario(Cursor cursor){
        Usuario model = new Usuario(
          cursor.getInt(cursor.getColumnIndex(BancoD.Usuarios._ID)),
          cursor.getInt(cursor.getColumnIndex(BancoD.Usuarios.NOME)),
          cursor.getInt(cursor.getColumnIndex(BancoD.Usuarios.LOGIN)),
          cursor.getInt(cursor.getColumnIndex(BancoD.Usuarios.SENHA)),
        );
        return model;
    }
    public List<Usuario> listarUsuarios(){
        Cursor cursor =  getDatabase().query(BancoD.Usuarios.TABELA,
                    BancoD.Usuarios.COLUNAS, null, null, null, null, null);

        List<Usuario> usuarios = new ArrayList<Usuario>();
        while(cursor.moveToNext()){
            Usuario model = criarUsuario(cursor);
            usuarios.add(model);
        }
        cursor.close();
        return usuarios;
    }

    public long salvarUsuario(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put(BancoD.Usuarios.NOME , usuario.getNome());
        valores.put(BancoD.Usuarios.LOGIN , usuario.getLogin());
        valores.put(BancoD.Usuarios.SENHA , usuario.getSenha());

        if (usuario.get_id() != null){
            return getDatabase().update(BancoD.Usuarios.TABELA, valores, "_id = ?",
                    new String[]{ usuario.get_id().toString() });

        }
        return getDatabase().insert(BancoD.Usuarios.TABELA, null, valores);
      }
      public boolean removerUsuario(int id){
          return getDatabase().delete(BancoD.Usuarios.TABELA,
                  "_id = ?", new String[]{Integer.toString(id) }) > 0;
      }

      public Usuario buscarUsuarioPorId(int id){
          Cursor cursor = getDatabase().query(BancoD.Usuarios.TABELA,
                  BancoD.Usuarios.COLUNAS, "_id = ?", new String[]{ Integer.toString(id) }, null, null, null);
          if (cursor.moveToNext()){
              Usuario model = criarUsuario(cursor);
              cursor.close();
              return  model
          }
          return null;
      }



    public void fecharDB(){
        bancoD.close();
        database = null;
    }
}
