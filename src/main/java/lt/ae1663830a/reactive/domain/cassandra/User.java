package lt.ae1663830a.reactive.domain.cassandra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private String username;
    private String email;
    private int age;
}
