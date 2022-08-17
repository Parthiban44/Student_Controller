package models;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ErrorObjectId {
    private String timestamp;
    private long status;
    private String error;
    private String path;
    private String message;
}
