package br.com.pgc.service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import br.com.pgc.model.DadosMaternos;
import br.com.pgc.model.DadosRecemNascido;
import br.com.pgc.model.DadosRetinopatiaPrematuridade;
import static br.com.pgc.util.Util.dividirTempoInternacao;
import static br.com.pgc.util.Util.obterValor;

public class PlanilhaService {

	public static Sheet gerarPlanilhaPadrao(Workbook workbook) {
		Sheet sheet = workbook.createSheet("MinhaPlanilha");
		Row mescla = sheet.createRow(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 8));
		Cell mergedCell = mescla.createCell(2);
		mergedCell.setCellValue("DADOS MATERNOS");

		int startColumnIndex = 9;
		int endColumnIndex = 47;
		sheet.addMergedRegion(new CellRangeAddress(0, 0, startColumnIndex, endColumnIndex));
		Cell mergedCell2 = mescla.createCell(startColumnIndex);
		mergedCell2.setCellValue("DADOS RECÉM-NASCIDO");

		int startColumnIndex2 = 48;
		int endColumnIndex2 = 71;
		sheet.addMergedRegion(new CellRangeAddress(0, 0, startColumnIndex2, endColumnIndex2));
		Cell mergedCell3 = mescla.createCell(startColumnIndex2);
		mergedCell3.setCellValue("DADOS REFERENTES A RETINOPATIA DA PREMATURIDADE");

		// Estilo para a célula mesclada (opcional)
		CellStyle style = workbook.createCellStyle();
		preecherStyleTituloMesclado(workbook, style);
		mergedCell.setCellStyle(style);
		CellStyle style2 = workbook.createCellStyle();
		preecherStyleTituloMesclado(workbook, style2);
		style2.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		mergedCell2.setCellStyle(style2);
		CellStyle style3 = workbook.createCellStyle();
		preecherStyleTituloMesclado(workbook, style3);
		style3.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		mergedCell3.setCellStyle(style3);

		Row row = sheet.createRow(1);
		row.createCell(1).setCellValue("REGISTRO (HC)");
		row.createCell(2).setCellValue("IDADE");
		row.createCell(3).setCellValue("COR");
		row.createCell(4).setCellValue("GESTAÇÃO");
		row.createCell(5).setCellValue("HAS/DHEG");
		row.createCell(6).setCellValue("DM/DMG");
		row.createCell(7).setCellValue("CORTICOIDE");
		row.createCell(8).setCellValue("TIPOPARTO");

		row.createCell(9).setCellValue("DATANASC");
		row.createCell(10).setCellValue("SEXO");
		row.createCell(11).setCellValue("IDADEGEST");
		row.createCell(12).setCellValue("PESONASC(G)");
		row.createCell(13).setCellValue("PESO/IG");
		row.createCell(14).setCellValue("PESO14D(G)");
		row.createCell(15).setCellValue("PESO42D(G)");
		row.createCell(16).setCellValue("GPP");
		row.createCell(17).setCellValue("PESOMÍN");
		row.createCell(18).setCellValue("DV-PM");
		row.createCell(19).setCellValue("TEMP.REC.NUTRI");
		row.createCell(20).setCellValue("APGAR1'");
		row.createCell(21).setCellValue("APGAR5'");
		row.createCell(22).setCellValue("REANIMAÇÃO?");
		row.createCell(23).setCellValue("VM");
		row.createCell(24).setCellValue("TEMPOVM");
		row.createCell(25).setCellValue("VMATÉ14DV?");
		row.createCell(26).setCellValue("VMATÉ42DV?");
		row.createCell(27).setCellValue("OXIGENIO");
		row.createCell(28).setCellValue("TEMPOOXIGENIO");
		row.createCell(29).setCellValue("TRANSFUSÃO");
		row.createCell(30).setCellValue("NÚMEROCH");
		row.createCell(31).setCellValue("CHATÉ14DV?");
		row.createCell(32).setCellValue("CHATÉ42DV?");
		row.createCell(33).setCellValue("PCA");
		row.createCell(34).setCellValue("IBUPROFENO");
		row.createCell(35).setCellValue("CIRURGIAPCA");
		row.createCell(36).setCellValue("DBP");
		row.createCell(37).setCellValue("DBP28D");
		row.createCell(38).setCellValue("DBP36S");
		row.createCell(39).setCellValue("HPIV");
		row.createCell(40).setCellValue("PIORHPIV");
		row.createCell(41).setCellValue("SEPSE");
		row.createCell(42).setCellValue("NÚMEROSEPSE");
		row.createCell(43).setCellValue("CULTURA(+)");
		row.createCell(44).setCellValue("DATAINTER");
		row.createCell(45).setCellValue("DATASAÍDA");
		row.createCell(46).setCellValue("DIASINTERNAÇÃO");
		row.createCell(47).setCellValue("DESFECHO");

		row.createCell(48).setCellValue("DATA1ºFO");
		row.createCell(49).setCellValue("IC 1ºFO");
		row.createCell(50).setCellValue("IGC 1°FO");
		row.createCell(51).setCellValue("FOs TOTAIS");
		row.createCell(52).setCellValue("ROP?");
		row.createCell(53).setCellValue("DATADIAG");
		row.createCell(54).setCellValue("IC DIAG");
		row.createCell(55).setCellValue("IGC DIAG");
		row.createCell(56).setCellValue("QUAL FO DIAG");
		row.createCell(57).setCellValue("PIOROU?");
		row.createCell(58).setCellValue("EM QUAL FO PIOROU");
		row.createCell(59).setCellValue("PIORGRAU");
		row.createCell(60).setCellValue("ZONA");
		row.createCell(61).setCellValue("PLUS?");
		row.createCell(62).setCellValue("EXTENSÃO?");
		row.createCell(63).setCellValue("OLHO");
		row.createCell(64).setCellValue("TRATAMENTO");
		row.createCell(65).setCellValue("DATATTO");
		row.createCell(66).setCellValue("IC NO TTO");
		row.createCell(67).setCellValue("IGC NO TTO");
		row.createCell(68).setCellValue("TEMPO DX-TTO");
		row.createCell(69).setCellValue("REATIVAÇÃO");
		row.createCell(70).setCellValue("NOVO TTO");
		row.createCell(71).setCellValue("ROPSCORE");
		return sheet;
	}

	public static void gerarExcel(Sheet sheet, int linha, Map<DadosMaternos, String> dados,
			Map<DadosRecemNascido, String> dados2, Map<DadosRetinopatiaPrematuridade, String> dados3) {
		Row row2 = sheet.createRow(linha);
		preencherDadosMaternosPlanilha(dados, row2);
		preencherRecemNascidosPlanilha(dados2, row2);
		preencherPrematuridadePlanilha(dados3, row2);
	}

	private static void preencherDadosMaternosPlanilha(Map<DadosMaternos, String> dados, Row row2) {
		for (Map.Entry<DadosMaternos, String> entry : dados.entrySet()) {
			if (entry.getKey().getColuna() == -1)
				continue;
			row2.createCell(entry.getKey().getColuna()).setCellValue(obterValor(entry.getValue()));
		}
	}

	private static void preencherRecemNascidosPlanilha(Map<DadosRecemNascido, String> dados2, Row row2) {
		for (Map.Entry<DadosRecemNascido, String> entry2 : dados2.entrySet()) {
			if (entry2.getKey() == DadosRecemNascido.PESO_AO_NASCER) {
				Pattern padrao = Pattern.compile("g\\s*(\\w+)");
				Matcher matcher = padrao.matcher(entry2.getValue());
				if (matcher.find()) {
					row2.createCell(DadosRecemNascido.PIG_AIG_GIG.getColuna())
							.setCellValue(obterValor(matcher.group(1)));
				}
				Pattern padrao2 = Pattern.compile("(\\d+g)");
				Matcher matcher2 = padrao2.matcher(entry2.getValue());
				if (matcher2.find()) {
					row2.createCell(DadosRecemNascido.PESO_AO_NASCER.getColuna()).setCellValue(matcher2.group(1));
				}
				continue;
			}
			if (entry2.getKey() == DadosRecemNascido.PESO_2_SEMANA
					|| entry2.getKey() == DadosRecemNascido.PESO_6_SEMANA) {
				Pattern padrao = Pattern.compile("(\\d+g)");
				Matcher matcher = padrao.matcher(entry2.getValue());
				if (matcher.find()) {
					row2.createCell(entry2.getKey().getColuna()).setCellValue(matcher.group(1));
				}
				continue;
			}
			if (entry2.getKey() == DadosRecemNascido.PESO_MINIMO) {
				Pattern padrao = Pattern.compile("(\\d+)\\s+dia");
				Matcher matcher = padrao.matcher(entry2.getValue());
				if (matcher.find()) {
					row2.createCell(DadosRecemNascido.DIAS_VIDA.getColuna()).setCellValue(matcher.group(1));
				}
				Pattern padrao2 = Pattern.compile("(\\d+g)");
				Matcher matcher2 = padrao2.matcher(entry2.getValue());
				if (matcher2.find()) {
					row2.createCell(entry2.getKey().getColuna()).setCellValue(matcher2.group(1));
				}
				continue;
			}
			if (entry2.getKey() == DadosRecemNascido.TEMPO_RECUPERACAO_NUTRICIONAL) {
				Pattern padrao = Pattern.compile("(\\d+)\\s+dia");
				Matcher matcher = padrao.matcher(entry2.getValue());
				if (matcher.find()) {
					row2.createCell(entry2.getKey().getColuna()).setCellValue(matcher.group(1));
				}
				continue;
			}
			if (entry2.getKey() == DadosRecemNascido.INDICE_DE_APGAR) {
				Pattern padrao10Minuto = Pattern.compile("(\\d+)\\s+10 minuto");
				Matcher matcher10Minuto = padrao10Minuto.matcher(entry2.getValue());
				if (matcher10Minuto.find()) {
					row2.createCell(DadosRecemNascido.APGAR1.getColuna()).setCellValue(matcher10Minuto.group(1));
				}
				Pattern padrao50Minuto = Pattern.compile("(\\d+)\\s+50 minuto");
				Matcher matcher50Minuto = padrao50Minuto.matcher(entry2.getValue());
				if (matcher50Minuto.find()) {
					row2.createCell(DadosRecemNascido.APGAR5.getColuna()).setCellValue(matcher50Minuto.group(1));
				}
				continue;
			}
			if (entry2.getKey() == DadosRecemNascido.HEMORRAGIA_INTRAVENTRICULAR) {
				Pattern padrao = Pattern.compile("^(.*?),");
				Matcher matcher = padrao.matcher(entry2.getValue().trim());
				if (matcher.find()) {
					row2.createCell(DadosRecemNascido.HEMORRAGIA_INTRAVENTRICULAR.getColuna())
							.setCellValue(obterValor(matcher.group(1)));
				}
				continue;
			}
			if (entry2.getKey() == DadosRecemNascido.VENTILACAO_MECANICA) {
				Pattern padrao = Pattern.compile("([^,]+),");
				Matcher matcher = padrao.matcher(entry2.getValue());
				if (matcher.find()) {
					row2.createCell(DadosRecemNascido.VENTILACAO_MECANICA.getColuna())
							.setCellValue(obterValor(matcher.group(1)));
				}
				Pattern padrao2 = Pattern.compile("(\\d+)\\s+dia");
				Matcher matcher2 = padrao2.matcher(entry2.getValue());
				if (matcher2.find()) {
					row2.createCell(DadosRecemNascido.QTD_VENTILACAO_MECANICA.getColuna())
							.setCellValue(matcher2.group(1));
				}
				continue;
			}
			if (entry2.getKey() == DadosRecemNascido.OXIGENOTERAPIA) {
				Pattern padrao = Pattern.compile("([^,]+),");
				Matcher matcher = padrao.matcher(entry2.getValue());
				if (matcher.find()) {
					row2.createCell(DadosRecemNascido.OXIGENOTERAPIA.getColuna())
							.setCellValue(obterValor(matcher.group(1)));
				}
				Pattern padrao2 = Pattern.compile("(\\d+)\\s+dia");
				Matcher matcher2 = padrao2.matcher(entry2.getValue());
				if (matcher2.find()) {
					row2.createCell(DadosRecemNascido.QTD_OXIGENOTERAPIA.getColuna()).setCellValue(matcher2.group(1));
				}
				continue;
			}
			if (entry2.getKey() == DadosRecemNascido.CONCENTRADO_HEMACIAS) {
				Pattern padrao = Pattern.compile("([^,]+),");
				Matcher matcher = padrao.matcher(entry2.getValue());
				if (matcher.find()) {
					row2.createCell(DadosRecemNascido.CONCENTRADO_HEMACIAS.getColuna())
							.setCellValue(obterValor(matcher.group(1)));
				}
				Pattern padrao2 = Pattern.compile("(\\d+)\\s+vez(es)?");
				Matcher matcher2 = padrao2.matcher(entry2.getValue());
				if (matcher2.find()) {
					row2.createCell(DadosRecemNascido.QTD_DIAS_CONCENTRADO_HEMACIAS.getColuna())
							.setCellValue(matcher2.group(1));
				}
				continue;
			}
			if (entry2.getKey() == DadosRecemNascido.CLINICO || entry2.getKey() == DadosRecemNascido.CIRURGICO
					|| entry2.getKey() == DadosRecemNascido.DIAS_VIDA_28
					|| entry2.getKey() == DadosRecemNascido.SEMANAS_36) {
				String valorTratado = "SIM";
				if (entry2.getValue() == null || entry2.getValue().trim() == "") {
					valorTratado = "NÃO";
				}
				row2.createCell(entry2.getKey().getColuna()).setCellValue(obterValor(valorTratado));
				continue;
			}
			if (entry2.getKey() == DadosRecemNascido.TEMPO_INTERNACAO) {
				String[] partes = dividirTempoInternacao(entry2.getValue());
				row2.createCell(DadosRecemNascido.INICIO_TRATAMENTO.getColuna()).setCellValue(partes[0]);
				row2.createCell(DadosRecemNascido.FIM_TRATAMENTO.getColuna()).setCellValue(partes[1]);
				row2.createCell(DadosRecemNascido.TEMPO_INTERNACAO.getColuna()).setCellValue(partes[2]);
				continue;
			}
			if (entry2.getKey().getColuna() == -1)
				continue;
			row2.createCell(entry2.getKey().getColuna()).setCellValue(obterValor(entry2.getValue()));
		}
	}

	private static void preencherPrematuridadePlanilha(Map<DadosRetinopatiaPrematuridade, String> dados2, Row row2) {
		for (Map.Entry<DadosRetinopatiaPrematuridade, String> entry2 : dados2.entrySet()) {
			if (entry2.getKey() == DadosRetinopatiaPrematuridade.ROP_PRESENTE) {
				Pattern padrao = Pattern.compile("([^,]+),");
				Matcher matcher = padrao.matcher(entry2.getValue());
				if (matcher.find()) {
					row2.createCell(DadosRetinopatiaPrematuridade.ROP_PRESENTE.getColuna())
							.setCellValue(obterValor(matcher.group(1)));
				}
				continue;
			}
			if (entry2.getKey() == DadosRetinopatiaPrematuridade.IC_TRATAMENTO
					|| entry2.getKey() == DadosRetinopatiaPrematuridade.TEMPO_ENTRE_DIAGNOSTICO_INTERVENCAO_TRATAMENTO
					|| entry2.getKey() == DadosRetinopatiaPrematuridade.SE_ROP_PRESENTE
					|| entry2.getKey() == DadosRetinopatiaPrematuridade.IC_PRIMEIRO_EXAME
					|| entry2.getKey() == DadosRetinopatiaPrematuridade.IC_DIAGNOSTICO
					|| entry2.getKey() == DadosRetinopatiaPrematuridade.PIORA_DA_DOENCA_NO) {
				Pattern padrao = Pattern.compile("\\d+");
				Matcher matcher = padrao.matcher(entry2.getValue());
				if (matcher.find()) {
					row2.createCell(entry2.getKey().getColuna()).setCellValue(obterValor(matcher.group()));
				}
				continue;
			}
			if (entry2.getKey().getColuna() == -1)
				continue;
			row2.createCell(entry2.getKey().getColuna()).setCellValue(obterValor(entry2.getValue()));
		}
	}

	private static void preecherStyleTituloMesclado(Workbook workbook, CellStyle style) {
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		Font font = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 14);
		style.setFont(font);
		style.setFillForegroundColor(IndexedColors.CORAL.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	}

}
