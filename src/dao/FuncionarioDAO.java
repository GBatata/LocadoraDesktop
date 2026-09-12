package dao;

import model.Funcionario;
import util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

  public void salvar(Funcionario funcionario) throws SQLException {
    String sql = "INSERT INTO tb_funcionario (nome, cpf, email, data_nascimento, ativo) VALUES (?, ?, ?, ?, ?)";
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preencher(stmt, funcionario, false);
      stmt.executeUpdate();
      ResultSet chaves = null;
      try {
        chaves = stmt.getGeneratedKeys();
        if (chaves.next()) funcionario.setId(chaves.getInt(1));
      } finally {
        Conexao.fechar(chaves);
      }
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  public void atualizar(Funcionario funcionario) throws SQLException {
    String sql = "UPDATE tb_funcionario SET nome=?, cpf=?, email=?, data_nascimento=?, ativo=? WHERE id=?";
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql);
      preencher(stmt, funcionario, true);
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
      stmt = conexao.prepareStatement("UPDATE tb_funcionario SET ativo=? WHERE id=?");
      stmt.setBoolean(1, ativo);
      stmt.setInt(2, id);
      if (stmt.executeUpdate() == 0) throw new SQLException("Registro nao encontrado.");
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  public Funcionario buscarPorId(int id) throws SQLException {
    List<Funcionario> lista = consultar("SELECT * FROM tb_funcionario WHERE id=?", Integer.valueOf(id));
    return lista.isEmpty() ? null : lista.get(0);
  }

  public List<Funcionario> buscarPorNome(String nome) throws SQLException {
    return consultar("SELECT * FROM tb_funcionario WHERE TRIM(nome) LIKE ? ORDER BY nome", "%" + nome.trim() + "%");
  }

  public List<Funcionario> listarTodos() throws SQLException {
    return consultar("SELECT * FROM tb_funcionario ORDER BY nome", null);
  }

  public List<Funcionario> listarAtivos() throws SQLException {
    return consultar("SELECT * FROM tb_funcionario WHERE ativo=1 ORDER BY nome", null);
  }

  private List<Funcionario> consultar(String sql, Object parametro) throws SQLException {
    List<Funcionario> lista = new ArrayList<Funcionario>();
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

  private Funcionario mapear(ResultSet rs) throws SQLException {
    Funcionario funcionario = new Funcionario();
    funcionario.setId(rs.getInt("id"));
    funcionario.setNome(rs.getString("nome"));
    funcionario.setCpf(rs.getString("cpf"));
    funcionario.setEmail(rs.getString("email"));
    funcionario.setDataNascimento(rs.getDate("data_nascimento"));
    funcionario.setAtivo(rs.getBoolean("ativo"));
    return funcionario;
  }

  private void preencher(PreparedStatement stmt, Funcionario funcionario, boolean atualizacao) throws SQLException {
    stmt.setString(1, funcionario.getNome().trim());
    stmt.setString(2, funcionario.getCpf().trim());
    stmt.setString(3, funcionario.getEmail().trim());
    stmt.setDate(4, new java.sql.Date(funcionario.getDataNascimento().getTime()));
    stmt.setBoolean(5, funcionario.isAtivo());
    if (atualizacao) stmt.setInt(6, funcionario.getId());
  }
}
