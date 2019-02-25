package Datatypes;

/**
 * @author Clay Oshiro-Leavitt
 * @version It 4
 * datatype for holding agent score, name information
 * used in leaderboard display on user score page
 */
public class agentScore implements Comparable<agentScore> {
    private String agentName;
    private int agentScore;

    public agentScore() {
        this.agentName = "";
        this.agentScore = 0;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public int getAgentScore() {
        return agentScore;
    }

    public void setAgentScore(int agentScore) {
        this.agentScore = agentScore;
    }

    public agentScore(String agentName, int agentScore) {
        this.agentName = agentName;
        this.agentScore = agentScore;
    }

    @Override
    public int compareTo(agentScore compareA) {
    //    int score = this.agentScore.compareTo(compare.agentScore);
        return this.agentScore < compareA.agentScore ? 1 : this.agentScore > compareA.agentScore ? -1 : 0;
    }
}
