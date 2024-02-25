package org.example;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.example.TranslationRequest;
import org.example.TranslationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TranslationController {

    @PostMapping("/translate/")
    public TranslationResponse translate(@RequestBody TranslationRequest request) {
        Translate translate = null;
        try {
            // Check if the request body contains the 'text' field
            if (request.getText() == null || request.getText().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing 'text' field in request body");
            }

            // Create Translate object using TranslateOptions
            translate = TranslateOptions.getDefaultInstance().getService();

            // Translate the text from English to French using Google Cloud Translation API
            Translation translation = translateText(translate, request.getText());

            // Return the translated text
            return new TranslationResponse(translation.getTranslatedText());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to translate text", e);
        }
    }

    // Translate text from English to French using Google Cloud Translation API
    private Translation translateText(Translate translate, String text) {
        // Translates the text into French
        return translate.translate(text, Translate.TranslateOption.targetLanguage("fr"));
    }
}
