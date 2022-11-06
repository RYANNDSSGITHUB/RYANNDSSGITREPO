package com.dss.service;

import com.dss.exception.CustomErrorException;
import com.dss.model.Usr;
import com.dss.model.UsrAuth;
import com.dss.repository.AuthDao;
import com.dss.util.PasswordEncoder;
import com.dss.util.StringValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl extends BaseServiceImpl<Usr> implements AuthService {

    @Autowired AuthDao authDao;

    @Override
    public boolean login(UsrAuth usrAuth) throws CustomErrorException {
        validateRequiredField(new Usr(usrAuth.getEmail(), usrAuth.getPassword()));

        int count = authDao.countByEmailAndPassword(usrAuth.getEmail(),
                PasswordEncoder.getEncodedPassword(usrAuth.getPassword()));

        if(count > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean register(Usr usr) throws CustomErrorException {
        List<String> errorList = new ArrayList<String>();
        validateExistingRecord(usr);
        validateRequiredField(usr);
        validateValidFormat(usr);

        usr.setPassword(PasswordEncoder.getEncodedPassword(usr.getPassword()));
        return authDao.save(usr).getId()!=null;
    }

    private void validateRequiredField(Usr usr) throws CustomErrorException {
        List<String> errorList = new ArrayList<String>();
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

        if(errorList.size() > 0){
            throw new CustomErrorException(errorList.toString());
        }
    }

    private void validateValidFormat(Usr usr) throws CustomErrorException {
        List<String> errorList = new ArrayList<String>();
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

        if(errorList.size() > 0){
            throw new CustomErrorException(errorList.toString());
        }
    }

    private void validateExistingRecord(Usr usr) throws CustomErrorException {
        List<String> errorList = new ArrayList<String>();
        if(usr.getEmail()!=null && countByEmail(usr.getEmail()) > 0){
            errorList.add("Email already exists");
        }
        if(usr.getContactNo()!=null && countByContactNo(usr.getContactNo()) > 0){
            errorList.add("Contact number already exists");
        }

        if(errorList.size() > 0){
            throw new CustomErrorException(errorList.toString());
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
