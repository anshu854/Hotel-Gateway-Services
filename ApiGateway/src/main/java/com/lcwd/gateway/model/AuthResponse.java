package com.lcwd.gateway.model;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	private String userId;
	private String accessToken;
	private String refereshToken;
	private Long expireAt;
	private Collection<String> authorities;
	
}
