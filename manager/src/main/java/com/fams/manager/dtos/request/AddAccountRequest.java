package com.fams.manager.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServlet;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddAccountRequest {
    private String username;
    private String email;
    private String password;
    HttpServlet
}
