package org.thecoducer.adapter;

public class JsonXmlConverter {
  public static XMLFile doForward(JSONFile input) {
    return new XMLFile(input.getData().split(".json")[0] + ".xml");
  }

  public static JSONFile doBackward(XMLFile input) {
    return new JSONFile(input.getData().split(".xml")[0] + ".json");
  }
}
