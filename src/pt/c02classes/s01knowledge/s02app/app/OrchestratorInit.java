package pt.c02classes.s01knowledge.s02app.app;

import java.util.Scanner;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;

public class OrchestratorInit
{
	public static void main(String[] args)
	{
		IEnquirer enq;
		IResponder resp;
		IStatistics stat;
		Scanner scanner = new Scanner(System.in);
		IBaseConhecimento base = new BaseConhecimento();
		
		System.out.println("Qual o desafio desejado?");
		System.out.println("(A)nimals, (M)aze, (N)enhum");
		
		String tipo = scanner.nextLine();
		
		while (!tipo.equalsIgnoreCase("N")) {
			   
			   String pc = scanner.nextLine();
			   switch (tipo.toUpperCase()) {
			      case "A": System.out.println("Qual o animal desejado?");
			      			tipo = scanner.nextLine();
			      			base.setScenario("animals");
			      			String listaAnimais[] = base.listaNomes();
			      	        for (int animal = 0; animal < listaAnimais.length; animal++) {
			      				System.out.println("Enquirer com " + listaAnimais[animal] + "...");
			      				stat = new Statistics();
			      				resp = new ResponderAnimals(stat, listaAnimais[animal]);
			      				enq = new EnquirerAnimals();
			      				enq.connect(resp);
			      				enq.discover();
			      				System.out.println("----------------------------------------------------------------------------------------\n");
			      	        }
			    	  		break;
			    	  		
			      case "M": System.out.println("Qual o mapa desejado?");
			      			tipo = scanner.nextLine();
			                break;
			   }
			   
			   System.out.println("Qual o desafio desejado?");
			   System.out.println("(A)nimals, (M)aze, (N)enhum");
			   tipo = scanner.nextLine();
			
		
		
		}		
	}
}
