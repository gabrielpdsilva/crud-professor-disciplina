package application.persistence;

import application.model.Professor;

import java.sql.SQLException;
import java.util.List;

public interface IProfessorDao {
    public void inserirProfessor(Professor professor) throws SQLException;
    public void excluirProfessor(Professor professor) throws SQLException;
    public void atualizarProfessor(Professor professor) throws SQLException;
    public Professor buscarProfessor(Professor professor) throws SQLException;
    public List<Professor> buscarProfessores() throws SQLException;
}
