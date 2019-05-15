package com.github.drminer.study;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

import com.github.drminer.visitor.ClassVisitorDR;
import com.github.drminer.visitor.MethodVisitorCK;

public class WebBudgetStudy implements Study {

	public static void main(String[] args) {
		new RepoDriller().start(new WebBudgetStudy());
	}

	public void execute() {
		new RepositoryMining().in(GitRepository.singleProject("D:/Projetos/_Web/web-budget"))
				.through(Commits.onlyInHead()).withThreads(1)
				.process(new ClassVisitorDR(), new CSVFile("D:/Projetos/_Web/web-budget/web-budget-drs.csv")).mine();

		new RepositoryMining().in(GitRepository.singleProject("D:/Projetos/_Web/web-budget"))
				.through(Commits.onlyInHead()).withThreads(1)
				.process(new MethodVisitorCK(), new CSVFile("D:/Projetos/_Web/web-budget/web-budget-metrics.csv"))
				.mine();
	}

}
