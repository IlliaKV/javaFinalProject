package com.webdev.employeesofthecompany.service.security;

import com.webdev.employeesofthecompany.domain.Employee;
import com.webdev.employeesofthecompany.service.jpa.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeValidateService {
    @Autowired
    private EmployeeService employeeService;

    public enum ValidateResult {
        ok,
        shortPassword,
        invalidEmail,
        userExists;

        public String getStringRepresenation() {
            switch (this) {
                case ok: return "Ok";
                case shortPassword: return "Короткий пароль. Довжина паролю має складати хоча б 5 символів";
                case invalidEmail: return "Неправильний email";
                case userExists: return "Користувач з таким email вже існує";
                default: return "";
            }
        }
    }

    public ValidateResult validate(Employee employee) {
        if (employee.getPassword().length() < 5) {
            return ValidateResult.shortPassword;
        }

        if (!isValidEmailAddress(employee.getEmail())) {
            return ValidateResult.invalidEmail;
        }

        if (employeeService.getByEmail(employee.getEmail()) != null) {
            return ValidateResult.userExists;
        }

        return ValidateResult.ok;
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
