package task.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import task.pojo.Post;

@Controller
public class PostsValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Post.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Post post = (Post) target;
		
		validate(ValidatorEnum.POSTFIELDS, post.getPostHeader(), "postHeader", errors);
		validate(ValidatorEnum.POSTFIELDS, post.getPostContent(), "postContent", errors);
		
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
