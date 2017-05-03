package ro.tru916.web.dto;

import java.io.Serializable;

/**
 * Created by Laura on 4/30/2017.
 */
public class BaseDto implements Serializable{
    private Long id;

    public BaseDto(Long id) {
        this.id = id;
    }

    public BaseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseDto{" +
                "id=" + id +
                '}';
    }
}
