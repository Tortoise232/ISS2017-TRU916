package ro.tru916.core.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cata on 27.04.2017.
 */
@Entity
@Table(name = "paper")
public class Paper extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "grade")
    private Float grade;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "eventid", nullable = false)
    private Long evetid;

    @Id
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="PAPER_USER",inverseJoinColumns={@JoinColumn(name="USER_ID")},joinColumns = {@JoinColumn(name="PAPER_ID")})
    private Set<User> owners=new HashSet<User>();

    @Id
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="CONFERENCE_PAPER")
    private Conference paper;

    @Id
    @JoinTable(name = "CONFERENCE_ACCEPTED_PAPERS")
    @OneToMany(fetch = FetchType.EAGER)
    private Conference accepted;

    public Paper() {
    }


    public Paper(String name, Float grade, String path, Long evetid) {
        this.name = name;
        this.grade = grade;
        this.path = path;
        this.evetid = evetid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getOwners() {
        return owners;
    }

    public void setOwners(Set<User> owners) {
        this.owners = owners;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getEvetid() {
        return evetid;
    }

    public void setEvetid(Long evetid) {
        this.evetid = evetid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paper paper = (Paper) o;

        if (name != null ? !name.equals(paper.name) : paper.name != null) return false;
        if (grade != null ? !grade.equals(paper.grade) : paper.grade != null) return false;
        if (path != null ? !path.equals(paper.path) : paper.path != null) return false;
        if (evetid != null ? !evetid.equals(paper.evetid) : paper.evetid != null) return false;
        return owners != null ? owners.equals(paper.owners) : paper.owners == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (evetid != null ? evetid.hashCode() : 0);
        result = 31 * result + (owners != null ? owners.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", path='" + path + '\'' +
                ", evetid=" + evetid +
                ", owners=" + owners +
                '}';
    }
}
