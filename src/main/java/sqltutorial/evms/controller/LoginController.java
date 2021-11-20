package sqltutorial.evms.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	private static Locale locale = Locale.getDefault();

	@Autowired
	private MessageSource messageSource;

	@GetMapping(value = { "", "/", "/login" })
	public String login(Model model) {
		model.addAttribute("pageTitle", messageSource.getMessage("login", null, locale));

		return "login";
	}

	@GetMapping("/loginFailed")
	public String loginFailed() {
		return "redirect:/login?error";
	}

	@PostMapping("/postLogin")
	public String postLogin(Model model) {
		return home(model);
	}

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("pageTitle", messageSource.getMessage("home", null, locale));

		model.addAttribute("currentUser", SecurityContextHolder.getContext().getAuthentication().getName());

		return "home";
	}
}
