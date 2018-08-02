package com.webdev.employeesofthecompany.controller;

import com.webdev.employeesofthecompany.domain.Status;
import com.webdev.employeesofthecompany.service.jpa.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatusController extends BaseSecurityController {

    @Autowired
    private StatusService statusService;

    @GetMapping("/moder/statuses/create")
    public ModelAndView createStatus() {
        return modelAndViewSecurityBase("moder/statuses/create");
    }

    @PostMapping("/moder/statuses/create")
    public String createStatus(@ModelAttribute Status status) {
        statusService.save(status);
        return "redirect:/moder/statuses/statuses";
    }

    @GetMapping("/moder/statuses/edit")
    public ModelAndView editStatusModer(@RequestParam long statusId) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/statuses/edit");
        modelAndView.addObject("status", statusService.getById(statusId));
        return modelAndView;
    }

    @PostMapping("/moder/statuses/edit")
    public String editStatustModer(@RequestParam(value = "statusId", required = true) long statusId,
                                      @RequestParam(value = "nameStatus", required = false) String nameStatus) {

        Status status = statusService.getById(statusId);
        status.setNameStatus(nameStatus);
        statusService.update(status);
        return "redirect:/moder/statuses/statuses";
    }

    @GetMapping("/moder/statuses/delete")
    public String deleteStatusById(@RequestParam long statusId) {
        if (statusService.exists(statusId)) {
            Status status = statusService.getById(statusId);
            statusService.delete(status);
        }
        return "redirect:/moder/statuses/statuses";
    }

    @GetMapping("/moder/statuses/statuses")
    public ModelAndView getStatuses(@RequestParam(required = false, defaultValue = "42") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/statuses/statuses");
        modelAndView.addObject("statuses", statusService.getAll());
        return modelAndView;
    }
}
