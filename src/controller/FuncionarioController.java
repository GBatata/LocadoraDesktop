package controller;

import dao.FuncionarioDAO;
import model.Funcionario;
import util.Validador;
import view.TelaFuncionarios;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;

public class FuncionarioController {

  private final TelaFuncionarios tela;
  private final FuncionarioDAO dao;

  public FuncionarioController(TelaFuncionarios tela) {
    this.tela = tela;
    this.dao = new FuncionarioDAO();
  }

  public void novo() {
    tela.limparFormulario();
    tela.definirEdicao(true);
    tela.getTxtNome().requestFocus();
  }

  public void limpar() {
    tela.limparFormulario();
    tela.definirEdicao(true);
  }

  public void carregarTabela() {
    consultar(false);
  }

  public void buscar() {
    consultar(true);
  }

  public void salvar() {
    try {
      validar();
      Funcionario c = lerFormulario();
      if (c.getId() == 0) {
        dao.salvar(c);
        mensagem("Funcionario cadastrado com sucesso.", JOptionPane.INFORMATION_MESSAGE);
      } else {
        dao.atualizar(c);
        mensagem("Funcionario atualizado com sucesso.", JOptionPane.INFORMATION_MESSAGE);
      }
      limpar();
      carregarTabela();
    } catch (Exception e) {
      erro(e);
    }
  }

  public void excluir() {
    int id = tela.getIdSelecionado();
    if (id == 0) {
      mensagem("Selecione um funcionario.", JOptionPane.WARNING_MESSAGE);
      return;
    }
    if (JOptionPane.showConfirmDialog( tela, "Deseja inativar este funcionario?", "Confirmacao", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION) {
      try {
        dao.excluir(id);
        mensagem("Funcionario inativado.", JOptionPane.INFORMATION_MESSAGE);
        limpar();
        carregarTabela();
      } catch (SQLException e) {
        erro(e);
      }
    }
  }

  public void selecionarLinha() {
    int linha = tela.getTabela().getSelectedRow();
    if (linha < 0) return;
    int id = ((Integer) tela.getTabela().getValueAt(linha, 0)).intValue();
    try {
      Funcionario c = dao.buscarPorId(id);
      if (c != null) tela.mostrarFuncionario(c);
    } catch (SQLException e) {
      erro(e);
    }
  }

  private Funcionario lerFormulario() throws ParseException {
    Funcionario c = new Funcionario();
    c.setId(tela.getIdSelecionado());
    c.setNome(tela.getTxtNome().getText().trim());
    c.setCpf(tela.getTxtCpf().getText().trim());
    c.setEmail(tela.getTxtEmail().getText().trim());
    c.setDataNascimento(Validador.converterData(tela.getTxtDataNascimento().getText().trim()));
    c.setAtivo(tela.getChkAtivo().isSelected());
    return c;
  }

  private void validar() {
    if (Validador.vazio(tela.getTxtNome().getText())) throw new IllegalArgumentException("Preencha o campo nome.");
    if (Validador.vazio(tela.getTxtCpf().getText())) throw new IllegalArgumentException("Preencha o campo cpf.");
    if (Validador.vazio(tela.getTxtEmail().getText())) throw new IllegalArgumentException("Preencha o campo e-mail.");
    if (Validador.vazio(tela.getTxtDataNascimento().getText())) throw new IllegalArgumentException("Preencha o campo data de nascimento.");
    if (!Validador.emailValido(tela.getTxtEmail().getText())) throw new IllegalArgumentException("Informe um e-mail valido.");
  }

  private void consultar(boolean filtro) {
    try {
      List<Funcionario> l = filtro
        ? dao.buscarPorNome(tela.getTxtPesquisa().getText())
        : dao.listarTodos();
      tela.preencherTabela(l);
    } catch (SQLException e) {
      erro(e);
    }
  }

  private void mensagem(String m, int tipo) {
    JOptionPane.showMessageDialog(tela, m, "Locadora", tipo);
  }

  private void erro(Exception e) {
    e.printStackTrace();
    mensagem("Nao foi possivel concluir a operacao.\n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
  }
}
