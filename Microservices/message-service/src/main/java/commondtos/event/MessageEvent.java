package commondtos.event;

import commondtos.dto.MessageRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class MessageEvent implements Event{

    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private MessageRequestDto messageRequestDto;
    private MessageStatus messageStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }

    public MessageEvent(MessageRequestDto messageRequestDto, MessageStatus messageStatus) {
        this.messageRequestDto = messageRequestDto;
        this.messageStatus = messageStatus;
    }
}
