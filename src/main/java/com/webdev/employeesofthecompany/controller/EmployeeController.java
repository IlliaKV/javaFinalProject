package com.webdev.employeesofthecompany.controller;

import com.webdev.employeesofthecompany.domain.*;
import com.webdev.employeesofthecompany.service.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController extends BaseSecurityController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private RoleService roleService;


    @GetMapping("/moder/employees/inputdates")
    public ModelAndView getInputDates(@RequestParam long employeeId) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/employees/inputdates");
        modelAndView.addObject("employee", employeeService.getById(employeeId));

        return modelAndView;
    }

    @PostMapping("/moder/employees/inputdates")
    public String postInputDates(@RequestParam(value = "employeeId", required = true) long employeeId,
                                    @RequestParam(value = "dateFrom", required = false) String dateFrom,
                                    @RequestParam(value = "dateTo", required = false) String dateTo) {

        return "redirect:/moder/employees/settlementsheets?employeeId=" + employeeId + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo;
    }

    @GetMapping("/admin/employees/create")
    public ModelAndView createEmployeeAdmin() {
        ModelAndView modelAndView = modelAndViewSecurityBase("admin/employees/create");
        modelAndView.addObject("departments", departmentService.getAll());
        modelAndView.addObject("statuses", statusService.getAll());
        modelAndView.addObject("positions", positionService.getAll());
        modelAndView.addObject("allRoles", roleService.getAllRoles());
        return modelAndView;
    }

    @PostMapping("/admin/employees/create")
    public String createEmployeeAdmin(@RequestParam(value = "firstName", required = false) String firstName,
                                      @RequestParam(value = "lastName", required = false) String lastName,
                                      @RequestParam(value = "email", required = true) String email,
                                      @RequestParam(value = "phone", required = false) String phone,
                                      @ModelAttribute Department department,
                                      @ModelAttribute Status status,
                                      @ModelAttribute Position position,
                                      @RequestParam(value = "password", required = false) String password,
                                      @RequestParam(value = "roles") String[] roles) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setDepartment(department);
        employee.setStatus(status);
        employee.setPosition(position);
        employeeService.setPassword(employee, password);
        for(String roleName: roles) {
            Role role = roleService.getRoleByName(roleName);
            employee.getRoles().add(role);
        }
        employeeService.save(employee);
        return "redirect:/admin/employees/employees";
    }

    @GetMapping("/admin/employees/edit")
    public ModelAndView editEmployeeAdmin(@RequestParam long employeeId) {
        ModelAndView modelAndView = modelAndViewSecurityBase("admin/employees/edit");

        modelAndView.addObject("employee", employeeService.getById(employeeId));
        modelAndView.addObject("departments", departmentService.getAll());
        modelAndView.addObject("statuses", statusService.getAll());
        modelAndView.addObject("positions", positionService.getAll());
        modelAndView.addObject("allRoles", roleService.getAllRoles());

        return modelAndView;
    }

    @PostMapping("/admin/employees/edit")
    public String editEmployeeAdmin(@RequestParam(value = "employeeId", required = true) long employeeId,
                                    @RequestParam(value = "firstName", required = false) String firstName,
                                    @RequestParam(value = "lastName", required = false) String lastName,
                                    @RequestParam(value = "phone", required = false) String phone,
                                    @RequestParam(value = "email", required = false) String email,
                                    @RequestParam(value = "department", required = false) Department department,
                                    @RequestParam(value = "status", required = false) Status status,
                                    @RequestParam(value = "position", required = false) Position position,
                                    @RequestParam(value = "password", required = false) String password,
                                    @RequestParam(value = "roles") String[] roles) {

        Employee employee = employeeService.getById(employeeId);
        employeeService.setPassword(employee, password);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPhone(phone);
        employee.setEmail(email);
        employee.setDepartment(department);
        employee.setStatus(status);
        employee.setPosition(position);
        employee.getRoles().clear();
        for(String roleName: roles) {
            Role role = roleService.getRoleByName(roleName);
            employee.getRoles().add(role);
        }

        employeeService.update(employee);
        return "redirect:/admin/employees/employees";
    }


    @GetMapping("/admin/employees/delete")
    public String deleteEmployeeByIdAdmin(@RequestParam long employeeId) {
        if (employeeService.exists(employeeId)) {
            Employee employee = employeeService.getById(employeeId);
            employeeService.delete(employee);
        }
        return "redirect:/admin/employees/employees";
    }

    @GetMapping("/admin/employees/employees")
    public ModelAndView getEmployeesAdmin(@RequestParam(required = false, defaultValue = "4") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("admin/employees/employees");
        modelAndView.addObject("employees", employeeService.getAll());
        return modelAndView;
    }

    @GetMapping("/moder/employees/create")
    public ModelAndView createEmployeeModer() {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/employees/create");
        modelAndView.addObject("departments", departmentService.getAll());
        modelAndView.addObject("statuses", statusService.getAll());
        modelAndView.addObject("positions", positionService.getAll());
        return modelAndView;
    }

    @PostMapping("/moder/employees/create")
    public String createEmployeeModer(@RequestParam(value = "firstName", required = false) String firstName,
                                      @RequestParam(value = "lastName", required = false) String lastName,
                                      @RequestParam(value = "email", required = true) String email,
                                      @RequestParam(value = "phone", required = false) String phone,
                                      @ModelAttribute Department department,
                                      @ModelAttribute Status status,
                                      @ModelAttribute Position position,
                                      @RequestParam(value = "password", required = false) String password) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPhone(phone);
        employeeService.setPassword(employee, password);
        employee.setDepartment(department);
        employee.setStatus(status);
        employee.setPosition(position);
        Role role = roleService.getRoleByName("EMPLO");
        employee.getRoles().add(role);
        employeeService.save(employee);
        return "redirect:/moder/employees/employees";
    }

    @GetMapping("/moder/employees/edit")
    public ModelAndView editEmployeeModer(@RequestParam long employeeId) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/employees/edit");

        modelAndView.addObject("employee", employeeService.getById(employeeId));
        modelAndView.addObject("departments", departmentService.getAll());
        modelAndView.addObject("statuses", statusService.getAll());
        modelAndView.addObject("positions", positionService.getAll());

        return modelAndView;
    }

    @PostMapping("/moder/employees/edit")
    public String editEmployeeModer(@RequestParam(value = "employeeId", required = true) long employeeId,
                                   @RequestParam(value = "firstName", required = false) String firstName,
                                   @RequestParam(value = "lastName", required = false) String lastName,
                                   @RequestParam(value = "phone", required = false) String phone,
                                    @RequestParam(value = "email", required = false) String email,
                                   @RequestParam(value = "department", required = false) Department department,
                                    @RequestParam(value = "status", required = false) Status status,
                                    @RequestParam(value = "position", required = false) Position position) {

        Employee employee = employeeService.getById(employeeId);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPhone(phone);
        employee.setEmail(email);
        employee.setDepartment(department);
        employee.setStatus(status);
        employee.setPosition(position);

        employeeService.update(employee);
        return "redirect:/moder/employees/employees";
    }


    @GetMapping("/moder/employees/delete")
    public String deleteEmployeeByIdModer(@RequestParam long employeeId) {
        if (employeeService.exists(employeeId)) {
            Employee employee = employeeService.getById(employeeId);
            employeeService.delete(employee);
        }
        return "redirect:/moder/employees/employees";
    }

    @GetMapping("/moder/employees/employees")
    public ModelAndView getEmployeesModer(@RequestParam(required = false, defaultValue = "4") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("moder/employees/employees");
        modelAndView.addObject("employees", employeeService.getAll());
        return modelAndView;
    }


    @GetMapping("/employees/employee")
    public ModelAndView getEmploee(@RequestParam(required = false, defaultValue = "42") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("employees/employee");
        modelAndView.addObject("employee", getCurrentUser());
        return modelAndView;
    }

    private Employee getCurrentUser() {
        String userEmail = securityProcessor.getCurrentUserEmail();
        return employeeService.getByEmail(userEmail);
    }
}
