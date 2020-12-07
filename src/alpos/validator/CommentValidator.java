package alpos.validator;

import alpos.model.CommentModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CommentValidator implements Validator {

    @Override
    public boolean supports(Class<?> paramClass) {
        return CommentModel.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        CommentModel comment = (CommentModel) obj;
    }

}