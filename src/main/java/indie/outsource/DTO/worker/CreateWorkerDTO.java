package indie.outsource.DTO.worker;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkerDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    @JsonProperty("name")
    private String name;
}
