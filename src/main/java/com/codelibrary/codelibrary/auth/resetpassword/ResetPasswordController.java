package com.codelibrary.codelibrary.auth.resetpassword;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reset_password")
@AllArgsConstructor
public class ResetPasswordController {


    private ResetPasswordService resetPasswordService;

    @PostMapping("/request")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String email) {
        return resetPasswordService.sendForgotPasswordOTP(email);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePassword(@RequestParam("email") String email,
                                            @RequestParam("password") String newPassword) {
       return resetPasswordService.updatePassword(email, newPassword);
    }

}
