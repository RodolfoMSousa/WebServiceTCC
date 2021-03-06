/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBanco.Requisicoes;

import br.com.ConexaoBanco.ConexaoMySQL;
import br.com.ConexaoBanco.Dao.AlunoDAO;
import br.com.ConexaoBanco.Dao.UsuarioDAO;
import br.com.ConexaoBanco.Entidades.Aluno;
import br.com.ConexaoBanco.Entidades.Usuario;
import com.google.gson.Gson;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service Retorna o Usuário
 *
 * @author Rodolfo
 */
@Path("userWS/")
public class UsuarioWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TCCWS
     */
    public UsuarioWS() {
    }

    /**
     * Retorna o status da conexão
     *
     * @return Status
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String statusConexao() {

        return ConexaoMySQL.getStatus();
    }

    /* 
    * Retorna o usuário 
    *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Response getUser(@PathParam("id") int usuarioId) throws SQLException, InstantiationException, IllegalAccessException {
        Usuario u = new Usuario();
        UsuarioDAO p = new UsuarioDAO();
        u.setUserId(usuarioId);
        u = p.select(u);
        Gson gson = new Gson();
        return Response
                .ok(gson.toJson(u))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }
    
    
    
    
    /**
     * POST para inserir usuario
     *Exemplo Json{"nome":"Maria","sobrenome":"Carvalho","cpf":555555,
      "dataNascimento":20100210,"ativo":1}
      * @return Exemplo de json do tipo aluno que sera retornado
      * {"alunoid":9,"usuarioId":13,"professorId":0,"scoreTotal":0,"turmaId":0
      * ,"userId":13,"cpf":0,"dataNascimento":0,"ativo":0,"dataCadastro":2018}
     */
    @POST
    @Path("/setAluno/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJson( String content) throws InstantiationException, IllegalAccessException {
        System.out.println("1");
        boolean r = false;
        Gson gson = new Gson();
        Usuario u = gson.fromJson(content, Usuario.class);
        UsuarioDAO d = new UsuarioDAO();
        r = d.insert(u);
        Aluno a = new Aluno();
        if(r){
            
            AlunoDAO aDAO = new AlunoDAO();
            u = d.selectLast();
            a = aDAO.insert(u.getUserId());
            r = false;
        }
        System.out.println("final");
        return Response
                .ok(gson.toJson(a))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();

    }
}
