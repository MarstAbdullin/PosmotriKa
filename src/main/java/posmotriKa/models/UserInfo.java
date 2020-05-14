package posmotriKa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {

    @Id
    private Long id;
    private String username;

    @ManyToMany
    @JoinTable(name = "user_to_video",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id"))
    private List<Video> videos;

    @OneToOne
    @MapsId
    private User user;

}
