package model;

public class Candidate {
    private String id;
    private String name;
    private String emailAddress;
    private boolean voterStatus;

    public Candidate(String id,  String name, String emailAddress, boolean voterStatus){
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.voterStatus = voterStatus;
    }
    /**
     * Create a Candidate with the ID only, leaving everything else as default.
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isVoterStatus() {
        return voterStatus;
    }

    public void setVoterStatus(boolean voterStatus) {
        this.voterStatus = voterStatus;
    }
}
