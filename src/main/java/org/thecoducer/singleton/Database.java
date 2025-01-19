package org.thecoducer.singleton;

import java.util.Objects;

public class Database {
  private static Database database;

  private Database() {}

  public static Database getInstance() {
    if (Objects.isNull(database)) {
      database = new Database();
      return database;
    } else {
      return database;
    }
  }
}
