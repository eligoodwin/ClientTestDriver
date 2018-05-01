package Authorization;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

public class JWTManager {
    public static String testJWT(){
        //Source: https://github.com/jwtk/jjwt
        //source: https://stormpath.com/blog/jwt-java-create-verify
        String jwt = "";
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        //TODO: get a real key
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("superDuperSecretKey");
        Key key = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());
        //TODO: add claims, auth, aud, etc...
            jwt = Jwts.builder()
                    .setSubject("User")
                    .signWith(signatureAlgorithm, key)
                    .compact();
        return jwt;
    }
}
