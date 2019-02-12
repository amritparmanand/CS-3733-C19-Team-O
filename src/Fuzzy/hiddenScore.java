package Fuzzy;

import Managers.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class hiddenScore implements IFuzzy {
    private DatabaseManager dbM;

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
    public String fuzzy(String input) {
        String best = "this is complete garbage";
        String iterator = "";
        int size = 0;

        try {
            String getSize = "select count(*) as size from FORMS";
            ResultSet r1 = dbM.getStmt().executeQuery(getSize);
            while(r1.next()){
                size = r1.getInt("size");
            }
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }

        for(int i = 1; i <= size; i++){
            try {
                String q = "select BRANDNAME from FORMS where FORMID = " + i;
                ResultSet r2 = dbM.getStmt().executeQuery(q);
                while(r2.next()){
                    iterator = r2.getString("brandName");
                }
            } catch (SQLException e) {
                if (!e.getSQLState().equals("X0Y32"))
                    e.printStackTrace();
            }

            if(hiddenScore(input,iterator) >= hiddenScore(input,best)){
                best = iterator;
            }

        }

        return best;
    }
}