package task.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import task.pojo.Comment;

@Controller
public class CommentsValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Comment.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Comment comment = (Comment) target;
		
		validate(ValidatorEnum.COMMENTAUTHORFIELD, comment.getAuthor(), "author", errors);
		validate(ValidatorEnum.COMMENTCONTENTFIELD, comment.getContent(), "content", errors);

		
	}
	
	private void validate(ValidatorEnum validator, String value, String fieldName, Errors errors) {
		
		String patternReg = validator.getPattern();
		Pattern pattern = Pattern.compile(patternReg);
		Matcher matcher = pattern.matcher(value);
		if (!matcher.matches()) {
			
			errors.rejectValue(fieldName, validator.getErrCode(), validator.getErrMessage());
		}
	}

}
