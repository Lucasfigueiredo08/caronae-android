package com.unifap.caronaeunifap;

public class Constants {

    public static final String BUILD_TYPE                   = "dev";            //prod
    public static final String API_BASE_URL                 = BUILD_TYPE.equals("dev")?"https://polar-sea-21011.herokuapp.com/":"https://polar-sea-21011.herokuapp.com/";
    //public static final String API_BASE_URL                 = BUILD_TYPE.equals("prod")?"http://10.0.2.2:8000/":"http://10.0.2.2:8000/";
    //public static final String CARONAE_URL_BASE             = BUILD_TYPE.equals("prod")?"http://10.0.2.2:8000/":"http://10.0.2.2:8000/";
    public static final String CARONAE_URL_BASE             = BUILD_TYPE.equals("dev")?"https://caronae.org/":"https://caronae.org/";
    public static final String SHARE_LINK                   = CARONAE_URL_BASE + "carona/";

}