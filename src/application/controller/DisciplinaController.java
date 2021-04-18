package application.controller;

import application.model.Disciplina;
import application.persistence.DisciplinaDao;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class DisciplinaController implements IDisciplinaController {

    private TextField tfCodigoDisciplina;
    private TextField tfNomeDisciplina;
    private TextField tfDisciplinaCodigoProfessor;
    private Label lblNomeProfessor;
    private TextArea taListaDisciplinas;

    public DisciplinaController(TextField tfCodigoDisciplina, TextField tfNomeDisciplina, TextField tfDisciplinaCodigoProfessor, Label lblNomeProfessor, TextArea taListaDisciplinas) {
        this.tfCodigoDisciplina = tfCodigoDisciplina;
        this.tfNomeDisciplina = tfNomeDisciplina;
        this.tfDisciplinaCodigoProfessor = tfDisciplinaCodigoProfessor;
        this.lblNomeProfessor = lblNomeProfessor;
        this.taListaDisciplinas = taListaDisciplinas;
    }

    @Override
    public void inserirDisciplina(Disciplina disciplina) throws ClassNotFoundException, SQLException {
        DisciplinaDao dDao = new DisciplinaDao();
        dDao.inserirDisciplina(disciplina);
        limparCamposDisciplina();
        buscarDisciplinas();
    }

    @Override
    public void atualizarDisciplina(Disciplina disciplina) throws ClassNotFoundException, SQLException {
        DisciplinaDao dDao = new DisciplinaDao();
        dDao.atualizarDisciplina(disciplina);
        limparCamposDisciplina();
        buscarDisciplinas();
    }

    @Override
    public void excluirDisciplina(Disciplina disciplina) throws ClassNotFoundException, SQLException {
        DisciplinaDao dDao = new DisciplinaDao();
        dDao.excluirDisciplina(disciplina);
        limparCamposDisciplina();
        buscarDisciplinas();
    }

    @Override
    public void buscarDisciplna(Disciplina disciplina) throws ClassNotFoundException, SQLException {
        limparCamposDisciplina();
        DisciplinaDao dDao = new DisciplinaDao();
        disciplina = dDao.buscarDisciplina(disciplina);
        tfCodigoDisciplina.setText(String.valueOf(disciplina.getCodigo()));
        tfNomeDisciplina.setText(disciplina.getNome());
        tfDisciplinaCodigoProfessor.setText(String.valueOf(disciplina.getProfessor().getCodigo()));
        lblNomeProfessor.setText(disciplina.getProfessor().getNome());
    }

    @Override
    public void buscarDisciplinas() throws ClassNotFoundException, SQLException {
        limparCamposDisciplina();
        DisciplinaDao dDao = new DisciplinaDao();
        List<Disciplina> listaDisciplinas = dDao.buscarDisciplinas();
        taListaDisciplinas.setText("");
        StringBuffer sb = new StringBuffer("Código\t\t\tNome\t\t\t\tProfessor\n");
        for(Disciplina d : listaDisciplinas) {
            sb.append(d.getCodigo() + "\t\t\t" + d.getNome() + "\t\t\t\t" + d.getProfessor() + "\n");
        }
        taListaDisciplinas.setText(sb.toString());
    }

    private void limparCamposDisciplina() {
        tfCodigoDisciplina.setText("");
        tfNomeDisciplina.setText("");
        tfCodigoDisciplina.setText("");
        lblNomeProfessor.setText("");
    }
}
