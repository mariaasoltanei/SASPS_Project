package sasps.documentmanagement.dtos;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class JwtResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -8091879091924046844L;

    private final String JWToken;

    public JwtResponse(String JWToken){
        this.JWToken = JWToken;
    }
}
