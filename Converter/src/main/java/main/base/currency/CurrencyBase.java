package main.base.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String charCode;

    private String name;

    private String value;

    private String date;

}
