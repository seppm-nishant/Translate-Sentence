package org.example;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class TranslationService {

    public String translateText(String text, String targetLanguage) {
        // Create Translate object using TranslateOptions
        Translate translate = TranslateOptions.getDefaultInstance().getService();

        try {
            // Perform translation
            Translation translation = translate.translate(
                    text,
                    Translate.TranslateOption.targetLanguage(targetLanguage)
            );

            // Return translated text
            return translation.getTranslatedText();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace(); // or log the exception
            return "Error translating text: " + e.getMessage();
        }
    }
}
