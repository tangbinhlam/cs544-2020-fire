package edu.mum.cs.cs544.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Attendance {
    @Id
    private Integer id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private TimeSlot timeSlot;
    @ManyToOne
    private Location location;
    @Temporal(TemporalType.DATE)
    private Date attendedDate;

    public Attendance() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getAttendedDate() {
        return attendedDate;
    }

    public void setAttendedDate(Date attendedDate) {
        this.attendedDate = attendedDate;
    }
}
