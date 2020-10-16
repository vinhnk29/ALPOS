package alpos.service;

import alpos.model.UserModel;

public interface UserService {

	public UserModel addUser(UserModel userModel) throws Exception;

	public UserModel findUserByEmail(String email);

}

