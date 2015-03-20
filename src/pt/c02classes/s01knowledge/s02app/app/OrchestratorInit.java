package pt.c02classes.s01knowledge.s02app.app;

import java.util.Scanner;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerMaze;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;
import pt.c02classes.s01knowledge.s02app.actors.ResponderMaze;

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
		
		String arg = scanner.nextLine();
		
		while (!arg.equalsIgnoreCase("N")) {
			   
			   switch (arg.toUpperCase()) {
			      case "A": System.out.println("Qual o animal desejado?");
			      			arg = scanner.nextLine();
			      			base.setScenario("animals");
			      			String listaAnimais[] = base.listaNomes();
			      				stat = new Statistics();
			      				resp = new ResponderAnimals(stat, arg);
			      				enq = new EnquirerAnimals();
			      				enq.connect(resp);
			      				enq.discover();
			      				System.out.println("----------------------------------------------------------------------------------------\n");
			      	        
			    	  		break;
			    	  		
			      case "M": System.out.println("Qual o mapa desejado?");
			      			base.setScenario("maze");
			      			arg = scanner.nextLine();
			      			stat = new Statistics();
			      			resp = new ResponderMaze(stat, arg);
			      			enq = new EnquirerMaze();
			      			enq.connect(resp);
			      			enq.discover();
			      			System.out.println("----------------------------------------------------------------------------------------\n");
			                break;
			   }
			   
			   System.out.println("Qual o desafio desejado?");
			   System.out.println("(A)nimals, (M)aze, (N)enhum");
			   arg = scanner.nextLine();
		}		
	}
}
