package net.engineeringdigest.journalApp.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;


@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntity {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
}
