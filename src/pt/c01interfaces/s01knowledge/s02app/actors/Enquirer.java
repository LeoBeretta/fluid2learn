package pt.c01interfaces.s01knowledge.s02app.actors;

import java.util.*;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;

public class Enquirer implements IEnquirer
{
    IObjetoConhecimento obj;
	
	public Enquirer()
	{
	}
	
	
	@Override
	public void connect(IResponder responder)
	{
        IBaseConhecimento bc = new BaseConhecimento();
		
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

	}

}
