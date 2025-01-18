package org.thecoducer.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * The MetabaseService is responsible for retrieving data from the Metabase.
 * The data comes in JSON format.
 */
@Slf4j
public class MetabaseService {
  public JSONFile getData() {
    // gets the real data from the Metabase
    return new JSONFile("Sales.json");
  }
}
