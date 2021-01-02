package tue.horse.entity;

import javax.persistence.*;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"human_agent_name"})})
public class HumanAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long human_agent_id;

    @Column
    private String human_agent_name;

    @Column
    private Long human_agent_team_id;

    @Column
    private Long human_agent_role_id;

    @Column
    private String human_agent_operation_status;


    public Long getHumanAgentId() {
        return human_agent_id;
    }

    public void setHumanAgentId(Long humanAgentId) {
        this.human_agent_id = humanAgentId;
    }

    public String getHumanAgentName() {
        return human_agent_name;
    }

    public void setHumanAgentName(String humanAgentName) {
        this.human_agent_name = humanAgentName;
    }

    public Long getHumanAgentTeamId() {
        return human_agent_team_id;
    }

    public void setHumanAgentTeamId(Long humanAgentTeamId) {
        this.human_agent_team_id = humanAgentTeamId;
    }

    public Long getHumanAgentRoleId() {
        return human_agent_role_id;
    }

    public void setHumanAgentRoleId(Long humanAgentRoleId) {
        this.human_agent_role_id = humanAgentRoleId;
    }

    public String getHumanAgentOperationStatus() {
        return human_agent_operation_status;
    }

    public void setHumanAgentIsAvailable(String humanAgentOperationStatus) {
        this.human_agent_operation_status = humanAgentOperationStatus;
    }
}
