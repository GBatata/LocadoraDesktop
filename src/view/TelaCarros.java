package view;

import controller.CarroController;
import model.Carro;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TelaCarros extends JPanel {

  private final JTextField txtId = new JTextField(8),
    txtPlaca = new JTextField(30),
    txtModelo = new JTextField(30),
    txtGrupo = new JTextField(30),
    txtNumeroAssento = new JTextField(12),
    txtValorDiaria = new JTextField(12),
    txtValorCaucao = new JTextField(12),
    txtPesquisa = new JTextField(25);
  private final JCheckBox chkGps = new JCheckBox("Tem GPS", false);
  private final JComboBox cmbCambio = new JComboBox(new String[] { "MANUAL", "AUTOMATICO" });
  private final JComboBox cmbStatus = new JComboBox(new String[] { Carro.DISPONIVEL, Carro.LOCADO, Carro.INATIVO });
  private final DefaultTableModel modelo = new DefaultTableModel(new Object[] { "ID", "Placa", "Modelo", "Grupo", "Cambio", "Assentos", "GPS", "Diaria", "Caucao", "Status" }, 0) {
    public boolean isCellEditable(int l, int c) {
      return false;
    }
  };
  private final JTable tabela = new JTable(modelo);
  private final CarroController controller;

  public TelaCarros() {
    setLayout(new BorderLayout(8, 8));
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    controller = new CarroController(this);
    montar();
    limparFormulario();
    controller.carregarTabela();
  }

  private void montar() {
    JPanel formulario = new JPanel(new GridBagLayout());
    formulario.setBorder(BorderFactory.createTitledBorder("Cadastro de carros"));
    GridBagConstraints g = new GridBagConstraints();
    g.insets = new Insets(3, 4, 3, 4);
    g.anchor = GridBagConstraints.WEST;
    adicionar(formulario, g, 0, "Codigo:", txtId);
    adicionar(formulario, g, 1, "Placa*:", txtPlaca);
    adicionar(formulario, g, 2, "Modelo*:", txtModelo);
    adicionar(formulario, g, 3, "Grupo*:", txtGrupo);
    adicionar(formulario, g, 4, "Cambio*:", cmbCambio);
    adicionar(formulario, g, 5, "Numero de assentos*:", txtNumeroAssento);
    adicionar(formulario, g, 6, "Valor da diaria*:", txtValorDiaria);
    adicionar(formulario, g, 7, "Valor da caucao*:", txtValorCaucao);
    adicionar(formulario, g, 8, "Status*:", cmbStatus);
    g.gridx = 1;
    g.gridy = 9;
    formulario.add(chkGps, g);
    txtId.setEditable(false);
    JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton novo = new JButton("Novo"),
      salvar = new JButton("Salvar"),
      excluir = new JButton("Excluir"),
      limpar = new JButton("Limpar");
    botoes.add(novo);
    botoes.add(salvar);
    botoes.add(excluir);
    botoes.add(limpar);
    JPanel topo = new JPanel(new BorderLayout());
    topo.add(formulario, BorderLayout.CENTER);
    topo.add(botoes, BorderLayout.SOUTH);
    add(topo, BorderLayout.NORTH);
    JPanel pesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
    pesquisa.add(new JLabel("Pesquisar por modelo:"));
    pesquisa.add(txtPesquisa);
    JButton buscar = new JButton("Buscar"),
      todos = new JButton("Mostrar todos");
    pesquisa.add(buscar);
    pesquisa.add(todos);
    JPanel centro = new JPanel(new BorderLayout());
    centro.add(pesquisa, BorderLayout.NORTH);
    centro.add(new JScrollPane(tabela), BorderLayout.CENTER);
    add(centro, BorderLayout.CENTER);
    tabela.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    novo.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.novo();
        }
      });
    salvar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.salvar();
        }
      });
    excluir.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.excluir();
        }
      });
    limpar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.limpar();
        }
      });
    buscar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.buscar();
        }
      });
    todos.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          txtPesquisa.setText("");
          controller.carregarTabela();
        }
      });
    tabela.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          controller.selecionarLinha();
        }
      });
  }

  private void adicionar(JPanel p, GridBagConstraints g, int y, String rotulo, Component campo) {
    g.gridx = 0;
    g.gridy = y;
    g.weightx = 0;
    g.fill = GridBagConstraints.NONE;
    p.add(new JLabel(rotulo), g);
    g.gridx = 1;
    g.weightx = 1;
    g.fill = GridBagConstraints.HORIZONTAL;
    p.add(campo, g);
  }

  public void limparFormulario() {
    txtId.setText("");
    txtPlaca.setText("");
    txtModelo.setText("");
    txtGrupo.setText("");
    cmbCambio.setSelectedItem("MANUAL");
    txtNumeroAssento.setText("");
    txtValorDiaria.setText("");
    txtValorCaucao.setText("0,00");
    cmbStatus.setSelectedItem(Carro.DISPONIVEL);
    chkGps.setSelected(false);
    tabela.clearSelection();
  }

  public void definirEdicao(boolean b) {
    txtPlaca.setEditable(b);
    txtModelo.setEditable(b);
    txtGrupo.setEditable(b);
    cmbCambio.setEnabled(b);
    txtNumeroAssento.setEditable(b);
    txtValorDiaria.setEditable(b);
    txtValorCaucao.setEditable(b);
    cmbStatus.setEnabled(b);
    chkGps.setEnabled(b);
  }

  public void mostrarCarro(Carro c) {
    txtId.setText(String.valueOf(c.getId()));
    txtPlaca.setText(c.getPlaca());
    txtModelo.setText(c.getModeloCarro());
    txtGrupo.setText(c.getGrupoCarro());
    cmbCambio.setSelectedItem(c.getCambio());
    txtNumeroAssento.setText(String.valueOf(c.getNumeroAssentos()));
    txtValorDiaria.setText(c.getValorDiaria().toPlainString());
    txtValorCaucao.setText(c.getValorCaucao().toPlainString());
    cmbStatus.setSelectedItem(c.getStatus());
    chkGps.setSelected(c.isGps());
  }

  public void preencherTabela(List<Carro> lista) {
    modelo.setRowCount(0);
    int i;
    for (i = 0; i < lista.size(); i++) {
      Carro c = lista.get(i);
      modelo.addRow(new Object[] { Integer.valueOf(c.getId()), c.getPlaca(), c.getModeloCarro(), c.getGrupoCarro(), c.getCambio(), Integer.valueOf(c.getNumeroAssentos()), c.isGps() ? "Sim" : "Nao", c.getValorDiaria(), c.getValorCaucao(), c.getStatus() });
    }
  }

  public int getIdSelecionado() {
    try {
      return Integer.parseInt(txtId.getText());
    } catch (Exception e) {
      return 0;
    }
  }

  public JTextField getTxtId() {
    return txtId;
  }

  public JTextField getTxtPlaca() {
    return txtPlaca;
  }

  public JTextField getTxtModelo() {
    return txtModelo;
  }

  public JTextField getTxtGrupo() {
    return txtGrupo;
  }

  public JComboBox getCmbCambio() {
    return cmbCambio;
  }

  public JTextField getTxtNumeroAssento() {
    return txtNumeroAssento;
  }

  public JTextField getTxtValorDiaria() {
    return txtValorDiaria;
  }

  public JTextField getTxtValorCaucao() {
    return txtValorCaucao;
  }

  public JComboBox getCmbStatus() {
    return cmbStatus;
  }

  public JTextField getTxtPesquisa() {
    return txtPesquisa;
  }

  public JCheckBox getChkGps() {
    return chkGps;
  }

  public JTable getTabela() {
    return tabela;
  }

}
