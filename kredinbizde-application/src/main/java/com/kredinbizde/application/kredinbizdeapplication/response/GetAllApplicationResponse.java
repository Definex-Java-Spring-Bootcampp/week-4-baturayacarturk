package com.kredinbizde.application.kredinbizdeapplication.response;

import lombok.Data;

import java.util.List;

@Data
public class GetAllApplicationResponse {
    List<GetApplicationResponse> applications;
}
