// Paul Truong Nguyen
// jadrn034
// Note: Date of Birth validation pattern found at: https://stackoverflow.com/questions/8647893/regular-expression-leap-years-and-more
// Age calculation found here: https://stackoverflow.com/questions/3224834/get-difference-between-2-dates-in-javascript

var j = jQuery.noConflict();

function isEmpty(inputValue) {
	return inputValue.val().trim();
}

function isNameValid(name) {
	var namePattern = /^[a-zA-Z\s]+$/;
	return name.val().match(namePattern);
}

function isAddressValid(address) {
	var addressPattern = /^[0-9a-zA-Z\s]+$/;
	return address.val().match(addressPattern);
}

function isValidZip(zip) {
	var zipPattern = /(^\d{5}$)/;
	return zip.val().match(zipPattern);
}

function isValidPhone(phone) {
	var phonePattern = /^[(]{0,1}[0-9]{3}[)]{0,1}[-\s\.]{0,1}[0-9]{3}[-\s\.]{0,1}[0-9]{4}$/;
	return phone.val().match(phonePattern);
}

function isValidBirthday(birthday) {
	var birthdayPattern = "((0[13578]|1[02])[\/.](0[1-9]|[12][0-9]|3[01])[\/.](19|20)[0-9]{2})|((0[469]|11)[\/.](0[1-9]|[12][0-9]|30)[\/.](19|20)[0-9]{2})|((02)[\/.](0[1-9]|1[0-9]|2[0-8])[\/.](19|20)[0-9]{2})|((02)[\/.]29[\/.](((19|20)(04|08|[2468][048]|[13579][26]))|2000))"
	return birthday.val().match(birthdayPattern);
}

function isValidEmail(email) {
	var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	return regex.test(email.val());
}

function isAge(str) {
	var _MS_PER_DAY = 1000 * 60 * 60 * 24;
	var parms = str.split(/[\.\-\/]/);
	var yyyy = parseInt(parms[2],10);
	var dd   = parseInt(parms[1],10);
	var mm   = parseInt(parms[0],10);
	var date = new Date(yyyy,mm-1,dd,0,0,0,0);
	var utc1 = Date.UTC(date.getFullYear(), date.getMonth(), date.getDate());
	
	var today = new Date();
	var utc2 = Date.UTC(today.getFullYear(), today.getMonth(), today.getDate());
	
	var diffDays = Math.floor((utc2 - utc1) / _MS_PER_DAY);
	age = diffDays/365;
	return age;
}

j(document).ready(function() {
	var fNameHandle = j('input[name="fname"]');
	var lNameHandle = j('input[name="lname"]');
	var address1Handle = j('input[name="address1"]');
	var cityHandle = j('input[name="city"]');
	var stateHandle = j('select[name="state"]');
	var zipHandle = j('input[name="zip"]');
	var phoneHandle = j('input[name="phone"]');
	var emailHandle = j('input[name="email"]');
	var genderHandle = j('input[name="gender"]');
	var dobHandle = j('input[name="dob"]');
	var experienceHandle = j('input[name="experience"]');
	var categoryHandle = j('input[name="category"]');
	var fileHandle = j('input[name="file"]');
	var statusHandle = j('#status');
	
	fNameHandle.focus();
	
	j('input[name="submitButton"]').on('click', function(e) {
		var ageHandle = isAge(dobHandle.val());
		if(!isEmpty(fNameHandle)) {
			fNameHandle.addClass('errorClass');
			fNameHandle.focus();
			statusHandle.text("Please fill in your first name.");
			return false;
		}
		if(!isNameValid(fNameHandle)) {
			fNameHandle.addClass('errorClass');
			fNameHandle.focus();
			statusHandle.text("Please fill in a valid first name. Ex: John");
			return false;
		}
		if(!isEmpty(lNameHandle)) {
			lNameHandle.addClass('errorClass');
			lNameHandle.focus();
			statusHandle.text("Please fill in your last name.");
			return false;
		}
		if(!isNameValid(lNameHandle)) {
			lNameHandle.addClass('errorClass');
			lNameHandle.focus();
			statusHandle.text("Please fill in a valid last name. Ex: Smith");
			return false;
		}
		if(!isEmpty(address1Handle)) {
			address1Handle.addClass('errorClass');
			address1Handle.focus();
			statusHandle.text("Please fill in your address.");
			return false;
		}
		if(!isAddressValid(address1Handle)) {
			address1Handle.addClass('errorClass');
			address1Handle.focus();
			statusHandle.text("Please fill in a valid address Ex: 123 Main St.");
			return false;
		}
		if(!isEmpty(cityHandle)) {
			cityHandle.addClass('errorClass');
			cityHandle.focus();
			statusHandle.text("Please fill in your city.");
			return false;
		}
		if(!isNameValid(cityHandle)) {
			cityHandle.addClass('errorClass');
			cityHandle.focus();
			statusHandle.text("Please fill in a valid city. Ex: Seattle");
			return false;
		}
		if(stateHandle.val() == "0") {
			stateHandle.addClass('errorClass');
			stateHandle.focus()
			statusHandle.text("Please select a state.");
			return false;
		}
		if(!isEmpty(zipHandle)) {
			zipHandle.addClass('errorClass');
			zipHandle.focus();
			statusHandle.text("Please fill in your zip code.");
			return false;
		}
		if(!isValidZip(zipHandle)) {
			zipHandle.addClass('errorClass');
			zipHandle.focus();
			statusHandle.text("Please fill in a valid zip code. Ex: 11111");
			return false;
		}
		if(!isEmpty(phoneHandle)) {
			phoneHandle.addClass('errorClass');
			phoneHandle.focus();
			statusHandle.text("Please fill in your phone number.");
			return false;
		}
		if(!isValidPhone(phoneHandle)) {
			phoneHandle.addClass('errorClass');
			phoneHandle.focus();
			statusHandle.text("Please fill in a valid phone number. Ex: 555-555-5555");
			return false;
		}
		if(!isEmpty(emailHandle)) {
			emailHandle.addClass('errorClass');
			emailHandle.focus();
			statusHandle.text("Please fill in your email.");
			return false;
		}
		if(!isValidEmail(emailHandle)) {
			emailHandle.addClass('errorClass');
			emailHandle.focus();
			statusHandle.text("Please fill in a valid email. Ex: abc@def.hij");
			return false;
		}
		if(!genderHandle.is(':checked')) {
			genderHandle.addClass('errorRadio');
			genderHandle.focus();
			statusHandle.text("Please select your gender.");
			return false;
		}
		if(!isEmpty(dobHandle)) {
			dobHandle.addClass('errorClass');
			dobHandle.focus();
			statusHandle.text("Please fill in your date of birth.");
			return false;
		}
		if(!isValidBirthday(dobHandle)) {
			dobHandle.addClass('errorClass');
			dobHandle.focus();
			statusHandle.text("Please fill in a valid date of birth. Ex: 01/01/1991");
			return false;
		}
		if(ageHandle < 16) {
			dobHandle.addClass('errorClass');
			dobHandle.focus();
			statusHandle.text("You must be at least 16 years old to sign up.");
			return false;
		}
		if(!experienceHandle.is(':checked')) {
			experienceHandle.addClass('errorRadio');
			experienceHandle.focus();
			statusHandle.text("Please select your experience level.");
			return false;
		}
		if(!categoryHandle.is(':checked')) {
			categoryHandle.addClass('errorRadio');
			categoryHandle.focus();
			statusHandle.text("Please select your race category.");
			return false;
		}
		if(fileHandle.get(0).files.length === 0) {
			fileHandle.addClass('errorClass');
			fileHandle.focus();
			statusHandle.text("You must upload a profile picture.");
			return false;
		}
		e.preventDefault();
        var params = "email="+j('#email').val();
        var url = "check_dup.php?"+params;
        j.get(url, dup_handler);
	});
	
	function dup_handler(response) {
    if(response == "dup")
        j('#status').text("It appears you have already registered.");
    else if(response == "OK") {
        j('form').serialize();
        j('form').submit();
        }
    else
        alert(response);
    }
	
	fNameHandle.on('change', function() {
		statusHandle.html("&nbsp;");
		fNameHandle.removeClass('errorClass');
	});
	lNameHandle.on('change', function() {
		statusHandle.html("&nbsp;");
		lNameHandle.removeClass('errorClass');
	});
	address1Handle.on('change', function() {
		statusHandle.html("&nbsp;");
		address1Handle.removeClass('errorClass');
	});
	cityHandle.on('change', function() {
		statusHandle.html("&nbsp;");
		cityHandle.removeClass('errorClass');
	});
	stateHandle.on('change', function() {
		statusHandle.html("&nbsp;");
		stateHandle.removeClass('errorClass');
	});
	zipHandle.on('change', function() {
		statusHandle.html("&nbsp;");
		zipHandle.removeClass('errorClass');
	});
	phoneHandle.on('change', function() {
		statusHandle.html("&nbsp;");
		phoneHandle.removeClass('errorClass');
	});
	emailHandle.on('change', function() {
		statusHandle.html("&nbsp;");
		emailHandle.removeClass('errorClass');
	});
	dobHandle.on('change', function() {
		statusHandle.html("&nbsp;");
		dobHandle.removeClass('errorClass');
	});
	genderHandle.on('change',  function() {
		statusHandle.html("&nbsp;");
		genderHandle.removeClass('errorRadio');
	});
	experienceHandle.on('change', function() {
		statusHandle.html("&nbsp;");
		experienceHandle.removeClass('errorRadio');
	});
	categoryHandle.on('change', function() {
		statusHandle.html("&nbsp;");
		categoryHandle.removeClass('errorRadio');
	});
});