package ifrn.pi.eventos.controlles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.eventos.models.Evento;

@Controller
public class EventosController {
	
	@RequestMapping("/eventos/form")
	public String form() {
		return "formEvento";
		
		
	}
	@RequestMapping("/eventos/submit")
	public String submit(Evento  evento) {
		System.out.println("Evento salvo com os seguintes dados:");
		System.out.println(evento.getNome());
		System.out.println(evento.getLocal());
		System.out.println(evento.getData());
		System.out.println(evento.getHorario());
		return "executandosubmit";
	}
	
	
	

}
