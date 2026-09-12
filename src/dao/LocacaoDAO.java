package dao;

import model.Locacao;
import model.Cliente;
import model.Carro;
import model.Funcionario;
import util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocacaoDAO {

  public void salvar(Locacao locacao) throws SQLException {
    String sql = "INSERT INTO tb_locacao (id_cliente, id_carro, id_funcionario, data_retirada, data_prevista_devolucao, data_devolucao, local_retirada, local_devolucao, limite_quilometragem, quantidade_diarias, valor_diaria, valor_caucao, valor_total, total_pago, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preencher(stmt, locacao, false);
      stmt.executeUpdate();
      ResultSet chaves = null;
      try {
        chaves = stmt.getGeneratedKeys();
        if (chaves.next()) locacao.setId(chaves.getInt(1));
      } finally {
        Conexao.fechar(chaves);
      }
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  public void atualizar(Locacao locacao) throws SQLException {
    String sql = "UPDATE tb_locacao SET id_cliente=?, id_carro=?, id_funcionario=?, data_retirada=?, data_prevista_devolucao=?, data_devolucao=?, local_retirada=?, local_devolucao=?, limite_quilometragem=?, quantidade_diarias=?, valor_diaria=?, valor_caucao=?, valor_total=?, total_pago=?, status=? WHERE id=?";
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql);
      preencher(stmt, locacao, true);
      if (stmt.executeUpdate() == 0) throw new SQLException("Registro nao encontrado.");
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  public void excluir(int id) throws SQLException {
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement("DELETE FROM tb_locacao WHERE id=?");
      stmt.setInt(1, id);
      if (stmt.executeUpdate() == 0) throw new SQLException("Registro nao encontrado.");
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  public Locacao buscarPorId(int id) throws SQLException {
    List<Locacao> lista = consultar("WHERE l.id=?", Integer.valueOf(id));
    return lista.isEmpty() ? null : lista.get(0);
  }

  public List<Locacao> buscarPorNome(String nome) throws SQLException {
    return consultar("WHERE TRIM(c.nome) LIKE ? ORDER BY c.nome, l.id DESC", "%" + nome.trim() + "%");
  }

  public List<Locacao> listarTodos() throws SQLException {
    return consultar("ORDER BY l.status, l.id DESC", null);
  }

  private List<Locacao> consultar(String filtro, Object parametro) throws SQLException {
    String sql = "SELECT l.*, c.nome nome_cliente, ca.placa placa_carro, ca.modelo_carro, f.nome nome_funcionario FROM tb_locacao l JOIN tb_cliente c ON c.id=l.id_cliente JOIN tb_carro ca ON ca.id=l.id_carro JOIN tb_funcionario f ON f.id=l.id_funcionario " + filtro;
    List<Locacao> lista = new ArrayList<Locacao>();
    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql);
      if (parametro instanceof Integer) stmt.setInt(1, ((Integer) parametro).intValue());
      if (parametro instanceof String) stmt.setString(1, (String) parametro);
      rs = stmt.executeQuery();
      while (rs.next()) lista.add(mapear(rs));
      return lista;
    } finally {
      Conexao.fechar(conexao, stmt, rs);
    }
  }

  private Locacao mapear(ResultSet rs) throws SQLException {
    Cliente cliente = new Cliente();
    cliente.setId(rs.getInt("id_cliente"));
    cliente.setNome(rs.getString("nome_cliente"));
    Carro carro = new Carro();
    carro.setId(rs.getInt("id_carro"));
    carro.setPlaca(rs.getString("placa_carro"));
    carro.setModeloCarro(rs.getString("modelo_carro"));
    Funcionario funcionario = new Funcionario();
    funcionario.setId(rs.getInt("id_funcionario"));
    funcionario.setNome(rs.getString("nome_funcionario"));
    Locacao locacao = new Locacao();
    locacao.setId(rs.getInt("id"));
    locacao.setCliente(cliente);
    locacao.setCarro(carro);
    locacao.setFuncionario(funcionario);
    locacao.setDataRetirada(rs.getTimestamp("data_retirada"));
    locacao.setDataPrevistaDevolucao(rs.getTimestamp("data_prevista_devolucao"));
    locacao.setDataDevolucao(rs.getTimestamp("data_devolucao"));
    locacao.setLocalRetirada(rs.getString("local_retirada"));
    locacao.setLocalDevolucao(rs.getString("local_devolucao"));
    locacao.setLimiteQuilometragem(rs.getString("limite_quilometragem"));
    locacao.setQuantidadeDiarias(rs.getInt("quantidade_diarias"));
    locacao.setValorDiaria(rs.getBigDecimal("valor_diaria"));
    locacao.setValorCaucao(rs.getBigDecimal("valor_caucao"));
    locacao.setValorTotal(rs.getBigDecimal("valor_total"));
    locacao.setTotalPago(rs.getBigDecimal("total_pago"));
    locacao.setStatus(rs.getString("status"));
    return locacao;
  }

  private void preencher(PreparedStatement stmt, Locacao locacao, boolean atualizacao) throws SQLException {
    stmt.setInt(1, locacao.getCliente().getId());
    stmt.setInt(2, locacao.getCarro().getId());
    stmt.setInt(3, locacao.getFuncionario().getId());
    stmt.setTimestamp(4, new java.sql.Timestamp(locacao.getDataRetirada().getTime()));
    stmt.setTimestamp(5, new java.sql.Timestamp(locacao.getDataPrevistaDevolucao().getTime()));
    stmt.setTimestamp(6, locacao.getDataDevolucao() == null ? null : new java.sql.Timestamp(locacao.getDataDevolucao().getTime()));
    stmt.setString(7, locacao.getLocalRetirada().trim());
    stmt.setString(8, locacao.getLocalDevolucao().trim());
    stmt.setString(9, locacao.getLimiteQuilometragem().trim());
    stmt.setInt(10, locacao.getQuantidadeDiarias());
    stmt.setBigDecimal(11, locacao.getValorDiaria());
    stmt.setBigDecimal(12, locacao.getValorCaucao());
    stmt.setBigDecimal(13, locacao.getValorTotal());
    stmt.setBigDecimal(14, locacao.getTotalPago());
    stmt.setString(15, locacao.getStatus().trim());
    if (atualizacao) stmt.setInt(16, locacao.getId());
  }
}
