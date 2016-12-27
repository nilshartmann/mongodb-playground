package nh.mongodb.playground;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
public class TrackingEvent {

  private String customerId;
  private String type;
  private Date timestamp;

  private Map<String, Object> payload;

  public String getCustomerId() {
    return customerId;
  }

  public boolean hasCustomerId() {
    return this.customerId != null;
  }

  public String getType() {
    return type;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public Map<String, Object> getPayload() {
    return payload;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public void setPayload(Map<String, Object> payload) {
    this.payload = payload;
  }

  public void addToPayload(String key, Object value) {
    if (this.payload == null) {
      payload = new HashMap<>();
    }

    payload.put(key, value);
  }

  @Override
  public String toString() {
    return "TrackingEvent{" +
        "customerId='" + customerId + '\'' +
        ", type='" + type + '\'' +
        ", timestamp=" + timestamp +
        ", payload=" + payload +
        '}';
  }
}
