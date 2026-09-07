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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TelaFuncionarios extends JPanel {

  private final JTextField txtId = new JTextField(8),
    txtNome = new JTextField(30),
    txtCpf = new JTextField(16),
    txtEmail = new JTextField(30),
    txtDataNascimento = new JTextField(12),
    txtPesquisa = new JTextField(25);

  private final JPasswordField txtSenha =
    new JPasswordField(20);

  private final JCheckBox chkAtivo =
    new JCheckBox("Funcionario ativo", true);

  private final DefaultTableModel modelo =
    new DefaultTableModel(
      new Object[] {
        "ID",
        "Nome",
        "CPF",
        "E-mail",
        "Data de nascimento",
        "Ativo"
      },
      0
    ) {
      public boolean isCellEditable(int linha, int coluna) {
        return false;
      }
    };

  private final JTable tabela = new JTable(modelo);
  private final FuncionarioController controller;

  public TelaFuncionarios() {
    setLayout(new BorderLayout(8, 8));
    setBorder(
      BorderFactory.createEmptyBorder(10, 10, 10, 10)
    );

    controller = new FuncionarioController(this);

    montar();
    controller.carregarTabela();
  }

  private void montar() {
    JPanel formulario =
      new JPanel(new GridBagLayout());

    formulario.setBorder(
      BorderFactory.createTitledBorder(
        "Cadastro de funcionários"
      )
    );

    GridBagConstraints g = new GridBagConstraints();
    g.insets = new Insets(3, 4, 3, 4);
    g.anchor = GridBagConstraints.WEST;

    adicionar(
      formulario,
      g,
      0,
      "Codigo:",
      txtId
    );

    adicionar(
      formulario,
      g,
      1,
      "Nome*:",
      txtNome
    );

    adicionar(
      formulario,
      g,
      2,
      "CPF*:",
      txtCpf
    );

    adicionar(
      formulario,
      g,
      3,
      "E-mail*:",
      txtEmail
    );

    adicionar(
      formulario,
      g,
      4,
      "Data de nascimento*:",
      txtDataNascimento
    );

    adicionar(
      formulario,
      g,
      5,
      "Senha*:",
      txtSenha
    );

    txtId.setEditable(false);

    txtDataNascimento.setToolTipText(
      "Utilize o formato dd/MM/yyyy"
    );

    g.gridx = 1;
    g.gridy = 6;
    formulario.add(chkAtivo, g);

    JLabel aviso = new JLabel(
      "Ao editar, deixe a senha vazia para mante-la."
    );

    g.gridy = 7;
    formulario.add(aviso, g);

    JPanel botoes =
      new JPanel(new FlowLayout(FlowLayout.LEFT));

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

    JPanel pesquisa =
      new JPanel(new FlowLayout(FlowLayout.LEFT));

    pesquisa.add(
      new JLabel("Pesquisar por nome:")
    );
    pesquisa.add(txtPesquisa);

    JButton buscar = new JButton("Buscar"),
      todos = new JButton("Mostrar todos");

    pesquisa.add(buscar);
    pesquisa.add(todos);

    JPanel centro = new JPanel(new BorderLayout());

    centro.add(pesquisa, BorderLayout.NORTH);
    centro.add(
      new JScrollPane(tabela),
      BorderLayout.CENTER
    );

    add(centro, BorderLayout.CENTER);

    tabela.setSelectionMode(
      javax.swing.ListSelectionModel.SINGLE_SELECTION
    );

    novo.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.novo();
        }
      }
    );

    salvar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.salvar();
        }
      }
    );

    excluir.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.excluir();
        }
      }
    );

    limpar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.limpar();
        }
      }
    );

    buscar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          controller.buscar();
        }
      }
    );

    todos.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          txtPesquisa.setText("");
          controller.carregarTabela();
        }
      }
    );

    tabela.addMouseListener(
      new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          controller.selecionarLinha();
        }
      }
    );
  }

  private void adicionar(
    JPanel painel,
    GridBagConstraints g,
    int linha,
    String rotulo,
    JTextField campo
  ) {
    g.gridx = 0;
    g.gridy = linha;
    g.weightx = 0;
    g.fill = GridBagConstraints.NONE;

    painel.add(new JLabel(rotulo), g);

    g.gridx = 1;
    g.weightx = 1;
    g.fill = GridBagConstraints.HORIZONTAL;

    painel.add(campo, g);
  }

  public void limparFormulario() {
    txtId.setText("");
    txtNome.setText("");
    txtCpf.setText("");
    txtEmail.setText("");
    txtDataNascimento.setText("");
    txtSenha.setText("");
    chkAtivo.setSelected(true);
    tabela.clearSelection();
  }

  public void definirEdicao(boolean habilitado) {
    txtNome.setEditable(habilitado);
    txtCpf.setEditable(habilitado);
    txtEmail.setEditable(habilitado);
    txtDataNascimento.setEditable(habilitado);
    txtSenha.setEditable(habilitado);
    chkAtivo.setEnabled(habilitado);
  }

  public void mostrarCliente(Funcionario funcionario) {
    txtId.setText(
      String.valueOf(funcionario.getId())
    );

    txtNome.setText(funcionario.getNome());
    txtCpf.setText(funcionario.getCpf());
    txtEmail.setText(funcionario.getEmail());

    txtDataNascimento.setText(
      Validador.formatarData(
    		  funcionario.getDataNascimento()
      )
    );

    txtSenha.setText("");
    chkAtivo.setSelected(funcionario.isAtivo());
  }

  public void preencherTabela(List<Funcionario> lista) {
    modelo.setRowCount(0);

    int i;

    for (i = 0; i < lista.size(); i++) {
    	Funcionario funcionario = lista.get(i);

      modelo.addRow(
        new Object[] {
          Integer.valueOf(funcionario.getId()),
          funcionario.getNome(),
          funcionario.getCpf(),
          funcionario.getEmail(),
          Validador.formatarData(
        		  funcionario.getDataNascimento()
          ),
          funcionario.isAtivo() ? "Sim" : "Nao"
        }
      );
    }
  }

  public int getIdSelecionado() {
    try {
      return Integer.parseInt(
        txtId.getText()
      );
    } catch (Exception e) {
      return 0;
    }
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

  public JPasswordField getTxtSenha() {
    return txtSenha;
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
