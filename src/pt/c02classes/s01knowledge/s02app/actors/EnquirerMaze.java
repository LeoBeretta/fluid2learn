package pt.c02classes.s01knowledge.s02app.actors;

import java.util.*;
import java.util.Scanner;

import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;


public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	
	boolean flag = true;
	
	Stack<String> direcoes = new Stack<String>();
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {
	Scanner scanner = new Scanner(System.in);
		
		boolean flag = true;
		String resp = null;
		
		while(flag){
			//ve se ta na saida
			resp = responder.ask("aqui");
			if(resp.equalsIgnoreCase("saida"))		
				flag = false;
			else{
				//tenta ir pro Norte
				resp = responder.ask("norte");
				if((resp.equalsIgnoreCase("passagem") || resp.equalsIgnoreCase("saida"))){
					if(direcoes.isEmpty()){
						responder.move("norte");
						direcoes.push("norte");
					}
					else if(direcoes.peek() != "sul"){
							responder.move("norte");
							direcoes.push("norte");
						}
				} 
				else{
					//tenta ir pro leste
					resp = responder.ask("leste");
					if((resp.equalsIgnoreCase("passagem") || resp.equalsIgnoreCase("saida"))){
						if(direcoes.isEmpty()){
						responder.move("leste");
						direcoes.push("leste");
						}
						else if(direcoes.peek() != "oeste"){
							responder.move("leste");
							direcoes.push("leste");
						}
					}
					else{
						//tenta ir pro sul
						resp = responder.ask("sul");
						if((resp.equalsIgnoreCase("passagem") || resp.equalsIgnoreCase("saida"))){
							if(direcoes.isEmpty()){
								responder.move("sul");
								direcoes.push("sul");
							}
							else if(direcoes.peek() != "norte"){
								responder.move("sul");
								direcoes.push("sul");
							}
						}
						else{
							//tenta ir pro oeste
							resp = responder.ask("oeste");
							if((resp.equalsIgnoreCase("passagem") || resp.equalsIgnoreCase("saida"))){
								if(direcoes.isEmpty()){
								responder.move("oeste");
								direcoes.push("oeste");
								}
								else if(direcoes.peek() != "leste"){
									responder.move("oeste");
									direcoes.push("oeste");
								}
							}
						}
					}
				}
			}
		}
		
		if (responder.finalAnswer("cheguei"))
			System.out.println("Você encontrou a saida!");
		else
			System.out.println("Fuém fuém fuém!");
		
		direcoes.clear();
		
		return true;
	}
	
}
