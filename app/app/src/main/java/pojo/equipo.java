package pojo;

public class equipo {
    public String team;
    public String points;
    public String wins;
    public String ties;
    public String contact;
    public String games;

    public void setTeam(String team) {
        this.team = team;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setGames(String games) {
        this.games = games;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public void setTies(String ties) {
        this.ties = ties;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTeam() {
        return team;
    }

    public String getPoints() {
        return points;
    }

    public String getGames() {
        return games;
    }

    public String getWins() {
        return wins;
    }

    public String getTies() {
        return ties;
    }

    public String getContact() {
        return contact;
    }
    private boolean isChecked;
    public boolean isChecked() {
        return  isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked=checked;
    }
}
