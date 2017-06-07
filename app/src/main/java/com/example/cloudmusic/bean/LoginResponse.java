package com.example.cloudmusic.bean;

/**
 * Created by 若希 on 2017/6/5.
 */

public class LoginResponse {

    /**
     * sessionToken : 3860mp2lo1ak29wv7xks54yii
     * updatedAt : 2017-06-03T02:43:02.258Z
     * objectId : 5922bf732f301e006b1d52ad
     * username : aaa
     * createdAt : 2017-05-22T10:37:39.434Z
     * emailVerified : false
     * installation : {"__type":"Pointer","className":"_Installation","objectId":"KniSQflr7EYeGfg2P7Ewz04OSFwF5U3q"}
     * location : {"longitude":118.78024,"latitude":31.83494,"__type":"GeoPoint"}
     * mobilePhoneVerified : false
     */

    private String sessionToken;
    private String updatedAt;
    private String objectId;
    private String username;
    private String createdAt;
    private boolean emailVerified;
    private InstallationBean installation;
    private LocationBean location;
    private boolean mobilePhoneVerified;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public InstallationBean getInstallation() {
        return installation;
    }

    public void setInstallation(InstallationBean installation) {
        this.installation = installation;
    }

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public boolean isMobilePhoneVerified() {
        return mobilePhoneVerified;
    }

    public void setMobilePhoneVerified(boolean mobilePhoneVerified) {
        this.mobilePhoneVerified = mobilePhoneVerified;
    }

    public static class InstallationBean {
        /**
         * __type : Pointer
         * className : _Installation
         * objectId : KniSQflr7EYeGfg2P7Ewz04OSFwF5U3q
         */

        private String __type;
        private String className;
        private String objectId;

        public String get__type() {
            return __type;
        }

        public void set__type(String __type) {
            this.__type = __type;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }
    }

    public static class LocationBean {
        /**
         * longitude : 118.78024
         * latitude : 31.83494
         * __type : GeoPoint
         */

        private double longitude;
        private double latitude;
        private String __type;

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String get__type() {
            return __type;
        }

        public void set__type(String __type) {
            this.__type = __type;
        }
    }
}
