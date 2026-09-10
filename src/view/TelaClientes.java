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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TelaClientes extends JPanel {

  private final JTextField txtId = new JTextField(8),
    txtNome = new JTextField(30),    
    txtCpf = new JTextField(16),
    txtCnh = new JTextField(16),
    txtDataNascimento = new JTextField(12),
    txtNacionalidade = new JTextField(30),
    txtEmail = new JTextField(30),
    txtCelular = new JTextField(30),
    txtPesquisa = new JTextField(25);
  
  private final JCheckBox chkResideBr =
		    new JCheckBox("Reside no Brasil", true);
  
  private final DefaultTableModel modelo =
		    new DefaultTableModel(
		      new Object[] {
		        "ID",
		        "Nome",		        
		        "CPF",
		        "CNH",
		        "Data de nascimento",		        
		        "Nacionalidade",
		        "Reside no Brasil",
		        "E-mail",
		        "Celular"
		      },
		      0
		    ) {
		      public boolean isCellEditable(int linha, int coluna) {
		        return false;
		      }
		    };
		    
		    private final JTable tabela = new JTable(modelo);
		    private final ClienteController controller;

		    public TelaClientes() {
		      setLayout(new BorderLayout(8, 8));
		      setBorder(
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)
		      );
		      
		      controller = new ClienteController(this);

		      montar();
		      controller.carregarTabela();		      
		    }
		    
		    private void montar() {
		        JPanel formulario =
		          new JPanel(new GridBagLayout());

		        formulario.setBorder(
		          BorderFactory.createTitledBorder(
		            "Cadastro de clientes"
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
						  "CNH*:",
						  txtCnh
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
						  "Nacionalidade*:",
						  txtNacionalidade
						);
		        
		        
		        
		        adicionar(
						  formulario,
						  g,
						  6,
						  "E-mail:",
						  txtEmail
						);
		        
		        adicionar(
						  formulario,
						  g,
						  7,
						  "Celular*:",
						  txtCelular
						);
		        

		        txtId.setEditable(false);

		        txtDataNascimento.setToolTipText(
		          "Utilize o formato dd/MM/yyyy"
		        );
		        
		        g.gridx = 1;
		        g.gridy = 6;
		        formulario.add(chkResideBr, g);

		        JPanel botoes =
		          new JPanel(new FlowLayout(FlowLayout.LEFT));

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
		    		    txtCnh.setText("");
		    		    txtDataNascimento.setText("");
		    		    txtNacionalidade.setText("");
		    		    chkResideBr.setSelected(true);
		    		    txtEmail.setText("");
		    		    txtCelular.setText("");        
		    		    tabela.clearSelection();
		    }
		    
		    public void definirEdicao(boolean habilitado) {
		    			txtNome.setEditable(habilitado);
		    		    txtDataNascimento.setEditable(habilitado);
		    		    txtCpf.setEditable(habilitado);
		    		    txtCnh.setEditable(habilitado);
		    		    txtNacionalidade.setEditable(habilitado);
		    		    txtEmail.setEditable(habilitado);
		    		    txtCelular.setEditable(habilitado);
		      }
		    
		    public void mostrarCliente(Cliente cliente) {
		        txtId.setText(
		          String.valueOf(cliente.getId())
		        );
		        
		        		txtNome.setText(cliente.getNome());		        	    
		        	    txtCpf.setText(cliente.getCpf());
		        	    txtCnh.setText(cliente.getCnh());
		        	    txtDataNascimento.setText(
		        	    	      Validador.formatarData(
		        	    	    	        cliente.getDataNascimento()
		        	    	    	      )
		        	    	    	    );
		        	    txtNacionalidade.setText(cliente.getNacionalidade());
		        	    txtEmail.setText(cliente.getEmail());
		        	    txtCelular.setText(cliente.getCelular());
		        	    chkResideBr.setSelected(cliente.isResideBrasil());
		        
		      }
		    
		    
		    
		    public void preencherTabela(List<Cliente> lista) {
		        modelo.setRowCount(0);

		        int i;

		        for (i = 0; i < lista.size(); i++) {
		          Cliente cliente = lista.get(i);

		          modelo.addRow(
		            new Object[] {
		              Integer.valueOf(cliente.getId()),
		              cliente.getNome(),		              
		              cliente.getCpf(),
		              cliente.getCnh(),
		              Validador.formatarData(
		                cliente.getDataNascimento()
		              ),
		              cliente.getNacionalidade(),
		              cliente.getEmail(),
		              cliente.getCelular(),
		              cliente.isResideBrasil() ? "Sim" : "Nao"
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
		      

			public JTextField getTxtId() {
				return txtId;
			}

			public JTextField getTxtNome() {
				return txtNome;
			}

			public JTextField getTxtDataNascimento() {
				return txtDataNascimento;
			}

			public JTextField getTxtCpf() {
				return txtCpf;
			}

			public JTextField getTxtCnh() {
				return txtCnh;
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

			public JTable getTabela() {
				return tabela;
			}

			public JCheckBox getChkResideBr() {
				return chkResideBr;
			}
		    
		    
}
