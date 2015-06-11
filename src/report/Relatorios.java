package report;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorios {

	public static void gerarRelatorioEstoque(List<EstoqueReport> lista) throws JRException, IOException
	{
		String relatorio = "reports/reportEstoque.jrxml";
		
		JasperReport report = JasperCompileManager
				.compileReport(relatorio);
		
		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(lista, true));
					
		String systemIconImagePath = new File("lib/.").getCanonicalPath() + "/" + "CDT_icon.png";
		
		JasperViewer viewer = new JasperViewer(print, false);
		viewer.setTitle("SCVE-CdT - Relatório de Conferência de Estoque");
		viewer.setIconImage((new ImageIcon(systemIconImagePath)).getImage());
		viewer.setVisible(true);	 	
	}	
	
	public static void gerarRelatorioVendas(List<Operacao> lista) throws JRException, IOException
	{
		String relatorio = "reports/reportVenda.jrxml";
		
		JasperReport report = JasperCompileManager
				.compileReport(relatorio);
		
		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(lista, true));
					
		String systemIconImagePath = new File("lib/.").getCanonicalPath() + "/" + "CDT_icon.png";
		
		JasperViewer viewer = new JasperViewer(print, false);
		viewer.setTitle("SCVE-CdT - Relatório de Conferência de Vendas");
		viewer.setIconImage((new ImageIcon(systemIconImagePath)).getImage());
		viewer.setVisible(true);	 	
	}
}
