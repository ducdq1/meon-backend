package com.viettel.etc.services;

import com.viettel.etc.dto.request.LoginRequest;
import com.viettel.etc.dto.request.RegisterRequest;
import com.viettel.etc.dto.request.VerifyOTPRequest;
import com.viettel.etc.utils.TeleCareException;

/**
 * Autogen class: Lop thong tin log
 * 
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
public interface UsersService {
    Object login(LoginRequest request) throws TeleCareException;
    Object register(RegisterRequest request) throws TeleCareException;
    Object getOTP(LoginRequest request) throws TeleCareException;
    Object verifyOTP(VerifyOTPRequest request) throws TeleCareException;
}