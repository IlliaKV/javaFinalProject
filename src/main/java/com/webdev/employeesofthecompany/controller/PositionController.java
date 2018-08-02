package com.webdev.employeesofthecompany.controller;

import com.webdev.employeesofthecompany.domain.Position;
import com.webdev.employeesofthecompany.service.jpa.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
public class PositionController extends BaseSecurityController{

    @Autowired
    private PositionService positionService;

    @GetMapping("/moder/positions/create")
    public ModelAndView createPosition() {
        return modelAndViewSecurityBase("moder/positions/create");
    }

    @PostMapping("/moder/positions/create")
    public String createPosition(@ModelAttribute Position position) {
        positionService.save(position);
        return "redirect:/moder/positions/positions";
    }

    @GetMapping("/moder/positions/edit")
    public ModelAndView editPositionModer(@RequestParam long positionId) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/positions/edit");
        modelAndView.addObject("position", positionService.getById(positionId));
        return modelAndView;
    }

    @PostMapping("/moder/positions/edit")
    public String editPositionModer(@RequestParam(value = "positionId", required = true) long positionId,
                                    @RequestParam(value = "namePosition", required = false) String namePosition,
                                    @RequestParam(value = "hourlyRate", required = false) String hourlyRate) {

        Position position = positionService.getById(positionId);
        position.setNamePosition(namePosition);
        position.setHourlyRate(new BigDecimal(hourlyRate));
        positionService.update(position);
        return "redirect:/moder/positions/positions";
    }

    @GetMapping("/moder/positions/delete")
    public String deletePositionsById(@RequestParam long positionId) {
        if (positionService.exists(positionId)) {
            Position position = positionService.getById(positionId);
            positionService.delete(position);
        }
        return "redirect:/moder/positions/positions";
    }

    @GetMapping("/moder/positions/positions")
    public ModelAndView getPositions(@RequestParam(required = false, defaultValue = "42") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/positions/positions");
        modelAndView.addObject("positions", positionService.getAll());
        return modelAndView;
    }
}
