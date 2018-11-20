package com.kzhong.mc10.model;

@SuppressWarnings("unused")
public class UserAccount {

    private User user;
    private String accessToken;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public class User {

        private String id;
        private String accountId;

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getAccountId() {
            return accountId;
        }
    }
}
