package org.thecoducer.abstractfactory.uicomponent;

import lombok.Data;

@Data
public abstract class UIComponent {
  private String label;

  protected abstract void render();
}
