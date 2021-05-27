package vocab.services;

import vocab.domain.*;

import java.util.List;

/**This interface provides methods to manage matches.
 * @version 0.1
 */

public interface MatchService {
    /**This method can be used to create a new match.
     * @param user The method requires the user who creates the match.
     * @param book The method requires a book, used to play.
     * @return The method returns a new Match instance.
     */
    Match createMatch(User user, Book book);
    /**This method can be used to access a match.
     * @param match_id The method requires the match_id representing the match.
     * @return The method retunrs a Match instance, or null in case of not finding a match with the match_id.
     */
    Match getMatch(Long match_id);
    /**This method can be used to update a match persistant.
     * @param match The method requires the match to be updated.
     * @return The method retunrs a boolean representing the success of the method.
     */
    Boolean updateMatch(Match match);
    /**This method can be used to get all matches with only one user.
     * @return The method returns a List of matches representing all matches with only one user.
     */
    List<Match> getAvailableMatches();

    /**
     *
     * @return
     */
    Match joinMatch(User user, Long match_id);

    /**
     *
     * @param answer
     * @param question
     * @param match_id
     * @param user
     * @return
     */
    Boolean submitAnswer(String answer, Question question, Long match_id, User user);

    /**
     *
     * @param match_id
     */
    void finishMatch(Long match_id);
}
