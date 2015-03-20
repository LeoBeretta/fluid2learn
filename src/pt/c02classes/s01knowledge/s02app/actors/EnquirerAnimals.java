package pt.c02classes.s01knowledge.s02app.actors;

import java.util.*;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IDeclaracao;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerAnimals implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {
        IBaseConhecimento bc = new BaseConhecimento();
        IObjetoConhecimento obj;
		
		bc.setScenario("animals");
		
		String[] listaAnimais;
		
        Hashtable<String, String> perguntasRepetidas = new Hashtable<String, String>();
                
        int animal;
        
        boolean naoAchei = true;
        
        String resposta = null;
        
        listaAnimais = bc.listaNomes();
        for (animal = 0; animal < listaAnimais.length && naoAchei; animal++){
			obj = bc.recuperaObjeto(listaAnimais[animal]);
			
			IDeclaracao decl = obj.primeira();
			
	        boolean animalEsperado = true;
	        
			while (decl != null && animalEsperado) {
				String pergunta = decl.getPropriedade();
				String respostaEsperada = decl.getValor();
				
					
				//se nao tiver a pergunta, faz e guarda ela
				if(!perguntasRepetidas.containsKey(pergunta)){
					resposta = responder.ask(pergunta);
					perguntasRepetidas.put(pergunta, resposta);
				} else //caso ja tenha, pega a resposta
					resposta = perguntasRepetidas.get(pergunta);
				if(resposta.equalsIgnoreCase(respostaEsperada)){
					decl = obj.proxima();
					if(decl == null)
						naoAchei = false;
				}
				else
					animalEsperado = false;
			}		
        }
		
		boolean acertei = responder.finalAnswer(listaAnimais[animal - 1]);
		
		if (acertei)
			System.out.println("Oba! Acertei!");
		else
			System.out.println("fuem! fuem! fuem!");
		
		return acertei;
	}

}
