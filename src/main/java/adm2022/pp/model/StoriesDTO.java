package adm2022.pp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StoriesDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String summary;

    private String description;

    private Integer points;

}
