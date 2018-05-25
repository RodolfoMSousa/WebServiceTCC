/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBanco.Requisicoes;

import br.com.ConexaoBanco.ConexaoMySQL;
import br.com.ConexaoBanco.Dao.LoginDAO;
import br.com.ConexaoBanco.Entidades.Login;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Web Service para busca dos scores
 * @author Rodolfo
 */

@Path("login/")
public class LoginWS {
    
    public LoginWS(){
        
    }
    
//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    public String statusConexao() {
//
//        return ConexaoMySQL.getStatus();
//    }
    
    
/*
    * POST para cadastrar novo login lembrar de passar o usuarioId no Json
    * 
    */
    
    @POST
 //   @Path("setLogin/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void login(String content){
        Gson gson = new Gson();
        Login login = gson.fromJson(content, Login.class);
      //Assumindo que o UsuarioId já foi enviado no Json
        LoginDAO dao = new LoginDAO();
        dao.setLogin(login);
        
    }
    
    @GET
 //   @Path("getLogin/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticar(@QueryParam("usuarioId") int usuarioId,
            @QueryParam("userName") String userName,
            @QueryParam("senha") String senha){
        Login login = new Login();
        login.setUsuarioId(usuarioId);
        login.setUserName(userName);
        login.setSenha(senha);
        LoginDAO dao = new LoginDAO();
        boolean status = dao.getLogin(login);
        Gson gson = new Gson();
        
        return Response
                .ok(gson.toJson(status))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }
    
}
