package sqltutorial.evms.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sqltutorial.evms.model.VaccineType;
import sqltutorial.evms.repository.VaccineTypeRepository;

@Controller
@RequestMapping("/vaccine_type")
public class VaccineTypeController {

	private static Locale locale = Locale.getDefault();

	@Autowired
	private MessageSource messageSource;

	@Autowired
	VaccineTypeRepository vaccineTypeRepository;

	@GetMapping("")
	public String index(Model model) {

		model.addAttribute("vaccineTypeList", vaccineTypeRepository.findAll());
		model.addAttribute("pageTitle", messageSource.getMessage("vaccineType", null, locale));

		return "/vaccine_type/index";
	}

	@PostMapping("/edit")
	public String edit(@ModelAttribute("vaccineType") VaccineType vaccineType, Model model,
			@RequestParam(value = "vaccineTypeSubmit") String vaccineTypeSubmit) {

		try {
			String name = vaccineType.getName(), currentUser = "Mohammed Monirul Islam",
					message = messageSource.getMessage("vaccineTypeAddSuccess", new String[] { name }, locale);

			boolean isEdit = messageSource.getMessage("edit", null, locale).equals(vaccineTypeSubmit);

			if (isEdit) {
				VaccineType updateVaccineType = vaccineTypeRepository.findById(name).get();
				updateVaccineType.setDescription(vaccineType.getDescription());
				vaccineType = updateVaccineType;
				message = messageSource.getMessage("vaccineTypeUpdateSuccess", new String[] { name }, locale);
			} else {
				vaccineType.setCreatedBy(currentUser);
			}
			vaccineType.setLastUpdatedBy(currentUser);
			vaccineTypeRepository.save(vaccineType);

			model.addAttribute("success", message);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", messageSource.getMessage("error", new String[] { e.getMessage() }, locale));
		}

		return index(model);
	}

	@GetMapping("/delete/{name}")
	public String delete(@PathVariable("name") String name, Model model) {

		try {
			VaccineType vaccineType = vaccineTypeRepository.findById(name).get();
			vaccineTypeRepository.delete(vaccineType);
			model.addAttribute("success",
					messageSource.getMessage("vaccineTypeDeleteSuccess", new String[] { name }, locale));
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", messageSource.getMessage("error", new String[] { e.getMessage() }, locale));
		}

		return index(model);
	}

	@GetMapping("/edit/{name}")
	public String edit(@PathVariable("name") String name, Model model) {
		boolean isAdd = "-1".equals(name);

		model.addAttribute("vaccineType", isAdd ? new VaccineType() : vaccineTypeRepository.findById(name).get());
		model.addAttribute("pageTitle", messageSource.getMessage("vaccineType", null, locale));

		return "/vaccine_type/maintain";
	}
}
