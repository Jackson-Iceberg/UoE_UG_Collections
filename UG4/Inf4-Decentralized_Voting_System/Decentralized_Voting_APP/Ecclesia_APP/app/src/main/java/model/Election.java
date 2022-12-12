package model;

public class Election {
    private String id;
    private String election_title;
    private String registerDay_start;
    private String registerDay_end;
    private String votingDay_start;
    private String votingDay_end;
    private String resultReleasingDay;


    public Election(String id, String election_title, String registerDay_start, String registerDay_end, String votingDay_start, String votingDay_end, String resultReleasingDay) {
        this.id = id;
        this.election_title = election_title;
        this.registerDay_start = registerDay_start;
        this.registerDay_end = registerDay_end;
        this.votingDay_start = votingDay_start;
        this.votingDay_end = votingDay_end;
        this.resultReleasingDay = resultReleasingDay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getElection_title() {
        return election_title;
    }

    public void setElection_title(String election_title) {
        this.election_title = election_title;
    }

    public String getRegisterDay_start() {
        return registerDay_start;
    }

    public void setRegisterDay_start(String registerDay_start) {
        this.registerDay_start = registerDay_start;
    }

    public String getRegisterDay_end() {
        return registerDay_end;
    }

    public void setRegisterDay_end(String registerDay_end) {
        this.registerDay_end = registerDay_end;
    }

    public String getVotingDay_start() {
        return votingDay_start;
    }

    public void setVotingDay_start(String votingDay_start) {
        this.votingDay_start = votingDay_start;
    }

    public String getVotingDay_end() {
        return votingDay_end;
    }

    public void setVotingDay_end(String votingDay_end) {
        this.votingDay_end = votingDay_end;
    }

    public String getResultReleasingDay() {
        return resultReleasingDay;
    }

    public void setResultReleasingDay(String resultReleasingDay) {
        this.resultReleasingDay = resultReleasingDay;
    }
}
