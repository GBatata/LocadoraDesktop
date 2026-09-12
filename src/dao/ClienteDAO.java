package dao;

import model.Cliente;
import util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

  public void salvar(Cliente cliente) throws SQLException {
    String sql = "INSERT INTO tb_cliente (nome, cpf, cnh, data_nascimento, nacionalidade, reside_brasil, email, celular, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preencher(stmt, cliente, false);
      stmt.executeUpdate();
      ResultSet chaves = null;
      try {
        chaves = stmt.getGeneratedKeys();
        if (chaves.next()) cliente.setId(chaves.getInt(1));
      } finally {
        Conexao.fechar(chaves);
      }
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  public void atualizar(Cliente cliente) throws SQLException {
    String sql = "UPDATE tb_cliente SET nome=?, cpf=?, cnh=?, data_nascimento=?, nacionalidade=?, reside_brasil=?, email=?, celular=?, ativo=? WHERE id=?";
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql);
      preencher(stmt, cliente, true);
      if (stmt.executeUpdate() == 0) throw new SQLException("Registro nao encontrado.");
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  public void excluir(int id) throws SQLException {
    alterarAtivo(id, false);
  }

  public void alterarAtivo(int id, boolean ativo) throws SQLException {
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement("UPDATE tb_cliente SET ativo=? WHERE id=?");
      stmt.setBoolean(1, ativo);
      stmt.setInt(2, id);
      if (stmt.executeUpdate() == 0) throw new SQLException("Registro nao encontrado.");
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  public Cliente buscarPorId(int id) throws SQLException {
    List<Cliente> lista = consultar("SELECT * FROM tb_cliente WHERE id=?", Integer.valueOf(id));
    return lista.isEmpty() ? null : lista.get(0);
  }

  public List<Cliente> buscarPorNome(String nome) throws SQLException {
    return consultar("SELECT * FROM tb_cliente WHERE TRIM(nome) LIKE ? ORDER BY nome", "%" + nome.trim() + "%");
  }

  public List<Cliente> listarTodos() throws SQLException {
    return consultar("SELECT * FROM tb_cliente ORDER BY nome", null);
  }

  public List<Cliente> listarAtivos() throws SQLException {
    return consultar("SELECT * FROM tb_cliente WHERE ativo=1 ORDER BY nome", null);
  }

  private List<Cliente> consultar(String sql, Object parametro) throws SQLException {
    List<Cliente> lista = new ArrayList<Cliente>();
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

  private Cliente mapear(ResultSet rs) throws SQLException {
    Cliente cliente = new Cliente();
    cliente.setId(rs.getInt("id"));
    cliente.setNome(rs.getString("nome"));
    cliente.setCpf(rs.getString("cpf"));
    cliente.setCnh(rs.getString("cnh"));
    cliente.setDataNascimento(rs.getDate("data_nascimento"));
    cliente.setNacionalidade(rs.getString("nacionalidade"));
    cliente.setResideBrasil(rs.getBoolean("reside_brasil"));
    cliente.setEmail(rs.getString("email"));
    cliente.setCelular(rs.getString("celular"));
    cliente.setAtivo(rs.getBoolean("ativo"));
    return cliente;
  }

  private void preencher(PreparedStatement stmt, Cliente cliente, boolean atualizacao) throws SQLException {
    stmt.setString(1, cliente.getNome().trim());
    stmt.setString(2, cliente.getCpf().trim());
    stmt.setString(3, cliente.getCnh().trim());
    stmt.setDate(4, new java.sql.Date(cliente.getDataNascimento().getTime()));
    stmt.setString(5, cliente.getNacionalidade().trim());
    stmt.setBoolean(6, cliente.isResideBrasil());
    stmt.setString(7, cliente.getEmail() == null ? null : cliente.getEmail().trim());
    stmt.setString(8, cliente.getCelular().trim());
    stmt.setBoolean(9, cliente.isAtivo());
    if (atualizacao) stmt.setInt(10, cliente.getId());
  }
}
