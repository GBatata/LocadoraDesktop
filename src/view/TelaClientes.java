package view;

import controller.ClienteController;
import model.Cliente;
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

public class TelaClientes extends JPanel {

  private final JTextField txtId = new JTextField(8),
    txtNome = new JTextField(30),
    txtCpf = new JTextField(30),
    txtCnh = new JTextField(30),
    txtDataNascimento = new JTextField(12),
    txtNacionalidade = new JTextField(30),
    txtEmail = new JTextField(30),
    txtCelular = new JTextField(30),
    txtPesquisa = new JTextField(25);
  private final JCheckBox chkResideBr = new JCheckBox("Reside no Brasil", true);
  private final JCheckBox chkAtivo = new JCheckBox("Cliente ativo", true);
  private final DefaultTableModel modelo = new DefaultTableModel(new Object[] { "ID", "Nome", "CPF", "CNH", "Data de nascimento", "Nacionalidade", "Reside no Brasil", "E-mail", "Celular", "Ativo" }, 0) {
    public boolean isCellEditable(int l, int c) {
      return false;
    }
  };
  private final JTable tabela = new JTable(modelo);
  private final ClienteController controller;

  public TelaClientes() {
    setLayout(new BorderLayout(8, 8));
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    controller = new ClienteController(this);
    montar();
    limparFormulario();
    controller.carregarTabela();
  }

  private void montar() {
    JPanel formulario = new JPanel(new GridBagLayout());
    formulario.setBorder(BorderFactory.createTitledBorder("Cadastro de clientes"));
    GridBagConstraints g = new GridBagConstraints();
    g.insets = new Insets(3, 4, 3, 4);
    g.anchor = GridBagConstraints.WEST;
    adicionar(formulario, g, 0, "Codigo:", txtId);
    adicionar(formulario, g, 1, "Nome*:", txtNome);
    adicionar(formulario, g, 2, "CPF*:", txtCpf);
    adicionar(formulario, g, 3, "CNH*:", txtCnh);
    adicionar(formulario, g, 4, "Data de nascimento*:", txtDataNascimento);
    adicionar(formulario, g, 5, "Nacionalidade*:", txtNacionalidade);
    adicionar(formulario, g, 6, "E-mail:", txtEmail);
    adicionar(formulario, g, 7, "Celular*:", txtCelular);
    g.gridx = 1;
    g.gridy = 8;
    formulario.add(chkResideBr, g);
    g.gridy = 9;
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
    txtCnh.setText("");
    txtDataNascimento.setText("");
    txtNacionalidade.setText("");
    txtEmail.setText("");
    txtCelular.setText("");
    chkResideBr.setSelected(true);
    chkAtivo.setSelected(true);
    tabela.clearSelection();
  }

  public void definirEdicao(boolean b) {
    txtNome.setEditable(b);
    txtCpf.setEditable(b);
    txtCnh.setEditable(b);
    txtDataNascimento.setEditable(b);
    txtNacionalidade.setEditable(b);
    txtEmail.setEditable(b);
    txtCelular.setEditable(b);
    chkResideBr.setEnabled(b);
    chkAtivo.setEnabled(b);
  }

  public void mostrarCliente(Cliente c) {
    txtId.setText(String.valueOf(c.getId()));
    txtNome.setText(c.getNome());
    txtCpf.setText(c.getCpf());
    txtCnh.setText(c.getCnh());
    txtDataNascimento.setText(Validador.formatarData(c.getDataNascimento()));
    txtNacionalidade.setText(c.getNacionalidade());
    txtEmail.setText(c.getEmail());
    txtCelular.setText(c.getCelular());
    chkResideBr.setSelected(c.isResideBrasil());
    chkAtivo.setSelected(c.isAtivo());
  }

  public void preencherTabela(List<Cliente> lista) {
    modelo.setRowCount(0);
    int i;
    for (i = 0; i < lista.size(); i++) {
      Cliente c = lista.get(i);
      modelo.addRow(new Object[] { Integer.valueOf(c.getId()), c.getNome(), c.getCpf(), c.getCnh(), Validador.formatarData(c.getDataNascimento()), c.getNacionalidade(), c.isResideBrasil() ? "Sim" : "Nao", c.getEmail(), c.getCelular(), c.isAtivo() ? "Sim" : "Nao" });
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

  public JTextField getTxtCnh() {
    return txtCnh;
  }

  public JTextField getTxtDataNascimento() {
    return txtDataNascimento;
  }

  public JTextField getTxtNacionalidade() {
    return txtNacionalidade;
  }

  public JTextField getTxtEmail() {
    return txtEmail;
  }

  public JTextField getTxtCelular() {
    return txtCelular;
  }

  public JTextField getTxtPesquisa() {
    return txtPesquisa;
  }

  public JCheckBox getChkResideBr() {
    return chkResideBr;
  }

  public JCheckBox getChkAtivo() {
    return chkAtivo;
  }

  public JTable getTabela() {
    return tabela;
  }

}
