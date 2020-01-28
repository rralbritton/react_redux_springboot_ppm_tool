package io.agileintelligence.ppmtool.security;

public class SecurityConstant {

    public static final String SIGN_UP_URLS = "/api/users/**";
    public static final String H2_URL = "h2_console/**";
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX = "Bearer "; //THERE MUST BE A SPACE AFTER Bearer!!!
    public static final String HEADER_STRING = "Authorization"; //Must be 'Authorization'
    public static final long EXPIRATION_TIME = 14_400_000; // 4 hours
}
