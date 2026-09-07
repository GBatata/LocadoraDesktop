package view;

import model.Funcionario;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import br.com.biblioteca.view.TelaClientes;
import br.com.biblioteca.view.TelaLivros;
import br.com.biblioteca.view.TelaUsuarios;

public class TelaPrincipal extends JFrame {

  private final JTabbedPane abas =
    new JTabbedPane();

  private final Funcionario funcionarioLogado;

  
  public TelaPrincipal() {
    this(null);
  }

  
  public TelaPrincipal(Funcionario funcionarioLogado) {
    super("Sistema de Locadora");

    this.funcionarioLogado = funcionarioLogado;

    montar();

    setDefaultCloseOperation(
      JFrame.DO_NOTHING_ON_CLOSE
    );
    setMinimumSize(new Dimension(920, 650));
    setSize(1100, 760);
    setLocationRelativeTo(null);

    addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          sair();
        }
      }
    );
  }

  private void montar() {
    setJMenuBar(criarMenu());

    JPanel inicio =
      new JPanel(new GridBagLayout());

    JLabel texto = new JLabel(
      "Selecione uma funcionalidade no menu acima."
    );

    texto.setFont(
      texto.getFont().deriveFont(
        Font.BOLD,
        18f
      )
    );

    inicio.add(texto);

    abas.addTab("Inicio", inicio);

    add(abas, BorderLayout.CENTER);

    JPanel rodape = new JPanel(new BorderLayout());

    rodape.setBorder(
      BorderFactory.createEmptyBorder(
        4,
        8,
        4,
        8
      )
    );

    String identificacao;

    if (funcionarioLogado == null) {
      identificacao = "Usuario: acesso sem login";
    } else {
      identificacao =
        "Usuario: " +
        funcionarioLogado.getNome() +
        " (" +
        funcionarioLogado.getEmail() +
        ")";
    }

    rodape.add(
      new JLabel(identificacao),
      BorderLayout.WEST
    );

    rodape.add(
      new JLabel(
        "Sistema de Locadora - Java Desktop"
      ),
      BorderLayout.EAST
    );

    add(rodape, BorderLayout.SOUTH);
  }

  private JMenuBar criarMenu() {
    JMenuBar barra = new JMenuBar();

    JMenu cadastros = new JMenu("Cadastros");
    JMenu sistema = new JMenu("Sistema");

    JMenuItem clientes = item("Clientes", 1);
    JMenuItem carros = item("Carros", 2);
    JMenuItem funcionarios = item("Funcionarios", 3);

    cadastros.add(clientes);
    cadastros.add(carros);
    cadastros.add(funcionarios);

    JMenuItem sobre = new JMenuItem("Sobre");
    JMenuItem sair = new JMenuItem("Sair");

    sobre.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          JOptionPane.showMessageDialog(
            TelaPrincipal.this,
            "Sistema didatico de Locadora\n" +
            "Java Desktop + Swing + JDBC + MySQL",
            "Sobre",
            JOptionPane.INFORMATION_MESSAGE
          );
        }
      }
    );

    sair.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          sair();
        }
      }
    );

    sistema.add(sobre);
    sistema.addSeparator();
    sistema.add(sair);

    barra.add(cadastros);
    barra.add(sistema);

    return barra;
  }

  private JMenuItem item(
    String titulo,
    final int modulo
  ) {
    JMenuItem item = new JMenuItem(titulo);

    item.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          abrirModulo(modulo);
        }
      }
    );

    return item;
  }

  private void abrirModulo(int modulo) {
	    String titulo;
	    JPanel painel;
	    if (modulo == 1) {
	      titulo = "Clientes";
	      if (selecionar(titulo)) return;
	      painel = new TelaClientes();
	    } else if (modulo == 2) {
	      titulo = "Carros";
	      if (selecionar(titulo)) return;
	      painel = new TelaCarros();
	    } else if (modulo == 3) {
	      titulo = "Funcionarios";
	      if (selecionar(titulo)) return;
	      painel = new TelaFuncionarios();
	    }
	    abrirAba(titulo, painel);
  }

  
  public void abrirAba(
    String titulo,
    JPanel painel
  ) {
    int indice = abas.indexOfTab(titulo);

    if (indice >= 0) {
      abas.setSelectedIndex(indice);
      return;
    }

    abas.addTab(titulo, painel);

    abas.setTabComponentAt(
      abas.indexOfComponent(painel),
      cabecalhoFechavel(titulo, painel)
    );

    abas.setSelectedComponent(painel);
  }

  private boolean selecionar(String titulo) {
    int indice = abas.indexOfTab(titulo);

    if (indice >= 0) {
      abas.setSelectedIndex(indice);
      return true;
    }

    return false;
  }

  private JPanel cabecalhoFechavel(
    String titulo,
    final Component painel
  ) {
    JPanel cabecalho =
      new JPanel(
        new FlowLayout(
          FlowLayout.LEFT,
          3,
          0
        )
      );

    cabecalho.setOpaque(false);
    cabecalho.add(new JLabel(titulo));

    JButton fechar = new JButton("x");

    fechar.setMargin(
      new Insets(0, 4, 0, 4)
    );
    fechar.setToolTipText("Fechar aba");

    fechar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          abas.remove(painel);
        }
      }
    );

    cabecalho.add(fechar);

    return cabecalho;
  }

  private void sair() {
    if (
      JOptionPane.showConfirmDialog(
        this,
        "Deseja encerrar o sistema?",
        "Sair",
        JOptionPane.YES_NO_OPTION
      ) == JOptionPane.YES_OPTION
    ) {
      dispose();
      System.exit(0);
    }
  }
}