package com.paygo.wallet_api.auth.security;

import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class GenerateKey {

	public static void main(String[] args) {
		SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
		String secret = Encoders.BASE64.encode(key.getEncoded());

		System.out.println(secret);
	}
}
