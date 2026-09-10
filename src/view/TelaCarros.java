package view;

import controller.CarroController;
import model.Carro;
import model.Cliente;

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

public class TelaCarros extends JPanel {

  private final JTextField txtId = new JTextField(8),
    txtPlaca = new JTextField(30),
    txtModelo = new JTextField(30),
    txtGrupo = new JTextField(30),
    txtCambio = new JTextField(30),
    txtNumeroAssento = new JTextField(30),
    txtLocalAtual = new JTextField(30),
    txtLimiteQuilometragem = new JTextField(30),
    txtValorDiaria = new JTextField(30),    
    txtValorCaucao = new JTextField(30),
    txtStatus = new JTextField(30),
    txtPesquisa = new JTextField(25);
  
  private final JCheckBox chkGps = new JCheckBox("Tem GPS", false);
  
  private final DefaultTableModel modelo =
		    new DefaultTableModel(
		      new Object[] {
		        "ID",
		        "Placa",
		        "Modelo",
		        "Grupo",
		        "Câmbio",
		        "Número de assentos",
		        "GPS",
		        "Local atual",
		        "Limite de quilometragem",
		        "Valor da diária",
		        "Valor Caução",
		        "Status"
		      },
		      0
		    ) {
		      public boolean isCellEditable(int linha, int coluna) {
		        return false;
		      }
		    };
		    

		    private final JTable tabela = new JTable(modelo);
		    private final CarroController controller;

		    public TelaCarros() {
		      setLayout(new BorderLayout(8, 8));
		      setBorder(
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)
		      );

		      controller = new CarroController(this);

		      montar();
		      controller.carregarTabela();
		    }
		    
		    private void montar() {
		        JPanel formulario =
		          new JPanel(new GridBagLayout());

		        formulario.setBorder(
		          BorderFactory.createTitledBorder(
		            "Cadastro de carros"
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
		          "Placa*:",
		          txtPlaca
		        );

		        adicionar(
		          formulario,
		          g,
		          2,
		          "Modelo*:",
		          txtModelo
		        );

		        adicionar(
		          formulario,
		          g,
		          3,
		          "Grupo*:",
		          txtGrupo
		        );

		        adicionar(
		          formulario,
		          g,
		          4,
		          "Câmbio*:",
		          txtCambio
		        );

		        adicionar(
		          formulario,
		          g,
		          5,
		          "Número de assentos*:",
		          txtNumeroAssento
		        );
		        
		        adicionar(
				          formulario,
				          g,
				          6,
				          "Local Atual*:",
				          txtLocalAtual
				        );
		        
		        adicionar(
				          formulario,
				          g,
				          7,
				          "Limite de quilometragem*:",
				          txtLimiteQuilometragem
				        );
		        
		        adicionar(
				          formulario,
				          g,
				          8,
				          "Valor da diária*:",
				          txtValorDiaria
				        );
		        
		        adicionar(
				          formulario,
				          g,
				          9,
				          "Valor do caução*:",
				          txtValorCaucao
				        );
		        
		        adicionar(
				          formulario,
				          g,
				          10,
				          "Status*:",
				          txtStatus
				        );

		        txtId.setEditable(false);

		        g.gridx = 1;
		        g.gridy = 6;
		        formulario.add(chkGps, g);


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
		          new JLabel("Pesquisar por modelo:")
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
		    			txtPlaca.setText("");
		    		    txtModelo.setText("");
		    		    txtGrupo.setText("");
		    		    txtCambio.setText("");
		    		    txtNumeroAssento.setText("");
		    		    chkGps.setSelected(false);
		    		    txtLocalAtual.setText("");
		    		    txtLimiteQuilometragem.setText("");
		    		    txtValorDiaria.setText("");
		    		    txtValorCaucao.setText("");
		    		    txtStatus.setText("");
		    			tabela.clearSelection();
		      }

		      public void definirEdicao(boolean habilitado) {
		    	  			txtPlaca.setEditable(habilitado);
		    			    txtModelo.setEditable(habilitado);
		    			    txtGrupo.setEditable(habilitado);
		    			    txtCambio.setEditable(habilitado);
		    			    txtNumeroAssento.setEditable(habilitado);
		    	  			chkGps.setEnabled(habilitado);
		    	  			txtLocalAtual.setEditable(habilitado);
		    	  			txtLimiteQuilometragem.setEditable(habilitado);
		    	  			txtValorDiaria.setEditable(habilitado);
		    	  			txtValorCaucao.setEditable(habilitado);
		    	  			txtStatus.setEditable(habilitado);
		      }
		      
		      public void mostrarCliente(Carro carro) {
		    	    txtId.setText(
		    	      String.valueOf(carro.getId())
		    	    );
		    	    txtPlaca.setText(carro.getPlaca());
    			    txtModelo.setText(carro.getModeloCarro());
    			    txtGrupo.setText(carro.getGrupoCarro());
    			    txtCambio.setText(carro.getCambio());
    			    txtNumeroAssento.setText(
    			    	      String.valueOf(carro.getNumeroAssentos())
    			    	    );
		    	    chkGps.setSelected(carro.isGps());
		    	    txtLocalAtual.setText(carro.getLocalAtual());
		    	    txtLimiteQuilometragem.setText(carro.getLimiteQuilometragem());
		    	    txtValorDiaria.setText(String.valueOf(carro.getValorDiaria()));
		    	    txtValorCaucao.setText(String.valueOf(carro.getValorCaucao()));
		    	    txtStatus.setText(carro.getStatus());
		    	    
		    	  }
		      
		      public void preencherTabela(List<Carro> lista) {
		    	    modelo.setRowCount(0);

		    	    int i;

		    	    for (i = 0; i < lista.size(); i++) {
		    	      Carro carro = lista.get(i);

		    	      modelo.addRow(
		    	        new Object[] {
		    	          Integer.valueOf(carro.getId()),
		    	          carro.getPlaca(),
		    	          carro.getModeloCarro(),
		    	          carro.getGrupoCarro(),
		    	          carro.getCambio(),		    	          
		    	          Integer.valueOf(carro.getNumeroAssentos()),
		    	          carro.isGps() ? "Sim" : "Nao",
		    	          carro.getLocalAtual(),
		    	          carro.getLimiteQuilometragem(),
		    	          carro.getValorDiaria(),
		    	          carro.getValorCaucao(),
		    	          carro.getStatus(),
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

				  public JTextField getTxtPlaca() {
					  return txtPlaca;
				  }

				  public JTextField getTxtModelo() {
					  return txtModelo;
				  }

				  public JTextField getTxtGrupo() {
					  return txtGrupo;
				  }

				  public JTextField getTxtCambio() {
					  return txtCambio;
				  }

				  public JTextField getTxtNumeroAssento() {
					  return txtNumeroAssento;
				  }

				  public JTextField getTxtLocalAtual() {
					  return txtLocalAtual;
				  }

				  public JTextField getTxtLimiteQuilometragem() {
					  return txtLimiteQuilometragem;
				  }

				  public JTextField getTxtValorDiaria() {
					  return txtValorDiaria;
				  }

				  public JTextField getTxtValorCaucao() {
					  return txtValorCaucao;
				  }

				  public JTextField getTxtStatus() {
					  return txtStatus;
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
