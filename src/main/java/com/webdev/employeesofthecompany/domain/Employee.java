package com.webdev.employeesofthecompany.domain;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(name = "employee_role", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JoinColumn(name = "positionId")
    @ManyToOne(fetch = FetchType.EAGER)
    private Position position;

    @JoinColumn(name = "statusId")
    @ManyToOne(fetch = FetchType.EAGER)
    private Status status;

    @JoinColumn(name = "departmentId")
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private Set<SettlementSheet> settlementSheets;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private Set<EventSheet> eventSheets;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<SettlementSheet> getSettlementSheets() {
        return settlementSheets;
    }

    public void setSettlementSheets(Set<SettlementSheet> settlementSheets) {
        this.settlementSheets = settlementSheets;
    }

    public Set<EventSheet> getEventSheets() {
        return eventSheets;
    }

    public void setEventSheets(Set<EventSheet> eventSheets) {
        this.eventSheets = eventSheets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
