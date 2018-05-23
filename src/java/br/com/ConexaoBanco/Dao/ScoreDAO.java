/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBanco.Dao;

import br.com.ConexaoBanco.ConexaoMySQL;
import br.com.ConexaoBanco.Entidades.Score;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodolfo
 */
public class ScoreDAO {

    public List<Score> getScore(Score s) {
        ConexaoMySQL c = new ConexaoMySQL();
        List<Score> ret = new ArrayList<>();
        String sql = "SELECT * FROM score WHERE alunoId = ? AND categoriaId = ?";
        PreparedStatement pst = c.getPreparedStatement(sql);
        try {
            pst.setInt(1, s.getAlunoId());
            pst.setInt(2, s.getCategoriaId());
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Score score = new Score();
                score.setScoreId(res.getInt("scoreId"));
                score.setJogoId(res.getInt("jogoId"));
                score.setAlunoId(res.getInt("alunoId"));
                score.setPontuacao(res.getInt("pontuacao"));
                score.setCategoriaId(res.getInt("categoriaId"));
                
                ret.add(score);
            }
        }catch(SQLException ex){
            System.out.println("catch Score dao\n" + ex.toString());
        }

        return ret;
    }

}
