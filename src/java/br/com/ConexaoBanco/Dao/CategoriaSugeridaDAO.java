/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBanco.Dao;

import br.com.ConexaoBanco.ConexaoMySQL;
import br.com.ConexaoBanco.Entidades.Aluno;
import br.com.ConexaoBanco.Entidades.CategoriaSugerida;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rodolfo
 */
public class CategoriaSugeridaDAO {

    /*
    *Metodo utilizado pelo @POST para cadastrar um sugestao de categoria
    *@return boolean
     */
    public boolean cadastrarSugestao(CategoriaSugerida catS) {
        boolean ret = false;
        ConexaoMySQL c = new ConexaoMySQL();
        String sql = "INSERT INTO categoriasugerida (categoriaId,turmaId) VALUES ( ?, ? )";
        System.out.println("1");
        PreparedStatement pst = c.getPreparedStatement(sql);
        try {
            pst.setInt(1, catS.getCategoriaId());
            pst.setInt(2, catS.getTurmaId());

            if (pst.executeUpdate() > 0) {
                ret = true;
                System.out.println("***Inserido***");
            } else {
                System.out.println("***else***");
            }
        } catch (SQLException ex) {
            System.out.println("catch categ sugeridaDAO\n" + ex.toString());
            ret = false;
        }

        return ret;
    }

    public CategoriaSugerida getSugestao(Aluno aluno) {
        ConexaoMySQL c = new ConexaoMySQL();
        CategoriaSugerida cat = new CategoriaSugerida();
        String sql = "SELECT turmaId FROM aluno WHERE alunoid = ?";

        PreparedStatement pst = c.getPreparedStatement(sql);
        try {
            pst.setInt(1, aluno.getAlunoid());
            ResultSet res = pst.executeQuery();
            if(res.next())
            cat.setTurmaId(res.getInt("turmaId"));

        } catch (SQLException ex) {
            System.out.println("Catch getSugestao\n" + ex.toString());
        }
        sql = "SELECT * FROM categoriaSugerida WHERE turmaId = ? ORDER BY dataCadastro DESC LIMIT 1";
        pst = c.getPreparedStatement(sql);
        try{
            pst.setInt(1, cat.getTurmaId());
            ResultSet res = pst.executeQuery();
            if(res.next()){
            cat.setCategoriaId(res.getInt("categoriaId"));
            cat.setId(res.getInt("id"));
            }
        }catch(SQLException ec){
            System.out.println(" Segundo Catch getSugestao\n" + ec.toString());
        }

        return cat;
    }
}
