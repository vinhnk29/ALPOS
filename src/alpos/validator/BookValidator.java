package alpos.validator;

import alpos.model.BookModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    @Override
    public boolean supports(Class<?> paramClass) {
        return BookModel.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        BookModel book = (BookModel) obj;
    }

}