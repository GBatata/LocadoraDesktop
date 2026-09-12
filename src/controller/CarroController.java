package controller;

import dao.CarroDAO;
import model.Carro;
import util.Validador;
import view.TelaCarros;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;

public class CarroController {

  private final TelaCarros tela;
  private final CarroDAO dao;

  public CarroController(TelaCarros tela) {
    this.tela = tela;
    this.dao = new CarroDAO();
  }

  public void novo() {
    tela.limparFormulario();
    tela.definirEdicao(true);
    tela.getTxtPlaca().requestFocus();
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
      Carro c = lerFormulario();
      if (c.getId() == 0) {
        if (Carro.LOCADO.equals(c.getStatus())) throw new IllegalArgumentException("Registre a retirada pela tela de locacoes.");
        dao.salvar(c);
        mensagem("Carro cadastrado com sucesso.", JOptionPane.INFORMATION_MESSAGE);
      } else {
        Carro atual = dao.buscarPorId(c.getId());
        if (atual == null) throw new SQLException("Carro nao encontrado.");
        if (Carro.LOCADO.equals(atual.getStatus()) && !Carro.LOCADO.equals(c.getStatus())) throw new IllegalArgumentException("Devolva o carro pela tela de devolucoes.");
        if (!Carro.LOCADO.equals(atual.getStatus()) && Carro.LOCADO.equals(c.getStatus())) throw new IllegalArgumentException("O status LOCADO e definido ao registrar uma locacao.");
        dao.atualizar(c);
        mensagem("Carro atualizado com sucesso.", JOptionPane.INFORMATION_MESSAGE);
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
      mensagem("Selecione um carro.", JOptionPane.WARNING_MESSAGE);
      return;
    }
    if (JOptionPane.showConfirmDialog( tela, "Deseja excluir este carro?", "Confirmacao", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION) {
      try {
        dao.excluir(id);
        mensagem("Carro excluido.", JOptionPane.INFORMATION_MESSAGE);
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
      Carro c = dao.buscarPorId(id);
      if (c != null) tela.mostrarCarro(c);
    } catch (SQLException e) {
      erro(e);
    }
  }

  private Carro lerFormulario() {
    Carro c = new Carro();
    c.setId(tela.getIdSelecionado());
    c.setPlaca(tela.getTxtPlaca().getText().trim());
    c.setModeloCarro(tela.getTxtModelo().getText().trim());
    c.setGrupoCarro(tela.getTxtGrupo().getText().trim());
    c.setCambio((String) tela.getCmbCambio().getSelectedItem());
    c.setNumeroAssentos(Integer.parseInt(tela.getTxtNumeroAssento().getText().trim()));
    c.setValorDiaria(new BigDecimal(tela.getTxtValorDiaria().getText().trim().replace(",", ".")));
    c.setValorCaucao(new BigDecimal(tela.getTxtValorCaucao().getText().trim().replace(",", ".")));
    c.setStatus((String) tela.getCmbStatus().getSelectedItem());
    c.setGps(tela.getChkGps().isSelected());
    if (c.getNumeroAssentos() <= 0) throw new IllegalArgumentException("Informe um numero de assentos maior que zero.");
    if (c.getValorDiaria().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Informe uma diaria maior que zero.");
    if (c.getValorCaucao().compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("A caucao nao pode ser negativa.");
    return c;
  }

  private void validar() {
    if (Validador.vazio(tela.getTxtPlaca().getText())) throw new IllegalArgumentException("Preencha o campo placa.");
    if (Validador.vazio(tela.getTxtModelo().getText())) throw new IllegalArgumentException("Preencha o campo modelo.");
    if (Validador.vazio(tela.getTxtGrupo().getText())) throw new IllegalArgumentException("Preencha o campo grupo.");
    if (Validador.vazio(tela.getTxtNumeroAssento().getText())) throw new IllegalArgumentException("Preencha o campo numero de assentos.");
    if (Validador.vazio(tela.getTxtValorDiaria().getText())) throw new IllegalArgumentException("Preencha o campo valor da diaria.");
    if (Validador.vazio(tela.getTxtValorCaucao().getText())) throw new IllegalArgumentException("Preencha o campo valor da caucao.");
  }

  private void consultar(boolean filtro) {
    try {
      List<Carro> l = filtro
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
