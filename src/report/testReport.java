package report;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class testReport {

	public static void main(String[] args) throws JRException {
		
		// Lista do Objeto em si para a impressão
		List<EstoqueReport> lista = new ArrayList<EstoqueReport>();
		
		// Local e layout do relatório
		String relatorio = "relatorios/RelatorioClientes.jrxml";
		
		// compilacao do JRXML
		JasperReport report = JasperCompileManager
				.compileReport(relatorio);

		// preenchimento do relatorio, note que o metodo recebe 3 parametros:
		// 1 - o relatorio
		//
		// 2 - um Map, com parametros que sao passados ao relatorio
		// no momento do preenchimento. No nosso caso eh null, pois nao
		// estamos usando nenhum parametro
		//
		// 3 - o data source. Note que nao devemos passar a lista diretamente,
		// e sim "transformar" em um data source utilizando a classe
		// JRBeanCollectionDataSource
		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(lista));
		
		// Apenas abre um popUp com o relatório
		JasperViewer.viewReport(print);
		
		// exportacao do relatorio para outro formato, no caso PDF
		//JasperExportManager.exportReportToPdfFile(print,
		//		"relatorios/RelatorioClientes.pdf");

	}

}
