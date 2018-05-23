/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBanco.Requisicoes;

import br.com.ConexaoBanco.ConexaoMySQL;
import br.com.ConexaoBanco.Dao.UsuarioDAO;
import br.com.ConexaoBanco.Entidades.Usuario;
import com.google.gson.Gson;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
//import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

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
    @Path("/getUsuario/{id}")
    public String getUser(@PathParam("id") int usuarioId) throws SQLException, InstantiationException, IllegalAccessException {
        Usuario u = new Usuario();
        UsuarioDAO p = new UsuarioDAO();
        u.setUsuarioId(usuarioId);
        u = p.select(u);
        Gson gson = new Gson();
        return gson.toJson(u);
    }

    /**
     * POST para inserir usuario
     *
     */
    @POST
    @Path("/setUsuario/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void putJson(String content) throws InstantiationException, IllegalAccessException {
        //Usuario u = new Usuario(3, "Marcos", "Benevides", 111111, 222, 333, 444);
        Gson gson = new Gson();
        Usuario u = gson.fromJson(content, Usuario.class);
        UsuarioDAO d = new UsuarioDAO();
        d.insert(u);

    }
}
