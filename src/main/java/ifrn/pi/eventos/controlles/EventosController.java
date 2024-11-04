package ifrn.pi.eventos.controlles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.eventos.models.Evento;
import ifrn.pi.eventos.repositories.eventoRepositorie;

@Controller
@RequestMapping("/eventos")
public class EventosController {

	@Autowired
	private eventoRepositorie er;

	@GetMapping("/form")
	public String form() {
		return "eventos/formEvento";

	}

	@RequestMapping
	public String submit(Evento evento) {
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

		md.setViewName("eventos/detalhes");
		Evento evento = opt.get();

		md.addObject("evento", evento);

		return md;

	}

}
