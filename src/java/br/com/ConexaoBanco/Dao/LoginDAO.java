/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBanco.Dao;

import br.com.ConexaoBanco.ConexaoMySQL;
import br.com.ConexaoBanco.Entidades.Login;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rodolfo
 */
public class LoginDAO {
    
    public LoginDAO(){
        
    }
    
    
    public boolean setLogin(Login login){
        ConexaoMySQL c = new ConexaoMySQL();
        boolean ret = false;
        String sql = "INSERT INTO login (usuarioId, userName, senha) VALUES (?,?,?)";
        PreparedStatement pst = c.getPreparedStatement(sql);
        try{
            pst.setInt(1, login.getUsuarioId());
            pst.setString(2, login.getUserName());
            pst.setString(3, login.getSenha());
            
            if(pst.executeUpdate() > 0){
                ret = true;
                System.out.println("***Inserido***"); 
           } else{
                System.out.println("entrou no else");
            }
        }catch(SQLException ex){
            System.out.println("catch loginDAO\n" + ex.toString());
            ret = false;
        }
        
        return ret;
    }
    
    public Boolean getLogin(Login login){
        ConexaoMySQL c = new ConexaoMySQL();
        Login ret = null;
        boolean status = false;
        String sql = "select * from login where usuarioId = ?";
        PreparedStatement pst = c.getPreparedStatement(sql);
        
        try{
            pst.setInt(1, login.getUsuarioId());
            ResultSet res = pst.executeQuery();
            
            if (res.next()) {
                ret = new Login();
                ret.setUsuarioId(res.getInt("usuarioId"));
                ret.setSenha(res.getString("senha"));
                ret.setUserName(res.getString("userName"));
                ret.setDataLogin(res.getInt("dataLogin"));
            }else{
                System.out.println("**Usuario não localizado** id = "+login.getUsuarioId());
            }
            if(ret.getUserName().equals(login.getUserName())&&ret.getSenha().equals(login.getSenha())){
                status = true;
            }else{
                System.out.println("user enviado: "+login.getUserName()+" user encontrado: "+ ret.getUserName());
                System.out.println("senha enviado: "+login.getSenha() + " senha encontrado: "+ ret.getSenha());
            }
        }catch(SQLException ex){
            System.out.println("catch loginDAO\n" + ex.toString());
        }
        
        return status;
    }
    
}
