/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBanco.Requisicoes;

import br.com.ConexaoBanco.Dao.ScoreDAO;
import br.com.ConexaoBanco.Entidades.Score;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

/**
 * Web Service para busca dos scores
 *
 * @author Rodolfo
 */
@Path("scorews/")
public class ScoreWS {

    public ScoreWS() {

    }

    
    /*
    * Modelo do json de entrada
    *[{"jogoId":1,"alunoId":3,"pontuacao":90,"categoriaId":1},
    {"jogoId":1,"alunoId":3,"pontuacao":75,"categoriaId":1},
    {"jogoId":1,"alunoId":3,"pontuacao":200,"categoriaId":1}]
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setScore(String content) {
        Gson gson = new Gson();
        //typetoten necessário para desserializar a lista de score recebida via Json
        Type listType = new TypeToken<ArrayList<Score>>() {}.getType();
        List<Score> scoreList = gson.fromJson(content, listType);
       
        ScoreDAO dao = new ScoreDAO();
        scoreList = dao.setScoreList(scoreList);

        return Response
                .ok(gson.toJson(scoreList))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }

//    /*
//    * Modelo do json de entrada
//    *{"jogoId" : 1, "alunoId": 3, "pontuacao": 200, "categoriaId": 1}
//    */
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response setScore(String content){
//        Gson gson = new Gson();
//        Score s = gson.fromJson(content, Score.class);
//        ScoreDAO dao = new ScoreDAO();
//        dao.setScore(s);
//        
//        return Response
//                .ok(gson.toJson(s))
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Credentials", "true")
//                .header("Access-Control-Allow-Headers",
//                        "origin, content-type, accept, authorization")
//                .header("Access-Control-Allow-Methods",
//                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//                .build();
//    }
    @GET
    @Path("/{alunoId}/{categoriaId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScoreStatus(@PathParam("alunoId") int alunoId,
            @PathParam("categoriaId") int categoriaId) {
        List<Score> score = new ArrayList<Score>();
        Score s = new Score();
        Gson gson = new Gson();
        ScoreDAO dao = new ScoreDAO();
        s.setAlunoId(alunoId);
        s.setCategoriaId(categoriaId);
        score = dao.getScore(s);

        return Response
                .ok(gson.toJson(score))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }

}
