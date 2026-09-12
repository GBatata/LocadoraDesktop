package view;

import controller.FuncionarioController;
import model.Funcionario;
import util.Validador;
import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TelaFuncionarios extends JPanel {

  private final JTextField txtId = new JTextField(8),
    txtNome = new JTextField(30),
    txtCpf = new JTextField(30),
    txtEmail = new JTextField(30),
    txtDataNascimento = new JTextField(12),
    txtPesquisa = new JTextField(25);
  private final JCheckBox chkAtivo = new JCheckBox("Funcionario ativo", true);
  private final DefaultTableModel modelo = new DefaultTableModel(new Object[] { "ID", "Nome", "CPF", "E-mail", "Data de nascimento", "Ativo" }, 0) {
    public boolean isCellEditable(int l, int c) {
      return false;
    }
  };
  private final JTable tabela = new JTable(modelo);
  private final FuncionarioController controller;

  public TelaFuncionarios() {
    setLayout(new BorderLayout(8, 8));
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    controller = new FuncionarioController(this);
    montar();
    limparFormulario();
    controller.carregarTabela();
  }

  private void montar() {
    JPanel formulario = new JPanel(new GridBagLayout());
    formulario.setBorder(BorderFactory.createTitledBorder("Cadastro de funcionarios"));
    GridBagConstraints g = new GridBagConstraints();
    g.insets = new Insets(3, 4, 3, 4);
    g.anchor = GridBagConstraints.WEST;
    adicionar(formulario, g, 0, "Codigo:", txtId);
    adicionar(formulario, g, 1, "Nome*:", txtNome);
    adicionar(formulario, g, 2, "CPF*:", txtCpf);
    adicionar(formulario, g, 3, "E-mail*:", txtEmail);
    adicionar(formulario, g, 4, "Data de nascimento*:", txtDataNascimento);
    g.gridx = 1;
    g.gridy = 5;
    formulario.add(chkAtivo, g);
    txtId.setEditable(false);
    txtDataNascimento.setToolTipText("Utilize o formato dd/MM/yyyy");
    JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton novo = new JButton("Novo"),
      salvar = new JButton("Salvar"),
      excluir = new JButton("Inativar"),
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
    pesquisa.add(new JLabel("Pesquisar por nome:"));
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

  private void adicionar(JPanel p, GridBagConstraints g, int y, String rotulo, JTextField campo) {
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
    txtNome.setText("");
    txtCpf.setText("");
    txtEmail.setText("");
    txtDataNascimento.setText("");
    chkAtivo.setSelected(true);
    tabela.clearSelection();
  }

  public void definirEdicao(boolean b) {
    txtNome.setEditable(b);
    txtCpf.setEditable(b);
    txtEmail.setEditable(b);
    txtDataNascimento.setEditable(b);
    chkAtivo.setEnabled(b);
  }

  public void mostrarFuncionario(Funcionario c) {
    txtId.setText(String.valueOf(c.getId()));
    txtNome.setText(c.getNome());
    txtCpf.setText(c.getCpf());
    txtEmail.setText(c.getEmail());
    txtDataNascimento.setText(Validador.formatarData(c.getDataNascimento()));
    chkAtivo.setSelected(c.isAtivo());
  }

  public void preencherTabela(List<Funcionario> lista) {
    modelo.setRowCount(0);
    int i;
    for (i = 0; i < lista.size(); i++) {
      Funcionario c = lista.get(i);
      modelo.addRow(new Object[] { Integer.valueOf(c.getId()), c.getNome(), c.getCpf(), c.getEmail(), Validador.formatarData(c.getDataNascimento()), c.isAtivo() ? "Sim" : "Nao" });
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

  public JTextField getTxtNome() {
    return txtNome;
  }

  public JTextField getTxtCpf() {
    return txtCpf;
  }

  public JTextField getTxtEmail() {
    return txtEmail;
  }

  public JTextField getTxtDataNascimento() {
    return txtDataNascimento;
  }

  public JTextField getTxtPesquisa() {
    return txtPesquisa;
  }

  public JCheckBox getChkAtivo() {
    return chkAtivo;
  }

  public JTable getTabela() {
    return tabela;
  }

}
