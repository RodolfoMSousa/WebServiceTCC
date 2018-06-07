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
import java.util.Iterator;
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
        } catch (SQLException ex) {
            System.out.println("catch Score dao\n" + ex.toString());
        }

        return ret;
    }

    public Boolean setScore(Score s) {
        ConexaoMySQL c = new ConexaoMySQL();
        Boolean ret = false;
        String sql = "INSERT INTO score (jogoId,alunoId,pontuacao,categoriaId) VALUES (?,?,?,?);";
        PreparedStatement pst = c.getPreparedStatement(sql);
        try {
            pst.setInt(1, s.getJogoId());
            pst.setInt(2, s.getAlunoId());
            pst.setInt(3, s.getPontuacao());
            pst.setInt(4, s.getCategoriaId());

            if (pst.executeUpdate() > 0) {
                ret = true;
                System.out.println("***Inserido***");
            } else {
                System.out.println("***else***");
            }
        } catch (SQLException ex) {
            System.out.println("catch ScoreDAO\n" + ex.toString());
            ret = false;
        }
        return ret;
    }

    public List<Score> setScoreList(List<Score> s) {
        ConexaoMySQL c = new ConexaoMySQL();
        List<Score> ret = new ArrayList<>(); 
        Iterator<Score> iterator = s.iterator();
        int aux = 0;
        while (iterator.hasNext()) {
            String sql = "INSERT INTO score (jogoId,alunoId,pontuacao,categoriaId) VALUES (?,?,?,?);";
            PreparedStatement pst = c.getPreparedStatement(sql);
            try {
                pst.setInt(1, s.get(aux).getJogoId());
                pst.setInt(2, s.get(aux).getAlunoId());
                pst.setInt(3, s.get(aux).getPontuacao());
                pst.setInt(4, s.get(aux).getCategoriaId());

                if (pst.executeUpdate() > 0) {
                    ret.add(s.get(aux));
                    aux++;
                    iterator.next();
                    System.out.println("***Inserido***");
                    
                } else {
                    System.out.println("***else***");
                }
            } catch (SQLException ex) {
                System.out.println("catch ScoreDAO\n" + ex.toString());
            }
        }
        return ret;
    }
}
