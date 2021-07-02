package vocab.services;

import vocab.domain.*;

import java.util.List;

/**
 * This interface provides methods to manage matches.
 * @version 0.1
 */
public interface MatchService {
    /**
     * This method can be used to create a new match.
     * @param user The method requires the user who creates the match.
     * @param book The method requires a book, used to play.
     * @return The method returns a new Match instance.
     */
    Match createMatch(User user, Book book);
    /**
     * This method can be used to access a match.
     * @param match_id The method requires the match_id representing the match.
     * @return The method returns a Match instance, or null in case of not finding a match with the match_id.
     */
    Match getMatch(Long match_id);
    /**
     * This method can be used to update a match persistent.
     * @param match The method requires the match to be updated.
     * @return The method returns a boolean representing the success of the method.
     */
    Boolean updateMatch(Match match);
    /**
     * This method can be used to get all matches with only one user.
     * @return The method returns a List of matches representing all matches with only one user.
     */
    List<Match> getAvailableMatches(User user);

    /**
     * This method is used to add a second user to a match.
     * @return The method returns the match that the second user was added.
     */
    Match joinMatch(User user, Long match_id);

    /**
     * This method provides the functionality to answer a question.
     * @param answer The method requires a String instance representing the answer.
     * @param question The method requires the question instance that should be answered.
     * @param match_id The method requires the id of the match which contains the question.
     * @param user The method requires the user that answers the question.
     * @return The method returns a boolean representing correctness of the give answer.
     */
    Boolean submitAnswer(String answer, Question question, Long match_id, User user);

    /**
     * This method provides the functionality to flag a match as finished.
     * @param match_id The method requires the id of the match that should be finished.
     */
    void finishMatch(Long match_id);

    /**
     * This method provides the functionality to get a question.
     * @param question_id The method requires the id of the question.
     * @return The method returns the question, or null in case of not finding the question.
     */
    Question getQuestion(Long question_id);

    /**
     * This method provides the functionality to start a round.
     * @param category The method requires the category that should be used in the round.
     * @param match The method requires the match that contains the round.
     * @return The method returns the round.
     */
    Round startRound(Category category, Match match);
}
