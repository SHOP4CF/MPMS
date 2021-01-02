package tue.horse.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"agent_name"})})
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agent_id;

    @Column
    private String agent_name;

    @Column
    private String agent_type;

    @Column
    private String agent_fullname;

    @Column
    private double agent_performance_time;

    @Column
    private double agent_performance_quality;

    @Column
    private double agent_travel_speed;

    @Column
    private boolean agent_online;

    @Column
    private int agent_queue;

    @Column
    private Timestamp agent_busy_time;

    @Column
    private double agent_cost;

    @Column
    private Long agent_role_id;



    public Long getAgentId() {
        return agent_id;
    }

    public void setAgentId(Long agentId) {
        this.agent_id = agentId;
    }

    public String getAgentName() {
        return agent_name;
    }

    public void setAgentName(String agentName) {
        this.agent_name = agentName;
    }

    public String getAgentType() {
        return agent_type;
    }

    public void setAgentType(String agentType) {
        this.agent_type = agentType;
    }

    public String getAgentFullname() {
        return agent_fullname;
    }

    public void setAgentFullname(String agent_fullname) {
        this.agent_fullname = agent_fullname;
    }

    public double getAgentPerformanceTime() {
        return agent_performance_time;
    }

    public void setAgentPerformanceTime(double agent_performance_time) {
        this.agent_performance_time = agent_performance_time;
    }

    public double getAgentPerformanceQuality() {
        return agent_performance_quality;
    }

    public void setAgentPerformanceQuality(double agent_performance_quality) {
        this.agent_performance_quality = agent_performance_quality;
    }

    public double getAgentTravelSpeed() {
        return agent_travel_speed;
    }

    public void setAgentTravelSpeed(double agent_travel_speed) {
        this.agent_travel_speed = agent_travel_speed;
    }

    public boolean getAgentOnline() {
        return agent_online;
    }

    public void setAgentOnline(boolean agent_online) {
        this.agent_online = agent_online;
    }

    public int getAgentQueue() {
        return agent_queue;
    }

    public void setAgentQueue(int agent_queue) {
        this.agent_queue = agent_queue;
    }

    public Timestamp getAgentBusyTime() {
        return agent_busy_time;
    }

    public void setAgentBusyTime(Timestamp agent_busy_time) {
        this.agent_busy_time = agent_busy_time;
    }

    public double getAgentCost() {
        return agent_cost;
    }

    public void setAgentCost(double agent_cost) {
        this.agent_cost = agent_cost;
    }

    public Long getAgentRoleId() {
        return agent_role_id;
    }

    public void setAgentRoleId(Long agentRoleId) {
        this.agent_role_id = agentRoleId;
    }


}