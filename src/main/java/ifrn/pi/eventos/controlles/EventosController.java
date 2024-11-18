package ifrn.pi.eventos.controlles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.eventos.models.Convidado;
import ifrn.pi.eventos.models.Evento;
import ifrn.pi.eventos.repositories.ConvidadoRepository;
import ifrn.pi.eventos.repositories.eventoRepositorie;

@Controller
@RequestMapping("/eventos")
public class EventosController {
	
	@Autowired
	private eventoRepositorie er;
	@Autowired
	private ConvidadoRepository cr;
	
	@GetMapping("/form")
	public String form() {
		return "eventos/formEvento";
		
		
	}
	@RequestMapping
	public String submit(Evento  evento) {
		System.out.println("Evento salvo com os seguintes dados:");
		System.out.println(evento.getNome());
		System.out.println(evento.getLocal());
		System.out.println(evento.getData());
		System.out.println(evento.getHorario());
		er.save(evento);
		return "eventos/executandosubmit";
	}
	@GetMapping
	public ModelAndView lister() {
		
		List<Evento> eventos = er.findAll();
		ModelAndView mv = new ModelAndView("eventos/lista");
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Evento> opt = er.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/eventos");
			return md;
		}
		
		md.setViewName("eventos/detalhe");
		Evento evento = opt.get();
		md.addObject("evento", evento);
		
		List<Convidado> convidado = cr.findByEvento(evento);
		md.addObject("convidado", convidado);
		
		return md;
	}
	
	@PostMapping("/{idEvento}")
	public String salvarConvidado(@PathVariable Long idEvento, Convidado convidado) {
		
		System.out.println("Id do evento: " + idEvento);
		System.out.println(convidado);
		
		Optional<Evento> opt = er.findById(idEvento);
		if(opt.isEmpty()) {
			return "redirect:/eventos";
		}
		
		Evento evento = opt.get();
		convidado.setEvento(evento);
		
		cr.save(convidado);
		
		return "redirect:/eventos/{idEvento}";
	}

}
