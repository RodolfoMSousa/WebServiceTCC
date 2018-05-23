/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBanco.Requisicoes;

import br.com.ConexaoBanco.ConexaoMySQL;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
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
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getScoreStatus() {
        return ConexaoMySQL.getStatus();
    }
}
