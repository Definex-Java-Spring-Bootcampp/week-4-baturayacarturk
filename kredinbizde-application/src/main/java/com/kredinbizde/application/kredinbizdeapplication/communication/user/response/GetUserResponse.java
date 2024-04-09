package com.kredinbizde.application.kredinbizdeapplication.communication.user.response;

import lombok.Data;

@Data
public class GetUserResponse {
    private String userName;
    private String email;
    private Long userId;
}
