package tue.horse.entity;

import javax.persistence.*;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"role_name"})})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    @Column
    private String role_name;

    @Column
    private String role_description;

    public Long getRoleId() {
        return role_id;
    }

    public void setRoleId(Long RoleId) {
        this.role_id = RoleId;
    }

    public String getRoleName() {
        return role_name;
    }

    public void setRoleName(String roleName) {
        this.role_name = roleName;
    }

    public String getRoleDescription() {
        return role_description;
    }

    public void setRoleDescription(String role_description) {
        this.role_description = role_description;
    }
}

