package com.webdev.employeesofthecompany.listener;

import com.webdev.employeesofthecompany.domain.*;
import com.webdev.employeesofthecompany.service.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import java.util.Set;
import java.util.LinkedHashSet;

import static com.webdev.employeesofthecompany.service.ParseDateService.parseDateDdMmYyyy;

@Component
public class AppReadyListener {

    @Autowired
    private PositionService positionService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EventService eventService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EventSheetService eventSheetService;

    @EventListener(ApplicationReadyEvent.class)
    public void appReady(){
        System.out.println("App ready");



        //addDataToDB();
    }

    public void addDataToDB(){
        //Add Positions
        //2 rows
        Position position1 = new Position();
        position1.setNamePosition("менеджер");
        position1.setHourlyRate(new BigDecimal("90.5"));
        Position position2 = new Position();
        position2.setNamePosition("Ст.менеджер");
        position2.setHourlyRate(new BigDecimal("140.3"));
        positionService.save(position1);
        positionService.save(position2);

        //Add Statuses
        String[] statusStr = {"больничный", "работает", "отпуск"};
        for (String str:
                statusStr) {
            Status status = new Status();
            status.setNameStatus(str);
            statusService.save(status);
        }

        //Add Departments
        String[] departmentsStr = {"продаж", "маркетинга", "закупок"};
        for (String str:
                departmentsStr) {
            Department department = new Department();
            department.setNameDepartment(str);
            departmentService.save(department);
        }

        //AddEvents
        String[] eventsStr = {"рабочий день", "техническая учеба"};
        for (String str:
                eventsStr) {
            Event event = new Event();
            event.setNameEvent(str);
            eventService.save(event);
        }

        //AddRoles
        //2 rows
        Role role1 = new Role();
        role1.setNameRole("ADMIN");
        role1.setDescription("Администратор");
        Role role3 = new Role();
        role3.setNameRole("MODER");
        role3.setDescription("Модератор");
        Role role2 = new Role();
        role2.setNameRole("EMPLO");
        role2.setDescription("Сотрудник");
        roleService.save(role1);
        roleService.save(role2);

        //Add Emploees
        //2 rows
        Employee employee1 = new Employee();
        employee1.setFirstName("Михаил");
        employee1.setLastName("Нафков Викторович");
        employee1.setEmail("user1@mail.com");
        employee1.setPhone("0931231122");
        employee1.setPosition(positionService.getById(1));
        employee1.setDepartment(departmentService.getById(1));
        employee1.setStatus(statusService.getById(1));
        Set<Role> roles = new LinkedHashSet<Role>();
        roles.add(roleService.getRoleByName("USER"));
        employee1.setRoles(roles);

        Employee employee2 = new Employee();
        employee2.setFirstName("Андрей");
        employee2.setLastName("Ротов Игоревич");
        employee2.setEmail("admin");
        employee2.setPhone("0931231123");
        employee2.setPosition(positionService.getById(2));
        employee2.setDepartment(departmentService.getById(1));
        employee2.setStatus(statusService.getById(1));
        Set<Role> roles2 = new LinkedHashSet<Role>();
        roles2.add(roleService.getRoleByName("ADMIN"));
        roles2.add(roleService.getRoleByName("USER"));
        employee2.setRoles(roles2);

        employeeService.save(employee1);
        employeeService.save(employee2);

        //Add EventSheets
        //2 rows
        EventSheet eventSheet1 = new EventSheet();
        eventSheet1.setEvent(eventService.getById(1));
        eventSheet1.setEmployee(employeeService.getById(1));
        eventSheet1.setNumberOfHours(8);
        eventSheet1.setDateStart(parseDateDdMmYyyy("19-07-2018"));

        EventSheet eventSheet2 = new EventSheet();
        eventSheet2.setEvent(eventService.getById(1));
        eventSheet2.setEmployee(employeeService.getById(2));
        eventSheet2.setNumberOfHours(8);
        eventSheet2.setDateStart(parseDateDdMmYyyy("19-07-2018"));

        eventSheetService.save(eventSheet1);
        eventSheetService.save(eventSheet2);
    }
}
