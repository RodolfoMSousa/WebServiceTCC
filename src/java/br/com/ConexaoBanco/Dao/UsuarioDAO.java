/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBanco.Dao;

import br.com.ConexaoBanco.ConexaoMySQL;
import br.com.ConexaoBanco.Entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Rodolfo
 */
public class UsuarioDAO {

    public boolean insert(Usuario user) throws InstantiationException, IllegalAccessException {
        ConexaoMySQL c = new ConexaoMySQL();
        Boolean ret = false;
        String sql = "INSERT INTO usuario (nome, sobrenome, cpf,dataNascimento,ativo) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = c.getPreparedStatement(sql);
        try {
            
            pst.setString(1, user.getNome());
            pst.setString(2, user.getSobrenome());
            pst.setInt(3, user.getCpf());
            pst.setInt(4, user.getDataNascimento());
            pst.setInt(5, user.getAtivo());
           // pst.setInt(6, user.getDataCadastro());

            if (pst.executeUpdate() > 0) {
                ret = true;
                System.out.println("***Inserido***"); 
           } else{
                System.out.println("entrou");
            }
        } catch (SQLException ex) {
            System.out.println("catch usuario dao\n" + ex.toString());
            ret = false;
        }
        return ret;
    }

    /*
    *Select com todos os usuarios
    *@return List<Usuario>
     */
    public List<Usuario> select() {
        ConexaoMySQL c = new ConexaoMySQL();
        String sql = "SELECT * FROM usuario";
        List<Usuario> ret = new ArrayList<>();
        PreparedStatement pst = c.getPreparedStatement(sql);
        try {
            ResultSet res = pst.executeQuery(sql);
            while (res.next()) {
                Usuario item = new Usuario();
                item.setNome(res.getString("nome"));
                item.setSobrenome(res.getString("sobrenome"));
                item.setCpf(res.getInt("cpf"));
                item.setDataNascimento(res.getInt("dataNascimento"));
                item.setAtivo(res.getInt("ativo"));
                item.setDataCadastro(res.getInt("dataCadastro"));

                ret.add(item);
            }
        } catch (SQLException e) {
            Logger.getLogger("erro ao buscar");
        }
        return ret;
    }

    /*
    *Select um usuario especifico
    * @return Usuario
     */
    public Usuario select(Usuario user) {
        Usuario ret = null;
        ConexaoMySQL con = new ConexaoMySQL();
        String sql = "SELECT * FROM usuario WHERE usuarioId=?";

        PreparedStatement pst = con.getPreparedStatement(sql);
        try {
            pst.setInt(1, user.getUsuarioId());
            ResultSet res = pst.executeQuery();

            if (res.next()) {
                ret = new Usuario();
                ret.setUsuarioId(res.getInt("usuarioId"));
                ret.setNome(res.getString("nome"));
                ret.setSobrenome(res.getString("sobrenome"));
                ret.setCpf(res.getInt("cpf"));
                ret.setDataNascimento(res.getInt("dataNascimento"));
                ret.setAtivo(res.getInt("ativo"));
                ret.setDataCadastro(res.getInt("dataCadastro"));
            }else{
                System.out.println("**Usuario não localizado** id = "+user.getUsuarioId());
            }
        } catch (SQLException ex) {
            Logger.getLogger("erro ao buscar");
        }
        
        return ret;
    }
    
    public Usuario selectLast() {
        Usuario ret = null;
        ConexaoMySQL con = new ConexaoMySQL();
        String sql = "SELECT * FROM usuario ORDER BY usuarioId DESC LIMIT 1";

        PreparedStatement pst = con.getPreparedStatement(sql);
        try {
            ResultSet res = pst.executeQuery();

            if (res.next()) {
                ret = new Usuario();
                ret.setUsuarioId(res.getInt("usuarioId"));
                ret.setNome(res.getString("nome"));
                ret.setSobrenome(res.getString("sobrenome"));
                ret.setCpf(res.getInt("cpf"));
                ret.setDataNascimento(res.getInt("dataNascimento"));
                ret.setAtivo(res.getInt("ativo"));
                ret.setDataCadastro(res.getInt("dataCadastro"));
            }else{
                System.out.println("**Usuario não localizado** ");
            }
        } catch (SQLException ex) {
            Logger.getLogger("erro ao buscar");
        }
        
        return ret;
    }

}
