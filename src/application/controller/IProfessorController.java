package application.controller;

import application.model.Professor;

import java.sql.SQLException;

public interface IProfessorController {
    public void inserirProfessor(Professor professor) throws ClassNotFoundException, SQLException;
    public void atualizarProfessor(Professor professor) throws ClassNotFoundException, SQLException;
    public void excluirProfessor(Professor professor) throws ClassNotFoundException, SQLException;
    public void buscarProfessor(Professor professor) throws ClassNotFoundException, SQLException;
    public void buscarProfessores() throws ClassNotFoundException, SQLException;
}
