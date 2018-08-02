package com.webdev.employeesofthecompany.domain;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nameEvent")
    private String nameEvent;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private List<EventSheet> eventSheets;

    public List<EventSheet> getEventSheets() {
        return eventSheets;
    }

    public void setEventSheets(List<EventSheet> eventSheets) {
        this.eventSheets = eventSheets;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
