package com.webdev.employeesofthecompany.domain;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class EventSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "numberOfHours")
    private int numberOfHours;

    @Column(name = "dateStart")
    private Date dateStart;

    @JoinColumn(name = "employeeId")
    @ManyToOne(fetch = FetchType.EAGER)
    private Employee employee;

    @JoinColumn(name = "eventId")
    @ManyToOne(fetch = FetchType.EAGER)
    private Event event;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }


    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    @Override
    public String toString() {
        return event.getNameEvent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventSheet that = (EventSheet) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
