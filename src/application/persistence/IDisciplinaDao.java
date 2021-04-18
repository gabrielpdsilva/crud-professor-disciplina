package application.persistence;

import application.model.Disciplina;

import java.sql.SQLException;
import java.util.List;

public interface IDisciplinaDao {
    public void inserirDisciplina(Disciplina disciplina) throws SQLException;
    public void atualizarDisciplina(Disciplina disciplina) throws SQLException;
    public void excluirDisciplina(Disciplina disciplina) throws SQLException;
    public Disciplina buscarDisciplina(Disciplina d) throws SQLException;
    public List<Disciplina> buscarDisciplinas() throws SQLException;
}
