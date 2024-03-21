package com.AbdulKhaliq.EcommerceApplication.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
@ToString@Builder
public class JwtResponse
{
    private String jwtToken;
    private String username;
}
