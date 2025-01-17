package org.thecoducer.inventorymanagementsystem.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommunicationIdentifiers {
  private String emailId;
  private String phoneNumber;
  private String slackId;
  private String facebookMessengerId;
  private boolean isSmsEnabled;
  private boolean isSlackEnabled;
  private boolean isFacebookMessengerEnabled;
}
