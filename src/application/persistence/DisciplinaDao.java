package application.persistence;

import application.model.Disciplina;
import application.model.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDao implements IDisciplinaDao {

    private Connection c;

    public DisciplinaDao() throws ClassNotFoundException, SQLException {
        GenericDao gDao = new GenericDao();
        c = gDao.getConnection();
    }

    @Override
    public void inserirDisciplina(Disciplina disciplina) throws SQLException {
        String sql = "INSERT INTO disciplina VALUES (?,?,?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, disciplina.getCodigo());
        ps.setString(2, disciplina.getNome());
        ps.setInt(3, disciplina.getProfessor().getCodigo());
        ps.execute();
        ps.close();
    }

    @Override
    public void atualizarDisciplina(Disciplina disciplina) throws SQLException {
        String sql = "UPDATE disciplina SET nome = ?, codigoProfessor = ? WHERE codigo = ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, disciplina.getNome());
        ps.setInt(2, disciplina.getProfessor().getCodigo());
        ps.setInt(3, disciplina.getCodigo());
        ps.execute();
        ps.close();
    }

    @Override
    public void excluirDisciplina(Disciplina disciplina) throws SQLException {
        String sql = "DELETE disciplina WHERE codigo = ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, disciplina.getCodigo());
        ps.execute();
        ps.close();
    }

    @Override
    public Disciplina buscarDisciplina(Disciplina d) throws SQLException {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT d.codigo AS codigoDisciplina, d.nome AS nomeDisciplina, ");
        sql.append("p.codigo AS codigoprofessor, p.nome AS nomeProfessor, p.titulacao ");
        sql.append("FROM disciplina d INNER JOIN professor p ");
        sql.append("ON d.codigoProfessor = p.codigo ");
        sql.append("WHERE d.codigo = ?");
        PreparedStatement ps = c.prepareStatement(sql.toString());
        ps.setInt(1, d.getCodigo());
        ResultSet rs = ps.executeQuery();
        int cont = 0;
        if(rs.next()) {
            Professor p = new Professor();
            p.setCodigo(rs.getInt("codigoProfessor"));
            p.setNome(rs.getString("nomeProfessor"));
            p.setTitulacao(rs.getString("titulacao"));
            d.setNome(rs.getString("nomeDisciplina"));
            d.setProfessor(p);
            cont++;
        }
        if(cont == 0) {
            d = new Disciplina();
            Professor p = new Professor();
            d.setProfessor(p);
        }
        rs.close();
        ps.close();
        return d;
    }

    @Override
    public List<Disciplina> buscarDisciplinas() throws SQLException {
        List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT d.codigo AS codigoDisciplina, d.nome AS nomeDisciplina, ");
        sql.append("p.codigo AS codigoprofessor, p.nome AS nomeProfessor, p.titulacao ");
        sql.append("FROM disciplina d INNER JOIN professor p ");
        sql.append("ON d.codigoProfessor = p.codigo");
        PreparedStatement ps = c.prepareStatement(sql.toString());
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Professor p = new Professor();
            p.setCodigo(rs.getInt("codigoProfessor"));
            p.setNome(rs.getString("nomeProfessor"));
            p.setTitulacao(rs.getString("titulacao"));
            Disciplina d = new Disciplina();
            d.setCodigo(rs.getInt("codigoDisciplina"));
            d.setNome(rs.getString("nomeDisciplina"));
            d.setProfessor(p);
            listaDisciplina.add(d);
        }
        rs.close();
        ps.close();
        return listaDisciplina;
    }
}
