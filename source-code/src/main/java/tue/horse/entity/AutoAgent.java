package tue.horse.entity;

import javax.persistence.*;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"auto_agent_name"})})
public class AutoAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auto_agent_id;

    @Column
    private String auto_agent_name;

    @Column
    private Long auto_agent_team_id;

    @Column
    private Long auto_agent_role_id;

    @Column
    private String auto_agent_operation_status;


    public Long getAutoAgentId() {
        return auto_agent_id;
    }

    public void setAutoAgentId(Long autoAgentId) {
        this.auto_agent_id = autoAgentId;
    }

    public String getAutoAgentName() {
        return auto_agent_name;
    }

    public void setAutoAgentName(String autoAgentName) {
        this.auto_agent_name = autoAgentName;
    }

    public Long getAutoAgentTeamId() {
        return auto_agent_team_id;
    }

    public void setAutoAgentTeamId(Long autoAgentTeamId) {
        this.auto_agent_team_id = autoAgentTeamId;
    }

    public Long getAutoAgentRoleId() {
        return auto_agent_role_id;
    }

    public void setAutoAgentRoleId(Long autoAgentRoleId) {
        this.auto_agent_role_id = autoAgentRoleId;
    }

    public String getAutoAgentOperationStatus() {
        return auto_agent_operation_status;
    }

    public void setAutoAgentOperationStatus(String autoAgentOperationStatus) {
        this.auto_agent_operation_status = autoAgentOperationStatus;
    }
}
