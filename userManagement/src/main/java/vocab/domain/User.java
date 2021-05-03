package vocab.domain;

import javax.persistence.*;

@Entity(name = "User")
@Table(name="users", uniqueConstraints = {
        @UniqueConstraint(name = "user_name_unique", columnNames = "user_name")
})
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_name",nullable = false, columnDefinition = "TEXT")
    private String userName;

    @Column(name = "password",nullable = false, columnDefinition = "TEXT")
    private String password;


    public User( String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
