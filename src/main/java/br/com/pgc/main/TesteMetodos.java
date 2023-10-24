package br.com.pgc.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import br.com.pgc.model.DadosMaternos;
import br.com.pgc.model.DadosRecemNascido;
import br.com.pgc.model.DadosRetinopatiaPrematuridade;
import br.com.pgc.service.DadosMaternosService;
import br.com.pgc.service.DadosPrematuridadeService;
import br.com.pgc.service.DadosRecemNascidoService;
import br.com.pgc.service.PlanilhaService;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class TesteMetodos {
		
	public static void main(String[] args) {		
			Scanner scanner = new Scanner(System.in);
			System.out.print("Insira o caminho da pasta onde estão os arquivos: ");
			String texto = scanner.nextLine();
			String directoryPath = texto;
		    File directory = new File(directoryPath);
		    File[] files = directory.listFiles();
		    if (files != null) {
		    	Workbook workbook = new XSSFWorkbook();
		    	new PlanilhaService();
				Sheet sheet = PlanilhaService.gerarPlanilhaPadrao(workbook);
		    	int linha = 2;
		        for (File file : files) {		            	
		            if (file.isFile() && file.getName().endsWith(".docx")) {
		            	System.out.print("\nProcessando: " + file.getName());
		                try {
		                 FileInputStream fis = new FileInputStream(file);
		    			 XWPFDocument document = new XWPFDocument(fis);		    
		    	            StringBuilder content = new StringBuilder();		    
		    	            List<XWPFTable> tables = document.getTables();
		    	            for (XWPFTable table : tables) {
		    	                List<XWPFTableRow> rows = table.getRows();
		    	                for (XWPFTableRow row : rows) {
		    	                    List<XWPFTableCell> cells = row.getTableCells();
		    	                    for (XWPFTableCell cell : cells) {
		    	                        content.append(cell.getText()).append(" ");
		    	                    }
		    	                    content.append("\n");
		    	                }
		    	            }		        
		    	        document.close();
		    	        fis.close();
		    	        //System.out.println("DADOS MATERNOS");
		    	        String entrada = new DadosMaternosService().getDadosMaternos(content.toString());
		    			Map<DadosMaternos, String> dados = new DadosMaternosService().extractDadosMaternos(entrada);
		    			//System.out.println("DADOS DO RECÉM-NASCIDO");
		    	        String entrada2 = new DadosRecemNascidoService().getDadosRecemNascido(content.toString());
		    			Map<DadosRecemNascido, String> dados2 = new DadosRecemNascidoService().extractRecemNascidos(entrada2);
		    			//System.out.println("DADOS REFERENTES À RETINOPATIA DA PREMATURIDADE");
		    			String entrada3 = new DadosPrematuridadeService().getDadosPrematuridade(content.toString());
		    			Map<DadosRetinopatiaPrematuridade, String> dados3 = new DadosPrematuridadeService().extractPrematuridade(entrada3);
		    			PlanilhaService.gerarExcel(sheet, linha, dados, dados2, dados3);
		    			linha ++;
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		            }
		        }
		        Date dataAtual = new Date();
	            SimpleDateFormat formatoDataHora = new SimpleDateFormat("ddMMYYYY_HHmm");
	            String dataHoraFormatada = formatoDataHora.format(dataAtual);
		        try (FileOutputStream outputStream = new FileOutputStream(texto + "\\planilha_"+dataHoraFormatada+".xlsx")) {
		            workbook.write(outputStream);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    } else {
		        System.out.println("\nO diretório não existe ou não é uma pasta.");
		    }
		    scanner.close();
		    System.out.println("\nPROCESSO FINALIZADO.");
	}
	
	
	
	

	

	

	
	
	
	

}