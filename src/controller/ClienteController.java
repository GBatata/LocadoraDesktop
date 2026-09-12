package controller;

import dao.ClienteDAO;
import model.Cliente;
import util.Validador;
import view.TelaClientes;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteController {

  private final TelaClientes tela;
  private final ClienteDAO dao;

  public ClienteController(TelaClientes tela) {
    this.tela = tela;
    this.dao = new ClienteDAO();
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
      Cliente c = lerFormulario();
      if (c.getId() == 0) {
        dao.salvar(c);
        mensagem("Cliente cadastrado com sucesso.", JOptionPane.INFORMATION_MESSAGE);
      } else {
        dao.atualizar(c);
        mensagem("Cliente atualizado com sucesso.", JOptionPane.INFORMATION_MESSAGE);
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
      mensagem("Selecione um cliente.", JOptionPane.WARNING_MESSAGE);
      return;
    }
    if (JOptionPane.showConfirmDialog( tela, "Deseja inativar este cliente?", "Confirmacao", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION) {
      try {
        dao.excluir(id);
        mensagem("Cliente inativado.", JOptionPane.INFORMATION_MESSAGE);
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
      Cliente c = dao.buscarPorId(id);
      if (c != null) tela.mostrarCliente(c);
    } catch (SQLException e) {
      erro(e);
    }
  }

  private Cliente lerFormulario() throws ParseException {
    Cliente c = new Cliente();
    c.setId(tela.getIdSelecionado());
    c.setNome(tela.getTxtNome().getText().trim());
    c.setCpf(tela.getTxtCpf().getText().trim());
    c.setCnh(tela.getTxtCnh().getText().trim());
    c.setDataNascimento(Validador.converterData(tela.getTxtDataNascimento().getText().trim()));
    c.setNacionalidade(tela.getTxtNacionalidade().getText().trim());
    c.setEmail(tela.getTxtEmail().getText().trim());
    c.setCelular(tela.getTxtCelular().getText().trim());
    c.setResideBrasil(tela.getChkResideBr().isSelected());
    c.setAtivo(tela.getChkAtivo().isSelected());
    return c;
  }

  private void validar() {
    if (Validador.vazio(tela.getTxtNome().getText())) throw new IllegalArgumentException("Preencha o campo nome.");
    if (Validador.vazio(tela.getTxtCpf().getText())) throw new IllegalArgumentException("Preencha o campo cpf.");
    if (Validador.vazio(tela.getTxtCnh().getText())) throw new IllegalArgumentException("Preencha o campo cnh.");
    if (Validador.vazio(tela.getTxtDataNascimento().getText())) throw new IllegalArgumentException("Preencha o campo data de nascimento.");
    if (Validador.vazio(tela.getTxtNacionalidade().getText())) throw new IllegalArgumentException("Preencha o campo nacionalidade.");
    if (Validador.vazio(tela.getTxtCelular().getText())) throw new IllegalArgumentException("Preencha o campo celular.");
    if (!Validador.emailValido(tela.getTxtEmail().getText())) throw new IllegalArgumentException("Informe um e-mail valido.");
  }

  private void consultar(boolean filtro) {
    try {
      List<Cliente> l = filtro
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
