package org.springframework.samples.petclinic.recoveryroom;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/recoveryroom")
public class RecoveryRoomController {
    RecoveryRoomService rrs;
    
    @Autowired
    public RecoveryRoomController(RecoveryRoomService rrs) {
    	this.rrs = rrs;
    }
    
    @GetMapping(path="/create")
	public ModelAndView createRecoveryRoom(){
		ModelAndView result=new ModelAndView("recoveryroom/createOrUpdateRecoveryRoomForm");
		result.addObject("recoveryRoom", new RecoveryRoom());
		result.addObject("types", rrs.getAllRecoveryRoomTypes());
		return result;
	}
	
	@PostMapping(path="/create")
	public ModelAndView saveRecoveryRoom(@Valid RecoveryRoom recoveryRoom, BindingResult br) throws DuplicatedRoomNameException{
		ModelAndView result;
		if(br.hasErrors()) {
			result=new ModelAndView("recoveryroom/createOrUpdateRecoveryRoomForm", br.getModel());
		} else {
			rrs.save(recoveryRoom);
			result=new ModelAndView("welcome");
			result.addObject("message", "The recovery room was added succesfully");
		}
		return result;
	}
}
