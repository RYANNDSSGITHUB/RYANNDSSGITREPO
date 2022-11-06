package com.dss.login.service;

import com.dss.login.exception.AbstractException;
import com.dss.login.exception.InvalidFormatException;
import com.dss.login.exception.LoginException;
import com.dss.login.exception.RequiredFieldException;
import com.dss.login.model.Usr;
import com.dss.login.model.UsrAuth;
import com.dss.login.repository.AuthDao;
import com.dss.login.util.PasswordEncoder;
import com.dss.login.util.StringValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired AuthDao authDao;

    @Override
    public boolean login(UsrAuth usrAuth) throws AbstractException {
        if(usrAuth!=null){
            validateRequiredField(new Usr(usrAuth.getEmail(), usrAuth.getPassword()));
        } else {
            throw new InvalidFormatException("Credentials cannot be null!");
        }
        int count = authDao.countByEmailAndPassword(usrAuth.getEmail(),
                PasswordEncoder.getEncodedPassword(usrAuth.getPassword()));

        return count > 0;
    }

    @Override
    public boolean register(Usr usr) throws AbstractException {
        validateExistingRecord(usr);
        validateRequiredField(usr);
        validateValidFormat(usr);

        usr.setPassword(PasswordEncoder.getEncodedPassword(usr.getPassword()));
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
