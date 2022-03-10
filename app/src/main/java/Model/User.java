package Model;

public class User {
    private String name, email, password, surveyscore;

    public User() {
    }

    public User(String name, String email, String password, String surveyscore) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.surveyscore = surveyscore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurveyscore(){
        return surveyscore;
    }
    public void setSurveyscore(String surveyscore){
        this.surveyscore = surveyscore;
    }
}





