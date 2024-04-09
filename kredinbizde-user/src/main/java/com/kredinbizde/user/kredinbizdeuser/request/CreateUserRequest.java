package com.kredinbizde.user.kredinbizdeuser.request;

import lombok.Data;


@Data
public class CreateUserRequest implements Request{
    private String userName;
    private String password;
    private String email;
    private String address;
}
