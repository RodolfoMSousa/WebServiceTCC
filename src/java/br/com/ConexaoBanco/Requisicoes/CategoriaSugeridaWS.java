/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBanco.Requisicoes;

import br.com.ConexaoBanco.Dao.CategoriaSugeridaDAO;
import br.com.ConexaoBanco.Entidades.Aluno;
import br.com.ConexaoBanco.Entidades.CategoriaSugerida;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Rodolfo
 */
@Path("/catsugws/")
public class CategoriaSugeridaWS {
    
    public CategoriaSugeridaWS (){
        
    }
    /*
    * metodo GET que recebe o ID do aluno via url
    *@return Json categoria sugerida
    */
    
    @GET
    @Path("{alunoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSugetao(@PathParam("alunoId") int alunoId) {
        Gson gson = new Gson();
        Aluno a = new Aluno();
        a.setAlunoid(alunoId);
        CategoriaSugeridaDAO dao = new CategoriaSugeridaDAO();
        CategoriaSugerida cat = new CategoriaSugerida();
        cat = dao.getSugestao(a);
        
        return Response
                .ok(gson.toJson(cat))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }
    
    
    /*
    * metodo POST que recebe o JSON da categoria sugerida
    *@return Json categoria sugerida
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void cadastraSugestao(String content)  {
        Gson gson = new Gson();
        CategoriaSugerida catSug = new CategoriaSugerida();
        CategoriaSugeridaDAO dao = new CategoriaSugeridaDAO();
        catSug = gson.fromJson(content, CategoriaSugerida.class);
        dao.cadastrarSugestao(catSug);

    }
}

