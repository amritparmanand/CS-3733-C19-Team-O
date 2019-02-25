package Fuzzy;

import Managers.DatabaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class hiddenScore implements IFuzzy {

    /**
     * Returns the hidden score
     * Use "best" matched letter if multiple chars matched
     * @param pattern
     * @param str
     * @return int
     */
    public int hiddenScore(String pattern, String str) {

        int score = 0;

        // Set variables
        int patternIdx = 0;
        int patternLength = pattern.length();
        int strIdx = 0;
        int strLength = str.length();
        boolean prevMatched = false; // If the previous char matched
        boolean prevLower = false; // If the previous char's lower case matched
        boolean prevSeparator = true; // If the previous char was blank

        // Rules to calculate score
        int adjacency_bonus = 5; // Consecutive match
        int separator_bonus = 10; // Char matches after a separator
        int camel_bonus = 10; // Upper case char matches when previous char is in lower case
        int unmatched_letter_penalty = -1; // Miss
        int leading_letter_penalty = -3; // Every miss before the fist match (at most counts 3 misses)

        // For calculating the best letter score
        char bestLetter = '\u0000';
        char bestLower = '\u0000';
        int bestLetterScore = 0;

        // Go through the length of str
        while (strIdx != strLength) {

            // Parsing input
            char patternChar = '\u0000';
            char patternLower = '\u0000';
            if(patternIdx != patternLength){
                patternChar = pattern.charAt(patternIdx);
                patternLower = Character.toLowerCase(patternChar);
            }
            char strChar = str.charAt(strIdx);
            char strLower = Character.toLowerCase(strChar);
            char strUpper = Character.toUpperCase(strChar);


            // Char matched
            boolean nextMatch = false;
            if(patternChar != '\u0000' && patternLower == strLower){
                nextMatch = true;
            }

            // Char matched again
            boolean rematch = false;
            if(bestLetter != '\u0000' && bestLower == strLower){
                rematch = true;
            }

            boolean advanced = false;
            if(nextMatch && bestLetter != '\u0000'){
                advanced = true;
            }
            boolean patternRepeat = false;
            if(bestLetter != '\u0000' && patternChar != '\u0000' && bestLower == patternLower){
                patternRepeat = true;
            }

            // Apply best letter score and reset best letter
            if (advanced || patternRepeat) {
                score += bestLetterScore;
                bestLetter = '\u0000';
                bestLower = '\u0000';
                bestLetterScore = 0;
            }

            // If the letter is matched
            if (nextMatch || rematch) {
                int newScore = 0;

                if (patternIdx == 0) {
                    score += leading_letter_penalty * Math.min(strIdx, 3);
                }
                if (prevMatched) newScore += adjacency_bonus;
                if (prevSeparator) newScore += separator_bonus;
                if (prevLower && strChar == strUpper && strLower != strUpper) newScore += camel_bonus;

                if (nextMatch) ++patternIdx;

                // Update best letter and best letter score
                if (newScore >= bestLetterScore) {
                    if (bestLetter != '\u0000') score += unmatched_letter_penalty; // Skip this char for now if it's not the best letter
                    bestLetter = strChar;
                    bestLower = Character.toLowerCase(bestLetter);
                    bestLetterScore = newScore;
                }

                prevMatched = true; // Mark prevMatched before going to next char
            }

            // Char didn't match
            else {
                score += unmatched_letter_penalty;
                prevMatched = false; // Mark prevMatched before going to next char
            }

            // Check for separators
            if(strChar == ' ' || strChar == '_'){
                prevSeparator = true;
            }
            else{
                prevSeparator = false;
            }

            // Mark prevLower before going to next char
            if(strChar == strLower && strLower != strUpper){
                prevLower = true;
            }
            else{
                prevLower = false;
            }

            ++strIdx;
        }

        // Apply the decided best score
        if (bestLetter != '\u0000') {
            score += bestLetterScore;
        }

        return score;
    }


    @Override
    public String fuzzy(String input, Connection conn) {
        String best = "this is complete garbage";
        String brandI = "";
        String fanciI = "";

        try {
            String getEverything = "select BRANDNAME, FANCIFULNAME from FORMS";
            ResultSet r1 = conn.createStatement().executeQuery(getEverything);
            while(r1.next()){
                brandI = r1.getString("brandName");
                fanciI = r1.getString("fancifulName");
                if(hiddenScore(input,brandI) >= hiddenScore(input,best)){
                    best = brandI;
                }
                if(hiddenScore(input,fanciI) >= hiddenScore(input,best)){
                    best = fanciI;
                }
            }
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }

        return best;
    }

    @Override
    public String fuzzy(String filter, String input, Connection conn) {
        return null;
    }
}