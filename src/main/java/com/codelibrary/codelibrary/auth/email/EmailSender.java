package com.codelibrary.codelibrary.auth.email;

public interface EmailSender {
    void send(String to, String email);
}
