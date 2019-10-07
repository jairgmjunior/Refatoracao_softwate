package br.com.diego.notafiscal;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotaFiscalController {

	@Autowired
	private NotaFiscalRepository notaFiscalRepository;

	private String notasFiscais = "notasfiscais";
	private String listagemNotas = "listanotasfiscais";

	public BigDecimal valorImposto() {
		return BigDecimal.valueOf(0.1);
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/listanotasfiscais")
	public String listaNotasFiscais(Model model) {
		Iterable<NotaFiscal> nf = notaFiscalRepository.findAll();

		model.addAttribute(notasFiscais, nf);
		return listagemNotas;
	}

	@PostMapping("/salvar")
	public String salvar(@RequestParam("nome") String nome, @RequestParam("valor") Double valor,
			@RequestParam("imposto") String imposto, Model model) {
		Imposto impostoSelecionado;

		if (imposto.toUpperCase().trim().compareTo("ICMS") == 0) {
			impostoSelecionado = new ICMS();
		} else {
			impostoSelecionado = new ISS();
		}

		NotaFiscal nf = new NotaFiscal(nome, impostoSelecionado.valorImposto().doubleValue(), valor);
		notaFiscalRepository.save(nf);
		Iterable<NotaFiscal> nfList = notaFiscalRepository.findAll();
		model.addAttribute(notasFiscais, nfList);
		
		return listagemNotas;
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Integer id, Model model) {
		Optional<NotaFiscal> notaFiscal = notaFiscalRepository.findById(id.longValue());

		if (notaFiscal.isPresent()) {
			notaFiscalRepository.delete(notaFiscal.get());
		}

		Iterable<NotaFiscal> nfList = notaFiscalRepository.findAll();
		model.addAttribute(notasFiscais, nfList);

		return listagemNotas;
	}
}