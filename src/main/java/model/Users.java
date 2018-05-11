package model;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "Szint")
    private Long szint;

    @Column(name = "TapasztalatiPont")
    private Long userTP;

    public Users(String username, String password, Long szint, Long userTP) {
        setUsername(username);
        setPassword(password);
        this.szint = szint;
        this.userTP = userTP;
    }

    protected Users() {
    }

    public Long getUserTP() {
        return userTP;
    }

    public void setUserTP(Long userExp) {
        this.userTP = userExp;
    }

    public Long getSzint() {
        return szint;
    }

    public void setSzint(Long szint) {
        this.szint = szint;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
