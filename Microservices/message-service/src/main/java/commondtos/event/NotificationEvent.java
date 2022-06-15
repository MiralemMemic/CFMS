package commondtos.event;

import commondtos.dto.NotificationRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class NotificationEvent implements Event{

    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private NotificationRequestDto notificationRequestDto;
    private NotificationStatus userStatus;


    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }


    public NotificationEvent(NotificationRequestDto notificationRequestDto, NotificationStatus userStatus) {
        this.notificationRequestDto = notificationRequestDto;
        this.userStatus = userStatus;
    }
}
