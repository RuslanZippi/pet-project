package main.base.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.base.user.User;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String date;


    private String inNameValue;


    private String outNameValue;


    private String inValue;


    private String outValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
