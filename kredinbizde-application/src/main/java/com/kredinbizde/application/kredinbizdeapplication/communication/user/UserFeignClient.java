package com.kredinbizde.application.kredinbizdeapplication.communication.user;

import com.kredinbizde.application.kredinbizdeapplication.communication.user.response.GetUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name="kredinbizde-user",url = "${user-service.url}")
public interface UserFeignClient {

    @GetMapping("/get-user-by")
    Optional<GetUserResponse> getUserByEmail(@RequestParam String email);
}
