/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

//Início da classe de conexão//
public class ConexaoMySQL {

    private String DRIVER = "com.mysql.jdbc.Driver";
    private String BD = "tccdb";
    private String URL = "jdbc:mysql://localhost:3306/" + BD;
    private String USERNAME = "root";
    private String PASSWORD = "root";
    private Connection conexao = null;
    private Statement stm;
    private String msg;
    public static String status = "não conectado";

    public ConexaoMySQL() {
        iniciaConexao();

    }

    public ConexaoMySQL(String bd, String user, String senha) {
        this.BD = bd;
        this.USERNAME = user;
        this.PASSWORD = senha;
        iniciaConexao();
    }

    public Connection iniciaConexao() {
        try {
            Class.forName(this.DRIVER);
            this.conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // Definimos o objeto responsÃ¡vel por executar os comandos
            this.stm = this.getConexao().createStatement();
            status = "conectado com sucesso ";
            return conexao;

        } catch (ClassNotFoundException e) {
            this.conexao = null;
            status = "Não foi possivel encontrar o driver de banco: " + e.getMessage();
            return conexao;
        } catch (SQLException e) {
            status = "SQLException Erro!" + e.getMessage();
            this.conexao = null;
            return conexao;
        }
    }

    public String fechaConexao() {
        try {
            if (this.getConexao() != null) {
                this.getConexao().close();
                this.conexao = null;
            }
            if (this.getStm() != null) {
                this.stm = null;
            }
            status = "Conexão Encerrada";
            return status;
        } catch (SQLException ex) {
            status = "Houve erro no fechamento da conexão! " + ex.getMessage();
            return status;
        }
    }

    
    //metodo que recebe um comando sql
    public PreparedStatement getPreparedStatement(String sql){
                 
        // testo se a conexão já foi criada
        if (conexao == null){
            // cria a conexão
            conexao = iniciaConexao();
        }
        try {
            // retorna um objeto java.sql.PreparedStatement
            return conexao.prepareStatement(sql);
        } catch (SQLException e){
            System.out.println("Erro de sql: "+
                    e.getMessage());
        }
        return null;
    }
    
    public Connection getConexao() {
        return conexao;
    }

    public Statement getStm() {
        return stm;
    }

    public String getMsg() {
        return msg;
    }

    public static String getStatus() {
        return status;
    }

}
