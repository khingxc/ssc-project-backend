package io.muzoo.ssc.project.backend.auth;

public class EmailAlreadyUsedException extends UserServiceException{

    public EmailAlreadyUsedException(String message){
        super(message);
    }
}
