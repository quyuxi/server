package com.resthome.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sun.misc.BASE64Encoder;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Created by 一缕仙缘 on 2017-07-04.
 */
public class JWTUtils
{
    public static String key="ZmF0ZQ==";


    public static String createJWT( String base64Security)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").setHeaderParam("lat","HS256")
                .setIssuer("resthome").setId("fate")
                .signWith(signatureAlgorithm, signingKey);

        //生成JWT
        return builder.compact();
    }



    public static Claims parseJWT(String jsonWebToken, String base64Security)
    {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }

    }

    public static  boolean validate(String jsonWebToken, String base64Security)
    {
        Claims claims = parseJWT(jsonWebToken, base64Security);
        if (!claims.getId().isEmpty()&&!claims.getIssuer().isEmpty()
                && "fate".equals(claims.getId())&&"resthome".equals(claims.getIssuer()))
        {
            return true;
        }return false;

    }


    public static void main(String[] args) {
        System.out.println(new BASE64Encoder().encode("fate".getBytes()));

    }

}