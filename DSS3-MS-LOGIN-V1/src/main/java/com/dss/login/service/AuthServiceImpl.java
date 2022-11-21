package com.dss.login.service;

import com.dss.login.domain.constants.RoleEnum;
import com.dss.login.domain.dto.AuthRequest;
import com.dss.login.domain.exception.AbstractException;
import com.dss.login.domain.exception.InvalidFormatException;
import com.dss.login.domain.exception.LoginException;
import com.dss.login.domain.exception.RequiredFieldException;
import com.dss.login.domain.model.Usr;
import com.dss.login.domain.util.JwtTokenUtil;
import com.dss.login.domain.util.MD5Encoder;
import com.dss.login.domain.util.StringValidator;
import com.dss.login.repository.AuthDao;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired AuthDao authDao;
    @Autowired JwtTokenUtil jwtTokenUtil;
    @Autowired PasswordEncoder passwordEncoder;

    @Override
    public String login(AuthRequest authRequest) throws AbstractException {
        if(authRequest !=null){
            validateRequiredField(new Usr(authRequest.getEmail(), authRequest.getPassword()));
        } else {
            throw new InvalidFormatException("Credentials cannot be null!");
        }
        int count = authDao.countByEmailAndPassword(authRequest.getEmail(),
                MD5Encoder.getEncodedPassword(authRequest.getPassword()));

        if(count > 0){
            return jwtTokenUtil.generateAccessToken(authRequest.getEmail());
        }
        return "";
    }

    @Override
    public boolean register(Usr usr) throws AbstractException {
//        validateExistingRecord(usr);
//        validateRequiredField(usr);
//        validateValidFormat(usr);

        usr.setRole(RoleEnum.USER.getIntValue());
        usr.setPassword(passwordEncoder.encode(usr.getPassword()));
        return authDao.save(usr).getId()!=null;
    }

    private void validateRequiredField(Usr usr) throws RequiredFieldException {
        List<String> errorList = new ArrayList<>();
        if(usr.getAlias()==null) {
            errorList.add("Alias is a required field");
        }
        if(usr.getContactNo()==null){
            errorList.add("Contact number is a required field");
        }
        if(usr.getEmail()==null ) {
            errorList.add("Email is a required field");
        }
        if(usr.getPassword()==null) {
            errorList.add("Password is a required field");
        }
        if(usr.getRole()==null){
            errorList.add("Role is a required field");
        }

        if(!errorList.isEmpty()){
            throw new RequiredFieldException(errorList.toString());
        }
    }

    private void validateValidFormat(Usr usr) throws InvalidFormatException {
        List<String> errorList = new ArrayList<>();
        if(!StringValidator.isAlphabetic(usr.getAlias())) {
            errorList.add("Alias must be alphabetic characters only");
        }
        if(!StringValidator.isNumeric(usr.getContactNo())) {
            errorList.add("Contact number must be numeric characters only");
        }
        String passwordErr = StringValidator.isValidPassword(usr.getPassword());
        if(passwordErr!=null){
            errorList.add(passwordErr);
        }

        if(!errorList.isEmpty()){
            throw new InvalidFormatException(errorList.toString());
        }
    }

    private void validateExistingRecord(Usr usr) throws LoginException {
        List<String> errorList = new ArrayList<>();
        if(usr.getEmail()!=null && countByEmail(usr.getEmail()) > 0){
            errorList.add("Email already exists");
        }
        if(usr.getContactNo()!=null && countByContactNo(usr.getContactNo()) > 0){
            errorList.add("Contact number already exists");
        }

        if(!errorList.isEmpty()){
            throw new LoginException(errorList.toString());
        }
    }

    @Override
    public int countByEmail(String email) {
        return authDao.countByEmail(email);
    }

    @Override
    public int countByContactNo(String email) {
        return authDao.countByContactNo(email);
    }
}
