public class fts_fuzzy_match {

    // Returns true if each character in pattern is found sequentially within str
    public static boolean fuzzy_match_simple(String pattern, String str) {
        pattern = pattern.toLowerCase();
        str = str.toLowerCase();

        int patternIdx = 0;
        int strIdx = 0;
        int patternLength = pattern.length();
        int strLength = str.length();

        while (patternIdx != patternLength && strIdx != strLength) {
            char patternChar = pattern.charAt(patternIdx);
            char strChar = str.charAt(strIdx);

            if (patternChar == strChar){
                ++patternIdx;
            }
            ++strIdx;
        }

        return patternLength != 0 && strLength != 0 && patternIdx == patternLength ? true : false;
    }

    /**
     * Returns [bool, score, formattedStr]
     *  bool: true if each character in pattern is found sequentially within str
     *  score: integer; higher is better match. Value has no intrinsic meaning. Range varies with pattern.
     *         Can only compare scores with same search pattern.
     *  formattedStr: input str with matched characters marked in <b> tags. Delete if unwanted.
     * @param pattern
     * @param str
     * @return
     */
    public static int fuzzy_match(String pattern, String str) {

        // Score consts
        int adjacency_bonus = 5;                // bonus for adjacent matches
        int separator_bonus = 10;               // bonus if match occurs after a separator
        int camel_bonus = 10;                   // bonus if match is uppercase and prev is lower
        int leading_letter_penalty = -3;        // penalty applied for every letter in str before the first match
        int max_leading_letter_penalty = -9;    // maximum penalty for leading letters
        int unmatched_letter_penalty = -1;      // penalty for every letter that doesn't matter

        // Loop variables
        int score = 0;
        int patternIdx = 0;
        int patternLength = pattern.length();
        int strIdx = 0;
        int strLength = str.length();
        boolean prevMatched = false;
        boolean prevLower = false;
        boolean prevSeparator = true;       // true so if first letter match gets separator bonus

        // Use "best" matched letter if multiple string letters match the pattern
        int bestLetter = 0;
        int bestLower = 0;
        int bestLetterIdx = 0;
        int bestLetterScore = 0;

        int [] matchedIndices = new int[0];

        // Loop over strings
        while (strIdx != strLength) {
            int patternChar = patternIdx != patternLength ? pattern.charAt(patternIdx) : null;
            int strChar = str.charAt(strIdx);

            var patternLower = patternChar != null ? patternChar.toLowerCase() : null;
            var strLower = strChar.toLowerCase();
            var strUpper = strChar.toUpperCase();

            var nextMatch = patternChar && patternLower == strLower;
            var rematch = bestLetter && bestLower == strLower;

            var advanced = nextMatch && bestLetter;
            var patternRepeat = bestLetter && patternChar && bestLower == patternLower;
            if (advanced || patternRepeat) {
                score += bestLetterScore;
                matchedIndices.push(bestLetterIdx);
                bestLetter = null;
                bestLower = null;
                bestLetterIdx = null;
                bestLetterScore = 0;
            }

            if (nextMatch || rematch) {
                var newScore = 0;

                // Apply penalty for each letter before the first pattern match
                // Note: std::max because penalties are negative values. So max is smallest penalty.
                if (patternIdx == 0) {
                    var penalty = Math.max(strIdx * leading_letter_penalty, max_leading_letter_penalty);
                    score += penalty;
                }

                // Apply bonus for consecutive bonuses
                if (prevMatched)
                    newScore += adjacency_bonus;

                // Apply bonus for matches after a separator
                if (prevSeparator)
                    newScore += separator_bonus;

                // Apply bonus across camel case boundaries. Includes "clever" isLetter check.
                if (prevLower && strChar == strUpper && strLower != strUpper)
                    newScore += camel_bonus;

                // Update patter index IFF the next pattern letter was matched
                if (nextMatch)
                    ++patternIdx;

                // Update best letter in str which may be for a "next" letter or a "rematch"
                if (newScore >= bestLetterScore) {

                    // Apply penalty for now skipped letter
                    if (bestLetter != null)
                        score += unmatched_letter_penalty;

                    bestLetter = strChar;
                    bestLower = bestLetter.toLowerCase();
                    bestLetterIdx = strIdx;
                    bestLetterScore = newScore;
                }

                prevMatched = true;
            }
            else {
                // Append unmatch characters
                formattedStr += strChar;

                score += unmatched_letter_penalty;
                prevMatched = false;
            }

            // Includes "clever" isLetter check.
            prevLower = strChar == strLower && strLower != strUpper;
            prevSeparator = strChar == '_' || strChar == ' ';

            ++strIdx;
        }

        // Apply score for last match
        if (bestLetter) {
            score += bestLetterScore;
            matchedIndices.push(bestLetterIdx);
        }

        // Finish out formatted string after last pattern matched
        // Build formated string based on matched letters
        var formattedStr = "";
        var lastIdx = 0;
        for (var i = 0; i < matchedIndices.length; ++i) {
            var idx = matchedIndices[i];
            formattedStr += str.substr(lastIdx, idx - lastIdx) + "<b>" + str.charAt(idx) + "</b>";
            lastIdx = idx + 1;
        }
        formattedStr += str.substr(lastIdx, str.length - lastIdx);

        var matched = patternIdx == patternLength;
        return [matched, score, formattedStr];
    }

    public static void main(String[] args){
        String pattern = "tomato";
        String str = "tomatooooo";

        System.out.println(fuzzy_match_simple(pattern,str));
    }
}
