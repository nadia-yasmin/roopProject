package com.roop.admin.brand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason="BRAND NOT FOUND")
public class BrandNotFoundException extends Exception {
	public BrandNotFoundException(String message) {
		super(message);
	}
}
