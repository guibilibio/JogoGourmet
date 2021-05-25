package br.objective.app;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import br.objective.model.ListPratos;
import br.objective.model.Prato;

public class App extends javax.swing.JFrame {

	private final Prato massa = new Prato("Lasanha","");
	private final Prato naoMassa = new Prato("Bolo de Chocolate","");

	private final ListPratos pratosMassa = new ListPratos();
	private final ListPratos pratosNaoMassa = new ListPratos();

	private int resposta;

	public App() {         
		
		initComponents();

		this.pratosMassa.getPratos().add(massa);
		this.pratosNaoMassa.getPratos().add(naoMassa);
	}
 
	
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jBtOk = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Jogo Gourmet");

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
		jLabel1.setText("Pense em um prato que gosta");

		jBtOk.setText("OK");
		jBtOk.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBtOkActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGap(108, 108, 108)
										.addComponent(jLabel1))
								.addGroup(layout.createSequentialGroup()
										.addGap(145, 145, 145)
										.addComponent(jBtOk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(123, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap(64, Short.MAX_VALUE)
						.addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jBtOk)
						.addGap(19, 19, 19))
				);

		pack();
		setLocationRelativeTo(null);
	}

	private void jBtOkActionPerformed(java.awt.event.ActionEvent evt) {
		this.setVisible(false);

		initJogo();

		this.setVisible(true);

	}

	public static void main(String ... args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			System.out.println("Erro ao carregar LookAndFeel");
		}

		java.awt.EventQueue.invokeLater(() -> {
			new App().setVisible(true);
		});
	}

	private javax.swing.JButton jBtOk;

	private javax.swing.JLabel jLabel1;

	private void initJogo() {
		resposta = JOptionPane.showConfirmDialog(rootPane, "O prato que você pensou é massa ?", "Confirm", JOptionPane.YES_NO_OPTION);

		if (resposta == JOptionPane.YES_OPTION) {
			advinharPratos(pratosMassa);
			return;
		}

		advinharPratos(pratosNaoMassa);        
	}

	private void advinharPratos(ListPratos pratos) {

		int contador;
		int tamanhoList = pratos.getPratos().size() - 1;

		for (contador = tamanhoList; contador > 0; contador--) {
			resposta = perguntaPrato(pratos, contador, true);


			if (resposta == JOptionPane.YES_OPTION) {

				resposta = perguntaPrato(pratos, contador, false);

				if (resposta == JOptionPane.YES_OPTION) {

					acertei();
					break;                    
				} else if ((resposta == JOptionPane.NO_OPTION) && (contador == 0)) {

					adicionarPrato(pratos, contador);
					break;                    
				}
			}
		}

		if (contador == 0) {

			resposta = perguntaPrato(pratos, contador, false);

			if (resposta == JOptionPane.YES_OPTION) {

				acertei();
				return;
			}
			adicionarPrato(pratos, contador);            
		}
	}

	private void adicionarPrato(ListPratos pratos, int ordemPrato) {                
		pratos.getPratos().add(montaObjetoPratoNovo(pratos, ordemPrato));
	}

	private void acertei() {
		JOptionPane.showMessageDialog(null, "Acertei de novo!");
	}

	private int perguntaPrato(ListPratos pratos, int contador, boolean caracteristica) {
		if (caracteristica) {
			return JOptionPane.showConfirmDialog(rootPane, "O prato que pensou é ".concat(pratos.getPratos().get(contador).getCaracteristica()).concat(" ?"), "Confirm", JOptionPane.YES_NO_OPTION);
		}

		return JOptionPane.showConfirmDialog(rootPane, "O prato que pensou é ".concat(pratos.getPratos().get(contador).getDescricao()).concat(" ?"), "Confirm", JOptionPane.YES_NO_OPTION);
	}

	private Prato montaObjetoPratoNovo(ListPratos pratos, int ordemPrato) {
		
		String descricaoPrato = JOptionPane.showInputDialog(rootPane, "Qual prato você pensou ?", "App", JOptionPane.QUESTION_MESSAGE);
		String caracteristicaPrato = JOptionPane.showInputDialog(rootPane, descricaoPrato.concat(" não é ________ mas ").concat(pratos.getPratos().get(ordemPrato).getDescricao()).concat(" é."), "Complete", JOptionPane.QUESTION_MESSAGE);

		Prato prato = new Prato(descricaoPrato, caracteristicaPrato);

		return prato;        
	}
}
