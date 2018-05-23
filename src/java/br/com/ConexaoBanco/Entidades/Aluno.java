/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBanco.Entidades;

/**
 *
 * @author Rodolfo
 */
public class Aluno extends Professor{
    private int alunoid, scoreTotal, turmaId;
    
    public Aluno(){
        
    }
    public Aluno(int usuarioId, String nome,String sobrenome, int cpf,
            int dataNascimento, int ativo, int dataCadastro, int professorId, 
            int turmaId, int alunoid, int scoreTotal){
        super(usuarioId, nome, sobrenome, cpf, dataNascimento, ativo, dataCadastro, professorId, turmaId);
        this.alunoid = alunoid;
        this.scoreTotal = scoreTotal;
        this.turmaId = turmaId;
    }

    /**
     * @return the alunoid
     */
    public int getAlunoid() {
        return alunoid;
    }

    /**
     * @param alunoid the alunoid to set
     */
    public void setAlunoid(int alunoid) {
        this.alunoid = alunoid;
    }

    /**
     * @return the scoreTotal
     */
    public int getScoreTotal() {
        return scoreTotal;
    }

    /**
     * @param scoreTotal the scoreTotal to set
     */
    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    /**
     * @return the turmaId
     */
    public int getTurmaId() {
        return turmaId;
    }

    /**
     * @param turmaId the turmaId to set
     */
    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }
}
