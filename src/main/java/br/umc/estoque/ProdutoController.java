package br.umc.estoque;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/produtos";
    }

    // LISTAR (com busca opcional por nome)
    @GetMapping("/produtos")
    public String listar(@RequestParam(required = false) String busca, Model model) {
        model.addAttribute("produtos", service.listar(busca));
        model.addAttribute("busca", busca);
        return "lista";
    }

    // NOVO
    @GetMapping("/produtos/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        return "form";
    }

    // SALVAR (cria ou atualiza) -> redirect evita cadastro duplicado ao recarregar
    @PostMapping("/produtos/salvar")
    public String salvar(@Valid @ModelAttribute("produto") Produto produto,
                         BindingResult result,
                         RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "form";
        }
        service.salvar(produto);
        redirect.addFlashAttribute("mensagem", "Produto salvo com sucesso.");
        return "redirect:/produtos";
    }

    // EDITAR
    @GetMapping("/produtos/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("produto", service.buscarPorId(id));
        return "form";
    }

    // EXCLUIR
    @PostMapping("/produtos/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        service.excluir(id);
        redirect.addFlashAttribute("mensagem", "Produto excluído.");
        return "redirect:/produtos";
    }
}
