package tue.horse.entity;

import javax.persistence.*;


@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"ability_name"})})
public class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ability_id;

    @Column
    private String ability_name;

    @Column
    private String ability_category;

    @Column
    private String ability_group;

    @Column
    private String ability_description;

    @Column
    private String ability_example_low;

    @Column
    private String ability_example_high;

    public Long getAbilityId() {
        return ability_id;
    }

    public void setAbilityId(Long ability_id) {
        this.ability_id = ability_id;
    }

    public String getAbilityName() {
        return ability_name;
    }

    public void setAbilityName(String ability_name) {
        this.ability_name = ability_name;
    }

    public String getAbilityCategory() {
        return ability_category;
    }

    public void setAbilityCategory(String ability_category) {
        this.ability_category = ability_category;
    }

    public String getAbilityGroup() {
        return ability_group;
    }

    public void setAbilityGroup(String ability_group) {
        this.ability_group = ability_group;
    }

    public String getAbilityDescription() {
        return ability_description;
    }

    public void setAbilityDescription(String ability_description) {
        this.ability_description = ability_description;
    }

    public String getAbilityExampleLow() {
        return ability_example_low;
    }

    public void setAbilityExampleLow(String ability_example_low) {
        this.ability_example_low = ability_example_low;
    }

    public String getAbilityExampleHigh() {
        return ability_example_high;
    }

    public void setAbilityExampleHigh(String ability_example_high) {
        this.ability_example_high = ability_example_high;
    }
}
