package application.persistence;

import application.model.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDao implements IProfessorDao {
    private Connection c;
    public ProfessorDao() throws ClassNotFoundException, SQLException {
        GenericDao gDao = new GenericDao();
        c = gDao.getConnection();
    }

    @Override
    public void inserirProfessor(Professor professor) throws SQLException {
        String sql = "INSERT INTO professor VALUES (?,?,?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, professor.getCodigo());
        ps.setString(2, professor.getNome());
        ps.setString(3, professor.getTitulacao());
        ps.execute();
        ps.close();
    }

    @Override
    public void excluirProfessor(Professor professor) throws SQLException {
        String sql = "DELETE professor WHERE codigo = ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, professor.getCodigo());
        ps.execute();
        ps.close();
    }

    @Override
    public void atualizarProfessor(Professor professor) throws SQLException {
        String sql = "UPDATE professor SET nome = ?, titulacao = ? WHERE codigo = ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, professor.getNome());
        ps.setString(2, professor.getTitulacao());
        ps.setInt(3, professor.getCodigo());
        ps.execute();
        ps.close();
    }

    @Override
    public Professor buscarProfessor(Professor professor) throws SQLException {
        String sql = "SELECT codigo, nome, titulacao FROM professor WHERE codigo = ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, professor.getCodigo());
        int cont = 0;
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            professor.setNome(rs.getString("nome"));
            professor.setTitulacao(rs.getString("titulacao"));
            cont++;
        }

        // pra ajudar a limpar a tela,
        // nao acumular valor
        if(cont == 0) {
            professor = new Professor();
        }

        rs.close();
        ps.close();
        return professor;
    }

    @Override
    public List<Professor> buscarProfessores() throws SQLException {
        String sql = "SELECT codigo, nome, titulacao FROM professor";
        PreparedStatement ps = c.prepareStatement(sql);
        int cont = 0;
        ResultSet rs = ps.executeQuery();
        List<Professor> listaProfessores = new ArrayList<Professor>();
        while(rs.next()) {
            Professor professor = new Professor();
            professor.setCodigo(rs.getInt("codigo"));
            professor.setNome(rs.getString("nome"));
            professor.setTitulacao(rs.getString("titulacao"));
            listaProfessores.add(professor);
        }
        return listaProfessores;
    }
}
