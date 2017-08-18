package br.ufba.smelldetector.negocio;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

import br.ufba.smelldetector.model.DadosMetodoSmell;
import br.ufba.smelldetector.model.LimiarTecnica;

public class FiltrarMetodosSmellTest {
	
	@Test
	public void testFiltrarPorValoresPredefinidos() {

		List<LimiarTecnica> listaTecnicas = new ArrayList<>();
		listaTecnicas = CarregaSalvaArquivo.carregarLimiares("configuration\\br.ufs.smelldetector\\");

		ArrayList<String> listaPathProjetos = new ArrayList<>();
		listaPathProjetos.add("D:/Projetos/mobilemedia/MobileMedia01_OO");

		ArrayList<CKNumber> listaClasses = new ArrayList<>();
		for (String path : listaPathProjetos) {
			CKReport report = new CK().calculate(path);
			listaClasses.addAll(report.all());
		}
		
		HashMap<String, DadosMetodoSmell> metodosSmell = null;
		metodosSmell = FiltrarMetodosSmell.filtrar(listaClasses, listaTecnicas, metodosSmell);

		exibeMetodosLongos(metodosSmell.values(), "Filtrar por valor limiar");

		assertTrue(metodosSmell.keySet().size() > 0);
	}

	
	private void exibeMetodosLongos(Collection<DadosMetodoSmell> metodosSmell, String descricaoMetodo) {
		System.out.println("\n\n\n" + descricaoMetodo);
		System.out.println("Classe   |     M�todo       |     Linhas C�digo     |  CC  | Efferent |  NOP");
		for (DadosMetodoSmell metodoSmell : metodosSmell) {
			System.out.println(metodoSmell.getNomeClasse() + "  | " + metodoSmell.getNomeMetodo() + " | "
					+ metodoSmell.getLinesOfCode() + "|" + metodoSmell.getComplexity() + " | "
					+ metodoSmell.getEfferent() + " | " + metodoSmell.getNumberOfParameters());
		}
		System.out.println("Total de m�todos longos: " + metodosSmell.size());
	}

}
