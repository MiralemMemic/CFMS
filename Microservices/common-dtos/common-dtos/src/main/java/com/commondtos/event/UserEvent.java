package com.commondtos.event;

import com.commondtos.dto.UserRequestDto;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
public class UserEvent implements Event{

    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private UserRequestDto userRequestDto;
    private UserStatus userStatus;


    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }


    public UserEvent(UserRequestDto userRequestDto, UserStatus userStatus) {
        this.userRequestDto = userRequestDto;
        this.userStatus = userStatus;
    }
}
