package org.example.synthetichumancorestarter;

import jakarta.annotation.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class transferObject {

    @NotBlank
    @Size(min = 1, max = 1000)
    private String description;

    @NotBlank
    private Priority priority;

    @NotBlank
    @Size(min = 1, max = 100)
    private String author;

    @NotBlank
    @Pattern(regexp = "\\d{4}-[01]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\d(:[0-5]\\d)?(\\.\\d+)?([+-][0-2]\\d:[0-5]\\d|Z)?")
    private String timestamp;

    public enum Priority {
        COMMON, CRITICAL
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
