/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBanco.Requisicoes;

import br.com.ConexaoBanco.ConexaoMySQL;
import br.com.ConexaoBanco.Dao.ScoreDAO;
import br.com.ConexaoBanco.Entidades.Score;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * Web Service para busca dos scores
 * @author Rodolfo
 */

@Path("scorews/")
public class ScoreWS {
    
    public ScoreWS(){
        
    }
    
    /**
     * Retorna o status da conexão
     * @return Status 
     */
//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    public String statusConexao() {
//
//        return ConexaoMySQL.getStatus();
//    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void setScore(String content){
        Gson gson = new Gson();
        Score s = gson.fromJson(content, Score.class);
        ScoreDAO dao = new ScoreDAO();
        dao.setScore(s);
    }
    
    
    @GET
    @Path("/{alunoId}/{categoriaId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getScoreStatus(@PathParam("alunoId") int alunoId, 
            @PathParam("categoriaId") int categoriaId) {
        List<Score> score = new ArrayList<Score>();
        Score s = new Score();
        Gson gson = new Gson();
        ScoreDAO dao = new ScoreDAO();
        s.setAlunoId(alunoId);
        s.setCategoriaId(categoriaId);
        score = dao.getScore(s);
        
        return gson.toJson(score);
    }
    
    
}
