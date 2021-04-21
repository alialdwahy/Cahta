package com.hrhera.cahdcaht.utl.model;

public class ChatUser{
        private String id;
        private String name;
        private String phone ;
        private boolean Active;
        private long joinData;
        private long lastLoginTime;
        private String photoLink;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }



        public boolean isActive() {
            return Active;
        }

        public void setActive(boolean active) {
            Active = active;
        }

        public long getJoinData() {
            return joinData;
        }

        public void setJoinData(long joinData) {
            this.joinData = joinData;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getPhotoLink() {
            return photoLink;
        }

        public void setPhotoLink(String photoLink) {
            this.photoLink = photoLink;
        }
}
