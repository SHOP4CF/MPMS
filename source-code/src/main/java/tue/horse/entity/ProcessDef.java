package tue.horse.entity;

import javax.persistence.*;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"process_name"})})
public class ProcessDef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long process_id;

    @Column
    private String process_name;

    @Column
    private String process_descr;

    @Column
    private double process_objective_cost;

    @Column
    private double process_objective_time;

    @Column
    private double process_objective_flexibility;

    @Column
    private double process_objective_quality;


    public Long getProcessId() {
        return process_id;
    }

    public void setProcessId(Long ProcessId) {
        this.process_id= ProcessId;
    }

    public String getProcessName() {
        return process_name;
    }

    public void setProcessName(String ProcessName) {
        this.process_name = ProcessName;
    }

    public String getProcessDescr() {
        return process_descr;
    }

    public void setProcessDescr(String ProcessDescr) {
        this.process_descr = ProcessDescr;
    }

    public double getProcessObjectiveCost() {
        return process_objective_cost;
    }

    public void setProcessObjectiveCost(double process_objective_cost) {
        this.process_objective_cost = process_objective_cost;
    }

    public double getProcessObjectiveTime() {
        return process_objective_time;
    }

    public void setProcessObjectiveTime(double process_objective_time) {
        this.process_objective_time = process_objective_time;
    }

    public double getProcessObjectiveFlexibility() {
        return process_objective_flexibility;
    }

    public void setProcessObjectiveFlexibility(double process_objective_flexibility) {
        this.process_objective_flexibility = process_objective_flexibility;
    }

    public double getProcessObjectiveQuality() {
        return process_objective_quality;
    }

    public void setProcessObjectiveQuality(double process_objective_quality) {
        this.process_objective_quality = process_objective_quality;
    }

}
