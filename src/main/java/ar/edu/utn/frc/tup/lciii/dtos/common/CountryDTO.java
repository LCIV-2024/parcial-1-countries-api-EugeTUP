package ar.edu.utn.frc.tup.lciii.dtos.common;

import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {

    private String code;
    private String name;

}
