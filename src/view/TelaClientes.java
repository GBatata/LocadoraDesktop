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
    txtDataNascimento = new JTextField(12),
    txtCpf = new JTextField(16),
    txtCnh = new JTextField(16),
    txtNacionalidade = new JTextField(30),
    txtEmail = new JTextField(30),
    txtCelular = new JTextField(30),
    txtCartaoCdt = new JTextField(30),
    txtCalcao = new JTextField(30),
    
    txtLocalRetirada = new JTextField(30),
    txtLocalDevolucao = new JTextField(30),
    txtDataRetirada = new JTextField(30),
    txtDataDevolucao = new JTextField(30),
    txtHoraRetirada = new JTextField(30),
    txtHoraDevolucao = new JTextField(30),
    txtGrupoCarro = new JTextField(30),
    txtProtecao = new JTextField(30),
    txtLimiteQuilometragem = new JTextField(30),
    txtCondutor = new JTextField(30),
    txtAssentoAdicional = new JTextField(30),
    txtIdFuncionario = new JTextField(30),
  
    txtPesquisa = new JTextField(25);
  
  private final DefaultTableModel modelo =
		    new DefaultTableModel(
		      new Object[] {
		        "ID",
		        "Nome",
		        "Data de nascimento",
		        "CPF",
		        "CNH",		        
		        "Nacionalidade",
		        "E-mail",
		        "Celular",
		        "Cartão de crédito",
		        "Calção",
		        "Local de retirada",
		        "Local de devolução",
		        "Data da retirada",
		        "Hora da retirada",
		        "Data da devolução",
		        "Hora da devolução",
		        "Grupo de carros",
		        "Proteção",
		        "Limite de quilometragem",
		        "Condutor adicional",
		        "Assento adicional",
		        "Id do funcionário"
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
				          "Data de nascimento*:",
				          txtDataNascimento
				        );

		        adicionar(
				          formulario,
				          g,
				          3,
				          "CPF*:",
				          txtCpf
				        );
		        
		        adicionar(
						  formulario,
						  g,
						  4,
						  "CNH*:",
						  txtCnh
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
		        
		        adicionar(
						  formulario,
						  g,
						  8,
						  "Cartão de Crédito*:",
						  txtCartaoCdt
						);
		        
		        adicionar(
						  formulario,
						  g,
						  9,
						  "Calção*:",
						  txtCalcao
						);
		        
		        adicionar(
						  formulario,
						  g,
						  10,
						  "Local de Retirada*:",
						  txtLocalRetirada
						);
		        
		        adicionar(
						  formulario,
						  g,
						  11,
						  "Local de Devolução*:",
						  txtLocalDevolucao
						);
		        
		        adicionar(
						  formulario,
						  g,
						  12,
						  "Data de Retirada*:",
						  txtDataRetirada
						);
		        
		        adicionar(
						  formulario,
						  g,
						  13,
						  "Hora de Retirada*:",
						  txtHoraRetirada
						);
		        
		        adicionar(
						  formulario,
						  g,
						  14,
						  "Data de Devolução*:",
						  txtDataDevolucao
						);
		        
		        adicionar(
						  formulario,
						  g,
						  15,
						  "Hora de Devolução*:",
						  txtHoraDevolucao
						);
		        
		        adicionar(
						  formulario,
						  g,
						  16,
						  "Grupo do carro*:",
						  txtGrupoCarro
						);
		        
		        adicionar(
						  formulario,
						  g,
						  17,
						  "Proteção*:",
						  txtProtecao
						);
		        
		        adicionar(
						  formulario,
						  g,
						  18,
						  "Limite de quilometragem*:",
						  txtLimiteQuilometragem
						);
		        
		        adicionar(
						  formulario,
						  g,
						  19,
						  "Condutor adicional*:",
						  txtCondutor
						);
		        
		        adicionar(
						  formulario,
						  g,
						  20,
						  "Assento adicional*:",
						  txtAssentoAdicional
						);
		        
		        adicionar(
						  formulario,
						  g,
						  21,
						  "ID do funcionário*:",
						  txtIdFuncionario
						);
		        

		        txtId.setEditable(false);

		        txtDataNascimento.setToolTipText(
		          "Utilize o formato dd/MM/yyyy"
		        );

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
		    		    txtDataNascimento.setText("");
		    		    txtCpf.setText("");
		    		    txtCnh.setText("");
		    		    txtNacionalidade.setText("");
		    		    txtEmail.setText("");
		    		    txtCelular.setText("");
		    		    txtCartaoCdt.setText("");
		    		    txtCalcao.setText("");
		    		    
		    		    txtLocalRetirada.setText("");
		    		    txtLocalDevolucao.setText("");
		    		    txtDataRetirada.setText("");
		    		    txtDataDevolucao.setText("");
		    		    txtHoraRetirada.setText("");
		    		    txtHoraDevolucao.setText("");
		    		    txtGrupoCarro.setText("");
		    		    txtProtecao.setText("");
		    		    txtLimiteQuilometragem.setText("");
		    		    txtCondutor.setText("");
		    		    txtAssentoAdicional.setText("");
		    		    txtIdFuncionario.setText("");	        
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
		    		    txtCartaoCdt.setEditable(habilitado);
		    		    txtCalcao.setEditable(habilitado);
		    		    
		    		    txtLocalRetirada.setEditable(habilitado);
		    		    txtLocalDevolucao.setEditable(habilitado);
		    		    txtDataRetirada.setEditable(habilitado);
		    		    txtDataDevolucao.setEditable(habilitado);
		    		    txtHoraRetirada.setEditable(habilitado);
		    		    txtHoraDevolucao.setEditable(habilitado);
		    		    txtGrupoCarro.setEditable(habilitado);
		    		    txtProtecao.setEditable(habilitado);
		    		    txtLimiteQuilometragem.setEditable(habilitado);
		    		    txtCondutor.setEditable(habilitado);
		    		    txtAssentoAdicional.setEditable(habilitado);
		    		    txtIdFuncionario.setEditable(habilitado);
		      }
		    
		    public void mostrarCliente(Cliente cliente) {
		        txtId.setText(
		          String.valueOf(cliente.getId())
		        );
		        
		        		txtNome.setText(cliente.getNome());
		        	    txtDataNascimento.setText(
		        	    	      Validador.formatarData(
		        	    	    	        cliente.getData_nascimento()
		        	    	    	      )
		        	    	    	    );
		        	    txtCpf.setText(cliente.getCpf());
		        	    txtCnh.setText(cliente.getCnh());
		        	    txtNacionalidade.setText(cliente.getNacionalidade());
		        	    txtEmail.setText(cliente.getEmail());
		        	    txtCelular.setText(cliente.getCelular());
		        	    txtCartaoCdt.setText("");
		        	    txtCalcao.setText(cliente.getCalcao());
		        	    
		        	    txtLocalRetirada.setText(cliente.getLocal_retirada());
		        	    txtLocalDevolucao.setText(cliente.getLocal_devolucao());
		        	    txtDataRetirada.setDate(Validador.formatarData(
		        	            cliente.getData_retirada()
		        	    	      )
		        	    	    );
		        	    txtDataDevolucao.setText(Validador.formatarData(
		        	            cliente.getData_devolucao()
		        	    	      )
		        	    	    );
		        	    txtHoraRetirada.setText(Validador.formatarHora(
		        	            cliente.getHora_retirada()
		        	    	      )
		        	    	    );
		        	    txtHoraDevolucao.setText(Validador.formatarHora(
		        	            cliente.getHora_devolucao()
		        	    	      )
		        	    	    );
		        	    txtGrupoCarro.setText(cliente.getGrupo_carro());
		        	    txtProtecao.setText(cliente.getProtecao());
		        	    txtLimiteQuilometragem.setText(cliente.getLimite_quilometragem());
		        	    txtCondutor.setText(cliente.getCondutor_adicional());
		        	    txtAssentoAdicional.setText(cliente.getAssento_adicional());
		        	    txtIdFuncionario.setText(
		      		          String.valueOf(cliente.getId_funcionario())
		        		        );

		        
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
		              Validador.formatarData(
		                cliente.getData_nascimento()
		              ),
		              cliente.getCpf(),
		              cliente.getCnh(),
		              cliente.getNacionalidade(),
		              cliente.getEmail(),
		              cliente.getCelular(),
		              cliente.getCartao_credito(),
		              cliente.getCalcao(),
		              cliente.getLocal_retirada(),
		              cliente.getLocal_devolucao(),
		              Validador.formatarData(
		            	cliente.getData_retirada()
		              ),
		              Validador.formatarData(
				       	cliente.getData_devolucao()
				      ),
		              Validador.formatarHora(
				        cliente.getHora_retirada()
				      ),
		              Validador.formatarHora(
				        cliente.getHora_devolucao()
				      ),
		              cliente.getGrupo_carro(),
		              cliente.getProtecao(),
		              cliente.getLimite_quilometragem(),
		              cliente.getCondutor_adicional(),
		              cliente.getAssento_adicional(),
		              Integer.valueOf(cliente.getId_funcionario()),
		            }
		          );
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

			public JTextField getTxtCartaoCdt() {
				return txtCartaoCdt;
			}

			public JTextField getTxtCalcao() {
				return txtCalcao;
			}

			public JTextField getTxtLocalRetirada() {
				return txtLocalRetirada;
			}

			public JTextField getTxtLocalDevolucao() {
				return txtLocalDevolucao;
			}

			public JTextField getTxtDataRetirada() {
				return txtDataRetirada;
			}

			public JTextField getTxtDataDevolucao() {
				return txtDataDevolucao;
			}

			public JTextField getTxtHoraRetirada() {
				return txtHoraRetirada;
			}

			public JTextField getTxtHoraDevolucao() {
				return txtHoraDevolucao;
			}

			public JTextField getTxtGrupoCarro() {
				return txtGrupoCarro;
			}

			public JTextField getTxtProtecao() {
				return txtProtecao;
			}

			public JTextField getTxtLimiteQuilometragem() {
				return txtLimiteQuilometragem;
			}

			public JTextField getTxtCondutor() {
				return txtCondutor;
			}

			public JTextField getTxtAssentoAdicional() {
				return txtAssentoAdicional;
			}

			public JTextField getTxtIdFuncionario() {
				return txtIdFuncionario;
			}

			public JTextField getTxtPesquisa() {
				return txtPesquisa;
			}

			public JTable getTabela() {
				return tabela;
			}
		    
		    
}
