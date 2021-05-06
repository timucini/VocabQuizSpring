package vocab.services;

import vocab.domain.Match;
import vocab.domain.User;
import java.util.List;

/**This interface provides methods to manage matches.
 * @version 0.1
 */

public interface MatchService {
    /**This method can be used to create a new match.
     * @param user The method requires the user who creates the match.
     * @param bookName The method requires the name of the book, used to play.
     * @return The method returns a new Match instance.
     */
    public Match createMatch(User user, String bookName);
    /**This method can be used to access a match.
     * @param match_id The method requires the match_id representing the match.
     * @return The method retunrs a Match instance, or null in case of not finding a match with the match_id.
     */
    public Match getMatch(Long match_id);
    /**This method can be used to update a match persistant.
     * @param match The method requires the match to be updated.
     * @return The method retunrs a boolean representing the success of the method.
     */
    Boolean updateMatch(Match match);
    /**This method can be used to get all matches with only one user.
     * @return The method returns a List of matches representing all matches with only one user.
     */
    List<Match> getAvailableMatches();
}
