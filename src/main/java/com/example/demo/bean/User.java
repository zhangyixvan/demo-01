package com.example.demo.bean;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private int roleId;
    private String name;

    public enum roleCode {
        student(1), teacher(2), admin(3);

        private int code;

        roleCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }

    }

}
