/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criadores;
/**
 *
 *
 */
public class Aluno extends Pessoa {
    private String matricula_ra;
    private String turma;

    public Aluno(String nome, String sobrenome, String matricula_ra, String turma) {
        super(nome, sobrenome);
        this.matricula_ra = matricula_ra;
        this.turma = turma;
    }

           
    
}
