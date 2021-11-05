package com.unifap.caronaeunifap.httpapis;

public class InvalidCredentialsException extends Exception {
    InvalidCredentialsException() {
        super("Invalid credentials");
    }
}
