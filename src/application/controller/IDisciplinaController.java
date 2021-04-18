package application.controller;

import application.model.Disciplina;
import application.model.Professor;

import java.sql.SQLException;

public interface IDisciplinaController {
    public void inserirDisciplina(Disciplina disciplina) throws ClassNotFoundException, SQLException;
    public void atualizarDisciplina(Disciplina disciplina) throws ClassNotFoundException, SQLException;
    public void excluirDisciplina(Disciplina disciplina) throws ClassNotFoundException, SQLException;
    public void buscarDisciplna(Disciplina disciplina) throws ClassNotFoundException, SQLException;
    public void buscarDisciplinas() throws ClassNotFoundException, SQLException;
}
