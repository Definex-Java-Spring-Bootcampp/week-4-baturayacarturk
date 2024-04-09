package com.kredinbizde.user.kredinbizdeuser.response;

import lombok.Data;

@Data
public class CreateUserResponse {
    private String userName;
    private String email;
    private String address;
    private String message;
}
