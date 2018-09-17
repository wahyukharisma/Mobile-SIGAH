package com.example.wk.sigah.model;

/**
 * Created by Kharisma on 04/04/2018.
 */

public class GetURL {
        //Server Kampus
        String URL="http://192.168.19.140/mobilewebservice/";

        //String URL="http://192.168.43.187:8205/API_SIGAH/";

        public void setURL(String URL) {
            this.URL = URL;
        }

        public GetURL(String URL) {

            this.URL = URL;
        }
        public String GetMyURL()
        {
            return "http://192.168.19.140/mobilewebservice/" ;
            //return "http://192.168.43.187:8205/API_SIGAH/";
        }
}
