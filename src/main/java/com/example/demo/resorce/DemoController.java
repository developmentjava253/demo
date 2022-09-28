package com.example.demo.resorce;

import com.example.demo.service.ValidatorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/validate", produces = "application/json")
public class DemoController {

	@Autowired
	private ValidatorService validatorService;

	@PostMapping("/")
	@ApiOperation(value = "Validate rectangles location.",
			notes = "Determine whether two rectangles have one or more intersecting lines," +
					"if a rectangle is wholly contained within another rectangle" +
					"or whether two rectangles are adjacent.",
			response = CoreServiceResponse.class)
	public @ResponseBody CoreServiceResponse<String,ValidationResponse>	 validate(@ApiParam(value = "Request with two Rectangle and the validation type.",
	required = true) @Valid @RequestBody ValidationRequest validationRequest, @ApiIgnore Errors errors){
		if (errors.hasErrors()) {
			return getErrors(new CoreServiceResponse<>(validationRequest.toString(),new ValidationResponse()),errors(errors));
		}
		CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(validationRequest);
		return response;
	}

	private CoreServiceResponse<String,ValidationResponse> getErrors(CoreServiceResponse<String,ValidationResponse> response, Map<String, String> errors){
		CoreServiceErrorResponse errorResponse = new CoreServiceErrorResponse();
		errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.toString());
		errorResponse.setErrorMessage(errors.toString());
		response.setErrorResponse(errorResponse);
		response.setHasErrorResponse(String.valueOf(Boolean.TRUE));
		return response;

	}

	private Map<String, String> errors(Errors errors){
		Map<String, String> errorsMap = new HashMap<>();
		errors.getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errorsMap.put(fieldName, errorMessage);
		});
		return errorsMap;
	}
}
