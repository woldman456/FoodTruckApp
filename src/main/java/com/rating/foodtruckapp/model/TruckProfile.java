package com.rating.foodtruckapp.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.validation.annotation.Validated;
import org.hibernate.annotations.Cache;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * this creates the truck profile table and specifies constraints for each of the field values
 */

@Validated
@Entity
@Table(name = "truckprofile")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class TruckProfile implements Serializable {

    private static final long serialVersionUID = 4048798961366546485L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long truckid;
    @NotBlank
    @Size(max = 100)
    private String truckName;

    @Size(max = 4000)
    private String location;

    @Size(max = 4000)
    private String menu;

    @Size(max = 25)
    private String dayOfWeek;

    @Pattern(regexp ="^\\+?[0-9. ()-]{7,25}$", message = "Phone number")
    @Size(max = 25)
    private String phone;

    @OneToMany(mappedBy = "userComment")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Comment> userComment;

    public List<Comment> getComments() {
        return userComment;
    }

    public void setComments(List<Comment> comments) {
        this.userComment = comments;
    }

    public TruckProfile(Long truckid, String truckName, String location, String menu, String dayOfWeek) {
        this.truckid = truckid;
        this.truckName = truckName;
        this.location = location;
        this.menu = menu;
        this.dayOfWeek = dayOfWeek;
    }

    public TruckProfile() {
    }

    public Long getTruckid() {
        return truckid;
    }

    public void setTruckid(Long truckid) {
        this.truckid = truckid;
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getDayOfWeek() {
        return dayOfWeek;

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void setDayOfWeek(String dayOfWeek, String location) {
        this.dayOfWeek = dayOfWeek;
        this.location = location;
    }
}
