package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.Main;
import net.sf.jasperreports.engine.JRException;
import report.EstoqueReport;
import report.Relatorios;
import repositorio.CategoriaDao;
import repositorio.EstoqueDao;
import repositorio.ProdutoDao;
import classes.Categoria;
import classes.Estoque;
import classes.Produto;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ModuloRelatorios {
	
	private static Dimension preferredSize;
	private static Border defaultBorder;
	private static JButton alterButton;
	private static JButton cancelButton;
	private static SystemInterface systemInterface;
	
	public ModuloRelatorios(SystemInterface systemInterface) {
		ModuloRelatorios.systemInterface = systemInterface;
		preferredSize = systemInterface.getSystemInterfaceDimension();
		defaultBorder = Main.isBrunoTesting ? BorderFactory.createRaisedBevelBorder() : BorderFactory.createEmptyBorder();
	}
	
	public JPanel relatorioConferenciaEstoque(Categoria categoriaSelecionada) {
		JPanel panelLevel0 = new JPanel(new BorderLayout());
		Common.makeLateralBorders(panelLevel0, preferredSize, defaultBorder);
		
		JPanel panelLevel1 = new JPanel(new BorderLayout());
		panelLevel0.add(panelLevel1, BorderLayout.CENTER);
		
		JPanel panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.NORTH);
		
		JLabel placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		placeHolder.setBorder(defaultBorder);
		panelLevel2.add(placeHolder, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Conferência de Estoque");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 20));
		panelLevel2.add(placeHolder, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 16)));
		placeHolder.setBorder(defaultBorder);
		panelLevel2.add(placeHolder, BorderLayout.SOUTH);
		
		panelLevel2 = new JPanel(new BorderLayout());
		panelLevel1.add(panelLevel2, BorderLayout.CENTER);
		
		JPanel panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.NORTH);
		
		placeHolder = new JLabel("Categoria");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getHeight() / 64 + (int) (preferredSize.getWidth() / 3)), 
			(int) (preferredSize.getHeight() / 32)));
		panelLevel3.add(placeHolder, BorderLayout.WEST);
		
		placeHolder = new JLabel("Data de Inclusão"); 
		placeHolder.setBorder(defaultBorder);
		placeHolder.setForeground(placeHolder.getBackground());
		placeHolder.setFont(new Font(null, Font.PLAIN + Font.BOLD, placeHolder.getFont().getSize() + 7));
		panelLevel3.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.CENTER);
		JPanel panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.NORTH);
		
		String lista[] = null;
		List<Categoria> categorias = null;
		
		try {
			CategoriaDao categoriaDao = new CategoriaDao(systemInterface.getSystemInterfaceDatabaseURL());
			categorias = categoriaDao.getAll();
		} catch (SQLException e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de categorias!");
		} finally {
			if(categorias == null || categorias.size() == 0) {
				categorias = new ArrayList<Categoria>();
				categorias.add(new Categoria("Nenhum valor encontrado"));
				String message = categorias == null ? "Houve um erro ao recuperar a lista de categorias!" : "Nenhuma categoria encontrada!";
				systemInterface.getSystemInterfaceLabelStatus().setText(message);
			}
			boolean isNull = categorias.get(0).getCategoriaDesc().equalsIgnoreCase("Nenhum valor encontrado");
			lista = new String[isNull ? categorias.size() : categorias.size() + 1];
			
			if(!isNull) {
				lista[0] = "Todas as categorias";
			}
			for(Categoria c : categorias)
				lista[categorias.indexOf(c) + (isNull ? 0 : 1)] = c.getCategoriaDesc();
		}
		JComboBox comboBoxCategorias = new JComboBox(lista);
		comboBoxCategorias.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 4), (int) (preferredSize.getHeight() / 32)));
		comboBoxCategorias.setBackground(Color.white);
		comboBoxCategorias.setEditable(false);
		comboBoxCategorias.setSelectedIndex(categoriaSelecionada == null ? 0 : categorias.indexOf(categoriaSelecionada));
		panelLevel4.add(comboBoxCategorias, BorderLayout.WEST);
		
		JPanel panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.CENTER);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 3) + (int) (preferredSize.getWidth() / 64) - 
			(int) (preferredSize.getWidth() / 4) - 5, (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.WEST);
		
		panelLevel3 = new JPanel(new BorderLayout());
		panelLevel2.add(panelLevel3, BorderLayout.SOUTH);
		panelLevel4 = new JPanel(new BorderLayout());
		panelLevel3.add(panelLevel4, BorderLayout.SOUTH);
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.NORTH);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.SOUTH);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth()), (int) (preferredSize.getHeight() / 32)));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		panelLevel5 = new JPanel(new BorderLayout());
		panelLevel4.add(panelLevel5, BorderLayout.EAST);
		
		cancelButton = new JButton("Voltar");
		cancelButton.setBackground(Color.white);
		cancelButton.setForeground(Color.black);
		cancelButton.addMouseListener(systemInterface.new HandlerHomeButton());
		cancelButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(cancelButton, BorderLayout.EAST);
		
		placeHolder = new JLabel("");
		placeHolder.setBorder(defaultBorder);
		placeHolder.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 64),  (int) (preferredSize.getHeight() / 16) - 
				(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(placeHolder, BorderLayout.CENTER);
		
		alterButton = new JButton("Gerar");
		float[] hsbColor = Color.RGBtoHSB(51, 122, 183, null); 
		alterButton.setBackground(Color.getHSBColor(hsbColor[0], hsbColor[1], hsbColor[2]));
		alterButton.setForeground(Color.white);
		alterButton.addMouseListener(new HandlerConferenciaEstoque(systemInterface, comboBoxCategorias, categorias));
		alterButton.setPreferredSize(new Dimension((int) (preferredSize.getWidth() / 8), (int) (preferredSize.getHeight() / 16) - 
			(int) (preferredSize.getHeight() / 64)));
		panelLevel5.add(alterButton, BorderLayout.WEST);
		
		return panelLevel0;
	}
	
	protected static class HandlerConferenciaEstoque implements MouseListener {
		
		private SystemInterface systemInterface;
		private JComboBox source;
		private List<Categoria> categorias;
		
		public HandlerConferenciaEstoque(SystemInterface systemInterface, JComboBox source, List<Categoria> categorias) {
			this.systemInterface = systemInterface;
			this.source = source;
			this.categorias = categorias;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!source.getSelectedItem().toString().equalsIgnoreCase("Nenhum valor encontrado")) {
				List<Estoque> listaEstoque = null;
				
				if(source.getSelectedItem().toString().equalsIgnoreCase("Todas as categorias")) {
					try {
						listaEstoque = new EstoqueDao(systemInterface.getSystemInterfaceDatabaseURL()).getAll();
					} catch (SQLException e1) {
						systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de estoque!");
					}
				} else {
					try {
						listaEstoque = new EstoqueDao(systemInterface.getSystemInterfaceDatabaseURL()).getForValue("estoque_categoria_id", 
								String.valueOf(categorias.get(source.getSelectedIndex() - 1).getCategoriaId()));
					} catch (SQLException e1) {
						systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de estoque!");
					}
				}
				List<EstoqueReport> lista = new ArrayList<EstoqueReport>();
				
				for(Estoque estoque : listaEstoque) {
					Produto prod = null;
					try {
						prod = new ProdutoDao(systemInterface.getSystemInterfaceDatabaseURL()).getById(estoque.getEstoque_produto_id());
					} catch (SQLException e1) {
						systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de estoque!");
					}
					Categoria categ = null;
					try {
						categ = new CategoriaDao(systemInterface.getSystemInterfaceDatabaseURL()).getById(prod.getProdutoCategoria());
					} catch (SQLException e1) {
						systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de estoque!");
					}			
					String ativo = prod.getProdutoAtivo() == 1 ? "Sim": "Não";
					Double total = estoque.getEstoque_produto_quantidade() * prod.getProdutoPreco();
					DecimalFormat decimal = new DecimalFormat("0.00");
					
					lista.add(new EstoqueReport(
							prod.getProdutoDesc(), 
							categ.getCategoriaDesc(), 
							estoque.getEstoque_produto_quantidade(), 
							"R$ " + decimal.format(prod.getProdutoPreco()), 
							"R$ " + decimal.format(total), 
							ativo)
					);
				}
				
				try {
					Relatorios.gerarRelatorioEstoque(lista);
				} catch (JRException e1) {
					systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de estoque!");
				} catch (IOException e1) {
					systemInterface.getSystemInterfaceLabelStatus().setText("Houve um erro ao recuperar a lista de estoque!");
				}
			} else {
				systemInterface.getSystemInterfaceLabelStatus().setText("Não há categoria válida a ser alterada!");
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText("Gera o relatório de conferência de estoque com a categoria selecionada");
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			systemInterface.getSystemInterfaceLabelStatus().setText(systemInterface.getSystemInterfaceStatusMessage());
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			mouseEntered(e);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			mouseExited(e);
		}
	}
}
