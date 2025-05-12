package com.booktrain.exachangeseat.entity;

import jakarta.persistence.*;

@Entity
public class PNRRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PNRId;

    private Long PNRNumber;

    @OneToOne
    private User user;

    @OneToOne
    private Bookings bookings;

    public PNRRecord() {
    }

    public PNRRecord(Long PNRId,Long PNRNumber, User user, Bookings bookings) {
       // this.PNRId = PNRId;
        this.PNRNumber = PNRNumber;
        this.user = user;
        this.bookings = bookings;
    }

    public Long getPNRId() {
        return PNRId;
    }

    public void setPNRId(Long PNRId) {
        this.PNRId = PNRId;
    }

    public Long getPNRNumber() {
        return PNRNumber;
    }

    public void setPNRNumber(Long PNRNumber) {
        this.PNRNumber = PNRNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bookings getBookings() {
        return bookings;
    }

    public void setBookings(Bookings bookings) {
        this.bookings = bookings;
    }
}
