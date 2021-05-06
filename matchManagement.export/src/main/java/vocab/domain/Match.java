package vocab.domain;

import javax.persistence.*;

@Entity
@Table(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Maybe not persist -> needs to be checked
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player1_id", referencedColumnName = "id")
    private User player1;

    // Maybe not persist -> needs to be checked
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player2_id", referencedColumnName = "id")
    private User player2;

    // Maybe not persist -> needs to be checked
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "currenctRound_id", referencedColumnName = "id")
    private Round currentRound;

    private Integer scorePlayer1;
    private Integer scorePlayer2;
    private Boolean finished;

    public Match(User player1) {
        this.player1 = player1;
    }


    public Match() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    public Integer getScorePlayer1() {
        return scorePlayer1;
    }

    public void setScorePlayer1(Integer scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    public Integer getScorePlayer2() {
        return scorePlayer2;
    }

    public void setScorePlayer2(Integer scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
