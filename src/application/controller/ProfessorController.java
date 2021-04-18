package application.controller;

import application.model.Professor;
import application.persistence.ProfessorDao;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class ProfessorController implements IProfessorController {

    private TextField tfCodigoProfessor;
    private TextField tfNomeProfessor;
    private TextField tfTitulacaoProfessor;
    private TextArea taListaProfessores;

    public ProfessorController(TextField tfCodigoProfessor, TextField tfNomeProfessor, TextField tfTitulacaoProfessor, TextArea taListaProfessores) {
        this.tfCodigoProfessor = tfCodigoProfessor;
        this.tfNomeProfessor = tfNomeProfessor;
        this.tfTitulacaoProfessor = tfTitulacaoProfessor;
        this.taListaProfessores = taListaProfessores;
    }

    @Override
    public void inserirProfessor(Professor professor) throws ClassNotFoundException, SQLException {
        ProfessorDao pDao = new ProfessorDao();
        pDao.inserirProfessor(professor);
        limparCamposProfessor();
        buscarProfessores();
    }

    @Override
    public void atualizarProfessor(Professor professor) throws ClassNotFoundException, SQLException {
        ProfessorDao pDao = new ProfessorDao();
        pDao.atualizarProfessor(professor);
        limparCamposProfessor();
        buscarProfessores();
    }

    @Override
    public void excluirProfessor(Professor professor) throws ClassNotFoundException, SQLException {
        ProfessorDao pDao = new ProfessorDao();
        pDao.excluirProfessor(professor);
        limparCamposProfessor();
        buscarProfessores();
    }

    @Override
    public void buscarProfessor(Professor professor) throws ClassNotFoundException, SQLException {
        limparCamposProfessor();
        ProfessorDao pDao = new ProfessorDao();
        professor = pDao.buscarProfessor(professor);
        tfCodigoProfessor.setText(String.valueOf(professor.getCodigo()));
        tfNomeProfessor.setText(professor.getNome());
        tfTitulacaoProfessor.setText(professor.getTitulacao());
    }

    @Override
    public void buscarProfessores() throws ClassNotFoundException, SQLException {
        limparCamposProfessor();
        taListaProfessores.setText("");
        ProfessorDao pDao = new ProfessorDao();
        List<Professor> listaProfessores = pDao.buscarProfessores();
        StringBuffer buffer = new StringBuffer("Código\t\t\t\tNome\t\t\t\tTitulação\n");
        for(Professor p: listaProfessores) {
            buffer.append(p.getCodigo() + "\t\t\t\t\t" + p.getNome() + "\t\t\t\t" + p.getTitulacao() + "\n");
        }
        taListaProfessores.setText(buffer.toString());
    }

    private void limparCamposProfessor() {
        tfCodigoProfessor.setText("");
        tfNomeProfessor.setText("");
        tfTitulacaoProfessor.setText("");
    }
}
