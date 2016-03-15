package business.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import business.controllers.CourtController;
import business.wrapper.CourtState;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class Presenter {

	@Autowired
	private CourtController courtController;

	@RequestMapping("/show_courts")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("jsp/showCourts");
		modelAndView.addObject("courtList", courtController.showCourts());
		return modelAndView;
	}

	@RequestMapping(value = "/create_court", method = RequestMethod.GET)
	public String createCourt(Model model) {
		model.addAttribute("court", new CourtState());
		return "jsp/createCourt";
	}

	@RequestMapping(value = "/create_court", method = RequestMethod.POST)
	public String createCourtSubmit(@ModelAttribute("court") CourtState court,
			BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors() && courtController.createCourt(court.getCourtId(), court.isActive())) {
			model.addAttribute("court", court);
			return "jsp/courtCreationSuccess";
		} else {
			bindingResult.rejectValue("courtId", "error.court",
					"Court already exists.");
		}
		return "jsp/createCourt";
	}
}
