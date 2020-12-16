package alpos.service;

import java.util.List;
import alpos.model.UserModel;

public interface UserService {

	public UserModel addUser(UserModel userModel) throws Exception;

	public UserModel findUserByEmail(String email);

	public UserModel findUser(Integer id);

	public UserModel editUser(UserModel userModel) throws Exception;

	public List<UserModel> findAll();

	public List<UserModel> followings(Integer userId);

}
