package com.booktrain.exachangeseat.dto;

public class PNRRecordDTO {

    private Long userId;

    private Long bookingId;

    public PNRRecordDTO() {
    }

    public PNRRecordDTO(Long userId, Long bookingId) {
        this.userId = userId;
        this.bookingId = bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }


}
