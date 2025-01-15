package org.thecoducer.observer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommunicationIdentifiers {
  private String emailId;
  private String phoneNumber;
  private String slackId;
  private String facebookMessengerId;
}
