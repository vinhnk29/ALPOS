package alpos.validator;


import alpos.model.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return UserModel.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UserModel user = (UserModel) obj;

		if (!user.getPassword().equals(user.getConfirmation())) {
			errors.rejectValue("confirmation", "user.validation.password.notmatch");
		}
	}

}
