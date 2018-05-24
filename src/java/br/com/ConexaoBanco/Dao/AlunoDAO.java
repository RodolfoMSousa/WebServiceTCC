/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBanco.Dao;

import br.com.ConexaoBanco.ConexaoMySQL;
import br.com.ConexaoBanco.Entidades.Aluno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author Rodolfo
 */
public class AlunoDAO {
    public Aluno select(Aluno a) {
        Aluno ret = null;
        ConexaoMySQL con = new ConexaoMySQL();
        String sql = "SELECT * FROM aluno WHERE alunoid=?";

        PreparedStatement pst = con.getPreparedStatement(sql);
        try {
            pst.setInt(1, a.getAlunoid());
            ResultSet res = pst.executeQuery();

            if (res.next()) {
                ret = new Aluno();
                ret.setUsuarioId(res.getInt("usuarioId"));
                ret.setAlunoid(res.getInt("alunoid"));
                ret.setScoreTotal(res.getInt("scoreTotal"));
                ret.setAtivo(res.getInt("ativo"));
                ret.setDataCadastro(res.getInt("dataCadastro"));
                ret.setTurmaId(res.getInt("turmaId"));
                ret.setProfessorId(res.getInt("professorId"));
            }else{
                System.out.println("**Usuario não localizado** id = "+a.getUsuarioId());
            }
        } catch (SQLException ex) {
            Logger.getLogger("erro ao buscar");
        }
        
        return ret;
    }
    
    public boolean insert(int usuarioId) {
        ConexaoMySQL c = new ConexaoMySQL();
        Boolean ret = false;
        String sql = "INSERT INTO aluno (usuarioId) VALUES (?)";
        PreparedStatement pst = c.getPreparedStatement(sql);
        try {
            pst.setInt(1, usuarioId);

            if (pst.executeUpdate() > 0) {
                ret = true;
                System.out.println("***Inserido***"); 
           } else{
                System.out.println("entrou");
            }
        } catch (SQLException ex) {
            System.out.println("catch alunoDAO\n" + ex.toString());
            ret = false;
        }
        return ret;
    }
}
