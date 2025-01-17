package org.rahzex.decorator;

public class TextDecorationManager {
    public String decorate(TextDecorationPreferences preferences) {
        Text text = new PlainText();

        if (preferences.isMakeBold())
            text = new BoldText(text);
        if (preferences.isMakeItalic())
            text = new ItalicText(text);
        if (preferences.isMakeUnderlined())
            text = new UnderlinedText(text);

        return text.process();
    }
}
