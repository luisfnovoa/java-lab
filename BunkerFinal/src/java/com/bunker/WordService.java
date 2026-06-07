package com.bunker;

import javax.ejb.Stateless;
import java.util.Random;

/**
 * ENTERPRISE JAVABEAN (EJB) - CORE DICTIONARY SERVICE
 * Architecture Layer: Stateless Session Bean
 * * Provides volatile, high-availability multi-language term extraction 
 * without maintaining conversational state with the client.
 */
@Stateless
public class WordService {

    /**
     * Extracts a synchronized data set containing architectural terms 
     * in 5 structural languages.
     * * Matrix Mapping Index:
     * [0] German (Pivot Key) | [1] Spanish (Target Translation) | 
     * [2] Mandarin Chinese   | [3] Japanese                | [4] Hindi
     * * @return String[] Selected security linguistic array
     */
    public String[] getRandomWordSet() {
        // Multi-language enterprise dictionary matrix
        String[][] library = {
            {"Schutz", "Protección", "保护", "保護", "संरक्षण"},
            {"Sicherheit", "Seguridad", "安全", "安全", "सुरक्षा"},
            {"Festung", "Fortaleza", "堡垒", "要塞", " किला"},
            {"Abschirmung", "Blindaje", "屏蔽", "遮蔽", "परिरक्षण"}
        };
        
        // Pseudo-random low-overhead cryptographic distribution selector
        Random rand = new Random();
        
        // Returns a single synchronized dimensional array from the pool
        return library[rand.nextInt(library.length)];
    }
}