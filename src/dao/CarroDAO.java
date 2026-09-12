package dao;

import model.Carro;
import util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {

  public void salvar(Carro carro) throws SQLException {
    String sql = "INSERT INTO tb_carro (placa, modelo_carro, grupo_carro, cambio, numero_assentos, gps, valor_diaria, valor_caucao, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preencher(stmt, carro, false);
      stmt.executeUpdate();
      ResultSet chaves = null;
      try {
        chaves = stmt.getGeneratedKeys();
        if (chaves.next()) carro.setId(chaves.getInt(1));
      } finally {
        Conexao.fechar(chaves);
      }
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  public void atualizar(Carro carro) throws SQLException {
    String sql = "UPDATE tb_carro SET placa=?, modelo_carro=?, grupo_carro=?, cambio=?, numero_assentos=?, gps=?, valor_diaria=?, valor_caucao=?, status=? WHERE id=?";
    Connection conexao = null;
    PreparedStatement stmt = null;
    try {
      conexao = Conexao.abrir();
      stmt = conexao.prepareStatement(sql);
      preencher(stmt, carro, true);
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
      stmt = conexao.prepareStatement("DELETE FROM tb_carro WHERE id=?");
      stmt.setInt(1, id);
      if (stmt.executeUpdate() == 0) throw new SQLException("Registro nao encontrado.");
    } finally {
      Conexao.fechar(conexao, stmt, null);
    }
  }

  public Carro buscarPorId(int id) throws SQLException {
    List<Carro> lista = consultar("SELECT * FROM tb_carro WHERE id=?", Integer.valueOf(id));
    return lista.isEmpty() ? null : lista.get(0);
  }

  public List<Carro> buscarPorNome(String modeloCarro) throws SQLException {
    return consultar("SELECT * FROM tb_carro WHERE TRIM(modelo_carro) LIKE ? ORDER BY modelo_carro", "%" + modeloCarro.trim() + "%");
  }

  public List<Carro> listarTodos() throws SQLException {
    return consultar("SELECT * FROM tb_carro ORDER BY modelo_carro", null);
  }

  private List<Carro> consultar(String sql, Object parametro) throws SQLException {
    List<Carro> lista = new ArrayList<Carro>();
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

  private Carro mapear(ResultSet rs) throws SQLException {
    Carro carro = new Carro();
    carro.setId(rs.getInt("id"));
    carro.setPlaca(rs.getString("placa"));
    carro.setModeloCarro(rs.getString("modelo_carro"));
    carro.setGrupoCarro(rs.getString("grupo_carro"));
    carro.setCambio(rs.getString("cambio"));
    carro.setNumeroAssentos(rs.getInt("numero_assentos"));
    carro.setGps(rs.getBoolean("gps"));
    carro.setValorDiaria(rs.getBigDecimal("valor_diaria"));
    carro.setValorCaucao(rs.getBigDecimal("valor_caucao"));
    carro.setStatus(rs.getString("status"));
    return carro;
  }

  private void preencher(PreparedStatement stmt, Carro carro, boolean atualizacao) throws SQLException {
    stmt.setString(1, carro.getPlaca().trim());
    stmt.setString(2, carro.getModeloCarro().trim());
    stmt.setString(3, carro.getGrupoCarro().trim());
    stmt.setString(4, carro.getCambio().trim());
    stmt.setInt(5, carro.getNumeroAssentos());
    stmt.setBoolean(6, carro.isGps());
    stmt.setBigDecimal(7, carro.getValorDiaria());
    stmt.setBigDecimal(8, carro.getValorCaucao());
    stmt.setString(9, carro.getStatus().trim());
    if (atualizacao) stmt.setInt(10, carro.getId());
  }
}
